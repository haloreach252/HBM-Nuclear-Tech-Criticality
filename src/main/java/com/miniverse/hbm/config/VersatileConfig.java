package com.miniverse.hbm.config;

import com.miniverse.hbm.interfaces.INeedsWork;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.potion.HbmPotion;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Collections;

@INeedsWork("This may need to be rewritten to use other configs values in the future. For now, it works.")
public class VersatileConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // General Settings
    public static final ForgeConfigSpec.BooleanValue enableLBSM;
    public static final ForgeConfigSpec.BooleanValue enableLBSMFullSchrab;
    public static final ForgeConfigSpec.IntValue schrabRate;
    public static final ForgeConfigSpec.BooleanValue enable528;

    // Potion Settings
    public static final ForgeConfigSpec.IntValue potionSickness;

    // Machine Settings
    public static final ForgeConfigSpec.BooleanValue doRTGsDecay;
    public static final ForgeConfigSpec.BooleanValue scaleRTGPower;
    public static final ForgeConfigSpec.BooleanValue enableLBSMShorterDecay;

    static {
        BUILDER.comment("General Configurations").push("general");

        enableLBSM = BUILDER
                .comment("Enable LBSM (Limited Balance System Mode)")
                .define("enableLBSM", false);

        enableLBSMFullSchrab = BUILDER
                .comment("Enable Full Schrabidium when LBSM is enabled.")
                .define("enableLBSMFullSchrab", false);

        schrabRate = BUILDER
                .comment("Schrabidium ore spawn chance when LBSM is enabled.")
                .defineInRange("schrabOreChance", 100, 1, Integer.MAX_VALUE);

        enable528 = BUILDER
                .comment("Enable experimental 528 settings.")
                .define("enable528", false);

        BUILDER.pop().comment("Potion Configurations").push("potions");

        potionSickness = BUILDER
                .comment("Potion sickness effect level.",
                        "0 = Disabled",
                        "1 = Normal",
                        "2 = Extended Duration")
                .defineInRange("potionSickness", 1, 0, 2);

        BUILDER.pop().comment("Machine Configurations").push("machines");

        doRTGsDecay = BUILDER
                .comment("Enable RTG (Radioisotope Thermoelectric Generator) decay over time.")
                .define("doRTGsDecay", false);

        scaleRTGPower = BUILDER
                .comment("Enable scaling RTG power.")
                .define("scaleRTGPower", false);

        enableLBSMShorterDecay = BUILDER
                .comment("Enable shorter decay timers when LBSM is enabled.")
                .define("enableLBSMShorterDecay", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    // Returns the transmutator item based on LBSM settings
    public static Item getTransmutatorItem() {
        return (enableLBSM.get() && enableLBSMFullSchrab.get()) ? ModItems.INGOT_SCHRABIDIUM : ModItems.INGOT_SCHRARANIUM;
    }

    // Returns the Schrabidium ore spawn rate
    public static int getSchrabOreChance() {
        return enableLBSM.get() ? schrabRate.get() : 100;
    }

    // Applies potion sickness effect
    public static void applyPotionSickness(LivingEntity entity, int duration) {
        if (potionSickness.get() == 0) return;

        if (potionSickness.get() == 2) duration *= 12;

        MobEffectInstance effect = new MobEffectInstance(HbmPotion.POTION_SICKNESS, duration * 20);
        effect.setCurativeItems(Collections.emptyList()); // No items can cure it
        entity.addEffect(effect);
    }

    // Checks if the entity has potion sickness
    public static boolean hasPotionSickness(LivingEntity entity) {
        return entity.hasEffect(HbmPotion.POTION_SICKNESS);
    }

    // Determines if RTGs should decay
    public static boolean rtgDecay() {
        return enable528.get() || doRTGsDecay.get();
    }

    // Determines if RTG power should scale
    public static boolean scaleRTGPower() {
        return enable528.get() || scaleRTGPower.get();
    }

    private static final int MINUTE = 60 * 20;
    private static final int HOUR = 60 * MINUTE;

    // Determines long decay chance
    public static int getLongDecayChance() {
        return enable528.get() ? 15 * HOUR :
                (enableLBSM.get() && enableLBSMShorterDecay.get()) ? 15 * MINUTE : 3 * HOUR;
    }

    // Determines short decay chance
    public static int getShortDecayChance() {
        return enable528.get() ? 3 * HOUR :
                (enableLBSM.get() && enableLBSMShorterDecay.get()) ? 3 * MINUTE : 15 * MINUTE;
    }
}
