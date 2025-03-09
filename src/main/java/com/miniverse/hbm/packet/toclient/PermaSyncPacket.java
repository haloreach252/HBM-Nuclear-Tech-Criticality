package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.packet.PermaSyncHandler;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PermaSyncPacket {

    // Store the raw packet data so that we can process it on the client thread.
    private final byte[] data;

    /**
     * Called on the client side to deserialize the packet.
     * Instead of processing directly in the constructor (which may not be on the main thread),
     * we store the raw bytes for later processing in the handle() method.
     */
    public PermaSyncPacket(FriendlyByteBuf buf) {
        int readable = buf.readableBytes();
        data = new byte[readable];
        buf.readBytes(data);
    }

    /**
     * This method is used server-side to encode the packet.
     * Note: The player must be a ServerPlayer.
     */
    public void encode(FriendlyByteBuf buf, Player player) {
        // Cast player to ServerPlayer and level to ServerLevel.
        PermaSyncHandler.writePacket(buf, (net.minecraft.server.level.ServerLevel) player.level(), (net.minecraft.server.level.ServerPlayer) player);
    }

    /**
     * This encode method is not used because encoding requires a player context.
     */
    public static void encode(PermaSyncPacket msg, FriendlyByteBuf buf) {
        throw new UnsupportedOperationException("Encoding requires player context; use encode(buf, player) instead.");
    }

    /**
     * Handle the packet on the client thread.
     * This method recreates a FriendlyByteBuf from the stored data and then calls PermaSyncHandler.readPacket.
     */
    public static void handle(PermaSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Reconstruct the buffer from the stored data.
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(msg.data));
            Level level = Minecraft.getInstance().level;
            Player player = Minecraft.getInstance().player;
            if (level != null && player != null) {
                PermaSyncHandler.readPacket(buf, level, player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
