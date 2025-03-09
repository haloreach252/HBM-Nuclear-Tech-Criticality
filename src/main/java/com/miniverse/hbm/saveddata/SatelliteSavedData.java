package com.miniverse.hbm.saveddata;

import com.miniverse.hbm.saveddata.satellites.Satellite;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * SatelliteSavedData stores a mapping of satellite frequencies to Satellite instances.
 * It persists the satellites data using the new SavedData API in Forge 1.20.1.
 */
public class SatelliteSavedData extends SavedData {

    public final HashMap<Integer, Satellite> sats = new HashMap<>();
    public static final String DATA_ID = "satellites";

    public SatelliteSavedData() {
        markDirty();
    }

    /**
     * Checks if a given frequency is already taken by a Satellite.
     * @param freq the frequency to check.
     * @return true if a Satellite exists with this frequency.
     */
    public boolean isFreqTaken(int freq) {
        return getSatFromFreq(freq) != null;
    }

    /**
     * Retrieves the Satellite associated with the given frequency.
     * @param freq the frequency key.
     * @return the Satellite if present, null otherwise.
     */
    public Satellite getSatFromFreq(int freq) {
        return sats.get(freq);
    }

    /**
     * Saves the satellites data to the provided CompoundTag.
     * @param nbt the CompoundTag to write to.
     * @return the updated CompoundTag.
     */
    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putInt("satCount", sats.size());
        int i = 0;
        for (Entry<Integer, Satellite> entry : sats.entrySet()) {
            CompoundTag data = new CompoundTag();
            entry.getValue().writeToNBT(data);
            nbt.putInt("sat_id_" + i, entry.getValue().getID());
            nbt.put("sat_data_" + i, data);
            nbt.putInt("sat_freq_" + i, entry.getKey());
            i++;
        }
        return nbt;
    }

    /**
     * Factory method to load SatelliteSavedData from NBT.
     * @param nbt the CompoundTag containing the saved data.
     * @return a new instance of SatelliteSavedData populated from the NBT.
     */
    public static SatelliteSavedData load(CompoundTag nbt) {
        SatelliteSavedData data = new SatelliteSavedData();
        int satCount = nbt.getInt("satCount");
        for (int i = 0; i < satCount; i++) {
            int id = nbt.getInt("sat_id_" + i);
            Satellite sat = Satellite.create(id);
            CompoundTag satData = nbt.getCompound("sat_data_" + i);
            sat.readFromNBT(satData);
            int freq = nbt.getInt("sat_freq_" + i);
            data.sats.put(freq, sat);
        }
        return data;
    }

    /**
     * Retrieves the SatelliteSavedData instance from the given Level.
     * @param level the world level.
     * @return the SatelliteSavedData instance.
     */
    public static SatelliteSavedData getData(Level level) {
        return level.getDataStorage().computeIfAbsent(SatelliteSavedData::load, SatelliteSavedData::new, DATA_ID);
    }
}
