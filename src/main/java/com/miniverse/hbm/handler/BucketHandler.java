package com.miniverse.hbm.handler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BucketHandler {

    public static final BucketHandler INSTANCE = new BucketHandler();
    public Map<Block, Item> buckets = new HashMap<>();

    private BucketHandler() {
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        ItemStack result = fillCustomBucket(event.getLevel(), event.getHitVec());
        if (result == null)
            return;

        event.setFilledBucket(result);
        event.setResult(Event.Result.ALLOW);
    }

    private ItemStack fillCustomBucket(Level world, BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        Item bucketItem = buckets.get(block);
        if (bucketItem != null) {
            // Check if the block state has a LEVEL property and that it is 0 (i.e. source block)
            if (state.hasProperty(BlockStateProperties.LEVEL) && state.getValue(BlockStateProperties.LEVEL) != 0) {
                return null;
            }
            // Remove the block (set to air)
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            return new ItemStack(bucketItem);
        } else {
            return null;
        }
    }
}
