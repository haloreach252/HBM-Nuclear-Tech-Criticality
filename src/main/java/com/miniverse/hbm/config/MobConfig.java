package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MobConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue enableMaskman;
    public static final ForgeConfigSpec.IntValue maskmanDelay;
    public static final ForgeConfigSpec.IntValue maskmanChance;
    public static final ForgeConfigSpec.IntValue maskmanMinRad;
    public static final ForgeConfigSpec.BooleanValue maskmanUnderground;

    public static final ForgeConfigSpec.BooleanValue enableRaids;
    public static final ForgeConfigSpec.IntValue raidDelay;
    public static final ForgeConfigSpec.IntValue raidChance;
    public static final ForgeConfigSpec.IntValue raidAmount;
    public static final ForgeConfigSpec.IntValue raidDrones;
    public static final ForgeConfigSpec.IntValue raidAttackDelay;
    public static final ForgeConfigSpec.IntValue raidAttackReach;
    public static final ForgeConfigSpec.IntValue raidAttackDistance;

    public static final ForgeConfigSpec.BooleanValue enableElementals;
    public static final ForgeConfigSpec.IntValue elementalDelay;
    public static final ForgeConfigSpec.IntValue elementalChance;
    public static final ForgeConfigSpec.IntValue elementalAmount;
    public static final ForgeConfigSpec.IntValue elementalDistance;

    public static final ForgeConfigSpec.BooleanValue enableDucks;
    public static final ForgeConfigSpec.BooleanValue enableMobGear;
    public static final ForgeConfigSpec.BooleanValue enableMobWeapons;

    public static final ForgeConfigSpec.BooleanValue enableHives;
    public static final ForgeConfigSpec.IntValue hiveSpawn;
    public static final ForgeConfigSpec.DoubleValue scoutThreshold;
    public static final ForgeConfigSpec.IntValue scoutSwarmSpawnChance;
    public static final ForgeConfigSpec.BooleanValue waypointDebug;
    public static final ForgeConfigSpec.IntValue largeHiveChance;
    public static final ForgeConfigSpec.IntValue largeHiveThreshold;

    public static final ForgeConfigSpec.IntValue swarmCooldown;
    public static final ForgeConfigSpec.IntValue baseSwarmSize;
    public static final ForgeConfigSpec.DoubleValue swarmScalingMult;
    public static final ForgeConfigSpec.IntValue sootStep;

    public static final ForgeConfigSpec.BooleanValue enableInfestation;
    public static final ForgeConfigSpec.DoubleValue baseInfestChance;
    public static final ForgeConfigSpec.DoubleValue spawnMax;
    public static final ForgeConfigSpec.DoubleValue targetingThreshold;

    public static final ForgeConfigSpec.BooleanValue rampantMode;
    public static final ForgeConfigSpec.BooleanValue rampantNaturalScoutSpawn;
    public static final ForgeConfigSpec.DoubleValue rampantScoutSpawnThresh;
    public static final ForgeConfigSpec.IntValue rampantScoutSpawnChance;
    public static final ForgeConfigSpec.BooleanValue rampantExtendedTargetting;
    public static final ForgeConfigSpec.BooleanValue rampantDig;
    public static final ForgeConfigSpec.BooleanValue rampantGlyphidGuidance;
    public static final ForgeConfigSpec.DoubleValue rampantSmokeStackOverride;
    public static final ForgeConfigSpec.DoubleValue pollutionMult;

    static {
        BUILDER.comment("Maskman Settings").push("maskman");
        enableMaskman = BUILDER.define("enableMaskman", true);
        maskmanDelay = BUILDER.defineInRange("maskmanDelay", 60 * 60 * 60, 1, Integer.MAX_VALUE);
        maskmanChance = BUILDER.defineInRange("maskmanChance", 3, 1, Integer.MAX_VALUE);
        maskmanMinRad = BUILDER.defineInRange("maskmanMinRad", 50, 0, Integer.MAX_VALUE);
        maskmanUnderground = BUILDER.define("maskmanUnderground", true);
        BUILDER.pop();

        BUILDER.comment("FBI Raid Settings").push("raid");
        enableRaids = BUILDER.define("enableRaids", false);
        raidDelay = BUILDER.defineInRange("raidDelay", 30 * 60 * 60, 1, Integer.MAX_VALUE);
        raidChance = BUILDER.defineInRange("raidChance", 3, 1, Integer.MAX_VALUE);
        raidAmount = BUILDER.defineInRange("raidAmount", 15, 1, Integer.MAX_VALUE);
        raidDrones = BUILDER.defineInRange("raidDrones", 5, 0, Integer.MAX_VALUE);
        raidAttackDelay = BUILDER.defineInRange("raidAttackDelay", 40, 1, Integer.MAX_VALUE);
        raidAttackReach = BUILDER.defineInRange("raidAttackReach", 2, 1, Integer.MAX_VALUE);
        raidAttackDistance = BUILDER.defineInRange("raidAttackDistance", 32, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Elemental Settings").push("elementals");
        enableElementals = BUILDER.define("enableElementals", true);
        elementalDelay = BUILDER.defineInRange("elementalDelay", 30 * 60 * 60, 1, Integer.MAX_VALUE);
        elementalChance = BUILDER.defineInRange("elementalChance", 2, 1, Integer.MAX_VALUE);
        elementalAmount = BUILDER.defineInRange("elementalAmount", 10, 1, Integer.MAX_VALUE);
        elementalDistance = BUILDER.defineInRange("elementalDistance", 32, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Misc Mob Settings").push("misc_mobs");
        enableDucks = BUILDER.define("enableDucks", true);
        enableMobGear = BUILDER.define("enableMobGear", true);
        enableMobWeapons = BUILDER.define("enableMobWeapons", true);
        BUILDER.pop();

        BUILDER.comment("Glyphid Hive Settings").push("hives");
        enableHives = BUILDER.define("enableHives", true);
        hiveSpawn = BUILDER.defineInRange("hiveSpawn", 256, 1, Integer.MAX_VALUE);
        scoutThreshold = BUILDER.defineInRange("scoutThreshold", 5.0, 0.0, Double.MAX_VALUE);
        scoutSwarmSpawnChance = BUILDER.defineInRange("scoutSwarmSpawnChance", 2, 1, Integer.MAX_VALUE);
        waypointDebug = BUILDER.define("waypointDebug", false);
        largeHiveChance = BUILDER.defineInRange("largeHiveChance", 5, 1, Integer.MAX_VALUE);
        largeHiveThreshold = BUILDER.defineInRange("largeHiveThreshold", 30, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Swarm Settings").push("swarm");
        swarmCooldown = BUILDER.defineInRange("swarmCooldown", 120 * 20, 1, Integer.MAX_VALUE);
        baseSwarmSize = BUILDER.defineInRange("baseSwarmSize", 5, 1, Integer.MAX_VALUE);
        swarmScalingMult = BUILDER.defineInRange("swarmScalingMult", 1.2, 0.1, Double.MAX_VALUE);
        sootStep = BUILDER.defineInRange("sootStep", 50, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Infestation Settings").push("infestation");
        enableInfestation = BUILDER.define("enableInfestation", true);
        baseInfestChance = BUILDER.defineInRange("baseInfestChance", 5.0, 0.0, Double.MAX_VALUE);
        spawnMax = BUILDER.defineInRange("spawnMax", 50.0, 1.0, Double.MAX_VALUE);
        targetingThreshold = BUILDER.defineInRange("targetingThreshold", 1.0, 0.0, Double.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Rampant Mode Settings").push("rampant");
        rampantMode = BUILDER.define("rampantMode", false);
        rampantNaturalScoutSpawn = BUILDER.define("rampantNaturalScoutSpawn", false);
        rampantScoutSpawnThresh = BUILDER.defineInRange("rampantScoutSpawnThresh", 14.0, 0.0, Double.MAX_VALUE);
        rampantScoutSpawnChance = BUILDER.defineInRange("rampantScoutSpawnChance", 1400, 1, Integer.MAX_VALUE);
        rampantExtendedTargetting = BUILDER.define("rampantExtendedTargetting", false);
        rampantDig = BUILDER.define("rampantDig", false);
        rampantGlyphidGuidance = BUILDER.define("rampantGlyphidGuidance", false);
        rampantSmokeStackOverride = BUILDER.defineInRange("rampantSmokeStackOverride", 0.4, 0.0, Double.MAX_VALUE);
        pollutionMult = BUILDER.defineInRange("pollutionMult", 3.0, 0.1, Double.MAX_VALUE);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
