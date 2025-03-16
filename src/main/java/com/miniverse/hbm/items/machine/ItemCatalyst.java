package com.miniverse.hbm.items.machine;

import java.util.List;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class ItemCatalyst extends Item {
    private final int color;
    private final long powerAbs;
    private final float powerMod;
    private final float heatMod;
    private final float fuelMod;

    public ItemCatalyst(Properties properties, int color) {
        super(properties);
        this.color = color;
        this.powerAbs = 0;
        this.powerMod = 1.0F;
        this.heatMod = 1.0F;
        this.fuelMod = 1.0F;
    }

    public ItemCatalyst(Properties properties, int color, long powerAbs, float powerMod, float heatMod, float fuelMod) {
        super(properties);
        this.color = color;
        this.powerAbs = powerAbs;
        this.powerMod = powerMod;
        this.heatMod = heatMod;
        this.fuelMod = fuelMod;
    }

    public int getColor() {
        return this.color;
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable net.minecraft.world.level.Level level, List<Component> tooltipComponents, net.minecraft.world.item.TooltipFlag isAdvanced) {
		/*tooltipComponents.add(Component.literal("Absolute Energy Bonus: " + powerAbs + "HE"));
		tooltipComponents.add(Component.literal("Energy Modifier:           " + (powerMod >= 1 ? "+" : "") + (Math.round(powerMod * 1000) * .10 - 100) + "%"));
		tooltipComponents.add(Component.literal("Heat Modifier:               " + (heatMod >= 1 ? "+" : "") + (Math.round(heatMod * 1000) * .10 - 100) + "%"));
		tooltipComponents.add(Component.literal("Fuel Modifier:               " + (fuelMod >= 1 ? "+" : "") + (Math.round(fuelMod * 1000) * .10 - 100) + "%"));*/
        //TODO: do something useful with this
        tooltipComponents.add(Component.literal("Adds spice to the core."));
        tooltipComponents.add(Component.literal("Look at all those colors!"));
    }

    public static long getPowerAbs(ItemStack stack) {
        if(stack.isEmpty() || !(stack.getItem() instanceof ItemCatalyst))
            return 0;
        return ((ItemCatalyst)stack.getItem()).powerAbs;
    }

    public static float getPowerMod(ItemStack stack) {
        if(stack.isEmpty() || !(stack.getItem() instanceof ItemCatalyst))
            return 0;
        return ((ItemCatalyst)stack.getItem()).powerMod;
    }

    public static float getHeatMod(ItemStack stack) {
        if(stack.isEmpty() || !(stack.getItem() instanceof ItemCatalyst))
            return 0;
        return ((ItemCatalyst)stack.getItem()).heatMod;
    }

    public static float getFuelMod(ItemStack stack) {
        if(stack.isEmpty() || !(stack.getItem() instanceof ItemCatalyst))
            return 0;
        return ((ItemCatalyst)stack.getItem()).fuelMod;
    }
}