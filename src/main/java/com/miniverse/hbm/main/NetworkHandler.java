package com.miniverse.hbm.main;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

    // Define a protocol version. In a real mod you might want a more complex version string.
    public static final String PROTOCOL_VERSION = "1";

    // Create a new SimpleChannel for your mod.
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(HBMNuclearTechCriticality.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    /**
     * Registers a new message type on the channel.
     *
     * @param index             A unique numerical discriminator for the message.
     * @param messageType       The class of the message.
     * @param encoder           A lambda to encode the message into a FriendlyByteBuf.
     * @param decoder           A lambda to decode the message from a FriendlyByteBuf.
     * @param messageConsumer   A lambda handling the message.
     * @param direction         The network direction target (PLAY_TO_CLIENT or PLAY_TO_SERVER).
     */
    public static <T> void registerMessage(int index, Class<T> messageType,
                                           java.util.function.BiConsumer<T, FriendlyByteBuf> encoder,
                                           java.util.function.Function<FriendlyByteBuf, T> decoder,
                                           java.util.function.BiConsumer<T, net.minecraftforge.network.NetworkEvent.Context> messageConsumer,
                                           NetworkDirection direction) {
        CHANNEL.registerMessage(index, messageType, encoder, decoder, messageConsumer, () -> direction);
    }

    /**
     * Sends a message from the client to the server.
     */
    public static void sendToServer(Object message) {
        CHANNEL.sendToServer(message);
    }

    /**
     * Sends a message from the server to a specific player.
     */
    public static void sendTo(Object message, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    /**
     * Sends a message to all connected players.
     */
    public static void sendToAll(Object message) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), message);
    }

    /**
     * Sends a message to all players in a specific dimension.
     */
    public static void sendToDimension(Object message, int dimensionId) {
        CHANNEL.send(PacketDistributor.DIMENSION.with(() -> dimensionId), message);
    }

    /**
     * Sends a message to all players within a given range of a point.
     *
     * Note: PacketDistributor.NEAR takes a TargetPoint that contains x, y, z, range, and dimension.
     */
    public static void sendToAllAround(Object message, PacketDistributor.TargetPoint point) {
        CHANNEL.send(PacketDistributor.NEAR.with(() -> point), message);
    }
}
