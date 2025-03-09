package com.miniverse.hbm.saveddata.satellites;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

/**
 * SatelliteResonator defines a satellite that uses coordinate actions to teleport a player.
 * When triggered, it plays a teleport sound, dismounts the player, teleports them to new coordinates,
 * and then plays the teleport sound again.
 */
public class SatelliteResonator extends Satellite {

    public SatelliteResonator() {
        this.coordAcs.add(CoordActions.HAS_Y);
        this.satIface = Interfaces.SAT_COORD;
    }

    @Override
    public void onCoordAction(Level level, Player player, int x, int y, int z) {
        if (!(player instanceof ServerPlayer)) {
            return;
        }

        // Play teleport sound at player's current position
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

        // Dismount the player if riding any entity
        player.stopRiding();

        // Teleport the player to the new coordinates (centered)
        ((ServerPlayer) player).teleportTo(x + 0.5, y, z + 0.5, player.getYRot(), player.getXRot());

        // Play teleport sound again at player's previous position (or you might choose to play it at the destination)
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
