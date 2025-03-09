package com.miniverse.hbm.util;

import com.miniverse.hbm.api.energymk2.IBatteryItem;
import com.miniverse.hbm.api.energymk2.IEnergyHandlerMK2;
import com.miniverse.hbm.api.fluid.IFluidUser;
import com.miniverse.hbm.api.tile.IInfoProviderEC;
import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.Fluids;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineGasCent;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineGasCent.PseudoFluidTank;
import com.miniverse.hbm.tileentity.machine.rbmk.TileEntityRBMKBase;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides compatibility data for Energy Control (EC) systems.
 *
 * This class offers helper methods that:
 * - Supply crafting materials.
 * - Check and discharge electric items (like batteries or powertools).
 * - Retrieve energy data (current power and capacity) from a BlockEntity.
 * - Get heat values from RBMK reactors.
 * - Retrieve fluid tank information from various machines.
 * - Gather extra data via IInfoProviderEC.
 * - Delegate core BlockEntity lookup for multi-block systems.
 * - Resolve fluid textures.
 *
 * Data keys (constants) for extra mod-specific statistics are defined at the bottom.
 */
public class CompatEnergyControl {

    /**
     * Returns the steel ingot used for crafting the sensor kit.
     *
     * @return an ItemStack containing the steel ingot.
     */
    public static ItemStack getCraftingMaterial() {
        return new ItemStack(ModItems.ingot_steel);
    }

    /**
     * Checks if the given item stack is an electric item (implements IBatteryItem).
     *
     * @param stack the item stack to check
     * @return true if the item is electric; false otherwise.
     */
    public static boolean isElectricItem(ItemStack stack) {
        return stack.getItem() instanceof IBatteryItem;
    }

    /**
     * Discharges the given electric item (IBatteryItem) by the needed amount.
     *
     * @param stack the item stack to discharge
     * @param needed the amount of energy needed
     * @return the amount that was actually discharged.
     */
    public static double dischargeItem(ItemStack stack, double needed) {
        IBatteryItem battery = (IBatteryItem) stack.getItem();
        long toDischarge = Math.min(battery.getDischargeRate(), Math.min(battery.getCharge(stack), (long) needed));
        battery.dischargeBattery(stack, toDischarge);
        return toDischarge;
    }

    /**
     * Writes energy data (type, current energy, and capacity) into the provided CompoundTag.
     *
     * @param tile the BlockEntity to query (must implement IEnergyHandlerMK2)
     * @param data the CompoundTag to store the data.
     */
    public static void getEnergyData(BlockEntity tile, CompoundTag data) {
        data.putString(KEY_EUTYPE, "HE");

        if (tile instanceof IEnergyHandlerMK2) {
            IEnergyHandlerMK2 user = (IEnergyHandlerMK2) tile;
            data.putDouble(L_ENERGY_HE, user.getPower());
            data.putDouble(L_CAPACITY_HE, user.getMaxPower());
        }
    }

    /**
     * Returns the heat value for RBMK reactors.
     *
     * @param tile the BlockEntity to query (expected to be a TileEntityRBMKBase)
     * @return the heat value or -1 if not applicable.
     */
    public static int getHeat(BlockEntity tile) {
        if (tile instanceof TileEntityRBMKBase) {
            return (int) ((TileEntityRBMKBase) tile).heat;
        }
        // Original implementation also considered SNR and LNR; these no longer exist.
        return -1;
    }

    /**
     * Returns a list of fluid tank information arrays from the given BlockEntity.
     * <p>
     * Each Object array contains:
     * [0] STRING - fluid name,
     * [1] INTEGER - current fill,
     * [2] INTEGER - capacity.
     * Tanks containing SMOKE, SMOKE_LEADED, or SMOKE_POISON are skipped.
     * Additionally, for machines like Gas Centrifuges, their input and output tanks are included.
     *
     * @param tile the BlockEntity to query.
     * @return a List of Object arrays representing each tank, or null if none are found.
     */
    public static List<Object[]> getAllTanks(BlockEntity tile) {
        List<Object[]> list = new ArrayList<>();

        if (tile instanceof IFluidUser) {
            IFluidUser user = (IFluidUser) tile;

            for (FluidTank tank : user.getAllTanks()) {
                if (tank.getTankType() == Fluids.SMOKE || tank.getTankType() == Fluids.SMOKE_LEADED || tank.getTankType() == Fluids.SMOKE_POISON) {
                    continue;
                }
                list.add(toFluidInfo(tank));
            }
        }

        if (tile instanceof TileEntityMachineGasCent) {
            TileEntityMachineGasCent cent = (TileEntityMachineGasCent) tile;
            list.add(toFluidInfo(cent.inputTank));
            list.add(toFluidInfo(cent.outputTank));
        }

        if (!list.isEmpty()) return list;
        return null;
    }

    /**
     * Converts a FluidTank to its fluid information array.
     *
     * @param tank the FluidTank to convert.
     * @return an Object array with the fluid's name, fill, and capacity.
     */
    private static Object[] toFluidInfo(FluidTank tank) {
        return new Object[] { tank.getTankType().getName(), tank.getFill(), tank.getMaxFill() };
    }

