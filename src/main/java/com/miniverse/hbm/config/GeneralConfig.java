package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Thermos Prevention
    public static final ForgeConfigSpec.BooleanValue enableThermosPreventer;

    // Packet Threading
    public static final ForgeConfigSpec.BooleanValue enablePacketThreading;
    public static final ForgeConfigSpec.IntValue packetThreadingCoreCount;
    public static final ForgeConfigSpec.IntValue packetThreadingMaxCount;
    public static final ForgeConfigSpec.BooleanValue packetThreadingErrorBypass;

    // Gameplay Toggles
    public static final ForgeConfigSpec.BooleanValue enableDebugMode;
    public static final ForgeConfigSpec.BooleanValue enableMycelium;
    public static final ForgeConfigSpec.BooleanValue enablePlutoniumOre;
    public static final ForgeConfigSpec.BooleanValue enableGuns;
    public static final ForgeConfigSpec.BooleanValue enableVirus;
    public static final ForgeConfigSpec.BooleanValue enableCrosshairs;
    public static final ForgeConfigSpec.BooleanValue enableRenderDistCheck;
    public static final ForgeConfigSpec.BooleanValue enableSilentCompStackErrors;
    public static final ForgeConfigSpec.BooleanValue enableSkyboxes;
    public static final ForgeConfigSpec.BooleanValue enableImpactWorldProvider;

    // Sound Settings
    public static final ForgeConfigSpec.BooleanValue enableSoundExtension;
    public static final ForgeConfigSpec.IntValue normalSoundChannels;

    // Expensive Mode
    public static final ForgeConfigSpec.BooleanValue enableExpensiveMode;

    // 528 Mode Settings
    public static final ForgeConfigSpec.BooleanValue enable528;
    public static final ForgeConfigSpec.BooleanValue enable528ReasimBoilers;
    public static final ForgeConfigSpec.BooleanValue enable528ColtanDeposit;
    public static final ForgeConfigSpec.BooleanValue enable528ColtanSpawn;
    public static final ForgeConfigSpec.BooleanValue enable528BedrockDeposit;
    public static final ForgeConfigSpec.BooleanValue enable528BedrockSpawn;
    public static final ForgeConfigSpec.BooleanValue enable528BosniaSimulator;
    public static final ForgeConfigSpec.BooleanValue enable528BedrockReplacement;
    public static final ForgeConfigSpec.BooleanValue enable528NetherBurn;
    public static final ForgeConfigSpec.IntValue coltanRate;
    public static final ForgeConfigSpec.IntValue bedrockRate;

    // LBSM Mode Settings
    public static final ForgeConfigSpec.BooleanValue enableLBSM;
    public static final ForgeConfigSpec.BooleanValue enableLBSMFullSchrab;
    public static final ForgeConfigSpec.BooleanValue enableLBSMShorterDecay;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleArmorRecipes;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleToolRecipes;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleAlloy;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleChemistry;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleCentrifuge;
    public static final ForgeConfigSpec.BooleanValue enableLBSMUnlockAnvil;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleCrafting;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSimpleMedicineRecipes;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSafeCrates;
    public static final ForgeConfigSpec.BooleanValue enableLBSMSafeMEDrives;
    public static final ForgeConfigSpec.BooleanValue enableLBSMIGen;
    public static final ForgeConfigSpec.IntValue schrabRate;

    static {
        BUILDER.comment("General Settings").push("general");

        enableThermosPreventer = BUILDER.comment("Prevents launching on Thermos servers")
                .define("enableThermosPreventer", true);

        BUILDER.pop().comment("Packet Threading").push("packet_threading");

        enablePacketThreading = BUILDER.comment("Enable separate thread for packets")
                .define("enablePacketThreading", true);
        packetThreadingCoreCount = BUILDER.comment("Number of core threads for packets")
                .defineInRange("packetThreadingCoreCount", 1, 1, 16);
        packetThreadingMaxCount = BUILDER.comment("Max threads for packet handling")
                .defineInRange("packetThreadingMaxCount", 1, 1, 16);
        packetThreadingErrorBypass = BUILDER.comment("Bypass most packet threading errors")
                .define("packetThreadingErrorBypass", false);

        BUILDER.pop().comment("Gameplay Toggles").push("gameplay");

        enableDebugMode = BUILDER.comment("Enable debugging mode")
                .define("enableDebugMode", false);
        enableMycelium = BUILDER.comment("Allows glowing mycelium to spread")
                .define("enableMycelium", false);
        enablePlutoniumOre = BUILDER.comment("Enables plutonium ore generation in the Nether")
                .define("enablePlutoniumOre", false);
        enableGuns = BUILDER.comment("Enables guns in the mod")
                .define("enableGuns", true);
        enableVirus = BUILDER.comment("Allows virus blocks to spread")
                .define("enableVirus", false);
        enableCrosshairs = BUILDER.comment("Shows custom crosshairs when holding an NTM gun")
                .define("enableCrosshairs", true);
        enableRenderDistCheck = BUILDER.comment("Check for invalid render distances")
                .define("enableRenderDistCheck", true);
        enableSilentCompStackErrors = BUILDER.comment("Suppresses log spam from unregistered items")
                .define("enableSilentCompStackErrors", false);
        enableSkyboxes = BUILDER.comment("Enable custom skyboxes")
                .define("enableSkyboxes", true);
        enableImpactWorldProvider = BUILDER.comment("Enable custom world provider for impact effects")
                .define("enableImpactWorldProvider", true);

        BUILDER.pop().comment("Sound Settings").push("sound");

        enableSoundExtension = BUILDER.comment("Increase the limit for sound channels")
                .define("enableSoundExtension", true);
        normalSoundChannels = BUILDER.comment("Number of sound channels (min 28, max 200)")
                .defineInRange("normalSoundChannels", 100, 28, 200);

        BUILDER.pop().comment("Other Settings").push("other");

        enableExpensiveMode = BUILDER.comment("Makes the game harder")
                .define("enableExpensiveMode", false);

        BUILDER.pop();
        BUILDER.push("528 Mode");

        enable528 = BUILDER.define("enable528", false);
        enable528ReasimBoilers = BUILDER.define("enable528ReasimBoilers", true);
        enable528ColtanDeposit = BUILDER.define("enable528ColtanDeposit", true);
        enable528ColtanSpawn = BUILDER.define("enable528ColtanSpawn", false);
        enable528BedrockDeposit = BUILDER.define("enable528BedrockDeposit", true);
        enable528BedrockSpawn = BUILDER.define("enable528BedrockSpawn", false);
        enable528BosniaSimulator = BUILDER.define("enable528BosniaSimulator", true);
        enable528BedrockReplacement = BUILDER.define("enable528BedrockReplacement", true);
        enable528NetherBurn = BUILDER.define("enable528NetherBurn", true);
        coltanRate = BUILDER.defineInRange("coltanRate", 2, 1, 100);
        bedrockRate = BUILDER.defineInRange("bedrockRate", 50, 1, 500);

        BUILDER.pop();
        BUILDER.push("LBSM Mode");

        enableLBSM = BUILDER.define("enableLBSM", false);
        enableLBSMFullSchrab = BUILDER.define("enableLBSMFullSchrab", true);
        enableLBSMShorterDecay = BUILDER.define("enableLBSMShorterDecay", true);
        enableLBSMSimpleArmorRecipes = BUILDER.define("enableLBSMSimpleArmorRecipes", true);
        enableLBSMSimpleToolRecipes = BUILDER.define("enableLBSMSimpleToolRecipes", true);
        enableLBSMSimpleAlloy = BUILDER.define("enableLBSMSimpleAlloy", true);
        enableLBSMSimpleChemistry = BUILDER.define("enableLBSMSimpleChemistry", true);
        enableLBSMSimpleCentrifuge = BUILDER.define("enableLBSMSimpleCentrifuge", true);
        enableLBSMUnlockAnvil = BUILDER.define("enableLBSMUnlockAnvil", true);
        enableLBSMSimpleCrafting = BUILDER.define("enableLBSMSimpleCrafting", true);
        enableLBSMSimpleMedicineRecipes = BUILDER.define("enableLBSMSimpleMedicineRecipes", true);
        enableLBSMSafeCrates = BUILDER.define("enableLBSMSafeCrates", true);
        enableLBSMSafeMEDrives = BUILDER.define("enableLBSMSafeMEDrives", true);
        enableLBSMIGen = BUILDER.define("enableLBSMIGen", true);
        schrabRate = BUILDER.defineInRange("schrabRate", 20, 1, 200);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
