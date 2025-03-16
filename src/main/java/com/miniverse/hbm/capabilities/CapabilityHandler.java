package com.miniverse.hbm.capabilities;

import com.miniverse.hbm.HBMNuclearTechCriticality;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Handles attachment of capabilities to entities.
 */
@Mod.EventBusSubscriber(modid = HBMNuclearTechCriticality.MODID)
public class CapabilityHandler {

    // Resource locations for capability attachment
    private static final ResourceLocation HBM_PLAYER_CAP = new ResourceLocation(HBMNuclearTechCriticality.MODID, "player_props");
    private static final ResourceLocation HBM_LIVING_CAP = new ResourceLocation(HBMNuclearTechCriticality.MODID, "living_props");

    /**
     * Attaches the appropriate capabilities to entities.
     * Player entities get both player and living capabilities.
     * Other living entities only get the living capability.
     */
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        // Players get the player capability
        if (entity instanceof Player player) {
            HbmPlayerPropsProvider playerProvider = new HbmPlayerPropsProvider(player);
            event.addCapability(HBM_PLAYER_CAP, playerProvider);

            HbmLivingPropsProvider livingProvider = new HbmLivingPropsProvider();
            event.addCapability(HBM_LIVING_CAP, livingProvider);

            // Add listeners to invalidate capabilities when needed
            event.addListener(playerProvider::invalidate);
            event.addListener(livingProvider::invalidate);
        }
        // Other living entities get the living capability
        else if (entity instanceof LivingEntity) {
            HbmLivingPropsProvider livingProvider = new HbmLivingPropsProvider();
            event.addCapability(HBM_LIVING_CAP, livingProvider);

            // Add listener to invalidate capability when needed
            event.addListener(livingProvider::invalidate);
        }
    }

    /**
     * Copies player capabilities when the player is cloned (e.g., after death or returning from the End)
     */
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        // Don't copy capabilities when returning from the End
        if (event.isWasDeath()) {
            // Copy living properties
            event.getOriginal().getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(oldCap -> {
                event.getEntity().getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(newCap -> {
                    CompoundTag nbt = new CompoundTag();
                    ((HbmLivingProps)oldCap).saveNBTData(nbt);
                    ((HbmLivingProps)newCap).loadNBTData(nbt);
                });
            });

            // Copy player properties - using the direct NBT serialization
            event.getOriginal().getCapability(CustomCapabilities.HBM_PLAYER_CAPABILITY).ifPresent(oldCap -> {
                event.getEntity().getCapability(CustomCapabilities.HBM_PLAYER_CAPABILITY).ifPresent(newCap -> {
                    CompoundTag nbt = ((HbmPlayerProps)oldCap).serializeNBT();
                    ((HbmPlayerProps)newCap).deserializeNBT(nbt);
                });
            });
        }
    }
}