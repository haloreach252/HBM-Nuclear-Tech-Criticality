package com.miniverse.hbm.saveddata.satellites;

import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.saveddata.SatelliteSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Abstract Satellite class.
 * It registers different satellite types and maps satellite items to their respective Satellite classes.
 * The class also defines actions for satellite interfaces and coordinates, and handles orbiting behavior.
 */
public abstract class Satellite {

    public static final List<Class<? extends Satellite>> satellites = new ArrayList<>();
    public static final HashMap<Item, Class<? extends Satellite>> itemToClass = new HashMap<>();

    public enum InterfaceActions {
        HAS_MAP,        // Lets the interface display loaded chunks
        CAN_CLICK,      // Enables onClick events
        SHOW_COORDS,    // Enables coordinates as a mouse tooltip
        HAS_RADAR,      // Lets the interface display loaded entities
        HAS_ORES        // Like HAS_MAP but only shows ores
    }

    public enum CoordActions {
        HAS_Y           // Enables the Y-coordinate field which is disabled by default
    }

    public enum Interfaces {
        NONE,           // Does not interact with any satellite interface (i.e. asteroid miners)
        SAT_PANEL,      // Allows interaction with the sat interface panel (for graphical applications)
        SAT_COORD       // Allows interaction with the sat coord remote (for teleportation or other coordinate-related actions)
    }

    public List<InterfaceActions> ifaceAcs = new ArrayList<>();
    public List<CoordActions> coordAcs = new ArrayList<>();
    public Interfaces satIface = Interfaces.NONE;

    /**
     * Registers all satellite types.
     */
    public static void register() {
        registerSatellite(SatelliteMapper.class, ModItems.sat_mapper);
        registerSatellite(SatelliteScanner.class, ModItems.sat_scanner);
        registerSatellite(SatelliteRadar.class, ModItems.sat_radar);
        registerSatellite(SatelliteLaser.class, ModItems.sat_laser);
        registerSatellite(SatelliteResonator.class, ModItems.sat_resonator);
        registerSatellite(SatelliteRelay.class, ModItems.sat_foeq);
        registerSatellite(SatelliteMiner.class, ModItems.sat_miner);
        registerSatellite(SatelliteLunarMiner.class, ModItems.sat_lunar_miner);
        registerSatellite(SatelliteHorizons.class, ModItems.sat_gerald);
    }

    /**
     * Registers a satellite by mapping its class to a satellite item.
     * @param sat Satellite class.
     * @param item Satellite item (which will be placed in a rocket).
     */
    public static void registerSatellite(Class<? extends Satellite> sat, Item item) {
        if (!itemToClass.containsKey(item) && !itemToClass.containsValue(sat)) {
