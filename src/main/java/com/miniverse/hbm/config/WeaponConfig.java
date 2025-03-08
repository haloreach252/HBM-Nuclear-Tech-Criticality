package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class WeaponConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Missile Settings
    public static final ForgeConfigSpec.IntValue radarRange;
    public static final ForgeConfigSpec.IntValue radarBuffer;
    public static final ForgeConfigSpec.IntValue radarAltitude;
    public static final ForgeConfigSpec.IntValue ciwsHitrate;

    // Item Drop Settings
    public static final ForgeConfigSpec.BooleanValue dropCell;
    public static final ForgeConfigSpec.BooleanValue dropSing;
    public static final ForgeConfigSpec.BooleanValue dropStar;
    public static final ForgeConfigSpec.BooleanValue dropCrys;
    public static final ForgeConfigSpec.BooleanValue dropDead;

    // Weapon Animation Settings
    public static final ForgeConfigSpec.BooleanValue linearAnimations;

    static {
        BUILDER.comment("Missile Radar & Defense Settings").push("missiles");

        radarRange = BUILDER
                .comment("Range of the radar. A value of 50 results in a 100x100 block coverage.")
                .defineInRange("radar_range", 1000, 50, 10000);

        radarBuffer = BUILDER
                .comment("How high entities must be above the radar to be detected.")
                .defineInRange("radar_buffer", 30, 0, 256);

        radarAltitude = BUILDER
                .comment("Minimum Y height required for the radar to work.")
                .defineInRange("radar_altitude", 55, 0, 256);

        ciwsHitrate = BUILDER
                .comment("Additional modifier for CIWS accuracy (higher values increase accuracy).")
                .defineInRange("ciws_accuracy", 50, 0, 100);

        BUILDER.pop().comment("Item Drop Behavior").push("drops");

        dropCell = BUILDER
                .comment("Should antimatter cells explode when dropped?")
                .define("drop_antimatter_cell", true);

        dropSing = BUILDER
                .comment("Should singularities and black holes spawn when dropped?")
                .define("drop_blackhole", true);

        dropStar = BUILDER
                .comment("Should rigged star blaster cells explode when dropped?")
                .define("drop_star_blaster", true);

        dropCrys = BUILDER
                .comment("Should xen crystals move blocks when dropped?")
                .define("drop_xen_crystals", true);

        dropDead = BUILDER
                .comment("Should dead man's explosives explode when dropped?")
                .define("drop_dead_mans_explosives", true);

        BUILDER.pop().comment("Weapon Animation Settings").push("weapons");

        linearAnimations = BUILDER
                .comment("Should stylized weapon animations be replaced with more conventional ones?")
                .define("linear_weapon_animations", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
