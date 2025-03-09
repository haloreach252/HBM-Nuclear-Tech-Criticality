package com.miniverse.hbm.saveddata.satellites;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SatelliteRelay extends Satellite {
    public SatelliteRelay() {
        this.satIface = Interfaces.NONE;
    }

    public void onOrbit(Level world, double x, double y, double z) {

        for(Object p : world.players())
            ((Player)p).triggerAchievement(HBMNuclearTechCriticality.achFOEQ);
    }
}
