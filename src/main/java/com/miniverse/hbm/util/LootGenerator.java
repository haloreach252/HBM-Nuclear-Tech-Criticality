package com.miniverse.hbm.util;

import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.blocks.generic.BlockLoot.TileEntityLoot;
import com.miniverse.hbm.crafting.handlers.MKUCraftingHandler;
import com.miniverse.hbm.inventory.OreDictManager.DictFrame;
import com.miniverse.hbm.itempool.ItemPool;
import com.miniverse.hbm.itempool.ItemPoolsPile;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.items.special.ItemBookLore;
import com.miniverse.hbm.items.weapon.sedna.factory.GunFactory.EnumAmmo;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import java.util.Random;

public class LootGenerator {

    public static final String LOOT_BOOKLET = "LOOT_BOOKLET";
    public static final String LOOT_CAPNUKE = "LOOT_CAPNUKE";
    public static final String LOOT_MEDICINE = "LOOT_MEDICINE";
    public static final String LOOT_CAPSTASH = "LOOT_CAPSTASH";
    public static final String LOOT_MAKESHIFT_GUN = "LOOT_MAKESHIFT_GUN";
    public static final String LOOT_NUKE_STORAGE = "LOOT_NUKE_STORAGE";
    public static final String LOOT_BONES = "LOOT_BONES";
    public static final String LOOT_GLYPHID_HIVE = "LOOT_GLYPHID_HIVE";
    public static final String LOOT_METEOR = "LOOT_METEOR";

    /**
     * Applies loot to the block entity at the given coordinates based on the loot name.
     * Break statements have been added to prevent fall-through.
     *
     * @param world The current level.
     * @param x     X coordinate.
     * @param y     Y coordinate.
     * @param z     Z coordinate.
     * @param name  The loot identifier.
     */
    public static void applyLoot(Level world, int x, int y, int z, String name) {
        switch(name) {
            case LOOT_BOOKLET:
                lootBooklet(world, x, y, z);
                break;
            case LOOT_CAPNUKE:
                lootCapNuke(world, x, y, z);
                break;
            case LOOT_MEDICINE:
                lootMedicine(world, x, y, z);
                break;
            case LOOT_CAPSTASH:
                lootCapStash(world, x, y, z);
                break;
            case LOOT_MAKESHIFT_GUN:
                lootMakeshiftGun(world, x, y, z);
                break;
            case LOOT_NUKE_STORAGE:
                lootNukeStorage(world, x, y, z);
                break;
            case LOOT_BONES:
                lootBones(world, x, y, z);
                break;
            case LOOT_GLYPHID_HIVE:
                lootGlyphidHive(world, x, y, z);
                break;
            case LOOT_METEOR:
                lootBookMeteor(world, x, y, z);
                break;
            default:
                lootBones(world, x, y, z);
                break;
        }
    }

    /**
     * Returns an array of all loot names.
     *
     * @return A string array containing all loot identifiers.
     */
    public static String[] getLootNames() {
        return new String[] {
                LOOT_BOOKLET,
                LOOT_CAPNUKE,
                LOOT_MEDICINE,
                LOOT_CAPSTASH,
                LOOT_MAKESHIFT_GUN,
                LOOT_NUKE_STORAGE,
                LOOT_BONES,
                LOOT_GLYPHID_HIVE,
                LOOT_METEOR,
        };
    }

    /**
     * Sets a loot block at the specified coordinates.
     *
     * @param world The current level.
     * @param x     X coordinate.
     * @param y     Y coordinate.
     * @param z     Z coordinate.
     */
    public static void setBlock(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        world.setBlock(pos, ModBlocks.deco_loot.defaultBlockState(), 3);
    }

    /**
     * Adds an item with a small random deviation to the loot.
     *
     * @param loot  The tile entity loot.
     * @param rand  A random instance.
     * @param stack The item stack to add.
     * @param x     X deviation.
     * @param y     Y deviation.
     * @param z     Z deviation.
     */
    public static void addItemWithDeviation(TileEntityLoot loot, Random rand, ItemStack stack, double x, double y, double z) {
        loot.addItem(stack, x + rand.nextGaussian() * 0.02, y, z + rand.nextGaussian() * 0.02);
    }

