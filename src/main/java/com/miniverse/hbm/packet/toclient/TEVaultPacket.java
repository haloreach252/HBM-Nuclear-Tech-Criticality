package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.tileentity.machine.TileEntityBlastDoor;
import com.miniverse.hbm.tileentity.machine.TileEntityVaultDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TEVaultPacket {

    private int x;
    private int y;
    private int z;
    private boolean isOpening;
    private int state;
    private long sysTime;
    private int type;

    public TEVaultPacket() { }

    public TEVaultPacket(int x, int y, int z, boolean isOpening, int state, long sysTime, int type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isOpening = isOpening;
        this.state = state;
        this.sysTime = sysTime;
        this.type = type;
    }

    public static TEVaultPacket decode(FriendlyByteBuf buf) {
        TEVaultPacket packet = new TEVaultPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.isOpening = buf.readBoolean();
        packet.state = buf.readInt();
        packet.sysTime = buf.readLong();
        packet.type = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeBoolean(isOpening);
        buf.writeInt(state);
        buf.writeLong(sysTime);
        buf.writeInt(type);
    }

    public static void handle(TEVaultPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            if (Minecraft.getInstance().level != null) {
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(pos);
                try {
                    if (te instanceof TileEntityVaultDoor) {
                        TileEntityVaultDoor vault = (TileEntityVaultDoor) te;
                        vault.isOpening = msg.isOpening;
                        vault.state = msg.state;
                        if (msg.sysTime == 1)
                            vault.sysTime = System.currentTimeMillis();
                        vault.type = msg.type;
                    }
                    if (te instanceof TileEntityBlastDoor) {
                        TileEntityBlastDoor blast = (TileEntityBlastDoor) te;
                        blast.isOpening = msg.isOpening;
                        blast.state = msg.state;
                        if (msg.sysTime == 1)
                            blast.sysTime = System.currentTimeMillis();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
