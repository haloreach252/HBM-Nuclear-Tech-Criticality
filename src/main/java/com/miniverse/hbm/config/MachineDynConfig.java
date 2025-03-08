package com.miniverse.hbm.config;

import com.google.gson.*;
import com.miniverse.hbm.tileentity.IConfigurableMachine;
import com.miniverse.hbm.tileentity.TileMappings;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Dynamically generated JSON config using the IConfigurableMachine interface.
 * This allows automatic configuration management for all machines implementing the interface.
 */
public class MachineDynConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "hbmMachines.json";

    /**
     * Initializes and loads the dynamic machine config.
     */
    public static void initialize() {
        Path configPath = getConfigPath();

        // Ensure config directory exists
        try {
            Files.createDirectories(configPath.getParent());
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create config directory: " + configPath.getParent(), e);
        }

        List<IConfigurableMachine> dummies = new ArrayList<>();
        TileMappings.configurables.forEach(x -> {
            try {
                dummies.add(x.getDeclaredConstructor().newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Read existing config and update machine settings
        JsonObject jsonConfig = readConfig(configPath);
        for (IConfigurableMachine dummy : dummies) {
            JsonElement element = jsonConfig.get(dummy.getConfigName());
            JsonObject machineConfig = element != null ? element.getAsJsonObject() : new JsonObject();
            dummy.readIfPresent(machineConfig);
        }

        // Write updated config back to file
        writeConfig(configPath, dummies);
    }

    /**
     * Gets the path to the JSON config file.
     */
    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve(CONFIG_FILE_NAME);
    }

    /**
     * Reads the JSON configuration file.
     */
    private static JsonObject readConfig(Path configPath) {
        if (!Files.exists(configPath)) {
            return new JsonObject();
        }

        try (Reader reader = Files.newBufferedReader(configPath)) {
            return GSON.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }

    /**
     * Writes the updated JSON configuration to file.
     */
    private static void writeConfig(Path configPath, List<IConfigurableMachine> machines) {
        try (Writer writer = Files.newBufferedWriter(configPath)) {
            JsonObject root = new JsonObject();

            // Add comment section
            JsonArray infoArray = new JsonArray();
            for (String line : getComment()) {
                infoArray.add(line);
            }
            root.add("info", infoArray);

            // Add machine configurations
            for (IConfigurableMachine machine : machines) {
                JsonObject machineConfig = new JsonObject();
                machine.writeConfig(machineConfig);
                root.add(machine.getConfigName(), machineConfig);
            }

            GSON.toJson(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an informational comment block about how this config works.
     */
    private static String[] getComment() {
        return new String[]{
                "This config file dynamically manages machine configurations.",
                "Any missing values are automatically added when the game starts.",
                "To reset a value, delete it from the JSON file, and it will be restored with defaults.",
                "Mod updates may introduce new values, which will be automatically added.",
                "Do not add custom values, as they will be removed when the file is rewritten."
        };
    }
}
