package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerInformPacket {

    private boolean fancy;
    private String dmesg = "";
    private int id;
    private Component component;
    private int millis = 0;

    public PlayerInformPacket() { }

    // Non-fancy constructor with default duration (millis == 0)
    public PlayerInformPacket(String dmesg, int id) {
        this.fancy = false;
        this.dmesg = dmesg;
        this.id = id;
    }

    // Fancy constructor using a Component
    public PlayerInformPacket(Component component, int id) {
        this.fancy = true;
        this.component = component;
        this.id = id;
    }

    // Non-fancy constructor with custom duration
    public PlayerInformPacket(String dmesg, int id, int millis) {
        this.fancy = false;
        this.dmesg = dmesg;
        this.id = id;
        this.millis = millis;
    }

    // Fancy constructor with custom duration
    public PlayerInformPacket(Component component, int id, int millis) {
        this.fancy = true;
        this.component = component;
        this.id = id;
        this.millis = millis;
    }

    /**
     * Decodes a packet from the network buffer.
     */
    public static PlayerInformPacket decode(FriendlyByteBuf buf) {
        PlayerInformPacket packet = new PlayerInformPacket();
        packet.id = buf.readInt();
        packet.millis = buf.readInt();
        packet.fancy = buf.readBoolean();
        if (!packet.fancy) {
            packet.dmesg = buf.readUtf(32767);
        } else {
            String json = buf.readUtf(32767);
            packet.component = Component.Serializer.fromJson(json);
        }
        return packet;
    }

    /**
     * Encodes this packet into the network buffer.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(millis);
        buf.writeBoolean(fancy);
        if (!fancy) {
            buf.writeUtf(dmesg);
        } else {
            buf.writeUtf(Component.Serializer.toJson(component));
        }
    }

    /**
     * Handles the packet on the client thread.
     */
    public static void handle(PlayerInformPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // On the client side, delegate the display to your proxy.
            // The displayTooltip method accepts a string (obtained from the Component via getString())
            if (msg.millis == 0) {
                HBMNuclearTechCriticality.proxy.displayTooltip(msg.fancy ? msg.component.getString() : msg.dmesg, msg.id);
            } else {
                HBMNuclearTechCriticality.proxy.displayTooltip(msg.fancy ? msg.component.getString() : msg.dmesg, msg.millis, msg.id);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
