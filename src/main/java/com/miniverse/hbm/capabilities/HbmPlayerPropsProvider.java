package com.miniverse.hbm.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Capability provider for HbmPlayerProps.
 * This implementation attaches to player entities to provide mod-specific player properties.
 */
public class HbmPlayerPropsProvider implements ICapabilitySerializable<CompoundTag> {

    // The capability instance
    private final HbmPlayerProps instance = new HbmPlayerProps();

    // Lazy optional of the capability for efficient retrieval
    private final LazyOptional<HbmPlayerProps> lazyOptional = LazyOptional.of(() -> instance);

    /**
     * Create a new provider for the given player
     * @param player The player to attach this capability to
     */
    public HbmPlayerPropsProvider(Player player) {
        instance.setPlayer(player);
    }

    /**
     * Create a new provider (player will be set later)
     */
    public HbmPlayerPropsProvider() {
        // Player will be set externally when needed
    }

    /**
     * Gets the capability if it matches the requested capability type.
     */
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CustomCapabilities.HBM_PLAYER_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    /**
     * Serializes the capability data to NBT.
     */
    @Override
    public CompoundTag serializeNBT() {
        return instance.serializeNBT();
    }

    /**
     * Deserializes capability data from NBT.
     */
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.deserializeNBT(nbt);
    }

    /**
     * Sets the player instance for this capability.
     * Should be called immediately after creating a new provider.
     */
    public void setPlayer(Player player) {
        instance.setPlayer(player);
    }

    /**
     * Called when the capability is invalidated (entity unloaded, etc.)
     * Important to prevent memory leaks.
     */
    public void invalidate() {
        lazyOptional.invalidate();
    }
}