package com.miniverse.hbm.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FalloutConfigJSON {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "hbmFallout.json";

    public static final List<FalloutEntry> entries = new ArrayList<>();
    private static final Random rand = new Random();

    /**
     * Initializes the Fallout Config system.
     */
    public static void initialize() {
        Path configPath = getConfigPath();

        if (!Files.exists(configPath)) {
            writeDefaultConfig(configPath);
        }

        readConfig(configPath);
    }

    /**
     * Gets the config file path.
     */
    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve(CONFIG_FILE_NAME);
    }

    /**
     * Writes a default fallout configuration file.
     */
    private static void writeDefaultConfig(Path configPath) {
        try (Writer writer = new FileWriter(configPath.toFile())) {
            JsonObject root = new JsonObject();
            JsonArray entriesArray = new JsonArray();

            // Example: Transform wood into petrified wood under fallout conditions
            JsonObject entry = new JsonObject();
            entry.addProperty("matchesBlock", "minecraft:oak_log");
            entry.addProperty("transformsTo", "hbmnucleartechcriticality:waste_log");
            entry.addProperty("maxRange", 65.0);
            entriesArray.add(entry);

            root.add("entries", entriesArray);
            GSON.toJson(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and loads the Fallout configuration from JSON.
     */
    public static void readConfig(Path configPath) {
        try (Reader reader = new FileReader(configPath.toFile())) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            JsonArray entriesArray = json.getAsJsonArray("entries");

            for (JsonElement entryElement : entriesArray) {
                FalloutEntry entry = FalloutEntry.fromJson(entryElement.getAsJsonObject());
                if (entry != null) {
                    entries.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Represents a Fallout Transformation Entry.
     */
    public static class FalloutEntry {
        public Block matchesBlock;
        public Block transformsTo;
        public double maxRange;

        public static FalloutEntry fromJson(JsonObject json) {
            FalloutEntry entry = new FalloutEntry();

            String blockName = json.get("matchesBlock").getAsString();
            entry.matchesBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));

            String transformBlockName = json.get("transformsTo").getAsString();
            entry.transformsTo = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(transformBlockName));

            entry.maxRange = json.get("maxRange").getAsDouble();

            return (entry.matchesBlock != null && entry.transformsTo != null) ? entry : null;
        }
    }
}
