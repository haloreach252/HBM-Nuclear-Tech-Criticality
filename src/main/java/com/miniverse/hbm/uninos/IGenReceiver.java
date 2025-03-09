package com.miniverse.hbm.uninos;

import com.miniverse.hbm.api.energymk2.IEnergyReceiverMK2.ConnectionPriority;

public interface IGenReceiver<T extends INetworkProvider> {

    public default ConnectionPriority getPriority() {
        return ConnectionPriority.NORMAL;
    }
}
