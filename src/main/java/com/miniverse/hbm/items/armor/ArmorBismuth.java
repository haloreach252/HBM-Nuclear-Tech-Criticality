package com.miniverse.hbm.items.armor;

import com.miniverse.hbm.client.model.armor.ModelArmorBismuth;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ArmorBismuth extends ArmorFSB {

    public ArmorBismuth(ArmorMaterial material, EquipmentSlot slot, String texture, Properties properties) {
        super(material, slot, texture, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @OnlyIn(Dist.CLIENT)
            private ModelArmorBismuth[] models;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {
                if(models == null) {
                    models = new ModelArmorBismuth[4];

                    for(int i = 0; i < 4; i++)
                        models[i] = new ModelArmorBismuth(i);
                }

                int index;
                switch(armorSlot) {
                    case HEAD: index = 0; break;
                    case CHEST: index = 1; break;
                    case LEGS: index = 2; break;
                    case FEET: index = 3; break;
                    default: return defaultModel;
                }

                ModelArmorBismuth model = models[index];

                // Set up model properties based on entity
                model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                model.setupAnim(entityLiving, 0, 0, 0, 0, 0);

                return model;
            }
        });
    }
}