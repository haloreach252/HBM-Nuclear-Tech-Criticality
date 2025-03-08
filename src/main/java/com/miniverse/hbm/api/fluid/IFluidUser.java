package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;
import com.miniverse.hbm.util.Compat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.PacketDistributor;

/**
 * Interface for objects that interact with fluid networks in the HBM mod.
 */
public interface IFluidUser extends IFluidConnector {

    /**
     * Sends fluid from a tank to a specified location.
     *
     * @param tank The fluid tank.
     * @param level The level where the fluid is being sent.
     * @param pos The position of the receiving block.
     * @param dir The direction of the transfer.
     */
    default void sendFluid(FluidTank tank, Level level, BlockPos pos, Direction dir) {
        sendFluid(tank.getTankType(), tank.getPressure(), level, pos, dir);
    }

    /**
     * Sends fluid of a given type to a specific position and direction.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @param level The level where the fluid is being sent.
     * @param pos The position of the receiving block.
     * @param dir The direction of the transfer.
     */
    default void sendFluid(FluidType type, int pressure, Level level, BlockPos pos, Direction dir) {
        BlockEntity te = level.getBlockEntity(pos);
        boolean wasSubscribed = false;
        boolean red = false;

        if (te instanceof IFluidConductor con) {
            if (con.getPipeNet(type) != null && con.getPipeNet(type).isSubscribed(this)) {
                con.getPipeNet(type).unsubscribe(this);
                wasSubscribed = true;
            }
        }

        if (te instanceof IFluidConnector con) {
            if (con.canConnect(type, dir.getOpposite())) {
                long toSend = this.getTotalFluidForSend(type, pressure);
                if (toSend > 0) {
                    long transfer = toSend - con.transferFluid(type, pressure, toSend);
                    this.removeFluidForTransfer(type, pressure, transfer);
                }
                red = true;
            }
        }

        if (wasSubscribed && te instanceof IFluidConductor con) {
            if (con.getPipeNet(type) != null && !con.getPipeNet(type).isSubscribed(this)) {
                con.getPipeNet(type).subscribe(this);
            }
        }

        if (particleDebug) {
            CompoundTag data = new CompoundTag();
            data.putString("type", "network");
            data.putString("mode", "fluid");
            data.putInt("color", type.getColor());

            double posX = pos.getX() + 0.5 - dir.getStepX() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
            double posY = pos.getY() + 0.5 - dir.getStepY() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
            double posZ = pos.getZ() + 0.5 - dir.getStepZ() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;

            data.putDouble("mX", dir.getStepX() * (red ? 0.025 : 0.1));
            data.putDouble("mY", dir.getStepY() * (red ? 0.025 : 0.1));
            data.putDouble("mZ", dir.getStepZ() * (red ? 0.025 : 0.1));

            PacketDispatcher.wrapper.send(PacketDistributor.NEAR.with(
                    () -> new PacketDistributor.TargetPoint(
                            level.dimension().location().toString(), posX, posY, posZ, 25
                    )
            ), new AuxParticlePacketNT(data, posX, posY, posZ));
        }
    }

    /**
     * Retrieves the fluid pipe network at the specified position.
     *
     * @param level The level.
     * @param pos The position.
     * @param type The fluid type.
     * @return The pipe network if found, otherwise null.
     */
    static IPipeNet getPipeNet(Level level, BlockPos pos, FluidType type) {
        BlockEntity te = Compat.getTileStandard(level, pos);
        if (te instanceof IFluidConductor con) {
            return con.getPipeNet(type);
        }
        return null;
    }

    /** Deprecated methods replaced with BlockPos-based alternatives */
    @Deprecated
    default void sendFluidToAll(FluidTank tank, BlockEntity te) {
        sendFluidToAll(tank.getTankType(), tank.getPressure(), te);
    }

    @Deprecated
    default void sendFluidToAll(FluidType type, int pressure, BlockEntity te) {
        for (Direction dir : Direction.values()) {
            sendFluid(type, pressure, te.getLevel(), te.getBlockPos().relative(dir), dir);
        }
    }

    /**
     * Gets the total amount of fluid available for sending.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @return The total amount of fluid available.
     */
    default long getTotalFluidForSend(FluidType type, int pressure) {
        return 0;
    }

    /**
     * Removes a specified amount of fluid after transfer.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @param amount The amount to remove.
     */
    default void removeFluidForTransfer(FluidType type, int pressure, long amount) { }

    /**
     * Subscribes this fluid user to all connected pipes.
     *
     * @param type The fluid type.
     * @param te The block entity.
     */
    default void subscribeToAllAround(FluidType type, BlockEntity te) {
        subscribeToAllAround(type, te.getLevel(), te.getBlockPos());
    }

    default void subscribeToAllAround(FluidType type, Level level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            this.trySubscribe(type, level, pos.relative(dir), dir);
        }
    }

    /**
     * Unsubscribes this fluid user from all connected pipes.
     *
     * @param type The fluid type.
     * @param te The block entity.
     */
    default void unsubscribeToAllAround(FluidType type, BlockEntity te) {
        unsubscribeToAllAround(type, te.getLevel(), te.getBlockPos());
    }

    default void unsubscribeToAllAround(FluidType type, Level level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            this.tryUnsubscribe(type, level, pos.relative(dir));
        }
    }

    /**
     * Retrieves all internal tanks of this fluid user.
     *
     * @return An array of fluid tanks.
     */
    FluidTank[] getAllTanks();
}
