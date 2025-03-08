package com.miniverse.hbm.api.energymk2;

import com.miniverse.hbm.util.DirPos;
import com.miniverse.hbm.energymk2.Nodespace.PowerNode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IEnergyConductorMK2 extends IEnergyConnectorMK2 {

    /**
     * Creates a power node for this conductor.
     *
     * @return A new PowerNode instance with configured connections.
     */
    default PowerNode createNode() {
        BlockEntity tile = (BlockEntity) this;
        BlockPos pos = tile.getBlockPos();

        return new PowerNode(pos).setConnections(
                new DirPos(pos.getX() + 1, pos.getY(), pos.getZ(), Direction.EAST),
                new DirPos(pos.getX() - 1, pos.getY(), pos.getZ(), Direction.WEST),
                new DirPos(pos.getX(), pos.getY() + 1, pos.getZ(), Direction.UP),
                new DirPos(pos.getX(), pos.getY() - 1, pos.getZ(), Direction.DOWN),
                new DirPos(pos.getX(), pos.getY(), pos.getZ() + 1, Direction.SOUTH),
                new DirPos(pos.getX(), pos.getY(), pos.getZ() - 1, Direction.NORTH)
        );
    }
}
