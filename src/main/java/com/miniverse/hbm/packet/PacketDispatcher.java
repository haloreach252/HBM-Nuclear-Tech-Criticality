package com.miniverse.hbm.packet;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.main.NetworkHandler;
import com.miniverse.hbm.packet.toclient.*;
import com.miniverse.hbm.packet.toserver.*;

import net.minecraftforge.network.NetworkDirection;

public class PacketDispatcher {

    // Packet Sending Device - our custom network wrapper using a SimpleChannel.
    public static final NetworkHandler wrapper = new NetworkHandler(HBMNuclearTechCriticality.MODID);

    public static void registerPackets() {
        int i = 0;

        // Signals server to consume items and create template
        wrapper.registerMessage(ItemFolderPacket.Handler.class, ItemFolderPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Siren packet for looped sounds
        wrapper.registerMessage(TESirenPacket.Handler.class, TESirenPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Signals server to change ItemStacks
        wrapper.registerMessage(ItemDesignatorPacket.Handler.class, ItemDesignatorPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Signals server to perform orbital strike, among other things
        wrapper.registerMessage(SatLaserPacket.Handler.class, SatLaserPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Universal package for sending small info packs back to server
        wrapper.registerMessage(AuxButtonPacket.Handler.class, AuxButtonPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Siren packet for looped sounds
        wrapper.registerMessage(TEVaultPacket.Handler.class, TEVaultPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Packet to send sat info to players
        wrapper.registerMessage(SatPanelPacket.Handler.class, SatPanelPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Packet to send block break particles
        wrapper.registerMessage(ParticleBurstPacket.Handler.class, ParticleBurstPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Packet to send chunk radiation info to individual players
        wrapper.registerMessage(ExtPropPacket.Handler.class, ExtPropPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Packet for force fields
        wrapper.registerMessage(TEFFPacket.Handler.class, TEFFPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Sends button information for ItemGunBase
        wrapper.registerMessage(GunButtonPacket.Handler.class, GunButtonPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Signals server to buy offer from bobmazon
        wrapper.registerMessage(ItemBobmazonPacket.Handler.class, ItemBobmazonPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Packet to send missile multipart information to TEs
        wrapper.registerMessage(TEMissileMultipartPacket.Handler.class, TEMissileMultipartPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Aux Particle Packet, New Technology: like the APP but with NBT
        wrapper.registerMessage(AuxParticlePacketNT.Handler.class, AuxParticlePacketNT.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Signals server to do coord based satellite stuff
        wrapper.registerMessage(SatCoordPacket.Handler.class, SatCoordPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Triggers gun animations of the client
        wrapper.registerMessage(GunAnimationPacket.Handler.class, GunAnimationPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Sends a fun text to display like a music disc announcement
        wrapper.registerMessage(PlayerInformPacket.Handler.class, PlayerInformPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Universal keybind packet
        wrapper.registerMessage(KeybindPacket.Handler.class, KeybindPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Packet to send NBT data from clients to server-side TEs
        wrapper.registerMessage(NBTControlPacket.Handler.class, NBTControlPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Packet to send for anvil recipes to be crafted
        wrapper.registerMessage(AnvilCraftPacket.Handler.class, AnvilCraftPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // Sends a fun text to display like a music disc announcement
        wrapper.registerMessage(TEDoorAnimationPacket.Handler.class, TEDoorAnimationPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Does ExVNT standard player knockback
        wrapper.registerMessage(ExplosionKnockbackPacket.Handler.class, ExplosionKnockbackPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Just go fuck yourself already (humorously named packet)
        wrapper.registerMessage(ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket.Handler.class,
                ExplosionVanillaNewTechnologyCompressedAffectedBlockPositionDataForClientEffectsAndParticleHandlingPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Packet to send NBT data from clients to the server-side held item
        wrapper.registerMessage(NBTItemControlPacket.Handler.class, NBTItemControlPacket.class, i++, NetworkDirection.PLAY_TO_SERVER);
        // General syncing for global values
        wrapper.registerMessage(PermaSyncPacket.Handler.class, PermaSyncPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // Syncs biome information for single positions or entire chunks
        wrapper.registerMessage(BiomeSyncPacket.Handler.class, BiomeSyncPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
        // The not-so-convenient but not laggy one
        wrapper.registerMessage(BufPacket.Handler.class, BufPacket.class, i++, NetworkDirection.PLAY_TO_CLIENT);
    }
}
