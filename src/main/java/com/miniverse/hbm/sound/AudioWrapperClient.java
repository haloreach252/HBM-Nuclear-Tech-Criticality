package com.miniverse.hbm.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AudioWrapperClient extends AudioWrapper {

    AudioDynamic sound;

    public AudioWrapperClient(ResourceLocation source) {
        if (source != null)
            sound = new AudioDynamic(source);
    }

    @Override
    public void setKeepAlive(int keepAlive) {
        if (sound != null)
            sound.setKeepAlive(keepAlive);
    }

    @Override
    public void keepAlive() {
        if (sound != null)
            sound.keepAlive();
    }

    @Override
    public void updatePosition(float x, float y, float z) {
        if (sound != null)
            sound.setPosition(x, y, z);
    }

    @Override
    public void updateVolume(float volume) {
        if (sound != null)
            sound.setVolumeCustom(volume);
    }

    @Override
    public void updateRange(float range) {
        if (sound != null)
            sound.setRange(range);
    }

    @Override
    public void updatePitch(float pitch) {
        if (sound != null)
            sound.setPitchCustom(pitch);
    }

    @Override
    public float getVolume() {
        if (sound != null)
            return sound.getVolume();
        return 1.0F;
    }

    @Override
    public float getPitch() {
        if (sound != null)
            return sound.getPitch();
        return 1.0F;
    }

    @Override
    public void startSound() {
        if (sound != null)
            sound.start();
    }

    @Override
    public void stopSound() {
        if (sound != null) {
            sound.stop();
            sound.setKeepAlive(0);
        }
    }

    @Override
    public boolean isPlaying() {
        return sound != null && sound.isPlaying();
    }
}
