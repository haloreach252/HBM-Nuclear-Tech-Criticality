package com.miniverse.hbm.packet.toclient;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import com.miniverse.hbm.util.Compat;
import com.miniverse.hbm.world.WorldUtil;

import java.util.function.Supplier;

public class BiomeSyncPacket {

    private int chunkX;
    private int chunkZ;
    private byte blockX;
    private byte blockZ;
    private short biome;
    private short[] biomeArray;

    public BiomeSyncPacket() { }

    public BiomeSyncPacket(int chunkX, int chunkZ, byte[] biomeArray) {
        this(chunkX, chunkZ, bytesToShorts(biomeArray));
    }

    public BiomeSyncPacket(int blockX, int blockZ, byte biome) {
        this(blockX, blockZ, (short) biome);
    }

    public BiomeSyncPacket(int chunkX, int chunkZ, short[] biomeArray) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.biomeArray = biomeArray;
    }

    public BiomeSyncPacket(int blockX, int blockZ, short biome) {
        this.chunkX = blockX >> 4;
        this.chunkZ = blockZ >> 4;
        this.blockX = (byte) (blockX & 15);
        this.blockZ = (byte) (blockZ & 15);
        this.biome = biome;
    }

    @Override
    public String toString() {
        return "BiomeSyncPacket{" +
                "chunkX=" + chunkX +
                ", chunkZ=" + chunkZ +
                ", blockX=" + blockX +
                ", blockZ=" + blockZ +
                ", biome=" + biome +
                ", biomeArray=" + (biomeArray != null ? "present" : "null") +
                '}';
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.chunkX);
        buf.writeInt(this.chunkZ);
        if (this.biomeArray == null) {
            buf.writeBoolean(false);
            buf.writeShort(this.biome);
            buf.writeByte(this.blockX);
            buf.writeByte(this.blockZ);
        } else {
            buf.writeBoolean(true);
            for (int i = 0; i < 256; i++) {
                buf.writeShort(this.biomeArray[i]);
            }
        }
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.chunkX = buf.readInt();
        this.chunkZ = buf.readInt();
        if (!buf.readBoolean()) {
            this.biome = buf.readShort();
            this.blockX = buf.readByte();
            this.blockZ = buf.readByte();
        } else {
            this.biomeArray = new short[256];
            for (int i = 0; i < 256; i++) {
                this.biomeArray[i] = buf.readShort();
            }
        }
    }

    private static short[] bytesToShorts(byte[] byteArray) {
        int size = byteArray.length;
        short[] shortArray = new short[size];
        for (int index = 0; index < size; index++) {
            shortArray[index] = (short) byteArray[index];
        }
        return shortArray;
    }

    /**
     * Handler for processing the packet on the client.
     */
    public static class Handler {
        public static void handle(final BiomeSyncPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Level world = Minecraft.getInstance().level;
                if (world == null)
                    return;

                // Check if the chunk is loaded
                if (!world.hasChunk(message.chunkX, message.chunkZ))
                    return;

                LevelChunk chunk = world.getChunk(message.chunkX, message.chunkZ);
                // Mark the chunk as modified so that changes are saved (if applicable)
                chunk.setUnsaved(true);

                // Use a mod list check instead of Loader.isModLoaded
                if (ModList.get().isLoaded(Compat.MOD_EIDS)) {
                    // Assume WorldUtil.getBiomeShortArray(chunk) returns a short[256]
                    short[] target = WorldUtil.getBiomeShortArray(chunk);
                    if (message.biomeArray == null) {
                        int index = ((message.blockZ & 15) << 4) | (message.blockX & 15);
                        target[index] = message.biome;
                        // Force a render update over the chunk area
                        BlockPos pos = new BlockPos(message.chunkX << 4, 0, message.chunkZ << 4);
                        BlockState state = world.getBlockState(pos);
                        world.sendBlockUpdated(pos, state, state, 3);
                    } else {
                        for (int i = 0; i < 256; i++) {
                            target[i] = message.biomeArray[i];
                        }
                        BlockPos pos = new BlockPos(message.chunkX << 4, 0, message.chunkZ << 4);
                        BlockState state = world.getBlockState(pos);
                        // Update the entire chunk's render range
                        world.sendBlockUpdated(pos, state, state, 3);
                    }
                } else {
                    // Fallback: update the chunk's biome array from the chunk object.
                    // In 1.20.1, the biome data is stored in a BiomeContainer. For simplicity, we assume that
                    // you have a way to access and modify it. Here we use the legacy byte array approach.
                    byte[] target = chunk.getBiomes().getRawBiomeIds(); // This method may vary depending on your implementation.
                    if (message.biomeArray == null) {
                        int index = ((message.blockZ & 15) << 4) | (message.blockX & 15);
                        target[index] = (byte) message.biome;
                        BlockPos pos = new BlockPos(message.chunkX << 4, 0, message.chunkZ << 4);
                        BlockState state = world.getBlockState(pos);
                        world.sendBlockUpdated(pos, state, state, 3);
                    } else {
                        for (int i = 0; i < 256; i++) {
                            target[i] = (byte) message.biomeArray[i];
                        }
                        BlockPos pos = new BlockPos(message.chunkX << 4, 0, message.chunkZ << 4);
                        BlockState state = world.getBlockState(pos);
                        world.sendBlockUpdated(pos, state, state, 3);
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
