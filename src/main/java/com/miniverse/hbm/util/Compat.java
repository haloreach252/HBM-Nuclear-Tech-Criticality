package com.miniverse.hbm.util;

import com.miniverse.hbm.config.GeneralConfig;
import com.miniverse.hbm.handler.HazmatRegistry;
import com.miniverse.hbm.hazard.HazardRegistry;
import com.miniverse.hbm.inventory.FluidContainer;
import com.miniverse.hbm.inventory.FluidContainerRegistry;
import com.miniverse.hbm.inventory.fluid.Fluids;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.core.Registry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.eventbus.api.EventBus;
import net.minecraftforge.eventbus.api.IEventListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class provides a set of compatibility and integration helper methods
 * for cross-mod support. Its responsibilities include:
 *
 * - Loading items and blocks by resource name.
 * - Checking if particular mods are loaded.
 * - Registering hazmat items and fluid container compatibility.
 * - Removing unwanted Railcraft event listeners via reflection.
 * - Providing a reflective hook into a biome helper (for Endless IDs).
 * - Safely retrieving tile entities (BlockEntities) without forcing chunk loads.
 *
 * In the port, legacy Forge 1.7.10 classes and methods have been updated to their modern equivalents.
 */
public class Compat {

    // Mod IDs for compatibility checks
    public static final String MOD_GT6 = "gregtech";
    public static final String MOD_GCC = "GalacticraftCore";
    public static final String MOD_AR = "advancedrocketry";
    public static final String MOD_EF = "etfuturum";
    public static final String MOD_REC = "ReactorCraft";
    public static final String MOD_TIC = "TConstruct";
    public static final String MOD_RC = "railcraft";
    public static final String MOD_TC = "tc";
    public static final String MOD_EIDS = "endlessids";
    public static final String MOD_ANG = "angelica";

    /**
     * Attempts to load an Item from the registry by its resource location.
     *
     * @param domain the mod namespace
     * @param name the item name
     * @return the Item if present; otherwise null.
     */
    public static Item tryLoadItem(String domain, String name) {
        ResourceLocation loc = new ResourceLocation(domain, name);
        return Registry.ITEM.containsKey(loc) ? Registry.ITEM.get(loc) : null;
    }

    /**
     * Attempts to load a Block from the registry by its resource location.
     *
     * @param domain the mod namespace
     * @param name the block name
     * @return the Block if present; otherwise null.
     */
    public static Block tryLoadBlock(String domain, String name) {
        ResourceLocation loc = new ResourceLocation(domain, name);
        return Registry.BLOCK.containsKey(loc) ? Registry.BLOCK.get(loc) : null;
    }

    // Helper for constructing the registry key string (kept for legacy reference)
    private static String getReg(String domain, String name) {
        return domain + ":" + name;
    }

    /**
     * Checks whether a mod is loaded.
     *
     * @param modid the mod identifier
     * @return true if the mod is loaded; false otherwise.
     */
    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    /**
     * Enumeration of isotopes with their associated radiation values.
     * The radiation values are taken from HazardRegistry.
     */
    public static enum ReikaIsotope {
        C14(HazardRegistry.gen_10K),
        U235(HazardRegistry.u235),
        U238(HazardRegistry.u238),
        Pu239(HazardRegistry.pu239),
        Pu244(HazardRegistry.gen_100M),
        Th232(HazardRegistry.th232),
        Rn222(HazardRegistry.gen_10D),
        Ra226(HazardRegistry.ra226),
        Sr90(HazardRegistry.gen_10Y),
        Po210(HazardRegistry.po210),
        Cs134(HazardRegistry.gen_1Y),
        Xe135(HazardRegistry.xe135),
        Zr93(HazardRegistry.gen_1M),
        Mo99(HazardRegistry.gen_10D),
        Cs137(HazardRegistry.cs137),
        Tc99(HazardRegistry.tc99),
        I131(HazardRegistry.i131),
        Pm147(HazardRegistry.gen_1Y),
        I129(HazardRegistry.gen_10M),
        Sm151(HazardRegistry.gen_100Y),
        Ru106(HazardRegistry.gen_1Y),
        Kr85(HazardRegistry.kr85),
        Pd107(HazardRegistry.gen_10M),
        Se79(HazardRegistry.gen_100K),
        Gd155(HazardRegistry.gen_1Y),
        Sb125(HazardRegistry.gen_1Y),
        Sn126(HazardRegistry.gen_100K),
        Xe136(0),
        I135(HazardRegistry.gen_H),
        Xe131(HazardRegistry.gen_10D),
        Ru103(HazardRegistry.gen_S),
        Pm149(HazardRegistry.gen_10D),
        Rh105(HazardRegistry.gen_H);

