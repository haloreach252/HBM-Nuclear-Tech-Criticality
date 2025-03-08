package com.miniverse.hbm.config;

import com.google.gson.*;
import com.miniverse.hbm.itempool.ItemPool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ItemPoolConfigJSON {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_FILE_NAME = "hbmItemPools.json";

    public static final Map<String, ItemPool> itemPools = new HashMap<>();

    /**
     * Initializes the Item Pool Config system.
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
     * Writes a default item pool configuration file.
     */
    private static void writeDefaultConfig(Path configPath) {
        try (Writer writer = new FileWriter(configPath.toFile())) {
            JsonObject root = new JsonObject();
            JsonObject pools = new JsonObject();

            // Example: Creating an item pool with a sample item
            JsonArray poolArray = new JsonArray();

            JsonArray itemEntry = new JsonArray();
            itemEntry.add("minecraft:diamond");
            itemEntry.add(1);  // Min count
            itemEntry.add(3);  // Max count
            itemEntry.add(10); // Weight

            poolArray.add(itemEntry);
            pools.add("examplePool", poolArray);

            root.add("pools", pools);
            GSON.toJson(root, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and loads the Item Pool configuration from JSON.
     */
    public static void readConfig(Path configPath) {
        try (Reader reader = new FileReader(configPath.toFile())) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            JsonObject pools = json.getAsJsonObject("pools");

            for (Map.Entry<String, JsonElement> entry : pools.entrySet()) {
                String poolName = entry.getKey();
                JsonArray poolArray = entry.getValue().getAsJsonArray();
                ItemPool pool = new ItemPool(poolName);

                for (JsonElement poolEntry : poolArray) {
                    JsonArray itemArray = poolEntry.getAsJsonArray();
                    ItemStack stack = readItemStack(itemArray.get(0).getAsString(), itemArray);
                    int min = itemArray.get(1).getAsInt();
                    int max = itemArray.get(2).getAsInt();
                    int weight = itemArray.get(3).getAsInt();
                    pool.add(stack, min, max, weight);
                }

                itemPools.put(poolName, pool);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an ItemStack from JSON.
     */
    public static ItemStack readItemStack(String itemName, JsonArray itemArray) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));

        if (item == null) {
            System.err.println("Error: Unknown item " + itemName);
            return ItemStack.EMPTY;
        }

        int stackSize = itemArray.size() > 1 ? itemArray.get(1).getAsInt() : 1;
        int meta = itemArray.size() > 2 ? itemArray.get(2).getAsInt() : 0;
        ItemStack stack = new ItemStack(item, stackSize);

        if (itemArray.size() > 3) {
            try {
                String nbtString = itemArray.get(3).getAsString();
                CompoundTag nbt = NbtIo.readCompressed(new ByteArrayInputStream(nbtString.getBytes()));
                stack.setTag(nbt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stack;
    }
}
