package com.miniverse.hbm.util;

import com.miniverse.hbm.config.VersatileConfig;
import com.miniverse.hbm.interfaces.ICustomWarhead.SaltedFuel.HalfLifeType;
import com.miniverse.hbm.items.machine.ItemRTGPellet;
import net.minecraft.world.item.ItemStack;

public class RTGUtil {

    /**
     * Returns the power (heat) output of an RTG pellet.
     * If the configuration scales RTG power, it uses the scaled value;
     * otherwise, it returns the base heat value of the pellet.
     *
     * @param fuel  The RTG pellet item
     * @param stack The ItemStack holding the pellet
     * @return The computed power as a short value.
     */
    public static short getPower(ItemRTGPellet fuel, ItemStack stack) {
        return VersatileConfig.scaleRTGPower() ? ItemRTGPellet.getScaledPower(fuel, stack) : fuel.getHeat();
    }

    /**
     * Checks an array of ItemStacks to see if any slot (specified by rtgSlots) contains an RTG pellet.
     *
     * @param inventory An array of ItemStacks representing an inventory.
     * @param rtgSlots  An array of slot indices to check.
     * @return true if any slot holds an RTG pellet; false otherwise.
     */
    public static boolean hasHeat(ItemStack[] inventory, int[] rtgSlots) {
        for (int slot : rtgSlots) {
            if (inventory[slot].isEmpty())
                continue;

            if (inventory[slot].getItem() instanceof ItemRTGPellet)
                return true;
        }
        return false;
    }

    /**
     * Iterates over the specified slots in the inventory, updates the decay of each RTG pellet,
     * sums the current power (heat) from all pellets, and returns the total heat.
     *
     * @param inventory An array of ItemStacks representing an inventory.
     * @param rtgSlots  An array of slot indices where RTG pellets are stored.
     * @return The sum of the heat values from all RTG pellets.
     */
    public static int updateRTGs(ItemStack[] inventory, int[] rtgSlots) {
        int newHeat = 0;
        for (int slot : rtgSlots) {
            if (inventory[slot].isEmpty())
                continue;

            if (!(inventory[slot].getItem() instanceof ItemRTGPellet))
                continue;

            final ItemRTGPellet pellet = (ItemRTGPellet) inventory[slot].getItem();
            newHeat += getPower(pellet, inventory[slot]);
            inventory[slot] = ItemRTGPellet.handleDecay(inventory[slot], pellet);
        }
        return newHeat;
    }

    /**
     * Calculates the lifespan of an RTG pellet based on its half-life.
     * The calculation factors in the unit of half-life (LONG, MEDIUM, or SHORT) and whether
     * the calculation should use real calendar years (365 days) or a simplified 100-day year.
     *
     * @param halfLife  The half-life value.
     * @param type      The unit/type of the half-life.
     * @param realYears If true, uses 365 days per year; otherwise, uses 100 days.
     * @return The lifespan converted into Minecraft ticks.
     */
    public static long getLifespan(float halfLife, HalfLifeType type, boolean realYears) {
        float life = 0;
        switch (type) {
            case LONG:
                life = (48000 * (realYears ? 365 : 100) * 100) * halfLife;
                break;
            case MEDIUM:
                life = (48000 * (realYears ? 365 : 100)) * halfLife;
                break;
            case SHORT:
                life = 48000 * halfLife;
                break;
        }
        return (long) life;
    }
}
