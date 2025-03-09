package com.miniverse.hbm.util;

import com.miniverse.hbm.inventory.RecipesCommon.AStack;
import com.miniverse.hbm.inventory.recipes.anvil.AnvilRecipes.AnvilOutput;
import com.miniverse.hbm.main.MainRegistry;
import com.miniverse.hbm.tileentity.machine.TileEntityFurnaceBrick;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.common.util.NonNullSupplier;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class InventoryUtil {

    // Returns a masqueraded slot array for sided inventories.
    // For certain types (furnace or custom furnace brick), it returns a hard-coded mapping.
    public static int[] masquerade(net.minecraft.world.inventory.SidedInventory sided, int side) {
        // Note: In modern Minecraft, FurnaceBlockEntity replaces TileEntityFurnace.
        if (sided instanceof net.minecraft.world.level.block.entity.FurnaceBlockEntity)
            return new int[] {1, 0};
        if (sided instanceof TileEntityFurnaceBrick)
            return new int[] {1, 0, 3};

        return sided.getSlotsForFace(side);
    }

    // Attempts to add as much of the given ItemStack into the provided inventory array as possible.
    // Returns the remainder (or ItemStack.EMPTY if fully added).
    public static ItemStack tryAddItemToInventory(ItemStack[] inv, int start, int end, ItemStack stack) {
        ItemStack rem = tryAddItemToExistingStack(inv, start, end, stack);
        if (rem.isEmpty())
            return ItemStack.EMPTY;
        boolean didAdd = tryAddItemToNewSlot(inv, start, end, rem);
        return didAdd ? ItemStack.EMPTY : rem;
    }

    public static ItemStack tryAddItemToInventory(ItemStack[] inv, ItemStack stack) {
        return tryAddItemToInventory(inv, 0, inv.length - 1, stack);
    }

    // Attempts to add the given ItemStack into existing stacks (without creating new stacks).
    // Returns the remainder (or ItemStack.EMPTY if fully added).
    public static ItemStack tryAddItemToExistingStack(ItemStack[] inv, int start, int end, ItemStack stack) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        for (int i = start; i <= end; i++) {
            if (doesStackDataMatch(inv[i], stack)) {
                int available = inv[i].getMaxStackSize() - inv[i].getCount();
                int transfer = Math.min(stack.getCount(), available);
                if (transfer > 0) {
                    inv[i].setCount(inv[i].getCount() + transfer);
                    stack.shrink(transfer);
                    if (stack.isEmpty())
                        return ItemStack.EMPTY;
                }
            }
        }
        return stack;
    }

    // Places the stack in the first empty slot.
    // Returns true if the stack was successfully added.
    public static boolean tryAddItemToNewSlot(ItemStack[] inv, int start, int end, ItemStack stack) {
        if (stack.isEmpty())
            return true;
        for (int i = start; i <= end; i++) {
            if (inv[i].isEmpty()) {
                inv[i] = stack;
                return true;
            }
        }
        return false;
    }

    // Same as tryAddItemToInventory but works on an IInventory instance.
    public static ItemStack tryAddItemToInventory(Container inv, int start, int end, ItemStack stack) {
        ItemStack rem = tryAddItemToExistingStack(inv, start, end, stack);
        if (rem.isEmpty())
            return ItemStack.EMPTY;
        boolean didAdd = tryAddItemToNewSlot(inv, start, end, rem);
        return didAdd ? ItemStack.EMPTY : rem;
    }

    public static ItemStack tryAddItemToExistingStack(Container inv, int start, int end, ItemStack stack) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;
        for (int i = start; i <= end; i++) {
            ItemStack current = inv.getItem(i);
            if (doesStackDataMatch(current, stack)) {
                int available = current.getMaxStackSize() - current.getCount();
                int transfer = Math.min(stack.getCount(), available);
                if (transfer > 0) {
                    current.setCount(current.getCount() + transfer);
                    stack.shrink(transfer);
                    if (stack.isEmpty())
                        return ItemStack.EMPTY;
                }
            }
        }
        return stack;
    }

    public static boolean tryAddItemToNewSlot(Container inv, int start, int end, ItemStack stack) {
        if (stack.isEmpty())
            return true;
        for (int i = start; i <= end; i++) {
            if (inv.getItem(i).isEmpty()) {
                inv.setItem(i, stack);
                return true;
            }
        }
        return false;
    }

    // Attempts to consume items from the provided ItemStack array according to the recipe defined in AStack.
    public static boolean tryConsumeAStack(ItemStack[] inv, int start, int end, AStack stack) {
        if (stack == null)
            return true;
        AStack copy = stack.copy();
        for (int i = start; i <= end; i++) {
            ItemStack in = inv[i];
            if (!in.isEmpty() && stack.matchesRecipe(in, true)) {
                int toTransfer = Math.min(copy.stacksize, in.getCount());
                in.shrink(toTransfer);
                copy.stacksize -= toTransfer;
                if (in.getCount() == 0) {
                    inv[i] = ItemStack.EMPTY;
                }
                if (copy.stacksize == 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * Compares item, metadata, and NBT data of two stacks. Also handles empty stacks.
     */
    public static boolean doesStackDataMatch(ItemStack stack1, ItemStack stack2) {
        if (stack1.isEmpty() && stack2.isEmpty())
            return true;
        if (stack1.isEmpty() || stack2.isEmpty())
            return false;
        if (stack1.getItem() != stack2.getItem())
            return false;
        if (stack1.getDamageValue() != stack2.getDamageValue())
            return false;
        if (!stack1.hasTag() && !stack2.hasTag())
            return true;
        if (stack1.hasTag() != stack2.hasTag())
            return false;
        return stack1.getTag().equals(stack2.getTag());
    }

    /**
     * Checks if a player has matching item stacks (as defined by a list of AStacks) in their inventory.
     * If shouldRemove is true, it also removes the items.
     */
    public static boolean doesPlayerHaveAStacks(Player player, List<AStack> stacks, boolean shouldRemove) {
        // Copy player's inventory (assume main inventory as a NonNullList)
        ItemStack[] original = player.getInventory().items.toArray(new ItemStack[0]);
        ItemStack[] inventory = new ItemStack[original.length];
        boolean[] modified = new boolean[original.length];
        AStack[] input = new AStack[stacks.size()];
        for (int i = 0; i < input.length; i++) {
            input[i] = stacks.get(i).copy();
        }
        for (int i = 0; i < original.length; i++) {
            if (!original[i].isEmpty()) {
                inventory[i] = original[i].copy();
            }
        }
        // Process each ingredient
        for (int i = 0; i < input.length; i++) {
            AStack ingredient = input[i];
            if (ingredient == null)
                continue;
            for (ItemStack invStack : inventory) {
                if (invStack.isEmpty())
                    continue;
                if (ingredient.matchesRecipe(invStack, true)) {
                    int size = Math.min(invStack.getCount(), ingredient.stacksize);
                    ingredient.stacksize -= size;
                    invStack.shrink(size);
                    // Mark modified slot if needed
                    // (No index available here, but we rely on later full scan.)
                    if (ingredient.stacksize <= 0) {
                        input[i] = null;
                        break;
                    }
                }
            }
            if (ingredient.stacksize > 0)
                return false;
        }
        if (shouldRemove) {
            // Write changes back to the player's inventory.
            for (int i = 0; i < original.length; i++) {
                if (!inventory[i].isEmpty() && inventory[i].getCount() <= 0) {
                    original[i] = ItemStack.EMPTY;
                } else if (modified[i]) {
                    original[i] = inventory[i];
                }
            }
            // Note: In modern Minecraft, you would update the player's inventory via proper container methods.
        }
        return true;
    }

    public static void giveChanceStacksToPlayer(Player player, List<AnvilOutput> stacks) {
        Random rand = player.getRandom();
        for (AnvilOutput out : stacks) {
            if (out.chance == 1.0F || rand.nextFloat() < out.chance) {
                ItemStack copy = out.stack.copy();
                if (!player.getInventory().add(copy)) {
                    player.drop(copy, false);
                }
            }
        }
    }

    // --- Reworked Ore Dictionary methods using tags instead ---

    /**
     * Checks if the player has at least the given count of items that belong to the specified tag.
     * The 'dict' string should be a valid resource location (e.g. "forge:ingots/iron").
     */
    public static boolean hasOreDictMatches(Player player, String dict, int count) {
        return countOreDictMatches(player, dict) >= count;
    }

    /**
     * Counts the total number of items in the player's inventory that belong to the specified tag.
     */
    public static int countOreDictMatches(Player player, String dict) {
        TagKey<Item> tag = TagKey.create(Registry.ITEM, new ResourceLocation(dict));
        int total = 0;
        for (ItemStack stack : player.getInventory().items) {
            if (!stack.isEmpty() && stack.is(tag)) {
                total += stack.getCount();
            }
        }
        return total;
    }

    /**
     * Consumes the specified number of items from the player's inventory that belong to the given tag.
     */
    public static void consumeOreDictMatches(Player player, String dict, int count) {
        TagKey<Item> tag = TagKey.create(Registry.ITEM, new ResourceLocation(dict));
        for (int i = 0; i < player.getInventory().items.size() && count > 0; i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (!stack.isEmpty() && stack.is(tag)) {
                int toConsume = Math.min(count, stack.getCount());
                stack.shrink(toConsume);
                count -= toConsume;
            }
        }
    }

    // --- End tag-based methods ---

    /**
     * Turns objects into 2D ItemStack arrays.
     * Supports:
     * - ItemStack
     * - ItemStack[]
     * - ItemStack[][]
     * - AStack and AStack[]
     * - Mixed Object[] arrays
     */
    public static ItemStack[][] extractObject(Object o) {
        if (o instanceof ItemStack) {
            ItemStack[][] stacks = new ItemStack[1][1];
            stacks[0][0] = ((ItemStack) o).copy();
            return stacks;
        }
        if (o instanceof ItemStack[]) {
            ItemStack[] ingredients = (ItemStack[]) o;
            ItemStack[][] stacks = new ItemStack[ingredients.length][1];
            for (int i = 0; i < ingredients.length; i++) {
                stacks[i][0] = ingredients[i].copy();
            }
            return stacks;
        }
        if (o instanceof ItemStack[][]) {
            return (ItemStack[][]) o;
        }
        if (o instanceof AStack) {
            AStack astack = (AStack) o;
            ItemStack[] ext = astack.extractForNEI().toArray(new ItemStack[0]);
            ItemStack[][] stacks = new ItemStack[1][ext.length];
            stacks[0] = ext;
            return stacks;
        }
        if (o instanceof AStack[]) {
            AStack[] ingredients = (AStack[]) o;
            ItemStack[][] stacks = new ItemStack[ingredients.length][];
            for (int i = 0; i < ingredients.length; i++) {
                stacks[i] = ingredients[i].extractForNEI().toArray(new ItemStack[0]);
            }
            return stacks;
        }
        if (o instanceof Object[]) {
            Object[] ingredients = (Object[]) o;
            ItemStack[][] stacks = new ItemStack[ingredients.length][];
            for (int i = 0; i < ingredients.length; i++) {
                Object ingredient = ingredients[i];
                if (ingredient instanceof AStack) {
                    stacks[i] = ((AStack) ingredient).extractForNEI().toArray(new ItemStack[0]);
                } else if (ingredient instanceof ItemStack) {
                    stacks[i] = new ItemStack[1];
                    stacks[i][0] = ((ItemStack) ingredient).copy();
                }
            }
            return stacks;
        }
        MainRegistry.logger.warn("InventoryUtil: extractObject failed for type " + o);
        return new ItemStack[0][0];
    }

    public static boolean doesArrayHaveIngredients(ItemStack[] array, int start, int end, AStack... ingredients) {
        ItemStack[] copy = carefulCopyArrayTruncate(array, start, end);
        AStack[] req = new AStack[ingredients.length];
        for (int i = 0; i < req.length; i++) {
            req[i] = ingredients[i] == null ? null : ingredients[i].copy();
        }
        for (AStack ingredient : req) {
            if (ingredient == null)
                continue;
            for (ItemStack input : copy) {
                if (input == null || input.isEmpty())
                    continue;
                if (ingredient.matchesRecipe(input, true)) {
                    int size = Math.min(input.getCount(), ingredient.stacksize);
                    ingredient.stacksize -= size;
                    input.shrink(size);
                    if (ingredient.stacksize == 0)
                        break;
                }
            }
            if (ingredient.stacksize > 0)
                return false;
        }
        return true;
    }

    public static boolean doesArrayHaveSpace(ItemStack[] array, int start, int end, ItemStack[] items) {
        ItemStack[] copy = carefulCopyArrayTruncate(array, start, end);
        for (ItemStack item : items) {
            if (item == null || item.isEmpty())
                continue;
            ItemStack remainder = tryAddItemToInventory(copy, item.copy());
            if (!remainder.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * A fixed re-implementation of the original Container.mergeItemStack that respects stack size and slot restrictions.
     */
    public static boolean mergeItemStack(List<Slot> slots, ItemStack stack, int start, int end, boolean reverse) {
        boolean success = false;
        int index = reverse ? end - 1 : start;
        while (!stack.isEmpty() && (reverse ? index >= start : index < end)) {
            Slot slot = slots.get(index);
            ItemStack current = slot.getItem();
            if (!current.isEmpty() && current.getItem() == stack.getItem() &&
                    (!stack.hasTag() || ItemStack.tagMatches(stack, current))) {
                int max = Math.min(stack.getMaxStackSize(), slot.getMaxStackSize());
                int combined = current.getCount() + stack.getCount();
                if (combined <= max) {
                    current.setCount(combined);
                    stack.setCount(0);
                    slot.set(current);
                    success = true;
                } else if (current.getCount() < max) {
                    int diff = max - current.getCount();
                    current.setCount(max);
                    stack.shrink(diff);
                    slot.set(current);
                    success = true;
                }
            }
            index = reverse ? index - 1 : index + 1;
        }
        if (!stack.isEmpty()) {
            index = reverse ? end - 1 : start;
            while ((reverse ? index >= start : index < end) && !stack.isEmpty()) {
                Slot slot = slots.get(index);
                ItemStack current = slot.getItem();
                if (current.isEmpty() && slot.mayPlace(stack)) {
                    int max = Math.min(stack.getMaxStackSize(), slot.getMaxStackSize());
                    int toTransfer = Math.min(stack.getCount(), max);
                    ItemStack split = stack.split(toTransfer);
                    slot.set(split);
                    success = true;
                }
                index = reverse ? index - 1 : index + 1;
            }
        }
        return success;
    }

    public static int countAStackMatches(ItemStack[] inventory, AStack stack, boolean ignoreSize) {
        int count = 0;
        for (ItemStack itemStack : inventory) {
            if (!itemStack.isEmpty() && stack.matchesRecipe(itemStack, true)) {
                count += itemStack.getCount();
            }
        }
        return ignoreSize ? count : count / stack.stacksize;
    }

    public static int countAStackMatches(Player player, AStack stack, boolean ignoreSize) {
        // Convert player's inventory to an array
        return countAStackMatches(player.getInventory().items.toArray(new ItemStack[0]), stack, ignoreSize);
    }

    public static boolean doesPlayerHaveAStack(Player player, AStack stack, boolean shouldRemove, boolean ignoreSize) {
        return doesInventoryHaveAStack(player.getInventory().items.toArray(new ItemStack[0]), stack, shouldRemove, ignoreSize);
    }

    public static boolean doesInventoryHaveAStack(ItemStack[] inventory, AStack stack, boolean shouldRemove, boolean ignoreSize) {
        int totalStacks = 0;
        for (ItemStack itemStack : inventory) {
            if (!itemStack.isEmpty() && stack.matchesRecipe(itemStack, ignoreSize)) {
                totalStacks += itemStack.getCount();
            }
        }
        int totalMatches = ignoreSize ? totalStacks : totalStacks / stack.stacksize;
        if (shouldRemove) {
            int consumed = 0;
            int required = ignoreSize ? 1 : stack.stacksize;
            for (ItemStack itemStack : inventory) {
                if (consumed >= required)
                    break;
                if (!itemStack.isEmpty() && stack.matchesRecipe(itemStack, true)) {
                    int toConsume = Math.min(itemStack.getCount(), required - consumed);
                    itemStack.shrink(toConsume);
                    consumed += toConsume;
                }
            }
        }
        return totalMatches > 0;
    }
}
