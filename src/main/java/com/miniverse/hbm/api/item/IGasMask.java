package com.miniverse.hbm.api.item;

import java.util.List;

import com.miniverse.hbm.util.ArmorRegistry.HazardClass;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * Interface for gas mask items in the HBM mod.
 */
public interface IGasMask {

    /**
     * Returns a list of HazardClasses which cannot be protected against by this mask (e.g., chlorine gas for half masks).
     * @param stack The item stack of the gas mask.
     * @param entity The entity wearing the mask.
     * @return A list of hazard classes that the mask cannot protect against, or an empty list if there's no blacklist.
     */
    List<HazardClass> getBlacklist(ItemStack stack, LivingEntity entity);

    /**
     * Returns the loaded filter, if there is any.
     * @param stack The item stack of the gas mask.
     * @param entity The entity wearing the mask.
     * @return The filter item stack, or null if no filter is installed.
     */
    ItemStack getFilter(ItemStack stack, LivingEntity entity);

    /**
     * Checks whether the provided filter can be screwed into the mask, ignoring already applied filters (those get ejected).
     * @param stack The item stack of the gas mask.
     * @param entity The entity wearing the mask.
     * @param filter The filter item stack to check.
     * @return True if the filter can be applied, false otherwise.
     */
    boolean isFilterApplicable(ItemStack stack, LivingEntity entity, ItemStack filter);

    /**
     * Installs the provided filter to the gas mask, replacing any previously installed filter.
     * @param stack The item stack of the gas mask.
     * @param entity The entity wearing the mask.
     * @param filter The filter item stack to install.
     */
    void installFilter(ItemStack stack, LivingEntity entity, ItemStack filter);

    /**
     * Damages the installed filter, if there is one.
     * @param stack The item stack of the gas mask.
     * @param entity The entity wearing the mask.
     * @param damage The amount of damage to deal to the filter.
     */
    void damageFilter(ItemStack stack, LivingEntity entity, int damage);
}
