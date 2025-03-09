package com.miniverse.hbm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;

// Updated mod API imports with new package names
import com.miniverse.hbm.api.energymk2.IEnergyHandlerMK2;
import com.miniverse.hbm.api.energymk2.IEnergyReceiverMK2;
import com.miniverse.hbm.api.fluid.IFluidUser;
import com.miniverse.hbm.api.recipe.IRecipeRegisterListener;
import com.miniverse.hbm.blocks.BlockDummyable;
import com.miniverse.hbm.entity.missile.EntityMissileCustom;
import com.miniverse.hbm.explosion.ExplosionNukeSmall;
import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;
import com.miniverse.hbm.inventory.recipes.loader.SerializableRecipe;
import com.miniverse.hbm.items.weapon.ItemCustomMissilePart.WarheadType;
import com.miniverse.hbm.tileentity.machine.TileEntityDummy;
import com.miniverse.hbm.tileentity.turret.TileEntityTurretSentry;

// Updated Minecraft classes (using Parchment 1.20.1 mappings)
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.ChatFormatting;

/**
 * Compatibility and integration helper for external systems.
 *
 * This class provides various static utility methods to:
 *
 * - Retrieve the "core" BlockEntity from a given position, taking into account dummy blocks
 *   from multi-block structures.
 * - Query energy and fluid information from a BlockEntity that implements custom mod APIs.
 * - Register turret targeting rules and recipe register listeners.
 *
 * It also contains examples (compatExamples) to demonstrate how to add targeting rules and configure
 * warhead properties.
 */
public class CompatExternal {

    /**
     * Gets the core BlockEntity at the specified position.
     * <p>
     * If the block at that position is a dummy (implementing BlockDummyable), then the method will
     * attempt to find its "core" coordinates and return the BlockEntity at that location.
     * Otherwise, if the BlockEntity is an old dummy (TileEntityDummy), it returns the core BlockEntity
     * based on the dummy's target coordinates.
     *
     * @param level the current Level (world)
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param z the z-coordinate
     * @return the core BlockEntity or null if not present.
     */
    public static BlockEntity getCoreFromPos(Level level, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        Block b = level.getBlockState(pos).getBlock();

        // If the block implements BlockDummyable, use its system to find the core coordinates.
        if (b instanceof BlockDummyable) {
            BlockDummyable dummy = (BlockDummyable) b;
            int[] corePos = dummy.findCore(level, x, y, z);
            if (corePos != null) {
                return level.getBlockEntity(new BlockPos(corePos[0], corePos[1], corePos[2]));
            }
        }

        BlockEntity tile = level.getBlockEntity(pos);

        // If the BlockEntity is an old dummy, return the BlockEntity at its target coordinates.
        if (tile instanceof TileEntityDummy) {
            TileEntityDummy dummy = (TileEntityDummy) tile;
            return level.getBlockEntity(new BlockPos(dummy.targetX, dummy.targetY, dummy.targetZ));
        }

        return tile;
    }

    /**
     * Returns the buffered energy currently held by the BlockEntity.
     * Relies on IEnergyHandlerMK2.
     *
     * @param tile the BlockEntity to query
     * @return the current stored energy, or 0 if not applicable
     */
    public static long getBufferedPowerFromTile(BlockEntity tile) {
        if (tile instanceof IEnergyHandlerMK2) {
            return ((IEnergyHandlerMK2) tile).getPower();
        }
        return 0L;
    }

    /**
     * Returns the maximum energy capacity of the BlockEntity.
     *
     * @param tile the BlockEntity to query
     * @return the energy capacity, or 0 if not applicable
     */
    public static long getMaxPowerFromTile(BlockEntity tile) {
        if (tile instanceof IEnergyHandlerMK2) {
            return ((IEnergyHandlerMK2) tile).getMaxPower();
        }
        return 0L;
    }

    /**
     * Returns the energy priority (ordinal) of the BlockEntity.
     *
     * @param tile the BlockEntity to query
     * @return 0 for low, 1 for normal, 2 for high, or -1 if not applicable.
     */
    public static int getEnergyPriorityFromTile(BlockEntity tile) {
        if (tile instanceof IEnergyReceiverMK2) {
            return ((IEnergyReceiverMK2) tile).getPriority().ordinal();
        }
        return -1;
    }

    /**
     * Returns a list of fluid tank information from the BlockEntity.
     * <p>
     * Each entry in the returned list is an Object array representing:
     * [0] STRING - unlocalized name of the fluid (use I18n for translation),
     * [1] INT - the unique fluid ID,
     * [2] INT - the hexadecimal color,
     * [3] INT - the current fluid amount (in millibuckets),
     * [4] INT - the tank capacity (in millibuckets).
     *
     * @param tile the BlockEntity to query
     * @return a list of fluid information arrays, or an empty list if the BlockEntity is not a fluid container.
     */
    public static ArrayList<Object[]> getFluidInfoFromTile(BlockEntity tile) {
        ArrayList<Object[]> list = new ArrayList<>();
        if (!(tile instanceof IFluidUser)) {
            return list;
        }
        IFluidUser container = (IFluidUser) tile;
        for (FluidTank tank : container.getAllTanks()) {
            FluidType type = tank.getTankType();
            list.add(new Object[] {
                    type.getConditionalName(),
                    type.getID(),
                    type.getColor(),
                    tank.getFill(),
                    tank.getMaxFill()
            });
        }
        return list;
    }

