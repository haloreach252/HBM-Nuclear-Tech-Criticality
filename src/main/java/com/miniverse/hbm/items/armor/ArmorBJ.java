package com.miniverse.hbm.items.armor;

import com.miniverse.hbm.client.model.armor.ModelArmorBJ;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.lib.ModDamageSource;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ArmorBJ extends ArmorFSBPowered {

    public ArmorBJ(ArmorMaterial material, EquipmentSlot slot, String texture, long maxPower, long chargeRate, long consumption, long drain, Properties properties) {
        super(material, slot, texture, maxPower, chargeRate, consumption, drain, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @OnlyIn(Dist.CLIENT)
            private ModelArmorBJ[] models;

            @Override
            @OnlyIn(Dist.CLIENT)
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> defaultModel) {
                if(models == null) {
                    models = new ModelArmorBJ[4];

                    for(int i = 0; i < 4; i++)
                        models[i] = new ModelArmorBJ(i);
                }

                int index;
                switch(armorSlot) {
                    case HEAD: index = 0; break;
                    case CHEST: index = 1; break;
                    case LEGS: index = 2; break;
                    case FEET: index = 3; break;
                    default: return defaultModel;
                }

                ModelArmorBJ model = models[index];

                // Set up model properties based on entity
                model.prepareMobModel(entityLiving, 0, 0, 1.0F);
                model.setupAnim(entityLiving, 0, 0, 0, 0, 0);

                return model;
            }
        });
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);

        if(this == ModItems.BJ_HELMET.get() && ArmorFSB.hasFSBArmorIgnoreCharge(player) && !ArmorFSB.hasFSBArmor(player)) {
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

            if(!helmet.isEmpty()) {
                // Try to add the helmet to inventory, drop it if can't add
                if(!player.getInventory().add(helmet)) {
                    player.drop(helmet, false);
                }

                // Clear the helmet slot
                player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);

                // Apply damage
                player.hurt(level.damageSources().source(ModDamageSource.LUNAR), 1000);
            }
        }
    }
}