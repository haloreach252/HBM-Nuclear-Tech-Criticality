package com.miniverse.hbm.items.armor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import org.joml.Vector3d;

import com.miniverse.hbm.capabilities.HbmLivingProps;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.util.ContaminationUtil;
import com.miniverse.hbm.util.I18nUtil;
import com.miniverse.hbm.handler.radiation.ChunkRadiationManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

//Armor with full set bonus
public class ArmorFSB extends ArmorItem implements IArmorDisableModel {

    private String texture = "";
    private ResourceLocation overlay = null;
    public List<MobEffectInstance> effects = new ArrayList<>();
    public boolean noHelmet = false;
    public boolean vats = false;
    public boolean thermal = false;
    public boolean geigerSound = false;
    public boolean customGeiger = false;
    public boolean hardLanding = false;
    public int dashCount = 0;
    public int stepSize = 0;
    public String step;
    public String jump;
    public String fall;

    public ArmorFSB(ArmorMaterial material, EquipmentSlot slot, String texture, Properties properties) {
        super(material, slot, properties);
        this.texture = texture;
    }

    public ArmorFSB addEffect(MobEffectInstance effect) {
        effects.add(effect);
        return this;
    }

    public ArmorFSB setNoHelmet(boolean noHelmet) {
        this.noHelmet = noHelmet;
        return this;
    }

    public ArmorFSB enableVATS(boolean vats) {
        this.vats = vats;
        return this;
    }

    public ArmorFSB enableThermalSight(boolean thermal) {
        this.thermal = thermal;
        return this;
    }

    public ArmorFSB setHasGeigerSound(boolean geiger) {
        this.geigerSound = geiger;
        return this;
    }

    public ArmorFSB setHasCustomGeiger(boolean geiger) {
        this.customGeiger = geiger;
        return this;
    }

    public ArmorFSB setHasHardLanding(boolean hardLanding) {
        this.hardLanding = hardLanding;
        return this;
    }

    public ArmorFSB setDashCount(int dashCount) {
        this.dashCount = dashCount;
        return this;
    }

    public ArmorFSB setStepSize(int stepSize) {
        this.stepSize = stepSize;
        return this;
    }

    public ArmorFSB setStep(String step) {
        this.step = step;
        return this;
    }

    public ArmorFSB setJump(String jump) {
        this.jump = jump;
        return this;
    }

    public ArmorFSB setFall(String fall) {
        this.fall = fall;
        return this;
    }

    public ArmorFSB setOverlay(String path) {
        this.overlay = new ResourceLocation(path);
        return this;
    }

    public ArmorFSB cloneStats(ArmorFSB original) {
        //lists aren't being modified after instantiation, so there's no need to dereference
        this.effects = original.effects;
        this.noHelmet = original.noHelmet;
        this.vats = original.vats;
        this.thermal = original.thermal;
        this.geigerSound = original.geigerSound;
        this.customGeiger = original.customGeiger;
        this.hardLanding = original.hardLanding;
        this.dashCount = original.dashCount;
        this.stepSize = original.stepSize;
        this.step = original.step;
        this.jump = original.jump;
        this.fall = original.fall;
        //overlay doesn't need to be copied because it's helmet exclusive
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return texture;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        List<Component> toAdd = new ArrayList<>();

        if(!effects.isEmpty()) {
            List<String> potionList = new ArrayList<>();
            for(MobEffectInstance effect : effects) {
                potionList.add(Component.translatable(effect.getEffect().getDescriptionId()).getString());
            }

            toAdd.add(Component.literal(String.join(", ", potionList)).withStyle(ChatFormatting.AQUA));
        }

        if(geigerSound) toAdd.add(Component.translatable("armor.geigerSound").withStyle(ChatFormatting.GOLD));
        if(customGeiger) toAdd.add(Component.translatable("armor.geigerHUD").withStyle(ChatFormatting.GOLD));
        if(vats) toAdd.add(Component.translatable("armor.vats").withStyle(ChatFormatting.RED));
        if(thermal) toAdd.add(Component.translatable("armor.thermal").withStyle(ChatFormatting.RED));
        if(hardLanding) toAdd.add(Component.translatable("armor.hardLanding").withStyle(ChatFormatting.RED));
        if(stepSize != 0) toAdd.add(Component.translatable("armor.stepSize", stepSize).withStyle(ChatFormatting.BLUE));
        if(dashCount > 0) toAdd.add(Component.translatable("armor.dash", dashCount).withStyle(ChatFormatting.AQUA));

        if(!toAdd.isEmpty()) {
            list.add(Component.translatable("armor.fullSetBonus").withStyle(ChatFormatting.GOLD));
            list.addAll(toAdd);
        }
    }

