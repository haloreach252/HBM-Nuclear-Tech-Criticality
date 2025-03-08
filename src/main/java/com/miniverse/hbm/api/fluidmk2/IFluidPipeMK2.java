package com.miniverse.hbm.api.fluidmk2;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.util.DirPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Interface for blocks that act as fluid pipes.
 */
public interface IFluidPipeMK2 {

    /**
     * Creates a FluidNode for this pipe with the appropriate fluid type and connections.
     *
     * @param type The fluid type for the node.
     * @return A newly created FluidNode instance.
     */
    default FluidNode createNode(FluidType type) {
        BlockEntity tile = (BlockEntity) this;
        BlockPos pos = tile.getBlockPos();

        return new FluidNode(type.getNetworkProvider(), pos).setConnections(
                new DirPos(pos.getX() + 1, pos.getY(), pos.getZ(), Direction.EAST),
                new DirPos(pos.getX() - 1, pos.getY(), pos.getZ(), Direction.WEST),
                new DirPos(pos.getX(), pos.getY() + 1, pos.getZ(), Direction.UP),
                new DirPos(pos.getX(), pos.getY() - 1, pos.getZ(), Direction.DOWN),
                new DirPos(pos.getX(), pos.getY(), pos.getZ() + 1, Direction.SOUTH),
                new DirPos(pos.getX(), pos.getY(), pos.getZ() - 1, Direction.NORTH)
        );
    }
}
