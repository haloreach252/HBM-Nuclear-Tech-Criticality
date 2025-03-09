package com.miniverse.hbm.lib;

import com.miniverse.hbm.blocks.BlockEnums.EnumStoneType;
import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.blocks.generic.BlockNTMFlower.EnumFlowerType;
import com.miniverse.hbm.config.GeneralConfig;
import com.miniverse.hbm.config.MobConfig;
import com.miniverse.hbm.config.WorldConfig;
import com.miniverse.hbm.handler.MultiblockHandlerXR;
import com.miniverse.hbm.itempool.ItemPool;
import com.miniverse.hbm.itempool.ItemPoolsSingle;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.main.HBMNuclearTechCriticality;
import com.miniverse.hbm.saveddata.TomSaveData;
import com.miniverse.hbm.tileentity.bomb.TileEntityLandmine;
import com.miniverse.hbm.tileentity.deco.TileEntityLanternBehemoth;
import com.miniverse.hbm.tileentity.machine.storage.TileEntitySafe;
import com.miniverse.hbm.tileentity.machine.storage.TileEntitySoyuzCapsule;
import com.miniverse.hbm.util.LootGenerator;
import com.miniverse.hbm.util.WeightedRandomGeneric;
import com.miniverse.hbm.world.dungeon.*;
import com.miniverse.hbm.world.feature.*;
import com.miniverse.hbm.world.feature.BedrockOre.BedrockOreDefinition;
import com.miniverse.hbm.world.generator.CellularDungeonFactory;
import com.miniverse.hbm.world.generator.DungeonToolbox;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.storage.LevelData;
// Note: IWorldGenerator no longer exists in modern Forge. This interface is retained here as a placeholder.
// You should consider migrating your world-gen to use BiomeLoadingEvent or DeferredRegister systems.
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityChest;
import net.minecraft.util.RandomSource; // for random; alternatively, use java.util.Random if preferred

import java.util.Random;

/**
 * Ported world generation class.
 *
 * <p>
 * This class was responsible for generating mod-specific ores, structures, and features in the Nether, Overworld, and End.
 * Note: In Forge 1.20.1, the recommended approach is to register world generation via BiomeLoadingEvent. This class implements
 * a legacy IWorldGenerator interface as a placeholder.
 * </p>
 */
