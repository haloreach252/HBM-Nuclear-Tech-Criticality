package com.miniverse.hbm.util;

import com.miniverse.hbm.tileentity.machine.rbmk.RBMKDials;
import net.minecraft.util.Mth;
import net.minecraft.world.GameRules;
import net.minecraft.world.level.Level;

public class GameRuleHelper {

    public static double getClampedDouble(Level world, RBMKDials.RBMKKeys rule, double min, double max) {
        String ruleValue = world.getGameRules().getString(rule.keyString);
        double d = parseDouble(world, ruleValue, rule.defValue);
        return Mth.clamp(d, min, max);
    }

    public static int getClampedInt(Level world, RBMKDials.RBMKKeys rule, int min, int max) {
        String ruleValue = world.getGameRules().getString(rule.keyString);
        int i = parseInt(world, ruleValue, rule.defValue);
        return Mth.clamp(i, min, max);
    }

    public static double getDoubleMinimum(Level world, RBMKDials.RBMKKeys rule, double min) {
        String ruleValue = world.getGameRules().getString(rule.keyString);
        double d = parseDouble(world, ruleValue, rule.defValue);
        return Math.max(d, min);
    }

    public static int getIntegerMinimum(Level world, RBMKDials.RBMKKeys rule, int min) {
        String ruleValue = world.getGameRules().getString(rule.keyString);
        int i = parseInt(world, ruleValue, rule.defValue);
        return Math.max(i, min);
    }

    public static double parseDouble(Level world, String s, double def) {
        if (s.isEmpty()) {
            return def;
        }
        try {
            return Double.parseDouble(s);
        } catch (Exception ex) {
            return def;
        }
    }

    public static int parseInt(Level world, String s, int def) {
        if (s.isEmpty()) {
            return def;
        }
        try {
            return Integer.parseInt(s);
        } catch (Exception ex) {
            return def;
        }
    }
}
