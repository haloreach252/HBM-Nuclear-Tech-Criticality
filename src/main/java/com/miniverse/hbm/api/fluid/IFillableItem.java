package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import net.minecraft.world.item.ItemStack;

/**
 * Interface for items that can store and transfer fluids.
 */
public interface IFillableItem {

    /**
     * Determines whether the given stack can be filled with the specified fluid type.
     * Not particularly useful for normal operations.
     *
     * @param type The fluid type.
     * @param stack The item stack being checked.
     * @return True if the stack can accept this fluid, false otherwise.
     */
    boolean acceptsFluid(FluidType type, ItemStack stack);

    /**
     * Tries to fill the item stack with the given fluid and returns the remaining amount that couldn't be added.
     *
     * @param type The fluid type being added.
     * @param amount The amount of fluid to insert.
     * @param stack The item stack being filled.
     * @return The amount of fluid that could not be added.
     */
    int tryFill(FluidType type, int amount, ItemStack stack);

    /**
     * Determines whether the given stack can be used to fill other containers with the specified fluid type.
     * Not particularly useful for normal operations.
     *
     * @param type The fluid type.
     * @param stack The item stack being checked.
     * @return True if the stack can provide this fluid, false otherwise.
     */
    boolean providesFluid(FluidType type, ItemStack stack);

    /**
     * Attempts to empty the specified amount of fluid from the item stack.
     *
     * @param type The fluid type being removed.
     * @param amount The amount of fluid to extract.
     * @param stack The item stack being emptied.
     * @return The amount of fluid successfully removed.
     */
    int tryEmpty(FluidType type, int amount, ItemStack stack);

    /**
     * Retrieves the first (or only) fluid type currently stored in the item.
     * May return null if no fluid is present. Currently only used for setting bedrock ores.
     *
     * @param stack The item stack being checked.
     * @return The fluid type stored in the item, or null if none is present.
     */
    FluidType getFirstFluidType(ItemStack stack);

    /**
     * Returns the current fill level of the specified fluid in the item stack.
     * Currently only used for setting bedrock ores.
     *
     * @param stack The item stack being checked.
     * @return The amount of fluid currently stored.
     */
    int getFill(ItemStack stack);
}