public class HbmWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, Level world, ChunkGenerator<?> chunkGenerator, ChunkAccess chunkProvider) {
        int dimension = world.dimension().getType().getId(); // Assumes dimension IDs: -1 for Nether, 0 for Overworld, 1 for End.
        int x = chunkX * 16;
        int z = chunkZ * 16;
        switch (dimension) {
            case -1:
                generateNether(world, rand, x, z);
                break;
            case 0:
                generateSurface(world, rand, x, z);
                break;
            case 1:
                generateEnd(world, rand, x, z);
                break;
            default:
                if (GeneralConfig.enableMDOres)
                    generateSurface(world, rand, x, z);
                break;
        }
    }

    private void generateSurface(Level world, Random rand, int i, int j) {
        // Retrieve biome at given position (using y=0 as a reference)
        BlockPos pos = new BlockPos(i, world.getMinBuildHeight(), j);
        Biome biome = world.getBiome(pos);

        // Example: TomSaveData and impact check
        if (!TomSaveData.forWorld(world).impact) {
            // Generate mod flowers based on biome
            // (DungeonToolbox.generateFlowers is assumed to be ported to use new APIs)
            if (isInstanceOfForest(biome) && rand.nextInt(16) == 0) {
                DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.FOXGLOVE.ordinal());
            }
            if (isRoofedForest(biome) && rand.nextInt(8) == 0) {
                DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.NIGHTSHADE.ordinal());
            }
            if (isInstanceOfJungle(biome) && rand.nextInt(8) == 0) {
                DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.TOBACCO.ordinal());
            }
            if (rand.nextInt(64) == 0) {
                DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.plant_flower, EnumFlowerType.WEED.ordinal());
            }
            if (isRiverBiome(biome) && rand.nextInt(4) == 0) {
                DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.reeds, 0);
            }
            if (isBeachBiome(biome) && rand.nextInt(8) == 0) {
                DungeonToolbox.generateFlowers(world, rand, i, j, ModBlocks.reeds, 0);
            }
        }

        // Example ore generation calls – these utility methods must be updated to work with new APIs.
        if (WorldConfig.gasbubbleSpawn > 0 && rand.nextInt(WorldConfig.gasbubbleSpawn) == 0)
            DungeonToolbox.generateOre(world, rand, i, j, 1, 32, 30, 10, ModBlocks.gas_flammable, 1);

        if (WorldConfig.explosivebubbleSpawn > 0 && rand.nextInt(WorldConfig.explosivebubbleSpawn) == 0)
            DungeonToolbox.generateOre(world, rand, i, j, 1, 32, 30, 10, ModBlocks.gas_explosive, 1);

        if (WorldConfig.alexandriteSpawn > 0 && rand.nextInt(WorldConfig.alexandriteSpawn) == 0)
            DungeonToolbox.generateOre(world, rand, i, j, 1, 3, 10, 5, ModBlocks.ore_alexandrite);

        if (WorldConfig.overworldOre) {
            // Depth deposit and ore generation calls. (These methods must be updated to use new APIs.)
            DepthDeposit.generateConditionOverworld(world, i, world.getMinBuildHeight(), 3, j, 5, 0.6D, ModBlocks.cluster_depth_iron, rand, 24);
            DepthDeposit.generateConditionOverworld(world, i, world.getMinBuildHeight(), 3, j, 5, 0.6D, ModBlocks.cluster_depth_titanium, rand, 32);
            DepthDeposit.generateConditionOverworld(world, i, world.getMinBuildHeight(), 3, j, 5, 0.6D, ModBlocks.cluster_depth_tungsten, rand, 32);
            DepthDeposit.generateConditionOverworld(world, i, world.getMinBuildHeight(), 3, j, 5, 0.8D, ModBlocks.ore_depth_cinnebar, rand, 16);
            DepthDeposit.generateConditionOverworld(world, i, world.getMinBuildHeight(), 3, j, 5, 0.8D, ModBlocks.ore_depth_zirconium, rand, 16);
            DepthDeposit.generateConditionOverworld(world, i, world.getMinBuildHeight(), 3, j, 5, 0.8D, ModBlocks.ore_depth_borax, rand, 16);

            DungeonToolbox.generateOre(world, rand, i, j, 25, 6, 30, 10, ModBlocks.ore_gneiss_iron, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, 10, 6, 30, 10, ModBlocks.ore_gneiss_gold, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.uraniumSpawn * 3, 6, 30, 10, ModBlocks.ore_gneiss_uranium, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.copperSpawn * 3, 6, 30, 10, ModBlocks.ore_gneiss_copper, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.asbestosSpawn * 3, 6, 30, 10, ModBlocks.ore_gneiss_asbestos, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.lithiumSpawn, 6, 30, 10, ModBlocks.ore_gneiss_lithium, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.rareSpawn, 6, 30, 10, ModBlocks.ore_gneiss_rare, ModBlocks.stone_gneiss);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.gassshaleSpawn * 3, 10, 30, 10, ModBlocks.ore_gneiss_gas, ModBlocks.stone_gneiss);

            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.uraniumSpawn, 5, 5, 20, ModBlocks.ore_uranium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.thoriumSpawn, 5, 5, 25, ModBlocks.ore_thorium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.titaniumSpawn, 6, 5, 30, ModBlocks.ore_titanium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.sulfurSpawn, 8, 5, 30, ModBlocks.ore_sulfur);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.aluminiumSpawn, 6, 5, 40, ModBlocks.ore_aluminium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.copperSpawn, 6, 5, 45, ModBlocks.ore_copper);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.fluoriteSpawn, 4, 5, 45, ModBlocks.ore_fluorite);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.niterSpawn, 6, 5, 30, ModBlocks.ore_niter);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.tungstenSpawn, 8, 5, 30, ModBlocks.ore_tungsten);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.leadSpawn, 9, 5, 30, ModBlocks.ore_lead);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.berylliumSpawn, 4, 5, 30, ModBlocks.ore_beryllium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.rareSpawn, 5, 5, 20, ModBlocks.ore_rare);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.ligniteSpawn, 24, 35, 25, ModBlocks.ore_lignite);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.asbestosSpawn, 4, 16, 16, ModBlocks.ore_asbestos);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.cinnebarSpawn, 4, 8, 16, ModBlocks.ore_cinnebar);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.cobaltSpawn, 4, 4, 8, ModBlocks.ore_cobalt);

            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.ironClusterSpawn, 6, 15, 45, ModBlocks.cluster_iron);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.titaniumClusterSpawn, 6, 15, 30, ModBlocks.cluster_titanium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.aluminiumClusterSpawn, 6, 15, 35, ModBlocks.cluster_aluminium);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.copperClusterSpawn, 6, 15, 20, ModBlocks.cluster_copper);

            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.limestoneSpawn, 16, 25, 30, ModBlocks.stone_resource, EnumStoneType.LIMESTONE.ordinal());

            // Bedrock ore generation – note that WeightedRandomGeneric and BedrockOre must be ported.
            if (WorldConfig.newBedrockOres) {
                if (rand.nextInt(10) == 0) {
                    int randPosX = i + rand.nextInt(2) + 8;
                    int randPosZ = j + rand.nextInt(2) + 8;
                    BedrockOre.generate(world, randPosX, randPosZ, new ItemStack(ModItems.bedrock_ore_base), null, 0xD78A16, 1);
                }
            } else {
                if (rand.nextInt(3) == 0) {
                    @SuppressWarnings("unchecked")
                    WeightedRandomGeneric<BedrockOreDefinition> item = (WeightedRandomGeneric<BedrockOreDefinition>) WeightedRandomGeneric.getRandomItem(rand, BedrockOre.weightedOres);
                    BedrockOreDefinition def = item.get();
                    if (GeneralConfig.enable528 && GeneralConfig.enable528BedrockReplacement) {
                        BedrockOreDefinition replacement = BedrockOre.replacements.get(def.id);
                        if (replacement != null)
                            def = replacement;
                    }
                    int randPosX = i + rand.nextInt(2) + 8;
                    int randPosZ = j + rand.nextInt(2) + 8;
                    BedrockOre.generate(world, randPosX, randPosZ, def.stack, def.acid, def.color, def.tier);
                }
            }

            if (GeneralConfig.enable528ColtanSpawn) {
                DungeonToolbox.generateOre(world, rand, i, j, GeneralConfig.coltanRate, 4, 15, 40, ModBlocks.ore_coltan);
            }

            Random colRand = new Random(world.getSeed() + 5);
            int colX = (int) (colRand.nextGaussian() * 1500);
            int colZ = (int) (colRand.nextGaussian() * 1500);
            int colRange = 750;

            if ((GeneralConfig.enable528BedrockSpawn || GeneralConfig.enable528BedrockDeposit) && rand.nextInt(GeneralConfig.bedrockRate) == 0) {
                int x = i + rand.nextInt(16) + 8;
                int z = j + rand.nextInt(16) + 8;
                if (GeneralConfig.enable528BedrockSpawn || (GeneralConfig.enable528BedrockDeposit && x <= colX + colRange && x >= colX - colRange && z <= colZ + colRange && z >= colZ - colRange)) {
                    BedrockOre.generate(world, x, z, new ItemStack(ModItems.fragment_coltan), null, 0xA78D7A, 1);
                }
            }

            if (GeneralConfig.enable528ColtanDeposit) {
                for (int k = 0; k < 2; k++) {
                    for (int r = 1; r <= 5; r++) {
                        int randPosX = i + rand.nextInt(16);
                        int randPosY = rand.nextInt(25) + 15;
                        int randPosZ = j + rand.nextInt(16);
                        int range = colRange / r;
                        if (randPosX <= colX + range && randPosX >= colX - range && randPosZ <= colZ + range && randPosZ >= colZ - range) {
                            // WorldGenMinable must be ported to use modern ore generation
                            (new WorldGenMinable(ModBlocks.ore_coltan, 4)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
                        }
                    }
                }
            }

            for (int k = 0; k < rand.nextInt(4); k++) {
                int randPosX = i + rand.nextInt(16);
                int randPosY = rand.nextInt(15) + 15;
                int randPosZ = j + rand.nextInt(16);
                // Example check for a specific region; update as needed.
                if (randPosX <= -350 && randPosX >= -450 && randPosZ <= -350 && randPosZ >= -450)
                    (new WorldGenMinable(ModBlocks.ore_australium, 50)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
            }
        }

        // Dungeon and structure generation
        boolean enableDungeons = world.getLevelData().isMapFeaturesEnabled();
        if (GeneralConfig.enableDungeons == 1)
            enableDungeons = true;
        if (GeneralConfig.enableDungeons == 0)
            enableDungeons = false;

        if (enableDungeons && world.dimension().getType().getId() == 0) {
            // Hive generation
            if (MobConfig.enableHives && rand.nextInt(MobConfig.hiveSpawn) == 0) {
                int x = i + rand.nextInt(16) + 8;
                int z = j + rand.nextInt(16) + 8;
                int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z));
                for (int k = 3; k >= -1; k--) {
                    // Check if the block at (x, y-1+k, z) is a full block. Replace with your own helper.
                    if (isNormalCube(world, new BlockPos(x, y - 1 + k, z))) {
                        GlyphidHive.generateSmall(world, x, y + k, z, rand, rand.nextInt(10) == 0, true);
                        break;
                    }
                }
            }

            // Other structure generations follow a similar pattern:
            if (isPlainsOrDesert(biome)) {
                if (WorldConfig.radioStructure > 0 && rand.nextInt(WorldConfig.radioStructure) == 0) {
                    int x = i + rand.nextInt(16);
                    int z = j + rand.nextInt(16);
                    int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z));
                    new Radio01().generate(world, rand, x, y, z);
                }
            }

            if (biome.getTemperature(new BlockPos(i, 0, j)) >= 0.4F && biome.getDownfall() <= 0.6F) {
                if (WorldConfig.antennaStructure > 0 && rand.nextInt(WorldConfig.antennaStructure) == 0) {
                    int x = i + rand.nextInt(16);
                    int z = j + rand.nextInt(16);
                    int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z));
                    new Antenna().generate(world, rand, x, y, z);
                }
            }

            if (!biome.canGenerateLightningBolt() && biome.getTemperature(new BlockPos(i, 0, j)) >= 1.5F) {
                if (WorldConfig.atomStructure > 0 && rand.nextInt(WorldConfig.atomStructure) == 0) {
                    int x = i + rand.nextInt(16);
                    int z = j + rand.nextInt(16);
                    int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z));
                    new DesertAtom001().generate(world, rand, x, y, z);
                }
            }

            if (WorldConfig.dungeonStructure > 0 && rand.nextInt(WorldConfig.dungeonStructure) == 0) {
                int x = i + rand.nextInt(16);
                int y = rand.nextInt(256);
                int z = j + rand.nextInt(16);
                new LibraryDungeon().generate(world, rand, x, y, z);
            }

            // … Additional structure generation (Relay, Satellite, OilSandBubble, Factory, Dud, Spaceship, Barrel, safe, etc.)
            // For each, update block access to use BlockPos and setBlockState calls.
        }

        // Oil and bedrock oil spawns, meteorites, etc.
        if (WorldConfig.oilSpawn > 0 && rand.nextInt(WorldConfig.oilSpawn) == 0) {
            int randPosX = i + rand.nextInt(16);
            int randPosY = rand.nextInt(25);
            int randPosZ = j + rand.nextInt(16);
            OilBubble.spawnOil(world, randPosX, randPosY, randPosZ, 10 + rand.nextInt(7));
        }

        if (WorldConfig.bedrockOilSpawn > 0 && rand.nextInt(WorldConfig.bedrockOilSpawn) == 0) {
            int randPosX = i + rand.nextInt(16);
            int randPosZ = j + rand.nextInt(16);
            for (int xOff = -4; xOff <= 4; xOff++) {
                for (int yOff = 0; yOff <= 4; yOff++) {
                    for (int zOff = -4; zOff <= 4; zOff++) {
                        if (Math.abs(xOff) + Math.abs(yOff) + Math.abs(zOff) <= 6) {
                            BlockPos orePos = new BlockPos(randPosX + xOff, yOff, randPosZ + zOff);
                            BlockState state = world.getBlockState(orePos);
                            // Replace isReplaceableOreGen with your own check based on the material, etc.
                            if (isReplaceableOreGen(state, Blocks.STONE) || isReplaceableOreGen(state, Blocks.BEDROCK)) {
                                world.setBlock(orePos, ModBlocks.ore_bedrock_oil.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
            DungeonToolbox.generateOre(world, rand, i, j, 16, 8, 10, 50, ModBlocks.stone_porous);
            OilSpot.generateOilSpot(world, randPosX, randPosZ, 5, 50, true);
        }

        if (WorldConfig.meteoriteSpawn > 0 && rand.nextInt(WorldConfig.meteoriteSpawn) == 0) {
            int x = i + rand.nextInt(16);
            int z = j + rand.nextInt(16);
            int y = world.getHeight(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z)) - rand.nextInt(10);
            if (y > 1)
                (new Meteorite()).generate(world, rand, x, y, z, false, false, false);
        }

        // Example for NITAN-specific spawns. (Block access updated, but logic remains similar.)
        if (GeneralConfig.enableNITAN) {
            // For each hardcoded coordinate check, use BlockPos and getBlockState.
            // (This block is repeated for several positions; update similarly.)
            BlockPos chestPos = new BlockPos(10000, 250, 10000);
            if (world.getBlockState(chestPos).getBlock() == Blocks.AIR) {
                world.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 3);
                if (world.getBlockState(chestPos).getBlock() == Blocks.CHEST) {
                    WeightedRandomChestContent.generateChestContents(rand, ItemPool.getPool(ItemPoolsSingle.POOL_POWDER),
                            (BlockEntityChest) world.getBlockEntity(chestPos), 29);
                }
            }
            // ... (repeat for other positions)
        }

        if (rand.nextInt(4) == 0) {
            int x = i + rand.nextInt(16) + 8;
            int y = 6 + rand.nextInt(13);
            int z = j + rand.nextInt(16) + 8;
            BlockPos posOre = new BlockPos(x, y, z);
            // Replace isReplaceableOreGen with your helper check.
            if (isReplaceableOreGen(world.getBlockState(posOre), Blocks.STONE)) {
                world.setBlock(posOre, ModBlocks.stone_keyhole.defaultBlockState(), 3);
            }
        }
    }

    private void generateNether(Level world, Random rand, int i, int j) {
        if (WorldConfig.netherOre) {
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherUraniumuSpawn, 6, 0, 127, ModBlocks.ore_nether_uranium, Blocks.NETHERRACK);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherTungstenSpawn, 10, 0, 127, ModBlocks.ore_nether_tungsten, Blocks.NETHERRACK);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherSulfurSpawn, 12, 0, 127, ModBlocks.ore_nether_sulfur, Blocks.NETHERRACK);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherPhosphorusSpawn, 6, 0, 127, ModBlocks.ore_nether_fire, Blocks.NETHERRACK);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherCoalSpawn, 32, 16, 96, ModBlocks.ore_nether_coal, Blocks.NETHERRACK);
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherCobaltSpawn, 6, 100, 26, ModBlocks.ore_nether_cobalt, Blocks.NETHERRACK);

            if (GeneralConfig.enablePlutoniumOre)
                DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.netherPlutoniumSpawn, 4, 0, 127, ModBlocks.ore_nether_plutonium, Blocks.NETHERRACK);

            if (rand.nextInt(10) == 0) {
                @SuppressWarnings("unchecked")
                WeightedRandomGeneric<BedrockOreDefinition> item = (WeightedRandomGeneric<BedrockOreDefinition>) WeightedRandomGeneric.getRandomItem(rand, BedrockOre.weightedOresNether);
                BedrockOreDefinition def = item.get();
                int randPosX = i + rand.nextInt(2) + 8;
                int randPosZ = j + rand.nextInt(2) + 8;
                BedrockOre.generate(world, randPosX, randPosZ, def.stack, def.acid, def.color, def.tier, ModBlocks.stone_depth_nether);
            }

            DepthDeposit.generateConditionNether(world, i, world.getMinBuildHeight(), 3, j, 7, 0.6D, ModBlocks.ore_depth_nether_neodymium, rand, 16);
            DepthDeposit.generateConditionNether(world, i, 125, 3, j, 7, 0.6D, ModBlocks.ore_depth_nether_neodymium, rand, 16);
        }

        for (int k = 0; k < 30; k++) {
            int x = i + rand.nextInt(16);
            int z = j + rand.nextInt(16);
            int d = 16 + rand.nextInt(96);
            for (int y = d - 5; y <= d; y++) {
                BlockPos pos = new BlockPos(x, y, z);
                if (world.getBlockState(pos.above()).getBlock() == Blocks.AIR &&
                        world.getBlockState(pos).getBlock() == Blocks.NETHERRACK) {
                    world.setBlock(pos, ModBlocks.ore_nether_smoldering.defaultBlockState(), 3);
                }
            }
        }

        for (int k = 0; k < 1; k++) {
            int x = i + rand.nextInt(16);
            int z = j + rand.nextInt(16);
            int d = 16 + rand.nextInt(96);
            for (int y = d - 5; y <= d; y++) {
                BlockPos pos = new BlockPos(x, y, z);
                if (world.getBlockState(pos.above()).getBlock() == Blocks.AIR &&
                        world.getBlockState(pos).getBlock() == Blocks.NETHERRACK) {
                    world.setBlock(pos, ModBlocks.geysir_nether.defaultBlockState(), 3);
                }
            }
        }
    }

    private void generateEnd(Level world, Random rand, int i, int j) {
        if (WorldConfig.endOre) {
            DungeonToolbox.generateOre(world, rand, i, j, WorldConfig.endTikiteSpawn, 6, 0, 127, ModBlocks.ore_tikite, Blocks.END_STONE);
            // Additional End-specific generation can go here.
        }
    }

    // Helper methods to mimic old biome type checks. You must implement these based on your mod's biomes.
    private boolean isInstanceOfForest(Biome biome) {
        // Replace with your own forest biome check.
        return biome.getBaseTemperature() < 0.7F;
    }

    private boolean isRoofedForest(Biome biome) {
        // Replace with your own roofed forest check.
        return "roofed_forest".equals(biome.getRegistryName().getPath());
    }

    private boolean isInstanceOfJungle(Biome biome) {
        // Replace with your own jungle biome check.
        return "jungle".equals(biome.getRegistryName().getPath());
    }

    private boolean isRiverBiome(Biome biome) {
        return "river".equals(biome.getRegistryName().getPath());
    }

    private boolean isBeachBiome(Biome biome) {
        return "beach".equals(biome.getRegistryName().getPath());
    }

    private boolean isPlainsOrDesert(Biome biome) {
        String path = biome.getRegistryName().getPath();
        return "plains".equals(path) || "desert".equals(path);
    }

    // Stub for normal cube check. Replace with a proper implementation.
    private boolean isNormalCube(Level world, BlockPos pos) {
        return world.getBlockState(pos).isSolidRender(world, pos);
    }

    // Stub for ore replaceability check.
    private boolean isReplaceableOreGen(BlockState state, Block target) {
        // Replace with your own logic; for example, checking material or tags.
        return state.getBlock() == target;
    }
}
