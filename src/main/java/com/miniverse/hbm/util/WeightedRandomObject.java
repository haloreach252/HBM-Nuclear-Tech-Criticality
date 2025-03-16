package com.miniverse.hbm.util;

import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class WeightedRandomObject implements WeightedEntry {
    private final Object item;
    private final int weight;

    public WeightedRandomObject(Object item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    @Nullable
    public ItemStack asStack() {
        if (item instanceof ItemStack stack) {
            return stack.copy();
        }
        return null;
    }

    @Nullable
    public Item asItem() {
        if (item instanceof Item item) {
            return item;
        }
        return null;
    }

    @Override
    public Weight getWeight() {
        return Weight.of(weight);
    }

    public Object getItem() {
        return item;
    }
}