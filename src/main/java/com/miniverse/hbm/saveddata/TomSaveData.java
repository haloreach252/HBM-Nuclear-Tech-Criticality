package com.miniverse.hbm.saveddata;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * TomSaveData stores impact-related data persistently for a world.
 * It tracks three fields: dust, fire, and a boolean flag impact.
 * This implementation uses the new SavedData API from Forge 1.20.1.
 */
public class TomSaveData extends SavedData {

    public static final String KEY = "impactData";
    public float dust;
    public float fire;
    public boolean impact;

    private static TomSaveData lastCachedUnsafe = null;

    /**
     * Retrieves the TomSaveData instance for the given level.
     * Uses the new data storage system; caching is maintained via a static field.
     *
     * @param level the world level
     * @return the TomSaveData instance for that level
     */
    public static TomSaveData forWorld(Level level) {
        TomSaveData result = level.getDataStorage().computeIfAbsent(TomSaveData::load, TomSaveData::new, KEY);
        lastCachedUnsafe = result;
        return result;
    }

    /**
     * Returns the last cached TomSaveData instance or null if none is cached.
     * This is useful when no Level instance is available.
     *
     * @return the last cached TomSaveData instance, or null
     */
    public static TomSaveData getLastCachedOrNull() {
        return lastCachedUnsafe;
    }

    /**
     * Resets the cached TomSaveData.
     */
    public static void resetLastCached() {
        lastCachedUnsafe = null;
    }

    /**
     * Default constructor for new TomSaveData.
     * Called when no saved data exists.
     */
    public TomSaveData() {
        // Mark the data as dirty whenever it's created/modified.
        markDirty();
    }

    /**
     * Loads an instance of TomSaveData from the given CompoundTag.
     *
     * @param compound the CompoundTag containing the saved data
     * @return a new instance of TomSaveData populated with the saved values
     */
    public static TomSaveData load(CompoundTag compound) {
        TomSaveData data = new TomSaveData();
        data.dust = compound.getFloat("dust");
        data.fire = compound.getFloat("fire");
        data.impact = compound.getBoolean("impact");
        return data;
    }

    /**
     * Saves this TomSaveData instance to a CompoundTag.
     *
     * @param compound the CompoundTag to write to
     * @return the CompoundTag with the saved data
     */
    @Override
    public CompoundTag save(CompoundTag compound) {
        compound.putFloat("dust", dust);
        compound.putFloat("fire", fire);
        compound.putBoolean("impact", impact);
        return compound;
    }
}
