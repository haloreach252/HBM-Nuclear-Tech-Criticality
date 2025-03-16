package com.miniverse.hbm.items;

import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemRemap extends Item {
    private final Item remapItem;
    private final ItemStack remapStack;

    public ItemRemap(Item item, int meta, Properties properties) {
        super(properties);
        this.remapItem = item;
        this.remapStack = new ItemStack(item);
        // Store the meta as NBT if needed
        if (meta > 0) {
            this.remapStack.getOrCreateTag().putInt("original_meta", meta);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if(!(entity instanceof Player player)) return;

        // Create a new stack of the target item with the same count
        ItemStack newStack = this.remapStack.copy();
        newStack.setCount(stack.getCount());

        // Copy any additional NBT data if needed
        if (stack.hasTag() && stack.getTag().contains("additional_data")) {
            if (newStack.getTag() == null) {
                newStack.setTag(stack.getTag().copy());
            } else {
                newStack.getTag().merge(stack.getTag());
            }
        }

        player.getInventory().setItem(slot, newStack);
    }

    @OnlyIn(Dist.CLIENT)
    public int getItemColor(ItemStack stack, int tintIndex) {
        return 0xFF8080;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipList, TooltipFlag flag) {
        tooltipList.add(Component.literal("Compatibility item, hold in inventory to convert!").withStyle(ChatFormatting.RED));
    }
}