package com.miniverse.hbm.util;

import api.hbm.block.ICrucibleAcceptor;
import com.hbm.inventory.material.Mats.MaterialStack;
import com.hbm.inventory.material.NTMMaterial.SmeltingBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CrucibleUtil {

    /**
     * Standard pouring, casting a hitscan straight down at the given coordinates with the given range.
     * Returns the leftover material (just like ICrucibleAcceptor's pour).
     * The method directly modifies the original stack, so be sure to copy it beforehand if needed.
     * Pass an empty MutableVec3 instance to get the impact position of the stream.
     */
    public static MaterialStack pourSingleStack(Level world, double x, double y, double z, double range, boolean safe, MaterialStack stack, int quanta, MutableVec3 impactPosHolder) {
        Vec3 start = new Vec3(x, y, z);
        Vec3 end = new Vec3(x, y - range, z);

        BlockHitResult[] hitResultHolder = new BlockHitResult[1];
        ICrucibleAcceptor acc = getPouringTarget(world, start, end, hitResultHolder);
        BlockHitResult hitResult = hitResultHolder[0];

        if (acc == null) {
            spill(hitResult, safe, stack, quanta, impactPosHolder);
            return stack;
        }

        MaterialStack ret = tryPourStack(world, acc, hitResult, stack, impactPosHolder);
        if (ret != null) {
            return ret;
        }

        spill(hitResult, safe, stack, quanta, impactPosHolder);
        return stack;
    }

    /**
     * Standard pouring, casting a hitscan straight down at the given coordinates with the given range.
     * Returns the MaterialStack that has been removed.
     * Note: This method does not copy the MaterialStacks in the list, so modifications apply directly.
     * Pass an empty MutableVec3 instance to receive the stream's impact position.
     */
    public static MaterialStack pourFullStack(Level world, double x, double y, double z, double range, boolean safe, List<MaterialStack> stacks, int quanta, MutableVec3 impactPosHolder) {
        if (stacks.isEmpty()) return null;

        Vec3 start = new Vec3(x, y, z);
        Vec3 end = new Vec3(x, y - range, z);

        BlockHitResult[] hitResultHolder = new BlockHitResult[1];
        ICrucibleAcceptor acc = getPouringTarget(world, start, end, hitResultHolder);
        BlockHitResult hitResult = hitResultHolder[0];

        if (acc == null) {
            return spill(hitResult, safe, stacks, quanta, impactPosHolder);
        }

        for (MaterialStack stack : stacks) {
            if (stack.material == null) continue;

            int amountToPour = Math.min(stack.amount, quanta);
            MaterialStack toPour = new MaterialStack(stack.material, amountToPour);
            MaterialStack left = tryPourStack(world, acc, hitResult, toPour, impactPosHolder);

            if (left != null) {
                stack.amount -= (amountToPour - left.amount);
                return new MaterialStack(stack.material, stack.amount - left.amount);
            }
        }

        return spill(hitResult, safe, stacks, quanta, impactPosHolder);
    }

    /**
     * Tries to pour the given stack onto the provided crucible acceptor.
     * Fills the provided MutableVec3 with the stream's impact position.
     * Returns the remaining MaterialStack when successful, or null on failure.
     */
    public static MaterialStack tryPourStack(Level world, ICrucibleAcceptor acc, BlockHitResult hitResult, MaterialStack stack, MutableVec3 impactPosHolder) {
        Vec3 hit = hitResult.getLocation();

        if (stack.material.smeltable != SmeltingBehavior.SMELTABLE) {
            return null;
        }

        BlockPos pos = hitResult.getBlockPos();
        Direction side = hitResult.getDirection();

        if (acc.canAcceptPartialPour(world, pos.getX(), pos.getY(), pos.getZ(), hit.x, hit.y, hit.z, side, stack)) {
            MaterialStack left = acc.pour(world, pos.getX(), pos.getY(), pos.getZ(), hit.x, hit.y, hit.z, side, stack);
            if (left == null) {
                left = new MaterialStack(stack.material, 0);
            }

            if (impactPosHolder != null) {
                impactPosHolder.x = hit.x;
                impactPosHolder.y = hit.y;
                impactPosHolder.z = hit.z;
            }
            return left;
        }

        return null;
    }

    /**
     * Uses hitscan to find the target of the pour from start (top) to end (bottom).
     * Pass a single-element BlockHitResult array to receive the hit result reference.
     */
    public static ICrucibleAcceptor getPouringTarget(Level world, Vec3 start, Vec3 end, BlockHitResult[] hitResultHolder) {
        ClipContext context = new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null);
        BlockHitResult result = world.clip(context);

        if (hitResultHolder != null) {
            hitResultHolder[0] = result;
        }

        if (result == null || result.getType() != HitResult.Type.BLOCK) {
            return null;
        }

        Block b = world.getBlockState(result.getBlockPos()).getBlock();
        if (!(b instanceof ICrucibleAcceptor)) {
            return null;
        }

        return (ICrucibleAcceptor) b;
    }

    /**
     * Spillage routine for when no valid crucible acceptor is found.
     * This version accepts a list of MaterialStacks and uses the first available one.
     * Removes any stacks that become empty.
     */
    public static MaterialStack spill(BlockHitResult hitResult, boolean safe, List<MaterialStack> stacks, int quanta, MutableVec3 impactPos) {
        MaterialStack top = stacks.get(0);
        MaterialStack ret = spill(hitResult, safe, top, quanta, impactPos);
        stacks.removeIf(o -> o.amount <= 0);
        return ret;
    }

    /**
     * Routine used when no valid crucible acceptor is found.
     * If safe mode is enabled, does nothing. Otherwise, subtracts the poured amount and returns it.
     */
    public static MaterialStack spill(BlockHitResult hitResult, boolean safe, MaterialStack stack, int quanta, MutableVec3 impactPos) {
        if (safe) {
            return null;
        }

        MaterialStack toWaste = new MaterialStack(stack.material, Math.min(stack.amount, quanta));
        stack.amount -= toWaste.amount;

        if (impactPos != null && hitResult != null) {
            Vec3 hit = hitResult.getLocation();
            impactPos.x = hit.x;
            impactPos.y = hit.y;
            impactPos.z = hit.z;
        }

        return toWaste;
    }
}
