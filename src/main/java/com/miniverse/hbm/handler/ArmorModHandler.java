package com.miniverse.hbm.handler;

import com.miniverse.hbm.items.armor.ItemArmorMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.UUID;

public class ArmorModHandler {

    public static final int helmet_only = 0;
    public static final int plate_only = 1;
    public static final int legs_only = 2;
    public static final int boots_only = 3;
    public static final int servos = 4;
    public static final int cladding = 5;
    public static final int kevlar = 6;
    public static final int extra = 7;
    public static final int battery = 8;

    public static final int MOD_SLOTS = 9;

    public static final UUID[] UUIDs = new UUID[]{
            UUID.fromString("8d6e5c77-133e-4056-9c80-a9e42a1a0b65"),
            UUID.fromString("b1b7ee0e-1d14-4400-8037-f7f2e02f21ca"),
            UUID.fromString("30b50d2a-4858-4e5b-88d4-3e3612224238"),
            UUID.fromString("426ee0d0-7587-4697-aaef-4772ab202e78")
    };

    public static final UUID[] fixedUUIDs = new UUID[]{
            UUID.fromString("e572caf4-3e65-4152-bc79-c4d4048cbd29"),
            UUID.fromString("bed30902-8a6a-4769-9f65-2a9b67469fff"),
            UUID.fromString("baebf7b3-1eda-4a14-b233-068e2493e9a2"),
            UUID.fromString("28016c1b-d992-4324-9409-a9f9f0ffb85c")
    };

    // The key for the CompoundTag that holds the armor mods
    public static final String MOD_COMPOUND_KEY = "ntm_armor_mods";
    // The key for the specific slot inside the armor mod CompoundTag
    public static final String MOD_SLOT_KEY = "mod_slot_";

    /**
     * Checks if a mod can be applied to an armor piece.
     * Prevents inserting invalid items into the armor table.
     *
     * @param armor the armor item stack
     * @param mod   the mod item stack
     * @return true if applicable, false otherwise
     */
    public static boolean isApplicable(ItemStack armor, ItemStack mod) {
        if (armor == null || mod == null)
            return false;

        // In 1.20.1, armor items are usually instances of ArmorItem.
        if (!(armor.getItem() instanceof ArmorItem))
            return false;

        if (!(mod.getItem() instanceof ItemArmorMod))
            return false;

        ArmorItem armorItem = (ArmorItem) armor.getItem();
        EquipmentSlot slot = armorItem.getSlot();
        ItemArmorMod aMod = (ItemArmorMod) mod.getItem();

        return (slot == EquipmentSlot.HEAD && aMod.helmet) ||
                (slot == EquipmentSlot.CHEST && aMod.chestplate) ||
                (slot == EquipmentSlot.LEGS && aMod.leggings) ||
                (slot == EquipmentSlot.FEET && aMod.boots);
    }

    /**
     * Applies a mod to the given armor piece.
     * Ensure that you check for applicability before calling this method.
     * This method will override any present mods so it should only be used on unmodded armor.
     *
     * @param armor the armor item stack
     * @param mod   the mod item stack to apply
     */
    public static void applyMod(ItemStack armor, ItemStack mod) {
        // Use getOrCreateTag to ensure the stack has an NBT CompoundTag.
        CompoundTag nbt = armor.getOrCreateTag();

        if (!nbt.contains(MOD_COMPOUND_KEY))
            nbt.put(MOD_COMPOUND_KEY, new CompoundTag());

        CompoundTag mods = nbt.getCompound(MOD_COMPOUND_KEY);

        ItemArmorMod aMod = (ItemArmorMod) mod.getItem();
        int slot = aMod.type;

        CompoundTag cmp = mod.save(new CompoundTag());
        mods.put(MOD_SLOT_KEY + slot, cmp);
    }

    /**
     * Removes the mod from the given slot.
     *
     * @param armor the armor item stack
     * @param slot  the slot index to remove the mod from
     */
    public static void removeMod(ItemStack armor, int slot) {
        if (armor == null)
            return;

        CompoundTag nbt = armor.getOrCreateTag();

        if (!nbt.contains(MOD_COMPOUND_KEY))
            nbt.put(MOD_COMPOUND_KEY, new CompoundTag());

        CompoundTag mods = nbt.getCompound(MOD_COMPOUND_KEY);
        mods.remove(MOD_SLOT_KEY + slot);

        if (mods.isEmpty())
            clearMods(armor);
    }

    /**
     * Removes ALL mods from the armor.
     * This is used when the armor piece is placed in the armor table AFTER the armor pieces have been separated.
     *
     * @param armor the armor item stack
     */
    public static void clearMods(ItemStack armor) {
        if (!armor.hasTag())
            return;

        CompoundTag nbt = armor.getTag();
        nbt.remove(MOD_COMPOUND_KEY);
    }

    /**
     * Checks if the armor item has any mods stored.
     *
     * @param armor the armor item stack
     * @return true if mods exist, false otherwise
     */
    public static boolean hasMods(ItemStack armor) {
        if (!armor.hasTag())
            return false;

        CompoundTag nbt = armor.getTag();
        return nbt.contains(MOD_COMPOUND_KEY);
    }

    /**
     * Retrieves all the modifications from the provided armor.
     *
     * @param armor the armor item stack
     * @return an array of ItemStacks corresponding to the mod slots (empty entries for no mod)
     */
    public static ItemStack[] pryMods(ItemStack armor) {
        ItemStack[] slots = new ItemStack[MOD_SLOTS];

        if (!hasMods(armor))
            return slots;

        CompoundTag nbt = armor.getTag();
        CompoundTag mods = nbt.getCompound(MOD_COMPOUND_KEY);

        for (int i = 0; i < MOD_SLOTS; i++) {
            CompoundTag cmp = mods.getCompound(MOD_SLOT_KEY + i);
            ItemStack stack = ItemStack.of(cmp);
            if (!stack.isEmpty())
                slots[i] = stack;
            else
                removeMod(armor, i);
        }

        return slots;
    }

    /**
     * Retrieves the mod from a specific slot in the armor.
     *
     * @param armor the armor item stack
     * @param slot  the mod slot index
     * @return the mod ItemStack if present, or an empty ItemStack if not
     */
    public static ItemStack pryMod(ItemStack armor, int slot) {
        if (!hasMods(armor))
            return ItemStack.EMPTY;

        CompoundTag nbt = armor.getTag();
        CompoundTag mods = nbt.getCompound(MOD_COMPOUND_KEY);
        CompoundTag cmp = mods.getCompound(MOD_SLOT_KEY + slot);
        ItemStack stack = ItemStack.of(cmp);

        if (!stack.isEmpty())
            return stack;

        removeMod(armor, slot);
        return ItemStack.EMPTY;
    }
}
