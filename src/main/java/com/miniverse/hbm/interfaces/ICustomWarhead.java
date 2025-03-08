package com.miniverse.hbm.interfaces;

import com.google.common.annotations.Beta;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.hazard.HazardRegistry;
import com.miniverse.hbm.util.BobMathUtil;
import com.miniverse.hbm.util.Either;
import com.miniverse.hbm.util.I18nUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import org.apache.logging.log4j.Level;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Interface for customizable warheads or other explosive devices.
 */
@Beta
public interface ICustomWarhead {

    enum EnumCustomWarhead {
        AMAT, BF, BIO, CHEM, FUSION, GRAV, HE, NUCLEAR, TX, SCHRAB, ZPE;

        public String getLoc() {
            return I18nUtil.resolveKey("warhead.".concat(toString()));
        }

        public enum EnumChemicalType {
            ACID, CHLORINE, NERVE, TOX;

            public String getLoc() {
                return I18nUtil.resolveKey("warhead.CHEM.".concat(toString()));
            }
        }

        public enum EnumBioType {
            ANTHRAX, MKU;

            public String getLoc() {
                return I18nUtil.resolveKey("warhead.BIO.".concat(toString()));
            }
        }
    }

    enum EnumCustomWarheadTrait {
        CLEAN, CLEANISH, DIRTY, RAD, SALT;

        public String getLoc() {
            return I18nUtil.resolveKey("warheadTrait.".concat(toString()));
        }
    }

    enum EnumWeaponType {
        DENIAL, STRATEGIC, TACTICAL, WMD;

        public String getLoc() {
            return I18nUtil.resolveKey("warheadType.".concat(toString()));
        }
    }

    // NBT Keys
    String NBT_GROUP = "NTM_NUKE_INFO";
    String NBT_YIELD = "YIELD";
    String NBT_ALTITUDE = "ALTITUDE";
    String NBT_MASS = "MASS";
    String NBT_SPECIAL = "SPECIAL_FIELD";
    String NBT_WARHEAD = "WARHEAD";
    String NBT_TYPE = "WARHEAD_TYPE";
    String NBT_TRAIT = "WARHEAD_TRAIT";

    DecimalFormat df = new DecimalFormat("#.00");

    static ChatFormatting getColorFromWarhead(EnumCustomWarhead warhead) {
        switch (warhead) {
            case AMAT -> { return ChatFormatting.DARK_RED; }
            case BF -> { return ChatFormatting.GREEN; }
            case BIO -> { return ChatFormatting.GOLD; }
            case CHEM -> { return ChatFormatting.YELLOW; }
            case FUSION -> { return ChatFormatting.BLUE; }
            case GRAV -> { return ChatFormatting.DARK_GRAY; }
            case HE -> { return ChatFormatting.RED; }
            case NUCLEAR -> { return ChatFormatting.DARK_GREEN; }
            case SCHRAB -> { return ChatFormatting.AQUA; }
            case TX -> { return ChatFormatting.DARK_PURPLE; }
            case ZPE -> { return System.currentTimeMillis() % 1000 < 500 ? ChatFormatting.DARK_AQUA : ChatFormatting.LIGHT_PURPLE; }
            default -> { return ChatFormatting.WHITE; }
        }
    }

    default float getYield() {
        return 0.0F;
    }

    default EnumCustomWarhead getWarheadType(CompoundTag data) {
        return EnumCustomWarhead.valueOf(data.getString(NBT_WARHEAD));
    }

    default EnumWeaponType getWeaponType(CompoundTag data) {
        return EnumWeaponType.valueOf(data.getString(NBT_TYPE));
    }

    default EnumCustomWarheadTrait getWeaponTrait(CompoundTag data) {
        return EnumCustomWarheadTrait.valueOf(data.getString(NBT_TRAIT));
    }

    ItemStack constructNew();

    ICustomWarhead getInstance();

    default Item getItem() {
        return (Item) this;
    }

    static ItemStack addData(CompoundTag data, Item item) {
        ItemStack stackOut = new ItemStack(item);
        stackOut.getOrCreateTag().put(NBT_GROUP, data);
        return stackOut.copy();
    }

    default CompoundTag getWarheadData(ItemStack stack) {
        return stack.getTagElement(NBT_GROUP);
    }

    default ItemStack addFuel(ItemStack stack, Enum<?> fuel, float amount) {
        if (!stack.isEmpty() && stack.getItem() instanceof ICustomWarhead) {
            CompoundTag data = getWarheadData(stack);
            if (data != null) {
                data.putFloat(fuel.toString(), amount);
                data.putFloat(NBT_MASS, data.getFloat(NBT_MASS) + amount);
            }
        }
        return stack;
    }

    default ItemStack addData(ItemStack stack, String key, String value) {
        if (!stack.isEmpty() && stack.getItem() instanceof ICustomWarhead) {
            getWarheadData(stack).putString(key, value);
        }
        return stack;
    }

    default void addCompositionalInfo(CompoundTag data, List<String> tooltip, List<Enum<?>> combinedFuels) {
        for (Enum<?> fuel : combinedFuels) {
            if (data.getFloat(fuel.toString()) > 0) {
                tooltip.add(String.format(Locale.US, "%s: %skg (%s)",
                        I18nUtil.resolveKey("warheadFuel.".concat(fuel.toString())),
                        df.format(data.getFloat(fuel.toString())),
                        BobMathUtil.toPercentage(data.getFloat(fuel.toString()), data.getFloat(NBT_MASS))
                ));
            }
        }
    }

