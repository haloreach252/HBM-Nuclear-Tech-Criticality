package com.miniverse.hbm.uninos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public abstract class NodeNet<R extends IGenReceiver, P extends IGenProvider, L extends GenNode> {

    /** Global random for operations such as random leftover distribution */
    public static Random rand = new Random();

    public boolean valid = true;
    public Set<L> links = new HashSet<>();
    public HashMap<R, Long> receiverEntries = new HashMap<>();
    public HashMap<P, Long> providerEntries = new HashMap<>();

    public NodeNet() {
        UniNodespace.activeNodeNets.add(this);
    }

    /// SUBSCRIBER HANDLING ///
    public boolean isSubscribed(R receiver) {
        return this.receiverEntries.containsKey(receiver);
    }

    public void addReceiver(R receiver) {
        this.receiverEntries.put(receiver, System.currentTimeMillis());
    }

    public void removeReceiver(R receiver) {
        this.receiverEntries.remove(receiver);
    }

    /// PROVIDER HANDLING ///
    public boolean isProvider(P provider) {
        return this.providerEntries.containsKey(provider);
    }

    public void addProvider(P provider) {
        this.providerEntries.put(provider, System.currentTimeMillis());
    }

    public void removeProvider(P provider) {
        this.providerEntries.remove(provider);
    }

    /** Combines two networks into one */
    public void joinNetworks(NodeNet<R, P, L> network) {
        if (network == this) return;

        List<L> oldNodes = new ArrayList<>(network.links.size());
        oldNodes.addAll(network.links);

        for (L conductor : oldNodes) {
            forceJoinLink(conductor);
        }
        network.links.clear();

        for (Object connector : network.receiverEntries.keySet()) {
            this.addReceiver((R) connector);
        }
        for (Object connector : network.providerEntries.keySet()) {
            this.addProvider((P) connector);
        }
        network.destroy();
    }

    /** Adds the node as part of this network's links, removing it from any previous network */
    public NodeNet<R, P, L> joinLink(L node) {
        if (node.net != null)
            node.net.leaveLink(node);
        return forceJoinLink(node);
    }

    /** Adds the node as part of this network's links without removing it from an existing network */
    public NodeNet<R, P, L> forceJoinLink(L node) {
        this.links.add(node);
        node.setNet(this);
        return this;
    }

    /** Removes the specified node from the network */
    public void leaveLink(L node) {
        node.setNet(null);
        this.links.remove(node);
    }

    /// GENERAL NETWORK CONTROL ///
    public void invalidate() {
        this.valid = false;
        UniNodespace.activeNodeNets.remove(this);
    }

    public boolean isValid() {
        return this.valid;
    }

    public void resetTrackers() { }

    public abstract void update();

    public void destroy() {
        this.invalidate();
        for (GenNode link : this.links) {
            if (link.net == this)
                link.setNet(null);
        }
        this.links.clear();
        this.receiverEntries.clear();
        this.providerEntries.clear();
    }
}
