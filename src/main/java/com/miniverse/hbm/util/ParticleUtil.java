package com.miniverse.hbm.util;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor.TargetPoint;

public class ParticleUtil {

    /**
     * Spawns a gas flame particle effect.
     * <p>
     * Creates a CompoundTag with the necessary data and either directly invokes the client effect
     * if on the client side, or sends a packet to all nearby players on the server.
     *
     * @param world The current world (Level) instance.
     * @param x     X position of the effect.
     * @param y     Y position of the effect.
     * @param z     Z position of the effect.
     * @param mX    Motion X component.
     * @param mY    Motion Y component.
     * @param mZ    Motion Z component.
     */
    public static void spawnGasFlame(Level world, double x, double y, double z, double mX, double mY, double mZ) {
        CompoundTag data = new CompoundTag();
        data.putString("type", "gasfire");
        data.putDouble("mX", mX);
        data.putDouble("mY", mY);
        data.putDouble("mZ", mZ);

        if (world.isClientSide()) {
            data.putDouble("posX", x);
            data.putDouble("posY", y);
            data.putDouble("posZ", z);
            HBMNuclearTechCriticality.proxy.effectNT(data);
        } else {
            TargetPoint targetPoint = new TargetPoint(x, y, z, 150, world.dimension());
            PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, x, y, z), targetPoint);
        }
    }

    /**
     * Spawns a drone debug line particle effect.
     * <p>
     * This method sets up a CompoundTag with the necessary data (including a color value) and either
     * directly triggers the client effect or sends a network packet to update nearby clients.
     *
     * @param world The current world (Level) instance.
     * @param x     X position of the effect.
     * @param y     Y position of the effect.
     * @param z     Z position of the effect.
     * @param x0    Secondary X coordinate (used for the line start/end).
     * @param y0    Secondary Y coordinate.
     * @param z0    Secondary Z coordinate.
     * @param color The color of the debug line.
     */
    public static void spawnDroneLine(Level world, double x, double y, double z, double x0, double y0, double z0, int color) {
        CompoundTag data = new CompoundTag();
        data.putString("type", "debugdrone");
        data.putDouble("mX", x0);
        data.putDouble("mY", y0);
        data.putDouble("mZ", z0);
        data.putInt("color", color);

        if (world.isClientSide()) {
            data.putDouble("posX", x);
            data.putDouble("posY", y);
            data.putDouble("posZ", z);
            HBMNuclearTechCriticality.proxy.effectNT(data);
        } else {
            TargetPoint targetPoint = new TargetPoint(x, y, z, 150, world.dimension());
            PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, x, y, z), targetPoint);
        }
    }
}
