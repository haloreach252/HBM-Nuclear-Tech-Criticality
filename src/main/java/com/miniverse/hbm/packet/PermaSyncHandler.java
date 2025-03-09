package com.miniverse.hbm.packet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.miniverse.hbm.handler.ImpactWorldHandler;
import com.miniverse.hbm.handler.pollution.PollutionHandler;
import com.miniverse.hbm.handler.pollution.PollutionHandler.PollutionData;
import com.miniverse.hbm.handler.pollution.PollutionHandler.PollutionType;
import com.miniverse.hbm.potion.HbmPotion;
import com.miniverse.hbm.saveddata.TomSaveData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Utility for permanently synchronizing values every tick with a player in the given world context.
 * Data is written directly into a FriendlyByteBuf (rather than using NBT) to reduce overhead.
 */
public class PermaSyncHandler {

    // A set to hold the IDs of players with the death effect.
    public static final HashSet<Integer> boykissers = new HashSet<>();
    // Array to store pollution values by type.
    public static final float[] pollution = new float[PollutionType.values().length];

    /**
     * Writes data into the provided FriendlyByteBuf for synchronization.
     *
     * @param buf    The byte buffer to write to.
     * @param world  The server level from which to get data.
     * @param player The server player for which the packet is prepared.
     */
    public static void writePacket(FriendlyByteBuf buf, ServerLevel world, ServerPlayer player) {

        // TOM IMPACT DATA
        TomSaveData data = TomSaveData.forWorld(world);
        buf.writeFloat(data.fire);
        buf.writeFloat(data.dust);
        buf.writeBoolean(data.impact);

        // SHITTY MEMES
        // Collect the IDs of all players with the "death" effect.
        List<Integer> ids = new ArrayList<>();
        // In 1.20.1, ServerLevel provides the players() method.
        for (ServerPlayer p : world.players()) {
            if (p.hasEffect(HbmPotion.death)) {
                ids.add(p.getId());
            }
        }
        buf.writeShort(ids.size());
        for (Integer id : ids) {
            buf.writeInt(id);
        }

        // POLLUTION
        PollutionData localPollution = PollutionHandler.getPollutionData(world,
                (int) Math.floor(player.getX()),
                (int) Math.floor(player.getY()),
                (int) Math.floor(player.getZ()));
        if (localPollution == null)
            localPollution = new PollutionData();
        for (int i = 0; i < PollutionType.values().length; i++) {
            buf.writeFloat(localPollution.pollution[i]);
        }
    }

    /**
     * Reads synchronized data from the provided FriendlyByteBuf and updates client-side handlers.
     *
     * @param buf    The byte buffer from which data is read.
     * @param world  The client level (or general level) on which to apply data.
     * @param player The player receiving the sync data.
     */
    public static void readPacket(FriendlyByteBuf buf, Level world, Player player) {

        // TOM IMPACT DATA
        ImpactWorldHandler.lastSyncWorld = player.getLevel();
        ImpactWorldHandler.fire = buf.readFloat();
        ImpactWorldHandler.dust = buf.readFloat();
        ImpactWorldHandler.impact = buf.readBoolean();

        // SHITTY MEMES
        boykissers.clear();
        int count = buf.readShort();
        for (int i = 0; i < count; i++) {
            boykissers.add(buf.readInt());
        }

        // POLLUTION
        for (int i = 0; i < PollutionType.values().length; i++) {
            pollution[i] = buf.readFloat();
        }
    }
}
