package com.miniverse.hbm.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "hbmClient.json";

    // UI and HUD Configuration
    public static final ForgeConfigSpec.IntValue GEIGER_OFFSET_HORIZONTAL;
    public static final ForgeConfigSpec.IntValue GEIGER_OFFSET_VERTICAL;
    public static final ForgeConfigSpec.IntValue INFO_OFFSET_HORIZONTAL;
    public static final ForgeConfigSpec.IntValue INFO_OFFSET_VERTICAL;
    public static final ForgeConfigSpec.IntValue INFO_POSITION;

    // Gun Visual Settings
    public static final ForgeConfigSpec.BooleanValue GUN_ANIMS_LEGACY;
    public static final ForgeConfigSpec.BooleanValue GUN_MODEL_FOV;
    public static final ForgeConfigSpec.BooleanValue GUN_VISUAL_RECOIL;
    public static final ForgeConfigSpec.DoubleValue GUN_ANIMATION_SPEED;

    // Item Tooltip
    public static final ForgeConfigSpec.BooleanValue ITEM_TOOLTIP_SHOW_OREDICT;
    public static final ForgeConfigSpec.BooleanValue ITEM_TOOLTIP_SHOW_CUSTOM_NUKE;

    // Other UI Settings
    public static final ForgeConfigSpec.BooleanValue MAIN_MENU_WACKY_SPLASHES;
    public static final ForgeConfigSpec.BooleanValue DODD_RBMK_DIAGNOSTIC;
    public static final ForgeConfigSpec.BooleanValue RENDER_CABLE_HANG;
    public static final ForgeConfigSpec.BooleanValue NUKE_HUD_FLASH;
    public static final ForgeConfigSpec.BooleanValue NUKE_HUD_SHAKE;
    public static final ForgeConfigSpec.BooleanValue RENDER_REEDS;

    static {
        BUILDER.comment("Geiger Counter & HUD Offsets").push("hud");

        GEIGER_OFFSET_HORIZONTAL = BUILDER.comment("Horizontal offset for Geiger counter")
                .defineInRange("geiger_offset_horizontal", 0, -500, 500);
        GEIGER_OFFSET_VERTICAL = BUILDER.comment("Vertical offset for Geiger counter")
                .defineInRange("geiger_offset_vertical", 0, -500, 500);
        INFO_OFFSET_HORIZONTAL = BUILDER.comment("Horizontal offset for Info HUD")
                .defineInRange("info_offset_horizontal", 0, -500, 500);
        INFO_OFFSET_VERTICAL = BUILDER.comment("Vertical offset for Info HUD")
                .defineInRange("info_offset_vertical", 0, -500, 500);
        INFO_POSITION = BUILDER.comment("Positioning mode for Info HUD (0 = Default)")
                .defineInRange("info_position", 0, 0, 10);

        BUILDER.pop().comment("Gun Visuals & Animations").push("guns");

        GUN_ANIMS_LEGACY = BUILDER.comment("Enable legacy gun animations")
                .define("gun_anims_legacy", false);
        GUN_MODEL_FOV = BUILDER.comment("Enable altered FOV for gun models")
                .define("gun_model_fov", false);
        GUN_VISUAL_RECOIL = BUILDER.comment("Enable visual recoil for guns")
                .define("gun_visual_recoil", true);
        GUN_ANIMATION_SPEED = BUILDER.comment("Speed of gun animations")
                .defineInRange("gun_animation_speed", 1.0, 0.1, 10.0);

        BUILDER.pop().comment("Item Tooltips").push("tooltips");

        ITEM_TOOLTIP_SHOW_OREDICT = BUILDER.comment("Show Ore Dictionary entries in tooltips")
                .define("item_tooltip_show_oredict", true);
        ITEM_TOOLTIP_SHOW_CUSTOM_NUKE = BUILDER.comment("Show custom nuke information in tooltips")
                .define("item_tooltip_show_custom_nuke", true);

        BUILDER.pop().comment("Other Client Settings").push("misc");

        MAIN_MENU_WACKY_SPLASHES = BUILDER.comment("Enable wacky main menu splash messages")
                .define("main_menu_wacky_splashes", true);
        DODD_RBMK_DIAGNOSTIC = BUILDER.comment("Enable diagnostic mode for RBMK reactors")
                .define("dodd_rbmk_diagnostic", true);
        RENDER_CABLE_HANG = BUILDER.comment("Enable hanging cables rendering")
                .define("render_cable_hang", true);
        NUKE_HUD_FLASH = BUILDER.comment("Enable nuclear explosion HUD flash")
                .define("nuke_hud_flash", true);
        NUKE_HUD_SHAKE = BUILDER.comment("Enable nuclear explosion HUD shake")
                .define("nuke_hud_shake", true);
        RENDER_REEDS = BUILDER.comment("Enable rendering of reeds (disable if conflicting with another mod)")
                .define("render_reeds", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    /**
     * Loads additional client-side settings from JSON.
     */
    public static void loadJsonConfig() {
        Path configPath = FMLPaths.CONFIGDIR.get().resolve(CONFIG_FILE_NAME);

        if (!Files.exists(configPath)) {
            saveJsonConfig(getDefaultConfig());
            return;
        }

        try (Reader reader = new FileReader(configPath.toFile())) {
            ConfigWrapper configWrapper = GSON.fromJson(reader, ConfigWrapper.class);
            applyConfig(configWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the JSON config file.
     */
    public static void saveJsonConfig(ConfigWrapper configWrapper) {
        Path configPath = FMLPaths.CONFIGDIR.get().resolve(CONFIG_FILE_NAME);

        try (Writer writer = new FileWriter(configPath.toFile())) {
            GSON.toJson(configWrapper, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the default JSON config values.
     */
    public static ConfigWrapper getDefaultConfig() {
        return new ConfigWrapper();
    }

    /**
     * Applies the JSON-based settings.
     */
    private static void applyConfig(ConfigWrapper configWrapper) {
        // If needed, load JSON settings into the client config here
    }

    /**
     * Wrapper class for JSON-based settings.
     */
    public static class ConfigWrapper {
        public int geigerOffsetX = 0;
        public int geigerOffsetY = 0;
        public int infoOffsetX = 0;
        public int infoOffsetY = 0;
        public boolean renderCableHang = true;
        public boolean enableWackySplashes = true;
    }
}
