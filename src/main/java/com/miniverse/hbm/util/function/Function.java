package com.miniverse.hbm.util.function;

import com.miniverse.hbm.util.BobMathUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Locale;

/**
 * A simple class for defining function types (linear, log, sqrt, etc.) for things like fuel reactivity.
 * Unlike current RBMK functions, constants used in the calculations have been removed.
 *
 * @author hbm
 */
public abstract class Function {

    protected double div = 1D;
    protected double off = 0;

    // The German pronunciation of f(x) - "F von X"
    public abstract double effonix(double x);
    public abstract String getLabelForFuel();
    public abstract Component getDangerFromFuel();

    public Function withDiv(double div) {
        this.div = div;
        return this;
    }

    public Function withOff(double off) {
        this.off = off;
        return this;
    }

    public double getX(double x) {
        return x / div + off;
    }

    public String getXName() {
        return getXName(true);
    }

    public String getXName(boolean brackets) {
        String x = "x";
        boolean mod = false;
        if (div != 1D) x += " / " + String.format(Locale.US, "%,.1f", div);
        if (off != 0D) x += " + " + String.format(Locale.US, "%,.1f", off);
        if (mod && brackets) x = "(" + x + ")";
        return x;
    }

    public static abstract class FunctionSingleArg extends Function {
        protected double level;

        public FunctionSingleArg(double level) {
            this.level = level;
        }
    }

    public static abstract class FunctionDoubleArg extends Function {
        protected double level, vOff;

        public FunctionDoubleArg(double level, double vOff) {
            this.level = level;
            this.vOff = vOff;
        }
    }

    public static class FunctionLogarithmic extends FunctionSingleArg {
        public FunctionLogarithmic(double level) {
            super(level);
            this.withOff(1D);
        }

        @Override
        public double effonix(double x) {
            return Math.log10(getX(x)) * level;
        }

        @Override
        public String getLabelForFuel() {
            return "log10(" + getXName(false) + ") * " + String.format(Locale.US, "%,.1f", this.level);
        }

        @Override
        public Component getDangerFromFuel() {
            return Component.literal("MEDIUM / LOGARITHMIC").withStyle(ChatFormatting.YELLOW);
        }
    }

    public static class FunctionPassive extends FunctionSingleArg {
        public FunctionPassive(double level) {
            super(level);
        }

        @Override
        public double effonix(double x) {
            return this.level;
        }

        @Override
        public String getLabelForFuel() {
            return String.format(Locale.US, "%,.1f", this.level);
        }

        @Override
        public Component getDangerFromFuel() {
            return Component.literal("SAFE / PASSIVE").withStyle(ChatFormatting.DARK_GREEN);
        }
    }

    public static class FunctionSqrt extends FunctionSingleArg {
        public FunctionSqrt(double level) {
            super(level);
        }

        @Override
        public double effonix(double x) {
            return BobMathUtil.squirt(getX(x)) * this.level;
        }

        @Override
        public String getLabelForFuel() {
            return "sqrt(" + getXName(false) + ") * " + String.format(Locale.US, "%,.3f", this.level);
        }

        @Override
        public Component getDangerFromFuel() {
            return Component.literal("MEDIUM / SQUARE ROOT").withStyle(ChatFormatting.YELLOW);
        }
    }

    public static class FunctionSqrtFalling extends FunctionSqrt {
        public FunctionSqrtFalling(double fallFactor) {
            super(1D / fallFactor);
            this.withOff(fallFactor * fallFactor);
        }
    }

    public static class FunctionLinear extends FunctionSingleArg {
        public FunctionLinear(double level) {
            super(level);
        }

        @Override
        public double effonix(double x) {
            return getX(x) * this.level;
        }

        @Override
        public String getLabelForFuel() {
            return getXName(true) + " * " + String.format(Locale.US, "%,.1f", this.level);
        }

        @Override
        public Component getDangerFromFuel() {
            return Component.literal("DANGEROUS / LINEAR").withStyle(ChatFormatting.RED);
        }
    }

    public static class FunctionQuadratic extends FunctionDoubleArg {
        public FunctionQuadratic(double level) {
            super(level, 0D);
        }

        public FunctionQuadratic(double level, double vOff) {
            super(level, vOff);
        }

        @Override
        public double effonix(double x) {
            return getX(x) * getX(x) * this.level + this.vOff;
        }

        @Override
        public String getLabelForFuel() {
            return getXName(true) + "² * " + String.format(Locale.US, "%,.1f", this.level) +
                    (vOff != 0 ? (" + " + String.format(Locale.US, "%,.1f", vOff)) : "");
        }

        @Override
        public Component getDangerFromFuel() {
            return Component.literal("DANGEROUS / QUADRATIC").withStyle(ChatFormatting.RED);
        }
    }
}
