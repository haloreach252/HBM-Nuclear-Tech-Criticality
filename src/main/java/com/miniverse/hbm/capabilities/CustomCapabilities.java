package com.miniverse.hbm.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CustomCapabilities {
    public static final Capability<HbmLivingProps> HBM_LIVING_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<HbmPlayerProps> HBM_PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(HbmLivingProps.class);
        event.register(HbmPlayerProps.class);
    }
}
