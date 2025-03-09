package com.miniverse.hbm.uninos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.miniverse.hbm.util.Tuple.Pair;
import com.miniverse.hbm.util.fauxpointtwelve.BlockPos;
import com.miniverse.hbm.util.fauxpointtwelve.DirPos;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;

public class UniNodespace {

    // Map of server worlds to their node-space data.
    public static HashMap<Level, UniNodeWorld> worlds = new HashMap<>();
    // Set of currently active node networks.
    public static Set<NodeNet> activeNodeNets = new HashSet<>();

    /**
     * Retrieves the node at the specified coordinates and network provider type.
     */
    public static GenNode getNode(Level world, int x, int y, int z, INetworkProvider type) {
        UniNodeWorld nodeWorld = worlds.get(world);
        if (nodeWorld != null)
            return nodeWorld.nodes.get(new Pair<>(new BlockPos(x, y, z), type));
        return null;
    }

    /**
     * Creates and registers a new node in the nodespace.
     */
    public static void createNode(Level world, GenNode node) {
        UniNodeWorld nodeWorld = worlds.get(world);
        if (nodeWorld == null) {
            nodeWorld = new UniNodeWorld();
            worlds.put(world, nodeWorld);
        }
        nodeWorld.pushNode(node);
    }

    /**
     * Destroys (removes) a node at the given position for the specified network provider.
     */
    public static void destroyNode(Level world, int x, int y, int z, INetworkProvider type) {
        GenNode node = getNode(world, x, y, z, type);
        if (node != null) {
            worlds.get(world).popNode(node);
        }
    }

    /**
     * Iterates through all server worlds, checking each node for connection updates.
     */
    public static void updateNodespace() {
        // Iterate over all loaded server worlds.
        for (Level world : MinecraftServer.getServer().getAllLevels()) {
            UniNodeWorld nodeWorld = worlds.get(world);
            if (nodeWorld == null)
                continue;

            for (Entry<Pair<BlockPos, INetworkProvider>, GenNode> entry : nodeWorld.nodes.entrySet()) {
                GenNode node = entry.getValue();
                INetworkProvider provider = entry.getKey().getValue();
                if (!node.hasValidNet() || node.recentlyChanged) {
                    checkNodeConnection(world, node, provider);
                    node.recentlyChanged = false;
                }
            }
        }

        updateNetworks();
    }

    /**
     * Updates all active node networks.
     */
    private static void updateNetworks() {
        for (NodeNet net : activeNodeNets)
            net.resetTrackers(); // Reset must be done before other updates.
        for (NodeNet net : activeNodeNets)
            net.update();
    }

    /**
     * Checks the connection of a node: for each connection point, finds neighbor nodes
     * and joins networks as needed.
     */
    private static void checkNodeConnection(Level world, GenNode node, INetworkProvider provider) {
        for (DirPos con : node.connections) {
            // Get the neighbor node at the connection point.
            GenNode conNode = getNode(world, con.getX(), con.getY(), con.getZ(), provider);
            if (conNode != null) {
                // If both nodes already share the same valid network, skip.
                if (conNode.hasValidNet() && conNode.net == node.net)
                    continue;
                if (checkConnection(conNode, con, false)) {
                    connectToNode(node, conNode);
                }
            }
        }
        // If the node lacks a valid network, create one.
        if (node.net == null || !node.net.isValid())
            provider.provideNetwork().joinLink(node);
    }

    /**
     * Checks if the given node can be connected based on a connection point.
     * If skipSideCheck is true, the directional check is ignored.
     */
    public static boolean checkConnection(GenNode connectsTo, DirPos connectFrom, boolean skipSideCheck) {
        for (DirPos revCon : connectsTo.connections) {
            if (revCon.getX() - revCon.getDir().offsetX == connectFrom.getX() &&
                    revCon.getY() - revCon.getDir().offsetY == connectFrom.getY() &&
                    revCon.getZ() - revCon.getDir().offsetZ == connectFrom.getZ() &&
                    (revCon.getDir() == connectFrom.getDir().getOpposite() || skipSideCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Connects two nodes that belong to different networks (or where one has no network).
     */
    private static void connectToNode(GenNode origin, GenNode connection) {
        if (origin.hasValidNet() && connection.hasValidNet()) { // Both nodes have networks, but they differ.
            if (origin.net.links.size() > connection.net.links.size()) {
                origin.net.joinNetworks(connection.net);
            } else {
                connection.net.joinNetworks(origin.net);
            }
        } else if (!origin.hasValidNet() && connection.hasValidNet()) { // Origin lacks a network.
            connection.net.joinLink(origin);
        } else if (origin.hasValidNet() && !connection.hasValidNet()) { // Connection lacks a network.
            origin.net.joinLink(connection);
        }
    }

    /**
     * A per-world container for nodes.
     */
    public static class UniNodeWorld {
        // Mapping of a (BlockPos, INetworkProvider) pair to its GenNode.
        public HashMap<Pair<BlockPos, INetworkProvider>, GenNode> nodes = new HashMap<>();

        /**
         * Adds a node at all of its positions to the nodespace.
         */
        public void pushNode(GenNode node) {
            for (BlockPos pos : node.positions) {
                nodes.put(new Pair<>(pos, node.networkProvider), node);
            }
        }

        /**
         * Removes the specified node from all positions in the nodespace.
         */
        public void popNode(GenNode node) {
            if (node.net != null)
                node.net.destroy();
            for (BlockPos pos : node.positions) {
                nodes.remove(new Pair<>(pos, node.networkProvider));
            }
            node.expired = true;
        }
    }
}
