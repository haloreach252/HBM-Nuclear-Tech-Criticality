package com.miniverse.hbm.interfaces;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;

public interface IItemHUD {

    /**
     * Renders a custom HUD for an item.
     *
     * @param event  The RenderGuiOverlayEvent.
     * @param player The player holding the item.
     * @param stack  The item stack being rendered.
     */
    void renderHUD(RenderGuiOverlayEvent event, Player player, ItemStack stack);
}
