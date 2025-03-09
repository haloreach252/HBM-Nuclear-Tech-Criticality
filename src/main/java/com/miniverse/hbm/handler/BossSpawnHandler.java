package com.miniverse.hbm.handler;

import java.util.Random;

import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.config.GeneralConfig;
import com.miniverse.hbm.config.MobConfig;
import com.miniverse.hbm.config.WorldConfig;
import com.miniverse.hbm.entity.mob.EntityFBI;
import com.miniverse.hbm.entity.mob.EntityFBIDrone;
import com.miniverse.hbm.entity.mob.EntityGhost;
import com.miniverse.hbm.entity.mob.EntityMaskMan;
import com.miniverse.hbm.entity.mob.EntityRADBeast;
import com.miniverse.hbm.entity.projectile.EntityMeteor;
import com.miniverse.hbm.extprop.HbmLivingProps;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.main.HBMNuclearTechCriticality;
import com.miniverse.hbm.handler.ArmorModHandler;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class BossSpawnHandler {

    // because some dimwit keeps resetting the world random
    private static final Random meteorRand = new Random();

    public static void rollTheDice(Level world) {
        // --- MASK MAN SPAWN ---
        if (MobConfig.enableMaskman) {
            if (world.getGameTime() % MobConfig.maskmanDelay == 0) {
                if (world.getRandom().nextInt(MobConfig.maskmanChance) == 0
                        && !world.players().isEmpty()
                        && world.dimension().equals(Level.OVERWORLD)) { // only in overworld

                    Player player = world.players().get(world.getRandom().nextInt(world.players().size()));
                    // Get the stat for the machine crystallizer (crafting or usage)
                    // In 1.20.1 stat tracking has changed; this is a placeholder using the vanilla stats.
                    int id = net.minecraft.world.item.Item.getId(ModBlocks.machine_crystallizer.asItem());
                    Stat<?> statCraft = Stats.CUSTOM.get(Stats.ITEM_CRAFTED.get(id));
                    Stat<?> statPlace = Stats.CUSTOM.get(Stats.ITEM_USED.get(id));

                    if (!(player instanceof ServerPlayer))
                        return;
                    ServerPlayer serverPlayer = (ServerPlayer) player;

                    // Check if the player has crafted or used the item
                    boolean acidizerStat = !GeneralConfig.enableStatReRegistering
                            || ((statCraft != null && serverPlayer.getStats().getValue(statCraft) > 0)
                            || (statPlace != null && serverPlayer.getStats().getValue(statPlace) > 0));

                    BlockPos pos = player.blockPosition();
                    int surfaceY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ());
                    if (acidizerStat
                            && ContaminationUtil.getRads(player) >= MobConfig.maskmanMinRad
                            && (surfaceY > player.getY() + 3 || !MobConfig.maskmanUnderground)) {

                        player.sendSystemMessage(Component.literal("The mask man is about to claim another victim.")
                                .withStyle(ChatFormatting.RED));

                        double spawnX = player.getX() + world.getRandom().nextGaussian() * 20;
                        double spawnZ = player.getZ() + world.getRandom().nextGaussian() * 20;
                        int spawnY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(spawnX), Mth.floor(spawnZ));

                        trySpawn(world, (float) spawnX, spawnY, (float) spawnZ, new EntityMaskMan(world));
                    }
                }
            }
        }

        // --- FBI RAID SPAWN ---
        if (MobConfig.enableRaids) {
            if (world.getGameTime() % MobConfig.raidDelay == 0) {
                if (world.getRandom().nextInt(MobConfig.raidChance) == 0
                        && !world.players().isEmpty()
                        && world.dimension().equals(Level.OVERWORLD)) {

                    Player player = world.players().get(world.getRandom().nextInt(world.players().size()));

                    // Retrieve the persistent data from the player under "PlayerPersisted"
                    // (This key is used by vanilla to store persistent data.)
                    // Adjust key names as needed.
                    if (player.getPersistentData().getCompound("PlayerPersisted").getLong("fbiMark") < world.getGameTime()) {
                        player.sendSystemMessage(Component.literal("FBI, OPEN UP!")
                                .withStyle(ChatFormatting.RED));

                        // Create a vector pointing outwards from the player
                        Vec3 vec = new Vec3(MobConfig.raidAttackDistance, 0, 0);
                        // Rotate by a random angle in degrees (converted from radians)
                        float angleDeg = (float) (360 * world.getRandom().nextFloat());
                        vec = vec.yRot(angleDeg);

                        for (int i = 0; i < MobConfig.raidAmount; i++) {
                            double spawnX = player.getX() + vec.x + world.getRandom().nextGaussian() * 5;
                            double spawnZ = player.getZ() + vec.z + world.getRandom().nextGaussian() * 5;
                            int spawnY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(spawnX), Mth.floor(spawnZ));
                            trySpawn(world, (float) spawnX, spawnY, (float) spawnZ, new EntityFBI(world));
                        }

                        for (int i = 0; i < MobConfig.raidDrones; i++) {
                            double spawnX = player.getX() + vec.x + world.getRandom().nextGaussian() * 5;
                            double spawnZ = player.getZ() + vec.z + world.getRandom().nextGaussian() * 5;
                            int spawnY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(spawnX), Mth.floor(spawnZ));
                            trySpawn(world, (float) spawnX, spawnY + 10, (float) spawnZ, new EntityFBIDrone(world));
                        }
                    }
                }
            }
        }

        // --- ELEMENTAL SPAWN ---
        if (MobConfig.enableElementals) {
            if (world.getGameTime() % MobConfig.elementalDelay == 0) {
                if (world.getRandom().nextInt(MobConfig.elementalChance) == 0
                        && !world.players().isEmpty()
                        && world.dimension().equals(Level.OVERWORLD)) {

                    Player player = world.players().get(world.getRandom().nextInt(world.players().size()));

                    if (player.getPersistentData().getCompound("PlayerPersisted").getBoolean("radMark")) {
                        player.sendSystemMessage(Component.literal("You hear a faint clicking...")
                                .withStyle(ChatFormatting.YELLOW));

                        // Mark is used once.
                        player.getPersistentData().getCompound("PlayerPersisted").putBoolean("radMark", false);

                        Vec3 vec = new Vec3(MobConfig.raidAttackDistance, 0, 0);
                        for (int i = 0; i < MobConfig.elementalAmount; i++) {
                            float angleDeg = (float) (360 * world.getRandom().nextFloat());
                            vec = vec.yRot(angleDeg);

                            double spawnX = player.getX() + vec.x + world.getRandom().nextGaussian();
                            double spawnZ = player.getZ() + vec.z + world.getRandom().nextGaussian();
                            int spawnY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(spawnX), Mth.floor(spawnZ));

                            EntityRADBeast rad = new EntityRADBeast(world);
                            if (i == 0)
                                rad.makeLeader();

                            trySpawn(world, (float) spawnX, spawnY, (float) spawnZ, rad);
                        }
                    }
                }
            }
        }

        // --- METEOR STRIKES / SHOWERS ---
        if (WorldConfig.enableMeteorStrikes && !world.isClientSide()) {
            meteorUpdate(world);
        }

        // --- GHOST SPAWN ---
        if (world.getGameTime() % 20 == 0) {
            if (world.getRandom().nextInt(5) == 0 && !world.players().isEmpty() && world.dimension().equals(Level.OVERWORLD)) {
                Player player = world.players().get(world.getRandom().nextInt(world.players().size()));

                // For armor, loop over the armor slots in proper order:
                // In modern Minecraft, use EquipmentSlot.FEET, LEGS, CHEST, HEAD.
                boolean spawnGhost = false;
                for (net.minecraft.world.entity.EquipmentSlot slot : new net.minecraft.world.entity.EquipmentSlot[] {
                        net.minecraft.world.entity.EquipmentSlot.FEET,
                        net.minecraft.world.entity.EquipmentSlot.LEGS,
                        net.minecraft.world.entity.EquipmentSlot.CHEST,
                        net.minecraft.world.entity.EquipmentSlot.HEAD }) {

                    // getItemBySlot returns an ItemStack (empty if none)
                    if (!player.getItemBySlot(slot).isEmpty() && ArmorModHandler.hasMods(player.getItemBySlot(slot))) {
                        // Loop over mod slots 0 through 7
                        for (int j = 0; j < 8; j++) {
                            if (!ArmorModHandler.pryMods(player.getItemBySlot(slot))[j].isEmpty()) {
                                if (ArmorModHandler.pryMods(player.getItemBySlot(slot))[j].getItem() == ModItems.protection_charm) {
                                    // If a protection charm is found, do not spawn a ghost.
                                    spawnGhost = false;
                                    break;
                                }
                                if (ArmorModHandler.pryMods(player.getItemBySlot(slot))[j].getItem() == ModItems.meteor_charm) {
                                    spawnGhost = false;
                                } else {
                                    spawnGhost = true;
                                }
                            }
                        }
                    }
                }
                if (spawnGhost) {
                    BlockPos pos = new BlockPos(
                            Mth.floor(player.getX() + world.getRandom().nextGaussian()),
                            0,
                            Mth.floor(player.getZ() + world.getRandom().nextGaussian()));
                    int spawnY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, pos.getX(), pos.getZ());
                    trySpawn(world, (float) (player.getX() + world.getRandom().nextGaussian()),
                            spawnY, (float) (player.getZ() + world.getRandom().nextGaussian()), new EntityGhost(world));
                }
            }
        }
    }

    private static void trySpawn(Level world, float x, float y, float z, LivingEntity e) {
        // Set the spawn location and a random yaw (0-360 degrees)
        e.moveTo(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
        // Check if the spawn is allowed
        net.minecraftforge.eventbus.api.Event.Result canSpawn = ForgeEventFactory.canEntitySpawn(e, world, x, y, z);
        if (canSpawn == net.minecraftforge.eventbus.api.Event.Result.ALLOW || canSpawn == net.minecraftforge.eventbus.api.Event.Result.DEFAULT) {
            world.addFreshEntity(e);
            ForgeEventFactory.doSpecialSpawn(e, world, x, y, z);
            // Finalize spawn (using MOB_SUMMONED as the spawn type)
            if (e instanceof LivingEntity && world instanceof ServerLevel) {
                ((LivingEntity) e).finalizeSpawn((ServerLevel) world, world.getCurrentDifficultyAt(new BlockPos(x, y, z)),
                        MobSpawnType.MOB_SUMMONED, null, null);
            }
        }
    }

    public static void markFBI(Player player) {
        if (!player.level().isClientSide()) {
            // Set "fbiMark" to current game time plus 20*60*20 ticks (i.e. delay)
            long newMark = player.level().getGameTime() + 20 * 60 * 20;
            // Use the persistent "PlayerPersisted" tag for saving custom data.
            player.getPersistentData().getCompound("PlayerPersisted").putLong("fbiMark", newMark);
        }
    }

    public static int meteorShower = 0;

    private static void meteorUpdate(Level world) {
        int chance = meteorShower > 0 ? WorldConfig.meteorShowerChance : WorldConfig.meteorStrikeChance;
        if (meteorRand.nextInt(chance) == 0) {
            if (!world.players().isEmpty()) {
                Player p = world.players().get(meteorRand.nextInt(world.players().size()));
                // Check dimension; assuming overworld has dimension ID 0
                if (p != null && p.level().dimension().equals(Level.OVERWORLD)) {
                    boolean repell = false;
                    boolean strike = true;
                    // Loop over armor slots (FEET, LEGS, CHEST, HEAD)
                    for (net.minecraft.world.entity.EquipmentSlot slot : new net.minecraft.world.entity.EquipmentSlot[] {
                            net.minecraft.world.entity.EquipmentSlot.FEET,
                            net.minecraft.world.entity.EquipmentSlot.LEGS,
                            net.minecraft.world.entity.EquipmentSlot.CHEST,
                            net.minecraft.world.entity.EquipmentSlot.HEAD }) {
                        // Use getItemBySlot; returns an empty stack if none
                        if (!p.getItemBySlot(slot).isEmpty() && ArmorModHandler.hasMods(p.getItemBySlot(slot))) {
                            for (int j = 0; j < 8; j++) {
                                if (!ArmorModHandler.pryMods(p.getItemBySlot(slot))[j].isEmpty()) {
                                    if (ArmorModHandler.pryMods(p.getItemBySlot(slot))[j].getItem() == ModItems.protection_charm)
                                        repell = true;
                                    if (ArmorModHandler.pryMods(p.getItemBySlot(slot))[j].getItem() == ModItems.meteor_charm)
                                        strike = false;
                                }
                            }
                        }
                    }
                    if (strike)
                        spawnMeteorAtPlayer(p, repell);
                }
            }
        }

        if (meteorShower > 0) {
            meteorShower--;
            if (meteorShower == 0 && GeneralConfig.enableDebugMode)
                HBMNuclearTechCriticality.logger.info("Ended meteor shower.");
        }

        if (meteorRand.nextInt(WorldConfig.meteorStrikeChance * 100) == 0 && WorldConfig.enableMeteorShowers) {
            meteorShower = (int) (WorldConfig.meteorShowerDuration * 0.75 + WorldConfig.meteorShowerDuration * 0.25 * meteorRand.nextFloat());
            if (GeneralConfig.enableDebugMode)
                HBMNuclearTechCriticality.logger.info("Started meteor shower! Duration: " + meteorShower);
        }
    }

    public static void spawnMeteorAtPlayer(Player player, boolean repell) {
        EntityMeteor meteor = new EntityMeteor(player.level());
        // Spawn meteor at a high altitude (Y=384) with a random offset near the player
        meteor.moveTo(player.getX() + meteorRand.nextInt(201) - 100, 384, player.getZ() + meteorRand.nextInt(201) - 100, 0, 0);

        Vec3 vec;
        if (repell) {
            // Create a normalized vector from the player to the meteor
            vec = new Vec3(meteor.getX() - player.getX(), 0, meteor.getZ() - player.getZ()).normalize();
            double vel = meteorRand.nextDouble();
            vec = new Vec3(vec.x * vel, vec.y, vec.z * vel);
            meteor.safe = true; // assuming "safe" is a public field or has a setter
        } else {
            vec = new Vec3(meteorRand.nextDouble() - 0.5D, 0, 0);
            float angleDeg = (float) (360 * meteorRand.nextDouble());
            vec = vec.yRot(angleDeg);
        }
        // Set meteor motion; in modern Minecraft, use setDeltaMovement
        meteor.setDeltaMovement(vec.x, -2.5, vec.z);
        player.level().addFreshEntity(meteor);
    }
}
