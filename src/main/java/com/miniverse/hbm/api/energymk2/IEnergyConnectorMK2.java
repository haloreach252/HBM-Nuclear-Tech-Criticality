package com.miniverse.hbm.api.energymk2;

import net.minecraft.core.Direction;

public interface IEnergyConnectorMK2 {

    /**
     * Determines whether the given side can be connected to.
     * The direction refers to the side of this block, not the connecting block doing the check.
     *
     * @param dir The direction being checked.
     * @return True if the block can connect on this side, false otherwise.
     */
    default boolean canConnect(Direction dir) {
        return dir != null;
    }
}
