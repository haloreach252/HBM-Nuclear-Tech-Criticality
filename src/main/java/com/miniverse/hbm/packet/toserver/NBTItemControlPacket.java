package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.items.IItemControlReceiver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * This packet is used to send an NBT (CompoundTag) from the client to the server.
 * When handled, it retrieves the item held in the player's main hand and, if the item implements
 * IItemControlReceiver, calls its receiveControl method with the NBT data.
 */
public class NBTItemControlPacket {

    private CompoundTag nbt;

    // Default constructor for reflection/registration.
    public NBTItemControlPacket() { }

    // Create a packet with the given NBT data.
    public NBTItemControlPacket(CompoundTag nbt) {
        this.nbt = nbt;
    }

    // Deserialize the packet from the buffer.
    public NBTItemControlPacket(FriendlyByteBuf buf) {
        // readNbt handles null values as well
        this.nbt = buf.readNbt();
    }

    // Serialize the packet into the buffer.
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(nbt);
    }

    /**
     * Handles the packet on the server side.
     * It retrieves the sender from the context, gets the item in their main hand,
     * and if that item is a control receiver, passes along the NBT.
     */
    public static void handle(NBTItemControlPacket message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            // Get the sending player. (Only valid on the server side.)
            var player = ctx.getSender();
            if (player != null) {
                ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!held.isEmpty() && held.getItem() instanceof IItemControlReceiver receiver) {
                    receiver.receiveControl(held, message.nbt);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
