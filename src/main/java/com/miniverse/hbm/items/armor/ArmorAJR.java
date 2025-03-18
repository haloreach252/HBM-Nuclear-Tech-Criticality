package com.miniverse.hbm.items.armor;

import com.miniverse.hbm.client.model.armor.ModelArmorAJR;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ArmorAJR extends ArmorFSBPowered {

    public ArmorAJR(ArmorMaterial material, EquipmentSlot slot, String texture, long maxPower, long chargeRate, long consumption, long drain, Properties properties) {
        super(material, slot, texture, maxPower, chargeRate, consumption, drain, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {
                ModelArmorAJR model = ModelArmorAJR.getModel(armorSlot);

                // Set up model properties based on entity
                model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                model.setupAnim(entityLiving, 0, 0, 0, 0, 0);

                return model;
            }
        });
    }
}