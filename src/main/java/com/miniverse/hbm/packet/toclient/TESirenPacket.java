package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.items.machine.ItemCassette.SoundType;
import com.miniverse.hbm.items.machine.ItemCassette.TrackType;
import com.miniverse.hbm.sound.SoundLoopSiren;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineSiren;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TESirenPacket {

    private int x;
    private int y;
    private int z;
    private int id;
    private boolean active;

    public TESirenPacket() { }

    public TESirenPacket(int x, int y, int z, int id, boolean active) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.active = active;
    }

    public static TESirenPacket decode(FriendlyByteBuf buf) {
        TESirenPacket packet = new TESirenPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.id = buf.readInt();
        packet.active = buf.readBoolean();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(id);
        buf.writeBoolean(active);
    }

    public static void handle(TESirenPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            if (Minecraft.getInstance().level != null) {
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(pos);
                if (te instanceof TileEntityMachineSiren) {
                    TileEntityMachineSiren sirenTile = (TileEntityMachineSiren) te;
                    SoundLoopSiren sound = null;
                    // Search for an existing sound linked to this tile entity.
                    for (SoundLoopSiren s : SoundLoopSiren.list) {
                        if (s.getTE() == te) {
                            sound = s;
                            break;
                        }
                    }

                    if (msg.active) {
                        if (sound == null) {
                            // Start sound if none exists.
                            if (msg.id > 0) {
                                boolean repeat = TrackType.getEnum(msg.id).getType().name().equals(SoundType.LOOP.name());
                                SoundLoopSiren newSound = new SoundLoopSiren(TrackType.getEnum(msg.id).getSoundLocation(), te, TrackType.getEnum(msg.id).getType());
                                newSound.setRepeat(repeat);
                                newSound.intendedVolume = TrackType.getEnum(msg.id).getVolume();
                                Minecraft.getInstance().getSoundManager().play(newSound);
                            }
                        } else {
                            // If the sound exists, verify if the track is the same.
                            ResourceLocation loc = TrackType.getEnum(msg.id).getSoundLocation();
                            if (loc != null) {
                                String path = loc.getNamespace() + ":" + loc.getPath();
                                if (!sound.getPath().equals(path)) {
                                    // Track switched, stop the current sound and start the new one.
                                    sound.endSound();
                                    if (msg.id > 0) {
                                        Minecraft.getInstance().getSoundManager().play(
                                                new SoundLoopSiren(TrackType.getEnum(msg.id).getSoundLocation(), te, TrackType.getEnum(msg.id).getType())
                                        );
                                    }
                                }
                            }
                            sound.intendedVolume = TrackType.getEnum(msg.id).getVolume();
                        }
                    } else {
                        // Stop the sound if it exists.
                        if (sound != null) {
                            sound.endSound();
                            SoundLoopSiren.list.remove(sound);
                        }
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
