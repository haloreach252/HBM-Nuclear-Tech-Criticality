package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;
import java.util.Locale;

public class CommonConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // General Settings
    public static final ForgeConfigSpec.BooleanValue enableLessBullshitMode;

    // Nukes
    public static final ForgeConfigSpec.IntValue bombRandomizerValue;

    // Radiation
    public static final ForgeConfigSpec.DoubleValue radiationDecayRate;

    // Machines
    public static final ForgeConfigSpec.BooleanValue enableAdvancedMachines;

    // Structures
    public static final ForgeConfigSpec.BooleanValue enableGeneratedStructures;

    static {
        BUILDER.comment("General Settings").push("general");

        enableLessBullshitMode = BUILDER.comment("Enable less bullshit mode")
                .define("lessBullshitMode", false);

        BUILDER.pop().comment("Nukes & Explosions").push("nukes");

        bombRandomizerValue = BUILDER.comment("Defines a randomizer value for bombs")
                .defineInRange("bombRandomizer", 1, 0, 100);

        BUILDER.pop().comment("Radiation Settings").push("radiation");

        radiationDecayRate = BUILDER.comment("Rate at which radiation decays")
                .defineInRange("radiationDecayRate", 0.95, 0.01, 1.0);

        BUILDER.pop().comment("Machine Settings").push("machines");

        enableAdvancedMachines = BUILDER.comment("Enable advanced machines")
                .define("enableAdvancedMachines", true);

        BUILDER.pop().comment("Structure Generation").push("structures");

        enableGeneratedStructures = BUILDER.comment("Enable generated structures")
                .define("enableGeneratedStructures", true);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    /**
     * Ensures values remain within valid ranges.
     */
    public static int setDefZero(int value, int def) {
        if (value < 0) {
            System.err.println(String.format(Locale.US,
                    "Error: Value below zero. Defaulting to %d", def));
            return def;
        }
        return value;
    }

    public static int setDef(int value, int def) {
        if (value <= 0) {
            System.err.println(String.format(Locale.US,
                    "Error: Value must be positive. Defaulting to %d", def));
            return def;
        }
        return value;
    }
}
