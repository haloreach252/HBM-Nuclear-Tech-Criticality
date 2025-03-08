package com.miniverse.hbm.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface ILaserable {

    /**
     * Adds energy to a block or tile entity when hit by a laser.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param energy The amount of energy to add.
     * @param dir The direction from which the laser is hitting.
     */
    void addEnergy(Level level, BlockPos pos, long energy, Direction dir);
}
