package com.miniverse.hbm.saveddata.satellites;

import com.miniverse.hbm.entity.projectile.EntityTom;
import com.miniverse.hbm.main.HBMNuclearTechCriticality;
import com.miniverse.hbm.saveddata.SatelliteSavedData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;

public class SatelliteHorizons extends Satellite {

    private boolean used = false;

    public SatelliteHorizons() {
        this.satIface = Interfaces.SAT_COORD;
    }

    @Override
    public void onOrbit(Level level, double x, double y, double z) {
        // Award achievement/advancement to all players on orbit.
        // Replace awardStat with your advancement trigger if necessary.
        for (Player p : level.players()) {
            p.awardStat(HBMNuclearTechCriticality.horizonsStart);
        }
    }

    @Override
    public void writeToNBT(CompoundTag nbt) {
        nbt.putBoolean("used", used);
    }

    @Override
    public void readFromNBT(CompoundTag nbt) {
        used = nbt.getBoolean("used");
    }

    @Override
    public void onCoordAction(Level level, Player player, int x, int y, int z) {
        if (used)
            return;

        used = true;
        SatelliteSavedData.getData(level).markDirty();

        EntityTom tom = new EntityTom(level);
        tom.setPos(x + 0.5, 600, z + 0.5);

        // Force the chunk containing the given coordinates to load.
        int chunkX = x >> 4;
        int chunkZ = z >> 4;
        // This call forces loading of the chunk; adjust if needed for your mod's behavior.
        ChunkAccess chunk = level.getChunkSource().getChunk(chunkX, chunkZ, true);

        level.addFreshEntity(tom);

        // Award achievement/advancement for completing Horizons.
        for (Player p : level.players()) {
            p.awardStat(HBMNuclearTechCriticality.horizonsEnd);
        }

        // Send a chat message to all players indicating activation.
        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();
            if (server != null) {
                server.getPlayerList().broadcastSystemMessage(
                        new TextComponent(ChatFormatting.RED + "Horizons has been activated."),
                        false
                );
            }
        }
    }
}
