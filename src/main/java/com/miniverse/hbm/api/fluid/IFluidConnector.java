package com.miniverse.hbm.api.fluid;

import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;
import com.miniverse.hbm.api.tile.ILoadedTile;
import com.miniverse.hbm.util.Compat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.PacketDistributor;

/**
 * Interface for fluid connectors in the HBM fluid system.
 */
public interface IFluidConnector extends ILoadedTile {

    /**
     * Transfers fluid and returns the remaining amount that couldn't be transferred.
     *
     * @param type The fluid type being transferred.
     * @param pressure The fluid pressure.
     * @param fluid The amount of fluid.
     * @return The remaining fluid amount that couldn't be transferred.
     */
    long transferFluid(FluidType type, int pressure, long fluid);

    /**
     * Determines whether this connector can connect to a given side.
     *
     * @param type The fluid type.
     * @param dir The direction being checked.
     * @return True if the connection is possible, false otherwise.
     */
    default boolean canConnect(FluidType type, Direction dir) {
        return dir != null;
    }

    /**
     * Returns the amount of fluid that this machine is able to receive.
     *
     * @param type The fluid type.
     * @param pressure The fluid pressure.
     * @return The amount of fluid that can be received.
     */
    long getDemand(FluidType type, int pressure);

    /**
     * Attempts to subscribe this connector to a nearby fluid network.
     *
     * @param type The fluid type.
     * @param level The world (level) where the connector is located.
     * @param pos The position of the connector.
     * @param dir The direction of the connection.
     */
    default void trySubscribe(FluidType type, Level level, BlockPos pos, Direction dir) {

        BlockEntity te = Compat.getTileStandard(level, pos.relative(dir));
        boolean red = false;

        if (te instanceof IFluidConductor con) {
            if (!con.canConnect(type, dir)) return;

            if (con.getPipeNet(type) != null && !con.getPipeNet(type).isSubscribed(this))
                con.getPipeNet(type).subscribe(this);

            if (con.getPipeNet(type) != null)
                red = true;
        }

        if (particleDebug) {
            CompoundTag data = new CompoundTag();
            data.putString("type", "network");
            data.putString("mode", "fluid");
            data.putInt("color", type.getColor());

            double posX = pos.getX() + 0.5 + dir.getStepX() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
            double posY = pos.getY() + 0.5 + dir.getStepY() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
            double posZ = pos.getZ() + 0.5 + dir.getStepZ() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;

            data.putDouble("mX", -dir.getStepX() * (red ? 0.025 : 0.1));
            data.putDouble("mY", -dir.getStepY() * (red ? 0.025 : 0.1));
            data.putDouble("mZ", -dir.getStepZ() * (red ? 0.025 : 0.1));

            PacketDispatcher.wrapper.send(PacketDistributor.NEAR.with(
                    () -> new PacketDistributor.TargetPoint(
                            level.dimension().location().toString(), posX, posY, posZ, 25
                    )
            ), new AuxParticlePacketNT(data, posX, posY, posZ));
        }
    }

    /**
     * Attempts to unsubscribe this connector from the fluid network.
     *
     * @param type The fluid type.
     * @param level The world (level) where the connector is located.
     * @param pos The position of the connector.
     */
    default void tryUnsubscribe(FluidType type, Level level, BlockPos pos) {
        BlockEntity te = level.getBlockEntity(pos);

        if (te instanceof IFluidConductor con) {
            if (con.getPipeNet(type) != null && con.getPipeNet(type).isSubscribed(this))
                con.getPipeNet(type).unsubscribe(this);
        }
    }

    boolean particleDebug = false;
}