    public static boolean hasFSBArmor(Player player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if(!chest.isEmpty() && chest.getItem() instanceof ArmorFSB) {
            ArmorFSB chestplate = (ArmorFSB) chest.getItem();
            boolean noHelmet = chestplate.noHelmet;

            // Check all armor slots
            for(EquipmentSlot slot : new EquipmentSlot[] {
                    noHelmet ? null : EquipmentSlot.HEAD,
                    EquipmentSlot.CHEST,
                    EquipmentSlot.LEGS,
                    EquipmentSlot.FEET
            }) {
                if(slot == null) continue;

                ItemStack armor = player.getItemBySlot(slot);

                if(armor.isEmpty() || !(armor.getItem() instanceof ArmorFSB))
                    return false;

                if(((ArmorFSB) armor.getItem()).getMaterial() != chestplate.getMaterial())
                    return false;

                if(!((ArmorFSB) armor.getItem()).isArmorEnabled(armor))
                    return false;
            }

            return true;
        }

        return false;
    }

    public static boolean hasFSBArmorIgnoreCharge(Player player) {
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if(!chest.isEmpty() && chest.getItem() instanceof ArmorFSB) {
            ArmorFSB chestplate = (ArmorFSB) chest.getItem();
            boolean noHelmet = chestplate.noHelmet;

            // Check all armor slots
            for(EquipmentSlot slot : new EquipmentSlot[] {
                    noHelmet ? null : EquipmentSlot.HEAD,
                    EquipmentSlot.CHEST,
                    EquipmentSlot.LEGS,
                    EquipmentSlot.FEET
            }) {
                if(slot == null) continue;

                ItemStack armor = player.getItemBySlot(slot);

                if(armor.isEmpty() || !(armor.getItem() instanceof ArmorFSB))
                    return false;

                if(((ArmorFSB) armor.getItem()).getMaterial() != chestplate.getMaterial())
                    return false;
            }

            return true;
        }

        return false;
    }

