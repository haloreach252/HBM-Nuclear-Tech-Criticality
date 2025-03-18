package com.miniverse.hbm.items.armor;

import com.miniverse.hbm.render.model.ModelArmorDigamma;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorDigamma extends ArmorFSBPowered {

    public ArmorDigamma(ArmorMaterial material, Type slot, Properties properties, String texture, long maxPower, long chargeRate, long consumption, long drain) {
        super(material, slot, properties, texture, maxPower, chargeRate, consumption, drain);
    }

    @OnlyIn(Dist.CLIENT)
    ModelArmorDigamma[] models;

    @Override
    @OnlyIn(Dist.CLIENT)
    public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if(models == null) {
            models = new ModelArmorDigamma[4];

            for(int i = 0; i < 4; i++)
                models[i] = new ModelArmorDigamma(i);
        }

        return models[armorSlot.getIndex()];
    }
}