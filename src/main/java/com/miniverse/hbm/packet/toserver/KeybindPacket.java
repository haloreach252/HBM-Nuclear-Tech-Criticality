package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.handler.HbmKeybinds.EnumKeybind;
import com.miniverse.hbm.handler.HbmKeybindsServer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KeybindPacket {

    private int key;
    private boolean pressed;

    public KeybindPacket() { }

    public KeybindPacket(EnumKeybind key, boolean pressed) {
        this.key = key.ordinal();
        this.pressed = pressed;
    }

    public static KeybindPacket decode(FriendlyByteBuf buf) {
        KeybindPacket packet = new KeybindPacket();
        packet.key = buf.readInt();
        packet.pressed = buf.readBoolean();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(key);
        buf.writeBoolean(pressed);
    }

    public static void handle(KeybindPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;
        ctx.get().enqueueWork(() -> {
            HbmKeybindsServer.onPressedServer(player, EnumKeybind.values()[msg.key], msg.pressed);
        });
        ctx.get().setPacketHandled(true);
    }
}
