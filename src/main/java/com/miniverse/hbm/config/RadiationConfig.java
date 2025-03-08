package com.miniverse.hbm.config;

import com.miniverse.hbm.handler.radiation.ChunkRadiationHandlerPRISM;
import com.miniverse.hbm.handler.radiation.ChunkRadiationManager;
import net.minecraftforge.common.ForgeConfigSpec;

public class RadiationConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Radiation Settings
    public static final ForgeConfigSpec.IntValue fogRad;
    public static final ForgeConfigSpec.IntValue fogCh;
    public static final ForgeConfigSpec.DoubleValue hellRad;
    public static final ForgeConfigSpec.IntValue worldRad;
    public static final ForgeConfigSpec.IntValue worldRadThreshold;
    public static final ForgeConfigSpec.BooleanValue worldRadEffects;
    public static final ForgeConfigSpec.BooleanValue cleanupDeadDirt;

    // Radiation Toggles
    public static final ForgeConfigSpec.BooleanValue enableContamination;
    public static final ForgeConfigSpec.BooleanValue enableChunkRads;
    public static final ForgeConfigSpec.BooleanValue enablePRISM;

    // Hazard Settings
    public static final ForgeConfigSpec.BooleanValue disableAsbestos;
    public static final ForgeConfigSpec.BooleanValue disableCoal;
    public static final ForgeConfigSpec.BooleanValue disableHot;
    public static final ForgeConfigSpec.BooleanValue disableExplosive;
    public static final ForgeConfigSpec.BooleanValue disableHydro;
    public static final ForgeConfigSpec.BooleanValue disableBlinding;
    public static final ForgeConfigSpec.BooleanValue disableFibrosis;

    // Pollution Settings
    public static final ForgeConfigSpec.BooleanValue enablePollution;
    public static final ForgeConfigSpec.BooleanValue enableLeadFromBlocks;
    public static final ForgeConfigSpec.BooleanValue enableLeadPoisoning;
    public static final ForgeConfigSpec.BooleanValue enableSootFog;
    public static final ForgeConfigSpec.BooleanValue enablePoison;
    public static final ForgeConfigSpec.DoubleValue buffMobThreshold;
    public static final ForgeConfigSpec.DoubleValue sootFogThreshold;
    public static final ForgeConfigSpec.DoubleValue sootFogDivisor;
    public static final ForgeConfigSpec.DoubleValue smokeStackSootMult;

    static {
        BUILDER.comment("Radiation Settings")
                .push("radiation");

        fogRad = BUILDER.comment("Radiation in RADs required for fog to spawn")
                .defineInRange("fog_threshold", 100, 0, Integer.MAX_VALUE);
        fogCh = BUILDER.comment("1:n chance of fog spawning every second")
                .defineInRange("fog_spawn_chance", 20, 1, Integer.MAX_VALUE);
        hellRad = BUILDER.comment("RAD/s in the nether")
                .defineInRange("ambient_nether_rads", 0.1, 0.0, Double.MAX_VALUE);
        worldRadEffects = BUILDER.comment("Whether high radiation levels should perform changes in the world")
                .define("world_radiation_effects", true);
        worldRad = BUILDER.comment("How many block operations radiation can perform per tick")
                .defineInRange("world_radiation_operations", 10, 0, Integer.MAX_VALUE);
        worldRadThreshold = BUILDER.comment("Minimum RADs required for block modification")
                .defineInRange("world_radiation_threshold", 20, 0, Integer.MAX_VALUE);
        cleanupDeadDirt = BUILDER.comment("Whether dead grass and mycelium should decay into dirt")
                .define("cleanup_dead_dirt", false);

        enableContamination = BUILDER.comment("Toggles player contamination")
                .define("enable_contamination", true);
        enableChunkRads = BUILDER.comment("Toggles world radiation system")
                .define("enable_chunk_radiation", true);
        enablePRISM = BUILDER.comment("Enables the 3D resistance-aware PRISM radiation system")
                .define("enable_PRISM", false);

        BUILDER.pop().comment("Hazard Settings")
                .push("hazards");

        disableAsbestos = BUILDER.define("disable_asbestos", false);
        disableCoal = BUILDER.define("disable_coal_dust", false);
        disableHot = BUILDER.define("disable_hot", false);
        disableExplosive = BUILDER.define("disable_explosive", false);
        disableHydro = BUILDER.define("disable_hydroactive", false);
        disableBlinding = BUILDER.define("disable_blinding", false);
        disableFibrosis = BUILDER.define("disable_fibrosis", false);

        BUILDER.pop().comment("Pollution Settings")
                .push("pollution");

        enablePollution = BUILDER.define("enable_pollution", true);
        enableLeadFromBlocks = BUILDER.define("enable_lead_from_blocks", true);
        enableLeadPoisoning = BUILDER.define("enable_lead_poisoning", true);
        enableSootFog = BUILDER.define("enable_soot_fog", true);
        enablePoison = BUILDER.define("enable_poison", true);
        buffMobThreshold = BUILDER.defineInRange("buff_mob_threshold", 15D, 0D, Double.MAX_VALUE);
        sootFogThreshold = BUILDER.defineInRange("soot_fog_threshold", 35D, 0D, Double.MAX_VALUE);
        sootFogDivisor = BUILDER.defineInRange("soot_fog_divisor", 120D, 1D, Double.MAX_VALUE);
        smokeStackSootMult = BUILDER.defineInRange("smoke_stack_soot_multiplier", 0.8D, 0D, Double.MAX_VALUE);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void onLoad() {
        if (enablePRISM.get()) {
            ChunkRadiationManager.proxy = new ChunkRadiationHandlerPRISM();
        }
    }
}
