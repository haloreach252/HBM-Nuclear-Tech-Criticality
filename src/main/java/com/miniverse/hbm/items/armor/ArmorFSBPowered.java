package com.miniverse.hbm.items.armor;

import java.util.List;

import com.miniverse.hbm.handler.ArmorModHandler;
import com.miniverse.hbm.util.BobMathUtil;

import com.miniverse.hbm.api.energymk2.IBatteryItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.TooltipFlag;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ArmorFSBPowered extends ArmorFSB implements IBatteryItem {

    public long maxPower = 1;
    public long chargeRate;
    public long consumption;
    public long drain;

    public ArmorFSBPowered(ArmorMaterial material, EquipmentSlot slot, String texture, long maxPower, long chargeRate, long consumption, long drain, Properties properties) {
        super(material, slot, texture, properties);
        this.maxPower = maxPower;
        this.chargeRate = chargeRate;
        this.consumption = consumption;
        this.drain = drain;
        this.setMaxDamage(1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.literal("Charge: " + BobMathUtil.getShortNumber(getCharge(stack)) + " / " + BobMathUtil.getShortNumber(getMaxCharge(stack))));
        super.appendHoverText(stack, level, list, flag);
    }

    @Override
    public boolean isArmorEnabled(ItemStack stack) {
        return getCharge(stack) > 0;
    }

    @Override
    public void chargeBattery(ItemStack stack, long amount) {
        if(stack.getItem() instanceof ArmorFSBPowered) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putLong("charge", tag.getLong("charge") + amount);
        }
    }

    @Override
    public void setCharge(ItemStack stack, long amount) {
        if(stack.getItem() instanceof ArmorFSBPowered) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putLong("charge", amount);
        }
    }

    @Override
    public void dischargeBattery(ItemStack stack, long amount) {
        if(stack.getItem() instanceof ArmorFSBPowered) {
            CompoundTag tag = stack.getOrCreateTag();
            long newCharge = tag.getLong("charge") - amount;
            tag.putLong("charge", Math.max(0, newCharge));
        }
    }

    @Override
    public long getCharge(ItemStack stack) {
        if(stack.getItem() instanceof ArmorFSBPowered) {
            CompoundTag tag = stack.getOrCreateTag();
            if(!tag.contains("charge")) {
                tag.putLong("charge", getMaxCharge(stack));
            }
            return Math.min(tag.getLong("charge"), getMaxCharge(stack));
        }
        return 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCharge(stack) < getMaxCharge(stack);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F * (float)getCharge(stack) / (float)getMaxCharge(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float ratio = (float)getCharge(stack) / (float)getMaxCharge(stack);
        return 0xFF000000 | Math.round(ratio * 0xFF) << 8;
    }

    @Override
    public long getMaxCharge(ItemStack stack) {
        if(ArmorModHandler.hasMods(stack)) {
            ItemStack mod = ArmorModHandler.pryMod(stack, ArmorModHandler.battery);
            if(mod != null && mod.getItem() instanceof ItemModBattery) {
                return (long) (maxPower * ((ItemModBattery) mod.getItem()).mod);
            }
        }
        return maxPower;
    }

    @Override
    public long getChargeRate() {
        return chargeRate;
    }

    @Override
    public long getDischargeRate() {
        return 0;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        this.dischargeBattery(stack, damage * consumption);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);

        if(this.drain > 0 && ArmorFSB.hasFSBArmor(player) && !player.getAbilities().instabuild) {
            this.dischargeBattery(stack, drain);
        }
    }
}