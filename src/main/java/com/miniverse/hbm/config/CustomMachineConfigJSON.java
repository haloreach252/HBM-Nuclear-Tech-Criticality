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

public class CustomMachineConfigJSON {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "hbmCustomMachines.json";

    public static final Map<String, MachineConfiguration> customMachines = new HashMap<>();
    public static final List<MachineConfiguration> niceList = new ArrayList<>();

    /**
     * Initializes the config file, loading or creating default values.
     */
    public static void initialize() {
        Path configPath = getConfigPath();

        if (!Files.exists(configPath)) {
            writeDefaultConfig(configPath);
        }

        readConfig(configPath);
    }

    /**
     * Gets the path to the machine config JSON.
     */
    private static Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get().resolve(CONFIG_FILE_NAME);
    }

    /**
     * Writes a default machine configuration file.
     */
    private static void writeDefaultConfig(Path configPath) {
        try (Writer writer = new FileWriter(configPath.toFile())) {
            JsonObject root = new JsonObject();
            JsonArray machines = new JsonArray();

            // Example default machine
            JsonObject machine = new JsonObject();
            machine.addProperty("recipeKey", "paperPress");
            machine.addProperty("unlocalizedName", "paperPress");
            machine.addProperty("localizedName", "Paper Press");

            JsonObject localization = new JsonObject();
            localization.addProperty("de_DE", "Papierpresse");
            machine.add("localization", localization);

            machine.addProperty("fluidInCount", 1);
            machine.addProperty("fluidInCap", 1000);
            machine.addProperty("itemInCount", 1);
            machine.addProperty("fluidOutCount", 0);
            machine.addProperty("fluidOutCap", 0);
            machine.addProperty("itemOutCount", 1);
            machine.addProperty("generatorMode", false);
            machine.addProperty("maxPollutionCap", 100);
            machine.addProperty("fluxMode", false);
            machine.addProperty("recipeSpeedMult", 1.0);
            machine.addProperty("recipeConsumptionMult", 1.0);
            machine.addProperty("maxPower", 10000);
            machine.addProperty("maxHeat", 0);

            // Recipe Shape
            JsonArray recipeShape = new JsonArray();
            recipeShape.add("IPI");
            recipeShape.add("PCP");
            recipeShape.add("IPI");
            machine.add("recipeShape", recipeShape);

            // Components
            JsonArray components = new JsonArray();
            JsonObject component = new JsonObject();
            component.addProperty("block", "hbm:tile.cm_block");
            component.addProperty("x", 0);
            component.addProperty("y", 0);
            component.addProperty("z", 0);

            JsonArray metas = new JsonArray();
            metas.add(0);
            component.add("metas", metas);
            components.add(component);
            machine.add("components", components);

            machines.add(machine);
            root.add("machines", machines);

            GSON.toJson(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the custom machine configuration from JSON.
     */
    public static void readConfig(Path configPath) {
        try (Reader reader = new FileReader(configPath.toFile())) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            JsonArray machines = json.getAsJsonArray("machines");

            for (JsonElement machineElement : machines) {
                JsonObject machineObject = machineElement.getAsJsonObject();

                MachineConfiguration configuration = new MachineConfiguration();
                configuration.recipeKey = machineObject.get("recipeKey").getAsString();
                configuration.unlocalizedName = machineObject.get("unlocalizedName").getAsString();
                configuration.localizedName = machineObject.get("localizedName").getAsString();

                if (machineObject.has("localization")) {
                    JsonObject localization = machineObject.getAsJsonObject("localization");
                    for (Map.Entry<String, JsonElement> entry : localization.entrySet()) {
                        configuration.localization.put(entry.getKey(), entry.getValue().getAsString());
                    }
                }

                configuration.fluidInCount = machineObject.get("fluidInCount").getAsInt();
                configuration.fluidInCap = machineObject.get("fluidInCap").getAsInt();
                configuration.itemInCount = machineObject.get("itemInCount").getAsInt();
                configuration.fluidOutCount = machineObject.get("fluidOutCount").getAsInt();
                configuration.fluidOutCap = machineObject.get("fluidOutCap").getAsInt();
                configuration.itemOutCount = machineObject.get("itemOutCount").getAsInt();
                configuration.generatorMode = machineObject.get("generatorMode").getAsBoolean();
                configuration.maxPollutionCap = machineObject.get("maxPollutionCap").getAsInt();
                configuration.fluxMode = machineObject.get("fluxMode").getAsBoolean();
                configuration.recipeSpeedMult = machineObject.get("recipeSpeedMult").getAsDouble();
                configuration.recipeConsumptionMult = machineObject.get("recipeConsumptionMult").getAsDouble();
                configuration.maxPower = machineObject.get("maxPower").getAsLong();
                configuration.maxHeat = machineObject.get("maxHeat").getAsInt();

                // Parse components
                JsonArray components = machineObject.getAsJsonArray("components");
                configuration.components = new ArrayList<>();

                for (JsonElement componentElement : components) {
                    JsonObject compObject = componentElement.getAsJsonObject();
                    MachineConfiguration.ComponentDefinition component = new MachineConfiguration.ComponentDefinition();

                    String blockName = compObject.get("block").getAsString();
                    Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockName));

                    if (block != null) {
                        component.block = block;
                    } else {
                        System.err.println("Warning: Unknown block " + blockName + " in machine config.");
                    }

                    component.x = compObject.get("x").getAsInt();
                    component.y = compObject.get("y").getAsInt();
                    component.z = compObject.get("z").getAsInt();

                    JsonArray metasArray = compObject.getAsJsonArray("metas");
                    component.allowedMetas = new HashSet<>();
                    for (JsonElement meta : metasArray) {
                        component.allowedMetas.add(meta.getAsInt());
                    }

                    configuration.components.add(component);
                }

                customMachines.put(configuration.unlocalizedName, configuration);
                niceList.add(configuration);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Represents a custom machine configuration.
     */
    public static class MachineConfiguration {
        public String recipeKey;
        public String unlocalizedName;
        public String localizedName;
        public Map<String, String> localization = new HashMap<>();
        public int fluidInCount;
        public int fluidInCap;
        public int itemInCount;
        public int fluidOutCount;
        public int fluidOutCap;
        public int itemOutCount;
        public boolean generatorMode;
        public int maxPollutionCap;
        public boolean fluxMode;
        public double recipeSpeedMult = 1D;
        public double recipeConsumptionMult = 1D;
        public long maxPower;
        public int maxHeat;
        public List<ComponentDefinition> components;

        public static class ComponentDefinition {
            public Block block;
            public Set<Integer> allowedMetas;
            public int x;
            public int y;
            public int z;
        }
    }
}
