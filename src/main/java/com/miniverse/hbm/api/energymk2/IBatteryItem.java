package com.miniverse.hbm.api.energymk2;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IBatteryItem {

    void chargeBattery(ItemStack stack, long i);
    void setCharge(ItemStack stack, long i);
    void dischargeBattery(ItemStack stack, long i);
    long getCharge(ItemStack stack);
    long getMaxCharge(ItemStack stack);
    long getChargeRate();
    long getDischargeRate();

    /** Returns a string for the NBT tag name of the long storing power */
    default String getChargeTagName() {
        return "charge";
    }

    /** Returns a string for the NBT tag name of the long storing power */
    static String getChargeTagName(ItemStack stack) {
        return ((IBatteryItem) stack.getItem()).getChargeTagName();
    }

    /** Returns an empty battery stack from the passed ItemStack, the original won't be modified */
    static ItemStack emptyBattery(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof IBatteryItem) {
            String keyName = getChargeTagName(stack);
            ItemStack stackOut = stack.copy();
            CompoundTag tag = stackOut.getOrCreateTag();
            tag.putLong(keyName, 0);
            return stackOut;
        }
        return ItemStack.EMPTY;
    }

    /** Returns an empty battery stack from the passed Item */
    static ItemStack emptyBattery(Item item) {
        return item instanceof IBatteryItem ? emptyBattery(new ItemStack(item)) : ItemStack.EMPTY;
    }
}
