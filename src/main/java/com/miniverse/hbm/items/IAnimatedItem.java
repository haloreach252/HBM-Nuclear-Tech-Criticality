package com.miniverse.hbm.items;

import com.miniverse.hbm.render.anim.BusAnimation;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IAnimatedItem {
    @OnlyIn(Dist.CLIENT)
    public BusAnimation getAnimation(CompoundTag data, ItemStack stack);
}
