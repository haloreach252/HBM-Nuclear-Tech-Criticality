package com.miniverse.hbm.items;

import com.miniverse.hbm.HBMNuclearTechCriticality;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class ItemGenericPart extends ItemEnumMulti {

    public static enum EnumPartType {
        PISTON_PNEUMATIC("piston_pneumatic"),
        PISTON_HYDRAULIC("piston_hydraulic"),
        PISTON_ELECTRIC("piston_electric"),
        LDE("low_density_element"),
        HDE("heavy_duty_element");

        private final String texName;

        private EnumPartType(String texName) {
            this.texName = texName;
        }

        public String getTextureName() {
            return this.texName;
        }
    }

    public ItemGenericPart(Item.Properties properties) {
        super(EnumPartType.class, true, true, properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerModels(ItemModelProvider modelProvider) {
        EnumPartType[] types = EnumPartType.values();

        for(int i = 0; i < types.length; i++) {
            EnumPartType type = types[i];
            String texturePath = type.getTextureName();

            // Use custom texture paths for parts
            modelProvider.getBuilder("generic_part_" + type.name().toLowerCase())
                    .parent(modelProvider.getExistingFile(modelProvider.mcLoc("item/generated")))
                    .texture("layer0", HBMNuclearTechCriticality.MODID + ":item/" + texturePath);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerItemColors(RegisterColorHandlersEvent.Item event, Item item) {
        // If you need custom coloring for the items
        event.register((stack, tintIndex) -> {
            // Return default color (no tinting)
            return 0xFFFFFF;
        }, (ItemGenericPart)item);
    }

    // Helper method to create an ItemStack with the specified part type
    public static ItemStack createStack(EnumPartType type) {
        ItemGenericPart item = null; // This should be your registered instance
        // You'll need to replace this with code to get your registered instance
        // e.g., return ForgeRegistries.ITEMS.getValue(new ResourceLocation(HBMNuclearTechCriticality.MODID, "generic_part"));

        if (item != null) {
            return item.stackFromEnum(type);
        }
        return ItemStack.EMPTY;
    }
}