package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;

/**
 * Interface for blocks that can connect to fluid networks.
 */
public interface IFluidConnectorBlock {

    /**
     * Determines whether this block can connect to a given face for fluid transfer.
     *
     * @param type The fluid type.
     * @param world The block getter (read-only access to world data).
     * @param pos The position of the block.
     * @param dir The direction going outwards from the block.
     * @return True if the block can connect on this side, false otherwise.
     */
    boolean canConnect(FluidType type, BlockGetter world, BlockPos pos, Direction dir);
}
