package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.items.tool.ItemSatInterface;
import com.miniverse.hbm.saveddata.satellites.Satellite;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SatPanelPacket {

    private int type;
    private CompoundTag nbt;

    public SatPanelPacket() { }

    public SatPanelPacket(Satellite sat) {
        this.type = sat.getID();
        CompoundTag tag = new CompoundTag();
        sat.writeToNBT(tag);
        this.nbt = tag;
    }

    /**
     * Decodes the packet from the buffer.
     */
    public static SatPanelPacket decode(FriendlyByteBuf buf) {
        SatPanelPacket packet = new SatPanelPacket();
        packet.type = buf.readInt();
        packet.nbt = buf.readNbt(); // May be null if nothing was written
        return packet;
    }

    /**
     * Encodes the packet into the buffer.
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(type);
        buf.writeNbt(nbt);
    }

    /**
     * Handles the packet on the client thread.
     */
    public static void handle(SatPanelPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Access the client instance
            Minecraft mc = Minecraft.getInstance();
            try {
                // Create the satellite instance based on type
                ItemSatInterface.currentSat = Satellite.create(msg.type);
                if (msg.nbt != null) {
                    ItemSatInterface.currentSat.readFromNBT(msg.nbt);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
