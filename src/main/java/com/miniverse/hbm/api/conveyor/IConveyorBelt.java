package com.miniverse.hbm.api.conveyor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface IConveyorBelt {

    /**
     * Determines if the item should stay on the conveyor.
     *
     * @param level The world (level) where the conveyor is located.
     * @param pos The position of the conveyor.
     * @param itemPos The current position of the item on the conveyor.
     * @return True if the item should stay on the conveyor, false if it should drop off.
     */
    boolean canItemStay(Level level, BlockPos pos, Vec3 itemPos);

    /**
     * Calculates the next travel position of an item on the conveyor.
     *
     * @param level The world (level) where the conveyor is located.
     * @param pos The position of the conveyor.
     * @param itemPos The current position of the item on the conveyor.
     * @param speed The movement speed of the item.
     * @return The new position of the item after movement.
     */
    Vec3 getTravelLocation(Level level, BlockPos pos, Vec3 itemPos, double speed);

    /**
     * Finds the closest snapping position for an item on the conveyor.
     *
     * @param level The world (level) where the conveyor is located.
     * @param pos The position of the conveyor.
     * @param itemPos The current position of the item on the conveyor.
     * @return The closest valid position where the item should snap.
     */
    Vec3 getClosestSnappingPosition(Level level, BlockPos pos, Vec3 itemPos);
}
