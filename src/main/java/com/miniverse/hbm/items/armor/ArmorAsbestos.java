package com.miniverse.hbm.items.armor;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeItem;

import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.lib.RefStrings;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

public class ArmorAsbestos extends ArmorItem implements IForgeItem {

    private ResourceLocation asbestosBlur = new ResourceLocation(HBMNuclearTechCriticality.MODID, "textures/misc/overlay_asbestos.png");

    public ArmorAsbestos(ArmorMaterial armorMaterial, EquipmentSlot slot, Properties properties) {
        super(armorMaterial, slot, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(stack.getItem().equals(ModItems.ASBESTOS_HELMET.get()) ||
                stack.getItem().equals(ModItems.ASBESTOS_PLATE.get()) ||
                stack.getItem().equals(ModItems.ASBESTOS_BOOTS.get())) {
            return HBMNuclearTechCriticality.MODID + ":textures/armor/asbestos_1.png";
        }
        if(stack.getItem().equals(ModItems.ASBESTOS_LEGS.get())) {
            return HBMNuclearTechCriticality.MODID + ":textures/armor/asbestos_2.png";
        }
        return null;
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, net.minecraft.nbt.CompoundTag nbt) {
        return new net.minecraftforge.common.capabilities.ForgeCapabilities.SpecialArmorCapability(stack) {
            @Override
            public ArmorProperties getProperties(LivingEntity player, ItemStack armor,
                                                 DamageSource source, double damage, int slot) {
                if(source.is(net.minecraft.tags.DamageTypeTags.IS_FIRE)) {
                    return new ArmorProperties(1, 1, Integer.MAX_VALUE);
                }
                return new ArmorProperties(0, 0, 0);
            }

            @Override
            public int getArmorDisplay(Player player, ItemStack armor, int slot) {
                EquipmentSlot eqSlot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, slot);

                if(eqSlot == EquipmentSlot.HEAD) {
                    return 3;
                }
                if(eqSlot == EquipmentSlot.CHEST) {
                    return 8;
                }
                if(eqSlot == EquipmentSlot.LEGS) {
                    return 6;
                }
                if(eqSlot == EquipmentSlot.FEET) {
                    return 3;
                }
                return 0;
            }

            @Override
            public void damageArmor(LivingEntity entity, ItemStack stack,
                                    DamageSource source, int damage, int slot) {
                stack.hurtAndBreak(damage, entity, (e) -> {
                    e.broadcastBreakEvent(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, slot));
                });
            }
        };
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        player.clearFire();
    }

    @OnlyIn(Dist.CLIENT)
    public void renderHelmetOverlay(GuiGraphics guiGraphics, float partialTick) {
        if(this != ModItems.ASBESTOS_HELMET.get())
            return;

        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();

        RenderSystem.setShaderTexture(0, asbestosBlur);
        guiGraphics.blit(asbestosBlur, 0, 0, 0, 0, width, height, width, height);

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}