package com.miniverse.hbm.packet.toclient;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticleBurstPacket {

    private int x;
    private int y;
    private int z;
    private int block;
    private int meta;

    public ParticleBurstPacket() { }

    public ParticleBurstPacket(int x, int y, int z, int block, int meta) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
        this.meta = meta;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(block);
        buf.writeInt(meta);
    }

    public void fromBytes(FriendlyByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        block = buf.readInt();
        meta = buf.readInt();
    }

    public static class Handler {
        public static void handle(final ParticleBurstPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                try {
                    if (Minecraft.getInstance().level == null)
                        return;

                    BlockPos pos = new BlockPos(message.x, message.y, message.z);
                    // Retrieve block using its legacy ID.
                    Block blk = Block.byLegacyId(message.block);
                    if (blk == null)
                        return;

                    // In modern versions, block metadata is replaced by BlockState properties.
                    // Here we use the default state; if you need to account for meta, you must convert it appropriately.
                    BlockState state = blk.defaultBlockState();

                    // Spawn block destroy particle effects.
                    Minecraft.getInstance().level.getParticleEngine().addBlockDestroyEffects(pos, state);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
