package com.miniverse.hbm.util;

import com.miniverse.hbm.config.ServerConfig;
import com.miniverse.hbm.tileentity.machine.rbmk.RBMKDials;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.List;

public class EntityDamageUtil {

    /**
     * Legacy hack: if the first attack fails due to invulnerability frames, try again with additional damage.
     * @deprecated Use attackEntityFromNT instead.
     */
    @Deprecated
    public static boolean attackEntityFromIgnoreIFrame(Entity victim, DamageSource src, float damage) {
        if (!victim.hurt(src, damage)) {
            if (victim instanceof LivingEntity living) {
                // Assume getHurtResistantTime() and getMaxHurtResistantTime() are accessible
                if (living.hurtResistantTime > living.maxHurtResistantTime / 2.0F) {
                    damage += living.lastDamage; // Assume lastDamage is accessible
                }
            }
            return victim.hurt(src, damage);
        } else {
            return true;
        }
    }

    /**
     * New and improved entity damage calculation.
     */
    public static boolean attackEntityFromNT(LivingEntity living, DamageSource source, float amount, boolean ignoreIFrame, boolean allowSpecialCancel, double knockbackMultiplier, float pierceDT, float pierce) {
        // For server players, check for PvP permissions
        if (living instanceof ServerPlayer serverPlayer && source.getEntity() instanceof Player attacker) {
            if (!serverPlayer.canHarmPlayer(attacker)) return false;
        }
        DamageResistanceHandler.setup(pierceDT, pierce);
        boolean ret = attackEntityFromNTInternal(living, source, amount, ignoreIFrame, allowSpecialCancel, knockbackMultiplier);
        DamageResistanceHandler.reset();
        return ret;
    }

    private static boolean attackEntityFromNTInternal(LivingEntity living, DamageSource source, float amount, boolean ignoreIFrame, boolean allowSpecialCancel, double knockbackMultiplier) {
        boolean superCompatibility = ServerConfig.DAMAGE_COMPATIBILITY_MODE.get();
        return superCompatibility
                ? attackEntitySuperCompatibility(living, source, amount, ignoreIFrame, allowSpecialCancel, knockbackMultiplier)
                : attackEntitySEDNAPatch(living, source, amount, ignoreIFrame, allowSpecialCancel, knockbackMultiplier);
    }

    /**
     * MK2 SEDNA damage system – uses vanilla damage calculation with tweaks.
     * (Note: This does not apply DR piercing to vanilla armor.)
     */
    private static boolean attackEntitySuperCompatibility(LivingEntity living, DamageSource source, float amount, boolean ignoreIFrame, boolean allowSpecialCancel, double knockbackMultiplier) {
        if (ignoreIFrame) {
            living.lastDamage = 0F; // assume accessible
            living.hurtResistantTime = 0;
        }
        // Cache current velocity
        Vec3 cachedVel = living.getDeltaMovement();
        // Perform damage calculation
        boolean ret = living.hurt(source, amount);
        // Restore velocity (simulate not affecting movement)
        living.setDeltaMovement(cachedVel);
        // Apply custom knockback
        Entity attacker = source.getEntity();
        if (attacker != null) {
            double deltaX = attacker.getX() - living.getX();
            double deltaZ;
            // Ensure nonzero vector
            for (deltaZ = attacker.getZ() - living.getZ(); deltaX * deltaX + deltaZ * deltaZ < 1.0E-4D; deltaZ = (Math.random() - Math.random()) * 0.01D) {
                deltaX = (Math.random() - Math.random()) * 0.01D;
            }
            living.attackedAtYaw = (float) (Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI) - living.getYRot(); // getYRot() as rotationYaw replacement
            if (knockbackMultiplier > 0)
                knockBack(living, attacker, amount, deltaX, deltaZ, knockbackMultiplier);
        }
        return ret;
    }

