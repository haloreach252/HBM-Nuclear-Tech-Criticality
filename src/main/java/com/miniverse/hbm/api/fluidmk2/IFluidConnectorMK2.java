package com.miniverse.hbm.api.fluidmk2;

import com.miniverse.hbm.inventory.fluid.FluidType;
import net.minecraft.core.Direction;

/**
 * Interface for objects that can connect to a fluid network.
 */
public interface IFluidConnectorMK2 {

    /**
     * Determines whether the given side can be connected to a fluid network.
     *
     * @param type The fluid type.
     * @param dir The direction of the connection.
     * @return True if the side can be connected, false otherwise.
     */
    default boolean canConnect(FluidType type, Direction dir) {
        return dir != Direction.UNKNOWN;
    }
}
