package com.miniverse.hbm.saveddata;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * AuxSavedData is used to store auxiliary data persistently in the world.
 * It holds a list of DataPair objects that store a key (as a String) and a value (as an int).
 * This implementation uses the newer SavedData API available in Forge 1.20.1.
 */
public class AuxSavedData extends SavedData {

    public List<DataPair> data = new ArrayList<>();
    public static final String DATA_ID = "hbmauxdata";

    // Default constructor. Called when creating new saved data.
    public AuxSavedData() {
        super();
        markDirty();
    }

    /**
     * DataPair represents a key/value pair.
     */
    static class DataPair {
        String key = "";
        int value;

        public DataPair() { }

        public DataPair(String s, int i) {
            key = s;
            value = i;
        }

        void readFromNBT(CompoundTag nbt, int i) {
            this.key = nbt.getString("aux_key_" + i);
            this.value = nbt.getInt("aux_val_" + i);
        }

        void writeToNBT(CompoundTag nbt, int i) {
            nbt.putString("aux_key_" + i, key);
            nbt.putInt("aux_val_" + i, value);
        }
    }

    /**
     * Factory method used by the SavedDataStorage to load an instance from NBT.
     */
    public static AuxSavedData load(CompoundTag nbt) {
        AuxSavedData data = new AuxSavedData();
        int count = nbt.getInt("dCount");
        for (int i = 0; i < count; i++) {
            DataPair struct = new DataPair();
            struct.readFromNBT(nbt, i);
            data.data.add(struct);
        }
        return data;
    }

    /**
     * Saves this data to a CompoundTag.
     */
    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putInt("dCount", data.size());
        for (int i = 0; i < data.size(); i++) {
            data.get(i).writeToNBT(nbt, i);
        }
        return nbt;
    }

    /**
     * Retrieves the AuxSavedData instance from the given Level.
     * It uses the new SavedDataStorage API.
     */
    public static AuxSavedData getData(Level level) {
        return level.getDataStorage().computeIfAbsent(AuxSavedData::load, AuxSavedData::new, DATA_ID);
    }

    /**
     * Sets the "thunder" value in the saved data.
     * If the "thunder" key is not present, it is added; otherwise, its value is updated.
     */
    public static void setThunder(Level level, int dura) {
        AuxSavedData data = getData(level);
        DataPair thunder = null;
        for (DataPair pair : data.data) {
            if (pair.key.equals("thunder")) {
                thunder = pair;
                break;
            }
        }
        if (thunder == null) {
            data.data.add(new DataPair("thunder", dura));
        } else {
            thunder.value = dura;
        }
        data.markDirty();
    }

    /**
     * Retrieves the "thunder" value from the saved data.
     * Returns 0 if the key is not found.
     */
    public static int getThunder(Level level) {
        AuxSavedData data = getData(level);
        for (DataPair pair : data.data) {
            if (pair.key.equals("thunder")) {
                return pair.value;
            }
        }
        return 0;
    }
}
