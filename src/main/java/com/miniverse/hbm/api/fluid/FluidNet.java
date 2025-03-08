package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.uninos.NodeNet;

public class FluidNet extends NodeNet {
    public long tracker = 0L;

    protected static int timeout = 3_000;

    @Override public void resetTrackers() { this.tracker = 0; }

    @Override
    public void update() {

    }
}
