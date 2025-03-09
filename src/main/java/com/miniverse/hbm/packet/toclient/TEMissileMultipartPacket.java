package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.handler.MissileStruct;
import com.miniverse.hbm.tileentity.bomb.TileEntityCompactLauncher;
import com.miniverse.hbm.tileentity.bomb.TileEntityLaunchTable;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineMissileAssembly;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TEMissileMultipartPacket {

    private int x;
    private int y;
    private int z;
    private MissileStruct missile;

    public TEMissileMultipartPacket() { }

    public TEMissileMultipartPacket(int x, int y, int z, MissileStruct missile) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.missile = missile;
    }

    public static TEMissileMultipartPacket decode(FriendlyByteBuf buf) {
        TEMissileMultipartPacket packet = new TEMissileMultipartPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.missile = MissileStruct.readFromByteBuffer(buf);
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        missile.writeToByteBuffer(buf);
    }

    public static void handle(TEMissileMultipartPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            if (Minecraft.getInstance().level != null) {
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(pos);
                if (te instanceof TileEntityCompactLauncher) {
                    TileEntityCompactLauncher launcher = (TileEntityCompactLauncher) te;
                    launcher.load = msg.missile;
                }
                if (te instanceof TileEntityLaunchTable) {
                    TileEntityLaunchTable launcher = (TileEntityLaunchTable) te;
                    launcher.load = msg.missile;
                }
                if (te instanceof TileEntityMachineMissileAssembly) {
                    TileEntityMachineMissileAssembly rack = (TileEntityMachineMissileAssembly) te;
                    rack.load = msg.missile;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
