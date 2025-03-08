package com.miniverse.hbm.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DirPos extends BlockPos {

    protected Direction dir;

    public DirPos(int x, int y, int z, Direction dir) {
        super(x, y, z);
        this.dir = dir;
    }

    public DirPos(BlockEntity blockEntity, Direction dir) {
        super(blockEntity.getBlockPos());
        this.dir = dir;
    }

    public DirPos(double x, double y, double z, Direction dir) {
        super((int) x, (int) y, (int) z);
        this.dir = dir;
    }

    /**
     * Rotates this position and its associated direction.
     *
     * @param rotation The rotation type.
     * @return The rotated DirPos.
     */
    @Override
    public DirPos rotate(Rotation rotation) {
        switch (rotation) {
            case NONE:
            default:
                return this;
            case CLOCKWISE_90:
                return new DirPos(-this.getZ(), this.getY(), this.getX(), rotateDirection90Clockwise(this.dir));
            case CLOCKWISE_180:
                return new DirPos(-this.getX(), this.getY(), -this.getZ(), this.dir.getOpposite());
            case COUNTERCLOCKWISE_90:
                return new DirPos(this.getZ(), this.getY(), -this.getX(), rotateDirection90CounterClockwise(this.dir));
        }
    }

    /**
     * Handles 90-degree clockwise rotation.
     * @param dir The original direction.
     * @return The rotated direction.
     */
    private Direction rotateDirection90Clockwise(Direction dir) {
        switch (dir) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            case WEST: return Direction.NORTH;
            default: return dir; // UP & DOWN remain unchanged
        }
    }

    /**
     * Handles 90-degree counterclockwise rotation.
     * @param dir The original direction.
     * @return The rotated direction.
     */
    private Direction rotateDirection90CounterClockwise(Direction dir) {
        switch (dir) {
            case NORTH: return Direction.WEST;
            case WEST: return Direction.SOUTH;
            case SOUTH: return Direction.EAST;
            case EAST: return Direction.NORTH;
            default: return dir; // UP & DOWN remain unchanged
        }
    }

    public Direction getDir() {
        return this.dir;
    }
}
