package com.miniverse.hbm.calc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Location {
    public int x;
    public int y;
    public int z;
    public Level world;

    public Location(Level world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location add(int xa, int ya, int za) {
        return new Location(world, x + xa, y + ya, z + za);
    }

    public Location add(Direction dir) {
        return add(dir.getStepX(), dir.getStepY(), dir.getStepZ());
    }

    public BlockEntity getBlockEntity() {
        return world.getBlockEntity(new BlockPos(x, y, z));
    }
}
