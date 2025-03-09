package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.explosion.vanillant.standard.ExplosionEffectStandard;
import com.miniverse.hbm.interfaces.NotableComments;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@NotableComments
public class ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket {

    private double posX;
    private double posY;
    private double posZ;
    private float size;
    private List<BlockPos> affectedBlocks;

    public ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket() { }

    public ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket(double x, double y, double z, float size, List<BlockPos> blocks) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.size = size;
        this.affectedBlocks = new ArrayList<>(blocks);
    }

    @Override
    public String toString() {
        return "ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", posZ=" + posZ +
                ", size=" + size +
                ", affectedBlocks=" + affectedBlocks +
                '}';
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat((float) this.posX);
        buf.writeFloat((float) this.posY);
        buf.writeFloat((float) this.posZ);
        buf.writeFloat(this.size);
        buf.writeInt(this.affectedBlocks.size());
        int baseX = (int) this.posX;
        int baseY = (int) this.posY;
        int baseZ = (int) this.posZ;
        for (BlockPos pos : this.affectedBlocks) {
            int offsetX = pos.getX() - baseX;
            int offsetY = pos.getY() - baseY;
            int offsetZ = pos.getZ() - baseZ;
            buf.writeByte(offsetX);
            buf.writeByte(offsetY);
            buf.writeByte(offsetZ);
        }
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.posX = buf.readFloat();
        this.posY = buf.readFloat();
        this.posZ = buf.readFloat();
        this.size = buf.readFloat();
        int count = buf.readInt();
        this.affectedBlocks = new ArrayList<>(count);
        int baseX = (int) this.posX;
        int baseY = (int) this.posY;
        int baseZ = (int) this.posZ;
        for (int i = 0; i < count; i++) {
            int offsetX = buf.readByte();
            int offsetY = buf.readByte();
            int offsetZ = buf.readByte();
            BlockPos pos = new BlockPos(baseX + offsetX, baseY + offsetY, baseZ + offsetZ);
            this.affectedBlocks.add(pos);
        }
    }

    public static class Handler {
        public static void handle(final ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Level world = Minecraft.getInstance().level;
                if (world != null) {
                    ExplosionEffectStandard.performClient(world, message.posX, message.posY, message.posZ, message.size, message.affectedBlocks);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
