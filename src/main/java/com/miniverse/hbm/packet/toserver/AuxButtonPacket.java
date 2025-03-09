package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.config.MobConfig;
import com.miniverse.hbm.entity.mob.EntityDuck;
import com.miniverse.hbm.items.weapon.ItemCustomMissilePart.PartSize;
import com.miniverse.hbm.tileentity.TileEntityMachineBase;
import com.miniverse.hbm.tileentity.TileEntityTickingBase;
import com.miniverse.hbm.tileentity.bomb.TileEntityLaunchTable;
import com.miniverse.hbm.tileentity.machine.TileEntityCoreEmitter;
import com.miniverse.hbm.tileentity.machine.TileEntityCoreStabilizer;
import com.miniverse.hbm.tileentity.machine.TileEntityForceField;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineMiningLaser;
import com.miniverse.hbm.tileentity.machine.TileEntityMachineMissileAssembly;
import com.miniverse.hbm.tileentity.machine.TileEntitySoyuzLauncher;
import com.miniverse.hbm.tileentity.machine.storage.TileEntityBarrel;
import com.miniverse.hbm.tileentity.machine.storage.TileEntityMachineBattery;

import api.hbm.energymk2.IEnergyReceiverMK2.ConnectionPriority;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AuxButtonPacket {

    private int x;
    private int y;
    private int z;
    private int value;
    private int id;

    public AuxButtonPacket() { }

    public AuxButtonPacket(int x, int y, int z, int value, int id) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
        this.id = id;
    }

    public static AuxButtonPacket decode(FriendlyByteBuf buf) {
        AuxButtonPacket packet = new AuxButtonPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.value = buf.readInt();
        packet.id = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(value);
        buf.writeInt(id);
    }

    public static void handle(AuxButtonPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            BlockEntity te = player.level.getBlockEntity(pos);

            if (te instanceof TileEntityForceField) {
                TileEntityForceField field = (TileEntityForceField) te;
                field.isOn = !field.isOn;
                field.setChanged();
            }

            if (te instanceof TileEntityMachineMissileAssembly) {
                TileEntityMachineMissileAssembly assembly = (TileEntityMachineMissileAssembly) te;
                assembly.construct();
                assembly.setChanged();
            }

            if (te instanceof TileEntityLaunchTable) {
                TileEntityLaunchTable launcher = (TileEntityLaunchTable) te;
                launcher.padSize = PartSize.values()[msg.value];
                launcher.setChanged();
            }

            if (te instanceof TileEntityCoreEmitter) {
                TileEntityCoreEmitter core = (TileEntityCoreEmitter) te;
                if (msg.id == 0) {
                    core.watts = msg.value;
                }
                if (msg.id == 1) {
                    core.isOn = !core.isOn;
                }
                core.setChanged();
            }

            if (te instanceof TileEntityCoreStabilizer) {
                TileEntityCoreStabilizer core = (TileEntityCoreStabilizer) te;
                if (msg.id == 0) {
                    core.watts = msg.value;
                }
                core.setChanged();
            }

            if (te instanceof TileEntityBarrel) {
                TileEntityBarrel barrel = (TileEntityBarrel) te;
                barrel.mode = (short) ((barrel.mode + 1) % barrel.modes);
                barrel.setChanged();
            }

            if (te instanceof TileEntityMachineBattery) {
                TileEntityMachineBattery bat = (TileEntityMachineBattery) te;
                if (msg.id == 0) {
                    bat.redLow = (short) ((bat.redLow + 1) % 4);
                    bat.setChanged();
                }
                if (msg.id == 1) {
                    bat.redHigh = (short) ((bat.redHigh + 1) % 4);
                    bat.setChanged();
                }
                if (msg.id == 2) {
                    switch (bat.priority) {
                        case LOW -> bat.priority = ConnectionPriority.NORMAL;
                        case NORMAL -> bat.priority = ConnectionPriority.HIGH;
                        case HIGH -> bat.priority = ConnectionPriority.LOW;
                    }
                    bat.setChanged();
                }
            }

            if (te instanceof TileEntitySoyuzLauncher) {
                TileEntitySoyuzLauncher launcher = (TileEntitySoyuzLauncher) te;
                if (msg.id == 0)
                    launcher.mode = (byte) msg.value;
                if (msg.id == 1)
                    launcher.startCountdown();
                launcher.setChanged();
            }

            if (te instanceof TileEntityMachineMiningLaser) {
                TileEntityMachineMiningLaser laser = (TileEntityMachineMiningLaser) te;
                laser.isOn = !laser.isOn;
                laser.setChanged();
            }

            if (te instanceof TileEntityMachineBase) {
                TileEntityMachineBase base = (TileEntityMachineBase) te;
                base.handleButtonPacket(msg.value, msg.id);
                base.setChanged();
            }
            if (te instanceof TileEntityTickingBase) {
                TileEntityTickingBase base = (TileEntityTickingBase) te;
                base.handleButtonPacket(msg.value, msg.id);
                base.setChanged();
            }

            // If no tile entity is found at the position and msg.value equals 999, spawn a duck entity.
            if (te == null && msg.value == 999) {
                CompoundTag perDat = player.getPersistentData();
                if (MobConfig.enableDucks && !perDat.getBoolean("hasDucked")) {
                    EntityDuck ducc = new EntityDuck(player.level);
                    ducc.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());
                    Vec3 look = player.getLookAngle();
                    ducc.setDeltaMovement(look.x, look.y, look.z);
                    player.level.addFreshEntity(ducc);

                    // Play duck sound. Retrieve the SoundEvent from Forge registries.
                    ResourceLocation duckSoundLoc = new ResourceLocation("hbm", "entity.ducc");
                    SoundEvent duckSound = ForgeRegistries.SOUND_EVENTS.getValue(duckSoundLoc);
                    if (duckSound != null) {
                        player.level.playSound(null, player.blockPosition(), duckSound, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }

                    perDat.putBoolean("hasDucked", true);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
