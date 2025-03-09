package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.interfaces.IAnimatedDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TEDoorAnimationPacket {

    private int x, y, z;
    private byte state;
    private byte skinIndex;
    private byte texture;

    public TEDoorAnimationPacket() {
        // Default constructor for decoding
    }

    public TEDoorAnimationPacket(int x, int y, int z, byte state) {
        this(x, y, z, state, (byte) 0, (byte) -1);
    }

    public TEDoorAnimationPacket(int x, int y, int z, byte state, byte skinIndex, byte texture) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.state = state;
        this.skinIndex = skinIndex;
        this.texture = texture;
    }

    public static TEDoorAnimationPacket decode(FriendlyByteBuf buf) {
        TEDoorAnimationPacket packet = new TEDoorAnimationPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.state = buf.readByte();
        packet.skinIndex = buf.readByte();
        if (buf.readableBytes() == 1) {
            packet.texture = buf.readByte();
        }
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(state);
        buf.writeByte(skinIndex);
        if (texture != -1) {
            buf.writeByte(texture);
        }
    }

    public static void handle(TEDoorAnimationPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            if (Minecraft.getInstance().level != null) {
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(pos);
                if (te instanceof IAnimatedDoor) {
                    IAnimatedDoor door = (IAnimatedDoor) te;
                    door.handleNewState(msg.state);
                    door.setSkinIndex(msg.skinIndex);
                    door.setTextureState(msg.texture);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
