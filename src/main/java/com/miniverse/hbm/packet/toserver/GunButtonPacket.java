package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.items.weapon.ItemGunBase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GunButtonPacket {

    private boolean state;
    private byte button;

    public GunButtonPacket() { }

    public GunButtonPacket(boolean state, byte button) {
        this.state = state;
        this.button = button;
    }

    public static GunButtonPacket decode(FriendlyByteBuf buf) {
        GunButtonPacket packet = new GunButtonPacket();
        packet.state = buf.readBoolean();
        packet.button = buf.readByte();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(state);
        buf.writeByte(button);
    }

    public static void handle(GunButtonPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;
        ctx.get().enqueueWork(() -> {
            ItemStack heldStack = player.getMainHandItem();
            if (!heldStack.isEmpty() && heldStack.getItem() instanceof ItemGunBase) {
                ItemGunBase item = (ItemGunBase) heldStack.getItem();
                switch (msg.button) {
                    case 0:
                        ItemGunBase.setIsMouseDown(heldStack, msg.state);
                        if (msg.state)
                            item.startAction(heldStack, player.level, player, true);
                        else
                            item.endAction(heldStack, player.level, player, true);
                        break;
                    case 1:
                        ItemGunBase.setIsAltDown(heldStack, msg.state);
                        if (msg.state)
                            item.startAction(heldStack, player.level, player, false);
                        else
                            item.endAction(heldStack, player.level, player, false);
                        break;
                    case 2:
                        if (item.canReload(heldStack, player.level, player)) {
                            item.startReloadAction(heldStack, player.level, player);
                        }
                        break;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
