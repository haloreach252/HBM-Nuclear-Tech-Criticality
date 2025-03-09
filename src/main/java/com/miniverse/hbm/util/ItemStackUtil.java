package com.miniverse.hbm.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemStackUtil {

    public static ItemStack carefulCopy(ItemStack stack) {
        if (stack == null)
            return null;
        return stack.copy();
    }

    public static ItemStack carefulCopyWithSize(ItemStack stack, int size) {
        if (stack == null)
            return null;

        ItemStack copy = stack.copy();
        copy.setCount(size);
        return copy;
    }

    /**
     * Runs carefulCopy over the entire ItemStack array.
     * @param array The source array.
     * @return A new array with copied stacks.
     */
    public static ItemStack[] carefulCopyArray(ItemStack[] array) {
        return carefulCopyArray(array, 0, array.length - 1);
    }

    /**
     * Recreates the ItemStack array and only runs carefulCopy over the supplied range.
     * All other indices remain null.
     * @param array The source array.
     * @param start The starting index.
     * @param end The ending index.
     * @return A new array with copied stacks in the given range.
     */
    public static ItemStack[] carefulCopyArray(ItemStack[] array, int start, int end) {
        if (array == null)
            return null;

        ItemStack[] copy = new ItemStack[array.length];
        for (int i = start; i <= end; i++) {
            copy[i] = carefulCopy(array[i]);
        }
        return copy;
    }

    /**
     * Creates a new array that only contains the copied range.
     * @param array The source array.
     * @param start The starting index.
     * @param end The ending index.
     * @return A new truncated array with copied stacks.
     */
    public static ItemStack[] carefulCopyArrayTruncate(ItemStack[] array, int start, int end) {
        if (array == null)
            return null;

        int length = end - start + 1;
        ItemStack[] copy = new ItemStack[length];
        for (int i = 0; i < length; i++) {
            copy[i] = carefulCopy(array[start + i]);
        }
        return copy;
    }

    /**
     * UNSAFE! Overrides all existing display tags. (Use only for items known not to have pre-existing display tags.)
     * Respects other NBT data.
     * @param stack The item stack.
     * @param lines Tooltip lines to add.
     * @return The modified item stack.
     */
    public static ItemStack addTooltipToStack(ItemStack stack, String... lines) {
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag display = new CompoundTag();
        ListTag lore = new ListTag();

        for (String line : lines) {
            lore.add(StringTag.valueOf(ChatFormatting.RESET.toString() + ChatFormatting.GRAY.toString() + line));
        }
        display.put("Lore", lore);
        tag.put("display", display);
        return stack;
    }

    /**
     * Writes multiple ItemStacks into the given stack's NBT data.
     * @param stack The container item stack.
     * @param stacks The item stacks to write.
     */
    public static void addStacksToNBT(ItemStack stack, ItemStack... stacks) {
        CompoundTag tag = stack.getOrCreateTag();
        ListTag list = new ListTag();

        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i] != null && !stacks[i].isEmpty()) {
                CompoundTag slotNBT = new CompoundTag();
                slotNBT.putByte("slot", (byte) i);
                stacks[i].save(slotNBT);
                list.add(slotNBT);
            }
        }
        tag.put("items", list);
    }

    /**
     * Reads ItemStacks stored in the given stack's NBT data.
     * @param stack The container item stack.
     * @param count Number of slots to read (if 0, reads all).
     * @return An array of ItemStacks loaded from NBT.
     */
    public static ItemStack[] readStacksFromNBT(ItemStack stack, int count) {
        if (!stack.hasTag())
            return null;

        CompoundTag tag = stack.getTag();
        ListTag list = tag.getList("items", 10); // 10 represents CompoundTag type
        if (count == 0) {
            count = list.size();
        }

        ItemStack[] stacks = new ItemStack[count];
        for (int i = 0; i < list.size(); i++) {
            CompoundTag slotNBT = list.getCompound(i);
            byte slot = slotNBT.getByte("slot");
            ItemStack loadedStack = ItemStack.of(slotNBT);
            if (slot >= 0 && slot < stacks.length && !loadedStack.isEmpty()) {
                stacks[slot] = loadedStack;
            }
        }
        return stacks;
    }

    public static ItemStack[] readStacksFromNBT(ItemStack stack) {
        return readStacksFromNBT(stack, 0);
    }

    /**
     * Returns a list of all item tag names for the given item stack.
     * This method now uses the new tag system instead of the old Ore Dictionary.
     *
     * @param stack The item stack (must not be null).
     * @return A List of tag names; empty if none exist.
     */
    public static List<String> getOreDictNames(ItemStack stack) {
        List<String> list = new ArrayList<>();
        // Iterate over the tags registered to the item via its built-in registry holder.
        for (TagKey<Item> tag : stack.getItem().builtInRegistryHolder().tags()) {
            list.add(tag.location().toString());
        }
        return list;
    }

    /**
     * Spills items from a container block into the world with random motion.
     * @param world The current level.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     * @param block The block used for neighbor updates.
     * @param rand A random instance.
     */
    public static void spillItems(Level world, int x, int y, int z, Block block, Random rand) {
        BlockPos pos = new BlockPos(x, y, z);
        // Cast to Container; adjust this cast if your block entity implements a different inventory interface.
        Container container = (Container) world.getBlockEntity(pos);
        if (container != null) {
            for (int slot = 0; slot < container.getContainerSize(); ++slot) {
                ItemStack itemstack = container.getItem(slot);
                if (!itemstack.isEmpty()) {
                    float oX = rand.nextFloat() * 0.8F + 0.1F;
                    float oY = rand.nextFloat() * 0.8F + 0.1F;
                    float oZ = rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.getCount() > 0) {
                        int j1 = rand.nextInt(21) + 10;
                        if (j1 > itemstack.getCount())
                            j1 = itemstack.getCount();
                        itemstack.setCount(itemstack.getCount() - j1);

                        ItemStack droppedStack = new ItemStack(itemstack.getItem(), j1, itemstack.getDamageValue());
                        if (itemstack.hasTag()) {
                            droppedStack.setTag(itemstack.getTag().copy());
                        }
                        ItemEntity entityItem = new ItemEntity(world, x + oX, y + oY, z + oZ, droppedStack);
                        float motion = 0.05F;
                        entityItem.setDeltaMovement(rand.nextGaussian() * motion, rand.nextGaussian() * motion + 0.2F, rand.nextGaussian() * motion);
                        world.addFreshEntity(entityItem);
                    }
                }
            }
            world.updateNeighborsAt(pos, block);
        }
    }
}
