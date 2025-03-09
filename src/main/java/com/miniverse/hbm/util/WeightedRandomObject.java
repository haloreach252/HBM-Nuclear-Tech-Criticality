package com.miniverse.hbm.util;

import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WeightedRandomObject implements WeightedEntry {

    private final Object item;
    private final int weight;

    public WeightedRandomObject(Object item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    public ItemStack asStack() {
        if (item instanceof ItemStack) {
            return ((ItemStack) item).copy();
        }
        return null;
    }

    public Item asItem() {
        if (item instanceof Item) {
            return (Item) item;
        }
        return null;
    }

    @Override
    public Weight getWeight() {
        return Weight.of(weight);
    }
}
