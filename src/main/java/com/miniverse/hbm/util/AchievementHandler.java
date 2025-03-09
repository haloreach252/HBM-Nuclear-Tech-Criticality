package com.miniverse.hbm.util;

import com.miniverse.hbm.blocks.ModBlocks;
import com.miniverse.hbm.inventory.RecipesCommon.ComparableStack;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import net.minecraft.advancements.Advancement;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public class AchievementHandler {

    // Map from a ComparableStack (crafted item) to a custom achievement (advancement)
    public static HashMap<ComparableStack, Advancement> craftingAchievements = new HashMap<>();

    public static void register() {
        craftingAchievements.put(new ComparableStack(ModItems.piston_selenium), HBMNuclearTechCriticality.achSelenium);
        craftingAchievements.put(new ComparableStack(ModItems.gun_b92), HBMNuclearTechCriticality.achSelenium);
        craftingAchievements.put(new ComparableStack(ModItems.battery_potatos), HBMNuclearTechCriticality.achPotato);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_press), HBMNuclearTechCriticality.achBurnerPress);
        craftingAchievements.put(new ComparableStack(ModItems.rbmk_fuel_empty), HBMNuclearTechCriticality.achRBMK);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_chemplant), HBMNuclearTechCriticality.achChemplant);
        craftingAchievements.put(new ComparableStack(ModBlocks.concrete_smooth), HBMNuclearTechCriticality.achConcrete);
        craftingAchievements.put(new ComparableStack(ModBlocks.concrete_asbestos), HBMNuclearTechCriticality.achConcrete);
        craftingAchievements.put(new ComparableStack(ModItems.ingot_polymer), HBMNuclearTechCriticality.achPolymer);
        craftingAchievements.put(new ComparableStack(ModItems.ingot_desh), HBMNuclearTechCriticality.achDesh);
        craftingAchievements.put(new ComparableStack(ModItems.gem_tantalium), HBMNuclearTechCriticality.achTantalum);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_gascent), HBMNuclearTechCriticality.achGasCent);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_centrifuge), HBMNuclearTechCriticality.achCentrifuge);
        craftingAchievements.put(new ComparableStack(ModItems.ingot_schrabidium), HBMNuclearTechCriticality.achSchrab);
        craftingAchievements.put(new ComparableStack(ModItems.nugget_schrabidium), HBMNuclearTechCriticality.achSchrab);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_crystallizer), HBMNuclearTechCriticality.achAcidizer);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_silex), HBMNuclearTechCriticality.achSILEX);
        craftingAchievements.put(new ComparableStack(ModItems.nugget_technetium), HBMNuclearTechCriticality.achTechnetium);
        craftingAchievements.put(new ComparableStack(ModBlocks.struct_watz_core), HBMNuclearTechCriticality.achWatz);
        craftingAchievements.put(new ComparableStack(ModItems.nugget_bismuth), HBMNuclearTechCriticality.achBismuth);
        craftingAchievements.put(new ComparableStack(ModItems.nugget_am241), HBMNuclearTechCriticality.achBreeding);
        craftingAchievements.put(new ComparableStack(ModItems.nugget_am242), HBMNuclearTechCriticality.achBreeding);
        craftingAchievements.put(new ComparableStack(ModItems.missile_nuclear), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.missile_nuclear_cluster), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.missile_doomsday), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.mp_warhead_10_nuclear), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.mp_warhead_10_nuclear_large), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.mp_warhead_15_nuclear), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.mp_warhead_15_nuclear_shark), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModItems.mp_warhead_15_boxcar), HBMNuclearTechCriticality.achRedBalloons);
        craftingAchievements.put(new ComparableStack(ModBlocks.struct_iter_core), HBMNuclearTechCriticality.achFusion);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_difurnace_off), HBMNuclearTechCriticality.achBlastFurnace);
        craftingAchievements.put(new ComparableStack(ModBlocks.machine_assembler), HBMNuclearTechCriticality.achAssembly);
        craftingAchievements.put(new ComparableStack(ModItems.billet_pu_mix), HBMNuclearTechCriticality.achChicagoPile);
    }

    /**
     * When a player crafts an item, fire() is called to check if it matches any registered achievement.
     * Note that in modern Minecraft achievements have been replaced by advancements.
     */
    public static void fire(Player player, ItemStack stack) {
        // Only proceed on the server side.
        if (player.level.isClientSide())
            return;
        ComparableStack comp = new ComparableStack(stack).makeSingular();
        Advancement achievement = craftingAchievements.get(comp);
        if (achievement != null) {
            grantAchievement(player, achievement);
        }
    }

    /**
     * Stub for granting an advancement to a player.
     * Replace this method with your mod's custom advancement-granting code.
     */
    private static void grantAchievement(Player player, Advancement achievement) {
        if (player instanceof ServerPlayer serverPlayer) {
            // TODO: Implement proper advancement criteria triggers or direct awarding.
            // For now, we log that the advancement would be granted.
            HBMNuclearTechCriticality.LOGGER.info("Granting advancement {} to player {}", achievement.getId(), serverPlayer.getScoreboardName());
            // Example (pseudo-code):
            // serverPlayer.getAdvancements().grant(achievement, "custom_trigger");
        }
    }
}
