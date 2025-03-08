package com.miniverse.hbm.api.energymk2;

import com.miniverse.hbm.util.CompatEnergyControl;
import com.miniverse.hbm.api.tile.ILoadedTile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

/**
 * DO NOT USE DIRECTLY!
 * This is the common ancestor for providers and receivers, since this behavior must be excluded from conductors.
 */
public interface IEnergyHandlerMK2 extends IEnergyConnectorMK2, ILoadedTile {

    long getPower();
    void setPower(long power);
    long getMaxPower();

    boolean particleDebug = false;

    /**
     * Gets the debug particle position for energy visualization.
     *
     * @return A Vec3 representing the debug particle position.
     */
    default Vec3 getDebugParticlePosMK2() {
        BlockEntity blockEntity = (BlockEntity) this;
        return new Vec3(blockEntity.getBlockPos().getX() + 0.5,
                blockEntity.getBlockPos().getY() + 1,
                blockEntity.getBlockPos().getZ() + 0.5);
    }

    /**
     * Provides information for CompatEnergyControl.
     *
     * @param data The NBT data where power information is stored.
     */
    default void provideInfoForECMK2(CompoundTag data) {
        data.putLong(CompatEnergyControl.L_ENERGY_HE, this.getPower());
        data.putLong(CompatEnergyControl.L_CAPACITY_HE, this.getMaxPower());
    }
}
