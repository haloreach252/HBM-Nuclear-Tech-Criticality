package com.miniverse.hbm.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IInsertable {

    /**
     * Attempts to insert an item into the block at the given position.
     *
     * @param level The world (level) where the block is located.
     * @param pos The position of the block.
     * @param dir The direction from which the item is being inserted.
     * @param stack The item stack being inserted.
     * @return True if the item was successfully inserted, false otherwise.
     */
    boolean insertItem(Level level, BlockPos pos, Direction dir, ItemStack stack);
}
