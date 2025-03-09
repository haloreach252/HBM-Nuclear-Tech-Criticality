package com.miniverse.hbm.saveddata;

import java.util.Arrays;

import net.minecraft.nbt.CompoundTag;

public class BroadcastSaveStructure {

    public int broadcastID;
    public BroadcastType broadcastType;
    public int posX;
    public int posZ;

    public BroadcastSaveStructure() { }

    public BroadcastSaveStructure(int id, BroadcastType type) {
        broadcastID = id;
        broadcastType = type;
    }

    public enum BroadcastType {
        DEMO;

        public static BroadcastType getEnum(int i) {
            if(i < BroadcastType.values().length)
                return BroadcastType.values()[i];
            else
                return BroadcastType.DEMO;
        }

        public int getID() {
            return Arrays.asList(BroadcastType.values()).indexOf(this);
        }
    }

    public void readFromNBT(CompoundTag nbt, int index) {
        broadcastID = nbt.getInt("bc_" + index + "_id");
        broadcastType = BroadcastType.getEnum(nbt.getInt("bc_" + index + "_type"));
        posX = nbt.getInt("bc_" + index + "_x");
        posZ = nbt.getInt("bc_" + index + "_z");
    }

    public void writeToNBT(CompoundTag nbt, int index) {
        nbt.putInt("bc_" + index + "_id", broadcastID);
        nbt.putInt("bc_" + index + "_type", broadcastType.getID());
        nbt.putInt("bc_" + index + "_x", posX);
        nbt.putInt("bc_" + index + "_z", posZ);
    }
}
