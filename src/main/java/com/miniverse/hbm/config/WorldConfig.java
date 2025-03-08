package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WorldConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Ore Generation Settings
    public static final ForgeConfigSpec.BooleanValue overworldOre;
    public static final ForgeConfigSpec.BooleanValue netherOre;
    public static final ForgeConfigSpec.BooleanValue endOre;

    public static final ForgeConfigSpec.IntValue uraniumSpawn;
    public static final ForgeConfigSpec.IntValue thoriumSpawn;
    public static final ForgeConfigSpec.IntValue titaniumSpawn;
    public static final ForgeConfigSpec.IntValue sulfurSpawn;
    public static final ForgeConfigSpec.IntValue aluminiumSpawn;
    public static final ForgeConfigSpec.IntValue copperSpawn;
    public static final ForgeConfigSpec.IntValue fluoriteSpawn;
    public static final ForgeConfigSpec.IntValue niterSpawn;
    public static final ForgeConfigSpec.IntValue tungstenSpawn;
    public static final ForgeConfigSpec.IntValue leadSpawn;
    public static final ForgeConfigSpec.IntValue berylliumSpawn;
    public static final ForgeConfigSpec.IntValue ligniteSpawn;
    public static final ForgeConfigSpec.IntValue asbestosSpawn;
    public static final ForgeConfigSpec.IntValue rareSpawn;
    public static final ForgeConfigSpec.IntValue lithiumSpawn;
    public static final ForgeConfigSpec.IntValue cinnebarSpawn;
    public static final ForgeConfigSpec.IntValue gassshaleSpawn;
    public static final ForgeConfigSpec.IntValue gasbubbleSpawn;
    public static final ForgeConfigSpec.IntValue explosivebubbleSpawn;
    public static final ForgeConfigSpec.IntValue cobaltSpawn;
    public static final ForgeConfigSpec.IntValue oilSpawn;
    public static final ForgeConfigSpec.IntValue bedrockOilSpawn;
    public static final ForgeConfigSpec.IntValue meteoriteSpawn;

    public static final ForgeConfigSpec.BooleanValue newBedrockOres;
    public static final ForgeConfigSpec.IntValue bedrockIronSpawn;
    public static final ForgeConfigSpec.IntValue bedrockCopperSpawn;
    public static final ForgeConfigSpec.IntValue bedrockBoraxSpawn;
    public static final ForgeConfigSpec.IntValue bedrockChlorocalciteSpawn;
    public static final ForgeConfigSpec.IntValue bedrockAsbestosSpawn;
    public static final ForgeConfigSpec.IntValue bedrockNiobiumSpawn;
    public static final ForgeConfigSpec.IntValue bedrockNeodymiumSpawn;
    public static final ForgeConfigSpec.IntValue bedrockTitaniumSpawn;
    public static final ForgeConfigSpec.IntValue bedrockTungstenSpawn;
    public static final ForgeConfigSpec.IntValue bedrockGoldSpawn;
    public static final ForgeConfigSpec.IntValue bedrockUraniumSpawn;
    public static final ForgeConfigSpec.IntValue bedrockThoriumSpawn;
    public static final ForgeConfigSpec.IntValue bedrockCoalSpawn;
    public static final ForgeConfigSpec.IntValue bedrockNiterSpawn;
    public static final ForgeConfigSpec.IntValue bedrockFluoriteSpawn;
    public static final ForgeConfigSpec.IntValue bedrockRedstoneSpawn;
    public static final ForgeConfigSpec.IntValue bedrockRareEarthSpawn;
    public static final ForgeConfigSpec.IntValue bedrockBauxiteSpawn;
    public static final ForgeConfigSpec.IntValue bedrockEmeraldSpawn;
    public static final ForgeConfigSpec.IntValue bedrockGlowstoneSpawn;
    public static final ForgeConfigSpec.IntValue bedrockPhosphorusSpawn;
    public static final ForgeConfigSpec.IntValue bedrockQuartzSpawn;

    public static final ForgeConfigSpec.IntValue ironClusterSpawn;
    public static final ForgeConfigSpec.IntValue titaniumClusterSpawn;
    public static final ForgeConfigSpec.IntValue aluminiumClusterSpawn;
    public static final ForgeConfigSpec.IntValue copperClusterSpawn;
    public static final ForgeConfigSpec.IntValue alexandriteSpawn;

    public static final ForgeConfigSpec.IntValue limestoneSpawn;

    public static final ForgeConfigSpec.IntValue netherUraniumSpawn;
    public static final ForgeConfigSpec.IntValue netherTungstenSpawn;
    public static final ForgeConfigSpec.IntValue netherSulfurSpawn;
    public static final ForgeConfigSpec.IntValue netherPhosphorusSpawn;
    public static final ForgeConfigSpec.IntValue netherCoalSpawn;
    public static final ForgeConfigSpec.IntValue netherPlutoniumSpawn;
    public static final ForgeConfigSpec.IntValue netherCobaltSpawn;

    public static final ForgeConfigSpec.IntValue endTikiteSpawn;

    public static final ForgeConfigSpec.BooleanValue enableHematite;
    public static final ForgeConfigSpec.BooleanValue enableMalachite;
    public static final ForgeConfigSpec.BooleanValue enableBauxite;

    public static final ForgeConfigSpec.BooleanValue enableSulfurCave;
    public static final ForgeConfigSpec.BooleanValue enableAsbestosCave;

    public static final ForgeConfigSpec.IntValue radioStructure;
    public static final ForgeConfigSpec.IntValue antennaStructure;
    public static final ForgeConfigSpec.IntValue atomStructure;
    public static final ForgeConfigSpec.IntValue dungeonStructure;
    public static final ForgeConfigSpec.IntValue relayStructure;
    public static final ForgeConfigSpec.IntValue satelliteStructure;
    public static final ForgeConfigSpec.IntValue factoryStructure;
    public static final ForgeConfigSpec.IntValue dudStructure;
    public static final ForgeConfigSpec.IntValue spaceshipStructure;
    public static final ForgeConfigSpec.IntValue barrelStructure;
    public static final ForgeConfigSpec.IntValue geyserWater;
    public static final ForgeConfigSpec.IntValue geyserChlorine;
    public static final ForgeConfigSpec.IntValue geyserVapor;
    public static final ForgeConfigSpec.IntValue capsuleStructure;
    public static final ForgeConfigSpec.IntValue arcticStructure;
    public static final ForgeConfigSpec.IntValue jungleStructure;
    public static final ForgeConfigSpec.IntValue pyramidStructure;

    public static final ForgeConfigSpec.IntValue broadcaster;
    public static final ForgeConfigSpec.IntValue minefreq;
    public static final ForgeConfigSpec.IntValue radfreq;
    public static final ForgeConfigSpec.IntValue vaultfreq;

    public static final ForgeConfigSpec.BooleanValue enableMeteorStrikes;
    public static final ForgeConfigSpec.BooleanValue enableMeteorShowers;
    public static final ForgeConfigSpec.BooleanValue enableMeteorTails;
    public static final ForgeConfigSpec.BooleanValue enableSpecialMeteors;
    public static final ForgeConfigSpec.IntValue meteorStrikeChance;
    public static final ForgeConfigSpec.IntValue meteorShowerChance;
    public static final ForgeConfigSpec.IntValue meteorShowerDuration;

    public static final ForgeConfigSpec.BooleanValue enableCraterBiomes;
    public static final ForgeConfigSpec.IntValue craterBiomeId;
    public static final ForgeConfigSpec.IntValue craterBiomeInnerId;
    public static final ForgeConfigSpec.IntValue craterBiomeOuterId;
    public static final ForgeConfigSpec.DoubleValue craterBiomeRad;
    public static final ForgeConfigSpec.DoubleValue craterBiomeInnerRad;
    public static final ForgeConfigSpec.DoubleValue craterBiomeOuterRad;
    public static final ForgeConfigSpec.DoubleValue craterBiomeWaterMult;

    static {
        BUILDER.comment("Ore Generation Settings").push("oregen");

        overworldOre = BUILDER
                .comment("General switch for whether overworld ores should be generated. Does not include special structures like oil.")
                .define("overworldOre", true);
        netherOre = BUILDER
                .comment("General switch for whether Nether ores should be generated.")
                .define("netherOre", true);
        endOre = BUILDER
                .comment("General switch for whether End ores should be generated. Does not include special structures like trixite crystals.")
                .define("endOre", true);

        uraniumSpawn = BUILDER
                .comment("Amount of uranium ore veins per chunk.")
                .defineInRange("uraniumSpawn", 6, 0, 64);
        thoriumSpawn = BUILDER
                .comment("Amount of thorium ore veins per chunk.")
                .defineInRange("thoriumSpawn", 7, 0, 64);
        titaniumSpawn = BUILDER
                .comment("Amount of titanium ore veins per chunk.")
                .defineInRange("titaniumSpawn", 8, 0, 64);
        sulfurSpawn = BUILDER
                .comment("Amount of sulfur ore veins per chunk.")
                .defineInRange("sulfurSpawn", 5, 0, 64);
        aluminiumSpawn = BUILDER
                .comment("Amount of aluminium ore veins per chunk.")
                .defineInRange("aluminiumSpawn", 7, 0, 64);
        copperSpawn = BUILDER
                .comment("Amount of copper ore veins per chunk.")
                .defineInRange("copperSpawn", 12, 0, 64);
        fluoriteSpawn = BUILDER
                .comment("Amount of fluorite ore veins per chunk.")
                .defineInRange("fluoriteSpawn", 6, 0, 64);
        niterSpawn = BUILDER
                .comment("Amount of niter ore veins per chunk.")
                .defineInRange("niterSpawn", 6, 0, 64);
        tungstenSpawn = BUILDER
                .comment("Amount of tungsten ore veins per chunk.")
                .defineInRange("tungstenSpawn", 10, 0, 64);
        leadSpawn = BUILDER
                .comment("Amount of lead ore veins per chunk.")
                .defineInRange("leadSpawn", 6, 0, 64);
        berylliumSpawn = BUILDER
                .comment("Amount of beryllium ore veins per chunk.")
                .defineInRange("berylliumSpawn", 6, 0, 64);
        ligniteSpawn = BUILDER
                .comment("Amount of lignite ore veins per chunk.")
                .defineInRange("ligniteSpawn", 2, 0, 64);
        asbestosSpawn = BUILDER
                .comment("Amount of asbestos ore veins per chunk.")
                .defineInRange("asbestosSpawn", 4, 0, 64);
        rareSpawn = BUILDER
                .comment("Amount of rare earth ore veins per chunk.")
                .defineInRange("rareSpawn", 6, 0, 64);
        lithiumSpawn = BUILDER
                .comment("Amount of lithium ore veins per chunk.")
                .defineInRange("lithiumSpawn", 6, 0, 64);
        cinnebarSpawn = BUILDER
                .comment("Amount of cinnebar ore veins per chunk.")
                .defineInRange("cinnebarSpawn", 1, 0, 64);
        cobaltSpawn = BUILDER
                .comment("Amount of cobalt ore veins per chunk.")
                .defineInRange("cobaltSpawn", 2, 0, 64);
        gassshaleSpawn = BUILDER
                .comment("Amount of oil shale veins per chunk.")
                .defineInRange("gassshaleSpawn", 5, 0, 64);
        gasbubbleSpawn = BUILDER
                .comment("Spawns a gas bubble every nTH chunk.")
                .defineInRange("gasbubbleSpawn", 12, 0, 128);
        explosivebubbleSpawn = BUILDER
                .comment("Spawns an explosive gas bubble every nTH chunk.")
                .defineInRange("explosivebubbleSpawn", 0, 0, 128);
        oilSpawn = BUILDER
                .comment("Spawns an oil bubble every nTH chunk.")
                .defineInRange("oilSpawn", 100, 0, 500);
        bedrockOilSpawn = BUILDER
                .comment("Spawns a bedrock oil node every nTH chunk.")
                .defineInRange("bedrockOilSpawn", 200, 0, 500);
        meteoriteSpawn = BUILDER
                .comment("Spawns a fallen meteorite every nTH chunk.")
                .defineInRange("meteoriteSpawn", 500, 0, 5000);
        ironClusterSpawn = BUILDER
                .comment("Amount of iron cluster veins per chunk.")
                .defineInRange("ironClusterSpawn", 4, 0, 64);
        titaniumClusterSpawn = BUILDER
                .comment("Amount of titanium cluster veins per chunk.")
                .defineInRange("titaniumClusterSpawn", 2, 0, 64);
        aluminiumClusterSpawn = BUILDER
                .comment("Amount of aluminium cluster veins per chunk.")
                .defineInRange("aluminiumClusterSpawn", 3, 0, 64);
        copperClusterSpawn = BUILDER
                .comment("Amount of copper cluster veins per chunk.")
                .defineInRange("copperClusterSpawn", 4, 0, 64);
        alexandriteSpawn = BUILDER
                .comment("Spawns an alexandrite vein every nTH chunk.")
                .defineInRange("alexandriteSpawn", 100, 0, 500);
        limestoneSpawn = BUILDER
                .comment("Amount of limestone block veins per chunk.")
                .defineInRange("limestoneSpawn", 1, 0, 64);
        netherUraniumSpawn = BUILDER
                .comment("Amount of nether uranium ore veins per chunk.")
                .defineInRange("netherUraniumSpawn", 8, 0, 64);
        netherTungstenSpawn = BUILDER
                .comment("Amount of nether tungsten ore veins per chunk.")
                .defineInRange("netherTungstenSpawn", 10, 0, 64);
        netherSulfurSpawn = BUILDER
                .comment("Amount of nether sulfur ore veins per chunk.")
                .defineInRange("netherSulfurSpawn", 26, 0, 64);
        netherPhosphorusSpawn = BUILDER
                .comment("Amount of nether phosphorus ore veins per chunk.")
                .defineInRange("netherPhosphorusSpawn", 24, 0, 64);
        netherCoalSpawn = BUILDER
                .comment("Amount of nether coal ore veins per chunk.")
                .defineInRange("netherCoalSpawn", 8, 0, 64);
        netherPlutoniumSpawn = BUILDER
                .comment("Amount of nether plutonium ore veins per chunk.")
                .defineInRange("netherPlutoniumSpawn", 8, 0, 64);
        netherCobaltSpawn = BUILDER
                .comment("Amount of nether cobalt ore veins per chunk.")
                .defineInRange("netherCobaltSpawn", 2, 0, 64);
        endTikiteSpawn = BUILDER
                .comment("Amount of end trixite ore veins per chunk.")
                .defineInRange("endTikiteSpawn", 8, 0, 64);
        enableHematite = BUILDER
                .comment("Toggles hematite deposits.")
                .define("enableHematite", true);
        enableMalachite = BUILDER
                .comment("Toggles malachite deposits.")
                .define("enableMalachite", true);
        enableBauxite = BUILDER
                .comment("Toggles bauxite deposits.")
                .define("enableBauxite", true);
        enableSulfurCave = BUILDER
                .comment("Toggles sulfur caves.")
                .define("enableSulfurCave", true);
        enableAsbestosCave = BUILDER
                .comment("Toggles asbestos caves.")
                .define("enableAsbestosCave", true);


        BUILDER.pop(); // Exit oregen category

        BUILDER.comment("Bedrock Ore Settings").push("bedrock_ores");

        newBedrockOres = BUILDER
                .comment("Enable the newer generic bedrock ores.")
                .define("newBedrockOres", true);
        bedrockIronSpawn = BUILDER
                .comment("Spawn weight for iron bedrock ore.")
                .defineInRange("bedrockIronSpawn", 100, 0, 500);
        bedrockCopperSpawn = BUILDER
                .comment("Spawn weight for copper bedrock ore.")
                .defineInRange("bedrockCopperSpawn", 200, 0, 500);
        bedrockBoraxSpawn = BUILDER
                .comment("Spawn weight for borax bedrock ore.")
                .defineInRange("bedrockBoraxSpawn", 50, 0, 500);
        bedrockGoldSpawn = BUILDER
                .comment("Spawn weight for gold bedrock ore.")
                .defineInRange("bedrockGoldSpawn", 50, 0, 500);
        bedrockUraniumSpawn = BUILDER
                .comment("Spawn weight for uranium bedrock ore.")
                .defineInRange("bedrockUraniumSpawn", 35, 0, 500);
        bedrockThoriumSpawn = BUILDER
                .comment("Spawn weight for thorium bedrock ore.")
                .defineInRange("bedrockThoriumSpawn", 50, 0, 500);
        bedrockChlorocalciteSpawn = BUILDER
                .comment("Spawn weight for chlorocalcite bedrock ore.")
                .defineInRange("bedrockChlorocalciteSpawn", 35, 0, 500);
        bedrockAsbestosSpawn = BUILDER
                .comment("Spawn weight for asbestos bedrock ore.")
                .defineInRange("bedrockAsbestosSpawn", 50, 0, 500);
        bedrockNiobiumSpawn = BUILDER
                .comment("Spawn weight for niobium bedrock ore.")
                .defineInRange("bedrockNiobiumSpawn", 50, 0, 500);
        bedrockNeodymiumSpawn = BUILDER
                .comment("Spawn weight for neodymium bedrock ore.")
                .defineInRange("bedrockNeodymiumSpawn", 50, 0, 500);
        bedrockTitaniumSpawn = BUILDER
                .comment("Spawn weight for titanium bedrock ore.")
                .defineInRange("bedrockTitaniumSpawn", 100, 0, 500);
        bedrockTungstenSpawn = BUILDER
                .comment("Spawn weight for tungsten bedrock ore.")
                .defineInRange("bedrockTungstenSpawn", 100, 0, 500);
        bedrockCoalSpawn = BUILDER
                .comment("Spawn weight for coal bedrock ore.")
                .defineInRange("bedrockCoalSpawn", 200, 0, 500);
        bedrockNiterSpawn = BUILDER
                .comment("Spawn weight for niter bedrock ore.")
                .defineInRange("bedrockNiterSpawn", 50, 0, 500);
        bedrockFluoriteSpawn = BUILDER
                .comment("Spawn weight for fluorite bedrock ore.")
                .defineInRange("bedrockFluoriteSpawn", 50, 0, 500);
        bedrockRedstoneSpawn = BUILDER
                .comment("Spawn weight for redstone bedrock ore.")
                .defineInRange("bedrockRedstoneSpawn", 50, 0, 500);
        bedrockRareEarthSpawn = BUILDER
                .comment("Spawn weight for rare earth bedrock ore.")
                .defineInRange("bedrockRareEarthSpawn", 50, 0, 500);
        bedrockBauxiteSpawn = BUILDER
                .comment("Spawn weight for bauxite bedrock ore.")
                .defineInRange("bedrockBauxiteSpawn", 100, 0, 500);
        bedrockEmeraldSpawn = BUILDER
                .comment("Spawn weight for emerald bedrock ore.")
                .defineInRange("bedrockEmeraldSpawn", 50, 0, 500);
        bedrockGlowstoneSpawn = BUILDER
                .comment("Spawn weight for glowstone bedrock ore.")
                .defineInRange("bedrockGlowstoneSpawn", 100, 0, 500);
        bedrockPhosphorusSpawn = BUILDER
                .comment("Spawn weight for phosphorus bedrock ore.")
                .defineInRange("bedrockPhosphorusSpawn", 50, 0, 500);
        bedrockQuartzSpawn = BUILDER
                .comment("Spawn weight for quartz bedrock ore.")
                .defineInRange("bedrockQuartzSpawn", 100, 0, 500);


        BUILDER.pop(); // Exit bedrock_ores category

        BUILDER.comment("Structure Settings").push("structures");
        radioStructure = BUILDER
                .comment("Spawn radio station on every nTH chunk.")
                .defineInRange("radioStructure", 500, 0, 10000);
        antennaStructure = BUILDER
                .comment("Spawn antenna on every nTH chunk.")
                .defineInRange("antennaStructure", 250, 0, 10000);
        atomStructure = BUILDER
                .comment("Spawn power plant on every nTH chunk.")
                .defineInRange("atomStructure", 500, 0, 10000);
        dungeonStructure = BUILDER
                .comment("Spawn library dungeon on every nTH chunk.")
                .defineInRange("dungeonStructure", 64, 0, 10000);
        relayStructure = BUILDER
                .comment("Spawn relay on every nTH chunk.")
                .defineInRange("relayStructure", 500, 0, 10000);
        satelliteStructure = BUILDER
                .comment("Spawn satellite dish on every nTH chunk.")
                .defineInRange("satelliteStructure", 500, 0, 10000);
        factoryStructure = BUILDER
                .comment("Spawn factory on every nTH chunk.")
                .defineInRange("factoryStructure", 1000, 0, 10000);
        dudStructure = BUILDER
                .comment("Spawn dud on every nTH chunk.")
                .defineInRange("dudStructure", 500, 0, 10000);
        spaceshipStructure = BUILDER
                .comment("Spawn spaceship on every nTH chunk.")
                .defineInRange("spaceshipStructure", 1000, 0, 10000);
        barrelStructure = BUILDER
                .comment("Spawn waste tank on every nTH chunk.")
                .defineInRange("barrelStructure", 5000, 0, 10000);
        geyserWater = BUILDER
                .comment("Spawn water geyser on every nTH chunk.")
                .defineInRange("geyserWater", 3000, 0, 10000);
        geyserChlorine = BUILDER
                .comment("Spawn poison geyser on every nTH chunk.")
                .defineInRange("geyserChlorine", 3000, 0, 10000);
        geyserVapor = BUILDER
                .comment("Spawn vapor geyser on every nTH chunk.")
                .defineInRange("geyserVapor", 500, 0, 10000);
        capsuleStructure = BUILDER
                .comment("Spawn landing capsule on every nTH chunk.")
                .defineInRange("capsuleStructure", 100, 0, 10000);
        arcticStructure = BUILDER
                .comment("Spawn arctic code vault on every nTH chunk.")
                .defineInRange("arcticStructure", 500, 0, 10000);
        jungleStructure = BUILDER
                .comment("Spawn jungle dungeon on every nTH chunk.")
                .defineInRange("jungleStructure", 2000, 0, 10000);
        pyramidStructure = BUILDER
                .comment("Spawn pyramid on every nTH chunk.")
                .defineInRange("pyramidStructure", 4000, 0, 10000);
        broadcaster = BUILDER
                .comment("Spawn corrupt broadcaster on every nTH chunk.")
                .defineInRange("broadcaster", 5000, 0, 10000);
        minefreq = BUILDER
                .comment("Spawn AP landmine on every nTH chunk.")
                .defineInRange("minefreq", 64, 0, 10000);
        radfreq = BUILDER
                .comment("Spawn radiation hotspot on every nTH chunk.")
                .defineInRange("radfreq", 5000, 0, 10000);
        vaultfreq = BUILDER
                .comment("Spawn locked safe on every nTH chunk.")
                .defineInRange("vaultfreq", 2500, 0, 10000);


        BUILDER.pop();

        BUILDER.comment("Meteor Settings").push("meteors");

        enableMeteorStrikes = BUILDER
                .comment("Toggles the spawning of meteors.")
                .define("enableMeteorStrikes", true);
        enableMeteorShowers = BUILDER
                .comment("Toggles meteor showers.")
                .define("enableMeteorShowers", true);
        enableMeteorTails = BUILDER
                .comment("Toggles the particle effect created by falling meteors.")
                .define("enableMeteorTails", true);
        enableSpecialMeteors = BUILDER
                .comment("Toggles rare, special meteor types with different impact effects.")
                .define("enableSpecialMeteors", true);
        meteorStrikeChance = BUILDER
                .comment("The probability of a meteor spawning (an average of once every nTH ticks).")
                .defineInRange("meteorStrikeChance", 20 * 60 * 180, 0, Integer.MAX_VALUE);
        meteorShowerChance = BUILDER
                .comment("The probability of a meteor spawning during meteor shower.")
                .defineInRange("meteorShowerChance", 20 * 60 * 5, 0, Integer.MAX_VALUE);
        meteorShowerDuration = BUILDER
                .comment("Max duration of meteor shower in ticks.")
                .defineInRange("meteorShowerDuration", 6000, 0, Integer.MAX_VALUE);

        BUILDER.pop(); // Exit meteors category
        BUILDER.comment("World Gen Settings").push("world-gen");

        enableCraterBiomes = BUILDER
                .comment("Enables the biome change caused by nuclear explosions.")
                .define("enableCraterBiomes", true);
        craterBiomeId = BUILDER
                .comment("The numeric ID for the crater biome.")
                .defineInRange("craterBiomeId", 80, 0, 255);
        craterBiomeInnerId = BUILDER
                .comment("The numeric ID for the inner crater biome.")
                .defineInRange("craterBiomeInnerId", 81, 0, 255);
        craterBiomeOuterId = BUILDER
                .comment("The numeric ID for the outer crater biome.")
                .defineInRange("craterBiomeOuterId", 82, 0, 255);
        craterBiomeRad = BUILDER
                .comment("RAD/s for the crater biome.")
                .defineInRange("craterBiomeRad", 5.0, 0.0, 100.0);
        craterBiomeInnerRad = BUILDER
                .comment("RAD/s for the inner crater biome.")
                .defineInRange("craterBiomeInnerRad", 25.0, 0.0, 100.0);
        craterBiomeOuterRad = BUILDER
                .comment("RAD/s for the outer crater biome.")
                .defineInRange("craterBiomeOuterRad", 0.5, 0.0, 100.0);
        craterBiomeWaterMult = BUILDER
                .comment("Multiplier for RAD/s in crater biomes when in water.")
                .defineInRange("craterBiomeWaterMult", 5.0, 0.0, 100.0);


        BUILDER.pop();

        SPEC = BUILDER.build();
    }

}
