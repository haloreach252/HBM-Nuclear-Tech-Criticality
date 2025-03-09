package com.miniverse.hbm.util;

import com.miniverse.hbm.handler.ArmorModHandler;
import com.miniverse.hbm.handler.HazmatRegistry;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.lib.Library;
import com.miniverse.hbm.potion.HbmPotion;
import com.miniverse.hbm.util.ArmorRegistry.HazardClass;
import com.miniverse.hbm.item.IGasMask;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArmorUtil {

    /*
     * The less horrifying part
     */

    public static void register() {
        ArmorRegistry.registerHazard(ModItems.gas_mask_filter, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.GAS_BLISTERING, HazardClass.BACTERIA);
        ArmorRegistry.registerHazard(ModItems.gas_mask_filter_mono, HazardClass.PARTICLE_COARSE, HazardClass.GAS_MONOXIDE);
        ArmorRegistry.registerHazard(ModItems.gas_mask_filter_combo, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.GAS_BLISTERING, HazardClass.BACTERIA, HazardClass.GAS_MONOXIDE);
        ArmorRegistry.registerHazard(ModItems.gas_mask_filter_rag, HazardClass.PARTICLE_COARSE);
        ArmorRegistry.registerHazard(ModItems.gas_mask_filter_piss, HazardClass.PARTICLE_COARSE, HazardClass.GAS_LUNG);

        ArmorRegistry.registerHazard(ModItems.gas_mask, HazardClass.SAND, HazardClass.LIGHT);
        ArmorRegistry.registerHazard(ModItems.gas_mask_m65, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.mask_rag, HazardClass.PARTICLE_COARSE);
        ArmorRegistry.registerHazard(ModItems.mask_piss, HazardClass.PARTICLE_COARSE, HazardClass.GAS_LUNG);

        ArmorRegistry.registerHazard(ModItems.goggles, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.ashglasses, HazardClass.LIGHT, HazardClass.SAND);

        ArmorRegistry.registerHazard(ModItems.attachment_mask, HazardClass.SAND);

        ArmorRegistry.registerHazard(ModItems.asbestos_helmet, HazardClass.SAND, HazardClass.LIGHT);
        ArmorRegistry.registerHazard(ModItems.hazmat_helmet, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.hazmat_helmet_red, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.hazmat_helmet_grey, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.hazmat_paa_helmet, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.liquidator_helmet, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.t45_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.ajr_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.ajro_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.steamsuit_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.hev_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.fau_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.dns_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.schrabidium_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.euphemium_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.rpa_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.envsuit_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        ArmorRegistry.registerHazard(ModItems.trenchmaster_helmet, HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);

        // Ob ihr wirklich richtig steht, seht ihr wenn das Licht angeht!
        registerIfExists(Compat.MOD_GT6, "gt.armor.hazmat.universal.head", HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        registerIfExists(Compat.MOD_GT6, "gt.armor.hazmat.biochemgas.head", HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
        registerIfExists(Compat.MOD_GT6, "gt.armor.hazmat.radiation.head", HazardClass.PARTICLE_COARSE, HazardClass.PARTICLE_FINE, HazardClass.GAS_LUNG, HazardClass.BACTERIA, HazardClass.GAS_BLISTERING, HazardClass.GAS_MONOXIDE, HazardClass.LIGHT, HazardClass.SAND);
    }

    private static void registerIfExists(String domain, String name, HazardClass... classes) {
        Item item = Compat.tryLoadItem(domain, name);
        if (item != null)
            ArmorRegistry.registerHazard(item, classes);
    }

    // Modernized armor-checking using EquipmentSlot
    public static boolean checkArmor(LivingEntity entity, Item head, Item chest, Item legs, Item feet) {
        return checkArmorPiece(entity, head, EquipmentSlot.HEAD)
                && checkArmorPiece(entity, chest, EquipmentSlot.CHEST)
                && checkArmorPiece(entity, legs, EquipmentSlot.LEGS)
                && checkArmorPiece(entity, feet, EquipmentSlot.FEET);
    }

    public static boolean checkArmorPiece(LivingEntity entity, Item armor, EquipmentSlot slot) {
        ItemStack stack = entity.getItemBySlot(slot);
        return !stack.isEmpty() && stack.getItem() == armor;
    }

    public static void damageSuit(LivingEntity entity, EquipmentSlot slot, int amount) {
        ItemStack armor = entity.getItemBySlot(slot);
        if (armor.isEmpty())
            return;
        // Modern method: hurtAndBreak updates the stack and broadcasts break events.
        armor.hurtAndBreak(amount, entity, (e) -> e.broadcastBreakEvent(slot));
    }

    public static void resetFlightTime(Player player) {
        if (player instanceof ServerPlayer) {
            ServerPlayer sp = (ServerPlayer) player;
            ObfuscationReflectionHelper.setPrivateValue(ServerGamePacketListenerImpl.class, sp.connection, 0, "floatingTickCount", "field_147365_f");
        }
    }

    public static boolean checkForHazmat(LivingEntity entity) {
        if (checkArmor(entity, ModItems.hazmat_helmet, ModItems.hazmat_plate, ModItems.hazmat_legs, ModItems.hazmat_boots)
                || checkArmor(entity, ModItems.hazmat_helmet_red, ModItems.hazmat_plate_red, ModItems.hazmat_legs_red, ModItems.hazmat_boots_red)
                || checkArmor(entity, ModItems.hazmat_helmet_grey, ModItems.hazmat_plate_grey, ModItems.hazmat_legs_grey, ModItems.hazmat_boots_grey)
                || checkArmor(entity, ModItems.t45_helmet, ModItems.t45_plate, ModItems.t45_legs, ModItems.t45_boots)
                || checkArmor(entity, ModItems.schrabidium_helmet, ModItems.schrabidium_plate, ModItems.schrabidium_legs, ModItems.schrabidium_boots)
                || checkForHaz2(entity))
            return true;
        if (entity.hasEffect(HbmPotion.mutation))
            return true;
        return false;
    }

    public static boolean checkForHaz2(LivingEntity entity) {
        if (checkArmor(entity, ModItems.hazmat_paa_helmet, ModItems.hazmat_paa_plate, ModItems.hazmat_paa_legs, ModItems.hazmat_paa_boots)
                || checkArmor(entity, ModItems.liquidator_helmet, ModItems.liquidator_plate, ModItems.liquidator_legs, ModItems.liquidator_boots)
                || checkArmor(entity, ModItems.euphemium_helmet, ModItems.euphemium_plate, ModItems.euphemium_legs, ModItems.euphemium_boots)
                || checkArmor(entity, ModItems.rpa_helmet, ModItems.rpa_plate, ModItems.rpa_legs, ModItems.rpa_boots)
                || checkArmor(entity, ModItems.fau_helmet, ModItems.fau_plate, ModItems.fau_legs, ModItems.fau_boots)
                || checkArmor(entity, ModItems.dns_helmet, ModItems.dns_plate, ModItems.dns_legs, ModItems.dns_boots))
            return true;
        return false;
    }

    public static boolean checkForAsbestos(LivingEntity entity) {
        return checkArmor(entity, ModItems.asbestos_helmet, ModItems.asbestos_plate, ModItems.asbestos_legs, ModItems.asbestos_boots);
    }

    public static boolean checkForDigamma(Player player) {
        if (checkArmor(player, ModItems.fau_helmet, ModItems.fau_plate, ModItems.fau_legs, ModItems.fau_boots))
            return true;
        if (checkArmor(player, ModItems.dns_helmet, ModItems.dns_plate, ModItems.dns_legs, ModItems.dns_boots))
            return true;
        if (player.hasEffect(HbmPotion.stability))
            return true;
        return false;
    }

    public static boolean checkForDigamma2(Player player) {
        if (!checkArmor(player, ModItems.robes_helmet, ModItems.robes_plate, ModItems.robes_legs, ModItems.robes_boots))
            return false;
        if (player.hasEffect(HbmPotion.stability))
            return true;
        // Iterate over standard armor slots (HEAD, CHEST, LEGS, FEET)
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack armor = player.getItemBySlot(slot);
            if (!armor.isEmpty() && ArmorModHandler.hasMods(armor)) {
                ItemStack[] mods = ArmorModHandler.pryMods(armor);
                if (!(mods[ArmorModHandler.cladding] != null && mods[ArmorModHandler.cladding].getItem() == ModItems.cladding_iron))
                    return false;
            }
        }
        return player.getMaxHealth() < 3;
    }

    public static boolean checkForFaraday(Player player) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
        if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty())
            return false;
        return isFaradayArmor(head) && isFaradayArmor(chest) && isFaradayArmor(legs) && isFaradayArmor(feet);
    }

    public static final String[] metals = new String[] {
            "chainmail",
            "iron",
            "silver",
            "gold",
            "platinum",
            "tin",
            "lead",
            "liquidator",
            "schrabidium",
            "euphemium",
            "steel",
            "cmb",
            "titanium",
            "alloy",
            "copper",
            "bronze",
            "electrum",
            "t45",
            "bj",
            "starmetal",
            "hazmat", // also count because rubber is insulating
            "rubber",
            "hev",
            "ajr",
            "rpa",
            "spacesuit"
    };

    public static boolean isFaradayArmor(ItemStack item) {
        String name = item.getDescriptionId();
        for (String metal : metals) {
            if (name.toLowerCase(Locale.US).contains(metal))
                return true;
        }
        if (HazmatRegistry.getCladding(item) > 0)
            return true;
        return false;
    }

    public static boolean checkForFiend(Player player) {
        return checkArmorPiece(player, ModItems.jackt, EquipmentSlot.CHEST)
                && Library.checkForHeld(player, ModItems.shimmer_sledge);
    }

    public static boolean checkForFiend2(Player player) {
        return checkArmorPiece(player, ModItems.jackt2, EquipmentSlot.CHEST)
                && Library.checkForHeld(player, ModItems.shimmer_axe);
    }

    // Default implementations for IGasMask items
    public static final String FILTERK_KEY = "hfrFilter";

    public static void installGasMaskFilter(ItemStack mask, ItemStack filter) {
        if (mask.isEmpty() || filter.isEmpty())
            return;
        if (!mask.hasTag())
            mask.setTag(new CompoundTag());
        CompoundTag attach = new CompoundTag();
        filter.save(attach);
        mask.getTag().put(FILTERK_KEY, attach);
    }

    public static void removeFilter(ItemStack mask) {
        if (mask.isEmpty())
            return;
        if (!mask.hasTag())
            return;
        mask.getTag().remove(FILTERK_KEY);
    }

    /**
     * Grabs the installed filter or the filter of the attachment, used for attachment rendering.
     */
    public static ItemStack getGasMaskFilterRecursively(ItemStack mask, LivingEntity entity) {
        ItemStack filter = getGasMaskFilter(mask);
        if (filter.isEmpty() && ArmorModHandler.hasMods(mask)) {
            ItemStack[] mods = ArmorModHandler.pryMods(mask);
            if (mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
                filter = ((IGasMask) mods[ArmorModHandler.helmet_only].getItem()).getFilter(mods[ArmorModHandler.helmet_only], entity);
        }
        return filter;
    }

    public static ItemStack getGasMaskFilter(ItemStack mask) {
        if (mask.isEmpty())
            return ItemStack.EMPTY;
        if (!mask.hasTag())
            return ItemStack.EMPTY;
        CompoundTag attach = mask.getTag().getCompound(FILTERK_KEY);
        return ItemStack.of(attach);
    }

    public static void damageGasMaskFilter(LivingEntity entity, int damage) {
        ItemStack mask = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (mask.isEmpty())
            return;
        if (!(mask.getItem() instanceof IGasMask)) {
            if (ArmorModHandler.hasMods(mask)) {
                ItemStack[] mods = ArmorModHandler.pryMods(mask);
                if (mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
                    mask = mods[ArmorModHandler.helmet_only];
            }
        }
        if (!mask.isEmpty())
            damageGasMaskFilter(mask, damage);
    }

    public static void damageGasMaskFilter(ItemStack mask, int damage) {
        ItemStack filter = getGasMaskFilter(mask);
        if (filter.isEmpty()) {
            if (ArmorModHandler.hasMods(mask)) {
                ItemStack[] mods = ArmorModHandler.pryMods(mask);
                if (mods[ArmorModHandler.helmet_only] != null && mods[ArmorModHandler.helmet_only].getItem() instanceof IGasMask)
                    filter = getGasMaskFilter(mods[ArmorModHandler.helmet_only]);
            }
        }
        if (filter.isEmpty() || filter.getMaxDamage() == 0)
            return;
        filter.setDamageValue(filter.getDamageValue() + damage);
        if (filter.getDamageValue() > filter.getMaxDamage())
            removeFilter(mask);
        else
            installGasMaskFilter(mask, filter);
    }

    public static void addGasMaskTooltip(ItemStack mask, Player player, List<String> list, boolean ext) {
        if (mask.isEmpty() || !(mask.getItem() instanceof IGasMask))
            return;
        ItemStack filter = ((IGasMask) mask.getItem()).getFilter(mask, player);
        if (filter.isEmpty()) {
            list.add(ChatFormatting.RED + "No filter installed!");
            return;
        }
        list.add(ChatFormatting.GOLD + "Installed filter:");
        int meta = filter.getDamageValue();
        int max = filter.getMaxDamage();
        String append = "";
        if (max > 0) {
            append = " (" + ((max - meta) * 100 / max) + "%)";
        }
        List<String> lore = new ArrayList<>();
        list.add("  " + filter.getHoverName().getString() + append);
        // Gather additional tooltip lines.
        filter.getItem().appendHoverText(filter, null, lore, ext ? net.minecraft.world.item.TooltipFlag.DEFAULT : net.minecraft.world.item.TooltipFlag.ADVANCED);
        ForgeEventFactory.onItemTooltip(filter, player, lore, ext);
        for (String s : lore)
            list.add(ChatFormatting.YELLOW + "  " + s);
    }

    public static boolean isWearingEmptyMask(Player player) {
        ItemStack mask = player.getItemBySlot(EquipmentSlot.HEAD);
        if (mask.isEmpty())
            return false;
        if (mask.getItem() instanceof IGasMask) {
            return getGasMaskFilter(mask).isEmpty();
        }
        ItemStack mod = ArmorModHandler.pryMods(mask)[ArmorModHandler.helmet_only];
        if (mod != null && mod.getItem() instanceof IGasMask) {
            return getGasMaskFilter(mod).isEmpty();
        }
        return false;
    }
}
