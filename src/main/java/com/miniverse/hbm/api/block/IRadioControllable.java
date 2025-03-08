package com.miniverse.hbm.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IRadioControllable {

    /**
     * Retrieves the available radio control variables for the given block.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @return An array of variable names that can be controlled.
     */
    String[] getVariables(Level level, BlockPos pos);

    /**
     * Receives a signal from a radio controller and processes it.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param channel The radio channel on which the signal was sent.
     * @param signal The signal value being received.
     */
    void receiveSignal(Level level, BlockPos pos, String channel, String signal);
}
