package com.miniverse.hbm.api.entity;

import com.miniverse.hbm.interfaces.INeedsWork;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * Represents a radar entry for tracking entities on radar.
 */
@INeedsWork("Need to solve the issues presented here.")
public class RadarEntry {

    /** Name used for radar display, uses I18n for lookup. */
    public String unlocalizedName;
    /** The type of dot to show on the radar as well as the redstone level in tier mode. */
    public int blipLevel;
    public int posX;
    public int posY;
    public int posZ;
    public String dimension;
    public int entityID;
    /** Whether this radar entry should be counted for the redstone output. */
    public boolean redstone;

    public RadarEntry() { } // Blank constructor for packets

    public RadarEntry(String name, int level, int x, int y, int z, String dimension, int entityID, boolean redstone) {
        this.unlocalizedName = name;
        this.blipLevel = level;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.dimension = dimension;
        this.entityID = entityID;
        this.redstone = redstone;
    }

    public RadarEntry(IRadarDetectableNT detectable, Entity entity, boolean redstone) {
        this(detectable.getUnlocalizedName(),
                detectable.getBlipLevel(),
                (int) Math.floor(entity.getX()),
                (int) Math.floor(entity.getY()),
                (int) Math.floor(entity.getZ()),
                entity.level.dimension().location().toString(),
                entity.getId(),
                redstone);
    }

    public RadarEntry(IRadarDetectable detectable, Entity entity) {
        this(detectable.getTargetType().name(),
                detectable.getTargetType().ordinal(),
                (int) Math.floor(entity.getX()),
                (int) Math.floor(entity.getY()),
                (int) Math.floor(entity.getZ()),
                entity.level.dimension().location().toString(),
                entity.getId(),
                entity.getDeltaMovement().y < 0);
    }

    public RadarEntry(Player player) {
        this(player.getScoreboardName(),
                IRadarDetectableNT.PLAYER,
                (int) Math.floor(player.getX()),
                (int) Math.floor(player.getY()),
                (int) Math.floor(player.getZ()),
                player.level.dimension().location().toString(),
                player.getId(),
                true);
    }

    /**
     * Reads the radar entry data from a byte buffer.
     *
     * @param buf The buffer to read from.
     */
    public void fromBytes(ByteBuf buf) {
        this.unlocalizedName = buf.readUtf(32767); // Replaces ByteBufUtils.readUTF8String
        this.blipLevel = buf.readShort();
        this.posX = buf.readInt();
        this.posY = buf.readInt();
        this.posZ = buf.readInt();
        this.dimension = buf.readUtf(32767); // Handles dimension string
        this.entityID = buf.readInt();
    }

    /**
     * Writes the radar entry data to a byte buffer.
     *
     * @param buf The buffer to write to.
     */
    public void toBytes(ByteBuf buf) {
        buf.writeUtf(this.unlocalizedName, 32767); // Replaces ByteBufUtils.writeUTF8String
        buf.writeShort(this.blipLevel);
        buf.writeInt(this.posX);
        buf.writeInt(this.posY);
        buf.writeInt(this.posZ);
        buf.writeUtf(this.dimension, 32767); // Writes dimension string
        buf.writeInt(this.entityID);
    }
}
