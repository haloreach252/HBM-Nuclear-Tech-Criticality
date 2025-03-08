package com.miniverse.hbm.api.fluidmk2;

import com.miniverse.hbm.uninos.GenNode;
import com.miniverse.hbm.uninos.INetworkProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

/**
 * Represents a node in the fluid network, managing connections and fluid flow.
 */
public class FluidNode extends GenNode<FluidNetMK2> {

    public FluidNode(INetworkProvider<FluidNetMK2> provider, BlockPos... positions) {
        super(provider, positions);
    }

    @Override
    public FluidNode setConnections(BlockPos... connections) {
        super.setConnections(connections);
        return this;
    }
}
