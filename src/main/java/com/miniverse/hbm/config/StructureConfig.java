package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Locale;

public class StructureConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Structure Generation Settings
    public static final ForgeConfigSpec.IntValue enableStructures;
    public static final ForgeConfigSpec.IntValue structureMinChunks;
    public static final ForgeConfigSpec.IntValue structureMaxChunks;
    public static final ForgeConfigSpec.DoubleValue lootAmountFactor;
    public static final ForgeConfigSpec.BooleanValue debugStructures;

    static {
        BUILDER.comment("Structure Generation Settings").push("structures");

        enableStructures = BUILDER
                .comment("Controls if NTM structures will spawn.",
                        "Valid values:",
                        "0 = Disable",
                        "1 = Enable",
                        "2 = Respect world structure generation flag")
                .defineInRange("enable_structures", 2, 0, 2);

        structureMinChunks = BUILDER
                .comment("Minimum non-zero distance between structures in chunks.",
                        "Settings lower than 8 may cause issues.")
                .defineInRange("structure_min_chunks", 8, 1, Integer.MAX_VALUE);

        structureMaxChunks = BUILDER
                .comment("Maximum non-zero distance between structures in chunks.")
                .defineInRange("structure_max_chunks", 24, 1, Integer.MAX_VALUE);

        lootAmountFactor = BUILDER
                .comment("General factor for loot spawns. Applies to spawned inventories, not loot blocks.")
                .defineInRange("loot_amount_factor", 1.0, 0.0, Double.MAX_VALUE);

        debugStructures = BUILDER
                .comment("If enabled, structure blocks like jigsaw blocks will not be transformed after generating.")
                .define("debug_structures", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void validateConfig() {
        if (structureMinChunks.get() > structureMaxChunks.get()) {
            System.err.println("[StructureConfig] Fatal error: Minimum chunk distance is greater than the maximum!");
            System.err.printf(Locale.US,
                    "Errored values defaulting to %d and %d. Please review configuration settings!\n",
                    8, 24);

            // Reset values to prevent crashes
            structureMinChunks.set(8);
            structureMaxChunks.set(24);
        }
    }
}
