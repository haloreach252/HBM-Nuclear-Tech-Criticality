package com.miniverse.hbm.saveddata;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * BroadcastSavedData is used to persistently store broadcast information.
 * It holds a list of BroadcastSaveStructure objects, tracks the count of broadcasts,
 * and provides helper methods to check and retrieve broadcasts by ID.
 * This implementation uses the new SavedData API introduced in Forge 1.20.1.
 */
public class BroadcastSavedData extends SavedData {

    public int bcCount;
    public List<BroadcastSaveStructure> broadcasts = new ArrayList<>();
    public static final String DATA_ID = "broadcasts";

    // Default constructor for new data.
    public BroadcastSavedData() {
        markDirty();
    }

    /**
     * Factory method used to load BroadcastSavedData from NBT.
     * @param nbt The CompoundTag containing the saved data.
     * @return A new instance of BroadcastSavedData populated from the NBT.
     */
    public static BroadcastSavedData load(CompoundTag nbt) {
        BroadcastSavedData data = new BroadcastSavedData();
        data.bcCount = nbt.getInt("bcCount");
        for (int i = 0; i < data.bcCount; i++) {
            BroadcastSaveStructure struct = new BroadcastSaveStructure();
            struct.readFromNBT(nbt, i);
            data.broadcasts.add(struct);
        }
        return data;
    }

    /**
     * Checks whether a broadcast with the specified id already exists.
     * @param id The broadcast id to check.
     * @return true if the id is taken, false otherwise.
     */
    public boolean isIdTaken(int id) {
        return getBroadcastFromId(id) != null;
    }

    /**
     * Retrieves the BroadcastSaveStructure corresponding to the given id.
     * @param id The broadcast id.
     * @return The BroadcastSaveStructure if found, or null otherwise.
     */
    public BroadcastSaveStructure getBroadcastFromId(int id) {
        for (BroadcastSaveStructure bc : broadcasts) {
            if (bc.broadcastID == id)
                return bc;
        }
        return null;
    }

    /**
     * Saves this data to a CompoundTag.
     * @param nbt The CompoundTag to write to.
     * @return The same CompoundTag with this data written.
     */
    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putInt("bcCount", broadcasts.size());
        for (int i = 0; i < broadcasts.size(); i++) {
            broadcasts.get(i).writeToNBT(nbt, i);
        }
        return nbt;
    }
}
