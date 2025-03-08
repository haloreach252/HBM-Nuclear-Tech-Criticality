package com.miniverse.hbm.api.fluidmk2;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.uninos.IGenReceiver;
import com.miniverse.hbm.uninos.networkproviders.FluidNetProvider;

/**
 * Interface for fluid receivers in the modern fluid network system.
 */
public interface IFluidReceiverMK2 extends IGenReceiver<FluidNetProvider> {

    /**
     * Transfers fluid of the specified type and pressure to the receiver.
     *
     * @param type The type of fluid to transfer.
     * @param pressure The pressure of the fluid.
     * @param amount The amount of fluid to transfer.
     * @return The amount of fluid that could not be received.
     */
    long transferFluid(FluidType type, int pressure, long amount);

    /**
     * Returns the speed at which the receiver can accept fluid.
     *
     * @param type The type of fluid.
     * @param pressure The pressure of the fluid.
     * @return The receiver speed for the specified fluid type and pressure.
     */
    long getReceiverSpeed(FluidType type, int pressure);
}
