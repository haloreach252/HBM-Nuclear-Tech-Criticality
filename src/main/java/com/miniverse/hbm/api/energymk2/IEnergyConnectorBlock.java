package com.miniverse.hbm.api.energymk2;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;

/**
 * Interface for all blocks that should visually connect to cables without having an IEnergyConnector tile entity.
 * This is meant for BLOCKS.
 * Used primarily for rendering purposes.
 *
 * @author hbm
 */
public interface IEnergyConnectorBlock {

    /**
     * Determines if this block can visually connect to cables.
     * This method is used for rendering purposes and does not affect energy transfer.
     *
     * @param world The block getter (read-only access to world data).
     * @param pos The position of the block.
     * @param dir The direction in which the connection is checked.
     * @return True if the block should visually connect, false otherwise.
     */
    boolean canConnect(BlockGetter world, BlockPos pos, Direction dir);
}
