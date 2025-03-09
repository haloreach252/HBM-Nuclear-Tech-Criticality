package com.miniverse.hbm.uninos.networkproviders;

import com.miniverse.hbm.api.fluidmk2.FluidNetMK2;
import com.miniverse.hbm.uninos.INetworkProvider;

public class FluidNetProvider implements INetworkProvider<FluidNetMK2> {
    @Override
    public FluidNetMK2 provideNetwork() {
        return new FluidNetMK2();
    }
}
