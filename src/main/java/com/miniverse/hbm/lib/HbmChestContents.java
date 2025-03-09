package com.miniverse.hbm.lib;

import com.miniverse.hbm.items.special.ItemBookLore;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
// If you have your own custom WeightedRandomChestContent class, update its package accordingly.
// Alternatively, consider replacing this with loot table entries.
import com.miniverse.hbm.util.WeightedRandomChestContent;

import java.util.Random;

public class HbmChestContents {

    public static WeightedRandomChestContent weighted(Item item, int meta, int min, int max, int weight) {
        return new WeightedRandomChestContent(item, meta, Math.min(min, max), Math.max(min, max), weight);
    }

    public static WeightedRandomChestContent weighted(Block block, int meta, int min, int max, int weight) {
        // In 1.20.1, use block.asItem() instead of Item.getItemFromBlock(block)
        return new WeightedRandomChestContent(block.asItem(), meta, Math.min(min, max), Math.max(min, max), weight);
    }

    public static WeightedRandomChestContent weighted(ItemStack item, int min, int max, int weight) {
        return new WeightedRandomChestContent(item, Math.min(min, max), Math.max(min, max), weight);
    }

    /** Methods for generating mod-specific lore books **/

    public static ItemStack generateOfficeBook(Random rand) {
        String key;
        int pages;
        switch (rand.nextInt(5)) {
            case 0:
                key = "resignation_note";
                pages = 3;
                break;
            case 1:
                key = "memo_stocks";
                pages = 1;
                break;
            case 2:
                key = "memo_schrab_gsa";
                pages = 2;
                break;
            case 3:
                key = "memo_schrab_rd";
                pages = 4;
                break;
            case 4:
                key = "memo_schrab_nuke";
                pages = 3;
                break;
            default:
                return ItemStack.EMPTY;
        }

        return ItemBookLore.createBook(key, pages, 0x6BC8FF, 0x0A0A0A);
    }

    public static ItemStack generateLabBook(Random rand) {
        String key;
        int pages;

        switch (rand.nextInt(5)) {
            case 0:
                key = "bf_bomb_1";
                pages = 4;
                break;
            case 1:
                key = "bf_bomb_2";
                pages = 6;
                break;
            case 2:
                key = "bf_bomb_3";
                pages = 6;
                break;
            case 3:
                key = "bf_bomb_4";
                pages = 5;
                break;
            case 4:
                key = "bf_bomb_5";
                pages = 9;
                break;
            default:
                return ItemStack.EMPTY;
        }

        return ItemBookLore.createBook(key, pages, 0x1E1E1E, 0x46EA44);
    }
}
