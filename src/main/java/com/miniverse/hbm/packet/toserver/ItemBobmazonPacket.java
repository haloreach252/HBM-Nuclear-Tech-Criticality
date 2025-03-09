package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.entity.missile.EntityBobmazon;
import com.miniverse.hbm.handler.BobmazonOfferFactory;
import com.miniverse.hbm.inventory.gui.GUIScreenBobmazon.Offer;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.lib.ModDamageSource;
import com.miniverse.hbm.util.AchievementHandler;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class ItemBobmazonPacket {

    private int offer;

    public ItemBobmazonPacket() { }

    public ItemBobmazonPacket(ServerPlayer player, Offer offer) {
        ItemStack held = player.getMainHandItem();
        if (!held.isEmpty() && held.getItem() == ModItems.bobmazon)
            this.offer = BobmazonOfferFactory.standard.indexOf(offer);
        if (!held.isEmpty() && held.getItem() == ModItems.bobmazon_hidden)
            this.offer = BobmazonOfferFactory.special.indexOf(offer);
    }

    public static ItemBobmazonPacket decode(FriendlyByteBuf buf) {
        ItemBobmazonPacket packet = new ItemBobmazonPacket();
        packet.offer = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(offer);
    }

    public static void handle(ItemBobmazonPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;
        Level world = player.level;
        ctx.get().enqueueWork(() -> {
            Offer offerObj = null;
            ItemStack held = player.getMainHandItem();
            if (!held.isEmpty() && held.getItem() == ModItems.bobmazon)
                offerObj = BobmazonOfferFactory.standard.get(msg.offer);
            if (!held.isEmpty() && held.getItem() == ModItems.bobmazon_hidden)
                offerObj = BobmazonOfferFactory.special.get(msg.offer);
            if (offerObj == null) {
                player.sendSystemMessage(Component.literal("[BOBMAZON] There appears to be a mismatch between the offer you have requested and the offers that exist."));
                player.sendSystemMessage(Component.literal("[BOBMAZON] Engaging fail-safe..."));
                player.hurt(ModDamageSource.nuclearBlast, 1000.0F);
                Vec3 currentMotion = player.getDeltaMovement();
                player.setDeltaMovement(currentMotion.x, 2.0D, currentMotion.z);
                return;
            }
            ItemStack stack = offerObj.offer;
            // Check achievement requirement.
            // (Assumes AchievementHandler.hasAchievementUnlocked(player, achievement) exists.)
            if (offerObj.requirement.achievement != null &&
                    (AchievementHandler.hasAchievementUnlocked(player, offerObj.requirement.achievement) || player.isCreative())) {
                if (countCaps(player) >= offerObj.cost || player.isCreative()) {
                    payCaps(player, offerObj.cost);
                    player.containerMenu.broadcastChanges();
                    Random rand = world.getRandom();
                    EntityBobmazon bob = new EntityBobmazon(world);
                    bob.setPos(player.getX() + rand.nextGaussian() * 10, 300, player.getZ() + rand.nextGaussian() * 10);
                    bob.payload = stack.copy();
                    world.addFreshEntity(bob);
                } else {
                    player.sendSystemMessage(Component.literal("[BOBMAZON] Not enough caps!"));
                }
            } else {
                player.sendSystemMessage(Component.literal("[BOBMAZON] Achievement requirement not met!"));
            }
        });
        ctx.get().setPacketHandled(true);
    }

    private static int countCaps(ServerPlayer player) {
        int count = 0;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty()) {
                Item item = stack.getItem();
                if (item == ModItems.cap_fritz ||
                        item == ModItems.cap_korl ||
                        item == ModItems.cap_nuka ||
                        item == ModItems.cap_quantum ||
                        item == ModItems.cap_rad ||
                        item == ModItems.cap_sparkle)
                    count += stack.getCount();
            }
        }
        return count;
    }

    private static void payCaps(ServerPlayer player, int price) {
        if (price == 0)
            return;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty()) {
                Item item = stack.getItem();
                if (item == ModItems.cap_fritz ||
                        item == ModItems.cap_korl ||
                        item == ModItems.cap_nuka ||
                        item == ModItems.cap_quantum ||
                        item == ModItems.cap_rad ||
                        item == ModItems.cap_sparkle) {
                    int stackCount = stack.getCount();
                    while (stackCount > 0 && price > 0) {
                        stack.shrink(1);
                        price--;
                        stackCount--;
                        if (stack.isEmpty()) {
                            player.getInventory().setItem(i, ItemStack.EMPTY);
                        }
                    }
                    if (price <= 0)
                        return;
                }
            }
        }
    }
}
