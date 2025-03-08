package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Locale;

public class PotionConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Potion IDs
    public static final ForgeConfigSpec.IntValue taintID;
    public static final ForgeConfigSpec.IntValue radiationID;
    public static final ForgeConfigSpec.IntValue bangID;
    public static final ForgeConfigSpec.IntValue mutationID;
    public static final ForgeConfigSpec.IntValue radxID;
    public static final ForgeConfigSpec.IntValue leadID;
    public static final ForgeConfigSpec.IntValue radawayID;
    public static final ForgeConfigSpec.IntValue telekinesisID;
    public static final ForgeConfigSpec.IntValue phosphorusID;
    public static final ForgeConfigSpec.IntValue stabilityID;
    public static final ForgeConfigSpec.IntValue potionsicknessID;
    public static final ForgeConfigSpec.IntValue deathID;

    // Potion sickness mode
    public static final ForgeConfigSpec.EnumValue<PotionSicknessMode> potionSickness;

    public enum PotionSicknessMode {
        OFF, NORMAL, TERRARIA
    }

    static {
        BUILDER.push("Potion Effects");

        taintID = BUILDER.comment("Potion ID for the Taint effect.")
                .defineInRange("taintID", 62, 1, 255);
        radiationID = BUILDER.comment("Potion ID for the Radiation effect.")
                .defineInRange("radiationID", 63, 1, 255);
        bangID = BUILDER.comment("Potion ID for the B93 Timebomb effect.")
                .defineInRange("bangID", 64, 1, 255);
        mutationID = BUILDER.comment("Potion ID for the Mutation effect.")
                .defineInRange("mutationID", 65, 1, 255);
        radxID = BUILDER.comment("Potion ID for the Rad-X effect.")
                .defineInRange("radxID", 66, 1, 255);
        leadID = BUILDER.comment("Potion ID for the Lead Poisoning effect.")
                .defineInRange("leadID", 67, 1, 255);
        radawayID = BUILDER.comment("Potion ID for the RadAway effect.")
                .defineInRange("radawayID", 68, 1, 255);
        telekinesisID = BUILDER.comment("Potion ID for the Telekinesis effect.")
                .defineInRange("telekinesisID", 69, 1, 255);
        phosphorusID = BUILDER.comment("Potion ID for the Phosphorus effect.")
                .defineInRange("phosphorusID", 70, 1, 255);
        stabilityID = BUILDER.comment("Potion ID for the Stability effect.")
                .defineInRange("stabilityID", 71, 1, 255);
        potionsicknessID = BUILDER.comment("Potion ID for the Potion Sickness effect.")
                .defineInRange("potionsicknessID", 72, 1, 255);
        deathID = BUILDER.comment("Potion ID for the Death effect.")
                .defineInRange("deathID", 73, 1, 255);

        potionSickness = BUILDER.comment("Potion Sickness Mode. Options: OFF, NORMAL, TERRARIA.")
                .defineEnum("potionSickness", PotionSicknessMode.OFF);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
