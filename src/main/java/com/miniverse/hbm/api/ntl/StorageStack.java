package com.miniverse.hbm.api.ntl;

import net.minecraft.world.item.ItemStack;

public class StorageStack {

    private ItemStack type;
    private long amount;

    public StorageStack(ItemStack type) {
        this(type, type.getCount());
    }

    public StorageStack(ItemStack type, long amount) {
        this.type = type.copy();
        this.amount = amount;
        this.type.setCount(0);
    }

    public ItemStack getType() {
        return this.type.copy();
    }

    public long getAmount() {
        return this.amount;
    }
}
