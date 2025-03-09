package com.miniverse.hbm.saveddata.satellites;

import com.miniverse.hbm.itempool.ItemPoolsSatellite;

public class SatelliteLunarMiner extends SatelliteMiner {

    static {
        registerCargo(SatelliteLunarMiner.class, ItemPoolsSatellite.POOL_SAT_LUNAR);
    }
}
