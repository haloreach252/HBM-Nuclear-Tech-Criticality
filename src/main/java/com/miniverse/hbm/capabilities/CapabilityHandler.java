package com.miniverse.hbm.capabilities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilityHandler {
    private static final ResourceLocation HBM_PLAYER_CAP = new ResourceLocation("hbm", "player_props");
    private static final ResourceLocation HBM_LIVING_CAP = new ResourceLocation("hbm", "living_props");

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(HBM_PLAYER_CAP, new HbmPlayerPropsProvider());
        }
    }
}
