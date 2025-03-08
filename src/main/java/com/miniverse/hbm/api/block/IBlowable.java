package com.miniverse.hbm.api.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface IBlowable {

    /**
     * Called server-side when a fan blows on an IBlowable in range every tick.
     *
     * @param level The world (level) where the interaction occurs.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @param dir The direction from which the fan is blowing.
     * @param dist The distance from the fan.
     */
    void applyFan(Level level, int x, int y, int z, Direction dir, int dist);
}
