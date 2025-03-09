package com.miniverse.hbm.util;

import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;

public class WeightedRandomGeneric<T> implements WeightedEntry {

    private final T item;
    private final int weight;

    public WeightedRandomGeneric(T item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    public T get() {
        return item;
    }

    @Override
    public Weight getWeight() {
        return Weight.of(weight);
    }
}
