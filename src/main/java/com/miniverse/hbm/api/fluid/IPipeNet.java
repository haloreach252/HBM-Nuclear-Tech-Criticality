package com.miniverse.hbm.api.fluid;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import com.miniverse.hbm.inventory.fluid.FluidType;

/**
 * Interface for pipe networks managing fluid flow in the HBM mod.
 */
public interface IPipeNet {

    /**
     * Merges this pipe network with another.
     *
     * @param network The network to merge with.
     */
    void joinNetworks(IPipeNet network);

    /**
     * Gets the fluid conductors linked to this pipe network.
     *
     * @return A list of linked fluid conductors.
     */
    List<IFluidConductor> getLinks();

    /**
     * Gets the subscribers connected to this pipe network.
     *
     * @return A set of subscribed fluid connectors.
     */
    Set<IFluidConnector> getSubscribers();

    /**
     * Adds a fluid conductor to this network.
     *
     * @param conductor The conductor to join.
     * @return The updated pipe network.
     */
    IPipeNet joinLink(IFluidConductor conductor);

    /**
     * Removes a fluid conductor from this network.
     *
     * @param conductor The conductor to remove.
     */
    void leaveLink(IFluidConductor conductor);

    /**
     * Subscribes a fluid connector to this pipe network.
     *
     * @param connector The fluid connector to subscribe.
     */
    void subscribe(IFluidConnector connector);

    /**
     * Unsubscribes a fluid connector from this pipe network.
     *
     * @param connector The fluid connector to unsubscribe.
     */
    void unsubscribe(IFluidConnector connector);

    /**
     * Checks if a given fluid connector is subscribed to this pipe network.
     *
     * @param connector The fluid connector to check.
     * @return True if the connector is subscribed, false otherwise.
     */
    boolean isSubscribed(IFluidConnector connector);

    /**
     * Destroys this pipe network, removing all links and subscriptions.
     */
    void destroy();

    /**
     * Checks if this pipe network is still valid.
     *
     * @return True if the network is valid, false otherwise.
     */
    boolean isValid();

    /**
     * Transfers fluid through the network.
     *
     * @param fill The amount of fluid to transfer.
     * @param pressure The pressure level of the fluid.
     * @return The amount of fluid that was successfully transferred.
     */
    long transferFluid(long fill, int pressure);

    /**
     * Gets the fluid type associated with this pipe network.
     *
     * @return The fluid type.
     */
    FluidType getType();

    /**
     * Gets the total amount of fluid transferred by this network.
     *
     * @return The total transferred fluid as a {@link BigInteger}.
     */
    BigInteger getTotalTransfer();
}
