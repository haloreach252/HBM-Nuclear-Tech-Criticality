package com.miniverse.hbm.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface IItemControlReceiver {
    public void receiveControl(ItemStack stack, CompoundTag data);
}
