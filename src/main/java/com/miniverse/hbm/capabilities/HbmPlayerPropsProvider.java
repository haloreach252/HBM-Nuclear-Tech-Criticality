package com.miniverse.hbm.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HbmPlayerPropsProvider implements ICapabilityProvider {
    private final HbmPlayerProps instance = new HbmPlayerProps();
    private final LazyOptional<HbmPlayerProps> optional = LazyOptional.of(() -> instance);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == CustomCapabilities.HBM_PLAYER_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    public void deserializeNBT(CompoundTag nbt) {
        instance.load(nbt);
    }

    public CompoundTag serializeNBT() {
        return instance.save();
    }
}
