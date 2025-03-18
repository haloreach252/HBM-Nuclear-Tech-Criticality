package com.miniverse.hbm.items.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.miniverse.hbm.handler.ArmorModHandler;
import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.client.render.model.ModelArmorDesh;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.model.HumanoidModel;

public class ArmorDesh extends ArmorFSBFueled {

    public ArmorDesh(ArmorMaterial material, int slot, String texture, FluidType fuelType, int maxFuel, int fillRate, int consumption, int drain) {
        super(material, slot, texture, fuelType, maxFuel, fillRate, consumption, drain);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> multimap = super.getDefaultAttributeModifiers(slot);

        if (slot == this.getSlot()) {
            multimap = ImmutableMultimap.<Attribute, AttributeModifier>builder()
                    .putAll(multimap)
                    .put(Attributes.MOVEMENT_SPEED,
                            new AttributeModifier(ArmorModHandler.fixedUUIDs[this.getSlot().getIndex()],
                                    "Armor modifier", -0.025D, AttributeModifier.Operation.MULTIPLY_TOTAL))
                    .build();
        }

        return multimap;
    }

    private ModelArmorDesh[] models;

    @Override
    public HumanoidModel<? extends LivingEntity> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<? extends LivingEntity> _default) {
        if (models == null) {
            models = new ModelArmorDesh[4];

            for (int i = 0; i < 4; i++)
                models[i] = new ModelArmorDesh(i);
        }

        return models[armorSlot.getIndex()];
    }
}