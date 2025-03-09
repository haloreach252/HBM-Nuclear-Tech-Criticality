package com.miniverse.hbm.sound;

import com.miniverse.hbm.entity.mob.EntityHunterChopper;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MovingSoundCrashing extends MovingSoundPlayerLoop {

    public MovingSoundCrashing(ResourceLocation soundLocation, Entity player, EnumHbmSound type) {
        super(soundLocation, player, type);
    }

    @Override
    public void update() {
        super.update();

        // If the associated entity is a HunterChopper and is not dying, stop the sound.
        if (player instanceof EntityHunterChopper && !((EntityHunterChopper) player).getIsDying()) {
            this.stop();
        }
    }
}
