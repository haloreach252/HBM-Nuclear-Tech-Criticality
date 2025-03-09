package com.miniverse.hbm.lib;

import com.miniverse.hbm.world.gen.MapGenNTMFeatures;
import com.miniverse.hbm.world.gen.NBTStructure;
import com.miniverse.hbm.world.gen.NTMWorldGenerator;
import com.miniverse.hbm.world.gen.component.*;
import com.miniverse.hbm.world.gen.component.BunkerComponents.BunkerStart;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * Handles mod-specific world generation registration and structure component registration.
 *
 * <p>
 * Note: The world generation system in Forge 1.20.1 is different from previous versions.
 * You will need to adapt your IWorldGenerator implementations to be registered via BiomeLoadingEvent,
 * or use DeferredRegisters for your features. In this example, we assume you have a custom registration
 * system (WorldGenRegistry) for storing your generators until they can be hooked into world generation.
 * </p>
 */
@Mod.EventBusSubscriber
public class HbmWorld {

    public static NTMWorldGenerator worldGenerator;

    /**
     * Called during mod initialization to register world generators and structures.
     */
    public static void mainRegistry() {
        initWorldGen();
    }

    public static void initWorldGen() {
        // Register structures with your custom MapGenStructureIO.
        MapGenStructureIO.registerStructure(MapGenNTMFeatures.Start.class, "NTMFeatures");
        MapGenStructureIO.registerStructure(BunkerStart.class, "NTMBunker");
        registerNTMFeatures();

        // Register world generators.
        // Note: In 1.20.1 you should register world generators via BiomeLoadingEvent or similar events.
        registerWorldGen(new HbmWorldGen(), 1);

        worldGenerator = new NTMWorldGenerator();
        registerWorldGen(worldGenerator, 1); // Ideally, move everything over from HbmWorldGen to NTMWorldGenerator

        // Register the world generator on the event bus.
        MinecraftForge.EVENT_BUS.register(worldGenerator);

        // Register any custom NBT structures.
        NBTStructure.register();
    }

    /**
     * Registers a world generator with a weighted probability.
     *
     * <p>
     * In 1.20.1, world generation registration is usually handled via events (e.g. BiomeLoadingEvent)
     * or using DeferredRegisters. Here, we delegate to a custom WorldGenRegistry.
     * </p>
     *
     * @param worldGen the world generator instance
     * @param weightedProbability the weight or priority for generation
     */
    private static void registerWorldGen(IWorldGenerator worldGen, int weightedProbability) {
        WorldGenRegistry.register(worldGen, weightedProbability);
    }

    /**
     * Registers structure components with MapGenStructureIO.
     */
    private static void registerNTMFeatures() {
        CivilianFeatures.registerComponents();
        OfficeFeatures.registerComponents();
        RuinFeatures.registerComponents();
        BunkerComponents.registerComponents();
        // The method formerly known as func_143031_a is now more descriptively named.
        MapGenStructureIO.registerStructureComponent(SiloComponent.class, "NTMSiloComponent");
    }

    /**
     * Dummy interface for your world generator. In 1.20.1 this might be replaced with your own
     * interface that is hooked into BiomeLoadingEvent or similar.
     */
    public interface IWorldGenerator {
        // Define the necessary methods for world generation.
    }

    /**
     * Dummy registry for world generators.
     * You will need to implement this registry to collect and later process your world generators.
     */
    public static class WorldGenRegistry {
        public static void register(IWorldGenerator worldGen, int weightedProbability) {
            // Implement registration logic here.
            // For example, add the worldGen to a list that is processed during BiomeLoadingEvent.
        }
    }
}
