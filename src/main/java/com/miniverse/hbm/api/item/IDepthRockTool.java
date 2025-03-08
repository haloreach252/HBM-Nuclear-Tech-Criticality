package com.miniverse.hbm.api.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;

/**
 * Interface for tools capable of breaking depth rock.
 */
public interface IDepthRockTool {

    /**
     * Determines whether this tool can break depth rock at the specified location.
     * This method can be extended to restrict mining for certain blocks, dimensions, or positions.
     *
     * @param level The level (world) in which the block exists.
     * @param player The player using the tool.
     * @param tool The tool being used.
     * @param blockState The block state of the block being broken.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @return True if the tool can break the block, otherwise false.
     */
    boolean canBreakRock(Level level, Player player, ItemStack tool, BlockState blockState, int x, int y, int z);
}