    /**
     * MK1 SEDNA damage system – a reimplementation of vanilla damage with modifications.
     */
    private static boolean attackEntitySEDNAPatch(LivingEntity living, DamageSource source, float amount, boolean ignoreIFrame, boolean allowSpecialCancel, double knockbackMultiplier) {
        // First, register a 0 damage hit to reset invulnerability (simulate)
        living.hurt(source, 0F);
        if (ignoreIFrame) living.lastDamage = 0F;
        if (ForgeHooks.onLivingAttack(living, source, amount) && allowSpecialCancel) return false;
        if (living.isInvulnerableTo(source)) return false;
        if (living.level.isClientSide()) return false;
        if (living instanceof Player player && player.getAbilities().invulnerable && !source.canHarmInCreative()) return false;

        living.entityAge = 0;
        if (living.getHealth() <= 0.0F) return false;
        if (source.isFire() && living.hasEffect(MobEffects.FIRE_RESISTANCE)) return false;

        living.limbSwingAmount = 1.5F;
        boolean didAttackRegister = true;

        if (living.hurtResistantTime > living.maxHurtResistantTime / 2.0F && !ignoreIFrame) {
            if (amount <= living.lastDamage) return false;
            damageEntityNT(living, source, amount - living.lastDamage);
            living.lastDamage = amount;
            didAttackRegister = false;
        } else {
            living.lastDamage = amount;
            living.prevHealth = living.getHealth(); // assume accessible
            living.hurtResistantTime = living.maxHurtResistantTime;
            damageEntityNT(living, source, amount);
            living.hurtTime = living.maxHurtTime = 10; // assume accessible
        }

        living.attackedAtYaw = 0.0F;
        Entity attacker = source.getEntity();
        if (attacker != null) {
            if (attacker instanceof LivingEntity) {
                living.setLastHurtByMob((LivingEntity) attacker); // replacement for setRevengeTarget
            }
            if (attacker instanceof Player) {
                living.recentlyHit = 100;
                living.attackingPlayer = (Player) attacker; // assume field exists or via accessor
            } else if (attacker instanceof TamableAnimal tameable) {
                if (tameable.isTame()) {
                    living.recentlyHit = 100;
                    living.attackingPlayer = null;
                }
            }
        }

        if (didAttackRegister) {
            living.level.broadcastEntityEvent(living, (byte) 2); // replacement for setEntityState
            if (source != DamageSource.DROWN) setBeenAttacked(living);
            if (attacker != null) {
                double deltaX = attacker.getX() - living.getX();
                double deltaZ;
                for (deltaZ = attacker.getZ() - living.getZ(); deltaX * deltaX + deltaZ * deltaZ < 1.0E-4D; deltaZ = (Math.random() - Math.random()) * 0.01D) {
                    deltaX = (Math.random() - Math.random()) * 0.01D;
                }
                living.attackedAtYaw = (float) (Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI) - living.getYRot();
                if (knockbackMultiplier > 0)
                    knockBack(living, attacker, amount, deltaX, deltaZ, knockbackMultiplier);
            } else {
                living.attackedAtYaw = (float) (((int) (Math.random() * 2.0D)) * 180);
            }
        }

        String sound;
        if (living.getHealth() <= 0.0F) {
            sound = getDeathSound(living);
            if (didAttackRegister && sound != null) {
                living.playSound(sound, getSoundVolume(living), getSoundPitch(living));
            }
            living.die(source); // modern replacement for onDeath
        } else {
            sound = getHurtSound(living);
            if (didAttackRegister && sound != null) {
                living.playSound(sound, getSoundVolume(living), getSoundPitch(living));
            }
        }
        return true;
    }

