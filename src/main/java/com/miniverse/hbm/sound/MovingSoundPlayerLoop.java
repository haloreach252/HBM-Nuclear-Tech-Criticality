package com.miniverse.hbm.sound;

import net.minecraft.client.sounds.AbstractTickableSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@OnlyIn(Dist.CLIENT)
public abstract class MovingSoundPlayerLoop extends AbstractTickableSoundInstance {

    public static List<MovingSoundPlayerLoop> globalSoundList = new ArrayList<>();
    public List<Entity> playerForSound = new ArrayList<>();
    public Entity player;
    public enum EnumHbmSound { soundTauLoop, soundChopperLoop, soundCrashingLoop, soundMineLoop }
    public EnumHbmSound type;
    public boolean init;

    public MovingSoundPlayerLoop(ResourceLocation res, Entity player, EnumHbmSound type) {
        super(res, SoundSource.MASTER);
        this.player = player;
        this.type = type;
        this.init = false;
        this.looping = true;
        if (getSoundByPlayer(player, type) == null)
            globalSoundList.add(this);
    }

    @Override
    public void tick() {
        if (player != null) {
            this.x = player.getX();
            this.y = player.getY();
            this.z = player.getZ();
        }
        if (player == null || !player.isAlive())
            this.stop();
    }

    @Override
    public void stop() {
        this.donePlaying = true;
        this.looping = false;
        // Remove any sound instance matching this player and type
        while (getSoundByPlayer(player, type) != null)
            globalSoundList.remove(getSoundByPlayer(player, type));
        this.player = null;
    }

    public static MovingSoundPlayerLoop getSoundByPlayer(Entity player, EnumHbmSound type) {
        for (MovingSoundPlayerLoop sound : globalSoundList) {
            if (sound.player == player && sound.type == type)
                return sound;
        }
        return null;
    }

    public void setPitchCustom(float f) {
        this.pitch = f;
    }

    public void setVolumeCustom(float f) {
        this.volume = f;
    }

    public void setDone(boolean b) {
        this.donePlaying = b;
    }
}
