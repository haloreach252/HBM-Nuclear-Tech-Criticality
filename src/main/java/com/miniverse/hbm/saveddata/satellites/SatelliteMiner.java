package com.miniverse.hbm.saveddata.satellites;

import com.miniverse.hbm.itempool.ItemPoolsSatellite;
import com.miniverse.hbm.util.WeightedRandomObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;

import java.util.HashMap;

/**
 * SatelliteMiner is a type of Satellite that delivers loot.
 * It maintains a static mapping (cargo) associating a satellite miner class with a cargo key.
 */
public class SatelliteMiner extends Satellite {

    /**
     * Mapping of SatelliteMiner classes to their cargo keys.
     * This key indicates which loot pool the satellite will deliver.
     */
    private static final HashMap<Class<? extends SatelliteMiner>, String> CARGO = new HashMap<>();

    public long lastOp;

    public SatelliteMiner() {
        this.satIface = Interfaces.NONE;
    }

    @Override
    public void writeToNBT(CompoundTag nbt) {
        nbt.putLong("lastOp", lastOp);
    }

    @Override
    public void readFromNBT(CompoundTag nbt) {
        lastOp = nbt.getLong("lastOp");
    }

    /**
     * Registers the cargo key for a given SatelliteMiner subclass.
     * @param minerSatelliteClass the class of the satellite miner
     * @param cargo the cargo key representing the loot that will be delivered
     */
    public static void registerCargo(Class<? extends SatelliteMiner> minerSatelliteClass, String cargo) {
        CARGO.put(minerSatelliteClass, cargo);
    }

    /**
     * Gets the cargo key for this satellite miner instance.
     * @return the cargo key (loot pool) assigned to this satellite miner.
     */
    public String getCargo() {
        return CARGO.get(getClass());
    }

    /**
     * Retrieves the cargo key for the given satellite item.
     * @param satelliteItem the satellite item
     * @return the cargo key for the mining satellite, or null if the item is not associated with a mining satellite.
     */
    public static String getCargoForItem(Item satelliteItem) {
        Class<? extends Satellite> satelliteClass = itemToClass.getOrDefault(satelliteItem, null);
        return satelliteClass != null ? CARGO.getOrDefault(satelliteClass, null) : null;
    }

    static {
        registerCargo(SatelliteMiner.class, ItemPoolsSatellite.POOL_SAT_MINER);
    }
}
