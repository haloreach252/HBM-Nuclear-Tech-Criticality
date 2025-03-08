package com.miniverse.hbm.api.energymk2;

import com.miniverse.hbm.interfaces.NotableComments;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;
import com.miniverse.hbm.uninos.IGenReceiver;
import com.miniverse.hbm.uninos.networkproviders.PowerNetProvider;
import com.miniverse.hbm.util.Compat;
import com.miniverse.hbm.energymk2.Nodespace.PowerNode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.PacketDistributor;

/**
 * If it receives energy, use this.
 */
@NotableComments
public interface IEnergyReceiverMK2 extends IEnergyHandlerMK2, IGenReceiver<PowerNetProvider> {

    /**
     * Transfers power to this energy receiver.
     *
     * @param power The amount of power being transferred.
     * @return The amount of power that exceeded the capacity.
     */
    default long transferPower(long power) {
        if (power + this.getPower() <= this.getMaxPower()) {
            this.setPower(power + this.getPower());
            return 0;
        }
        long capacity = this.getMaxPower() - this.getPower();
        long overshoot = power - capacity;
        this.setPower(this.getMaxPower());
        return overshoot;
    }

    /**
     * Gets the maximum speed at which the receiver can accept power.
     *
     * @return The receiver's speed.
     */
    default long getReceiverSpeed() {
        return this.getMaxPower();
    }

    /**
     * Attempts to subscribe this receiver to a conductor.
     *
     * @param level The world (level) where the receiver is located.
     * @param pos The position of the receiver.
     * @param dir The direction of the connection.
     */
    default void trySubscribe(Level level, BlockPos pos, Direction dir) {

        BlockEntity te = Compat.getTileStandard(level, pos.relative(dir));
        boolean red = false;

        if (te instanceof IEnergyConductorMK2 con) {
            if (!con.canConnect(dir.getOpposite())) return;

            PowerNode node = Nodespace.getNode(level, pos.relative(dir));

            if (node != null && node.net != null) {
                node.net.addReceiver(this);
                red = true;
            }
        }

        if (particleDebug) {
            CompoundTag data = new CompoundTag();
            data.putString("type", "network");
            data.putString("mode", "power");

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
     * Attempts to unsubscribe this receiver from a conductor.
     *
     * @param level The world (level) where the receiver is located.
     * @param pos The position of the receiver.
     */
    default void tryUnsubscribe(Level level, BlockPos pos) {
        BlockEntity te = level.getBlockEntity(pos);

        if (te instanceof IEnergyConductorMK2 con) {
            PowerNode node = con.createNode();

            if (node != null && node.net != null) {
                node.net.removeReceiver(this);
            }
        }
    }

    /**
     * Defines the connection priority for energy receivers.
     */
    enum ConnectionPriority {
        LOWEST,
        LOW,
        NORMAL,
        HIGH,
        HIGHEST
    }
}
