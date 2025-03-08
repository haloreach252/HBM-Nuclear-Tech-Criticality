package com.miniverse.hbm;

import com.miniverse.hbm.config.*;
import com.miniverse.hbm.items.ModCreativeModeTabs;
import com.miniverse.hbm.items.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HBMNuclearTechCriticality.MODID)
public class HBMNuclearTechCriticality
{
    public static final String MODID = "hbmnucleartechcriticality";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HBMNuclearTechCriticality(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register items, creative mode tabs, and other features
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);

        // Register config files
        // Client config files
        context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        ClientConfig.loadJsonConfig();

        // Common Config Files
        context.registerConfig(ModConfig.Type.COMMON, BombConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, GeneralConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, MachineConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, MobConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, PotionConfig.SPEC);
        context.registerConfig(ModConfig.Type.COMMON, RadiationConfig.SPEC);

        // Server Config Files
        context.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);
        context.registerConfig(ModConfig.Type.SERVER, StructureConfig.SPEC);
        context.registerConfig(ModConfig.Type.SERVER, ToolConfig.SPEC);
        context.registerConfig(ModConfig.Type.SERVER, VersatileConfig.SPEC);
        context.registerConfig(ModConfig.Type.SERVER, WeaponConfig.SPEC);
        context.registerConfig(ModConfig.Type.SERVER, WorldConfig.SPEC);

        // Custom Configs
        CustomMachineConfigJSON.initialize();
        FalloutConfigJSON.initialize();
        ItemPoolConfigJSON.initialize();
        MachineDynConfig.initialize();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        StructureConfig.validateConfig();
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
