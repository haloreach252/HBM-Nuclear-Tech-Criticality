package com.miniverse.hbm.items.armor;

import java.util.List;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.inventory.fluid.FluidType;
import com.miniverse.hbm.util.BobMathUtil;
import com.miniverse.hbm.api.fluid.IFillableItem;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class ArmorFSBFueled extends ArmorFSB implements IFillableItem {
    FluidType fuelType;
    public int maxFuel = 1;
    public int fillRate;
    public int consumption;
    public int drain;

    public ArmorFSBFueled(ArmorMaterial material, int slot, String texture, FluidType fuelType, int maxFuel, int fillRate, int consumption, int drain) {
        super(material, slot, texture);
        this.fuelType = fuelType;
        this.fillRate = fillRate;
        this.consumption = consumption;
        this.drain = drain;
        this.maxFuel = maxFuel;
    }

    @Override
    public int getFill(ItemStack stack) {
        if(stack.getNbt() == null) {
            NbtCompound nbt = new NbtCompound();
            stack.setNbt(nbt);
            setFill(stack, maxFuel);
            return maxFuel;
        }

        return stack.getNbt().getInt("fuel");
    }

    public void setFill(ItemStack stack, int fill) {
        if(stack.getNbt() == null) {
            NbtCompound nbt = new NbtCompound();
            stack.setNbt(nbt);
        }

        stack.getNbt().putInt("fuel", fill);
    }

    public int getMaxFill(ItemStack stack) {
        return this.maxFuel;
    }

    public int getLoadSpeed(ItemStack stack) {
        return this.fillRate;
    }

    public int getUnloadSpeed(ItemStack stack) {
        return 0;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        this.setFill(stack, Math.max(this.getFill(stack) - (damage * consumption), 0));
    }

    @Override
    public boolean isArmorEnabled(ItemStack stack) {
        return getFill(stack) > 0;
    }

    @Override
    public void onArmorTick(World world, PlayerEntity player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if(this.drain > 0 && ArmorFSB.hasFSBArmor(player) && !player.getAbilities().creativeMode && world.getTime() % 10 == 0) {
            this.setFill(stack, Math.max(this.getFill(stack) - this.drain, 0));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of(this.fuelType.getLocalizedName() + ": " + BobMathUtil.getShortNumber(getFill(stack)) + " / " + BobMathUtil.getShortNumber(getMaxFill(stack))));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getFill(stack) < getMaxFill(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0F - (float)(getMaxFill(stack) - getFill(stack)) * 13.0F / (float)getMaxFill(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x00FF00; // Green color for the durability bar
    }

    @Override
    public boolean acceptsFluid(FluidType type, ItemStack stack) {
        return type == this.fuelType;
    }

    @Override
    public int tryFill(FluidType type, int amount, ItemStack stack) {

        if(!acceptsFluid(type, stack))
            return amount;

        int toFill = Math.min(amount, this.fillRate);
        toFill = Math.min(toFill, this.maxFuel - this.getFill(stack));
        this.setFill(stack, this.getFill(stack) + toFill);

        return amount - toFill;
    }

    @Override
    public boolean providesFluid(FluidType type, ItemStack stack) {
        return false;
    }

    @Override
    public int tryEmpty(FluidType type, int amount, ItemStack stack) {
        return 0;
    }

    @Override
    public FluidType getFirstFluidType(ItemStack stack) {
        return null;
    }
}