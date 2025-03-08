package com.miniverse.hbm.api.energymk2;

import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toclient.AuxParticlePacketNT;
import com.miniverse.hbm.uninos.IGenProvider;
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
 * Interface for energy providers in the MK2 energy system.
 * If it sends energy, use this.
 */
public interface IEnergyProviderMK2 extends IEnergyHandlerMK2, IGenProvider<PowerNetProvider> {

    /**
     * Uses up available power. The default implementation has no sanity checking,
     * so ensure that the requested power is less than or equal to the current power.
     *
     * @param power The amount of power to use.
     */
    default void usePower(long power) {
        this.setPower(this.getPower() - power);
    }

    /**
     * Gets the maximum speed at which the provider can supply power.
     *
     * @return The provider's speed.
     */
    default long getProviderSpeed() {
        return this.getMaxPower();
    }

    /**
     * Attempts to provide power to an adjacent block in the specified direction.
     *
     * @param level The world (level) where the provider is located.
     * @param pos The position of the provider.
     * @param dir The direction in which power is provided.
     */
    default void tryProvide(Level level, BlockPos pos, Direction dir) {

        BlockEntity te = Compat.getTileStandard(level, pos.relative(dir));
        boolean red = false;

        if (te instanceof IEnergyConductorMK2 con) {
            if (con.canConnect(dir.getOpposite())) {
                PowerNode node = Nodespace.getNode(level, pos.relative(dir));

                if (node != null && node.net != null) {
                    node.net.addProvider(this);
                    red = true;
                }
            }
        }

        if (te instanceof IEnergyReceiverMK2 rec && te != this) {
            if (rec.canConnect(dir.getOpposite())) {
                long provides = Math.min(this.getPower(), this.getProviderSpeed());
                long receives = Math.min(rec.getMaxPower() - rec.getPower(), rec.getReceiverSpeed());
                long toTransfer = Math.min(provides, receives);
                toTransfer -= rec.transferPower(toTransfer);
                this.usePower(toTransfer);
            }
        }

        if (particleDebug) {
            CompoundTag data = new CompoundTag();
            data.putString("type", "network");
            data.putString("mode", "power");

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
}