    /**
     * Converts a PseudoFluidTank to its fluid information array.
     *
     * @param tank the PseudoFluidTank to convert.
     * @return an Object array with the fluid's name, fill, and capacity.
     */
    private static Object[] toFluidInfo(PseudoFluidTank tank) {
        return new Object[] { tank.getTankType().getName(), tank.getFill(), tank.getMaxFill() };
    }

    /**
     * Populates extra non-standard data (like progress or unique stats) from an IInfoProviderEC.
     *
     * @param tile the BlockEntity to query.
     * @param data the CompoundTag to fill with extra data.
     */
    public static void getExtraData(BlockEntity tile, CompoundTag data) {
        if (tile instanceof IInfoProviderEC) {
            IInfoProviderEC provider = (IInfoProviderEC) tile;
            provider.provideExtraInfo(data);
        }
    }

    /**
     * Returns the core BlockEntity for the given position.
     * <p>
     * This method delegates to CompatExternal.getCoreFromPos which handles resolving multi-block
     * systems (both MK1 and MK2).
     *
     * @param level the current Level
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     * @return the core BlockEntity.
     */
    public static BlockEntity findTileEntity(Level level, int x, int y, int z) {
        return CompatExternal.getCoreFromPos(level, x, y, z);
    }

    /**
     * Returns the ResourceLocation for the fluid texture associated with the given fluid name.
     *
     * @param name the name of the fluid.
     * @return the ResourceLocation for the fluid texture, or null if not found.
     */
    public static ResourceLocation getFluidTexture(String name) {
        FluidType type = Fluids.fromName(name);
        return type == null ? null : type.getTexture();
    }

    /*
     * [DATA TYPE] _ [NAME] _ [UNIT]
     */

    public static final String KEY_EUTYPE = "euType";

    public static final String L_ENERGY_HE =              "energy";
    public static final String L_ENERGY_TU =              "energyTU";
    public static final String L_ENERGY_ =                "energy_";          // Blast Furnace fuel

    public static final String L_CAPACITY_HE =            "capacity";
    public static final String L_CAPACITY_TU =            "capacityTU";
    public static final String L_CAPACITY_ =              "capacity_";        // Blast Furnace fuel capacity

    public static final String D_CONSUMPTION_HE =         "consumptionHE";
    public static final String D_CONSUMPTION_MB =         "consumption";
    @Deprecated public static final String S_CONSUMPTION_ = "consumption_";      // FWatz fluid consumption rates

    public static final String D_OUTPUT_HE =              "output";
    public static final String D_OUTPUT_MB =              "outputmb";
    public static final String D_OUTPUT_TU =              "outputTU";

    public static final String L_DIFF_HE =                "diff";             // Battery diff per tick
    @Deprecated public static final String I_TEMP_K =     "temp";             // Unused?
    public static final String D_TURBINE_PERCENT =        "turbine";          // CCGT slider
    public static final String I_TURBINE_SPEED =          "speed";            // CCGT RPM
    public static final String L_COREHEAT_C =             "core";             // Research Reactor core heat
    public static final String L_HULLHEAT_C =             "hull";             // Research Reactor hull heat
    public static final String S_LEVEL_PERCENT =          "level";            // Research Reactor rods
    @Deprecated public static final String L_HEATL =     "heatL";            // AMS and old Watz heat values
    public static final String D_HEAT_C =                 "heat";             // Research Reactor and RBMK column heat
    public static final String D_MAXHEAT_C =              "maxHeat";          // ZIRNOX melting temp
    public static final String L_PRESSURE_BAR =           "bar";              // ZIRNOX pressure
    public static final String L_FUEL =                   "fuel";             // RTG Blast Furnace heat
    @Deprecated public static final String S_FUELTEXT =   "fuelText";         // Large Nuclear Reactor only
    @Deprecated public static final String S_DEPLETED =   "depleted";         // Large Nuclear Reactor only
    public static final String D_DEPLETION_PERCENT =      "depletion";        // RBMK Fuel depletion
    public static final String D_XENON_PERCENT =          "xenon";            // RBMK Fuel xenon poisoning
    public static final String D_SKIN_C =                 "skin";             // RBMK Fuel skin heat
    public static final String D_CORE_C =                 "c_heat";           // RBMK Fuel core heat
    public static final String D_MELT_C =                 "melt";             // RBMK Fuel melting point
    public static final String I_PROGRESS =               "progress";
    public static final String I_FLUX =                   "flux";             // Research and Breeding Reactor flux
    public static final String I_WATER =                  "water";            // Research Reactor water gauge
    public static final String L_DURABILITY =             "durability";       // DFC Stabilizer Lens
    public static final String S_TANK =                   "tank";
    public static final String S_TANK2 =                  "tank2";
    public static final String S_TANK3 =                  "tank3";
    public static final String S_TANK4 =                  "tank4";
    public static final String S_TANK5 =                  "tank5";
    @Deprecated public static final String I_PISTONS =    "pistons";          // Radial Performance Engine piston count
    public static final String S_CHUNKRAD =               "chunkRad";         // Geiger Counter
    public static final String B_ACTIVE =                 "active";
}
