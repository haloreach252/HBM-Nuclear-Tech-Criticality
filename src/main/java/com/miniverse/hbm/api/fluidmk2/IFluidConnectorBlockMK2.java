package com.miniverse.hbm.api.fluidmk2;

import com.miniverse.hbm.inventory.fluid.FluidType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;

/**
 * Interface for blocks that can connect to fluid networks.
 */
public interface IFluidConnectorBlockMK2 {

    /**
     * Determines if the block can connect to a fluid network.
     *
     * @param type The fluid type.
     * @param level The level (world) reader.
     * @param pos The position of the block.
     * @param dir The direction of the connection.
     * @return True if the block can connect, false otherwise.
     */
    boolean canConnect(FluidType type, LevelReader level, BlockPos pos, Direction dir);
}
