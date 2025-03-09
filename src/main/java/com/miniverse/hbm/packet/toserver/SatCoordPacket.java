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
 * This packet sends coordinate data (x, y, z) and a frequency (freq) from the client to the server.
 * On the server side, if the player is holding an item that is an instance of ItemSatInterface,
 * the packet retrieves the frequency from the held item using ISatChip.getFreqS. If the frequencies match,
 * the packet retrieves the Satellite associated with the frequency from SatelliteSavedData and calls its onCoordAction.
 */
public class SatCoordPacket {

    private int x;
    private int y;
    private int z;
    private int freq;

    // Default constructor for registration.
    public SatCoordPacket() { }

    public SatCoordPacket(int x, int y, int z, int freq) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.freq = freq;
    }

    // Deserialize from the network buffer.
    public SatCoordPacket(FriendlyByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.freq = buf.readInt();
    }

    // Serialize the data into the network buffer.
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(freq);
    }

    /**
     * Handles the packet on the server side.
     * It checks if the player is holding an item of type ItemSatInterface and if so,
     * compares the held item's frequency with the packet frequency. If they match,
     * the corresponding Satellite is retrieved and its onCoordAction is triggered.
     */
    public static void handle(SatCoordPacket message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            // Get the sending player (must be non-null on server side)
            Player player = ctx.getSender();
            if (player != null) {
                ItemStack held = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (!held.isEmpty() && held.getItem() instanceof ItemSatInterface) {
                    int heldFreq = ISatChip.getFreqS(held);
                    if (heldFreq == message.freq) {
                        Satellite sat = SatelliteSavedData.getData(player.level).getSatFromFreq(message.freq);
                        if (sat != null) {
                            sat.onCoordAction(player.level, player, message.x, message.y, message.z);
                        }
                    }
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
