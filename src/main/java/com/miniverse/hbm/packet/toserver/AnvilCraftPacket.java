package com.miniverse.hbm.packet.toserver;

import com.miniverse.hbm.inventory.container.ContainerAnvil;
import com.miniverse.hbm.inventory.recipes.anvil.AnvilRecipes;
import com.miniverse.hbm.inventory.recipes.anvil.AnvilRecipes.AnvilConstructionRecipe;
import com.miniverse.hbm.util.AchievementHandler;
import com.miniverse.hbm.util.InventoryUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AnvilCraftPacket {

    private int recipeIndex;
    private int mode;

    public AnvilCraftPacket() { }

    public AnvilCraftPacket(AnvilConstructionRecipe recipe, int mode) {
        this.recipeIndex = AnvilRecipes.getConstruction().indexOf(recipe);
        this.mode = mode;
    }

    public static AnvilCraftPacket decode(FriendlyByteBuf buf) {
        AnvilCraftPacket packet = new AnvilCraftPacket();
        packet.recipeIndex = buf.readInt();
        packet.mode = buf.readInt();
        return packet;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.recipeIndex);
        buf.writeInt(this.mode);
    }

    public static void handle(AnvilCraftPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player == null)
            return;

        ctx.get().enqueueWork(() -> {
            // Validate recipe index
            if (msg.recipeIndex < 0 || msg.recipeIndex >= AnvilRecipes.getConstruction().size())
                return;

            // Ensure the player is using an anvil container
            if (!(player.containerMenu instanceof ContainerAnvil))
                return;

            ContainerAnvil anvil = (ContainerAnvil) player.containerMenu;
            AnvilConstructionRecipe recipe = AnvilRecipes.getConstruction().get(msg.recipeIndex);

            // Verify the anvil tier is valid for this recipe
            if (!recipe.isTierValid(anvil.tier))
                return;

            int count;
            if (msg.mode == 1) {
                // If there are multiple outputs, assume a stack size of 64;
                // otherwise, calculate based on the maximum stack size vs. current count.
                if (recipe.output.size() > 1)
                    count = 64;
                else
                    count = recipe.output.get(0).stack.getMaxStackSize() / recipe.output.get(0).stack.getCount();
            } else {
                count = 1;
            }

            for (int i = 0; i < count; i++) {
                if (InventoryUtil.doesPlayerHaveAStacks(player, recipe.input, true)) {
                    InventoryUtil.giveChanceStacksToPlayer(player, recipe.output);
                    AchievementHandler.fire(player, recipe.output.get(0).stack);
                } else {
                    break;
                }
            }

            // Sync container changes with the client
            player.containerMenu.broadcastChanges();
        });
        ctx.get().setPacketHandled(true);
    }
}
