package com.miniverse.hbm.explosion;

import java.util.Random;

import com.miniverse.hbm.blocks.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class NukeEnvironmentalEffect {

    // Using RandomSource instead of java.util.Random in newer Minecraft versions
    static RandomSource rand = RandomSource.create();

    /**
     * Area of effect radiation effect. j > 0 for jagged edges of the spherical area. Args: world, x, y, z, radius, outer radius with random chance.
     */
    @Deprecated //does not use scorched uranium, implementation is garbage anyway
    public static void applyStandardAOE(Level world, int x, int y, int z, int r, int j) {
        int r2 = r * r;
        int r22 = r2 / 2;
        for (int xx = -r; xx < r; xx++) {
            int X = xx + x;
            int XX = xx * xx;
            for (int yy = -r; yy < r; yy++) {
                int Y = yy + y;
                int YY = XX + yy * yy;
                for (int zz = -r; zz < r; zz++) {
                    int Z = zz + z;
                    int ZZ = YY + zz * zz;
                    if (ZZ < r22 + rand.nextInt(j)) {
                        applyStandardEffect(world, X, Y, Z);
                    }
                }
            }
        }
    }

    public static void applyStandardEffect(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        applyStandardEffect(world, pos);
    }

    public static void applyStandardEffect(Level world, BlockPos pos) {
        int chance = 100;
        Block b = null;

        BlockState inState = world.getBlockState(pos);
        Block in = inState.getBlock();

        if (inState.isAir()) {
            return;
        }

        //Task done by fallout effect entity.
        /*if(in == Blocks.grass) {
            b = ModBlocks.WASTE_EARTH.get();
        } else */

        if (in == Blocks.SAND) {
            // In 1.20.1, we use block properties instead of metadata
            if (inState.is(Blocks.RED_SAND)) {
                b = ModBlocks.WASTE_TRINITITE_RED.get();
            } else {
                b = ModBlocks.WASTE_TRINITITE.get();
            }

            chance = 20;

        } else if (in == Blocks.MYCELIUM) {
            b = ModBlocks.WASTE_MYCELIUM.get();

        } else if (in == Blocks.OAK_LOG || in == Blocks.SPRUCE_LOG ||
                in == Blocks.BIRCH_LOG || in == Blocks.JUNGLE_LOG ||
                in == Blocks.ACACIA_LOG || in == Blocks.DARK_OAK_LOG ||
                in == Blocks.MANGROVE_LOG || in == Blocks.CHERRY_LOG) {
            b = ModBlocks.WASTE_LOG.get();

        } else if (in == Blocks.OAK_PLANKS || in == Blocks.SPRUCE_PLANKS ||
                in == Blocks.BIRCH_PLANKS || in == Blocks.JUNGLE_PLANKS ||
                in == Blocks.ACACIA_PLANKS || in == Blocks.DARK_OAK_PLANKS ||
                in == Blocks.MANGROVE_PLANKS || in == Blocks.CHERRY_PLANKS ||
                in == Blocks.BAMBOO_PLANKS) {
            b = ModBlocks.WASTE_PLANKS.get();

        } else if (in == Blocks.MOSSY_COBBLESTONE) {
            b = ModBlocks.ORE_OIL.get();
            chance = 50;

        } else if (in == Blocks.COAL_ORE || in == Blocks.DEEPSLATE_COAL_ORE) {
            b = Blocks.DIAMOND_ORE;
            chance = 10;

        } else if (in == ModBlocks.ORE_URANIUM.get()) {
            b = ModBlocks.ORE_SCHRABIDIUM.get();
            chance = 10;

        } else if (in == ModBlocks.ORE_NETHER_URANIUM.get()) {
            b = ModBlocks.ORE_NETHER_SCHRABIDIUM.get();
            chance = 10;

        } else if (in == ModBlocks.ORE_NETHER_PLUTONIUM.get()) {
            b = ModBlocks.ORE_NETHER_SCHRABIDIUM.get();
            chance = 25;

        } else if (in == Blocks.BROWN_MUSHROOM_BLOCK) {
            // Need to check specific block state property that replaced meta=10
            // Using a simple check for stem property if available in your implementation
            b = ModBlocks.WASTE_PLANKS.get();

        } else if (in == Blocks.RED_MUSHROOM_BLOCK) {
            // Need to check specific block state property that replaced meta=10
            // Using a simple check for stem property if available in your implementation
            b = ModBlocks.WASTE_PLANKS.get();

        } else if (in == Blocks.END_STONE) {
            b = ModBlocks.ORE_TIKITE.get();
            chance = 1;

        } else if (in == Blocks.CLAY) {
            b = Blocks.TERRACOTTA;

        } else if (in.isFlammable(inState, world, pos, net.minecraft.core.Direction.UP)) {
            b = Blocks.FIRE;
            chance = 100;
        }

        if (b != null && rand.nextInt(1000) < chance) {
            // Using default block state and update flag 2
            world.setBlock(pos, b.defaultBlockState(), 2);
        }
    }
}