        private final float rads;

        private ReikaIsotope(float rads) {
            this.rads = rads;
        }

        public float getRad() {
            return this.rads;
        }
    }

    /**
     * Extracts a list of ItemStacks from a given ME drive item.
     * The method reads a short ("it") value from the drive's NBT and then retrieves
     * each contained ItemStack using tags like "#i" and "@" + i for count.
     *
     * @param meDrive the ItemStack representing the ME drive.
     * @return a List of ItemStacks extracted from the drive.
     */
    public static List<ItemStack> scrapeItemFromME(ItemStack meDrive) {
        List<ItemStack> stacks = new ArrayList<>();
        try {
            if (meDrive != null && meDrive.hasTag()) {
                CompoundTag nbt = meDrive.getTag();
                int types = nbt.getShort("it"); // ITEM_TYPE_TAG

                for (int i = 0; i < types; i++) {
                    Tag stackTag = nbt.get("#" + i);
                    if (stackTag instanceof CompoundTag) {
                        CompoundTag compound = (CompoundTag) stackTag;
                        // Load an ItemStack from its CompoundTag.
                        ItemStack stack = ItemStack.of(compound);
                        int count = nbt.getInt("@" + i);
                        stack.setCount(count);
                        stacks.add(stack);
                    }
                }
            }
        } catch(Exception ex) {
            // Exception is swallowed – consider logging if needed.
        }
        return stacks;
    }

    /**
     * Registers hazmat items from various mods with their radiation resistance values.
     */
    public static void registerCompatHazmat() {
        double helmet = 0.2D;
        double chest = 0.4D;
        double legs = 0.3D;
        double boots = 0.1D;

        double p90 = 1.0D; // 90%
        double p99 = 2D;    // 99%

        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.radiation.head", p90 * helmet);
        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.radiation.chest", p90 * chest);
        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.radiation.legs", p90 * legs);
        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.radiation.boots", p90 * boots);

        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.universal.head", p99 * helmet);
        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.universal.chest", p99 * chest);
        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.universal.legs", p99 * legs);
        tryRegisterHazmat(MOD_GT6, "gt.armor.hazmat.universal.boots", p99 * boots);

        tryRegisterHazmat(MOD_REC, "reactorcraft_item_hazhelmet", p99 * helmet);
        tryRegisterHazmat(MOD_REC, "reactorcraft_item_hazchest", p99 * chest);
        tryRegisterHazmat(MOD_REC, "reactorcraft_item_hazlegs", p99 * legs);
        tryRegisterHazmat(MOD_REC, "reactorcraft_item_hazboots", p99 * boots);

        tryRegisterHazmat(MOD_EF, "netherite_helmet", p90 * helmet);
        tryRegisterHazmat(MOD_EF, "netherite_chestplate", p90 * chest);
        tryRegisterHazmat(MOD_EF, "netherite_leggings", p90 * legs);
        tryRegisterHazmat(MOD_EF, "netherite_boots", p90 * boots);
    }

    // Helper method that loads an item and, if found, registers it with HazmatRegistry.
    private static void tryRegisterHazmat(String mod, String name, double resistance) {
        Item item = tryLoadItem(mod, name);
        if (item != null) {
            HazmatRegistry.registerHazmat(item, resistance);
        }
    }

    /**
     * Registers fluid container compatibility for a mod.
     * Checks if the mod is loaded and fluid container compatibility is enabled,
     * then registers a container mapping for diesel.
     */
    public static void registerCompatFluidContainers() {
        if (isModLoaded(MOD_TC) && GeneralConfig.enableFluidContainerCompat) {
            Item canister = tryLoadItem(MOD_TC, "emptyCanister");
            Item diesel = tryLoadItem(MOD_TC, "diesel");
            if (diesel != null && canister != null) {
                FluidContainerRegistry.registerContainer(
                        new FluidContainer(new ItemStack(diesel), new ItemStack(canister), Fluids.DIESEL, 1000)
                );
            }
        }
    }

