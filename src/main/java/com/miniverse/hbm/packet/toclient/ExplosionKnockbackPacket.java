package com.miniverse.hbm.packet.toclient;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ExplosionKnockbackPacket {

    private float motionX;
    private float motionY;
    private float motionZ;

    public ExplosionKnockbackPacket() { }

    public ExplosionKnockbackPacket(Vec3 vec) {
        this.motionX = (float) vec.x();
        this.motionY = (float) vec.y();
        this.motionZ = (float) vec.z();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(this.motionX);
        buf.writeFloat(this.motionY);
        buf.writeFloat(this.motionZ);
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.motionX = buf.readFloat();
        this.motionY = buf.readFloat();
        this.motionZ = buf.readFloat();
    }

    public static class Handler {
        public static void handle(final ExplosionKnockbackPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().player == null)
                    return;
                // Get the player's current velocity.
                Vec3 currentVelocity = Minecraft.getInstance().player.getDeltaMovement();
                // Create a vector for the knockback.
                Vec3 knockback = new Vec3(message.motionX, message.motionY, message.motionZ);
                // Set the new velocity as the sum of current velocity and knockback.
                Minecraft.getInstance().player.setDeltaMovement(currentVelocity.add(knockback));
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
