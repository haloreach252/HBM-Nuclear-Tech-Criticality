package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.tileentity.machine.TileEntityForceField;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TEFFPacket {

    private int x;
    private int y;
    private int z;
    private float rad;
    private int health;
    private int maxHealth;
    private int power;
    private boolean isOn;
    private int color;
    private int cooldown;

    public TEFFPacket() { }

    public TEFFPacket(int x, int y, int z, float rad, int health, int maxHealth, int power, boolean isOn, int color, int cooldown) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rad = rad;
        this.health = health;
        this.maxHealth = maxHealth;
        this.power = power;
        this.isOn = isOn;
        this.color = color;
        this.cooldown = cooldown;
    }

    public static TEFFPacket decode(FriendlyByteBuf buf) {
        TEFFPacket packet = new TEFFPacket();
        packet.x = buf.readInt();
        packet.y = buf.readInt();
        packet.z = buf.readInt();
        packet.rad = buf.readFloat();
        packet.health = buf.readInt();
        packet.maxHealth = buf.readInt();
        packet.power = buf.readInt();
        packet.isOn = buf.readBoolean();
        packet.color = buf.readInt();
        packet.cooldown = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeFloat(rad);
        buf.writeInt(health);
        buf.writeInt(maxHealth);
        buf.writeInt(power);
        buf.writeBoolean(isOn);
        buf.writeInt(color);
        buf.writeInt(cooldown);
    }

    public static void handle(TEFFPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            BlockPos pos = new BlockPos(msg.x, msg.y, msg.z);
            if (Minecraft.getInstance().level != null) {
                BlockEntity te = Minecraft.getInstance().level.getBlockEntity(pos);
                if (te instanceof TileEntityForceField) {
                    TileEntityForceField ff = (TileEntityForceField) te;
                    ff.radius = msg.rad;
                    ff.health = msg.health;
                    ff.maxHealth = msg.maxHealth;
                    ff.power = msg.power;
                    ff.isOn = msg.isOn;
                    ff.color = msg.color;
                    ff.cooldown = msg.cooldown;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
