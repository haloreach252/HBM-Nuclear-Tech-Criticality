package com.miniverse.hbm.packet.toclient;

import com.miniverse.hbm.items.weapon.ItemGunBase;
import com.miniverse.hbm.items.weapon.sedna.GunConfig;
import com.miniverse.hbm.items.weapon.sedna.ItemGunBaseNT;
import com.miniverse.hbm.items.weapon.sedna.Receiver;
import com.miniverse.hbm.items.weapon.sedna.ItemGunBaseNT.LambdaContext;
import com.miniverse.hbm.render.anim.BusAnimation;
import com.miniverse.hbm.render.anim.HbmAnimations;
import com.miniverse.hbm.render.anim.HbmAnimations.AnimType;
import com.miniverse.hbm.render.anim.HbmAnimations.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class GunAnimationPacket {

    private short type;
    private int receiverIndex;
    private int gunIndex;

    public GunAnimationPacket() { }

    public GunAnimationPacket(int type) {
        this.type = (short) type;
        this.receiverIndex = 0;
        this.gunIndex = 0;
    }

    public GunAnimationPacket(int type, int rec) {
        this.type = (short) type;
        this.receiverIndex = rec;
        this.gunIndex = 0;
    }

    public GunAnimationPacket(int type, int rec, int gun) {
        this.type = (short) type;
        this.receiverIndex = rec;
        this.gunIndex = gun;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeShort(this.type);
        buf.writeInt(this.receiverIndex);
        buf.writeInt(this.gunIndex);
    }

    public void fromBytes(FriendlyByteBuf buf) {
        this.type = buf.readShort();
        this.receiverIndex = buf.readInt();
        this.gunIndex = buf.readInt();
    }

    public static class Handler {
        public static void handle(final GunAnimationPacket message, final Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                try {
                    Player player = Minecraft.getInstance().player;
                    if (player == null)
                        return;

                    // In modern Minecraft, the held item in main hand is accessed via getMainHandItem()
                    ItemStack stack = player.getMainHandItem();
                    // Alternatively, if you need the hotbar slot index, use:
                    int slot = player.getInventory().selected;

                    if (stack.isEmpty())
                        return;

                    // Handle Sedna guns first.
                    if (stack.getItem() instanceof ItemGunBaseNT) {
                        handleSedna(player, stack, slot, AnimType.values()[message.type], message.receiverIndex, message.gunIndex);
                        return;
                    }

                    // Fallback: only handle if the item is an instance of ItemGunBase.
                    if (!(stack.getItem() instanceof ItemGunBase))
                        return;

                    if (message.type < 0 || message.type >= AnimType.values().length)
                        return;

                    AnimType animType = AnimType.values()[message.type];
                    ItemGunBase base = (ItemGunBase) stack.getItem();
                    BusAnimation animation = base.getAnimation(stack, animType);

                    // Fallback to regular reload if no empty reload animation
                    if (animation == null && animType == AnimType.RELOAD_EMPTY) {
                        animation = base.getAnimation(stack, AnimType.RELOAD);
                    }

                    // Fallback to regular cycle if no ALT_CYCLE or CYCLE_EMPTY exists
                    if (animation == null && (animType == AnimType.ALT_CYCLE || animType == AnimType.CYCLE_EMPTY)) {
                        animation = base.getAnimation(stack, AnimType.CYCLE);
                    }

                    if (animation != null) {
                        boolean isReloadAnimation = animType == AnimType.RELOAD || animType == AnimType.RELOAD_CYCLE || animType == AnimType.RELOAD_EMPTY;
                        // Use getDescriptionId() instead of getUnlocalizedName()
                        HbmAnimations.hotbar[slot][0] = new Animation(stack.getItem().getDescriptionId(), System.currentTimeMillis(), animation, isReloadAnimation && base.mainConfig.reloadAnimationsSequential);
                    }

                } catch (Exception ignored) { }
            });
            ctx.get().setPacketHandled(true);
        }

        public static void handleSedna(Player player, ItemStack stack, int slot, AnimType type, int receiverIndex, int gunIndex) {
            ItemGunBaseNT gun = (ItemGunBaseNT) stack.getItem();
            GunConfig config = gun.getConfig(stack, gunIndex);

            if (type == AnimType.CYCLE) {
                if (gunIndex < gun.lastShot.length) {
                    gun.lastShot[gunIndex] = System.currentTimeMillis();
                }
                gun.shotRand = player.level.random.nextDouble();

                Receiver[] receivers = config.getReceivers(stack);
                if (receiverIndex >= 0 && receiverIndex < receivers.length) {
                    Receiver rec = receivers[receiverIndex];
                    BiConsumer<ItemStack, LambdaContext> onRecoil = rec.getRecoil(stack);
                    if (onRecoil != null)
                        onRecoil.accept(stack, new LambdaContext(config, player, player.getInventory(), receiverIndex));
                }
            }

            BiFunction<ItemStack, AnimType, BusAnimation> anims = config.getAnims(stack);
            BusAnimation animation = anims.apply(stack, type);

            if (animation == null && type == AnimType.RELOAD_EMPTY) {
                animation = anims.apply(stack, AnimType.RELOAD);
            }
            if (animation == null && (type == AnimType.ALT_CYCLE || type == AnimType.CYCLE_EMPTY)) {
                animation = anims.apply(stack, AnimType.CYCLE);
            }

            if (animation != null) {
                // Reset equipped progress and update the item to render.
                Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer().resetEquipProgress();
                Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer().itemStackInHand = stack;
                boolean isReloadAnimation = type == AnimType.RELOAD || type == AnimType.RELOAD_CYCLE || type == AnimType.RELOAD_EMPTY;
                HbmAnimations.hotbar[slot][gunIndex] = new Animation(stack.getItem().getDescriptionId(), System.currentTimeMillis(), animation, isReloadAnimation && config.getReloadAnimSequential(stack));
            }
        }
    }
}
