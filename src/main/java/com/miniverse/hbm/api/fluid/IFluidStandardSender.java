package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;

/**
 * Provides a default implementation for common fluid senders.
 * This interface handles a single output tank of the same type.
 * Uses standard {@link FluidTank} instances, which store fluids as int32.
 *
 * Note: This is NOT part of the official API and should not be exposed as such.
 *
 * @author hbm
 */
public interface IFluidStandardSender extends IFluidUser {

    /**
     * Retrieves the fluid tanks used for sending fluids.
     *
     * @return An array of fluid tanks that can send fluid.
     */
    FluidTank[] getSendingTanks();

    /**
     * Gets the total fluid available for sending from this sender.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @return The amount of fluid available for sending.
     */
    @Override
    default long getTotalFluidForSend(FluidType type, int pressure) {
        for (FluidTank tank : getSendingTanks()) {
            if (tank.getTankType() == type && tank.getPressure() == pressure) {
                return tank.getFill();
            }
        }
        return 0;
    }

    /**
     * Removes fluid from the sender when transferring it to another location.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @param amount The amount of fluid to remove.
     */
    @Override
    default void removeFluidForTransfer(FluidType type, int pressure, long amount) {
        for (FluidTank tank : getSendingTanks()) {
            if (tank.getTankType() == type && tank.getPressure() == pressure) {
                tank.setFill(tank.getFill() - (int) amount);
                return;
            }
        }
    }

    /**
     * Default transfer method that simply returns the requested fluid amount.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @param fluid The amount of fluid to transfer.
     * @return The same fluid amount, as this sender does not alter it.
     */
    @Override
    default long transferFluid(FluidType type, int pressure, long fluid) {
        return fluid;
    }

    /**
     * Gets the demand for fluid in this sender.
     * Since this is a fluid sender, it has no demand.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @return Always returns 0, as senders do not request fluid.
     */
    @Override
    default long getDemand(FluidType type, int pressure) {
        return 0;
    }
}
