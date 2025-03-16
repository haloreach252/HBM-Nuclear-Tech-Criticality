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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBMNuclearTechCriticality.MODID);

    public static final RegistryObject<Item> REDSTONE_SWORD =
            ITEMS.register("redstone_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIG_SWORD =
            ITEMS.register("big_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TH232 =
            ITEMS.register("ingot_th232", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_URANIUM =
            ITEMS.register("ingot_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_U233 =
            ITEMS.register("ingot_u233", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_U235 =
            ITEMS.register("ingot_u235", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_U238 =
            ITEMS.register("ingot_u238", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_U238M2 =
            ITEMS.register("ingot_u238m2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PLUTONIUM =
            ITEMS.register("ingot_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PU238 =
            ITEMS.register("ingot_pu238", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PU239 =
            ITEMS.register("ingot_pu239", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PU240 =
            ITEMS.register("ingot_pu240", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PU241 =
            ITEMS.register("ingot_pu241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PU_MIX =
            ITEMS.register("ingot_pu_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_AM241 =
            ITEMS.register("ingot_am241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_AM242 =
            ITEMS.register("ingot_am242", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_AM_MIX =
            ITEMS.register("ingot_am_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_NEPTUNIUM =
            ITEMS.register("ingot_neptunium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_POLONIUM =
            ITEMS.register("ingot_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TECHNETIUM =
            ITEMS.register("ingot_technetium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CO60 =
            ITEMS.register("ingot_co60", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SR90 =
            ITEMS.register("ingot_sr90", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_AU198 =
            ITEMS.register("ingot_au198", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PB209 =
            ITEMS.register("ingot_pb209", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_RA226 =
            ITEMS.register("ingot_ra226", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TITANIUM =
            ITEMS.register("ingot_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_COBALT =
            ITEMS.register("ingot_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SULFUR =
            ITEMS.register("sulfur", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NITRA =
            ITEMS.register("nitra", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NITRA_SMALL =
            ITEMS.register("nitra_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COKE =
            ITEMS.register("coke", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIGNITE =
            ITEMS.register("lignite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LIGNITE =
            ITEMS.register("powder_lignite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRIQUETTE =
            ITEMS.register("briquette", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COAL_INFERNAL =
            ITEMS.register("coal_infernal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CINNEBAR =
            ITEMS.register("cinnebar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ASH =
            ITEMS.register("powder_ash", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LIMESTONE =
            ITEMS.register("powder_limestone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CEMENT =
            ITEMS.register("powder_cement", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NITER =
            ITEMS.register("niter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_COPPER =
            ITEMS.register("ingot_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_RED_COPPER =
            ITEMS.register("ingot_red_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TUNGSTEN =
            ITEMS.register("ingot_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ALUMINIUM =
            ITEMS.register("ingot_aluminium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUORITE =
            ITEMS.register("fluorite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BERYLLIUM =
            ITEMS.register("ingot_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SCHRARANIUM =
            ITEMS.register("ingot_schraranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SCHRABIDIUM =
            ITEMS.register("ingot_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SCHRABIDATE =
            ITEMS.register("ingot_schrabidate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PLUTONIUM_FUEL =
            ITEMS.register("ingot_plutonium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_NEPTUNIUM_FUEL =
            ITEMS.register("ingot_neptunium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_URANIUM_FUEL =
            ITEMS.register("ingot_uranium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_MOX_FUEL =
            ITEMS.register("ingot_mox_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_AMERICIUM_FUEL =
            ITEMS.register("ingot_americium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SCHRABIDIUM_FUEL =
            ITEMS.register("ingot_schrabidium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_THORIUM_FUEL =
            ITEMS.register("ingot_thorium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_URANIUM_FUEL =
            ITEMS.register("nugget_uranium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_THORIUM_FUEL =
            ITEMS.register("nugget_thorium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PLUTONIUM_FUEL =
            ITEMS.register("nugget_plutonium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_NEPTUNIUM_FUEL =
            ITEMS.register("nugget_neptunium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_MOX_FUEL =
            ITEMS.register("nugget_mox_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AMERICIUM_FUEL =
            ITEMS.register("nugget_americium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_SCHRABIDIUM_FUEL =
            ITEMS.register("nugget_schrabidium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ADVANCED_ALLOY =
            ITEMS.register("ingot_advanced_alloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TCALLOY =
            ITEMS.register("ingot_tcalloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CDALLOY =
            ITEMS.register("ingot_cdalloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BISMUTH_BRONZE =
            ITEMS.register("ingot_bismuth_bronze", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ARSENIC_BRONZE =
            ITEMS.register("ingot_arsenic_bronze", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BSCCO =
            ITEMS.register("ingot_bscco", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LITHIUM =
            ITEMS.register("lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ZIRCONIUM =
            ITEMS.register("ingot_zirconium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_HES =
            ITEMS.register("ingot_hes", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_LES =
            ITEMS.register("ingot_les", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_HES =
            ITEMS.register("nugget_hes", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_LES =
            ITEMS.register("nugget_les", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_MAGNETIZED_TUNGSTEN =
            ITEMS.register("ingot_magnetized_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_COMBINE_STEEL =
            ITEMS.register("ingot_combine_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SOLINIUM =
            ITEMS.register("ingot_solinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_SOLINIUM =
            ITEMS.register("nugget_solinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PHOSPHORUS =
            ITEMS.register("ingot_phosphorus", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SEMTEX =
            ITEMS.register("ingot_semtex", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_C4 =
            ITEMS.register("ingot_c4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BORON =
            ITEMS.register("ingot_boron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_GRAPHITE =
            ITEMS.register("ingot_graphite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FIREBRICK =
            ITEMS.register("ingot_firebrick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SMORE =
            ITEMS.register("ingot_smore", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_GH336 =
            ITEMS.register("ingot_gh336", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_GH336 =
            ITEMS.register("nugget_gh336", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_AUSTRALIUM =
            ITEMS.register("ingot_australium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AUSTRALIUM =
            ITEMS.register("nugget_australium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AUSTRALIUM_LESSER =
            ITEMS.register("nugget_australium_lesser", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AUSTRALIUM_GREATER =
            ITEMS.register("nugget_australium_greater", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_DESH =
            ITEMS.register("ingot_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_DESH =
            ITEMS.register("nugget_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_DINEUTRONIUM =
            ITEMS.register("ingot_dineutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_DINEUTRONIUM =
            ITEMS.register("nugget_dineutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_DINEUTRONIUM =
            ITEMS.register("powder_dineutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TETRANEUTRONIUM =
            ITEMS.register("ingot_tetraneutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_TETRANEUTRONIUM =
            ITEMS.register("nugget_tetraneutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TETRANEUTRONIUM =
            ITEMS.register("powder_tetraneutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_STARMETAL =
            ITEMS.register("ingot_starmetal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_GUNMETAL =
            ITEMS.register("ingot_gunmetal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_GUNMETAL =
            ITEMS.register("plate_gunmetal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_WEAPONSTEEL =
            ITEMS.register("ingot_weaponsteel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_WEAPONSTEEL =
            ITEMS.register("plate_weaponsteel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SATURNITE =
            ITEMS.register("ingot_saturnite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_SATURNITE =
            ITEMS.register("plate_saturnite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FERROURANIUM =
            ITEMS.register("ingot_ferrouranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ELECTRONIUM =
            ITEMS.register("ingot_electronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_ZIRCONIUM =
            ITEMS.register("nugget_zirconium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_MERCURY =
            ITEMS.register("nugget_mercury", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_MERCURY =
            ITEMS.register("ingot_mercury", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_MERCURY =
            ITEMS.register("bottle_mercury", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_BYPRODUCT =
            ITEMS.register("ore_byproduct", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_BEDROCK =
            ITEMS.register("ore_bedrock", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_CENTRIFUGED =
            ITEMS.register("ore_centrifuged", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_CLEANED =
            ITEMS.register("ore_cleaned", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_SEPARATED =
            ITEMS.register("ore_separated", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_PURIFIED =
            ITEMS.register("ore_purified", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_NITRATED =
            ITEMS.register("ore_nitrated", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_NITROCRYSTALLINE =
            ITEMS.register("ore_nitrocrystalline", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_DEEPCLEANED =
            ITEMS.register("ore_deepcleaned", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_SEARED =
            ITEMS.register("ore_seared", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_ENRICHED =
            ITEMS.register("ore_enriched", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEDROCK_ORE_BASE =
            ITEMS.register("bedrock_ore_base", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEDROCK_ORE =
            ITEMS.register("bedrock_ore", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BEDROCK_ORE_FRAGMENT =
            ITEMS.register("bedrock_ore_fragment", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_URANIUM =
            ITEMS.register("billet_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_U233 =
            ITEMS.register("billet_u233", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_U235 =
            ITEMS.register("billet_u235", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_U238 =
            ITEMS.register("billet_u238", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_TH232 =
            ITEMS.register("billet_th232", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PLUTONIUM =
            ITEMS.register("billet_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PU238 =
            ITEMS.register("billet_pu238", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PU239 =
            ITEMS.register("billet_pu239", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PU240 =
            ITEMS.register("billet_pu240", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PU241 =
            ITEMS.register("billet_pu241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PU_MIX =
            ITEMS.register("billet_pu_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AM241 =
            ITEMS.register("billet_am241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AM242 =
            ITEMS.register("billet_am242", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AM_MIX =
            ITEMS.register("billet_am_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_NEPTUNIUM =
            ITEMS.register("billet_neptunium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_POLONIUM =
            ITEMS.register("billet_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_TECHNETIUM =
            ITEMS.register("billet_technetium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_COBALT =
            ITEMS.register("billet_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_CO60 =
            ITEMS.register("billet_co60", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_SR90 =
            ITEMS.register("billet_sr90", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AU198 =
            ITEMS.register("billet_au198", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PB209 =
            ITEMS.register("billet_pb209", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_RA226 =
            ITEMS.register("billet_ra226", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_ACTINIUM =
            ITEMS.register("billet_actinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_SCHRABIDIUM =
            ITEMS.register("billet_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_SOLINIUM =
            ITEMS.register("billet_solinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_GH336 =
            ITEMS.register("billet_gh336", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AUSTRALIUM =
            ITEMS.register("billet_australium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AUSTRALIUM_LESSER =
            ITEMS.register("billet_australium_lesser", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AUSTRALIUM_GREATER =
            ITEMS.register("billet_australium_greater", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_URANIUM_FUEL =
            ITEMS.register("billet_uranium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_THORIUM_FUEL =
            ITEMS.register("billet_thorium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PLUTONIUM_FUEL =
            ITEMS.register("billet_plutonium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_NEPTUNIUM_FUEL =
            ITEMS.register("billet_neptunium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_MOX_FUEL =
            ITEMS.register("billet_mox_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_AMERICIUM_FUEL =
            ITEMS.register("billet_americium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_LES =
            ITEMS.register("billet_les", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_SCHRABIDIUM_FUEL =
            ITEMS.register("billet_schrabidium_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_HES =
            ITEMS.register("billet_hes", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PO210BE =
            ITEMS.register("billet_po210be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_RA226BE =
            ITEMS.register("billet_ra226be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_PU238BE =
            ITEMS.register("billet_pu238be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_YHARONITE =
            ITEMS.register("billet_yharonite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_BALEFIRE_GOLD =
            ITEMS.register("billet_balefire_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_FLASHLEAD =
            ITEMS.register("billet_flashlead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_ZFB_BISMUTH =
            ITEMS.register("billet_zfb_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_ZFB_PU241 =
            ITEMS.register("billet_zfb_pu241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_ZFB_AM_MIX =
            ITEMS.register("billet_zfb_am_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_BERYLLIUM =
            ITEMS.register("billet_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_BISMUTH =
            ITEMS.register("billet_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_ZIRCONIUM =
            ITEMS.register("billet_zirconium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_NUCLEAR_WASTE =
            ITEMS.register("billet_nuclear_waste", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_TH232 =
            ITEMS.register("nugget_th232", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_URANIUM =
            ITEMS.register("nugget_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_U233 =
            ITEMS.register("nugget_u233", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_U235 =
            ITEMS.register("nugget_u235", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_U238 =
            ITEMS.register("nugget_u238", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PLUTONIUM =
            ITEMS.register("nugget_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PU238 =
            ITEMS.register("nugget_pu238", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PU239 =
            ITEMS.register("nugget_pu239", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PU240 =
            ITEMS.register("nugget_pu240", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PU241 =
            ITEMS.register("nugget_pu241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PU_MIX =
            ITEMS.register("nugget_pu_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AM241 =
            ITEMS.register("nugget_am241", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AM242 =
            ITEMS.register("nugget_am242", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AM_MIX =
            ITEMS.register("nugget_am_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_NEPTUNIUM =
            ITEMS.register("nugget_neptunium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_POLONIUM =
            ITEMS.register("nugget_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_TECHNETIUM =
            ITEMS.register("nugget_technetium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_COBALT =
            ITEMS.register("nugget_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_CO60 =
            ITEMS.register("nugget_co60", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_SR90 =
            ITEMS.register("nugget_sr90", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_AU198 =
            ITEMS.register("nugget_au198", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_PB209 =
            ITEMS.register("nugget_pb209", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_RA226 =
            ITEMS.register("nugget_ra226", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_ACTINIUM =
            ITEMS.register("nugget_actinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_TITANIUM =
            ITEMS.register("plate_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ALUMINIUM =
            ITEMS.register("plate_aluminium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEUTRON_REFLECTOR =
            ITEMS.register("neutron_reflector", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_STEEL =
            ITEMS.register("ingot_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_STEEL =
            ITEMS.register("plate_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_IRON =
            ITEMS.register("plate_iron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_LEAD =
            ITEMS.register("ingot_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_LEAD =
            ITEMS.register("nugget_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BISMUTH =
            ITEMS.register("ingot_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_BISMUTH =
            ITEMS.register("nugget_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ARSENIC =
            ITEMS.register("ingot_arsenic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_ARSENIC =
            ITEMS.register("nugget_arsenic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TANTALIUM =
            ITEMS.register("ingot_tantalium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_TANTALIUM =
            ITEMS.register("nugget_tantalium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SILICON =
            ITEMS.register("ingot_silicon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BILLET_SILICON =
            ITEMS.register("billet_silicon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_SILICON =
            ITEMS.register("nugget_silicon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_NIOBIUM =
            ITEMS.register("ingot_niobium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_NIOBIUM =
            ITEMS.register("nugget_niobium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_OSMIRIDIUM =
            ITEMS.register("ingot_osmiridium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_OSMIRIDIUM =
            ITEMS.register("nugget_osmiridium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_LEAD =
            ITEMS.register("plate_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_DURA_STEEL =
            ITEMS.register("plate_dura_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_SCHRABIDIUM =
            ITEMS.register("nugget_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_SCHRABIDIUM =
            ITEMS.register("plate_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_COPPER =
            ITEMS.register("plate_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_BERYLLIUM =
            ITEMS.register("nugget_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_GOLD =
            ITEMS.register("plate_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_CLOTH =
            ITEMS.register("hazmat_cloth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_CLOTH_RED =
            ITEMS.register("hazmat_cloth_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_CLOTH_GREY =
            ITEMS.register("hazmat_cloth_grey", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASBESTOS_CLOTH =
            ITEMS.register("asbestos_cloth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAG =
            ITEMS.register("rag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAG_DAMP =
            ITEMS.register("rag_damp", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAG_PISS =
            ITEMS.register("rag_piss", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FILTER_COAL =
            ITEMS.register("filter_coal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ADVANCED_ALLOY =
            ITEMS.register("plate_advanced_alloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_COMBINE_STEEL =
            ITEMS.register("plate_combine_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_MIXED =
            ITEMS.register("plate_mixed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_PAA =
            ITEMS.register("plate_paa", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPES_STEEL =
            ITEMS.register("pipes_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRILL_TITANIUM =
            ITEMS.register("drill_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_DALEKANIUM =
            ITEMS.register("plate_dalekanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_EUPHEMIUM =
            ITEMS.register("plate_euphemium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOLT =
            ITEMS.register("bolt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOLT_SPIKE =
            ITEMS.register("bolt_spike", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_POLYMER =
            ITEMS.register("plate_polymer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_KEVLAR =
            ITEMS.register("plate_kevlar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_DINEUTRONIUM =
            ITEMS.register("plate_dineutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_DESH =
            ITEMS.register("plate_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_BISMUTH =
            ITEMS.register("plate_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PHOTO_PANEL =
            ITEMS.register("photo_panel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_BASE =
            ITEMS.register("sat_base", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THRUSTER_NUCLEAR =
            ITEMS.register("thruster_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAFETY_FUSE =
            ITEMS.register("safety_fuse", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_GENERIC =
            ITEMS.register("part_generic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ITEM_SECRET =
            ITEMS.register("item_secret", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEMICAL_DYE =
            ITEMS.register("chemical_dye", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRAYON =
            ITEMS.register("crayon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNDEFINED =
            ITEMS.register("undefined", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALL_RESIN =
            ITEMS.register("ball_resin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_DURA_STEEL =
            ITEMS.register("ingot_dura_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_POLYMER =
            ITEMS.register("ingot_polymer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BAKELITE =
            ITEMS.register("ingot_bakelite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BIORUBBER =
            ITEMS.register("ingot_biorubber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_RUBBER =
            ITEMS.register("ingot_rubber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PET =
            ITEMS.register("ingot_pet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PC =
            ITEMS.register("ingot_pc", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_PVC =
            ITEMS.register("ingot_pvc", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FIBERGLASS =
            ITEMS.register("ingot_fiberglass", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ASBESTOS =
            ITEMS.register("ingot_asbestos", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ASBESTOS =
            ITEMS.register("powder_asbestos", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CALCIUM =
            ITEMS.register("ingot_calcium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CALCIUM =
            ITEMS.register("powder_calcium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CADMIUM =
            ITEMS.register("ingot_cadmium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CADMIUM =
            ITEMS.register("powder_cadmium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BISMUTH =
            ITEMS.register("powder_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_MUD =
            ITEMS.register("ingot_mud", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CFT =
            ITEMS.register("ingot_cft", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_LANTHANIUM =
            ITEMS.register("ingot_lanthanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ACTINIUM =
            ITEMS.register("ingot_actinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_METEORITE =
            ITEMS.register("ingot_meteorite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_METEORITE_FORGED =
            ITEMS.register("ingot_meteorite_forged", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADE_METEORITE =
            ITEMS.register("blade_meteorite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_STEEL_DUSTED =
            ITEMS.register("ingot_steel_dusted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CHAINSTEEL =
            ITEMS.register("ingot_chainsteel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ARMOR_TITANIUM =
            ITEMS.register("plate_armor_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ARMOR_AJR =
            ITEMS.register("plate_armor_ajr", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ARMOR_HEV =
            ITEMS.register("plate_armor_hev", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ARMOR_LUNAR =
            ITEMS.register("plate_armor_lunar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ARMOR_FAU =
            ITEMS.register("plate_armor_fau", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_ARMOR_DNT =
            ITEMS.register("plate_armor_dnt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OIL_TAR =
            ITEMS.register("oil_tar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL =
            ITEMS.register("solid_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL_PRESTO =
            ITEMS.register("solid_fuel_presto", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL_PRESTO_TRIPLET =
            ITEMS.register("solid_fuel_presto_triplet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL_BF =
            ITEMS.register("solid_fuel_bf", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL_PRESTO_BF =
            ITEMS.register("solid_fuel_presto_bf", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLID_FUEL_PRESTO_TRIPLET_BF =
            ITEMS.register("solid_fuel_presto_triplet_bf", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROCKET_FUEL =
            ITEMS.register("rocket_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_COAL =
            ITEMS.register("crystal_coal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_IRON =
            ITEMS.register("crystal_iron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_GOLD =
            ITEMS.register("crystal_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_REDSTONE =
            ITEMS.register("crystal_redstone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_LAPIS =
            ITEMS.register("crystal_lapis", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_DIAMOND =
            ITEMS.register("crystal_diamond", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_URANIUM =
            ITEMS.register("crystal_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_THORIUM =
            ITEMS.register("crystal_thorium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_PLUTONIUM =
            ITEMS.register("crystal_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_TITANIUM =
            ITEMS.register("crystal_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_SULFUR =
            ITEMS.register("crystal_sulfur", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_NITER =
            ITEMS.register("crystal_niter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_COPPER =
            ITEMS.register("crystal_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_TUNGSTEN =
            ITEMS.register("crystal_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_ALUMINIUM =
            ITEMS.register("crystal_aluminium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_FLUORITE =
            ITEMS.register("crystal_fluorite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_BERYLLIUM =
            ITEMS.register("crystal_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_LEAD =
            ITEMS.register("crystal_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_SCHRARANIUM =
            ITEMS.register("crystal_schraranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_SCHRABIDIUM =
            ITEMS.register("crystal_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_RARE =
            ITEMS.register("crystal_rare", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_PHOSPHORUS =
            ITEMS.register("crystal_phosphorus", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_LITHIUM =
            ITEMS.register("crystal_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_COBALT =
            ITEMS.register("crystal_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_STARMETAL =
            ITEMS.register("crystal_starmetal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_CINNEBAR =
            ITEMS.register("crystal_cinnebar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_TRIXITE =
            ITEMS.register("crystal_trixite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_OSMIRIDIUM =
            ITEMS.register("crystal_osmiridium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEM_SODALITE =
            ITEMS.register("gem_sodalite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEM_TANTALIUM =
            ITEMS.register("gem_tantalium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEM_VOLCANIC =
            ITEMS.register("gem_volcanic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEM_RAD =
            ITEMS.register("gem_rad", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEM_ALEXANDRITE =
            ITEMS.register("gem_alexandrite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LEAD =
            ITEMS.register("powder_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TANTALIUM =
            ITEMS.register("powder_tantalium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_NEPTUNIUM =
            ITEMS.register("powder_neptunium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_POLONIUM =
            ITEMS.register("powder_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CO60 =
            ITEMS.register("powder_co60", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SR90 =
            ITEMS.register("powder_sr90", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SR90_TINY =
            ITEMS.register("powder_sr90_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_AU198 =
            ITEMS.register("powder_au198", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_RA226 =
            ITEMS.register("powder_ra226", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_I131 =
            ITEMS.register("powder_i131", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_I131_TINY =
            ITEMS.register("powder_i131_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_XE135 =
            ITEMS.register("powder_xe135", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_XE135_TINY =
            ITEMS.register("powder_xe135_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CS137 =
            ITEMS.register("powder_cs137", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CS137_TINY =
            ITEMS.register("powder_cs137_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_AT209 =
            ITEMS.register("powder_at209", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SCHRABIDIUM =
            ITEMS.register("powder_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SCHRABIDATE =
            ITEMS.register("powder_schrabidate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ALUMINIUM =
            ITEMS.register("powder_aluminium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BERYLLIUM =
            ITEMS.register("powder_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COPPER =
            ITEMS.register("powder_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_GOLD =
            ITEMS.register("powder_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_IRON =
            ITEMS.register("powder_iron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TITANIUM =
            ITEMS.register("powder_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TUNGSTEN =
            ITEMS.register("powder_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_URANIUM =
            ITEMS.register("powder_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_PLUTONIUM =
            ITEMS.register("powder_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DUST =
            ITEMS.register("dust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DUST_TINY =
            ITEMS.register("dust_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FALLOUT =
            ITEMS.register("fallout", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_POWER =
            ITEMS.register("powder_power", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_THORIUM =
            ITEMS.register("powder_thorium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_IODINE =
            ITEMS.register("powder_iodine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_NEODYMIUM =
            ITEMS.register("powder_neodymium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ASTATINE =
            ITEMS.register("powder_astatine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CAESIUM =
            ITEMS.register("powder_caesium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_STRONTIUM =
            ITEMS.register("powder_strontium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COBALT =
            ITEMS.register("powder_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BROMINE =
            ITEMS.register("powder_bromine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_NIOBIUM =
            ITEMS.register("powder_niobium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TENNESSINE =
            ITEMS.register("powder_tennessine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CERIUM =
            ITEMS.register("powder_cerium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ADVANCED_ALLOY =
            ITEMS.register("powder_advanced_alloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TCALLOY =
            ITEMS.register("powder_tcalloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COAL =
            ITEMS.register("powder_coal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COAL_TINY =
            ITEMS.register("powder_coal_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COMBINE_STEEL =
            ITEMS.register("powder_combine_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_DIAMOND =
            ITEMS.register("powder_diamond", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_EMERALD =
            ITEMS.register("powder_emerald", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LAPIS =
            ITEMS.register("powder_lapis", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_QUARTZ =
            ITEMS.register("powder_quartz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_MAGNETIZED_TUNGSTEN =
            ITEMS.register("powder_magnetized_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CHLOROPHYTE =
            ITEMS.register("powder_chlorophyte", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_RED_COPPER =
            ITEMS.register("powder_red_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_STEEL =
            ITEMS.register("powder_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LITHIUM =
            ITEMS.register("powder_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ZIRCONIUM =
            ITEMS.register("powder_zirconium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SODIUM =
            ITEMS.register("powder_sodium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_AUSTRALIUM =
            ITEMS.register("powder_australium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_DURA_STEEL =
            ITEMS.register("powder_dura_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_POLYMER =
            ITEMS.register("powder_polymer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BAKELITE =
            ITEMS.register("powder_bakelite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_EUPHEMIUM =
            ITEMS.register("powder_euphemium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_METEORITE =
            ITEMS.register("powder_meteorite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_STEEL_TINY =
            ITEMS.register("powder_steel_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LITHIUM_TINY =
            ITEMS.register("powder_lithium_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_NEODYMIUM_TINY =
            ITEMS.register("powder_neodymium_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COBALT_TINY =
            ITEMS.register("powder_cobalt_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_NIOBIUM_TINY =
            ITEMS.register("powder_niobium_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CERIUM_TINY =
            ITEMS.register("powder_cerium_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LANTHANIUM_TINY =
            ITEMS.register("powder_lanthanium_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ACTINIUM_TINY =
            ITEMS.register("powder_actinium_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BORON_TINY =
            ITEMS.register("powder_boron_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_METEORITE_TINY =
            ITEMS.register("powder_meteorite_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COLTAN_ORE =
            ITEMS.register("powder_coltan_ore", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_COLTAN =
            ITEMS.register("powder_coltan", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_TEKTITE =
            ITEMS.register("powder_tektite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_PALEOGENITE =
            ITEMS.register("powder_paleogenite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_PALEOGENITE_TINY =
            ITEMS.register("powder_paleogenite_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_IMPURE_OSMIRIDIUM =
            ITEMS.register("powder_impure_osmiridium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BORAX =
            ITEMS.register("powder_borax", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_CHLOROCALCITE =
            ITEMS.register("powder_chlorocalcite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_MOLYSITE =
            ITEMS.register("powder_molysite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_LANTHANIUM =
            ITEMS.register("powder_lanthanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ACTINIUM =
            ITEMS.register("powder_actinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BORON =
            ITEMS.register("powder_boron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_DESH =
            ITEMS.register("powder_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SEMTEX_MIX =
            ITEMS.register("powder_semtex_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_DESH_MIX =
            ITEMS.register("powder_desh_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_DESH_READY =
            ITEMS.register("powder_desh_ready", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_NITAN_MIX =
            ITEMS.register("powder_nitan_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SPARK_MIX =
            ITEMS.register("powder_spark_mix", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_YELLOWCAKE =
            ITEMS.register("powder_yellowcake", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_MAGIC =
            ITEMS.register("powder_magic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_BALEFIRE =
            ITEMS.register("powder_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_SAWDUST =
            ITEMS.register("powder_sawdust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_FLUX =
            ITEMS.register("powder_flux", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_FERTILIZER =
            ITEMS.register("powder_fertilizer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_NEODYMIUM =
            ITEMS.register("fragment_neodymium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_COBALT =
            ITEMS.register("fragment_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_NIOBIUM =
            ITEMS.register("fragment_niobium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_CERIUM =
            ITEMS.register("fragment_cerium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_LANTHANIUM =
            ITEMS.register("fragment_lanthanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_ACTINIUM =
            ITEMS.register("fragment_actinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_BORON =
            ITEMS.register("fragment_boron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_METEORITE =
            ITEMS.register("fragment_meteorite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRAGMENT_COLTAN =
            ITEMS.register("fragment_coltan", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHUNK_ORE =
            ITEMS.register("chunk_ore", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIOMASS =
            ITEMS.register("biomass", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIOMASS_COMPRESSED =
            ITEMS.register("biomass_compressed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIO_WAFER =
            ITEMS.register("bio_wafer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLANT_ITEM =
            ITEMS.register("plant_item", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_COPPER =
            ITEMS.register("coil_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_COPPER_TORUS =
            ITEMS.register("coil_copper_torus", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_TUNGSTEN =
            ITEMS.register("coil_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TANK_STEEL =
            ITEMS.register("tank_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOTOR =
            ITEMS.register("motor", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOTOR_DESH =
            ITEMS.register("motor_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOTOR_BISMUTH =
            ITEMS.register("motor_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CENTRIFUGE_ELEMENT =
            ITEMS.register("centrifuge_element", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REACTOR_CORE =
            ITEMS.register("reactor_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RTG_UNIT =
            ITEMS.register("rtg_unit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEVITATION_UNIT =
            ITEMS.register("levitation_unit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_ADVANCED_ALLOY =
            ITEMS.register("coil_advanced_alloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_ADVANCED_TORUS =
            ITEMS.register("coil_advanced_torus", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_MAGNETIZED_TUNGSTEN =
            ITEMS.register("coil_magnetized_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_GOLD =
            ITEMS.register("coil_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIL_GOLD_TORUS =
            ITEMS.register("coil_gold_torus", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGNET_CIRCULAR =
            ITEMS.register("magnet_circular", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COMPONENT_LIMITER =
            ITEMS.register("component_limiter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COMPONENT_EMITTER =
            ITEMS.register("component_emitter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE_PINWHEEL =
            ITEMS.register("chlorine_pinwheel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEUTERIUM_FILTER =
            ITEMS.register("deuterium_filter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTS_LEGENDARY =
            ITEMS.register("parts_legendary", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CIRCUIT =
            ITEMS.register("circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRT_DISPLAY =
            ITEMS.register("crt_display", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CIRCUIT_STAR_PIECE =
            ITEMS.register("circuit_star_piece", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> CIRCUIT_STAR_COMPONENT =
            ITEMS.register("circuit_star_component", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> CIRCUIT_STAR =
            ITEMS.register("circuit_star", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_REVOLVER_1 =
            ITEMS.register("mechanism_revolver_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_REVOLVER_2 =
            ITEMS.register("mechanism_revolver_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_RIFLE_1 =
            ITEMS.register("mechanism_rifle_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_RIFLE_2 =
            ITEMS.register("mechanism_rifle_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_LAUNCHER_1 =
            ITEMS.register("mechanism_launcher_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_LAUNCHER_2 =
            ITEMS.register("mechanism_launcher_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECHANISM_SPECIAL =
            ITEMS.register("mechanism_special", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASSEMBLY_NUKE =
            ITEMS.register("assembly_nuke", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CASING =
            ITEMS.register("casing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WIRING_RED_COPPER =
            ITEMS.register("wiring_red_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHELL =
            ITEMS.register("shell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPE =
            ITEMS.register("pipe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FINS_FLAT =
            ITEMS.register("fins_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FINS_SMALL_STEEL =
            ITEMS.register("fins_small_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FINS_BIG_STEEL =
            ITEMS.register("fins_big_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FINS_TRI_STEEL =
            ITEMS.register("fins_tri_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FINS_QUAD_TITANIUM =
            ITEMS.register("fins_quad_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPHERE_STEEL =
            ITEMS.register("sphere_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PEDESTAL_STEEL =
            ITEMS.register("pedestal_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DYSFUNCTIONAL_REACTOR =
            ITEMS.register("dysfunctional_reactor", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADE_TITANIUM =
            ITEMS.register("blade_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TURBINE_TITANIUM =
            ITEMS.register("turbine_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADE_TUNGSTEN =
            ITEMS.register("blade_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TURBINE_TUNGSTEN =
            ITEMS.register("turbine_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RING_STARMETAL =
            ITEMS.register("ring_starmetal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLYWHEEL_BERYLLIUM =
            ITEMS.register("flywheel_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEAR_LARGE =
            ITEMS.register("gear_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAWBLADE =
            ITEMS.register("sawblade", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOOTHPICKS =
            ITEMS.register("toothpicks", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DUCTTAPE =
            ITEMS.register("ducttape", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CATALYST_CLAY =
            ITEMS.register("catalyst_clay", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_GENERIC_SMALL =
            ITEMS.register("warhead_generic_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_GENERIC_MEDIUM =
            ITEMS.register("warhead_generic_medium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_GENERIC_LARGE =
            ITEMS.register("warhead_generic_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_INCENDIARY_SMALL =
            ITEMS.register("warhead_incendiary_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_INCENDIARY_MEDIUM =
            ITEMS.register("warhead_incendiary_medium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_INCENDIARY_LARGE =
            ITEMS.register("warhead_incendiary_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_CLUSTER_SMALL =
            ITEMS.register("warhead_cluster_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_CLUSTER_MEDIUM =
            ITEMS.register("warhead_cluster_medium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_CLUSTER_LARGE =
            ITEMS.register("warhead_cluster_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_BUSTER_SMALL =
            ITEMS.register("warhead_buster_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_BUSTER_MEDIUM =
            ITEMS.register("warhead_buster_medium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_BUSTER_LARGE =
            ITEMS.register("warhead_buster_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_NUCLEAR =
            ITEMS.register("warhead_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_MIRV =
            ITEMS.register("warhead_mirv", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WARHEAD_VOLCANO =
            ITEMS.register("warhead_volcano", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUEL_TANK_SMALL =
            ITEMS.register("fuel_tank_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUEL_TANK_MEDIUM =
            ITEMS.register("fuel_tank_medium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUEL_TANK_LARGE =
            ITEMS.register("fuel_tank_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THRUSTER_SMALL =
            ITEMS.register("thruster_small", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THRUSTER_MEDIUM =
            ITEMS.register("thruster_medium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THRUSTER_LARGE =
            ITEMS.register("thruster_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_HEAD_MAPPER =
            ITEMS.register("sat_head_mapper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_HEAD_SCANNER =
            ITEMS.register("sat_head_scanner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_HEAD_RADAR =
            ITEMS.register("sat_head_radar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_HEAD_LASER =
            ITEMS.register("sat_head_laser", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_HEAD_RESONATOR =
            ITEMS.register("sat_head_resonator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SEG_10 =
            ITEMS.register("seg_10", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SEG_15 =
            ITEMS.register("seg_15", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SEG_20 =
            ITEMS.register("seg_20", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOPPER_HEAD =
            ITEMS.register("chopper_head", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOPPER_GUN =
            ITEMS.register("chopper_gun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOPPER_TORSO =
            ITEMS.register("chopper_torso", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOPPER_TAIL =
            ITEMS.register("chopper_tail", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOPPER_WING =
            ITEMS.register("chopper_wing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOPPER_BLADES =
            ITEMS.register("chopper_blades", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COMBINE_SCRAP =
            ITEMS.register("combine_scrap", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHIMMER_HEAD =
            ITEMS.register("shimmer_head", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHIMMER_AXE_HEAD =
            ITEMS.register("shimmer_axe_head", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHIMMER_HANDLE =
            ITEMS.register("shimmer_handle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENTANGLEMENT_KIT =
            ITEMS.register("entanglement_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STONE_FLAT =
            ITEMS.register("stamp_stone_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STONE_PLATE =
            ITEMS.register("stamp_stone_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STONE_WIRE =
            ITEMS.register("stamp_stone_wire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STONE_CIRCUIT =
            ITEMS.register("stamp_stone_circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_IRON_FLAT =
            ITEMS.register("stamp_iron_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_IRON_PLATE =
            ITEMS.register("stamp_iron_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_IRON_WIRE =
            ITEMS.register("stamp_iron_wire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_IRON_CIRCUIT =
            ITEMS.register("stamp_iron_circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STEEL_FLAT =
            ITEMS.register("stamp_steel_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STEEL_PLATE =
            ITEMS.register("stamp_steel_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STEEL_WIRE =
            ITEMS.register("stamp_steel_wire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_STEEL_CIRCUIT =
            ITEMS.register("stamp_steel_circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_TITANIUM_FLAT =
            ITEMS.register("stamp_titanium_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_TITANIUM_PLATE =
            ITEMS.register("stamp_titanium_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_TITANIUM_WIRE =
            ITEMS.register("stamp_titanium_wire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_TITANIUM_CIRCUIT =
            ITEMS.register("stamp_titanium_circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_OBSIDIAN_FLAT =
            ITEMS.register("stamp_obsidian_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_OBSIDIAN_PLATE =
            ITEMS.register("stamp_obsidian_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_OBSIDIAN_WIRE =
            ITEMS.register("stamp_obsidian_wire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_OBSIDIAN_CIRCUIT =
            ITEMS.register("stamp_obsidian_circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_FLAT =
            ITEMS.register("stamp_desh_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_PLATE =
            ITEMS.register("stamp_desh_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_WIRE =
            ITEMS.register("stamp_desh_wire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_CIRCUIT =
            ITEMS.register("stamp_desh_circuit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_BOOK =
            ITEMS.register("stamp_book", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_357 =
            ITEMS.register("stamp_357", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_44 =
            ITEMS.register("stamp_44", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_9 =
            ITEMS.register("stamp_9", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_50 =
            ITEMS.register("stamp_50", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_357 =
            ITEMS.register("stamp_desh_357", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_44 =
            ITEMS.register("stamp_desh_44", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_9 =
            ITEMS.register("stamp_desh_9", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAMP_DESH_50 =
            ITEMS.register("stamp_desh_50", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADES_STEEL =
            ITEMS.register("blades_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADES_TITANIUM =
            ITEMS.register("blades_titanium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADES_ADVANCED_ALLOY =
            ITEMS.register("blades_advanced_alloy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLADES_DESH =
            ITEMS.register("blades_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOLD_BASE =
            ITEMS.register("mold_base", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOLD =
            ITEMS.register("mold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRAPS =
            ITEMS.register("scraps", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_RAW =
            ITEMS.register("ingot_raw", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_CAST =
            ITEMS.register("plate_cast", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_WELDED =
            ITEMS.register("plate_welded", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEAVY_COMPONENT =
            ITEMS.register("heavy_component", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WIRE_FINE =
            ITEMS.register("wire_fine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WIRE_DENSE =
            ITEMS.register("wire_dense", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_BARREL_LIGHT =
            ITEMS.register("part_barrel_light", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_BARREL_HEAVY =
            ITEMS.register("part_barrel_heavy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_RECEIVER_LIGHT =
            ITEMS.register("part_receiver_light", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_RECEIVER_HEAVY =
            ITEMS.register("part_receiver_heavy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_MECHANISM =
            ITEMS.register("part_mechanism", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_STOCK =
            ITEMS.register("part_stock", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_GRIP =
            ITEMS.register("part_grip", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_LITHIUM =
            ITEMS.register("part_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_BERYLLIUM =
            ITEMS.register("part_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_CARBON =
            ITEMS.register("part_carbon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_COPPER =
            ITEMS.register("part_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PART_PLUTONIUM =
            ITEMS.register("part_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASER_CRYSTAL_CO2 =
            ITEMS.register("laser_crystal_co2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASER_CRYSTAL_BISMUTH =
            ITEMS.register("laser_crystal_bismuth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASER_CRYSTAL_CMB =
            ITEMS.register("laser_crystal_cmb", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASER_CRYSTAL_DNT =
            ITEMS.register("laser_crystal_dnt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LASER_CRYSTAL_DIGAMMA =
            ITEMS.register("laser_crystal_digamma", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THERMO_ELEMENT =
            ITEMS.register("thermo_element", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CATALYTIC_CONVERTER =
            ITEMS.register("catalytic_converter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRACKPIPE =
            ITEMS.register("crackpipe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_DEPLETED =
            ITEMS.register("pellet_rtg_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_RADIUM =
            ITEMS.register("pellet_rtg_radium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_WEAK =
            ITEMS.register("pellet_rtg_weak", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG =
            ITEMS.register("pellet_rtg", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_STRONTIUM =
            ITEMS.register("pellet_rtg_strontium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_COBALT =
            ITEMS.register("pellet_rtg_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_ACTINIUM =
            ITEMS.register("pellet_rtg_actinium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_POLONIUM =
            ITEMS.register("pellet_rtg_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_AMERICIUM =
            ITEMS.register("pellet_rtg_americium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_GOLD =
            ITEMS.register("pellet_rtg_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_RTG_LEAD =
            ITEMS.register("pellet_rtg_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRITIUM_DEUTERIUM_CAKE =
            ITEMS.register("tritium_deuterium_cake", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PISTON_SELENIUM =
            ITEMS.register("piston_selenium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PISTON_SET =
            ITEMS.register("piston_set", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRILLBIT =
            ITEMS.register("drillbit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_BLANK =
            ITEMS.register("rune_blank", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_ISA =
            ITEMS.register("rune_isa", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_DAGAZ =
            ITEMS.register("rune_dagaz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_HAGALAZ =
            ITEMS.register("rune_hagalaz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_JERA =
            ITEMS.register("rune_jera", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_THURISAZ =
            ITEMS.register("rune_thurisaz", () -> new Item(new Item.Properties()));

    private static Item.Properties catalystProps() {
        return new Item.Properties()
                .stacksTo(1);
    }

    public static final RegistryObject<Item> AMS_CATALYST_BLANK =
            ITEMS.register("ams_catalyst_blank", () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> AMS_CATALYST_ALUMINIUM =
            ITEMS.register("ams_catalyst_aluminium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_BERYLLIUM =
            ITEMS.register("ams_catalyst_beryllium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_CAESIUM =
            ITEMS.register("ams_catalyst_caesium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_CERIUM =
            ITEMS.register("ams_catalyst_cerium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_COBALT =
            ITEMS.register("ams_catalyst_cobalt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_COPPER =
            ITEMS.register("ams_catalyst_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_DINEUTRONIUM =
            ITEMS.register("ams_catalyst_dineutronium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_EUPHEMIUM =
            ITEMS.register("ams_catalyst_euphemium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_IRON =
            ITEMS.register("ams_catalyst_iron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_LITHIUM =
            ITEMS.register("ams_catalyst_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_NIOBIUM =
            ITEMS.register("ams_catalyst_niobium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_SCHRABIDIUM =
            ITEMS.register("ams_catalyst_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_STRONTIUM =
            ITEMS.register("ams_catalyst_strontium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_THORIUM =
            ITEMS.register("ams_catalyst_thorium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CATALYST_TUNGSTEN =
            ITEMS.register("ams_catalyst_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_FOCUS_BLANK =
            ITEMS.register("ams_focus_blank", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_FOCUS_LIMITER =
            ITEMS.register("ams_focus_limiter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_FOCUS_BOOSTER =
            ITEMS.register("ams_focus_booster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_MUZZLE =
            ITEMS.register("ams_muzzle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_LENS =
            ITEMS.register("ams_lens", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CORE_SING =
            ITEMS.register("ams_core_sing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CORE_WORMHOLE =
            ITEMS.register("ams_core_wormhole", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CORE_EYEOFHARMONY =
            ITEMS.register("ams_core_eyeofharmony", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMS_CORE_THINGY =
            ITEMS.register("ams_core_thingy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSION_SHIELD_TUNGSTEN =
            ITEMS.register("fusion_shield_tungsten", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSION_SHIELD_DESH =
            ITEMS.register("fusion_shield_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSION_SHIELD_CHLOROPHYTE =
            ITEMS.register("fusion_shield_chlorophyte", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSION_SHIELD_VAPORWAVE =
            ITEMS.register("fusion_shield_vaporwave", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_EMPTY =
            ITEMS.register("cell_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_UF6 =
            ITEMS.register("cell_uf6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_PUF6 =
            ITEMS.register("cell_puf6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_DEUTERIUM =
            ITEMS.register("cell_deuterium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_TRITIUM =
            ITEMS.register("cell_tritium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_SAS3 =
            ITEMS.register("cell_sas3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_ANTIMATTER =
            ITEMS.register("cell_antimatter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_ANTI_SCHRABIDIUM =
            ITEMS.register("cell_anti_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CELL_BALEFIRE =
            ITEMS.register("cell_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEMON_CORE_OPEN =
            ITEMS.register("demon_core_open", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEMON_CORE_CLOSED =
            ITEMS.register("demon_core_closed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PA_COIL =
            ITEMS.register("pa_coil", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_EMPTY =
            ITEMS.register("particle_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_HYDROGEN =
            ITEMS.register("particle_hydrogen", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_COPPER =
            ITEMS.register("particle_copper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_LEAD =
            ITEMS.register("particle_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_APROTON =
            ITEMS.register("particle_aproton", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_AELECTRON =
            ITEMS.register("particle_aelectron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_AMAT =
            ITEMS.register("particle_amat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_ASCHRAB =
            ITEMS.register("particle_aschrab", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_HIGGS =
            ITEMS.register("particle_higgs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_MUON =
            ITEMS.register("particle_muon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_TACHYON =
            ITEMS.register("particle_tachyon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_STRANGE =
            ITEMS.register("particle_strange", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_DARK =
            ITEMS.register("particle_dark", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_SPARKTICLE =
            ITEMS.register("particle_sparkticle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_DIGAMMA =
            ITEMS.register("particle_digamma", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PARTICLE_LUTECE =
            ITEMS.register("particle_lutece", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_ANTIMATTER =
            ITEMS.register("pellet_antimatter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SINGULARITY_MICRO =
            ITEMS.register("singularity_micro", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SINGULARITY =
            ITEMS.register("singularity", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SINGULARITY_COUNTER_RESONANT =
            ITEMS.register("singularity_counter_resonant", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SINGULARITY_SUPER_HEATED =
            ITEMS.register("singularity_super_heated", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_HOLE =
            ITEMS.register("black_hole", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SINGULARITY_SPARK =
            ITEMS.register("singularity_spark", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_XEN =
            ITEMS.register("crystal_xen", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INF_WATER =
            ITEMS.register("inf_water", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INF_WATER_MK2 =
            ITEMS.register("inf_water_mk2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUEL_ADDITIVE =
            ITEMS.register("fuel_additive", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CANISTER_EMPTY =
            ITEMS.register("canister_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CANISTER_FULL =
            ITEMS.register("canister_full", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CANISTER_NAPALM =
            ITEMS.register("canister_napalm", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_EMPTY =
            ITEMS.register("gas_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_FULL =
            ITEMS.register("gas_full", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_TANK_FULL =
            ITEMS.register("fluid_tank_full", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_TANK_EMPTY =
            ITEMS.register("fluid_tank_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_TANK_LEAD_FULL =
            ITEMS.register("fluid_tank_lead_full", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_TANK_LEAD_EMPTY =
            ITEMS.register("fluid_tank_lead_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_BARREL_FULL =
            ITEMS.register("fluid_barrel_full", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_BARREL_EMPTY =
            ITEMS.register("fluid_barrel_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_BARREL_INFINITE =
            ITEMS.register("fluid_barrel_infinite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPETTE =
            ITEMS.register("pipette", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPETTE_BORON =
            ITEMS.register("pipette_boron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPETTE_LABORATORY =
            ITEMS.register("pipette_laboratory", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIPHON =
            ITEMS.register("siphon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DISPERSER_CANISTER_EMPTY =
            ITEMS.register("disperser_canister_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DISPERSER_CANISTER =
            ITEMS.register("disperser_canister", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLYPHID_GLAND =
            ITEMS.register("glyphid_gland", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLYPHID_GLAND_EMPTY =
            ITEMS.register("glyphid_gland_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_EMPTY =
            ITEMS.register("syringe_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_ANTIDOTE =
            ITEMS.register("syringe_antidote", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_POISON =
            ITEMS.register("syringe_poison", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_AWESOME =
            ITEMS.register("syringe_awesome", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_METAL_EMPTY =
            ITEMS.register("syringe_metal_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_METAL_STIMPAK =
            ITEMS.register("syringe_metal_stimpak", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_METAL_MEDX =
            ITEMS.register("syringe_metal_medx", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_METAL_PSYCHO =
            ITEMS.register("syringe_metal_psycho", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_METAL_SUPER =
            ITEMS.register("syringe_metal_super", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_TAINT =
            ITEMS.register("syringe_taint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SYRINGE_MKUNICORN =
            ITEMS.register("syringe_mkunicorn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IV_EMPTY =
            ITEMS.register("iv_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IV_BLOOD =
            ITEMS.register("iv_blood", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IV_XP_EMPTY =
            ITEMS.register("iv_xp_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IV_XP =
            ITEMS.register("iv_xp", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RADAWAY =
            ITEMS.register("radaway", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RADAWAY_STRONG =
            ITEMS.register("radaway_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RADAWAY_FLUSH =
            ITEMS.register("radaway_flush", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RADX =
            ITEMS.register("radx", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIOX =
            ITEMS.register("siox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILL_HERBAL =
            ITEMS.register("pill_herbal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> XANAX =
            ITEMS.register("xanax", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FMN =
            ITEMS.register("fmn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FIVE_HTP =
            ITEMS.register("five_htp", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MED_BAG =
            ITEMS.register("med_bag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILL_IODINE =
            ITEMS.register("pill_iodine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLAN_C =
            ITEMS.register("plan_c", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILL_RED =
            ITEMS.register("pill_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEALTH_BOY =
            ITEMS.register("stealth_boy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_FILTER =
            ITEMS.register("gas_mask_filter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_FILTER_MONO =
            ITEMS.register("gas_mask_filter_mono", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_FILTER_COMBO =
            ITEMS.register("gas_mask_filter_combo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_FILTER_RAG =
            ITEMS.register("gas_mask_filter_rag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_FILTER_PISS =
            ITEMS.register("gas_mask_filter_piss", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JETPACK_TANK =
            ITEMS.register("jetpack_tank", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_KIT_1 =
            ITEMS.register("gun_kit_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_KIT_2 =
            ITEMS.register("gun_kit_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CBT_DEVICE =
            ITEMS.register("cbt_device", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CIGARETTE =
            ITEMS.register("cigarette", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_EMPTY =
            ITEMS.register("can_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_SMART =
            ITEMS.register("can_smart", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_CREATURE =
            ITEMS.register("can_creature", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_REDBOMB =
            ITEMS.register("can_redbomb", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_MRSUGAR =
            ITEMS.register("can_mrsugar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_OVERCHARGE =
            ITEMS.register("can_overcharge", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_LUNA =
            ITEMS.register("can_luna", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_BEPIS =
            ITEMS.register("can_bepis", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_BREEN =
            ITEMS.register("can_breen", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAN_MUG =
            ITEMS.register("can_mug", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MUCHO_MANGO =
            ITEMS.register("mucho_mango", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_EMPTY =
            ITEMS.register("bottle_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_NUKA =
            ITEMS.register("bottle_nuka", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_CHERRY =
            ITEMS.register("bottle_cherry", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_QUANTUM =
            ITEMS.register("bottle_quantum", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_SPARKLE =
            ITEMS.register("bottle_sparkle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_RAD =
            ITEMS.register("bottle_rad", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE2_EMPTY =
            ITEMS.register("bottle2_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE2_KORL =
            ITEMS.register("bottle2_korl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE2_FRITZ =
            ITEMS.register("bottle2_fritz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE2_KORL_SPECIAL =
            ITEMS.register("bottle2_korl_special", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE2_FRITZ_SPECIAL =
            ITEMS.register("bottle2_fritz_special", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLASK_EMPTY =
            ITEMS.register("flask_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLASK_INFUSION =
            ITEMS.register("flask_infusion", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOCOLATE_MILK =
            ITEMS.register("chocolate_milk", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COFFEE =
            ITEMS.register("coffee", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COFFEE_RADIUM =
            ITEMS.register("coffee_radium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHOCOLATE =
            ITEMS.register("chocolate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAP_NUKA =
            ITEMS.register("cap_nuka", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAP_QUANTUM =
            ITEMS.register("cap_quantum", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAP_SPARKLE =
            ITEMS.register("cap_sparkle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAP_RAD =
            ITEMS.register("cap_rad", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAP_KORL =
            ITEMS.register("cap_korl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAP_FRITZ =
            ITEMS.register("cap_fritz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RING_PULL =
            ITEMS.register("ring_pull", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BDCL =
            ITEMS.register("bdcl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CANNED_CONSERVE =
            ITEMS.register("canned_conserve", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> CAN_KEY =
            ITEMS.register("can_key", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOAT_RUBBER =
            ITEMS.register("boat_rubber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CART =
            ITEMS.register("cart", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRAIN =
            ITEMS.register("train", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRONE =
            ITEMS.register("drone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIN_CREEPER =
            ITEMS.register("coin_creeper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIN_RADIATION =
            ITEMS.register("coin_radiation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIN_MASKMAN =
            ITEMS.register("coin_maskman", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIN_WORM =
            ITEMS.register("coin_worm", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COIN_UFO =
            ITEMS.register("coin_ufo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_EMPTY =
            ITEMS.register("rod_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD =
            ITEMS.register("rod", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_DUAL_EMPTY =
            ITEMS.register("rod_dual_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_DUAL =
            ITEMS.register("rod_dual", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_QUAD_EMPTY =
            ITEMS.register("rod_quad_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_QUAD =
            ITEMS.register("rod_quad", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_EMPTY =
            ITEMS.register("rod_zirnox_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_TRITIUM =
            ITEMS.register("rod_zirnox_tritium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX =
            ITEMS.register("rod_zirnox", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_NATURAL_URANIUM_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_natural_uranium_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_URANIUM_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_uranium_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_THORIUM_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_thorium_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_MOX_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_mox_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_PLUTONIUM_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_plutonium_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_U233_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_u233_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_U235_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_u235_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_LES_FUEL_DEPLETED =
            ITEMS.register("rod_zirnox_les_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_ZIRNOX_ZFB_MOX_DEPLETED =
            ITEMS.register("rod_zirnox_zfb_mox_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_NATURAL_URANIUM =
            ITEMS.register("waste_natural_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_URANIUM =
            ITEMS.register("waste_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_THORIUM =
            ITEMS.register("waste_thorium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_MOX =
            ITEMS.register("waste_mox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLUTONIUM =
            ITEMS.register("waste_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_U233 =
            ITEMS.register("waste_u233", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_U235 =
            ITEMS.register("waste_u235", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_SCHRABIDIUM =
            ITEMS.register("waste_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_ZFB_MOX =
            ITEMS.register("waste_zfb_mox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_U233 =
            ITEMS.register("waste_plate_u233", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_U235 =
            ITEMS.register("waste_plate_u235", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_MOX =
            ITEMS.register("waste_plate_mox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_PU239 =
            ITEMS.register("waste_plate_pu239", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_SA326 =
            ITEMS.register("waste_plate_sa326", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_RA226BE =
            ITEMS.register("waste_plate_ra226be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WASTE_PLATE_PU238BE =
            ITEMS.register("waste_plate_pu238be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_URANIUM =
            ITEMS.register("pile_rod_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_PU239 =
            ITEMS.register("pile_rod_pu239", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_PLUTONIUM =
            ITEMS.register("pile_rod_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_SOURCE =
            ITEMS.register("pile_rod_source", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_BORON =
            ITEMS.register("pile_rod_boron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_LITHIUM =
            ITEMS.register("pile_rod_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PILE_ROD_DETECTOR =
            ITEMS.register("pile_rod_detector", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_U233 =
            ITEMS.register("plate_fuel_u233", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_U235 =
            ITEMS.register("plate_fuel_u235", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_MOX =
            ITEMS.register("plate_fuel_mox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_PU239 =
            ITEMS.register("plate_fuel_pu239", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_SA326 =
            ITEMS.register("plate_fuel_sa326", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_RA226BE =
            ITEMS.register("plate_fuel_ra226be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLATE_FUEL_PU238BE =
            ITEMS.register("plate_fuel_pu238be", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PWR_FUEL =
            ITEMS.register("pwr_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PWR_FUEL_HOT =
            ITEMS.register("pwr_fuel_hot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PWR_FUEL_DEPLETED =
            ITEMS.register("pwr_fuel_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_LID =
            ITEMS.register("rbmk_lid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_LID_GLASS =
            ITEMS.register("rbmk_lid_glass", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_EMPTY =
            ITEMS.register("rbmk_fuel_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_UEU =
            ITEMS.register("rbmk_fuel_ueu", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_MEU =
            ITEMS.register("rbmk_fuel_meu", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEU233 =
            ITEMS.register("rbmk_fuel_heu233", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEU235 =
            ITEMS.register("rbmk_fuel_heu235", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_THMEU =
            ITEMS.register("rbmk_fuel_thmeu", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_LEP =
            ITEMS.register("rbmk_fuel_lep", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_MEP =
            ITEMS.register("rbmk_fuel_mep", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEP239 =
            ITEMS.register("rbmk_fuel_hep239", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEP241 =
            ITEMS.register("rbmk_fuel_hep241", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_LEA =
            ITEMS.register("rbmk_fuel_lea", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_MEA =
            ITEMS.register("rbmk_fuel_mea", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEA241 =
            ITEMS.register("rbmk_fuel_hea241", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEA242 =
            ITEMS.register("rbmk_fuel_hea242", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_MEN =
            ITEMS.register("rbmk_fuel_men", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEN =
            ITEMS.register("rbmk_fuel_hen", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_MOX =
            ITEMS.register("rbmk_fuel_mox", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_LES =
            ITEMS.register("rbmk_fuel_les", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_MES =
            ITEMS.register("rbmk_fuel_mes", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HES =
            ITEMS.register("rbmk_fuel_hes", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_LEAUS =
            ITEMS.register("rbmk_fuel_leaus", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_HEAUS =
            ITEMS.register("rbmk_fuel_heaus", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_PO210BE =
            ITEMS.register("rbmk_fuel_po210be", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_RA226BE =
            ITEMS.register("rbmk_fuel_ra226be", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_PU238BE =
            ITEMS.register("rbmk_fuel_pu238be", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_BALEFIRE_GOLD =
            ITEMS.register("rbmk_fuel_balefire_gold", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_FLASHLEAD =
            ITEMS.register("rbmk_fuel_flashlead", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_BALEFIRE =
            ITEMS.register("rbmk_fuel_balefire", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_ZFB_BISMUTH =
            ITEMS.register("rbmk_fuel_zfb_bismuth", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_ZFB_PU241 =
            ITEMS.register("rbmk_fuel_zfb_pu241", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_ZFB_AM_MIX =
            ITEMS.register("rbmk_fuel_zfb_am_mix", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_DRX =
            ITEMS.register("rbmk_fuel_drx", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_FUEL_TEST =
            ITEMS.register("rbmk_fuel_test", () -> new ItemRBMKRod(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_UEU =
            ITEMS.register("rbmk_pellet_ueu", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_MEU =
            ITEMS.register("rbmk_pellet_meu", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEU233 =
            ITEMS.register("rbmk_pellet_heu233", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEU235 =
            ITEMS.register("rbmk_pellet_heu235", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_THMEU =
            ITEMS.register("rbmk_pellet_thmeu", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_LEP =
            ITEMS.register("rbmk_pellet_lep", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_MEP =
            ITEMS.register("rbmk_pellet_mep", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEP239 =
            ITEMS.register("rbmk_pellet_hep239", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEP241 =
            ITEMS.register("rbmk_pellet_hep241", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_LEA =
            ITEMS.register("rbmk_pellet_lea", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_MEA =
            ITEMS.register("rbmk_pellet_mea", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEA241 =
            ITEMS.register("rbmk_pellet_hea241", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEA242 =
            ITEMS.register("rbmk_pellet_hea242", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_MEN =
            ITEMS.register("rbmk_pellet_men", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEN =
            ITEMS.register("rbmk_pellet_hen", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_MOX =
            ITEMS.register("rbmk_pellet_mox", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_LES =
            ITEMS.register("rbmk_pellet_les", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_MES =
            ITEMS.register("rbmk_pellet_mes", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HES =
            ITEMS.register("rbmk_pellet_hes", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_LEAUS =
            ITEMS.register("rbmk_pellet_leaus", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_HEAUS =
            ITEMS.register("rbmk_pellet_heaus", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_PO210BE =
            ITEMS.register("rbmk_pellet_po210be", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_RA226BE =
            ITEMS.register("rbmk_pellet_ra226be", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_PU238BE =
            ITEMS.register("rbmk_pellet_pu238be", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_BALEFIRE_GOLD =
            ITEMS.register("rbmk_pellet_balefire_gold", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_FLASHLEAD =
            ITEMS.register("rbmk_pellet_flashlead", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_BALEFIRE =
            ITEMS.register("rbmk_pellet_balefire", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_ZFB_BISMUTH =
            ITEMS.register("rbmk_pellet_zfb_bismuth", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_ZFB_PU241 =
            ITEMS.register("rbmk_pellet_zfb_pu241", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_ZFB_AM_MIX =
            ITEMS.register("rbmk_pellet_zfb_am_mix", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_PELLET_DRX =
            ITEMS.register("rbmk_pellet_drx", () -> new ItemRBMKPellet(new Item.Properties()));

    public static final RegistryObject<Item> WATZ_PELLET =
            ITEMS.register("watz_pellet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATZ_PELLET_DEPLETED =
            ITEMS.register("watz_pellet_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ICF_PELLET_EMPTY =
            ITEMS.register("icf_pellet_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ICF_PELLET =
            ITEMS.register("icf_pellet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ICF_PELLET_DEPLETED =
            ITEMS.register("icf_pellet_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRAP_PLASTIC =
            ITEMS.register("scrap_plastic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRAP =
            ITEMS.register("scrap", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRAP_OIL =
            ITEMS.register("scrap_oil", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRAP_NUCLEAR =
            ITEMS.register("scrap_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRINITITE =
            ITEMS.register("trinitite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_LONG =
            ITEMS.register("nuclear_waste_long", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_LONG_TINY =
            ITEMS.register("nuclear_waste_long_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_SHORT =
            ITEMS.register("nuclear_waste_short", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_SHORT_TINY =
            ITEMS.register("nuclear_waste_short_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_LONG_DEPLETED =
            ITEMS.register("nuclear_waste_long_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_LONG_DEPLETED_TINY =
            ITEMS.register("nuclear_waste_long_depleted_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_SHORT_DEPLETED =
            ITEMS.register("nuclear_waste_short_depleted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_SHORT_DEPLETED_TINY =
            ITEMS.register("nuclear_waste_short_depleted_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE =
            ITEMS.register("nuclear_waste", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_TINY =
            ITEMS.register("nuclear_waste_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_VITRIFIED =
            ITEMS.register("nuclear_waste_vitrified", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_VITRIFIED_TINY =
            ITEMS.register("nuclear_waste_vitrified_tiny", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_GRAPHITE =
            ITEMS.register("debris_graphite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_METAL =
            ITEMS.register("debris_metal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_FUEL =
            ITEMS.register("debris_fuel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_CONCRETE =
            ITEMS.register("debris_concrete", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_EXCHANGER =
            ITEMS.register("debris_exchanger", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_SHRAPNEL =
            ITEMS.register("debris_shrapnel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEBRIS_ELEMENT =
            ITEMS.register("debris_element", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CONTAINMENT_BOX =
            ITEMS.register("containment_box", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLASTIC_BAG =
            ITEMS.register("plastic_bag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CASING_BAG =
            ITEMS.register("casing_bag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_IGNITER =
            ITEMS.register("test_nuke_igniter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_PROPELLANT =
            ITEMS.register("test_nuke_propellant", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_TIER1_SHIELDING =
            ITEMS.register("test_nuke_tier1_shielding", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_TIER2_SHIELDING =
            ITEMS.register("test_nuke_tier2_shielding", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_TIER1_BULLET =
            ITEMS.register("test_nuke_tier1_bullet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_TIER2_BULLET =
            ITEMS.register("test_nuke_tier2_bullet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_TIER1_TARGET =
            ITEMS.register("test_nuke_tier1_target", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEST_NUKE_TIER2_TARGET =
            ITEMS.register("test_nuke_tier2_target", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORDITE =
            ITEMS.register("cordite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALLISTITE =
            ITEMS.register("ballistite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALL_DYNAMITE =
            ITEMS.register("ball_dynamite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALL_TNT =
            ITEMS.register("ball_tnt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALL_TATB =
            ITEMS.register("ball_tatb", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALL_FIRECLAY =
            ITEMS.register("ball_fireclay", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_CLUSTER =
            ITEMS.register("pellet_cluster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_FIRE =
            ITEMS.register("powder_fire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_ICE =
            ITEMS.register("powder_ice", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_POISON =
            ITEMS.register("powder_poison", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_THERMITE =
            ITEMS.register("powder_thermite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_GAS =
            ITEMS.register("pellet_gas", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGNETRON =
            ITEMS.register("magnetron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_BUCKSHOT =
            ITEMS.register("pellet_buckshot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PELLET_CHARGED =
            ITEMS.register("pellet_charged", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESIGNATOR =
            ITEMS.register("designator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESIGNATOR_RANGE =
            ITEMS.register("designator_range", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESIGNATOR_MANUAL =
            ITEMS.register("designator_manual", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESIGNATOR_ARTY_RANGE =
            ITEMS.register("designator_arty_range", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LINKER =
            ITEMS.register("linker", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REACTOR_SENSOR =
            ITEMS.register("reactor_sensor", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OIL_DETECTOR =
            ITEMS.register("oil_detector", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DOSIMETER =
            ITEMS.register("dosimeter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GEIGER_COUNTER =
            ITEMS.register("geiger_counter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIGAMMA_DIAGNOSTIC =
            ITEMS.register("digamma_diagnostic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POLLUTION_DETECTOR =
            ITEMS.register("pollution_detector", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORE_DENSITY_SCANNER =
            ITEMS.register("ore_density_scanner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SURVEY_SCANNER =
            ITEMS.register("survey_scanner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIRROR_TOOL =
            ITEMS.register("mirror_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RBMK_TOOL =
            ITEMS.register("rbmk_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COLTAN_TOOL =
            ITEMS.register("coltan_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POWER_NET_TOOL =
            ITEMS.register("power_net_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ANALYSIS_TOOL =
            ITEMS.register("analysis_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COUPLING_TOOL =
            ITEMS.register("coupling_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRONE_LINKER =
            ITEMS.register("drone_linker", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RADAR_LINKER =
            ITEMS.register("radar_linker", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SETTINGS_TOOL =
            ITEMS.register("settings_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEMPLATE_FOLDER =
            ITEMS.register("template_folder", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JOURNAL_PIP =
            ITEMS.register("journal_pip", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JOURNAL_BJ =
            ITEMS.register("journal_bj", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JOURNAL_SILVER =
            ITEMS.register("journal_silver", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASSEMBLY_TEMPLATE =
            ITEMS.register("assembly_template", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEMISTRY_TEMPLATE =
            ITEMS.register("chemistry_template", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEMISTRY_ICON =
            ITEMS.register("chemistry_icon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRUCIBLE_TEMPLATE =
            ITEMS.register("crucible_template", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_IDENTIFIER =
            ITEMS.register("fluid_identifier", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_IDENTIFIER_MULTI =
            ITEMS.register("fluid_identifier_multi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_ICON =
            ITEMS.register("fluid_icon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIREN_TRACK =
            ITEMS.register("siren_track", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUID_DUCT =
            ITEMS.register("fluid_duct", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOBMAZON =
            ITEMS.register("bobmazon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOBMAZON_HIDDEN =
            ITEMS.register("bobmazon_hidden", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LAUNCH_CODE_PIECE =
            ITEMS.register("launch_code_piece", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LAUNCH_CODE =
            ITEMS.register("launch_code", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LAUNCH_KEY =
            ITEMS.register("launch_key", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_ASSEMBLY =
            ITEMS.register("missile_assembly", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_GENERIC =
            ITEMS.register("missile_generic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_ANTI_BALLISTIC =
            ITEMS.register("missile_anti_ballistic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_INCENDIARY =
            ITEMS.register("missile_incendiary", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_CLUSTER =
            ITEMS.register("missile_cluster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_BUSTER =
            ITEMS.register("missile_buster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_DECOY =
            ITEMS.register("missile_decoy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_STRONG =
            ITEMS.register("missile_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_INCENDIARY_STRONG =
            ITEMS.register("missile_incendiary_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_CLUSTER_STRONG =
            ITEMS.register("missile_cluster_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_BUSTER_STRONG =
            ITEMS.register("missile_buster_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_EMP_STRONG =
            ITEMS.register("missile_emp_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_BURST =
            ITEMS.register("missile_burst", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_INFERNO =
            ITEMS.register("missile_inferno", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_RAIN =
            ITEMS.register("missile_rain", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_DRILL =
            ITEMS.register("missile_drill", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_NUCLEAR =
            ITEMS.register("missile_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_NUCLEAR_CLUSTER =
            ITEMS.register("missile_nuclear_cluster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_VOLCANO =
            ITEMS.register("missile_volcano", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_DOOMSDAY =
            ITEMS.register("missile_doomsday", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_DOOMSDAY_RUSTED =
            ITEMS.register("missile_doomsday_rusted", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_TAINT =
            ITEMS.register("missile_taint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_MICRO =
            ITEMS.register("missile_micro", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_BHOLE =
            ITEMS.register("missile_bhole", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SCHRABIDIUM =
            ITEMS.register("missile_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_EMP =
            ITEMS.register("missile_emp", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SHUTTLE =
            ITEMS.register("missile_shuttle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_STEALTH =
            ITEMS.register("missile_stealth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_TEST =
            ITEMS.register("missile_test", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_10_KEROSENE =
            ITEMS.register("mp_thruster_10_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_10_KEROSENE_TEC =
            ITEMS.register("mp_thruster_10_kerosene_tec", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_10_SOLID =
            ITEMS.register("mp_thruster_10_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_10_XENON =
            ITEMS.register("mp_thruster_10_xenon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_KEROSENE =
            ITEMS.register("mp_thruster_15_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_KEROSENE_TEC =
            ITEMS.register("mp_thruster_15_kerosene_tec", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_KEROSENE_DUAL =
            ITEMS.register("mp_thruster_15_kerosene_dual", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_KEROSENE_TRIPLE =
            ITEMS.register("mp_thruster_15_kerosene_triple", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_SOLID =
            ITEMS.register("mp_thruster_15_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_SOLID_HEXDECUPLE =
            ITEMS.register("mp_thruster_15_solid_hexdecuple", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_HYDROGEN =
            ITEMS.register("mp_thruster_15_hydrogen", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_HYDROGEN_DUAL =
            ITEMS.register("mp_thruster_15_hydrogen_dual", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_BALEFIRE_SHORT =
            ITEMS.register("mp_thruster_15_balefire_short", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_BALEFIRE =
            ITEMS.register("mp_thruster_15_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_BALEFIRE_LARGE =
            ITEMS.register("mp_thruster_15_balefire_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_15_BALEFIRE_LARGE_RAD =
            ITEMS.register("mp_thruster_15_balefire_large_rad", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_20_KEROSENE =
            ITEMS.register("mp_thruster_20_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_20_KEROSENE_DUAL =
            ITEMS.register("mp_thruster_20_kerosene_dual", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_20_KEROSENE_TRIPLE =
            ITEMS.register("mp_thruster_20_kerosene_triple", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_20_SOLID =
            ITEMS.register("mp_thruster_20_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_20_SOLID_MULTI =
            ITEMS.register("mp_thruster_20_solid_multi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_THRUSTER_20_SOLID_MULTIER =
            ITEMS.register("mp_thruster_20_solid_multier", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_10_FLAT =
            ITEMS.register("mp_stability_10_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_10_CRUISE =
            ITEMS.register("mp_stability_10_cruise", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_10_SPACE =
            ITEMS.register("mp_stability_10_space", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_15_FLAT =
            ITEMS.register("mp_stability_15_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_15_THIN =
            ITEMS.register("mp_stability_15_thin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_15_SOYUZ =
            ITEMS.register("mp_stability_15_soyuz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_STABILITY_20_FLAT =
            ITEMS.register("mp_stability_20_flat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE =
            ITEMS.register("mp_fuselage_10_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_CAMO =
            ITEMS.register("mp_fuselage_10_kerosene_camo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_DESERT =
            ITEMS.register("mp_fuselage_10_kerosene_desert", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_SKY =
            ITEMS.register("mp_fuselage_10_kerosene_sky", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_FLAMES =
            ITEMS.register("mp_fuselage_10_kerosene_flames", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_INSULATION =
            ITEMS.register("mp_fuselage_10_kerosene_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_SLEEK =
            ITEMS.register("mp_fuselage_10_kerosene_sleek", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_METAL =
            ITEMS.register("mp_fuselage_10_kerosene_metal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_KEROSENE_TAINT =
            ITEMS.register("mp_fuselage_10_kerosene_taint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID =
            ITEMS.register("mp_fuselage_10_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_FLAMES =
            ITEMS.register("mp_fuselage_10_solid_flames", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_INSULATION =
            ITEMS.register("mp_fuselage_10_solid_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_SLEEK =
            ITEMS.register("mp_fuselage_10_solid_sleek", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_SOVIET_GLORY =
            ITEMS.register("mp_fuselage_10_solid_soviet_glory", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_CATHEDRAL =
            ITEMS.register("mp_fuselage_10_solid_cathedral", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_MOONLIT =
            ITEMS.register("mp_fuselage_10_solid_moonlit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_BATTERY =
            ITEMS.register("mp_fuselage_10_solid_battery", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_SOLID_DURACELL =
            ITEMS.register("mp_fuselage_10_solid_duracell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_XENON =
            ITEMS.register("mp_fuselage_10_xenon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_XENON_BHOLE =
            ITEMS.register("mp_fuselage_10_xenon_bhole", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE =
            ITEMS.register("mp_fuselage_10_long_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_CAMO =
            ITEMS.register("mp_fuselage_10_long_kerosene_camo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_DESERT =
            ITEMS.register("mp_fuselage_10_long_kerosene_desert", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_SKY =
            ITEMS.register("mp_fuselage_10_long_kerosene_sky", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_FLAMES =
            ITEMS.register("mp_fuselage_10_long_kerosene_flames", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_INSULATION =
            ITEMS.register("mp_fuselage_10_long_kerosene_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_SLEEK =
            ITEMS.register("mp_fuselage_10_long_kerosene_sleek", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_METAL =
            ITEMS.register("mp_fuselage_10_long_kerosene_metal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_TAINT =
            ITEMS.register("mp_fuselage_10_long_kerosene_taint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_DASH =
            ITEMS.register("mp_fuselage_10_long_kerosene_dash", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_KEROSENE_VAP =
            ITEMS.register("mp_fuselage_10_long_kerosene_vap", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID =
            ITEMS.register("mp_fuselage_10_long_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID_FLAMES =
            ITEMS.register("mp_fuselage_10_long_solid_flames", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID_INSULATION =
            ITEMS.register("mp_fuselage_10_long_solid_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID_SLEEK =
            ITEMS.register("mp_fuselage_10_long_solid_sleek", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID_SOVIET_GLORY =
            ITEMS.register("mp_fuselage_10_long_solid_soviet_glory", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID_BULLET =
            ITEMS.register("mp_fuselage_10_long_solid_bullet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_LONG_SOLID_SILVERMOONLIGHT =
            ITEMS.register("mp_fuselage_10_long_solid_silvermoonlight", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_15_KEROSENE =
            ITEMS.register("mp_fuselage_10_15_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_15_SOLID =
            ITEMS.register("mp_fuselage_10_15_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_15_HYDROGEN =
            ITEMS.register("mp_fuselage_10_15_hydrogen", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_10_15_BALEFIRE =
            ITEMS.register("mp_fuselage_10_15_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE =
            ITEMS.register("mp_fuselage_15_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_CAMO =
            ITEMS.register("mp_fuselage_15_kerosene_camo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_DESERT =
            ITEMS.register("mp_fuselage_15_kerosene_desert", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_SKY =
            ITEMS.register("mp_fuselage_15_kerosene_sky", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_INSULATION =
            ITEMS.register("mp_fuselage_15_kerosene_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_METAL =
            ITEMS.register("mp_fuselage_15_kerosene_metal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_DECORATED =
            ITEMS.register("mp_fuselage_15_kerosene_decorated", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_STEAMPUNK =
            ITEMS.register("mp_fuselage_15_kerosene_steampunk", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_POLITE =
            ITEMS.register("mp_fuselage_15_kerosene_polite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_BLACKJACK =
            ITEMS.register("mp_fuselage_15_kerosene_blackjack", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_LAMBDA =
            ITEMS.register("mp_fuselage_15_kerosene_lambda", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_MINUTEMAN =
            ITEMS.register("mp_fuselage_15_kerosene_minuteman", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_PIP =
            ITEMS.register("mp_fuselage_15_kerosene_pip", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_TAINT =
            ITEMS.register("mp_fuselage_15_kerosene_taint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_KEROSENE_YUCK =
            ITEMS.register("mp_fuselage_15_kerosene_yuck", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID =
            ITEMS.register("mp_fuselage_15_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_INSULATION =
            ITEMS.register("mp_fuselage_15_solid_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_DESH =
            ITEMS.register("mp_fuselage_15_solid_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_SOVIET_GLORY =
            ITEMS.register("mp_fuselage_15_solid_soviet_glory", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_SOVIET_STANK =
            ITEMS.register("mp_fuselage_15_solid_soviet_stank", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_FAUST =
            ITEMS.register("mp_fuselage_15_solid_faust", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_SILVERMOONLIGHT =
            ITEMS.register("mp_fuselage_15_solid_silvermoonlight", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_SNOWY =
            ITEMS.register("mp_fuselage_15_solid_snowy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_PANORAMA =
            ITEMS.register("mp_fuselage_15_solid_panorama", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_ROSES =
            ITEMS.register("mp_fuselage_15_solid_roses", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_SOLID_MIMI =
            ITEMS.register("mp_fuselage_15_solid_mimi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_HYDROGEN =
            ITEMS.register("mp_fuselage_15_hydrogen", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_HYDROGEN_CATHEDRAL =
            ITEMS.register("mp_fuselage_15_hydrogen_cathedral", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_BALEFIRE =
            ITEMS.register("mp_fuselage_15_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_20_KEROSENE =
            ITEMS.register("mp_fuselage_15_20_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_20_KEROSENE_MAGNUSSON =
            ITEMS.register("mp_fuselage_15_20_kerosene_magnusson", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_15_20_SOLID =
            ITEMS.register("mp_fuselage_15_20_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_FUSELAGE_20_KEROSENE =
            ITEMS.register("mp_fuselage_20_kerosene", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_HE =
            ITEMS.register("mp_warhead_10_he", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_INCENDIARY =
            ITEMS.register("mp_warhead_10_incendiary", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_BUSTER =
            ITEMS.register("mp_warhead_10_buster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_NUCLEAR =
            ITEMS.register("mp_warhead_10_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_NUCLEAR_LARGE =
            ITEMS.register("mp_warhead_10_nuclear_large", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_TAINT =
            ITEMS.register("mp_warhead_10_taint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_10_CLOUD =
            ITEMS.register("mp_warhead_10_cloud", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_HE =
            ITEMS.register("mp_warhead_15_he", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_INCENDIARY =
            ITEMS.register("mp_warhead_15_incendiary", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_NUCLEAR =
            ITEMS.register("mp_warhead_15_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_NUCLEAR_SHARK =
            ITEMS.register("mp_warhead_15_nuclear_shark", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_NUCLEAR_MIMI =
            ITEMS.register("mp_warhead_15_nuclear_mimi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_BOXCAR =
            ITEMS.register("mp_warhead_15_boxcar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_N2 =
            ITEMS.register("mp_warhead_15_n2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_BALEFIRE =
            ITEMS.register("mp_warhead_15_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_15_TURBINE =
            ITEMS.register("mp_warhead_15_turbine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_WARHEAD_20_HE =
            ITEMS.register("mp_warhead_20_he", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_CHIP_1 =
            ITEMS.register("mp_chip_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_CHIP_2 =
            ITEMS.register("mp_chip_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_CHIP_3 =
            ITEMS.register("mp_chip_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_CHIP_4 =
            ITEMS.register("mp_chip_4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MP_CHIP_5 =
            ITEMS.register("mp_chip_5", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_CAMO =
            ITEMS.register("missile_skin_camo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_DESERT =
            ITEMS.register("missile_skin_desert", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_FLAMES =
            ITEMS.register("missile_skin_flames", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_MANLY_PINK =
            ITEMS.register("missile_skin_manly_pink", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_ORANGE_INSULATION =
            ITEMS.register("missile_skin_orange_insulation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_SLEEK =
            ITEMS.register("missile_skin_sleek", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_SOVIET_GLORY =
            ITEMS.register("missile_skin_soviet_glory", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_SOVIET_STANK =
            ITEMS.register("missile_skin_soviet_stank", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SKIN_METAL =
            ITEMS.register("missile_skin_metal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_CUSTOM =
            ITEMS.register("missile_custom", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SOYUZ =
            ITEMS.register("missile_soyuz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_SOYUZ_LANDER =
            ITEMS.register("missile_soyuz_lander", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_MAPPER =
            ITEMS.register("sat_mapper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_SCANNER =
            ITEMS.register("sat_scanner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_RADAR =
            ITEMS.register("sat_radar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_LASER =
            ITEMS.register("sat_laser", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_FOEQ =
            ITEMS.register("sat_foeq", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_RESONATOR =
            ITEMS.register("sat_resonator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_MINER =
            ITEMS.register("sat_miner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_LUNAR_MINER =
            ITEMS.register("sat_lunar_miner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_GERALD =
            ITEMS.register("sat_gerald", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_CHIP =
            ITEMS.register("sat_chip", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_INTERFACE =
            ITEMS.register("sat_interface", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_COORD =
            ITEMS.register("sat_coord", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_DESIGNATOR =
            ITEMS.register("sat_designator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAT_RELAY =
            ITEMS.register("sat_relay", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_MISC =
            ITEMS.register("ammo_misc", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_SHELL =
            ITEMS.register("ammo_shell", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_FIREEXT =
            ITEMS.register("ammo_fireext", () -> new ItemEnumMulti(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_DGK =
            ITEMS.register("ammo_dgk", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_ARTY =
            ITEMS.register("ammo_arty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_HIMARS =
            ITEMS.register("ammo_himars", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_B92 =
            ITEMS.register("gun_b92", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_B92_AMMO =
            ITEMS.register("gun_b92_ammo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_CRYOCANNON =
            ITEMS.register("gun_cryocannon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_CRYOLATOR_AMMO =
            ITEMS.register("gun_cryolator_ammo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FIREEXT =
            ITEMS.register("gun_fireext", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_DEBUG =
            ITEMS.register("gun_debug", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_DEBUG =
            ITEMS.register("ammo_debug", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_PEPPERBOX =
            ITEMS.register("gun_pepperbox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_LIGHT_REVOLVER =
            ITEMS.register("gun_light_revolver", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_LIGHT_REVOLVER_ATLAS =
            ITEMS.register("gun_light_revolver_atlas", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_LIGHT_REVOLVER_DANI =
            ITEMS.register("gun_light_revolver_dani", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_HENRY =
            ITEMS.register("gun_henry", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_GREASEGUN =
            ITEMS.register("gun_greasegun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MARESLEG =
            ITEMS.register("gun_maresleg", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MARESLEG_AKIMBO =
            ITEMS.register("gun_maresleg_akimbo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MARESLEG_BROKEN =
            ITEMS.register("gun_maresleg_broken", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FLAREGUN =
            ITEMS.register("gun_flaregun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_HEAVY_REVOLVER =
            ITEMS.register("gun_heavy_revolver", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_HEAVY_REVOLVER_LILMAC =
            ITEMS.register("gun_heavy_revolver_lilmac", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_HEAVY_REVOLVER_PROTEGE =
            ITEMS.register("gun_heavy_revolver_protege", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_CARBINE =
            ITEMS.register("gun_carbine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_AM180 =
            ITEMS.register("gun_am180", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_LIBERATOR =
            ITEMS.register("gun_liberator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_CONGOLAKE =
            ITEMS.register("gun_congolake", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FLAMER =
            ITEMS.register("gun_flamer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FLAMER_TOPAZ =
            ITEMS.register("gun_flamer_topaz", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FLAMER_DAYBREAKER =
            ITEMS.register("gun_flamer_daybreaker", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_UZI =
            ITEMS.register("gun_uzi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_UZI_AKIMBO =
            ITEMS.register("gun_uzi_akimbo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_SPAS12 =
            ITEMS.register("gun_spas12", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_PANZERSCHRECK =
            ITEMS.register("gun_panzerschreck", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_G3 =
            ITEMS.register("gun_g3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_STINGER =
            ITEMS.register("gun_stinger", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_CHEMTHROWER =
            ITEMS.register("gun_chemthrower", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_M2 =
            ITEMS.register("gun_m2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_AUTOSHOTGUN =
            ITEMS.register("gun_autoshotgun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_AUTOSHOTGUN_SHREDDER =
            ITEMS.register("gun_autoshotgun_shredder", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_AUTOSHOTGUN_SEXY =
            ITEMS.register("gun_autoshotgun_sexy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_QUADRO =
            ITEMS.register("gun_quadro", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_LAG =
            ITEMS.register("gun_lag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MINIGUN =
            ITEMS.register("gun_minigun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MINIGUN_LACUNAE =
            ITEMS.register("gun_minigun_lacunae", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MISSILE_LAUNCHER =
            ITEMS.register("gun_missile_launcher", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_TESLA_CANNON =
            ITEMS.register("gun_tesla_cannon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_STG77 =
            ITEMS.register("gun_stg77", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_TAU =
            ITEMS.register("gun_tau", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FATMAN =
            ITEMS.register("gun_fatman", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_LASRIFLE =
            ITEMS.register("gun_lasrifle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_COILGUN =
            ITEMS.register("gun_coilgun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_HANGMAN =
            ITEMS.register("gun_hangman", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_MAS36 =
            ITEMS.register("gun_mas36", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_BOLTER =
            ITEMS.register("gun_bolter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_FOLLY =
            ITEMS.register("gun_folly", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_ABERRATOR =
            ITEMS.register("gun_aberrator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_ABERRATOR_EOTT =
            ITEMS.register("gun_aberrator_eott", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_DOUBLE_BARREL =
            ITEMS.register("gun_double_barrel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUN_DOUBLE_BARREL_SACRED_DRAGON =
            ITEMS.register("gun_double_barrel_sacred_dragon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_STANDARD =
            ITEMS.register("ammo_standard", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_SECRET =
            ITEMS.register("ammo_secret", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRUCIBLE =
            ITEMS.register("crucible", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STICK_DYNAMITE =
            ITEMS.register("stick_dynamite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STICK_DYNAMITE_FISHING =
            ITEMS.register("stick_dynamite_fishing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STICK_TNT =
            ITEMS.register("stick_tnt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STICK_SEMTEX =
            ITEMS.register("stick_semtex", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STICK_C4 =
            ITEMS.register("stick_c4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_GENERIC =
            ITEMS.register("grenade_generic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_STRONG =
            ITEMS.register("grenade_strong", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_FRAG =
            ITEMS.register("grenade_frag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_FIRE =
            ITEMS.register("grenade_fire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_SHRAPNEL =
            ITEMS.register("grenade_shrapnel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_CLUSTER =
            ITEMS.register("grenade_cluster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_FLARE =
            ITEMS.register("grenade_flare", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_ELECTRIC =
            ITEMS.register("grenade_electric", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_POISON =
            ITEMS.register("grenade_poison", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_GAS =
            ITEMS.register("grenade_gas", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_PULSE =
            ITEMS.register("grenade_pulse", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_PLASMA =
            ITEMS.register("grenade_plasma", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_TAU =
            ITEMS.register("grenade_tau", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_SCHRABIDIUM =
            ITEMS.register("grenade_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_LEMON =
            ITEMS.register("grenade_lemon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_GASCAN =
            ITEMS.register("grenade_gascan", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_KYIV =
            ITEMS.register("grenade_kyiv", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_MK2 =
            ITEMS.register("grenade_mk2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_ASCHRAB =
            ITEMS.register("grenade_aschrab", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_NUKE =
            ITEMS.register("grenade_nuke", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_NUCLEAR =
            ITEMS.register("grenade_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_ZOMG =
            ITEMS.register("grenade_zomg", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_BLACK_HOLE =
            ITEMS.register("grenade_black_hole", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_CLOUD =
            ITEMS.register("grenade_cloud", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_PINK_CLOUD =
            ITEMS.register("grenade_pink_cloud", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ULLAPOOL_CABER =
            ITEMS.register("ullapool_caber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_GENERIC =
            ITEMS.register("grenade_if_generic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_HE =
            ITEMS.register("grenade_if_he", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_BOUNCY =
            ITEMS.register("grenade_if_bouncy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_STICKY =
            ITEMS.register("grenade_if_sticky", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_IMPACT =
            ITEMS.register("grenade_if_impact", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_INCENDIARY =
            ITEMS.register("grenade_if_incendiary", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_TOXIC =
            ITEMS.register("grenade_if_toxic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_CONCUSSION =
            ITEMS.register("grenade_if_concussion", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_BRIMSTONE =
            ITEMS.register("grenade_if_brimstone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_MYSTERY =
            ITEMS.register("grenade_if_mystery", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_SPARK =
            ITEMS.register("grenade_if_spark", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_HOPWIRE =
            ITEMS.register("grenade_if_hopwire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_IF_NULL =
            ITEMS.register("grenade_if_null", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_SMART =
            ITEMS.register("grenade_smart", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_MIRV =
            ITEMS.register("grenade_mirv", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_BREACH =
            ITEMS.register("grenade_breach", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_BURST =
            ITEMS.register("grenade_burst", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUCLEAR_WASTE_PEARL =
            ITEMS.register("nuclear_waste_pearl", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WEAPONIZED_STARBLASTER_CELL =
            ITEMS.register("weaponized_starblaster_cell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOMB_WAFFLE =
            ITEMS.register("bomb_waffle", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHNITZEL_VEGAN =
            ITEMS.register("schnitzel_vegan", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COTTON_CANDY =
            ITEMS.register("cotton_candy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> APPLE_LEAD =
            ITEMS.register("apple_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> APPLE_SCHRABIDIUM =
            ITEMS.register("apple_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEM_FLAKES =
            ITEMS.register("tem_flakes", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLOWING_STEW =
            ITEMS.register("glowing_stew", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALEFIRE_SCRAMBLED =
            ITEMS.register("balefire_scrambled", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALEFIRE_AND_HAM =
            ITEMS.register("balefire_and_ham", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEMON =
            ITEMS.register("lemon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEFINITELYFOOD =
            ITEMS.register("definitelyfood", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOOPS =
            ITEMS.register("loops", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOOP_STEW =
            ITEMS.register("loop_stew", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPONGEBOB_MACARONI =
            ITEMS.register("spongebob_macaroni", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FOODITEM =
            ITEMS.register("fooditem", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TWINKIE =
            ITEMS.register("twinkie", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STATIC_SANDWICH =
            ITEMS.register("static_sandwich", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PUDDING =
            ITEMS.register("pudding", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PANCAKE =
            ITEMS.register("pancake", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET =
            ITEMS.register("nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PEAS =
            ITEMS.register("peas", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MARSHMALLOW =
            ITEMS.register("marshmallow", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEESE =
            ITEMS.register("cheese", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUESADILLA =
            ITEMS.register("quesadilla", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLYPHID_MEAT =
            ITEMS.register("glyphid_meat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLYPHID_MEAT_GRILLED =
            ITEMS.register("glyphid_meat_grilled", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EGG_GLYPHID =
            ITEMS.register("egg_glyphid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MED_IPECAC =
            ITEMS.register("med_ipecac", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MED_PTSD =
            ITEMS.register("med_ptsd", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MED_SCHIZOPHRENIA =
            ITEMS.register("med_schizophrenia", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CANTEEN_VODKA =
            ITEMS.register("canteen_vodka", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CANTEEN_FAB =
            ITEMS.register("canteen_fab", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEFUSER =
            ITEMS.register("defuser", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REACHER =
            ITEMS.register("reacher", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_TOOL =
            ITEMS.register("bismuth_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MELTDOWN_TOOL =
            ITEMS.register("meltdown_tool", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLAME_PONY =
            ITEMS.register("flame_pony", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLAME_CONSPIRACY =
            ITEMS.register("flame_conspiracy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLAME_POLITICS =
            ITEMS.register("flame_politics", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLAME_OPINION =
            ITEMS.register("flame_opinion", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EARLY_EXPLOSIVE_LENSES =
            ITEMS.register("early_explosive_lenses", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EXPLOSIVE_LENSES =
            ITEMS.register("explosive_lenses", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GADGET_WIREING =
            ITEMS.register("gadget_wireing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GADGET_CORE =
            ITEMS.register("gadget_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOY_IGNITER =
            ITEMS.register("boy_igniter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOY_PROPELLANT =
            ITEMS.register("boy_propellant", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOY_BULLET =
            ITEMS.register("boy_bullet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOY_TARGET =
            ITEMS.register("boy_target", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOY_SHIELDING =
            ITEMS.register("boy_shielding", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAN_IGNITER =
            ITEMS.register("man_igniter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAN_CORE =
            ITEMS.register("man_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIKE_CORE =
            ITEMS.register("mike_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIKE_DEUT =
            ITEMS.register("mike_deut", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIKE_COOLING_UNIT =
            ITEMS.register("mike_cooling_unit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TSAR_CORE =
            ITEMS.register("tsar_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLEIJA_IGNITER =
            ITEMS.register("fleija_igniter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLEIJA_PROPELLANT =
            ITEMS.register("fleija_propellant", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLEIJA_CORE =
            ITEMS.register("fleija_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLINIUM_IGNITER =
            ITEMS.register("solinium_igniter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLINIUM_PROPELLANT =
            ITEMS.register("solinium_propellant", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLINIUM_CORE =
            ITEMS.register("solinium_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> N2_CHARGE =
            ITEMS.register("n2_charge", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EGG_BALEFIRE_SHARD =
            ITEMS.register("egg_balefire_shard", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EGG_BALEFIRE =
            ITEMS.register("egg_balefire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_TNT =
            ITEMS.register("custom_tnt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_NUKE =
            ITEMS.register("custom_nuke", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_HYDRO =
            ITEMS.register("custom_hydro", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_AMAT =
            ITEMS.register("custom_amat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_DIRTY =
            ITEMS.register("custom_dirty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_SCHRAB =
            ITEMS.register("custom_schrab", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_FALL =
            ITEMS.register("custom_fall", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_GENERIC =
            ITEMS.register("battery_generic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_ADVANCED =
            ITEMS.register("battery_advanced", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_LITHIUM =
            ITEMS.register("battery_lithium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SCHRABIDIUM =
            ITEMS.register("battery_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK =
            ITEMS.register("battery_spark", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_TRIXITE =
            ITEMS.register("battery_trixite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_CREATIVE =
            ITEMS.register("battery_creative", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_RED_CELL =
            ITEMS.register("battery_red_cell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_RED_CELL_6 =
            ITEMS.register("battery_red_cell_6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_RED_CELL_24 =
            ITEMS.register("battery_red_cell_24", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_ADVANCED_CELL =
            ITEMS.register("battery_advanced_cell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_ADVANCED_CELL_4 =
            ITEMS.register("battery_advanced_cell_4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_ADVANCED_CELL_12 =
            ITEMS.register("battery_advanced_cell_12", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_LITHIUM_CELL =
            ITEMS.register("battery_lithium_cell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_LITHIUM_CELL_3 =
            ITEMS.register("battery_lithium_cell_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_LITHIUM_CELL_6 =
            ITEMS.register("battery_lithium_cell_6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SCHRABIDIUM_CELL =
            ITEMS.register("battery_schrabidium_cell", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SCHRABIDIUM_CELL_2 =
            ITEMS.register("battery_schrabidium_cell_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SCHRABIDIUM_CELL_4 =
            ITEMS.register("battery_schrabidium_cell_4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_6 =
            ITEMS.register("battery_spark_cell_6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_25 =
            ITEMS.register("battery_spark_cell_25", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_100 =
            ITEMS.register("battery_spark_cell_100", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_1000 =
            ITEMS.register("battery_spark_cell_1000", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_2500 =
            ITEMS.register("battery_spark_cell_2500", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_10000 =
            ITEMS.register("battery_spark_cell_10000", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SPARK_CELL_POWER =
            ITEMS.register("battery_spark_cell_power", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUBE_POWER =
            ITEMS.register("cube_power", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_URANIUM =
            ITEMS.register("battery_sc_uranium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_TECHNETIUM =
            ITEMS.register("battery_sc_technetium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_PLUTONIUM =
            ITEMS.register("battery_sc_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_POLONIUM =
            ITEMS.register("battery_sc_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_GOLD =
            ITEMS.register("battery_sc_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_LEAD =
            ITEMS.register("battery_sc_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_SC_AMERICIUM =
            ITEMS.register("battery_sc_americium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_POTATO =
            ITEMS.register("battery_potato", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATTERY_POTATOS =
            ITEMS.register("battery_potatos", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEV_BATTERY =
            ITEMS.register("hev_battery", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSION_CORE =
            ITEMS.register("fusion_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSION_CORE_INFINITE =
            ITEMS.register("fusion_core_infinite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENERGY_CORE =
            ITEMS.register("energy_core", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FUSE =
            ITEMS.register("fuse", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REDCOIL_CAPACITOR =
            ITEMS.register("redcoil_capacitor", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EUPHEMIUM_CAPACITOR =
            ITEMS.register("euphemium_capacitor", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCREWDRIVER =
            ITEMS.register("screwdriver", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCREWDRIVER_DESH =
            ITEMS.register("screwdriver_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAND_DRILL =
            ITEMS.register("hand_drill", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAND_DRILL_DESH =
            ITEMS.register("hand_drill_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WRENCH_ARCHINEER =
            ITEMS.register("wrench_archineer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEMISTRY_SET =
            ITEMS.register("chemistry_set", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHEMISTRY_SET_BORON =
            ITEMS.register("chemistry_set_boron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOWTORCH =
            ITEMS.register("blowtorch", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ACETYLENE_TORCH =
            ITEMS.register("acetylene_torch", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOLTGUN =
            ITEMS.register("boltgun", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OVERFUSE =
            ITEMS.register("overfuse", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARC_ELECTRODE =
            ITEMS.register("arc_electrode", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARC_ELECTRODE_BURNT =
            ITEMS.register("arc_electrode_burnt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_MUFFLER =
            ITEMS.register("upgrade_muffler", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_TEMPLATE =
            ITEMS.register("upgrade_template", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_SPEED_1 =
            ITEMS.register("upgrade_speed_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_SPEED_2 =
            ITEMS.register("upgrade_speed_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_SPEED_3 =
            ITEMS.register("upgrade_speed_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_EFFECT_1 =
            ITEMS.register("upgrade_effect_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_EFFECT_2 =
            ITEMS.register("upgrade_effect_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_EFFECT_3 =
            ITEMS.register("upgrade_effect_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_POWER_1 =
            ITEMS.register("upgrade_power_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_POWER_2 =
            ITEMS.register("upgrade_power_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_POWER_3 =
            ITEMS.register("upgrade_power_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_FORTUNE_1 =
            ITEMS.register("upgrade_fortune_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_FORTUNE_2 =
            ITEMS.register("upgrade_fortune_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_FORTUNE_3 =
            ITEMS.register("upgrade_fortune_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_AFTERBURN_1 =
            ITEMS.register("upgrade_afterburn_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_AFTERBURN_2 =
            ITEMS.register("upgrade_afterburn_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_AFTERBURN_3 =
            ITEMS.register("upgrade_afterburn_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_OVERDRIVE_1 =
            ITEMS.register("upgrade_overdrive_1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_OVERDRIVE_2 =
            ITEMS.register("upgrade_overdrive_2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_OVERDRIVE_3 =
            ITEMS.register("upgrade_overdrive_3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_RADIUS =
            ITEMS.register("upgrade_radius", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_HEALTH =
            ITEMS.register("upgrade_health", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_SMELTER =
            ITEMS.register("upgrade_smelter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_SHREDDER =
            ITEMS.register("upgrade_shredder", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_CENTRIFUGE =
            ITEMS.register("upgrade_centrifuge", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_CRYSTALLIZER =
            ITEMS.register("upgrade_crystallizer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_NULLIFIER =
            ITEMS.register("upgrade_nullifier", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_SCREM =
            ITEMS.register("upgrade_screm", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_GC_SPEED =
            ITEMS.register("upgrade_gc_speed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_5G =
            ITEMS.register("upgrade_5g", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_STACK =
            ITEMS.register("upgrade_stack", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UPGRADE_EJECTOR =
            ITEMS.register("upgrade_ejector", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_EUPHEMIUM =
            ITEMS.register("ingot_euphemium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_EUPHEMIUM =
            ITEMS.register("nugget_euphemium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EUPHEMIUM_HELMET =
            ITEMS.register("euphemium_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EUPHEMIUM_PLATE =
            ITEMS.register("euphemium_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EUPHEMIUM_LEGS =
            ITEMS.register("euphemium_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EUPHEMIUM_BOOTS =
            ITEMS.register("euphemium_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> APPLE_EUPHEMIUM =
            ITEMS.register("apple_euphemium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATCH =
            ITEMS.register("watch", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOGGLES =
            ITEMS.register("goggles", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASHGLASSES =
            ITEMS.register("ashglasses", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK =
            ITEMS.register("gas_mask", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_M65 =
            ITEMS.register("gas_mask_m65", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_MONO =
            ITEMS.register("gas_mask_mono", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_MASK_OLDE =
            ITEMS.register("gas_mask_olde", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MASK_RAG =
            ITEMS.register("mask_rag", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MASK_PISS =
            ITEMS.register("mask_piss", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAT =
            ITEMS.register("hat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BETA =
            ITEMS.register("beta", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NO9 =
            ITEMS.register("no9", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> T45_HELMET =
            ITEMS.register("t45_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> T45_PLATE =
            ITEMS.register("t45_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> T45_LEGS =
            ITEMS.register("t45_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> T45_BOOTS =
            ITEMS.register("t45_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEAMSUIT_HELMET =
            ITEMS.register("steamsuit_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEAMSUIT_PLATE =
            ITEMS.register("steamsuit_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEAMSUIT_LEGS =
            ITEMS.register("steamsuit_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEAMSUIT_BOOTS =
            ITEMS.register("steamsuit_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIESELSUIT_HELMET =
            ITEMS.register("dieselsuit_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIESELSUIT_PLATE =
            ITEMS.register("dieselsuit_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIESELSUIT_LEGS =
            ITEMS.register("dieselsuit_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIESELSUIT_BOOTS =
            ITEMS.register("dieselsuit_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHAINSAW =
            ITEMS.register("chainsaw", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_HELMET =
            ITEMS.register("schrabidium_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_PLATE =
            ITEMS.register("schrabidium_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_LEGS =
            ITEMS.register("schrabidium_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_BOOTS =
            ITEMS.register("schrabidium_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_HELMET =
            ITEMS.register("titanium_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_PLATE =
            ITEMS.register("titanium_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_LEGS =
            ITEMS.register("titanium_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_BOOTS =
            ITEMS.register("titanium_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_HELMET =
            ITEMS.register("steel_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_PLATE =
            ITEMS.register("steel_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_LEGS =
            ITEMS.register("steel_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_BOOTS =
            ITEMS.register("steel_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_HELMET =
            ITEMS.register("alloy_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_PLATE =
            ITEMS.register("alloy_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_LEGS =
            ITEMS.register("alloy_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_BOOTS =
            ITEMS.register("alloy_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_HELMET =
            ITEMS.register("cmb_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_PLATE =
            ITEMS.register("cmb_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_LEGS =
            ITEMS.register("cmb_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_BOOTS =
            ITEMS.register("cmb_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAA_PLATE =
            ITEMS.register("paa_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAA_LEGS =
            ITEMS.register("paa_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAA_BOOTS =
            ITEMS.register("paa_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASBESTOS_HELMET =
            ITEMS.register("asbestos_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASBESTOS_PLATE =
            ITEMS.register("asbestos_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASBESTOS_LEGS =
            ITEMS.register("asbestos_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASBESTOS_BOOTS =
            ITEMS.register("asbestos_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SECURITY_HELMET =
            ITEMS.register("security_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SECURITY_PLATE =
            ITEMS.register("security_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SECURITY_LEGS =
            ITEMS.register("security_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SECURITY_BOOTS =
            ITEMS.register("security_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_HELMET =
            ITEMS.register("cobalt_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_PLATE =
            ITEMS.register("cobalt_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_LEGS =
            ITEMS.register("cobalt_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_BOOTS =
            ITEMS.register("cobalt_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_HELMET =
            ITEMS.register("starmetal_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_PLATE =
            ITEMS.register("starmetal_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_LEGS =
            ITEMS.register("starmetal_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_BOOTS =
            ITEMS.register("starmetal_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNT_HELMET =
            ITEMS.register("dnt_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNT_PLATE =
            ITEMS.register("dnt_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNT_LEGS =
            ITEMS.register("dnt_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNT_BOOTS =
            ITEMS.register("dnt_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJR_HELMET =
            ITEMS.register("ajr_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJR_PLATE =
            ITEMS.register("ajr_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJR_LEGS =
            ITEMS.register("ajr_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJR_BOOTS =
            ITEMS.register("ajr_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJRO_HELMET =
            ITEMS.register("ajro_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJRO_PLATE =
            ITEMS.register("ajro_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJRO_LEGS =
            ITEMS.register("ajro_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AJRO_BOOTS =
            ITEMS.register("ajro_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RPA_HELMET =
            ITEMS.register("rpa_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RPA_PLATE =
            ITEMS.register("rpa_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RPA_LEGS =
            ITEMS.register("rpa_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RPA_BOOTS =
            ITEMS.register("rpa_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_HELMET =
            ITEMS.register("bismuth_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_PLATE =
            ITEMS.register("bismuth_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_LEGS =
            ITEMS.register("bismuth_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_BOOTS =
            ITEMS.register("bismuth_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BJ_HELMET =
            ITEMS.register("bj_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BJ_PLATE =
            ITEMS.register("bj_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BJ_PLATE_JETPACK =
            ITEMS.register("bj_plate_jetpack", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BJ_LEGS =
            ITEMS.register("bj_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BJ_BOOTS =
            ITEMS.register("bj_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENVSUIT_HELMET =
            ITEMS.register("envsuit_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENVSUIT_PLATE =
            ITEMS.register("envsuit_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENVSUIT_LEGS =
            ITEMS.register("envsuit_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENVSUIT_BOOTS =
            ITEMS.register("envsuit_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEV_HELMET =
            ITEMS.register("hev_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEV_PLATE =
            ITEMS.register("hev_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEV_LEGS =
            ITEMS.register("hev_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEV_BOOTS =
            ITEMS.register("hev_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FAU_HELMET =
            ITEMS.register("fau_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FAU_PLATE =
            ITEMS.register("fau_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FAU_LEGS =
            ITEMS.register("fau_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FAU_BOOTS =
            ITEMS.register("fau_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNS_HELMET =
            ITEMS.register("dns_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNS_PLATE =
            ITEMS.register("dns_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNS_LEGS =
            ITEMS.register("dns_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNS_BOOTS =
            ITEMS.register("dns_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRENCHMASTER_HELMET =
            ITEMS.register("trenchmaster_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRENCHMASTER_PLATE =
            ITEMS.register("trenchmaster_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRENCHMASTER_LEGS =
            ITEMS.register("trenchmaster_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TRENCHMASTER_BOOTS =
            ITEMS.register("trenchmaster_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ZIRCONIUM_LEGS =
            ITEMS.register("zirconium_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROBES_HELMET =
            ITEMS.register("robes_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROBES_PLATE =
            ITEMS.register("robes_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROBES_LEGS =
            ITEMS.register("robes_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROBES_BOOTS =
            ITEMS.register("robes_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JETPACK_BOOST =
            ITEMS.register("jetpack_boost", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JETPACK_BREAK =
            ITEMS.register("jetpack_break", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JETPACK_FLY =
            ITEMS.register("jetpack_fly", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JETPACK_VECTOR =
            ITEMS.register("jetpack_vector", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WINGS_LIMP =
            ITEMS.register("wings_limp", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WINGS_MURK =
            ITEMS.register("wings_murk", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JACKT =
            ITEMS.register("jackt", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JACKT2 =
            ITEMS.register("jackt2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_SWORD =
            ITEMS.register("schrabidium_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_PICKAXE =
            ITEMS.register("schrabidium_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_AXE =
            ITEMS.register("schrabidium_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_SHOVEL =
            ITEMS.register("schrabidium_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_HOE =
            ITEMS.register("schrabidium_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_SWORD =
            ITEMS.register("titanium_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_PICKAXE =
            ITEMS.register("titanium_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_AXE =
            ITEMS.register("titanium_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_SHOVEL =
            ITEMS.register("titanium_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TITANIUM_HOE =
            ITEMS.register("titanium_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SWORD =
            ITEMS.register("steel_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_PICKAXE =
            ITEMS.register("steel_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_AXE =
            ITEMS.register("steel_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SHOVEL =
            ITEMS.register("steel_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_HOE =
            ITEMS.register("steel_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_SWORD =
            ITEMS.register("alloy_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_PICKAXE =
            ITEMS.register("alloy_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_AXE =
            ITEMS.register("alloy_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_SHOVEL =
            ITEMS.register("alloy_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ALLOY_HOE =
            ITEMS.register("alloy_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_SWORD =
            ITEMS.register("cmb_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_PICKAXE =
            ITEMS.register("cmb_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_AXE =
            ITEMS.register("cmb_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_SHOVEL =
            ITEMS.register("cmb_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CMB_HOE =
            ITEMS.register("cmb_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ELEC_SWORD =
            ITEMS.register("elec_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ELEC_PICKAXE =
            ITEMS.register("elec_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ELEC_AXE =
            ITEMS.register("elec_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ELEC_SHOVEL =
            ITEMS.register("elec_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESH_SWORD =
            ITEMS.register("desh_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESH_PICKAXE =
            ITEMS.register("desh_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESH_AXE =
            ITEMS.register("desh_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESH_SHOVEL =
            ITEMS.register("desh_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DESH_HOE =
            ITEMS.register("desh_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_SWORD =
            ITEMS.register("cobalt_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_PICKAXE =
            ITEMS.register("cobalt_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_AXE =
            ITEMS.register("cobalt_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_SHOVEL =
            ITEMS.register("cobalt_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_HOE =
            ITEMS.register("cobalt_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_DECORATED_SWORD =
            ITEMS.register("cobalt_decorated_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_DECORATED_PICKAXE =
            ITEMS.register("cobalt_decorated_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_DECORATED_AXE =
            ITEMS.register("cobalt_decorated_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_DECORATED_SHOVEL =
            ITEMS.register("cobalt_decorated_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COBALT_DECORATED_HOE =
            ITEMS.register("cobalt_decorated_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_SWORD =
            ITEMS.register("starmetal_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_PICKAXE =
            ITEMS.register("starmetal_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_AXE =
            ITEMS.register("starmetal_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_SHOVEL =
            ITEMS.register("starmetal_shovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STARMETAL_HOE =
            ITEMS.register("starmetal_hoe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SMASHING_HAMMER =
            ITEMS.register("smashing_hammer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CENTRI_STICK =
            ITEMS.register("centri_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRAX =
            ITEMS.register("drax", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRAX_MK2 =
            ITEMS.register("drax_mk2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRAX_MK3 =
            ITEMS.register("drax_mk3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_PICKAXE =
            ITEMS.register("bismuth_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BISMUTH_AXE =
            ITEMS.register("bismuth_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VOLCANIC_PICKAXE =
            ITEMS.register("volcanic_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VOLCANIC_AXE =
            ITEMS.register("volcanic_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLOROPHYTE_PICKAXE =
            ITEMS.register("chlorophyte_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLOROPHYTE_AXE =
            ITEMS.register("chlorophyte_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MESE_PICKAXE =
            ITEMS.register("mese_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MESE_AXE =
            ITEMS.register("mese_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DNT_SWORD =
            ITEMS.register("dnt_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DWARVEN_PICKAXE =
            ITEMS.register("dwarven_pickaxe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD =
            ITEMS.register("meteorite_sword", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_SEARED =
            ITEMS.register("meteorite_sword_seared", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_REFORGED =
            ITEMS.register("meteorite_sword_reforged", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_HARDENED =
            ITEMS.register("meteorite_sword_hardened", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_ALLOYED =
            ITEMS.register("meteorite_sword_alloyed", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_MACHINED =
            ITEMS.register("meteorite_sword_machined", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_TREATED =
            ITEMS.register("meteorite_sword_treated", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_ETCHED =
            ITEMS.register("meteorite_sword_etched", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_BRED =
            ITEMS.register("meteorite_sword_bred", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_IRRADIATED =
            ITEMS.register("meteorite_sword_irradiated", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_FUSED =
            ITEMS.register("meteorite_sword_fused", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEORITE_SWORD_BALEFUL =
            ITEMS.register("meteorite_sword_baleful", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MATCHSTICK =
            ITEMS.register("matchstick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALEFIRE_AND_STEEL =
            ITEMS.register("balefire_and_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MASK_OF_INFAMY =
            ITEMS.register("mask_of_infamy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCHRABIDIUM_HAMMER =
            ITEMS.register("schrabidium_hammer", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHIMMER_SLEDGE =
            ITEMS.register("shimmer_sledge", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHIMMER_AXE =
            ITEMS.register("shimmer_axe", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLE_OPENER =
            ITEMS.register("bottle_opener", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PCH =
            ITEMS.register("pch", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOOD_GAVEL =
            ITEMS.register("wood_gavel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEAD_GAVEL =
            ITEMS.register("lead_gavel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_GAVEL =
            ITEMS.register("diamond_gavel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MESE_GAVEL =
            ITEMS.register("mese_gavel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CROWBAR =
            ITEMS.register("crowbar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WRENCH =
            ITEMS.register("wrench", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WRENCH_FLIPPED =
            ITEMS.register("wrench_flipped", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEMESPOON =
            ITEMS.register("memespoon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_HIT =
            ITEMS.register("multitool_hit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_DIG =
            ITEMS.register("multitool_dig", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_SILK =
            ITEMS.register("multitool_silk", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_EXT =
            ITEMS.register("multitool_ext", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_MINER =
            ITEMS.register("multitool_miner", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_BEAM =
            ITEMS.register("multitool_beam", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_SKY =
            ITEMS.register("multitool_sky", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_MEGA =
            ITEMS.register("multitool_mega", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_JOULE =
            ITEMS.register("multitool_joule", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTITOOL_DECON =
            ITEMS.register("multitool_decon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAW =
            ITEMS.register("saw", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAT =
            ITEMS.register("bat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAT_NAIL =
            ITEMS.register("bat_nail", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOLF_CLUB =
            ITEMS.register("golf_club", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPE_RUSTY =
            ITEMS.register("pipe_rusty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIPE_LEAD =
            ITEMS.register("pipe_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REER_GRAAR =
            ITEMS.register("reer_graar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STOPSIGN =
            ITEMS.register("stopsign", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOPSIGN =
            ITEMS.register("sopsign", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHERNOBYLSIGN =
            ITEMS.register("chernobylsign", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_HORN =
            ITEMS.register("crystal_horn", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_CHARRED =
            ITEMS.register("crystal_charred", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ATTACHMENT_MASK =
            ITEMS.register("attachment_mask", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ATTACHMENT_MASK_MONO =
            ITEMS.register("attachment_mask_mono", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BACK_TESLA =
            ITEMS.register("back_tesla", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SERVO_SET =
            ITEMS.register("servo_set", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SERVO_SET_DESH =
            ITEMS.register("servo_set_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADS_RUBBER =
            ITEMS.register("pads_rubber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADS_SLIME =
            ITEMS.register("pads_slime", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADS_STATIC =
            ITEMS.register("pads_static", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_PAINT =
            ITEMS.register("cladding_paint", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_RUBBER =
            ITEMS.register("cladding_rubber", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_LEAD =
            ITEMS.register("cladding_lead", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_DESH =
            ITEMS.register("cladding_desh", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_GHIORSIUM =
            ITEMS.register("cladding_ghiorsium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_IRON =
            ITEMS.register("cladding_iron", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLADDING_OBSIDIAN =
            ITEMS.register("cladding_obsidian", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_KEVLAR =
            ITEMS.register("insert_kevlar", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_SAPI =
            ITEMS.register("insert_sapi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_ESAPI =
            ITEMS.register("insert_esapi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_XSAPI =
            ITEMS.register("insert_xsapi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_STEEL =
            ITEMS.register("insert_steel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_DU =
            ITEMS.register("insert_du", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_POLONIUM =
            ITEMS.register("insert_polonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_GHIORSIUM =
            ITEMS.register("insert_ghiorsium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_ERA =
            ITEMS.register("insert_era", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_YHARONITE =
            ITEMS.register("insert_yharonite", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INSERT_DOXIUM =
            ITEMS.register("insert_doxium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_POLISH =
            ITEMS.register("armor_polish", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BANDAID =
            ITEMS.register("bandaid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SERUM =
            ITEMS.register("serum", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> QUARTZ_PLUTONIUM =
            ITEMS.register("quartz_plutonium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MORNING_GLORY =
            ITEMS.register("morning_glory", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LODESTONE =
            ITEMS.register("lodestone", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HORSESHOE_MAGNET =
            ITEMS.register("horseshoe_magnet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INDUSTRIAL_MAGNET =
            ITEMS.register("industrial_magnet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATHWATER =
            ITEMS.register("bathwater", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BATHWATER_MK2 =
            ITEMS.register("bathwater_mk2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPIDER_MILK =
            ITEMS.register("spider_milk", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INK =
            ITEMS.register("ink", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEART_PIECE =
            ITEMS.register("heart_piece", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEART_CONTAINER =
            ITEMS.register("heart_container", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEART_BOOSTER =
            ITEMS.register("heart_booster", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEART_FAB =
            ITEMS.register("heart_fab", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_DIAMOND =
            ITEMS.register("black_diamond", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WD40 =
            ITEMS.register("wd40", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRUMPY =
            ITEMS.register("scrumpy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WILD_P =
            ITEMS.register("wild_p", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FABSOLS_VODKA =
            ITEMS.register("fabsols_vodka", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SHACKLES =
            ITEMS.register("shackles", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INJECTOR_5HTP =
            ITEMS.register("injector_5htp", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INJECTOR_KNIFE =
            ITEMS.register("injector_knife", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEDAL_LIQUIDATOR =
            ITEMS.register("medal_liquidator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOTTLED_CLOUD =
            ITEMS.register("bottled_cloud", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PROTECTION_CHARM =
            ITEMS.register("protection_charm", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEOR_CHARM =
            ITEMS.register("meteor_charm", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NEUTRINO_LENS =
            ITEMS.register("neutrino_lens", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GAS_TESTER =
            ITEMS.register("gas_tester", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEFUSER_GOLD =
            ITEMS.register("defuser_gold", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BALLISTIC_GAUNTLET =
            ITEMS.register("ballistic_gauntlet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NIGHT_VISION =
            ITEMS.register("night_vision", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CARD_AOS =
            ITEMS.register("card_aos", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CARD_QOS =
            ITEMS.register("card_qos", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AUSTRALIUM_III =
            ITEMS.register("australium_iii", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_BATTERY =
            ITEMS.register("armor_battery", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_BATTERY_MK2 =
            ITEMS.register("armor_battery_mk2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_BATTERY_MK3 =
            ITEMS.register("armor_battery_mk3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_HELMET =
            ITEMS.register("hazmat_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PLATE =
            ITEMS.register("hazmat_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_LEGS =
            ITEMS.register("hazmat_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_BOOTS =
            ITEMS.register("hazmat_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_HELMET_RED =
            ITEMS.register("hazmat_helmet_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PLATE_RED =
            ITEMS.register("hazmat_plate_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_LEGS_RED =
            ITEMS.register("hazmat_legs_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_BOOTS_RED =
            ITEMS.register("hazmat_boots_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_HELMET_GREY =
            ITEMS.register("hazmat_helmet_grey", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PLATE_GREY =
            ITEMS.register("hazmat_plate_grey", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_LEGS_GREY =
            ITEMS.register("hazmat_legs_grey", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_BOOTS_GREY =
            ITEMS.register("hazmat_boots_grey", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIQUIDATOR_HELMET =
            ITEMS.register("liquidator_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIQUIDATOR_PLATE =
            ITEMS.register("liquidator_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIQUIDATOR_LEGS =
            ITEMS.register("liquidator_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIQUIDATOR_BOOTS =
            ITEMS.register("liquidator_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PAA_HELMET =
            ITEMS.register("hazmat_paa_helmet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PAA_PLATE =
            ITEMS.register("hazmat_paa_plate", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PAA_LEGS =
            ITEMS.register("hazmat_paa_legs", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_PAA_BOOTS =
            ITEMS.register("hazmat_paa_boots", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WAND =
            ITEMS.register("wand", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WAND_S =
            ITEMS.register("wand_s", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WAND_D =
            ITEMS.register("wand_d", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRUCTURE_SINGLE =
            ITEMS.register("structure_single", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRUCTURE_SOLID =
            ITEMS.register("structure_solid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRUCTURE_PATTERN =
            ITEMS.register("structure_pattern", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRUCTURE_RANDOMIZED =
            ITEMS.register("structure_randomized", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRUCTURE_RANDOMLY =
            ITEMS.register("structure_randomly", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STRUCTURE_CUSTOMMACHINE =
            ITEMS.register("structure_custommachine", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROD_OF_DISCORD =
            ITEMS.register("rod_of_discord", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAPE_RADIATION =
            ITEMS.register("cape_radiation", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAPE_GASMASK =
            ITEMS.register("cape_gasmask", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAPE_SCHRABIDIUM =
            ITEMS.register("cape_schrabidium", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAPE_HIDDEN =
            ITEMS.register("cape_hidden", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUKE_STARTER_KIT =
            ITEMS.register("nuke_starter_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUKE_ADVANCED_KIT =
            ITEMS.register("nuke_advanced_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUKE_COMMERCIALLY_KIT =
            ITEMS.register("nuke_commercially_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUKE_ELECTRIC_KIT =
            ITEMS.register("nuke_electric_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GADGET_KIT =
            ITEMS.register("gadget_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOY_KIT =
            ITEMS.register("boy_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAN_KIT =
            ITEMS.register("man_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIKE_KIT =
            ITEMS.register("mike_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TSAR_KIT =
            ITEMS.register("tsar_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MULTI_KIT =
            ITEMS.register("multi_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUSTOM_KIT =
            ITEMS.register("custom_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRENADE_KIT =
            ITEMS.register("grenade_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLEIJA_KIT =
            ITEMS.register("fleija_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PROTOTYPE_KIT =
            ITEMS.register("prototype_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MISSILE_KIT =
            ITEMS.register("missile_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> T45_KIT =
            ITEMS.register("t45_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EUPHEMIUM_KIT =
            ITEMS.register("euphemium_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOLINIUM_KIT =
            ITEMS.register("solinium_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_KIT =
            ITEMS.register("hazmat_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_RED_KIT =
            ITEMS.register("hazmat_red_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAZMAT_GREY_KIT =
            ITEMS.register("hazmat_grey_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KIT_CUSTOM =
            ITEMS.register("kit_custom", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KIT_TOOLBOX_EMPTY =
            ITEMS.register("kit_toolbox_empty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KIT_TOOLBOX =
            ITEMS.register("kit_toolbox", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOOT_10 =
            ITEMS.register("loot_10", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOOT_15 =
            ITEMS.register("loot_15", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOOT_MISC =
            ITEMS.register("loot_misc", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMMO_CONTAINER =
            ITEMS.register("ammo_container", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IGNITER =
            ITEMS.register("igniter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DETONATOR =
            ITEMS.register("detonator", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DETONATOR_MULTI =
            ITEMS.register("detonator_multi", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DETONATOR_LASER =
            ITEMS.register("detonator_laser", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DETONATOR_DEADMAN =
            ITEMS.register("detonator_deadman", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DETONATOR_DE =
            ITEMS.register("detonator_de", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOMB_CALLER =
            ITEMS.register("bomb_caller", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METEOR_REMOTE =
            ITEMS.register("meteor_remote", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ANCHOR_REMOTE =
            ITEMS.register("anchor_remote", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REMOTE =
            ITEMS.register("remote", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TURRET_CHIP =
            ITEMS.register("turret_chip", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPAWN_CHOPPER =
            ITEMS.register("spawn_chopper", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPAWN_WORM =
            ITEMS.register("spawn_worm", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPAWN_UFO =
            ITEMS.register("spawn_ufo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPAWN_DUCK =
            ITEMS.register("spawn_duck", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KEY =
            ITEMS.register("key", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KEY_RED =
            ITEMS.register("key_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KEY_RED_CRACKED =
            ITEMS.register("key_red_cracked", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KEY_KIT =
            ITEMS.register("key_kit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KEY_FAKE =
            ITEMS.register("key_fake", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PIN =
            ITEMS.register("pin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADLOCK_RUSTY =
            ITEMS.register("padlock_rusty", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADLOCK =
            ITEMS.register("padlock", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADLOCK_REINFORCED =
            ITEMS.register("padlock_reinforced", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PADLOCK_UNBREAKABLE =
            ITEMS.register("padlock_unbreakable", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MECH_KEY =
            ITEMS.register("mech_key", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUCKET_MUD =
            ITEMS.register("bucket_mud", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUCKET_ACID =
            ITEMS.register("bucket_acid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUCKET_TOXIC =
            ITEMS.register("bucket_toxic", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUCKET_SCHRABIDIC_ACID =
            ITEMS.register("bucket_schrabidic_acid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BUCKET_SULFURIC_ACID =
            ITEMS.register("bucket_sulfuric_acid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DOOR_METAL =
            ITEMS.register("door_metal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DOOR_OFFICE =
            ITEMS.register("door_office", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DOOR_BUNKER =
            ITEMS.register("door_bunker", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DOOR_RED =
            ITEMS.register("door_red", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SLIDING_BLAST_DOOR_SKIN =
            ITEMS.register("sliding_blast_door_skin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RECORD_LC =
            ITEMS.register("record_lc", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RECORD_SS =
            ITEMS.register("record_ss", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RECORD_VC =
            ITEMS.register("record_vc", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RECORD_GLASS =
            ITEMS.register("record_glass", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOOK_GUIDE =
            ITEMS.register("book_guide", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOOK_LORE =
            ITEMS.register("book_lore", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HOLOTAPE_IMAGE =
            ITEMS.register("holotape_image", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HOLOTAPE_DAMAGED =
            ITEMS.register("holotape_damaged", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLAY_TABLET =
            ITEMS.register("clay_tablet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POLAROID =
            ITEMS.register("polaroid", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLITCH =
            ITEMS.register("glitch", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LETTER =
            ITEMS.register("letter", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOOK_SECRET =
            ITEMS.register("book_secret", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOOK_OF_ =
            ITEMS.register("book_of_", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PAGE_OF_ =
            ITEMS.register("page_of_", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOOK_LEMEGETON =
            ITEMS.register("book_lemegeton", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BURNT_BARK =
            ITEMS.register("burnt_bark", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE1 =
            ITEMS.register("chlorine1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE2 =
            ITEMS.register("chlorine2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE3 =
            ITEMS.register("chlorine3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE4 =
            ITEMS.register("chlorine4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE5 =
            ITEMS.register("chlorine5", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE6 =
            ITEMS.register("chlorine6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE7 =
            ITEMS.register("chlorine7", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHLORINE8 =
            ITEMS.register("chlorine8", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC1 =
            ITEMS.register("pc1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC2 =
            ITEMS.register("pc2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC3 =
            ITEMS.register("pc3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC4 =
            ITEMS.register("pc4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC5 =
            ITEMS.register("pc5", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC6 =
            ITEMS.register("pc6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC7 =
            ITEMS.register("pc7", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PC8 =
            ITEMS.register("pc8", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD1 =
            ITEMS.register("cloud1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD2 =
            ITEMS.register("cloud2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD3 =
            ITEMS.register("cloud3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD4 =
            ITEMS.register("cloud4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD5 =
            ITEMS.register("cloud5", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD6 =
            ITEMS.register("cloud6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD7 =
            ITEMS.register("cloud7", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOUD8 =
            ITEMS.register("cloud8", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE1 =
            ITEMS.register("orange1", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE2 =
            ITEMS.register("orange2", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE3 =
            ITEMS.register("orange3", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE4 =
            ITEMS.register("orange4", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE5 =
            ITEMS.register("orange5", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE6 =
            ITEMS.register("orange6", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE7 =
            ITEMS.register("orange7", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE8 =
            ITEMS.register("orange8", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NOTHING =
            ITEMS.register("nothing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ACHIEVEMENT_ICON =
            ITEMS.register("achievement_icon", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOB_METALWORKS =
            ITEMS.register("bob_metalworks", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOB_ASSEMBLY =
            ITEMS.register("bob_assembly", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOB_CHEMISTRY =
            ITEMS.register("bob_chemistry", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOB_OIL =
            ITEMS.register("bob_oil", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOB_NUCLEAR =
            ITEMS.register("bob_nuclear", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MYSTERYSHOVEL =
            ITEMS.register("mysteryshovel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEMORY =
            ITEMS.register("memory", () -> new Item(new Item.Properties()));

    public enum ItemCategory {
        PARTS,
        CONTROL,
        TEMPLATE,
        BLOCK,
        MACHINE,
        NUKE,
        MISSILE,
        WEAPON,
        CONSUMABLE
    }

    private static final Map<RegistryObject<?>, ItemCategory> ITEM_CATEGORIES = new HashMap<>();

    private static <T extends Item> RegistryObject<T> registerWithCategory(
            String name, Supplier<T> supplier, ItemCategory category) {
        RegistryObject<T> regObj = ITEMS.register(name, supplier);
        ITEM_CATEGORIES.put(regObj, category);
        return regObj;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
