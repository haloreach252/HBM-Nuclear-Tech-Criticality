package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.interfaces.IControlReceiver;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NBTControlPacket {

    private CompoundTag nbt;
    private int x;
    private int y;
    private int z;

    public NBTControlPacket() { }

    public NBTControlPacket(CompoundTag nbt, int x, int y, int z) {
        this.nbt = nbt;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static NBTControlPacket decode(FriendlyByteBuf buf) {
        NBTControlPacket packet = new NBTControlPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.nbt = buf.readNbt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeNbt(nbt);
    }

    public static void handle(NBTControlPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            BlockEntity te = player.level.getBlockEntity(pos);
            if (te instanceof IControlReceiver) {
                IControlReceiver controlReceiver = (IControlReceiver) te;
                if (controlReceiver.hasPermission(player)) {
                    controlReceiver.receiveControl(player, msg.nbt);
                    controlReceiver.receiveControl(msg.nbt);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
