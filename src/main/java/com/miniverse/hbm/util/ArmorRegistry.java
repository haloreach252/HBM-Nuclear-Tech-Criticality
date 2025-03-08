package com.miniverse.hbm.util;

import com.miniverse.hbm.handler.ArmorModHandler;
import com.miniverse.hbm.api.item.IGasMask;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.*;

/**
 * Registry for armor items providing protection against various hazards.
 */
public class ArmorRegistry {

    public static final Map<Item, List<HazardClass>> hazardClasses = new HashMap<>();

    public static void registerHazard(Item item, HazardClass... hazards) {
        hazardClasses.put(item, new ArrayList<>(Arrays.asList(hazards)));
    }

    public static boolean hasAllProtection(LivingEntity entity, EquipmentSlot slot, HazardClass... clazz) {
        if (ArmorUtil.checkArmorNull(entity, slot))
            return false;

        List<HazardClass> list = getProtectionFromItem(entity.getItemBySlot(slot), entity);
        return list.containsAll(Arrays.asList(clazz));
    }

    public static boolean hasAnyProtection(LivingEntity entity, EquipmentSlot slot, HazardClass... clazz) {
        if (ArmorUtil.checkArmorNull(entity, slot))
            return false;

        List<HazardClass> list = getProtectionFromItem(entity.getItemBySlot(slot), entity);

        if (list == null || list.isEmpty())
            return false;

        for (HazardClass haz : clazz) {
            if (list.contains(haz)) return true;
        }
        return false;
    }

    public static boolean hasProtection(LivingEntity entity, EquipmentSlot slot, HazardClass clazz) {
        if (ArmorUtil.checkArmorNull(entity, slot))
            return false;

        List<HazardClass> list = getProtectionFromItem(entity.getItemBySlot(slot), entity);
        return list != null && list.contains(clazz);
    }

    public static List<HazardClass> getProtectionFromItem(ItemStack stack, LivingEntity entity) {
        List<HazardClass> prot = new ArrayList<>();

        if (stack.isEmpty())
            return prot;

        Item item = stack.getItem();

        // If the item has HazardClasses assigned to it, add those
        if (hazardClasses.containsKey(item))
            prot.addAll(hazardClasses.get(item));

        if (item instanceof IGasMask mask) {
            ItemStack filter = mask.getFilter(stack, entity);

            if (!filter.isEmpty()) {
                List<HazardClass> filProt = hazardClasses.getOrDefault(filter.getItem(), new ArrayList<>());

                for (HazardClass c : mask.getBlacklist(stack, entity))
                    filProt.remove(c);

                prot.addAll(filProt);
            }
        }

        if (ArmorModHandler.hasMods(stack)) {
            ItemStack[] mods = ArmorModHandler.pryMods(stack);

            for (ItemStack mod : mods) {
                if (!mod.isEmpty())
                    prot.addAll(getProtectionFromItem(mod, entity));
            }
        }

        return prot;
    }

    public enum HazardClass {
        GAS_LUNG("hazard.gasChlorine"),         // Also attacks eyes -> no half mask
        GAS_MONOXIDE("hazard.gasMonoxide"),     // Only affects lungs
        GAS_INERT("hazard.gasInert"),           // SA
        PARTICLE_COARSE("hazard.particleCoarse"), // Only affects lungs
        PARTICLE_FINE("hazard.particleFine"),   // Only affects lungs
        BACTERIA("hazard.bacteria"),           // No half masks
        GAS_BLISTERING("hazard.corrosive"),     // Corrosive substance, also attacks skin
        SAND("hazard.sand"),                   // Blinding sand particles
        LIGHT("hazard.light");                 // Blinding light

        public final String lang;

        HazardClass(String lang) {
            this.lang = lang;
        }
    }
}
