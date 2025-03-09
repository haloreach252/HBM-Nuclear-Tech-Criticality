package com.miniverse.hbm.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AudioDynamic extends AbstractTickableSoundInstance {

    public float maxVolume = 1.0F;
    public float range;
    public int keepAlive;
    public int timeSinceKA;
    public boolean shouldExpire = false;

    /**
     * Constructs a dynamic sound with the given sound event resource.
     * Assumes that the ResourceLocation corresponds to a registered SoundEvent.
     */
    protected AudioDynamic(ResourceLocation loc) {
        // The superclass constructor expects a SoundEvent and a SoundSource.
        // It is assumed that your mod registers a SoundEvent corresponding to 'loc'.
        super(loc, SoundSource.MASTER);
        this.looping = true;
        this.attenuation = SoundInstance.Attenuation.NONE;
        this.range = 10.0F;
    }

    /**
     * Sets the sound’s position.
     */
    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Tick method called every game tick.
     * Updates volume based on the distance from the local player and handles expiration.
     */
    @Override
    public void tick() {
        if (Minecraft.getInstance().player != null) {
            double dx = this.x - Minecraft.getInstance().player.getX();
            double dy = this.y - Minecraft.getInstance().player.getY();
            double dz = this.z - Minecraft.getInstance().player.getZ();
            float dist = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
            this.volume = func(dist);
        } else {
            this.volume = maxVolume;
        }

        if (this.shouldExpire) {
            if (this.timeSinceKA > this.keepAlive) {
                this.stop();
            }
            this.timeSinceKA++;
        }
    }

    /**
     * Starts playing this sound by adding it to the sound manager.
     */
    public void start() {
        Minecraft.getInstance().getSoundManager().play(this);
    }

    /**
     * Stops this sound via the sound manager.
     */
    public void stop() {
        Minecraft.getInstance().getSoundManager().stop(this);
    }

    /**
     * Sets the maximum volume for this dynamic sound.
     */
    public void setVolumeCustom(float volume) {
        this.maxVolume = volume;
    }

    /**
     * Sets the effective range for the sound’s volume attenuation.
     */
    public void setRange(float range) {
        this.range = range;
    }

    /**
     * Sets the keep-alive duration in ticks and marks the sound for expiration.
     */
    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
        this.shouldExpire = true;
    }

    /**
     * Resets the expiration timer.
     */
    public void keepAlive() {
        this.timeSinceKA = 0;
    }

    /**
     * Sets the sound’s pitch.
     */
    public void setPitchCustom(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Calculates the volume based on distance.
     * The formula linearly scales the volume from maxVolume at 0 distance down to 0 at 'range'.
     */
    public float func(float dist) {
        return (dist / range) * -maxVolume + maxVolume;
    }

    /**
     * Checks if this sound is currently active.
     */
    public boolean isPlaying() {
        return Minecraft.getInstance().getSoundManager().isActive(this);
    }
}
