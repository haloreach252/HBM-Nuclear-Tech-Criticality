package com.miniverse.hbm.api.item;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Interface for items that designate a target position.
 */
public interface IDesignatorItem {

    /**
     * Determines whether the target is valid.
     * This can include checks for things like restricting dimensions or getting entities.
     *
     * @param level The level (world) in which the target is located.
     * @param stack The item stack, used to check NBT and metadata.
     * @param x The x-coordinate of the launch pad.
     * @param y The y-coordinate of the launch pad.
     * @param z The z-coordinate of the launch pad.
     * @return True if the target is valid, otherwise false.
     */
    boolean isReady(Level level, ItemStack stack, int x, int y, int z);

    /**
     * Returns the target position if the designator is ready.
     *
     * @param level The level (world) in which the target is located.
     * @param stack The item stack, used to check NBT and metadata.
     * @param x The x-coordinate of the launch pad.
     * @param y The y-coordinate of the launch pad.
     * @param z The z-coordinate of the launch pad.
     * @return The target position as a Vec3.
     */
    Vec3 getCoords(Level level, ItemStack stack, int x, int y, int z);
}
