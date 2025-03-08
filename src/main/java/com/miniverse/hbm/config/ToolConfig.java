package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ToolConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Tool Settings
    public static final ForgeConfigSpec.IntValue recursionDepth;
    public static final ForgeConfigSpec.BooleanValue recursiveStone;
    public static final ForgeConfigSpec.BooleanValue recursiveNetherrack;

    // Tool Abilities
    public static final ForgeConfigSpec.BooleanValue abilityHammer;
    public static final ForgeConfigSpec.BooleanValue abilityVein;
    public static final ForgeConfigSpec.BooleanValue abilityLuck;
    public static final ForgeConfigSpec.BooleanValue abilitySilk;
    public static final ForgeConfigSpec.BooleanValue abilityFurnace;
    public static final ForgeConfigSpec.BooleanValue abilityShredder;
    public static final ForgeConfigSpec.BooleanValue abilityCentrifuge;
    public static final ForgeConfigSpec.BooleanValue abilityCrystallizer;
    public static final ForgeConfigSpec.BooleanValue abilityMercury;
    public static final ForgeConfigSpec.BooleanValue abilityExplosion;

    static {
        BUILDER.comment("Tool Settings").push("tools");

        recursionDepth = BUILDER
                .comment("Limits veinminer's recursive function.",
                        "Higher values may cause performance issues, especially on servers.")
                .defineInRange("recursion_depth", 500, 1, 5000);

        recursiveStone = BUILDER
                .comment("Determines whether veinminer can break stone.")
                .define("recursive_stone", true);

        recursiveNetherrack = BUILDER
                .comment("Determines whether veinminer can break netherrack.")
                .define("recursive_netherrack", true);

        BUILDER.comment("Tool Abilities").push("abilities");

        abilityHammer = BUILDER.define("ability_hammer", true);
        abilityVein = BUILDER.define("ability_vein", true);
        abilityLuck = BUILDER.define("ability_luck", true);
        abilitySilk = BUILDER.define("ability_silk", true);
        abilityFurnace = BUILDER.define("ability_furnace", true);
        abilityShredder = BUILDER.define("ability_shredder", true);
        abilityCentrifuge = BUILDER.define("ability_centrifuge", true);
        abilityCrystallizer = BUILDER.define("ability_crystallizer", true);
        abilityMercury = BUILDER.define("ability_mercury", true);
        abilityExplosion = BUILDER.define("ability_explosion", true);

        BUILDER.pop(2); // Exits both "abilities" and "tools"
        SPEC = BUILDER.build();
    }
}
