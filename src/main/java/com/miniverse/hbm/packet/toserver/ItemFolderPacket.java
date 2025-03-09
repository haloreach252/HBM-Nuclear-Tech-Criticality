package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.inventory.RecipesCommon.ComparableStack;
import com.miniverse.hbm.inventory.recipes.AssemblerRecipes;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.items.machine.ItemAssemblyTemplate;
import com.miniverse.hbm.items.machine.ItemCassette;
import com.miniverse.hbm.items.machine.ItemChemistryTemplate;
import com.miniverse.hbm.items.machine.ItemCrucibleTemplate;
import com.miniverse.hbm.items.machine.ItemFluidIdentifier;
import com.miniverse.hbm.util.InventoryUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemFolderPacket {

    private String itemId;
    private int meta;

    public ItemFolderPacket() { }

    // When constructing from an ItemStack, store the item's registry name as a String.
    public ItemFolderPacket(ItemStack stack) {
        if (!stack.isEmpty()) {
            this.itemId = stack.getItem().getRegistryName().toString();
            this.meta = stack.getDamageValue(); // formerly getItemDamage()
        }
    }

    public static ItemFolderPacket decode(FriendlyByteBuf buf) {
        ItemFolderPacket packet = new ItemFolderPacket();
        packet.itemId = buf.readUtf(32767);
        packet.meta = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(itemId);
        buf.writeInt(meta);
    }

    public static class Handler {
        public static void handle(ItemFolderPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayer player = ctx.get().getSender();
            if (player == null)
                return;
            ctx.get().enqueueWork(() -> {
                // Reconstruct the ItemStack using the stored registry name and meta.
                ResourceLocation loc = new ResourceLocation(msg.itemId);
                Item item = ForgeRegistries.ITEMS.getValue(loc);
                if (item == null)
                    return;
                ItemStack stack = new ItemStack(item, 1, msg.meta);

                // Creative mode: simply give the item to the player.
                if (player.isCreative()) {
                    if (stack.getItem() == ModItems.assembly_template) {
                        ComparableStack out = AssemblerRecipes.recipeList.get(stack.getDamageValue());
                        if (out != null) {
                            stack.setDamageValue(0);
                            ItemAssemblyTemplate.writeType(stack, out);
                        }
                    }
                    if (!player.getInventory().add(stack))
                        player.drop(stack, false);
                    return;
                }

                // Process different item types using helper methods.
                if (stack.getItem() instanceof ItemFluidIdentifier) {
                    tryMakeItem(player, stack, "plateIron", "dye");
                    return;
                }
                if (stack.getItem() instanceof ItemAssemblyTemplate) {
                    tryMakeItem(player, stack, net.minecraft.world.item.Items.PAPER, "dye");
                    return;
                }
                if (stack.getItem() instanceof ItemChemistryTemplate) {
                    tryMakeItem(player, stack, net.minecraft.world.item.Items.PAPER, "dye");
                    return;
                }
                if (stack.getItem() instanceof ItemCrucibleTemplate) {
                    tryMakeItem(player, stack, net.minecraft.world.item.Items.PAPER, "dye");
                    return;
                }
                if (stack.getItem() instanceof ItemCassette) {
                    tryMakeItem(player, stack, ModItems.plate_polymer, "plateSteel");
                    return;
                }
                if (stack.getItem() == ModItems.stamp_stone_plate ||
                        stack.getItem() == ModItems.stamp_stone_wire ||
                        stack.getItem() == ModItems.stamp_stone_circuit) {
                    tryConvert(player, ModItems.stamp_stone_flat, stack.getItem());
                    return;
                }
                if (stack.getItem() == ModItems.stamp_iron_plate ||
                        stack.getItem() == ModItems.stamp_iron_wire ||
                        stack.getItem() == ModItems.stamp_iron_circuit) {
                    tryConvert(player, ModItems.stamp_iron_flat, stack.getItem());
                    return;
                }
                if (stack.getItem() == ModItems.stamp_steel_plate ||
                        stack.getItem() == ModItems.stamp_steel_wire ||
                        stack.getItem() == ModItems.stamp_steel_circuit) {
                    tryConvert(player, ModItems.stamp_steel_flat, stack.getItem());
                    return;
                }
                if (stack.getItem() == ModItems.stamp_titanium_plate ||
                        stack.getItem() == ModItems.stamp_titanium_wire ||
                        stack.getItem() == ModItems.stamp_titanium_circuit) {
                    tryConvert(player, ModItems.stamp_titanium_flat, stack.getItem());
                    return;
                }
                if (stack.getItem() == ModItems.stamp_obsidian_plate ||
                        stack.getItem() == ModItems.stamp_obsidian_wire ||
                        stack.getItem() == ModItems.stamp_obsidian_circuit) {
                    tryConvert(player, ModItems.stamp_obsidian_flat, stack.getItem());
                    return;
                }
                if (stack.getItem() == ModItems.stamp_desh_plate ||
                        stack.getItem() == ModItems.stamp_desh_wire ||
                        stack.getItem() == ModItems.stamp_desh_circuit) {
                    tryConvert(player, ModItems.stamp_desh_flat, stack.getItem());
                    return;
                }
            });
            ctx.get().setPacketHandled(true);
        }

        private static void tryMakeItem(ServerPlayer player, ItemStack output, Object... ingredients) {
            // Check for required ingredients.
            for (Object o : ingredients) {
                if (o instanceof Item) {
                    if (!InventoryUtil.hasItem(player, (Item) o))
                        return;
                }
                if (o instanceof String) {
                    if (!InventoryUtil.hasOreDictMatches(player, (String) o, 1))
                        return;
                }
            }
            // Consume ingredients.
            for (Object o : ingredients) {
                if (o instanceof Item) {
                    InventoryUtil.consumeItem(player, (Item) o);
                }
                if (o instanceof String) {
                    InventoryUtil.consumeOreDictMatches(player, (String) o, 1);
                }
            }
            if (output.getItem() == ModItems.assembly_template) {
                ComparableStack out = AssemblerRecipes.recipeList.get(output.getDamageValue());
                if (out != null) {
                    output.setDamageValue(0);
                    ItemAssemblyTemplate.writeType(output, out);
                }
            }
            if (!player.getInventory().add(output))
                player.drop(output, false);
        }

        private static void tryConvert(ServerPlayer player, Item target, Item result) {
            // Iterate over the player's main inventory.
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (!stack.isEmpty() && stack.getItem() == target) {
                    // Replace the item while preserving count and damage.
                    player.getInventory().setItem(i, new ItemStack(result, stack.getCount(), stack.getDamageValue()));
                    return;
                }
            }
        }
    }
}
