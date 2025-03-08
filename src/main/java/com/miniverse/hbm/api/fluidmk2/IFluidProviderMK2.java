package com.miniverse.hbm.api.fluidmk2;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.uninos.IGenProvider;
import com.miniverse.hbm.uninos.networkproviders.FluidNetProvider;

/**
 * Interface for fluid providers in the modern fluid network system.
 */
public interface IFluidProviderMK2 extends IGenProvider<FluidNetProvider> {

    /**
     * Consumes the specified amount of fluid from the provider.
     *
     * @param type The type of fluid to consume.
     * @param pressure The pressure of the fluid.
     * @param amount The amount of fluid to consume.
     */
    void useUpFluid(FluidType type, int pressure, long amount);

    /**
     * Returns the speed at which the provider can provide fluid.
     *
     * @param type The type of fluid.
     * @param pressure The pressure of the fluid.
     * @return The provider speed for the specified fluid type and pressure.
     */
    long getProviderSpeed(FluidType type, int pressure);
}
