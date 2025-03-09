package com.miniverse.hbm.sound;

import java.util.ArrayList;
import java.util.List;

import com.miniverse.hbm.items.machine.ItemCassette.SoundType;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineSiren;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SoundLoopSiren extends SoundLoopMachine {

    public static List<SoundLoopSiren> list = new ArrayList<>();
    public float intendedVolume;
    public SoundType type;

    public SoundLoopSiren(ResourceLocation path, BlockEntity blockEntity, SoundType type) {
        // Use the constructor that forces no attenuation
        super(path, blockEntity, SoundInstance.Attenuation.NONE);
        list.add(this);
        this.intendedVolume = 10.0F;
        // The attenuation is already set to NONE via the constructor.
        this.type = type;
    }

    @Override
    public void tick() {
        super.tick();

        LocalPlayer player = Minecraft.getInstance().player;
        float distance = 0;

        if(player != null) {
            double dx = this.x - player.getX();
            double dy = this.y - player.getY();
            double dz = this.z - player.getZ();
            distance = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
            this.volume = func(distance, intendedVolume);
        } else {
            this.volume = intendedVolume;
        }

        // Check if the associated blockEntity is an instance of the expected siren BlockEntity.
        if(blockEntity instanceof TileEntityMachineSiren) {
            // Set repeating based on whether the sound type is LOOP.
            this.setRepeat(type.name().equals(SoundType.LOOP.name()));
        } else {
            this.donePlaying = true;
        }
    }

    // Returns the associated BlockEntity (formerly TileEntity).
    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public void endSound() {
        this.donePlaying = true;
    }

    public String getPath() {
        // Assuming the super class stores the sound ResourceLocation in the field "sound"
        return this.sound.getNamespace() + ":" + this.sound.getPath();
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void setRepeatDelay(int delay) {
        this.repeatDelay = delay;
    }

    // A helper function that computes volume based on the distance.
    public float func(float distance, float maxVolume) {
        return (distance / maxVolume) * -2 + 2;
    }
}
