package com.miniverse.hbm.config;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.miniverse.hbm.HBMNuclearTechCriticality;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class RunningConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static File getConfig(String name) {
        File folder = HBMNuclearTechCriticality.configHbmDir;
        return new File(folder, name);
    }

    public static void readConfig(File config, HashMap<String, ConfigWrapper<?>> configMap) {
        if (!config.exists()) {
            System.err.println("[RunningConfig] Config file not found: " + config.getAbsolutePath());
            return;
        }

        try (Reader reader = new FileReader(config)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);

            if (json == null) {
                System.err.println("[RunningConfig] Failed to parse JSON from config file.");
                return;
            }

            for (Map.Entry<String, ConfigWrapper<?>> entry : configMap.entrySet()) {
                String key = entry.getKey();
                ConfigWrapper<?> wrapper = entry.getValue();

                if (json.has(key)) {
                    try {
                        JsonElement value = json.get(key);
                        wrapper.updateFromJson(value);
                    } catch (Exception ex) {
                        System.err.println("[RunningConfig] Error parsing key: " + key);
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("[RunningConfig] Error reading config file: " + config.getAbsolutePath());
            ex.printStackTrace();
        }
    }

    public static void writeConfig(File config, HashMap<String, ConfigWrapper<?>> configMap, String info) {
        JsonObject json = new JsonObject();
        json.addProperty("info", info);

        List<String> keys = new ArrayList<>(configMap.keySet());
        Collections.sort(keys); // Sort for readability

        for (String key : keys) {
            ConfigWrapper<?> wrapper = configMap.get(key);
            wrapper.writeToJson(json, key);
        }

        try {
            Files.writeString(config.toPath(), GSON.toJson(json), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.err.println("[RunningConfig] Error writing to config file: " + config.getAbsolutePath());
            ex.printStackTrace();
        }
    }

    public static class ConfigWrapper<T> {
        private T value;

        public ConfigWrapper(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        public void updateFromJson(JsonElement jsonElement) {
            try {
                if (value instanceof String) {
                    this.value = (T) jsonElement.getAsString();
                } else if (value instanceof Float) {
                    this.value = (T) Float.valueOf(jsonElement.getAsFloat());
                } else if (value instanceof Double) {
                    this.value = (T) Double.valueOf(jsonElement.getAsDouble());
                } else if (value instanceof Integer) {
                    this.value = (T) Integer.valueOf(jsonElement.getAsInt());
                } else if (value instanceof Boolean) {
                    this.value = (T) Boolean.valueOf(jsonElement.getAsBoolean());
                }
            } catch (Exception ex) {
                System.err.println("[ConfigWrapper] Failed to update value from JSON: " + jsonElement);
                ex.printStackTrace();
            }
        }

        public void writeToJson(JsonObject json, String key) {
            if (value instanceof String) {
                json.addProperty(key, (String) value);
            } else if (value instanceof Float) {
                json.addProperty(key, (Float) value);
            } else if (value instanceof Double) {
                json.addProperty(key, (Double) value);
            } else if (value instanceof Integer) {
                json.addProperty(key, (Integer) value);
            } else if (value instanceof Boolean) {
                json.addProperty(key, (Boolean) value);
            }
        }
    }
}
