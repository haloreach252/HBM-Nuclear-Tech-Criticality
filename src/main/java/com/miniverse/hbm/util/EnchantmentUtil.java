package com.miniverse.hbm.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class EnchantmentUtil {

    /**
     * Adds an enchantment of the given level to the supplied itemstack.
     *
     * In modern Forge you can often simply call the built-in method; however,
     * if the method is unavailable you can use EnchantmentHelper to add it manually.
     *
     * @param stack the item stack to enchant
     * @param enchantment the enchantment to add
     * @param level the level of the enchantment
     */
    public static void addEnchantment(ItemStack stack, Enchantment enchantment, int level) {
        // If the following method is available, it is preferred:
        stack.addEnchantment(enchantment, level);
        // Otherwise, you can alternatively do:
        // Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
        // enchants.put(enchantment, level);
        // EnchantmentHelper.setEnchantments(enchants, stack);
    }

    /**
     * Removes an enchantment from the given itemstack, regardless of level.
     *
     * In modern Forge, enchantments are stored in the NBT under "Enchantments",
     * so we use EnchantmentHelper to safely remove one.
     *
     * @param stack the item stack to modify
     * @param enchantment the enchantment to remove
     */
    public static void removeEnchantment(ItemStack stack, Enchantment enchantment) {
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);
        if (enchants.containsKey(enchantment)) {
            enchants.remove(enchantment);
            EnchantmentHelper.setEnchantments(enchants, stack);
        }
    }

    /**
     * Returns the size of the XP bar for the given level using a custom formula.
     * (Note: vanilla Minecraft uses a different formula.)
     *
     * @param level the player level
     * @return the XP cap for that level
     */
    public static int xpBarCap(int level) {
        return level >= 30 ? 62 + (level - 30) * 7 : (level >= 15 ? 17 + (level - 15) * 3 : 17);
    }

    /**
     * Calculates the level corresponding to a given total amount of experience.
     *
     * @param xp the total experience points
     * @return the computed level
     */
    public static int getLevelForExperience(int xp) {
        int level = 0;
        while (true) {
            int xpCap = xpBarCap(level);
            if (xp < xpCap)
                return level;
            xp -= xpCap;
            level++;
        }
    }

    /**
     * Adds the specified amount of experience to the player.
     * This method mimics the vanilla experience addition logic (except it does not increase the player’s score),
     * leveling up the player as needed.
     *
     * The method uses the player’s totalExperience, experienceProgress, and experienceLevel.
     *
     * @param player the player to add XP to
     * @param xp the amount of experience to add
     * @param silent if true, levels are added silently (via addExperienceLevelSilent); otherwise, via giveExperienceLevels
     */
    public static void addExperience(Player player, int xp, boolean silent) {
        int maxXP = Integer.MAX_VALUE - player.totalExperience;
        if (xp > maxXP) {
            xp = maxXP;
        }

        // Calculate the absolute XP in the current level from the fractional progress
        int currentLevel = player.experienceLevel;
        int xpNeeded = xpBarCap(currentLevel);
        float currentXPAbs = player.experienceProgress * xpNeeded;
        currentXPAbs += xp;
        player.totalExperience += xp;

        // Level up as long as the XP in the current level exceeds the cap
        while (currentXPAbs >= xpNeeded) {
            currentXPAbs -= xpNeeded;
            if (silent)
                addExperienceLevelSilent(player, 1);
            else
                player.giveExperienceLevels(1);
            xpNeeded = xpBarCap(player.experienceLevel);
        }
        // Update the progress as a fraction of the new level's cap
        player.experienceProgress = currentXPAbs / (float) xpNeeded;
    }

    /**
     * Sets the player’s experience to a specific total by first resetting their XP
     * and then adding the specified XP silently.
     *
     * @param player the player whose XP is to be set
     * @param xp the total experience points to set
     */
    public static void setExperience(Player player, int xp) {
        player.experienceLevel = 0;
        player.experienceProgress = 0.0F;
        player.totalExperience = 0;
        addExperience(player, xp, true);
    }

    /**
     * Adds levels to the player without the usual side effects.
     *
     * If the resulting level is negative (which should not happen), it resets the XP fields.
     *
     * @param player the player to modify
     * @param level the number of levels to add
     */
    public static void addExperienceLevelSilent(Player player, int level) {
        player.experienceLevel += level;
        if (player.experienceLevel < 0) {
            player.experienceLevel = 0;
            player.experienceProgress = 0.0F;
            player.totalExperience = 0;
        }
    }

    /**
     * Computes the total experience of a player based on their level and XP progress.
     *
     * This is calculated by summing the XP required for each complete level and then
     * adding the partial progress in the current level.
     *
     * @param player the player whose XP is computed
     * @return the computed total experience points
     */
    public static int getTotalExperience(Player player) {
        int xp = 0;
        for (int i = 0; i < player.experienceLevel; i++) {
            xp += xpBarCap(i);
        }
        xp += xpBarCap(player.experienceLevel) * player.experienceProgress;
        return xp;
    }
}
