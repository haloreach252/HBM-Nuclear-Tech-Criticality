package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.capabilities.HbmLivingProps;
import com.miniverse.hbm.capabilities.HbmPlayerProps;
import com.miniverse.hbm.packet.PrecompiledPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public class ExtPropPacket extends PrecompiledPacket {

    private HbmLivingProps props;
    private HbmPlayerProps pprps;
    // Store the remaining data from the packet.
    private ByteBuf buf;

    public ExtPropPacket() { }

    public ExtPropPacket(HbmLivingProps props, HbmPlayerProps pprps) {
        this.props = props;
        this.pprps = pprps;
    }

    @Override
    public void fromBytes(FriendlyByteBuf buf) {
        // Simply store the buffer for later deserialization.
        this.buf = buf;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        // Let the property objects write their data to the buffer.
        props.serialize(buf);
        pprps.serialize(buf);
    }

    public static class Handler {
        public static void handle(final ExtPropPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().level == null)
                    return;

                // Get the client player's extended properties.
                HbmLivingProps props = HbmLivingProps.getData(Minecraft.getInstance().player);
                HbmPlayerProps pprps = HbmPlayerProps.getData(Minecraft.getInstance().player);

                // Deserialize the data from the packet buffer.
                props.deserialize(message.buf);
                pprps.deserialize(message.buf);

                // Release the buffer.
                message.buf.release();
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
