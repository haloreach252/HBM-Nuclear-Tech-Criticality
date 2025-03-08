package com.miniverse.hbm.api.conveyor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface IEnterableBlock {

    /**
     * Determines if a moving item can enter from the given side. When this happens,
     * the {@link IConveyorItem} will call {@code onEnter} and despawn.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param dir The direction from which the item is attempting to enter.
     * @param entity The item trying to enter the block.
     * @return True if the item can enter, false otherwise.
     */
    boolean canItemEnter(Level level, BlockPos pos, Direction dir, IConveyorItem entity);

    /**
     * Called when an item enters the block.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param dir The direction from which the item is entering.
     * @param entity The item entering the block.
     */
    void onItemEnter(Level level, BlockPos pos, Direction dir, IConveyorItem entity);

    /**
     * Determines if a conveyor package can enter from the given side.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param dir The direction from which the package is attempting to enter.
     * @param entity The conveyor package trying to enter the block.
     * @return True if the package can enter, false otherwise.
     */
    boolean canPackageEnter(Level level, BlockPos pos, Direction dir, IConveyorPackage entity);

    /**
     * Called when a conveyor package enters the block.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param dir The direction from which the package is entering.
     * @param entity The conveyor package entering the block.
     */
    void onPackageEnter(Level level, BlockPos pos, Direction dir, IConveyorPackage entity);
}
