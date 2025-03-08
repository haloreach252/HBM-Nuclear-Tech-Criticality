package com.miniverse.hbm.api.tile;

import net.minecraft.nbt.CompoundTag;

/**
 * Info providers for ENERGY CONTROL
 *
 * Original EC implementation references:
 * https://github.com/Zuxelus/Energy-Control/blob/1.7.10/src/main/java/com/zuxelus/energycontrol/crossmod/CrossHBM.java
 * https://github.com/Zuxelus/Energy-Control/blob/1.7.10/src/main/java/com/zuxelus/energycontrol/items/cards/ItemCardHBM.java
 * https://github.com/Zuxelus/Energy-Control/blob/1.7.10/src/main/java/com/zuxelus/energycontrol/utils/DataHelper.java
 *
 * (keys from DataHelper.java and CrossHBM.java)
 */
public interface IInfoProviderEC {

    /**
     * Adds any custom data that isn't covered by the standard energy and fluid implementations.
     *
     * @param data the CompoundTag to store additional information.
     */
    void provideExtraInfo(CompoundTag data);
}
