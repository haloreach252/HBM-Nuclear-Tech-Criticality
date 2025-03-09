package com.miniverse.hbm.potion;

import com.miniverse.hbm.capabilities.CustomCapabilities;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;

import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.config.ServerConfig;
import com.miniverse.hbm.entity.mob.EntityTaintCrab;
import com.miniverse.hbm.entity.mob.EntityCreeperTainted;
import com.miniverse.hbm.explosion.ExplosionLarge;
import com.miniverse.hbm.extprop.HbmLivingProps;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.lib.ModDamageSource;
import com.miniverse.hbm.sound.ModSoundEvents;
import com.miniverse.hbm.util.ContaminationUtil;
import com.miniverse.hbm.util.ContaminationUtil.ContaminationType;
import com.miniverse.hbm.util.ContaminationUtil.HazardType;

public class HbmPotion extends MobEffect {

    public static HbmPotion taint;
    public static HbmPotion radiation;
    public static HbmPotion bang;
    public static HbmPotion mutation;
    public static HbmPotion radx;
    public static HbmPotion lead;
    public static HbmPotion radaway;
    //public static HbmPotion telekinesis;
    public static HbmPotion phosphorus;
    public static HbmPotion stability;
    public static HbmPotion potionsickness;
    public static HbmPotion death;

    // Optional fields to store GUI icon coordinates and a potion name.
    private int iconX;
    private int iconY;
    private String potionName;

    public HbmPotion(boolean isBad, int color) {
        super(isBad ? MobEffectCategory.HARMFUL : MobEffectCategory.BENEFICIAL, color);
    }

    public void setPotionName(String name) {
        this.potionName = name;
    }

    public void setIconIndex(int x, int y) {
        this.iconX = x;
        this.iconY = y;
    }

    /**
     * Initializes all custom potions.
     * In 1.20.1 potions should be registered via DeferredRegister, so this method is for legacy use only.
     */
    public static void init() {
        taint = registerPotion(true, 0x800080, "potion.hbm_taint", 0, 0);
        radiation = registerPotion(true, 0x84C128, "potion.hbm_radiation", 1, 0);
        bang = registerPotion(true, 0x111111, "potion.hbm_bang", 3, 0);
        mutation = registerPotion(false, 0x800080, "potion.hbm_mutation", 2, 0);
        radx = registerPotion(false, 0xBB4B00, "potion.hbm_radx", 5, 0);
        lead = registerPotion(true, 0x767682, "potion.hbm_lead", 6, 0);
        radaway = registerPotion(false, 0xBB4B00, "potion.hbm_radaway", 7, 0);
        //telekinesis = registerPotion(true, 0x00F3FF, "potion.hbm_telekinesis", 0, 1);
        phosphorus = registerPotion(true, 0xFFFF00, "potion.hbm_phosphorus", 1, 1);
        stability = registerPotion(false, 0xD0D0D0, "potion.hbm_stability", 2, 1);
        potionsickness = registerPotion(false, 0xff8080, "potion.hbm_potionsickness", 3, 1);
        death = registerPotion(false, 0x111111, "potion.hbm_death", 4, 1);
    }

    /**
     * Creates a new instance of HbmPotion. Note that the numeric ID parameter from 1.7.10 is removed.
     */
    public static HbmPotion registerPotion(boolean isBad, int color, String name, int x, int y) {
        HbmPotion effect = new HbmPotion(isBad, color);
        effect.setPotionName(name);
        effect.setIconIndex(x, y);
        return effect;
    }

    /**
     * This method is called each tick for entities affected by this potion.
     * It replaces the 1.7.10 performEffect(EntityLivingBase, int) method.
     */
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level.isClientSide())
            return;

        if (this == taint) {
            if (!(entity instanceof EntityCreeperTainted) && !(entity instanceof EntityTaintCrab)
                    && entity.level.random.nextInt(40) == 0) {
                entity.hurt(ModDamageSource.taint, (amplifier + 1));
            }

            if (ServerConfig.TAINT_TRAILS.get()) {
                int x = Mth.floor(entity.getX());
                int y = Mth.floor(entity.getY());
                int z = Mth.floor(entity.getZ());
                BlockPos posBelow = new BlockPos(x, y - 1, z);
                BlockState stateBelow = entity.level.getBlockState(posBelow);
                // Check that the block below is a solid block and not air.
                if (y > 1 && stateBelow.isSolidRender(entity.level, posBelow) && !entity.level.isEmptyBlock(posBelow)) {
                    entity.level.setBlockAndUpdate(posBelow, ModBlocks.taint.defaultBlockState());
                }
            }
        }
        if (this == radiation) {
            ContaminationUtil.contaminate(entity, HazardType.RADIATION, ContaminationType.CREATIVE,
                    (float) (amplifier + 1) * 0.05F);
        }
        if (this == radaway) {
            entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.incrementRadiation(-(amplifier + 1)));
        }
        if (this == bang) {
            entity.hurt(ModDamageSource.bang, 1000);
            entity.setHealth(0.0F);

            if (!(entity instanceof Player)) {
                entity.remove();
            }

            entity.level.playSound(null, entity.blockPosition(), ModSoundEvents.LASER_BANG,
                    net.minecraft.sounds.SoundSource.HOSTILE, 100.0F, 1.0F);
            ExplosionLarge.spawnParticles(entity.level, entity.getX(), entity.getY(), entity.getZ(), 10);

            if (entity instanceof Cow) {
                Cow cow = (Cow) entity;
                int toDrop = cow.isBaby() ? 10 : 3;
                cow.spawnAtLocation(ModItems.cheese, toDrop);
            }
        }
        if (this == lead) {
            entity.hurt(ModDamageSource.lead, (amplifier + 1));
        }
        if (this == phosphorus) {
            entity.setSecondsOnFire(1);
        }
    }

    /**
     * Determines whether the effect should be applied on a given tick.
     * This replaces the old isReady(int, int) method.
     */
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        if (this == taint) {
            return duration % 2 == 0;
        }
        if (this == radiation || this == radaway || /* this == telekinesis || */ this == phosphorus) {
            return true;
        }
        if (this == bang) {
            return duration <= 10;
        }
        if (this == lead) {
            int k = 60;
            return k > 0 ? duration % k == 0 : true;
        }
        return false;
    }

    /**
     * Utility method to check if a given effect is harmful.
     */
    public static boolean getIsBadEffect(MobEffect effect) {
        return effect.getCategory() == MobEffectCategory.HARMFUL;
    }
}
