package com.miniverse.hbm.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Capability provider for HbmLivingProps.
 * This implementation attaches to living entities to provide radiation and other contamination tracking.
 */
public class HbmLivingPropsProvider implements ICapabilitySerializable<CompoundTag> {

    // The capability instance
    private final HbmLivingProps instance = new HbmLivingProps();

    // Lazy optional of the capability for efficient retrieval
    private final LazyOptional<HbmLivingProps> lazyOptional = LazyOptional.of(() -> instance);

    /**
     * Gets the capability if it matches the requested capability type.
     */
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CustomCapabilities.HBM_LIVING_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    /**
     * Serializes the capability data to NBT.
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        instance.saveNBTData(tag);

        // Also save the contamination effects list
        if (!instance.getCont().isEmpty()) {
            tag.putInt("contCount", instance.getCont().size());

            for (int i = 0; i < instance.getCont().size(); i++) {
                instance.getCont().get(i).saveToNBT(tag, i);
            }
        }

        return tag;
    }

    /**
     * Deserializes capability data from NBT.
     */
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.loadNBTData(nbt);

        // Also load the contamination effects list
        instance.getCont().clear();
        int count = nbt.getInt("contCount");

        for (int i = 0; i < count; i++) {
            instance.getCont().add(HbmLivingProps.ContaminationEffect.loadFromNBT(nbt, i));
        }
    }

    /**
     * Called when the capability is invalidated (entity unloaded, etc.)
     * Important to prevent memory leaks.
     */
    public void invalidate() {
        lazyOptional.invalidate();
    }
}