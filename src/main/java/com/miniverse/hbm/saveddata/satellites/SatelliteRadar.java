package com.miniverse.hbm.saveddata.satellites;

public class SatelliteRadar extends Satellite {
    public SatelliteRadar() {
        this.ifaceAcs.add(InterfaceActions.HAS_RADAR);
        this.ifaceAcs.add(InterfaceActions.HAS_MAP);
        this.satIface = Interfaces.SAT_PANEL;
    }
}
