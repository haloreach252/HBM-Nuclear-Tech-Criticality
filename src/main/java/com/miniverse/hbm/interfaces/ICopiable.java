package com.miniverse.hbm.interfaces;

import com.miniverse.hbm.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface ICopiable {

    CompoundTag getSettings(BlockPos pos, Level level);

    void applySettings(CompoundTag data, int index, Player player, Level level, BlockPos pos);

    default String getSettingsSourceID(Either<BlockEntity, Block> self) {
        Block block = self.isLeft() ? self.left().getBlockState().getBlock() : self.right();
        return block.getDescriptionId();
    }

    default String getSettingsSourceDisplay(Either<BlockEntity, Block> self) {
        Block block = self.isLeft() ? self.left().getBlockState().getBlock() : self.right();
        return block.getName().getString();
    }
}
