package com.miniverse.hbm.items;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBMNuclearTechCriticality.MODID);

    public static final RegistryObject<Item> SULFUR =
            ITEMS.register("sulfur", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REACHER =
            ITEMS.register("reacher", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
