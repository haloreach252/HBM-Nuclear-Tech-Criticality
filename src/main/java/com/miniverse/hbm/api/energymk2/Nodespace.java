package com.miniverse.hbm.energymk2;

import com.miniverse.hbm.interfaces.NotableComments;
import com.miniverse.hbm.uninos.GenNode;
import com.miniverse.hbm.uninos.UniNodespace;
import com.miniverse.hbm.uninos.networkproviders.PowerNetProvider;
import com.miniverse.hbm.util.DirPos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * The dead fucking corpse of nodespace MK1.
 * A fantastic proof of concept, but ultimately it was killed for being just not that versatile.
 * This class is mostly just a compatibility husk that should allow uninodespace to slide into the mod
 * with as much lubrication as it deserves.
 *
 * @author hbm
 */
public class Nodespace {

    public static final PowerNetProvider THE_POWER_PROVIDER = new PowerNetProvider();

    /**
     * Gets the PowerNode at a given position.
     *
     * @param level The world (level) where the node is located.
     * @param pos The position of the node.
     * @return The PowerNode at the given position, or null if none exists.
     */
    @Deprecated
    public static PowerNode getNode(Level level, BlockPos pos) {
        return (PowerNode) UniNodespace.getNode(level, pos, THE_POWER_PROVIDER);
    }

    /**
     * Creates a new PowerNode in the given world.
     *
     * @param level The world (level) where the node is created.
     * @param node The PowerNode instance to be created.
     */
    @Deprecated
    public static void createNode(Level level, PowerNode node) {
        UniNodespace.createNode(level, node);
    }

    /**
     * Destroys the PowerNode at the given position.
     *
     * @param level The world (level) where the node is destroyed.
     * @param pos The position of the node to be destroyed.
     */
    @Deprecated
    public static void destroyNode(Level level, BlockPos pos) {
        UniNodespace.destroyNode(level, pos, THE_POWER_PROVIDER);
    }

    @NotableComments
    public static class PowerNode extends GenNode<PowerNetMK2> {

        /**
         * Okay so here's the deal: The code has shit idiot brain fungus. I don't know why. I re-tested every part involved several times.
         * I don't know why. But for some reason, during neighbor checks, on certain arbitrary fucking places, the joining operation just fails.
         * Disallowing nodes to create new networks fixed the problem completely, which is hardly surprising since they wouldn't be able to make
         * a new net anyway and they will re-check neighbors until a net is found, so the solution is tautological in nature. So I tried limiting
         * creation of new networks. Didn't work. So what's there left to do? Hand out a mark to any node that has changed networks, and let those
         * recently modified nodes do another re-check. This creates a second layer of redundant operations, and in theory doubles (in practice,
         * it might be an extra 20% due to break-off section sizes) the amount of CPU time needed for re-building the networks after joining or
         * breaking, but it seems to allow those parts to connect back to their neighbor nets as they are supposed to. I am not proud of this solution,
         * this issue shouldn't exist to begin with and I am going fucking insane but it is what it is.
         */

        public PowerNode(BlockPos... positions) {
            super(THE_POWER_PROVIDER, positions);
            this.positions = positions;
        }

        @Override
        public PowerNode setConnections(DirPos... connections) {
            super.setConnections(connections);
            return this;
        }
    }
}
