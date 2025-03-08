package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;

/**
 * Represents a transceiver, a device capable of both sending and receiving fluids.
 *
 * <p>Definition of transceiver:</p>
 * <ul>
 *     <li>A transmitter and receiver housed together in a single unit and having some circuits in common.</li>
 *     <li>A combined radio transmitter and receiver.</li>
 *     <li>A device that performs transmitting and receiving functions, especially if using common components.</li>
 * </ul>
 *
 * <p>Only supports one tank per type (for input and output separately).</p>
 *
 * @author hbm
 */
public interface IFluidStandardTransceiver extends IFluidUser {

    /**
     * Retrieves the fluid tanks used for sending fluids.
     *
     * @return An array of fluid tanks that can send fluid.
     */
    FluidTank[] getSendingTanks();

    /**
     * Retrieves the fluid tanks used for receiving fluids.
     *
     * @return An array of fluid tanks that can receive fluid.
     */
    FluidTank[] getReceivingTanks();

    /**
     * Gets the total fluid available for sending from this transceiver.
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
     * Removes fluid from the transceiver when transferring it to another location.
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
     * Gets the demand for a specific fluid type and pressure.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
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

    /**
     * Transfers fluid into the transceiver's receiving tanks.
     *
     * @param type The fluid type being transferred.
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
}
