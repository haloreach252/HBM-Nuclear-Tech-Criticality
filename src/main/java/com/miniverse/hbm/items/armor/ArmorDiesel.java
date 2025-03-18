package com.miniverse.hbm.items.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.handler.ArmorModHandler;
import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.Fluids;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

import com.miniverse.hbm.render.model.ModelArmorDiesel;

public class ArmorDiesel extends ArmorFSBFueled {

    public ArmorDiesel(ArmorMaterial material, Type slot, Properties properties, String texture, FluidType fuelType, int maxFuel, int fillRate, int consumption, int drain) {
        super(material, slot, properties, texture, fuelType, maxFuel, fillRate, consumption, drain);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();

        if (slot == this.slot) {
            multimap.put(Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(ArmorModHandler.fixedUUIDs[this.slot.getIndex()],
                            "Armor modifier", 0.25D, AttributeModifier.Operation.MULTIPLY_BASE));
        }

        return multimap;
    }

    @OnlyIn(Dist.CLIENT)
    ModelArmorDiesel[] models;

    @Override
    @OnlyIn(Dist.CLIENT)
    public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if(models == null) {
            models = new ModelArmorDiesel[4];

            for(int i = 0; i < 4; i++)
                models[i] = new ModelArmorDiesel(i);
        }

        return models[armorSlot.getIndex()];
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        super.onArmorTick(stack, world, player);

        if(!world.isClientSide && this == ModItems.dieselsuit_legs && this.hasFSBArmor(player) && world.getGameTime() % 3 == 0) {
            CompoundTag data = new CompoundTag();
            data.putString("type", "bnuuy");
            data.putInt("player", player.getId());

            PacketDispatcher.wrapper.send(
                    PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(
                            player.getX(), player.getY(), player.getZ(), 100, world.dimension())),
                    new AuxParticlePacketNT(data, player.getX(), player.getY(), player.getZ())
            );
        }
    }

    @Override
    public boolean acceptsFluid(FluidType type, ItemStack stack) {
        return type == Fluids.DIESEL || type == Fluids.DIESEL_CRACK;
    }
}