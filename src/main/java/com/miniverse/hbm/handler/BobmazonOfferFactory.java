package com.miniverse.hbm.handler;

import java.util.ArrayList;
import java.util.List;

import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.blocks.generic.BlockConcreteColoredExt.EnumConcreteType;
import com.miniverse.hbm.blocks.generic.BlockNTMFlower.EnumFlowerType;
import com.miniverse.hbm.blocks.generic.BlockPlushie.PlushieType;
import com.miniverse.hbm.blocks.generic.BlockSnowglobe.SnowglobeType;
import com.miniverse.hbm.inventory.fluid.Fluids;
import com.miniverse.hbm.inventory.gui.GUIScreenBobmazon.Offer;
import com.miniverse.hbm.inventory.gui.GUIScreenBobmazon.Requirement;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.items.food.ItemConserve.EnumFoodType;
import com.miniverse.hbm.items.machine.ItemBattery;
import com.miniverse.hbm.items.special.ItemKitCustom;
import com.miniverse.hbm.items.special.ItemKitNBT;
import com.miniverse.hbm.items.weapon.sedna.factory.GunFactory.EnumAmmo;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class BobmazonOfferFactory {

    public static List<Offer> standard = new ArrayList<>();
    public static List<Offer> special = new ArrayList<>();

    public static void init() {
        standard.clear();
        special.clear();

        // Gear offers
        standard.add(new Offer(new ItemStack(Blocks.TORCH, 64), Requirement.NONE, 2));
        standard.add(new Offer(new ItemStack(ModItems.definitelyfood, 16), Requirement.NONE, 4));
        standard.add(new Offer(new ItemStack(ModItems.nitra, 4), Requirement.CHEMICS, 16));
        standard.add(new Offer(new ItemStack(ModItems.gun_kit_1, 1), Requirement.ASSEMBLY, 16));
        standard.add(new Offer(new ItemStack(ModItems.geiger_counter, 1), Requirement.NONE, 16));
        standard.add(new Offer(new ItemStack(ModItems.matchstick, 16), Requirement.STEEL, 2));

        // Plants
        {
            ItemStack sapling = new ItemStack(Blocks.SAPLING, 1);
            sapling.setDamageValue(3);
            standard.add(new Offer(sapling, Requirement.STEEL, 12, 9));
        }
        {
            ItemStack flowerFoxglove = new ItemStack(ModBlocks.plant_flower, 1);
            flowerFoxglove.setDamageValue(EnumFlowerType.FOXGLOVE.ordinal());
            standard.add(new Offer(flowerFoxglove, Requirement.STEEL, 16, 5));
        }
        {
            ItemStack flowerTobacco = new ItemStack(ModBlocks.plant_flower, 1);
            flowerTobacco.setDamageValue(EnumFlowerType.TOBACCO.ordinal());
            standard.add(new Offer(flowerTobacco, Requirement.STEEL, 16, 9));
        }
        {
            ItemStack flowerNightshade = new ItemStack(ModBlocks.plant_flower, 1);
            flowerNightshade.setDamageValue(EnumFlowerType.NIGHTSHADE.ordinal());
            standard.add(new Offer(flowerNightshade, Requirement.STEEL, 16, 3));
        }
        {
            ItemStack flowerWeed = new ItemStack(ModBlocks.plant_flower, 1);
            flowerWeed.setDamageValue(EnumFlowerType.WEED.ordinal());
            standard.add(new Offer(flowerWeed, Requirement.STEEL, 4, 10));
        }
        {
            ItemStack flowerCD0 = new ItemStack(ModBlocks.plant_flower, 1);
            flowerCD0.setDamageValue(EnumFlowerType.CD0.ordinal());
            standard.add(new Offer(flowerCD0, Requirement.NUCLEAR, 64, 8));
        }

        // Deco offers
        for (EnumConcreteType conc : EnumConcreteType.values()) {
            ItemStack concreteStack = new ItemStack(ModBlocks.concrete_colored_ext, 16);
            concreteStack.setDamageValue(conc.ordinal());
            standard.add(new Offer(concreteStack, Requirement.CHEMICS, 4));
        }
        for (SnowglobeType globe : SnowglobeType.values()) {
            ItemStack globeStack = new ItemStack(ModBlocks.snowglobe, 1);
            globeStack.setDamageValue(globe.ordinal());
            standard.add(new Offer(globeStack, Requirement.CHEMICS, 128));
        }
        for (int i = 1; i < PlushieType.values().length; i++) {
            ItemStack plushieStack = new ItemStack(ModBlocks.plushie, 1);
            plushieStack.setDamageValue(i);
            standard.add(new Offer(plushieStack, Requirement.OIL, 16, i < 3 ? 10 : 0));
        }

        // Special offers
        special.add(new Offer(new ItemStack(Items.IRON_INGOT, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_steel, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_copper, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_red_copper, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_titanium, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_tungsten, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_cobalt, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_desh, 64), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_tantalium, 64), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.ingot_bismuth, 16), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.ingot_schrabidium, 16), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.ingot_euphemium, 8), Requirement.STEEL, 16));
        special.add(new Offer(new ItemStack(ModItems.ingot_dineutronium, 1), Requirement.STEEL, 16));
        special.add(new Offer(new ItemStack(ModItems.ingot_starmetal, 16), Requirement.STEEL, 8));
        special.add(new Offer(new ItemStack(ModItems.ingot_semtex, 16), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_u235, 16), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ingot_pu239, 16), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.ammo_container, 16), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.nuke_starter_kit), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.nuke_advanced_kit), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.nuke_commercially_kit), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.boy_kit), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.prototype_kit), Requirement.STEEL, 10));
        special.add(new Offer(new ItemStack(ModItems.missile_kit), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.grenade_kit), Requirement.STEEL, 5));
        special.add(new Offer(new ItemStack(ModItems.jetpack_vector), Requirement.STEEL, 2));
        special.add(new Offer(new ItemStack(ModItems.jetpack_tank), Requirement.STEEL, 2));
        special.add(new Offer(new ItemStack(ModItems.gun_kit_1, 1), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModItems.gun_kit_2, 1), Requirement.STEEL, 3));
        special.add(new Offer(new ItemStack(ModBlocks.struct_launcher_core, 1), Requirement.STEEL, 3));
        special.add(new Offer(new ItemStack(ModBlocks.struct_launcher_core_large, 1), Requirement.STEEL, 3));
        special.add(new Offer(new ItemStack(ModBlocks.struct_launcher, 40), Requirement.STEEL, 7));
        special.add(new Offer(new ItemStack(ModBlocks.struct_scaffold, 11), Requirement.STEEL, 7));
        special.add(new Offer(new ItemStack(ModItems.loot_10, 1), Requirement.STEEL, 2));
        special.add(new Offer(new ItemStack(ModItems.loot_15, 1), Requirement.STEEL, 2));
        special.add(new Offer(new ItemStack(ModItems.loot_misc, 1), Requirement.STEEL, 2));
        special.add(new Offer(new ItemStack(ModBlocks.crate_can, 1), Requirement.STEEL, 1));
        special.add(new Offer(new ItemStack(ModBlocks.crate_ammo, 1), Requirement.STEEL, 2));
        {
            ItemStack crucible = new ItemStack(ModItems.crucible, 1);
            crucible.setDamageValue(3);
            special.add(new Offer(crucible, Requirement.STEEL, 10));
        }
        special.add(new Offer(new ItemStack(ModItems.spawn_chopper, 1), Requirement.STEEL, 10));
        special.add(new Offer(new ItemStack(ModItems.spawn_worm, 1), Requirement.STEEL, 10));
        special.add(new Offer(new ItemStack(ModItems.spawn_ufo, 1), Requirement.STEEL, 10));
        special.add(new Offer(new ItemStack(ModItems.sat_laser, 1), Requirement.HIDDEN, 8));
        special.add(new Offer(new ItemStack(ModItems.sat_gerald, 1), Requirement.HIDDEN, 32));
        special.add(new Offer(new ItemStack(ModItems.billet_yharonite, 4), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModItems.ingot_chainsteel, 1), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModItems.ingot_electronium, 1), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModItems.book_of_, 1), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModItems.mese_pickaxe, 1), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModItems.mysteryshovel, 1), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModBlocks.ntm_dirt, 1), Requirement.HIDDEN, 16));
        special.add(new Offer(new ItemStack(ModItems.euphemium_kit, 1), Requirement.HIDDEN, 64));

        special.add(new Offer(ItemKitCustom.create("Fusion Man", "For the nuclear physicist on the go", 0xff00ff, 0x800080,
                new ItemStack(ModBlocks.iter),
                new ItemStack(ModBlocks.plasma_heater),
                new ItemStack(ModItems.fusion_shield_vaporwave),
                ItemBattery.getFullBattery(ModItems.battery_spark),
                new ItemStack(ModBlocks.machine_chemplant, 10),
                new ItemStack(ModBlocks.machine_fluidtank, 8),
                new ItemStack(ModBlocks.red_wire_coated, 64),
                new ItemStack(ModBlocks.red_cable, 64),
                new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.DEUTERIUM.getID()),
                new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.TRITIUM.getID()),
                new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.XENON.getID()),
                new ItemStack(ModItems.fluid_barrel_full, 64, Fluids.MERCURY.getID()),
                new ItemStack(ModBlocks.red_pylon_large, 8),
                new ItemStack(ModBlocks.substation, 4),
                new ItemStack(ModBlocks.red_pylon, 16),
                new ItemStack(ModBlocks.red_connector, 64),
                new ItemStack(ModItems.wiring_red_copper, 1),
                new ItemStack(ModBlocks.machine_chungus, 1),
                new ItemStack(ModBlocks.machine_large_turbine, 3),
                new ItemStack(ModItems.template_folder, 1),
                new ItemStack(Items.PAPER, 64),
                new ItemStack(Items.DYE, 64)
        ), Requirement.HIDDEN, 64));

        special.add(new Offer(ItemKitCustom.create("Maid's Cleaning Utensils", "For the hard to reach spots", 0x00ff00, 0x008000,
                new ItemStack(ModItems.gun_m2),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.BMG50_DU.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.BMG50_DU.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.BMG50_DU.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.BMG50_DU.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.BMG50_DU.ordinal()),
                new ItemStack(ModItems.gun_autoshotgun),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.G12_MAGNUM.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.G12_MAGNUM.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.G12_MAGNUM.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.G12_EXPLOSIVE.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.G12_EXPLOSIVE.ordinal())
        ), Requirement.HIDDEN, 64));

        special.add(new Offer(ItemKitNBT.create(
                new ItemStack(ModItems.rod_of_discord).setHoverName(Component.literal("Cock Joke")),
                ModItems.canned_conserve.stackFromEnum(64, EnumFoodType.JIZZ).setHoverName(Component.literal("Class A Horse Semen")),
                new ItemStack(ModItems.pipe_lead).setHoverName(Component.literal("Get Nutted, Dumbass")),
                new ItemStack(ModItems.gem_alexandrite)
        ).setHoverName(Component.literal("The Nut Bucket")), Requirement.HIDDEN, 64));

        special.add(new Offer(ItemKitNBT.create(
                new ItemStack(ModItems.rpa_helmet),
                new ItemStack(ModItems.rpa_plate),
                new ItemStack(ModItems.rpa_legs),
                new ItemStack(ModItems.rpa_boots),
                new ItemStack(ModItems.gun_minigun_lacunae),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.CAPACITOR_OVERCHARGE.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.CAPACITOR_OVERCHARGE.ordinal()),
                new ItemStack(ModItems.ammo_standard, 64, EnumAmmo.CAPACITOR_OVERCHARGE.ordinal())
        ).setHoverName(Component.literal("Frenchie's Reward")), Requirement.HIDDEN, 32));
    }

    public static List<Offer> getOffers(ItemStack stack) {
        if (stack != null) {
            if (stack.getItem() == ModItems.bobmazon)
                return standard;
            if (stack.getItem() == ModItems.bobmazon_hidden)
                return special;
        }
        return null;
    }
}
