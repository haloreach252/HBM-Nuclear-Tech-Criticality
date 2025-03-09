package com.miniverse.hbm.packet;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Abstract class for precompiled packets.
 * Use this if the packet is going to be threaded through the PacketThreading handler.
 */
public abstract class PrecompiledPacket {

    private FriendlyByteBuf preCompiledBuf;

    /**
     * Returns a precompiled buffer used to avoid race conditions when sending certain packets in threads.
     * @return The precompiled packet in a FriendlyByteBuf.
     */
    public FriendlyByteBuf getPreBuf() {
        if (preCompiledBuf == null || preCompiledBuf.readableBytes() <= 0) {
            this.makePreBuf();
        }
        return preCompiledBuf;
    }

    /**
     * Forcefully creates the precompiled buffer.
     * Use getPreBuf() whenever possible.
     */
    public void makePreBuf() {
        if (preCompiledBuf != null) {
            preCompiledBuf.release();
        }
        preCompiledBuf = new FriendlyByteBuf(Unpooled.buffer());
        this.toBytes(preCompiledBuf); // Create buffer and write data into it.
    }

    /**
     * Write this packet's data to the provided buffer.
     *
     * @param buf The buffer to write data to.
     */
    public abstract void toBytes(FriendlyByteBuf buf);

    /**
     * Read this packet's data from the provided buffer.
     * Subclasses should implement this if needed.
     *
     * @param buf The buffer to read data from.
     */
    public abstract void fromBytes(FriendlyByteBuf buf);
}
