package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.items.ISatChip;
import com.miniverse.hbm.items.tool.ItemSatInterface;
import com.miniverse.hbm.saveddata.SatelliteSavedData;
import com.miniverse.hbm.saveddata.satellites.Satellite;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * This packet sends x and z coordinates along with a frequency (freq) from the client to the server.
 * When received, the server checks if the player is holding an item of type ItemSatInterface.
 * If the held item's frequency (obtained via ISatChip.getFreqS) matches the packet frequency,
 * it retrieves the corresponding Satellite from SatelliteSavedData and triggers its onClick action.
 */
public class SatLaserPacket {

    private int x;
    private int z;
    private int freq;

    // Default constructor for registration.
    public SatLaserPacket() { }

    public SatLaserPacket(int x, int z, int freq) {
        this.x = x;
        this.z = z;
        this.freq = freq;
    }

    // Deserialize the packet from the network buffer.
    public SatLaserPacket(FriendlyByteBuf buf) {
        this.x = buf.readInt();
        this.z = buf.readInt();
        this.freq = buf.readInt();
    }

    // Serialize the packet into the network buffer.
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(z);
        buf.writeInt(freq);
    }

    /**
     * Handles the packet on the server side.
     * It verifies that the sender is holding an item that is an instance of ItemSatInterface.
     * If the held item's frequency matches the packet's frequency, it retrieves the associated Satellite
     * from SatelliteSavedData and triggers its onClick method.
     */
    public static void handle(SatLaserPacket message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            Player player = ctx.getSender();
            if (player != null) {
                ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!held.isEmpty() && held.getItem() instanceof ItemSatInterface) {
                    int heldFreq = ISatChip.getFreqS(held);
                    if (heldFreq == message.freq) {
                        Satellite sat = SatelliteSavedData.getData(player.level).getSatFromFreq(message.freq);
                        if (sat != null) {
                            sat.onClick(player.level, message.x, message.z);
                        }
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