    /**
     * Applies custom knockback to a living entity.
     */
    public static void knockBack(LivingEntity living, Entity attacker, float damage, double motionX, double motionZ, double multiplier) {
        if (living.getRandom().nextDouble() >= living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)) {
            living.setIsAirborne(true); // assume setter exists
            double horizontal = Math.sqrt(motionX * motionX + motionZ * motionZ);
            double magnitude = 0.4D * multiplier;
            Vec3 currentVel = living.getDeltaMovement();
            // Halve current velocity
            Vec3 newVel = currentVel.scale(0.5);
            // Apply custom knockback components
            newVel = newVel.subtract(motionX / horizontal * magnitude, 0, motionZ / horizontal * magnitude)
                    .add(0, magnitude, 0);
            // Clamp vertical component
            if (newVel.y > 0.2D) {
                newVel = new Vec3(newVel.x, 0.2D * multiplier, newVel.z);
            }
            living.setDeltaMovement(newVel);
        }
    }

    /**
     * Applies damage to the entity after armor and potion calculations.
     */
    public static void damageEntityNT(LivingEntity living, DamageSource source, float amount) {
        if (!living.isInvulnerableTo(source)) {
            amount = ForgeHooks.onLivingHurt(living, source, amount);
            if (amount <= 0) return;

            amount = applyArmorCalculationsNT(living, source, amount);
            amount = applyPotionDamageCalculations(living, source, amount);

            float originalAmount = amount;
            amount = Math.max(amount - living.getAbsorptionAmount(), 0.0F);
            living.setAbsorptionAmount(living.getAbsorptionAmount() - (originalAmount - amount));

            if (amount != 0.0F) {
                float health = living.getHealth();
                living.setHealth(health - amount);
                // Record damage in combat tracker; replace func_110142_aN().func_94547_a(...) with modern method if available
                living.getCombatTracker().recordDamage(source, health, amount);
                living.setAbsorptionAmount(living.getAbsorptionAmount() - amount);
            }
        }
    }

    public static float applyArmorCalculationsNT(LivingEntity living, DamageSource source, float amount) {
        if (!source.isBypassInvul()) {
            float i = 25F - (living.getTotalArmorValue() * (1 - DamageResistanceHandler.currentPDR));
            float armor = amount * i;
            damageArmorNT(living, amount);
            amount = armor / 25.0F;
        }
        return amount;
    }

    public static void damageArmorNT(LivingEntity living, float amount) {
        // No-op; override if needed
    }

    /**
     * Legacy vanilla damage calculation – not recommended.
     * @deprecated Use attackEntityFromNT instead.
     */
    @Deprecated
    public static boolean attackEntityFromNT(LivingEntity living, DamageSource source, float amount) {
        if (ForgeHooks.onLivingAttack(living, source, amount))
            return false;
        if (living.isInvulnerableTo(source))
            return false;
        if (living.level.isClientSide())
            return false;
        if (living instanceof Player player && player.getAbilities().invulnerable && !source.canHarmInCreative())
            return false;

        living.entityAge = 0;
        if (living.getHealth() <= 0.0F)
            return false;
        if (source.isFire() && living.hasEffect(MobEffects.FIRE_RESISTANCE))
            return false;

        if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) && !living.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            // Damage the equipment in chest slot; adjust as needed
            living.getItemBySlot(EquipmentSlot.CHEST).hurtAndBreak((int) (amount * 4.0F + living.getRandom().nextFloat() * amount * 2.0F), living, (e) -> {});
            amount *= 0.75F;
        }

        living.limbSwingAmount = 1.5F;
        boolean flag = true;
        if (living.hurtResistantTime > living.maxHurtResistantTime / 2.0F) {
            if (amount <= living.lastDamage)
                return false;
            damageEntity(living, source, amount - living.lastDamage);
            living.lastDamage = amount;
            flag = false;
        } else {
            living.lastDamage = amount;
            living.prevHealth = living.getHealth();
            living.hurtResistantTime = living.maxHurtResistantTime;
            damageEntity(living, source, amount);
            living.hurtTime = living.maxHurtTime = 10;
        }

        living.attackedAtYaw = 0.0F;
        Entity attacker = source.getEntity();
        if (attacker != null) {
            if (attacker instanceof LivingEntity)
                living.setLastHurtByMob((LivingEntity) attacker);
            if (attacker instanceof Player) {
                living.recentlyHit = 100;
                living.attackingPlayer = (Player) attacker;
            } else if (attacker instanceof TamableAnimal tameable) {
                if (tameable.isTame()) {
                    living.recentlyHit = 100;
                    living.attackingPlayer = null;
                }
            }
        }
        if (flag) {
            living.level.broadcastEntityEvent(living, (byte) 2);
            if (source != DamageSource.DROWN)
                setBeenAttacked(living);
            if (attacker != null) {
                double deltaX = attacker.getX() - living.getX();
                double deltaZ;
                for (deltaZ = attacker.getZ() - living.getZ(); deltaX * deltaX + deltaZ * deltaZ < 1.0E-4D; deltaZ = (Math.random() - Math.random()) * 0.01D) {
                    deltaX = (Math.random() - Math.random()) * 0.01D;
                }
                living.attackedAtYaw = (float) (Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI) - living.getYRot();
                living.knockback(attacker, amount, deltaX, deltaZ);
            } else {
                living.attackedAtYaw = (float) (((int) (Math.random() * 2.0D)) * 180);
            }
        }
        String s;
        if (living.getHealth() <= 0.0F) {
            s = getDeathSound(living);
            if (flag && s != null)
                living.playSound(s, getSoundVolume(living), getSoundPitch(living));
            living.die(source);
        } else {
            s = getHurtSound(living);
            if (flag && s != null)
                living.playSound(s, getSoundVolume(living), getSoundPitch(living));
        }
        return true;
    }

    public static String getDeathSound(LivingEntity living) {
        Method m = ObfuscationReflectionHelper.findMethod(LivingEntity.class, living, new String[] {"m_21430_", "getDeathSound"});
        try {
            return (String) m.invoke(living);
        } catch (Exception e) { }
        return "game.neutral.die";
    }

    public static String getHurtSound(LivingEntity living) {
        Method m = ObfuscationReflectionHelper.findMethod(LivingEntity.class, living, new String[] {"m_21380_", "getHurtSound"});
        try {
            return (String) m.invoke(living);
        } catch (Exception e) { }
        return "game.neutral.hurt";
    }

    public static float getSoundVolume(LivingEntity living) {
        Method m = ObfuscationReflectionHelper.findMethod(LivingEntity.class, living, new String[] {"m_21389_", "getSoundVolume"});
        try {
            return (float) m.invoke(living);
        } catch (Exception e) { }
        return 1F;
    }

    public static float getSoundPitch(LivingEntity living) {
        Method m = ObfuscationReflectionHelper.findMethod(LivingEntity.class, living, new String[] {"m_21391_", "getSoundPitch"});
        try {
            return (float) m.invoke(living);
        } catch (Exception e) { }
        return 1F;
    }

    @Deprecated
    public static void damageEntity(LivingEntity living, DamageSource source, float amount) {
        if (!living.isInvulnerableTo(source)) {
            amount = ForgeHooks.onLivingHurt(living, source, amount);
            if (amount <= 0) return;
            amount = applyArmorCalculations(living, source, amount);
            amount = applyPotionDamageCalculations(living, source, amount);
            float f1 = amount;
            amount = Math.max(amount - living.getAbsorptionAmount(), 0.0F);
            living.setAbsorptionAmount(living.getAbsorptionAmount() - (f1 - amount));
            if (amount != 0.0F) {
                float f2 = living.getHealth();
                living.setHealth(f2 - amount);
                living.getCombatTracker().recordDamage(source, f2, amount);
                living.setAbsorptionAmount(living.getAbsorptionAmount() - amount);
            }
        }
    }

    @Deprecated
    public static float applyArmorCalculations(LivingEntity living, DamageSource source, float amount) {
        if (!source.isBypassInvul()) {
            int i = 25 - living.getTotalArmorValue();
            float armor = amount * i;
            // living.damageArmor(amount); // not used
            amount = armor / 25.0F;
        }
        return amount;
    }

    public static float applyPotionDamageCalculations(LivingEntity living, DamageSource source, float amount) {
        if (source.isMagic()) { // Using isMagic as a proxy for absolute damage
            return amount;
        } else {
            int resistance;
            int j;
            float f1;
            if (living.hasEffect(MobEffects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
                resistance = (living.getEffect(MobEffects.RESISTANCE).getAmplifier() + 1) * 5;
                j = 25 - resistance;
                f1 = amount * j;
                amount = f1 / 25.0F;
            }
            if (amount <= 0.0F) {
                return 0.0F;
            } else {
                resistance = net.minecraft.world.item.enchantment.EnchantmentHelper.getEnchantmentDamageBonus(living.getAllSlots(), source);
                if (resistance > 20) resistance = 20;
                if (resistance > 0 && resistance <= 20) {
                    j = 25 - resistance;
                    f1 = amount * j;
                    amount = f1 / 25.0F;
                }
                return amount;
            }
        }
    }

    public static void setBeenAttacked(LivingEntity living) {
        // Simulate setting "velocityChanged" based on a random check against knockback resistance.
        living.velocityChanged = living.getRandom().nextDouble() >= living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
    }

    // --- Ray tracing / mouse over methods ---

    /**
     * Returns the current HitResult for the given attacker (e.g. for selecting an entity in the world).
     */
    public static HitResult getMouseOver(Player attacker, double reach) {
        Level level = attacker.level;
        HitResult objectMouseOver = rayTrace(attacker, reach, 1F);
        Vec3 pos = getPosition(attacker);
        Vec3 look = attacker.getLookAngle();
        Vec3 end = pos.add(look.scale(reach));
        Vec3 hitvec = null;
        float grace = 1.0F;
        List<Entity> list = level.getEntities(attacker, attacker.getBoundingBox().expandTowards(look.scale(reach)).inflate(grace));
        double closest = reach;
        Entity pointedEntity = null;
        for (Entity entity : list) {
            if (entity.isPickable()) {
                float borderSize = entity.getPickRadius();
                AABB aabb = entity.getBoundingBox().inflate(borderSize);
                HitResult movingobjectposition = aabb.clip(pos, end);
                if (aabb.contains(pos)) {
                    if (0.0D <= closest) {
                        pointedEntity = entity;
                        hitvec = (movingobjectposition == null) ? pos : movingobjectposition.getLocation();
                        closest = 0.0D;
                    }
                } else if (movingobjectposition != null) {
                    double dist = pos.distanceTo(movingobjectposition.getLocation());
                    if (dist < closest || closest == 0.0D) {
                        if (entity == attacker.getVehicle() && !entity.canRiderInteract()) {
                            if (closest == 0.0D) {
                                pointedEntity = entity;
                                hitvec = movingobjectposition.getLocation();
                            }
                        } else {
                            pointedEntity = entity;
                            hitvec = movingobjectposition.getLocation();
                            closest = dist;
                        }
                    }
                }
            }
        }
        if (pointedEntity != null && (closest < reach || objectMouseOver == null)) {
            objectMouseOver = HitResult.entity(pointedEntity, hitvec);
        }
        return objectMouseOver;
    }

    /**
     * Performs a ray trace from the player's eyes.
     */
    public static HitResult rayTrace(Player player, double dist, float interp) {
        Vec3 pos = getPosition(player);
        Vec3 look = player.getLookAngle();
        Vec3 end = pos.add(look.scale(dist));
        return player.level.clip(new net.minecraft.world.phys.ClipContext(pos, end,
                net.minecraft.world.phys.ClipContext.Block.OUTLINE, net.minecraft.world.phys.ClipContext.Fluid.NONE, player));
    }

    /**
     * Returns the eye position of the player as a Vec3.
     */
    public static Vec3 getPosition(Player player) {
        return new Vec3(player.getX(), player.getEyeY(), player.getZ());
    }
}
