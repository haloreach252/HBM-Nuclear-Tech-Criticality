package com.miniverse.hbm.itempool;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class ItemPool {
    public static final Map<String, ItemPool> pools = new HashMap<>();
    private final List<LootPoolEntryContainer.Builder<?>> buildingList = new ArrayList<>();
    private LootPool lootPool;
    public String name;

    public static void initialize() {
        ItemPoolsLegacy.init();
        ItemPoolsComponent.init();
        ItemPoolsSingle.init();
        ItemPoolsRedRoom.init();
        ItemPoolsSatellite.init();
        ItemPoolsPile.init();
        ItemPoolsC130.init();
    }

    public ItemPool() {}

    public ItemPool(String name) {
        this.name = name;
        pools.put(name, this);
    }

    public ItemPool add(Item item, int min, int max, int weight) {
        buildingList.add(
                LootItem.lootTableItem(item)
                        .setWeight(weight)
                        .apply(SetItemCountFunction.setCount(randomRange(min, max)))
        );
        return this;
    }

    public ItemPool add(Block block, int min, int max, int weight) {
        return add(Item.byBlock(block), min, max, weight);
    }

    public ItemPool add(ItemStack stack, int min, int max, int weight) {
        buildingList.add(
                LootItem.lootTableItem(stack.getItem())
                        .setWeight(weight)
                        .apply(SetItemCountFunction.setCount(randomRange(min, max)))
        );
        return this;
    }

    public ItemPool build() {
        LootPool.Builder poolBuilder = LootPool.lootPool();
        for (LootPoolEntryContainer.Builder<?> entry : buildingList) {
            poolBuilder.add(entry);
        }
        lootPool = poolBuilder.build();
        buildingList.clear();
        return this;
    }

    public static LootPool getPool(String name) {
        ItemPool pool = pools.get(name);
        if (pool == null) return backupPool;
        return pool.lootPool;
    }

    private static LootPool backupPool = LootPool.lootPool()
            .add(LootItem.lootTableItem(Items.BREAD)
                    .setWeight(10)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
            .add(LootItem.lootTableItem(Items.STICK)
                    .setWeight(10)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))
            .build();

    private static ConstantValue randomRange(int min, int max) {
        return ConstantValue.exactly(min + new Random().nextInt(max - min + 1));
    }
}
