package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.packet.PrecompiledPacket;
import com.miniverse.hbm.tileentity.IBufPacketReceiver;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.network.FriendlyByteBuf;
import java.util.function.Supplier;

public class BufPacket extends PrecompiledPacket {

    private int x;
    private int y;
    private int z;
    private IBufPacketReceiver rec;
    // We store the remaining data in this buffer.
    private ByteBuf buf;

    public BufPacket() { }

    public BufPacket(int x, int y, int z, IBufPacketReceiver rec) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rec = rec;
    }

    @Override
    public void fromBytes(FriendlyByteBuf buf) {
        // Read the coordinates.
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        // Store the remaining data in our field.
        // Note: FriendlyByteBuf extends ByteBuf, so we can keep a reference.
        this.buf = buf;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        // Let the receiver serialize its data into the buffer.
        this.rec.serialize(buf);
    }

    public static class Handler {
        public static void handle(final BufPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().level == null)
                    return;

                BlockPos pos = new BlockPos(message.x, message.y, message.z);
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(pos);

                if (te instanceof IBufPacketReceiver) {
                    try {
                        ((IBufPacketReceiver) te).deserialize(message.buf);
                    } catch (Exception e) { // In case of buffer underflow or mis-read.
                        HBMNuclearTechCriticality.LOGGER.warn("A ByteBuf packet failed to be read and has thrown an error. This normally means that there was a buffer underflow and more data was read than was actually in the packet.");
                        HBMNuclearTechCriticality.LOGGER.warn("Tile: {}", te.getBlockState().getBlock().getName().getString());
                        HBMNuclearTechCriticality.LOGGER.warn(e.getMessage());
                    } finally {
                        message.buf.release();
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
