package com.miniverse.hbm.api.item;

import java.util.List;

import com.miniverse.hbm.util.Tuple.Pair;
import com.miniverse.hbm.util.Tuple.Triplet;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * Interface for providing HUD (Heads-Up Display) information for guns.
 */
public interface IGunHUDProvider {

    /**
     * Gets the progress for the status bar right of the toolbar.
     * Usually reserved for durability, but now supports multiple bars for things like overheating or special charges.
     *
     * @param stack The item stack (gun) to get the status bars for.
     * @param player The player using the gun.
     * @return A list of triplets containing progress, foreground color, and background color for each status bar.
     */
    List<Triplet<Double, Integer, Integer>> getStatusBars(ItemStack stack, Player player);

    /**
     * Gets a list containing preview icons for loaded ammo and the corresponding ammo count.
     * This can also be used for other HUD elements such as tooltips or a built-in compass.
     *
     * @param stack The item stack (gun) to get the ammo info for.
     * @param player The player using the gun.
     * @return A list of pairs containing the ammo preview icon and ammo count text.
     */
    List<Pair<ModelResourceLocation, String>> getAmmoInfo(ItemStack stack, Player player);
}
