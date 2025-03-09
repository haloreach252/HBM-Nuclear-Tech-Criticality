package com.miniverse.hbm.sound;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.resources.sounds.PositionedSoundInstance;
import net.minecraft.client.resources.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class SoundLoopMachine extends PositionedSoundInstance implements TickableSoundInstance {
    private boolean donePlaying = false;
    private final BlockEntity blockEntity;

    public SoundLoopMachine(ResourceLocation soundLocation, BlockEntity blockEntity) {
        super(soundLocation, SoundSource.BLOCKS, 1.0f, 1.0f,
                blockEntity.getBlockPos().getX(),
                blockEntity.getBlockPos().getY(),
                blockEntity.getBlockPos().getZ());
        this.blockEntity = blockEntity;
        this.repeat = true;
        this.repeatDelay = 0;
    }

    // New constructor that allows specifying attenuation
    public SoundLoopMachine(ResourceLocation soundLocation, BlockEntity blockEntity, SoundInstance.Attenuation attenuation) {
        super(soundLocation, SoundSource.BLOCKS, 1.0f, 1.0f,
                blockEntity.getBlockPos().getX(),
                blockEntity.getBlockPos().getY(),
                blockEntity.getBlockPos().getZ(), true, 0, attenuation);
        this.blockEntity = blockEntity;
    }

    @Override
    public void tick() {
        // In 1.20.1, BlockEntity.isRemoved() indicates that the entity is no longer valid.
        if (blockEntity == null || blockEntity.isRemoved()) {
            donePlaying = true;
        }
    }

    @Override
    public boolean isDonePlaying() {
        return donePlaying;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void stopSound() {
        donePlaying = true;
    }
}
