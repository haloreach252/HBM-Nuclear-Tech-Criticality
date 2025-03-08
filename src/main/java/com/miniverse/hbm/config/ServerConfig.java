package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ServerConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Config values
    public static final ForgeConfigSpec.BooleanValue DAMAGE_COMPATIBILITY_MODE;
    public static final ForgeConfigSpec.DoubleValue MINE_AP_DAMAGE;
    public static final ForgeConfigSpec.DoubleValue MINE_HE_DAMAGE;
    public static final ForgeConfigSpec.DoubleValue MINE_SHRAP_DAMAGE;
    public static final ForgeConfigSpec.DoubleValue MINE_NUKE_DAMAGE;
    public static final ForgeConfigSpec.BooleanValue TAINT_TRAILS;

    static {
        BUILDER.comment("Hbm's Nuclear Tech Server Config").push("server");

        DAMAGE_COMPATIBILITY_MODE = BUILDER
                .comment("Enable compatibility mode for damage calculations.")
                .define("damage_compatibility_mode", false);

        MINE_AP_DAMAGE = BUILDER
                .comment("Armor-piercing mine damage.")
                .defineInRange("mine_ap_damage", 10.0, 0.0, Double.MAX_VALUE);

        MINE_HE_DAMAGE = BUILDER
                .comment("High-explosive mine damage.")
                .defineInRange("mine_he_damage", 35.0, 0.0, Double.MAX_VALUE);

        MINE_SHRAP_DAMAGE = BUILDER
                .comment("Shrapnel mine damage.")
                .defineInRange("mine_shrap_damage", 7.5, 0.0, Double.MAX_VALUE);

        MINE_NUKE_DAMAGE = BUILDER
                .comment("Nuclear mine damage.")
                .defineInRange("mine_nuke_damage", 100.0, 0.0, Double.MAX_VALUE);

        TAINT_TRAILS = BUILDER
                .comment("Enable taint trails effect.")
                .define("taint_trails", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