    public void handleTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if(ArmorFSB.hasFSBArmor(player)) {
            ItemStack plate = player.getItemBySlot(EquipmentSlot.CHEST);
            ArmorFSB chestplate = (ArmorFSB) plate.getItem();

            if(!chestplate.effects.isEmpty()) {
                for(MobEffectInstance i : chestplate.effects) {
                    player.addEffect(new MobEffectInstance(
                            i.getEffect(),
                            i.getDuration(),
                            i.getAmplifier(),
                            true,
                            false
                    ));
                }
            }

            if(chestplate.step != null && player.level().isClientSide && player.onGround()) {
                // Handle step sounds
                // Since reflection no longer works, we use a simpler approach with persistent data
                float nextStepDistance = player.getPersistentData().getFloat("hfr_nextStepDistance");
                float distanceWalkedOnStep = player.walkDist;

                if(nextStepDistance == 0) {
                    player.getPersistentData().putFloat("hfr_nextStepDistance", distanceWalkedOnStep + 1);
                    nextStepDistance = distanceWalkedOnStep + 1;
                }

                BlockPos pos = player.blockPosition().below();
                BlockState blockState = player.level().getBlockState(pos);

                if(!blockState.isAir() && nextStepDistance <= distanceWalkedOnStep) {
                    player.playSound(
                            player.level().registryAccess()
                                    .registryOrThrow(net.minecraft.core.Registry.SOUND_EVENT_REGISTRY)
                                    .get(new ResourceLocation(chestplate.step)),
                            1.0F,
                            1.0F
                    );
                    player.getPersistentData().putFloat("hfr_nextStepDistance", distanceWalkedOnStep + 1);
                }
            }
        }
    }

    public void handleJump(Player player) {
        if(ArmorFSB.hasFSBArmor(player)) {
            ArmorFSB chestplate = (ArmorFSB) player.getItemBySlot(EquipmentSlot.CHEST).getItem();

            if(chestplate.jump != null) {
                player.playSound(
                        player.level().registryAccess()
                                .registryOrThrow(net.minecraft.core.Registry.SOUND_EVENT_REGISTRY)
                                .get(new ResourceLocation(chestplate.jump)),
                        1.0F,
                        1.0F
                );
            }
        }
    }

    public void handleFall(Player player) {
        if(ArmorFSB.hasFSBArmor(player)) {
            ArmorFSB chestplate = (ArmorFSB) player.getItemBySlot(EquipmentSlot.CHEST).getItem();

            if(chestplate.hardLanding && player.fallDistance > 10) {
                // Player has landed hard, affect nearby entities
                List<Entity> entities = player.level().getEntitiesOfClass(
                        Entity.class,
                        player.getBoundingBox().inflate(3, 0, 3),
                        entity -> entity != player && !(entity instanceof ItemEntity)
                );

                for(Entity e : entities) {
                    Vec3 vec = new Vec3(player.getX() - e.getX(), 0, player.getZ() - e.getZ());
                    double length = vec.length();

                    if(length < 3) {
                        double intensity = 3 - length;
                        e.setDeltaMovement(
                                e.getDeltaMovement().add(
                                        vec.x * intensity * -2,
                                        0.1D * intensity,
                                        vec.z * intensity * -2
                                )
                        );

                        e.hurt(player.level().damageSources().playerAttack(player), (float) (intensity * 10));
                    }
                }
            }

            if(chestplate.fall != null) {
                player.playSound(
                        player.level().registryAccess()
                                .registryOrThrow(net.minecraft.core.Registry.SOUND_EVENT_REGISTRY)
                                .get(new ResourceLocation(chestplate.fall)),
                        1.0F,
                        1.0F
                );
            }
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(this.getSlot() != EquipmentSlot.CHEST) return;
        if(!hasFSBArmor(player) || !this.geigerSound) return;

        // Check if player has geiger counter items
        boolean hasGeiger = player.getInventory().contains(new ItemStack(ModItems.GEIGER_COUNTER.get()));
        boolean hasDosimeter = player.getInventory().contains(new ItemStack(ModItems.DOSIMETER.get()));

        if(hasGeiger || hasDosimeter) return;

        if(level.getGameTime() % 5 == 0) {
            // Armor piece dosimeters indicate radiation dosage inside the armor, so reduce the counts by the effective protection
            float mod = ContaminationUtil.calculateRadiationMod(player);
            float x = HbmLivingProps.getRadBuf(player) * mod;

            if(x > 1E-5) {
                List<Integer> soundList = new ArrayList<>();

                if(x < 1) soundList.add(0);
                if(x < 5) soundList.add(0);
                if(x < 10) soundList.add(1);
                if(x > 5 && x < 15) soundList.add(2);
                if(x > 10 && x < 20) soundList.add(3);
                if(x > 15 && x < 25) soundList.add(4);
                if(x > 20 && x < 30) soundList.add(5);
                if(x > 25) soundList.add(6);

                int r = soundList.get(level.getRandom().nextInt(soundList.size()));

                if(r > 0) {
                    player.level().playSound(
                            null,
                            player.blockPosition(),
                            player.level().registryAccess()
                                    .registryOrThrow(net.minecraft.core.Registry.SOUND_EVENT_REGISTRY)
                                    .get(new ResourceLocation("hbm:item.geiger" + r)),
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F
                    );
                }
            }
        }
    }

    public static int check(Level level, int x, int y, int z) {
        int rads = (int) Math.ceil(ChunkRadiationManager.proxy.getRadiation(level, x, y, z));
        return rads;
    }

    // For crazier stuff not possible without hooking the event
    @OnlyIn(Dist.CLIENT)
    public void handleOverlay(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        // This method left intentionally empty for override
    }

    public boolean isArmorEnabled(ItemStack stack) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void renderHelmetOverlay(GuiGraphics guiGraphics, float partialTick) {
        if(overlay == null)
            return;

        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();

        RenderSystem.setShaderTexture(0, overlay);
        guiGraphics.blit(overlay, 0, 0, 0, 0, width, height, width, height);

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private HashSet<EnumPlayerPart> hidden = new HashSet<>();
    private boolean needsFullSet = false;

    public ArmorFSB hides(EnumPlayerPart... parts) {
        Collections.addAll(hidden, parts);
        return this;
    }

    public ArmorFSB setFullSetForHide() {
        needsFullSet = true;
        return this;
    }

    @Override
    public boolean disablesPart(Player player, ItemStack stack, EnumPlayerPart part) {
        return hidden.contains(part) && (!needsFullSet || hasFSBArmorIgnoreCharge(player));
    }

    public void handleAttack(LivingAttackEvent event) {
        // This method left intentionally empty for override
    }

    public void handleHurt(LivingHurtEvent event) {
        // This method left intentionally empty for override
    }
}