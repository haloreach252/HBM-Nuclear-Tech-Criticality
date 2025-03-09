package com.miniverse.hbm.uninos.networkproviders;

import com.miniverse.hbm.api.energymk2.PowerNetMK2;
import com.miniverse.hbm.uninos.INetworkProvider;

public class PowerNetProvider implements INetworkProvider<PowerNetMK2> {
    @Override
    public PowerNetMK2 provideNetwork() {
        return new PowerNetMK2();
    }
}
