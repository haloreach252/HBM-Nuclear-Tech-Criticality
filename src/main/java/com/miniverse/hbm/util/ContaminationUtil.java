package com.miniverse.hbm.util;

import com.miniverse.hbm.capabilities.CustomCapabilities;
import com.miniverse.hbm.capabilities.HbmLivingProps;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import java.util.HashSet;

public class ContaminationUtil {

    /**
     * Calculates how much radiation can be applied to this entity by calculating resistance
     * @param entity
     * @return
     */
    public static float calculateRadiationMod(LivingEntity entity) {
        if (entity instanceof Player) {
            Player player = (Player)entity;

            float koeff = 10.0f;
            return (float) Math.pow(koeff, -HazmatRegistry.getResistance(player));
        }

        return 1;
    }

    public static float getRads(Entity e) {
        if (!(e instanceof LivingEntity entity) || isRadImmune(entity)) {
            return 0.0f;
        }

        LazyOptional<HbmLivingProps> props = entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY);
        return props.map(HbmLivingProps::getRadiation).orElse(0.0f);
    }

    public static HashSet<Class> immuneEntities = new HashSet<>();

    public static boolean isRadImmune(Entity e) {
        if (e instanceof LivingEntity entity && entity.hasEffect(HbmPotion.mutation)) {
            return true;
        }

        if (immuneEntities.isEmpty()) {
            immuneEntities.add(EntityCreeperNuclear.class);
            immuneEntities.add(EntityMooshroom.class);
            immuneEntities.add(EntityZombie.class);
            immuneEntities.add(EntitySkeleton.class);
            immuneEntities.add(EntityQuackos.class);
            immuneEntities.add(Cat.class);
            immuneEntities.add(IRadiationImmune.class);
        }

        return immuneEntities.stream().anyMatch(clazz -> clazz.isAssignableFrom(e.getClass()));
    }

    /// ASBESTOS ///
    public static void applyAsbestos(Entity e, int i) {
        if (!(e instanceof LivingEntity entity) || (entity instanceof Player player && (player.getAbilities().instabuild || player.tickCount < 200))) {
            return;
        }

        if (ArmorRegistry.hasAllProtection(entity, 3, HazardClass.PARTICLE_FINE)) {
            ArmorUtil.damageGasMaskFilter(entity, i);
        } else {
            entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.incrementAsbestos(entity, i));
        }
    }

    /// DIGAMMA ///
    public static void applyDigammaData(Entity e, float f) {
        if (!(e instanceof LivingEntity entity) || entity instanceof EntityDuck || entity instanceof Cat | (entity instanceof Player player && (player.getAbilities().instabuild || player.tickCount < 200))) {
            return;
        }

        if (entity.hasEffect(HbmPotion.stability.id)) {
            return;
        }

        if (!(entity instanceof Player player && ArmorUtil.checkForDigamma(player))) {
            entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.incrementDigamma(entity, f));
        }
    }

    public static void applyDigammaDirect(Entity e, float f) {
        if (!(e instanceof LivingEntity entity) || entity instanceof IRadiationImmune || (e instanceof Player player && (player.getAbilities().instabuild))) {
            return;
        }

        entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.incrementDigamma(entity, f));
    }

    public static float getDigamma(Entity e) {
        if (!(e instanceof LivingEntity entity)) {
            return 0.0f;
        }

        return entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).map(HbmLivingProps::getDigamma).orElse(0.0f);
    }

    public static void printGeigerData(Player player) {

        Level world = player.level();

        double eRad = ((int)(player.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).map(HbmLivingProps::getRadiation).orElse(0.0f) * 10)) / 10D;

        double rads = ((int)(ChunkRadiationManager.proxy.getRadiation(world, (int) Math.floor(player.getX()), (int) Math.floor(player.getY()), (int) Math.floor(player.getZ())) * 10)) / 10D;
        double env = ((int)(player.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).map(HbmLivingProps::getRadBuf).orElse(0.0f) * 10D)) / 10D;

        double res = ((int)(10000D - ContaminationUtil.calculateRadiationMod(player) * 10000D)) / 100D;
        double resKoeff = ((int)(HazmatRegistry.getResistance(player) * 100D)) / 100D;

        String chunkPrefix = getPreffixFromRad(rads);
        String envPrefix = getPreffixFromRad(env);
        String radPrefix = "";
        String resPrefix = "" + ChatFormatting.WHITE;

        if(eRad < 200)
            radPrefix += ChatFormatting.GREEN;
        else if(eRad < 400)
            radPrefix += ChatFormatting.YELLOW;
        else if(eRad < 600)
            radPrefix += ChatFormatting.GOLD;
        else if(eRad < 800)
            radPrefix += ChatFormatting.RED;
        else if(eRad < 1000)
            radPrefix += ChatFormatting.DARK_RED;
        else
            radPrefix += ChatFormatting.DARK_GRAY;

        if(resKoeff > 0)
            resPrefix += ChatFormatting.GREEN;

        //localization and server-side restrictions have turned this into a painful mess
        //a *functioning* painful mess, nonetheless
        player.sendSystemMessage(Component.literal("===== ☢ ").append(Component.translatable("geiger.title")).append(Component.literal(" ☢ =====")).setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
        player.sendSystemMessage(Component.literal("geiger.chunkRad").append(Component.literal(" " + chunkPrefix + rads + " RAD/s")).setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW)));
        player.sendSystemMessage(Component.literal("geiger.envRad").append(Component.literal(" " + envPrefix + env + " RAD/s")).setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW)));
        player.sendSystemMessage(Component.literal("geiger.playerRad").append(Component.literal(" " + radPrefix + eRad + " RAD")).setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW)));
        player.sendSystemMessage(Component.literal("geiger.playerRes").append(Component.literal(" " + resPrefix + res + "% (" + resKoeff + ")")).setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW)));
    }

    public static void printDosimeterData(Player player) {

        double env = ((int)(player.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).map(HbmLivingProps::getRadBuf).orElse(0.0f) * 10D)) / 10D;
        boolean limit = false;

        if(env > 3.6D) {
            env = 3.6D;
            limit = true;
        }

        String envPrefix = getPreffixFromRad(env);

        player.sendSystemMessage(Component.literal("===== ☢ ").append(Component.translatable("geiger.title.dosimeter")).append(Component.literal(" ☢ =====")).setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD)));
        player.sendSystemMessage(Component.translatable("geiger.envRad").append(Component.literal(" " + envPrefix + (limit ? ">" : "") + env + " RAD/s")).setStyle(Style.EMPTY.withColor(ChatFormatting.YELLOW)));
    }

    public static String getPreffixFromRad(double rads) {

        String chunkPrefix = "";

        if(rads == 0)
            chunkPrefix += ChatFormatting.GREEN;
        else if(rads < 1)
            chunkPrefix += ChatFormatting.YELLOW;
        else if(rads < 10)
            chunkPrefix += ChatFormatting.GOLD;
        else if(rads < 100)
            chunkPrefix += ChatFormatting.RED;
        else if(rads < 1000)
            chunkPrefix += ChatFormatting.DARK_RED;
        else
            chunkPrefix += ChatFormatting.DARK_GRAY;

        return chunkPrefix;
    }

    public static void printDiagnosticData(Player player) {

        double digamma = ((int)(player.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).map(HbmLivingProps::getDigamma).orElse(0.0f) * 100)) / 100D;
        double halflife = ((int)((1D - Math.pow(0.5, digamma)) * 10000)) / 100D;

        player.sendSystemMessage(Component.literal("===== Ϝ ")
                .append(Component.translatable("digamma.title"))
                .append(Component.literal(" Ϝ ====="))
                .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));

        player.sendSystemMessage(Component.translatable("digamma.playerDigamma")
                .append(Component.literal(" " + digamma + " DRX").withStyle(ChatFormatting.RED))
                .setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)));

        player.sendSystemMessage(Component.translatable("digamma.playerHealth")
                .append(Component.literal(" " + halflife + "%").withStyle(ChatFormatting.RED))
                .setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)));

        player.sendSystemMessage(Component.translatable("digamma.playerRes")
                .append(Component.literal(" N/A").withStyle(ChatFormatting.BLUE))
                .setStyle(Style.EMPTY.withColor(ChatFormatting.LIGHT_PURPLE)));
    }

    public static enum HazardType {
        RADIATION,
        DIGAMMA
    }

    public static enum ContaminationType {
        FARADAY,			//preventable by metal armor
        HAZMAT,				//preventable by hazmat
        HAZMAT2,			//preventable by heavy hazmat
        DIGAMMA,			//preventable by fau armor or stability
        DIGAMMA2,			//preventable by robes
        CREATIVE,			//preventable by creative mode, for rad calculation armor piece bonuses still apply
        RAD_BYPASS,			//same as creative but will not apply radiation resistance calculation
        NONE				//not preventable
    }

    /*
     * This system is nice but the cont types are a bit confusing. Cont types should have much better names and multiple cont types should be applicable.
     */
    @SuppressWarnings("incomplete-switch") //just shut up
    //instead of this does-everything-but-nothing-well solution, please use the ArmorRegistry to check for protection and the HBM Props for applying contamination. still good for regular radiation tho
    public static boolean contaminate(LivingEntity entity, HazardType hazard, ContaminationType cont, float amount) {

        if(hazard == HazardType.RADIATION) {
            float radEnv = entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).map(HbmLivingProps::getRadEnv).orElse(0.0f);
            entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.setRadEnv(radEnv + amount));
        }

        if(entity instanceof Player) {

            Player player = (Player)entity;

            switch(cont) {
                case FARADAY:			if(ArmorUtil.checkForFaraday(player))	return false; break;
                case HAZMAT:			if(ArmorUtil.checkForHazmat(player))	return false; break;
                case HAZMAT2:			if(ArmorUtil.checkForHaz2(player))		return false; break;
                case DIGAMMA:			if(ArmorUtil.checkForDigamma(player))	return false; if(ArmorUtil.checkForDigamma2(player))	return false; break;
                case DIGAMMA2:			if(ArmorUtil.checkForDigamma2(player))	return false; break;
            }

            if(player.getAbilities().instabuild && cont != ContaminationType.NONE && cont != ContaminationType.DIGAMMA2)
                return false;

            if(player.tickCount < 200)
                return false;
        }

        if(hazard == HazardType.RADIATION && isRadImmune(entity))
            return false;

        switch(hazard) {
            case RADIATION: entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.incrementRadiation(amount * (cont == ContaminationType.RAD_BYPASS ? 1 : calculateRadiationMod(entity)))); break;
            case DIGAMMA: entity.getCapability(CustomCapabilities.HBM_LIVING_CAPABILITY).ifPresent(props -> props.incrementDigamma(amount)); break;
        }

        return true;
    }
}
