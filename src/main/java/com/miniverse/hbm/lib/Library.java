package com.miniverse.hbm.lib;

import com.miniverse.hbm.api.energymk2.IBatteryItem;
import com.miniverse.hbm.api.energymk2.IEnergyConnectorBlock;
import com.miniverse.hbm.api.energymk2.IEnergyConnectorMK2;
import com.miniverse.hbm.api.fluid.IFluidConnector;
import com.miniverse.hbm.api.fluid.IFluidConnectorBlock;
import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.entity.mob.EntityHunterChopper;
import com.miniverse.hbm.entity.projectile.EntityChopperMine;
import com.miniverse.hbm.interfaces.INeedsWork;
import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

@INeedsWork("This needs to have all the imports and stuff fixed, as well as the block and item IDs")
public class Library {

    static RandomSource rand = RandomSource.create();

    public static boolean checkForHeld(Player player, Item item) {
        return player.getMainHandItem().getItem() == item;
    }

    public static boolean canConnect(Level world, BlockPos pos, net.minecraft.core.Direction dir) {
        if (pos.getY() > 255 || pos.getY() < 0) return false;

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof IEnergyConnectorBlock connectorBlock) {
            if (connectorBlock.canConnect(world, pos, dir.getOpposite())) return true;
        }

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof IEnergyConnectorMK2 connector) {
            if (connector.canConnect(dir.getOpposite())) return true;
        }

        return false;
    }

    public static boolean canConnectFluid(Level world, BlockPos pos, net.minecraft.core.Direction dir, FluidType type) {
        if (pos.getY() > 255 || pos.getY() < 0) return false;

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof IFluidConnectorBlock fluidConnector) {
            if (fluidConnector.canConnect(type, world, pos, dir.getOpposite())) return true;
        }

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof IFluidConnector fluidConnector) {
            if (fluidConnector.canConnect(type, dir.getOpposite())) return true;
        }

        return false;
    }

    public static LivingEntity getClosestEntityForChopper(Level world, Vec3 pos, double radius) {
        return world.getEntitiesOfClass(LivingEntity.class, new AABB(pos, pos.add(1, 1, 1)).inflate(radius),
                        entity -> !(entity instanceof EntityHunterChopper) && entity.isAlive())
                .stream()
                .min((e1, e2) -> Double.compare(e1.distanceToSqr(pos), e2.distanceToSqr(pos)))
                .orElse(null);
    }

    public static Player getClosestPlayerForSound(Level world, Vec3 pos, double radius) {
        return world.getEntitiesOfClass(Player.class, new AABB(pos, pos.add(1, 1, 1)).inflate(radius),
                        Entity::isAlive)
                .stream()
                .min((e1, e2) -> Double.compare(e1.distanceToSqr(pos), e2.distanceToSqr(pos)))
                .orElse(null);
    }

    public static EntityHunterChopper getClosestChopperForSound(Level world, Vec3 pos, double radius) {
        return world.getEntitiesOfClass(EntityHunterChopper.class, new AABB(pos, pos.add(1, 1, 1)).inflate(radius),
                        Entity::isAlive)
                .stream()
                .min((e1, e2) -> Double.compare(e1.distanceToSqr(pos), e2.distanceToSqr(pos)))
                .orElse(null);
    }

    public static EntityChopperMine getClosestMineForSound(Level world, Vec3 pos, double radius) {
        return world.getEntitiesOfClass(EntityChopperMine.class, new AABB(pos, pos.add(1, 1, 1)).inflate(radius),
                        Entity::isAlive)
                .stream()
                .min((e1, e2) -> Double.compare(e1.distanceToSqr(pos), e2.distanceToSqr(pos)))
                .orElse(null);
    }

    public static long chargeItemsFromTE(ItemStack[] slots, int index, long power, long maxPower) {
        if (power < 0) return 0;
        if (power > maxPower) return maxPower;

        if (slots[index] != null && slots[index].getItem() instanceof IBatteryItem battery) {
            long toCharge = Math.min(Math.min(power, battery.getChargeRate()), battery.getMaxCharge(slots[index]) - battery.getCharge(slots[index]));
            power -= toCharge;
            battery.chargeBattery(slots[index], toCharge);
        }
        return power;
    }

    public static long chargeTEFromItems(ItemStack[] slots, int index, long power, long maxPower) {
        if (slots[index] != null) {
            if (slots[index].getItem() == ModItems.battery_creative || slots[index].getItem() == ModItems.fusion_core_infinite) {
                return maxPower;
            }
            if (slots[index].getItem() instanceof IBatteryItem battery) {
                long toDischarge = Math.min(Math.min((maxPower - power), battery.getDischargeRate()), battery.getCharge(slots[index]));
                battery.dischargeBattery(slots[index], toDischarge);
                power += toDischarge;
            }
        }
        return power;
    }

    public static double smoothstep(double t, double edge0, double edge1) {
        t = (t - edge0) / (edge1 - edge0);
        t = Math.max(0.0, Math.min(1.0, t));
        return t * t * (3.0 - 2.0 * t);
    }

    public static float smoothstep(float t, float edge0, float edge1) {
        t = (t - edge0) / (edge1 - edge0);
        t = Math.max(0.0F, Math.min(1.0F, t));
        return t * t * (3.0F - 2.0F * t);
    }

    public static boolean isObstructed(Level world, Vec3 start, Vec3 end) {
        return world.clip(new net.minecraft.world.level.ClipContext(start, end, net.minecraft.world.level.ClipContext.Block.COLLIDER, net.minecraft.world.level.ClipContext.Fluid.NONE, null))
                .getType() != HitResult.Type.MISS;
    }

    public static Block getRandomConcrete() {
        int i = rand.nextInt(20);
        if (i <= 1) return ModBlocks.brick_concrete_broken;
        if (i <= 4) return ModBlocks.brick_concrete_cracked;
        if (i <= 10) return ModBlocks.brick_concrete_mossy;
        return ModBlocks.brick_concrete;
    }
}
