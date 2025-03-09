package com.miniverse.hbm.sound;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MovingSoundChopperMine extends MovingSoundPlayerLoop {

    public MovingSoundChopperMine(ResourceLocation soundLocation, Entity player, EnumHbmSound type) {
        super(soundLocation, player, type);
    }
}
