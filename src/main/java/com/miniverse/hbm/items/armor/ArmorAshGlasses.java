package com.miniverse.hbm.items.armor;

import com.miniverse.hbm.client.model.armor.ModelGlasses;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ArmorAshGlasses extends ArmorItem {

    public ArmorAshGlasses(ArmorMaterial armorMaterial, EquipmentSlot slot, Properties properties) {
        super(armorMaterial, slot, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private ModelGlasses model;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {
                if(model == null) {
                    model = new ModelGlasses(0);

                    // Set up model properties based on entity
                    model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                    model.setupAnim(entityLiving, 0, 0, 0, 0, 0);
                }

                model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                model.setupAnim(entityLiving, 0, 0, 0, 0, 0);

                return model;
            }
        });
    }
}