    /**
     * Handles Railcraft compatibility by attempting to remove an unwanted event listener.
     * This method reflects into the Forge event bus (MinecraftForge.EVENT_BUS) to locate
     * a listener with a class name "mods.railcraft.common.blocks.hidden.TrailTicker" and unregister it.
     *
     * Note: Internal structures of the event bus may change in future Forge versions.
     */
    public static void handleRailcraftNonsense() {
        if (!isModLoaded(MOD_RC))
            return;

        HBMNuclearTechCriticality.LOGGER.info("#######################################################");
        HBMNuclearTechCriticality.LOGGER.info("| Railcraft detected, deploying anti-nonsense measures...");

        try {
            EventBus eventBus = MinecraftForge.EVENT_BUS;
            // Reflectively access the private "listeners" field in the event bus.
            Field listenersField = ObfuscationReflectionHelper.findField(eventBus.getClass(), "listeners");
            listenersField.setAccessible(true);
            Object listenersObj = listenersField.get(eventBus);
            if (listenersObj instanceof ConcurrentHashMap) {
                ConcurrentHashMap<?, ?> listeners = (ConcurrentHashMap<?, ?>) listenersObj;
                Object nonsense = null;
                for (Object key : listeners.keySet()) {
                    if (key.getClass().getName().equals("mods.railcraft.common.blocks.hidden.TrailTicker")) {
                        HBMNuclearTechCriticality.LOGGER.info("| Found TrailTicker!");
                        nonsense = key;
                        break;
                    }
                }
                if (nonsense != null) {
                    eventBus.unregister(nonsense);
                    HBMNuclearTechCriticality.LOGGER.info("| Successfully removed Railcraft nonsense.");
                }
            }
        } catch (Exception x) {
            HBMNuclearTechCriticality.LOGGER.error("| Tried to remove Railcraft block but failed due to " + x.getMessage());
        }
        HBMNuclearTechCriticality.LOGGER.info("#######################################################");
    }

    /**
     * Returns the class for the EndlessIDs biome hook, if available.
     *
     * @return the Class object or null if not found.
     */
    public static Class<?> getChunkBiomeHook() {
        try {
            return Class.forName("com.falsepattern.endlessids.mixin.helpers.ChunkBiomeHook");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    // Cached reflective Method for retrieving a short[] from a biome hook.
    public static Method getBiomeShortArray;

    /**
     * Uses reflection to retrieve the "getBiomeShortArray" method from the biome hook.
     *
     * @return the Method if available; otherwise null.
     */
    public static Method getBiomeShortArray() {
        if (getBiomeShortArray != null)
            return getBiomeShortArray;
        try {
            Method m = getChunkBiomeHook().getDeclaredMethod("getBiomeShortArray");
            getBiomeShortArray = m;
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Invokes the reflected "getBiomeShortArray" method on the given instance.
     *
     * @param instance the object instance on which to invoke the method.
     * @return the short array result or null if invocation fails.
     */
    public static short[] getBiomeShortArray(Object instance) {
        Method m = getBiomeShortArray();
        if (m != null) {
            try {
                return (short[]) m.invoke(instance);
            } catch (Exception e) {
                // Ignore exceptions and return null.
            }
        }
        return null;
    }

    /**
     * Safely retrieves a BlockEntity (tile entity) from a Level without forcing a chunk load.
     *
     * @param level the current Level
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     * @return the BlockEntity if the chunk is loaded; otherwise null.
     */
    public static BlockEntity getTileStandard(Level level, int x, int y, int z) {
        int chunkX = x >> 4;
        int chunkZ = z >> 4;
        // Use getChunk with 'create' flag set to false so that the chunk is not loaded if not already present.
        if (level.getChunk(chunkX, chunkZ, false) == null) {
            return null;
        }
        return level.getBlockEntity(new BlockPos(x, y, z));
    }
}
