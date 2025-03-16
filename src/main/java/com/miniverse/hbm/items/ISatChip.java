package com.miniverse.hbm.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public interface ISatChip {

    static int getFreqS(ItemStack stack) {
        if(!stack.isEmpty() && stack.getItem() instanceof ISatChip) {
            return ((ISatChip) stack.getItem()).getFreq(stack);
        }

        return 0;
    }

    static void setFreqS(ItemStack stack, int freq) {
        if(!stack.isEmpty() && stack.getItem() instanceof ISatChip) {
            ((ISatChip) stack.getItem()).setFreq(stack, freq);
        }
    }

    default int getFreq(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt("freq");
    }

    default void setFreq(ItemStack stack, int freq) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("freq", freq);
    }
}