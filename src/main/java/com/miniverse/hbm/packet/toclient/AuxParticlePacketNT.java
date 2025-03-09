package com.miniverse.hbm.packet.toclient;

import java.util.function.Supplier;

import com.miniverse.hbm.HBMNuclearTechCriticality;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class AuxParticlePacketNT {

    private FriendlyByteBuf buffer;

    // No-argument constructor needed for network registration.
    public AuxParticlePacketNT() { }

    /**
     * Creates a new AuxParticlePacketNT.
     * Adds position data to the provided CompoundTag and writes it into a FriendlyByteBuf.
     *
     * @param nbt The CompoundTag containing data.
     * @param x   X-coordinate.
     * @param y   Y-coordinate.
     * @param z   Z-coordinate.
     */
    public AuxParticlePacketNT(CompoundTag nbt, double x, double y, double z) {
        // Create a new buffer.
        this.buffer = new FriendlyByteBuf(Unpooled.buffer());

        // Add position data.
        nbt.putDouble("posX", x);
        nbt.putDouble("posY", y);
        nbt.putDouble("posZ", z);

        // Write the CompoundTag to the buffer.
        try {
            buffer.writeNbt(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the packet data from the given buffer.
     * Here we simply copy the received data into our internal buffer.
     *
     * @param buf The buffer received from the network.
     */
    public void fromBytes(FriendlyByteBuf buf) {
        // Copy the incoming buffer so we have our own instance.
        this.buffer = new FriendlyByteBuf(buf.copy());
    }

    /**
     * Writes the packet data to the given buffer.
     *
     * @param buf The buffer to which data is written.
     */
    public void toBytes(FriendlyByteBuf buf) {
        if (this.buffer == null) {
            this.buffer = new FriendlyByteBuf(Unpooled.buffer());
        }
        buf.writeBytes(this.buffer);
    }

    /**
     * The handler for processing this packet on the client side.
     */
    public static class Handler {
        public static void handle(final AuxParticlePacketNT message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                // Ensure the client world is loaded.
                if (Minecraft.getInstance().level == null)
                    return;

                try {
                    // Read the CompoundTag from the packet's buffer.
                    CompoundTag nbt = message.buffer.readNbt();
                    if (nbt != null) {
                        // Call the effect method on your client proxy.
                        HBMNuclearTechCriticality.proxy.effectNT(nbt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Release the internal buffer.
                    message.buffer.release();
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