    public static void lootBooklet(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            loot.addItem(ItemBookLore.createBook("beacon", 12, 0x404040, 0xD637B3), 0, 0, 0);
        }
    }

    public static void lootCapNuke(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            if (world.getRandom().nextInt(5) == 0)
                loot.addItem(DictFrame.fromOne(ModItems.ammo_standard, EnumAmmo.NUKE_STANDARD), -0.25, 0, -0.125);
            else
                loot.addItem(DictFrame.fromOne(ModItems.ammo_standard, EnumAmmo.ROCKET_HEAT), -0.25, 0, -0.25);

            for (int i = 0; i < 4; i++)
                addItemWithDeviation(loot, world.getRandom(), new ItemStack(ModItems.cap_nuka, 2), 0.125, i * 0.03125, 0.25);
            for (int i = 0; i < 2; i++)
                addItemWithDeviation(loot, world.getRandom(), new ItemStack(ModItems.syringe_metal_stimpak, 1), -0.25, i * 0.03125, 0.25);
            for (int i = 0; i < 6; i++)
                addItemWithDeviation(loot, world.getRandom(), new ItemStack(ModItems.cap_nuka, 2), 0.125, i * 0.03125, -0.25);
        }
    }

    public static void lootMedicine(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            for (int i = 0; i < 4; i++)
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_MED_SYRINGE, world.getRandom()), 0.125, i * 0.03125, 0.25);
            addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_MED_PILLS, world.getRandom()), -0.25, 0, -0.125);
        }
    }

    public static void lootCapStash(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int count = world.getRandom().nextInt(5) + 3;
                    for (int k = 0; k < count; k++) {
                        addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_CAPS, world.getRandom()), i * 0.3125, k * 0.03125, j * 0.3125);
                    }
                }
            }
        }
    }

    public static void lootMakeshiftGun(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            boolean r = world.getRandom().nextBoolean();
            if (r)
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_MAKESHIFT_GUN, world.getRandom()), 0.125, 0.025, 0.25);

            if (!r || world.getRandom().nextBoolean())
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_MAKESHIFT_WRENCH, world.getRandom()), -0.25, 0, -0.28125);

            int count = world.getRandom().nextInt(2) + 1;
            for (int i = 0; i < count; i++)
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_MAKESHIFT_PLATES, world.getRandom()), -0.25, i * 0.03125, 0.3125);

            count = world.getRandom().nextInt(2) + 2;
            for (int i = 0; i < count; i++)
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPoolsPile.POOL_PILE_MAKESHIFT_WIRE, world.getRandom()), 0.25, i * 0.03125, 0.1875);
        }
    }

    public static void lootNukeStorage(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (world.getRandom().nextBoolean()) {
                        loot.addItem(ItemPool.getStack(ItemPoolsPile.POOL_PILE_NUKE_STORAGE, world.getRandom()), -0.375 + i * 0.25, 0, -0.375 + j * 0.25);
                    }
                }
            }
        }
    }

    public static void lootBones(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            int limit = world.getRandom().nextInt(3) + 3;
            for (int i = 0; i < limit; i++) {
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPool.getPool(ItemPoolsPile.POOL_PILE_BONES), world.getRandom()), world.getRandom().nextDouble() - 0.5, i * 0.03125, world.getRandom().nextDouble() - 0.5);
            }
        }
    }

    public static void lootGlyphidHive(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            int limit = world.getRandom().nextInt(3) + 3;
            for (int i = 0; i < limit; i++) {
                addItemWithDeviation(loot, world.getRandom(), ItemPool.getStack(ItemPool.getPool(ItemPoolsPile.POOL_PILE_HIVE), world.getRandom()), world.getRandom().nextDouble() - 0.5, i * 0.03125, world.getRandom().nextDouble() - 0.5);
            }
        }
    }

    public static void lootBookMeteor(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            Item mkuItem = MKUCraftingHandler.getMKUItem(world);
            ItemStack mkuBook = MKUCraftingHandler.generateBook(world, mkuItem);

            addItemWithDeviation(loot, world.getRandom(), new ItemStack(mkuItem), 0, 0, 0.25);
            addItemWithDeviation(loot, world.getRandom(), mkuBook, 0, 0, -0.25);
        }
    }

    public static void lootBookLore(Level world, int x, int y, int z, ItemStack book) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntityLoot loot = (TileEntityLoot) world.getBlockEntity(pos);
        if (loot != null && loot.items.isEmpty()) {
            addItemWithDeviation(loot, world.getRandom(), book, 0, 0, -0.25);

            int count = world.getRandom().nextInt(3) + 2;
            for (int k = 0; k < count; k++)
                addItemWithDeviation(loot, world.getRandom(), new ItemStack(Items.BOOK), -0.25, k * 0.03125, 0.25);

            count = world.getRandom().nextInt(2) + 1;
            for (int k = 0; k < count; k++)
                addItemWithDeviation(loot, world.getRandom(), new ItemStack(Items.PAPER), 0.25, k * 0.03125, 0.125);
        }
    }
}
