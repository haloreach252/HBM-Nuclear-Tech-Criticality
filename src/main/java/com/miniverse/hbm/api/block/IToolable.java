package com.miniverse.hbm.api.block;

import com.miniverse.hbm.inventory.RecipesCommon.ComparableStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IToolable {

    /**
     * Called when a tool is used on this block.
     *
     * @param level The world (level) where the block is located.
     * @param player The player using the tool.
     * @param pos The position of the block.
     * @param side The side of the block being interacted with.
     * @param fX The hit position on the X-axis.
     * @param fY The hit position on the Y-axis.
     * @param fZ The hit position on the Z-axis.
     * @param tool The type of tool used.
     * @return True if the interaction was successful, false otherwise.
     */
    boolean onScrew(Level level, Player player, BlockPos pos, int side, float fX, float fY, float fZ, ToolType tool);

    /**
     * Represents different tool types that can interact with this block.
     */
    enum ToolType {
        SCREWDRIVER,
        HAND_DRILL,
        DEFUSER,
        WRENCH,
        TORCH,
        BOLT;

        public final List<ItemStack> stacksForDisplay = new ArrayList<>();
        private static final HashMap<ComparableStack, ToolType> map = new HashMap<>();

        /**
         * Registers an ItemStack as a valid tool for this type.
         *
         * @param stack The tool item to register.
         */
        public void register(ItemStack stack) {
            stacksForDisplay.add(stack);
        }

        /**
         * Gets the tool type from an ItemStack.
         *
         * @param stack The item being checked.
         * @return The corresponding ToolType, or null if none match.
         */
        public static ToolType getType(ItemStack stack) {
            if (!map.isEmpty()) {
                return map.get(new ComparableStack(stack));
            }

            for (ToolType type : ToolType.values()) {
                for (ItemStack tool : type.stacksForDisplay) {
                    map.put(new ComparableStack(tool), type);
                }
            }

            return map.get(new ComparableStack(stack));
        }
    }
}
