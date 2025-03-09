package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.items.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ItemDesignatorPacket {

    private int operator;
    private int value;
    private int reference;

    public ItemDesignatorPacket() { }

    public ItemDesignatorPacket(int operator, int value, int reference) {
        this.operator = operator;
        this.value = value;
        this.reference = reference;
    }

    public static ItemDesignatorPacket decode(FriendlyByteBuf buf) {
        ItemDesignatorPacket packet = new ItemDesignatorPacket();
        packet.operator = buf.readInt();
        packet.value = buf.readInt();
        packet.reference = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(operator);
        buf.writeInt(value);
        buf.writeInt(reference);
    }

    public static void handle(ItemDesignatorPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;
        ctx.get().enqueueWork(() -> {
            ItemStack stack = player.getMainHandItem();
            if (!stack.isEmpty() && stack.getItem() == ModItems.designator_manual) {
                CompoundTag tag = stack.getOrCreateTag();
                int x = tag.getInt("xCoord");
                int z = tag.getInt("zCoord");
                int result = 0;
                if (msg.operator == 0) { // Add
                    result = msg.value;
                } else if (msg.operator == 1) { // Subtract
                    result = -msg.value;
                } else if (msg.operator == 2) { // Set
                    if (msg.reference == 0)
                        tag.putInt("xCoord", (int) Math.round(player.getX()));
                    else
                        tag.putInt("zCoord", (int) Math.round(player.getZ()));
                    return;
                }
                if (msg.reference == 0)
                    tag.putInt("xCoord", x + result);
                else
                    tag.putInt("zCoord", z + result);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
