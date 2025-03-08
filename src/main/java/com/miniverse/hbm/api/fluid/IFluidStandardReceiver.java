package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;

/**
 * Provides a default implementation for common fluid receivers.
 * This interface handles a single input tank of the same type.
 * Uses standard {@link FluidTank} instances, which store fluids as int32.
 *
 * Note: This is NOT part of the official API and should not be exposed as such.
 *
 * @author hbm
 */
public interface IFluidStandardReceiver extends IFluidUser {

    /**
     * Transfers fluid into the receiver's tanks.
     *
     * @param type The type of fluid being transferred.
     * @param pressure The pressure level of the fluid.
     * @param amount The amount of fluid to transfer.
     * @return The remaining fluid that couldn't be added.
     */
    @Override
    default long transferFluid(FluidType type, int pressure, long amount) {
        for (FluidTank tank : getReceivingTanks()) {
            if (tank.getTankType() == type && tank.getPressure() == pressure) {
                tank.setFill(tank.getFill() + (int) amount);

                if (tank.getFill() > tank.getMaxFill()) {
                    long overshoot = tank.getFill() - tank.getMaxFill();
                    tank.setFill(tank.getMaxFill());
                    return overshoot;
                }
                return 0;
            }
        }
        return amount;
    }

    /**
     * Retrieves the fluid tanks used by this receiver.
     *
     * @return An array of fluid tanks that can receive fluid.
     */
    FluidTank[] getReceivingTanks();

    /**
     * Gets the total demand for a specific fluid type and pressure.
     *
     * @param type The type of fluid.
     * @param pressure The pressure level of the fluid.
     * @return The amount of fluid that can still be received.
     */
    @Override
    default long getDemand(FluidType type, int pressure) {
        for (FluidTank tank : getReceivingTanks()) {
            if (tank.getTankType() == type && tank.getPressure() == pressure) {
                return tank.getMaxFill() - tank.getFill();
            }
        }
        return 0;
    }
}
