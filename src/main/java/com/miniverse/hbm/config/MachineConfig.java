package com.miniverse.hbm.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MachineConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue scaleRTGPower;
    public static final ForgeConfigSpec.BooleanValue doRTGsDecay;

    static {
        BUILDER.comment("Machine Settings").push("machines");

        scaleRTGPower = BUILDER.comment("Should RTG/Betavoltaic fuel power scale down as it decays?")
                .define("scaleRTGPower", false);

        doRTGsDecay = BUILDER.comment("Should RTG/Betavoltaic fuel decay at all?")
                .define("doRTGsDecay", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
