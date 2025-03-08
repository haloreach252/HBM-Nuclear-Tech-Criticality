package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BombConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Bomb explosion radii
    public static final ForgeConfigSpec.IntValue gadgetRadius;
    public static final ForgeConfigSpec.IntValue boyRadius;
    public static final ForgeConfigSpec.IntValue manRadius;
    public static final ForgeConfigSpec.IntValue mikeRadius;
    public static final ForgeConfigSpec.IntValue tsarRadius;
    public static final ForgeConfigSpec.IntValue prototypeRadius;
    public static final ForgeConfigSpec.IntValue fleijaRadius;
    public static final ForgeConfigSpec.IntValue soliniumRadius;
    public static final ForgeConfigSpec.IntValue n2Radius;
    public static final ForgeConfigSpec.IntValue missileRadius;
    public static final ForgeConfigSpec.IntValue mirvRadius;
    public static final ForgeConfigSpec.IntValue fatmanRadius;
    public static final ForgeConfigSpec.IntValue nukaRadius;
    public static final ForgeConfigSpec.IntValue aSchrabRadius;

    // Explosion parameters
    public static final ForgeConfigSpec.IntValue mk5;
    public static final ForgeConfigSpec.IntValue blastSpeed;
    public static final ForgeConfigSpec.IntValue falloutRange;
    public static final ForgeConfigSpec.IntValue falloutDelay;
    public static final ForgeConfigSpec.IntValue limitExplosionLifespan;
    public static final ForgeConfigSpec.IntValue rainDuration;
    public static final ForgeConfigSpec.IntValue falloutRainRadiation;

    public static final ForgeConfigSpec.BooleanValue enableChunkLoading;

    static {
        BUILDER.comment("Bomb Explosion Settings").push("nukes");

        gadgetRadius = BUILDER.comment("Radius of the Gadget")
                .defineInRange("gadgetRadius", 150, 0, 10000);

        boyRadius = BUILDER.comment("Radius of Little Boy")
                .defineInRange("boyRadius", 120, 0, 10000);

        manRadius = BUILDER.comment("Radius of Fat Man")
                .defineInRange("manRadius", 175, 0, 10000);

        mikeRadius = BUILDER.comment("Radius of Ivy Mike")
                .defineInRange("mikeRadius", 250, 0, 10000);

        tsarRadius = BUILDER.comment("Radius of the Tsar Bomba")
                .defineInRange("tsarRadius", 500, 0, 10000);

        prototypeRadius = BUILDER.comment("Radius of the Prototype")
                .defineInRange("prototypeRadius", 150, 0, 10000);

        fleijaRadius = BUILDER.comment("Radius of F.L.E.I.J.A.")
                .defineInRange("fleijaRadius", 50, 0, 10000);

        soliniumRadius = BUILDER.comment("Radius of the blue rinse")
                .defineInRange("soliniumRadius", 150, 0, 10000);

        n2Radius = BUILDER.comment("Radius of the N2 mine")
                .defineInRange("n2Radius", 200, 0, 10000);

        missileRadius = BUILDER.comment("Radius of the nuclear missile")
                .defineInRange("missileRadius", 100, 0, 10000);

        mirvRadius = BUILDER.comment("Radius of a MIRV")
                .defineInRange("mirvRadius", 100, 0, 10000);

        fatmanRadius = BUILDER.comment("Radius of the Fatman Launcher")
                .defineInRange("fatmanRadius", 35, 0, 10000);

        nukaRadius = BUILDER.comment("Radius of the nuka grenade")
                .defineInRange("nukaRadius", 25, 0, 10000);

        aSchrabRadius = BUILDER.comment("Radius of dropped anti schrabidium")
                .defineInRange("aSchrabRadius", 20, 0, 10000);

        BUILDER.pop().comment("Explosion Physics").push("explosions");

        mk5 = BUILDER.comment("Minimum amount of milliseconds per tick allocated for mk5 chunk processing")
                .defineInRange("mk5", 50, 0, 1000);

        blastSpeed = BUILDER.comment("Base speed of MK3 system detonations (Blocks/tick)")
                .defineInRange("blastSpeed", 1024, 1, Integer.MAX_VALUE);

        falloutRange = BUILDER.comment("Radius of fallout area (base radius * value in percent)")
                .defineInRange("falloutRange", 100, 0, 1000);

        falloutDelay = BUILDER.comment("How many ticks to wait for the next fallout chunk computation")
                .defineInRange("falloutDelay", 4, 1, 100);

        limitExplosionLifespan = BUILDER.comment("How long an explosion can be unloaded until it dies (seconds). 0 disables this.")
                .defineInRange("limitExplosionLifespan", 0, 0, Integer.MAX_VALUE);

        rainDuration = BUILDER.comment("Duration of the thunderstorm after fallout in ticks (only large explosions)")
                .defineInRange("rainDuration", 0, 0, 100000);

        falloutRainRadiation = BUILDER.comment("Radiation in 100th RADs created by fallout rain")
                .defineInRange("falloutRainRadiation", 0, 0, 1000);

        enableChunkLoading = BUILDER.comment("Allows all types of procedural explosions to keep the central chunk loaded.")
                .define("enableChunkLoading", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
