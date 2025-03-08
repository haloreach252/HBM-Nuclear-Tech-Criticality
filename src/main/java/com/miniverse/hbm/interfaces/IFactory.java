package com.miniverse.hbm.interfaces;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IFactory {

    boolean isStructureValid(Level world);

    public long getPowerScaled(long i);

    public int getProgressScaled(int i);

    public boolean isProcessable(ItemStack item);
}