    // Sets of classes used for turret targeting rules.
    public static Set<Class<?>> turretTargetPlayer = new HashSet<>();
    public static Set<Class<?>> turretTargetFriendly = new HashSet<>();
    public static Set<Class<?>> turretTargetHostile = new HashSet<>();
    public static Set<Class<?>> turretTargetMachine = new HashSet<>();

    /**
     * Registers a class for turret targeting.
     *
     * @param clazz the class to be targeted
     * @param type determines which turret setting applies:
     *             0 = player, 1 = friendly, 2 = hostile, 3 = machine.
     */
    public static void registerTurretTargetSimple(Class<?> clazz, int type) {
        switch(type) {
            case 0: turretTargetPlayer.add(clazz); break;
            case 1: turretTargetFriendly.add(clazz); break;
            case 2: turretTargetHostile.add(clazz); break;
            case 3: turretTargetMachine.add(clazz); break;
        }
    }

    // A set of classes that turrets will fully ignore.
    public static Set<Class<?>> turretTargetBlacklist = new HashSet<>();

    /**
     * Registers a class to be ignored by turrets.
     *
     * @param clazz the class that should be ignored.
     */
    public static void registerTurretTargetBlacklist(Class<?> clazz) {
        turretTargetBlacklist.add(clazz);
    }

    // Map for more complex turret targeting logic.
    public static HashMap<Class<?>, BiFunction<Entity, Object, Integer>> turretTargetCondition = new HashMap<>();

    /**
     * Registers a BiFunction lambda for complex turret targeting behavior.
     * <p>
     * The lambda takes the entity and the turret object and returns:
     * 0 to continue with normal checks, -1 to ignore the entity, or 1 to force targeting.
     *
     * @param clazz the class the rule applies to
     * @param bi the targeting lambda
     */
    public static void registerTurretTargetingCondition(Class<?> clazz, BiFunction<Entity, Object, Integer> bi) {
        turretTargetCondition.put(clazz, bi);
    }

    /**
     * Sets a custom label for a warhead type.
     *
     * @param type the warhead type
     * @param label the custom label
     */
    public static void setWarheadLabel(WarheadType type, String label) {
        type.labelCustom = label;
    }

    /**
     * Sets a custom impact behavior for a warhead type.
     *
     * @param type the warhead type
     * @param impact the custom impact effect as a Consumer lambda
     */
    public static void setWarheadImpact(WarheadType type, Consumer<EntityMissileCustom> impact) {
        type.impactCustom = impact;
    }

    /**
     * Sets a custom update behavior for a warhead type.
     *
     * @param type the warhead type
     * @param update the custom update effect as a Consumer lambda
     */
    public static void setWarheadUpdate(WarheadType type, Consumer<EntityMissileCustom> update) {
        type.updateCustom = update;
    }

    /**
     * Registers an IRecipeRegisterListener which is notified when recipes are loaded.
     *
     * @param listener the recipe listener to register.
     */
    public static void registerRecipeRegisterListener(IRecipeRegisterListener listener) {
        SerializableRecipe.additionalListeners.add(listener);
    }

    /**
     * Examples demonstrating how to use the compatibility methods.
     * <p>
     * - Registers cows (Cow.class) to be targeted by turrets in player mode.
     * - Registers chickens (Chicken.class) to be ignored by turrets.
     * - Adds a condition so that turrets will always target players named "Target Practice" while sentry turrets ignore players.
     * - Configures a custom warhead type (CUSTOM0) with a custom label and impact effect.
     */
    public static void compatExamples() {
        // Register cows to be targeted in player mode.
        registerTurretTargetSimple(Cow.class, 0);
        // Register chickens to be ignored.
        registerTurretTargetBlacklist(Chicken.class);
        // Register complex turret targeting condition for players.
        registerTurretTargetingCondition(Player.class, (entity, turret) -> {
            // For Player, use getName().getString() to compare names.
            if (entity.getName().getString().equals("Target Practice")) return 1;
            if (turret instanceof TileEntityTurretSentry) return -1;
            return 0;
        });
        // Configure CUSTOM0 warhead with a custom label and explosion effect.
        setWarheadLabel(WarheadType.CUSTOM0, ChatFormatting.YELLOW + "Micro Nuke");
        setWarheadImpact(WarheadType.CUSTOM0, (missile) -> {
            // Use getLevel() and getter methods to retrieve missile position.
            ExplosionNukeSmall.explode(missile.getLevel(), missile.getX(), missile.getY() + 0.5, missile.getZ(), ExplosionNukeSmall.PARAMS_MEDIUM);
        });
    }
}
