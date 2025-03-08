package com.miniverse.hbm.api.block;

import com.miniverse.hbm.api.block.IMiningDrill;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IDrillInteraction {

    /**
     * Whether the breaking of the block should be successful. Will destroy the block and not drop anything from clusters.
     * Should use a random function; otherwise, the clusters will stay indefinitely, printing free ore.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param drill Might be a tool, tile entity, or anything that breaks blocks.
     * @return True if the block should be broken.
     */
    boolean canBreak(Level level, BlockPos pos, IMiningDrill drill);

    /**
     * Returns an ItemStack, usually when the block is not destroyed. Laser drills may drop this, and
     * mechanical drills will add it to their inventories.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param drill Might be a tool, tile entity, or anything that breaks blocks.
     * @return An ItemStack representing the extracted resource.
     */
    ItemStack extractResource(Level level, BlockPos pos, IMiningDrill drill);

    /**
     * The hardness that should be considered instead of the hardness value of the block itself.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param drill The mining drill performing the operation.
     * @return The modified hardness value.
     */
    float getRelativeHardness(Level level, BlockPos pos, IMiningDrill drill);
}