    default void addTooltip(ItemStack stack, List<String> tooltip) {
        try {
            CompoundTag data = getWarheadData(stack);
            if (data == null) return;

            List<Enum<?>> combinedFuels = new ArrayList<>();
            combinedFuels.addAll(Arrays.asList(FissileFuel.values()));
            combinedFuels.addAll(Arrays.asList(FusionFuel.values()));
            combinedFuels.addAll(Arrays.asList(SaltedFuel.values()));
            combinedFuels.addAll(Arrays.asList(EnumCustomWarhead.values()));

            EnumCustomWarhead warhead = getWarheadType(data);
            tooltip.add(data.getFloat(NBT_MASS) + "kg total");
            tooltip.add("");

            switch (warhead) {
                case CHEM, BIO -> tooltip.add("Type: " + getColorFromWarhead(warhead) +
                        I18nUtil.resolveKey("warhead.".concat(warhead.toString()),
                                I18nUtil.resolveKey(data.getString(NBT_SPECIAL))));
                default -> tooltip.add("Type: " + getColorFromWarhead(warhead) + warhead.getLoc());
            }

            tooltip.add("Function: " + getWeaponType(data).getLoc());

            switch (warhead) {
                case AMAT, BF, FUSION, GRAV, HE, NUCLEAR, TX ->
                        tooltip.add("Yield: " + BobMathUtil.getShortNumber(data.getInt(NBT_YIELD)) + "T");
                case BIO, CHEM, SCHRAB ->
                        tooltip.add("Radius: " + BobMathUtil.getShortNumber(data.getInt(NBT_YIELD)) + "M");
            }

            tooltip.add("Trait: " + getWeaponTrait(data).getLoc());

        } catch (Exception e) {
            HBMNuclearTechCriticality.LOGGER.error(e.toString());
        }
    }

    public enum FissileFuel
    {
        U233(15F, 197.5F, HazardRegistry.u233, 19.05F),
        U235(52F, 202.5F, HazardRegistry.u235, 19.05F),
        Np237(60F, 202.5F, HazardRegistry.np237, 20.45F),
        Pu239(10F, 207.1F, HazardRegistry.pu239, 19.86F),
        Pu241(12, 210F, HazardRegistry.pu241, 19.86F),
        Am241(66, 210F, HazardRegistry.am241, 13.67F),
        Am242m(11F, 212F, HazardRegistry.am242, 13.67F),
        Sa326(1F, 250F, HazardRegistry.sa326, 39.7F);
        public final float criticalMass;
        public final float energyReleased;
        public final float radioactivity;
        private final float mass;
        private FissileFuel(float criticalMass, float energyReleased, float radioactivity, float mass)
        {
            this.criticalMass = criticalMass;
            this.energyReleased = energyReleased;
            this.radioactivity = radioactivity;
            this.mass = mass;
        }
        public float getBlockMass()
        {
            return mass * 100;
        }
        public float getIngotMass()
        {
            return getBlockMass() / 9;
        }
        public float getNuggetMass()
        {
            return getIngotMass() / 9;
        }
        public String getLoc()
        {
            return I18nUtil.resolveKey("warheadFuel.".concat(toString()));
        }
    }
    public enum FusionFuel
    {
        DEUT,
        TRIT,
        Li,
        LiDEUT;
        public String getLoc()
        {
            return I18nUtil.resolveKey("warheadFuel".concat(toString()));
        }
    }
    public enum SaltedFuel
    {
        Co59(1.4902F * 0.75F, 5, HalfLifeType.MEDIUM, 8.86F),
        Co60(1.4902F, 5, HalfLifeType.MEDIUM, 8.86F),
        Sr90(0.546F, 28, HalfLifeType.MEDIUM, 2.64F),
        Cs137(1.1737F, 30, HalfLifeType.MEDIUM, 1.93F),
        Ta181(0.52F * 0.75F, 114, HalfLifeType.SHORT, 16.65F),
        Ta182(0.52F, 114, HalfLifeType.SHORT, 16.654F),
        Au197(1.3735F * 0.75F, 2, HalfLifeType.SHORT, 19.32F),
        Au198(1.3735F, 2, HalfLifeType.SHORT, 19.32F),
        Pu240(5.25575F, 65, HalfLifeType.LONG, 19.86F),
        Sa327(0.5F, 100, HalfLifeType.LONG, 39.7F);
        public final float decayEnergy;
        public final int halfLife;
        public final HalfLifeType type;
        private final float mass;
        SaltedFuel(float decayEnergy, int halfLife, HalfLifeType type, float mass)
        {
            this.decayEnergy = decayEnergy;
            this.halfLife = halfLife;
            this.type = type;
            this.mass = mass;
        }
        public enum HalfLifeType
        {
            /** Counted in days **/
            SHORT,
            /** Counted in years **/
            MEDIUM,
            /** Counted in hundreds of years **/
            LONG;
        }
        public float getBlockMass()
        {
            return mass * 100;
        }
        public float getIngotMass()
        {
            return getBlockMass() / 9;
        }
        public float getNuggetMass()
        {
            return getIngotMass() / 9;
        }
    }
}
