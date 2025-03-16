package com.miniverse.hbm.items;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.interfaces.IOrderedEnum;
import com.miniverse.hbm.util.EnumUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;

public class ItemEnumMulti extends Item {

    // The enum class this item is based on
    protected final Class<? extends Enum> theEnum;
    protected final boolean multiName;
    protected final boolean multiTexture;

    public ItemEnumMulti(Class<? extends Enum> theEnum, boolean multiName, boolean multiTexture, Properties properties) {
        super(properties);
        this.theEnum = theEnum;
        this.multiName = multiName;
        this.multiTexture = multiTexture;
    }

    @Override
    public void fillItemCategory(CreativeModeTab.Output output) {
        if (!this.allowedIn(output.tab())) {
            return;
        }

        Enum[] order = theEnum.getEnumConstants();
        if(order.length > 0 && order[0] instanceof IOrderedEnum) {
            order = ((IOrderedEnum) order[0]).getOrder();
        }

        for(int i = 0; i < order.length; i++) {
            ItemStack stack = new ItemStack(this);
            stack.getOrCreateTag().putInt("Type", order[i].ordinal());
            output.accept(stack);
        }
    }

    /**
     * Creates a new ItemStack with the specified enum value
     * @param count The stack size
     * @param num The enum value
     * @return A new ItemStack, or null if the enum type doesn't match
     */
    public ItemStack stackFromEnum(int count, Enum num) {
        if(num.getClass() != this.theEnum) {
            return null;
        }

        ItemStack stack = new ItemStack(this, count);
        stack.getOrCreateTag().putInt("Type", num.ordinal());
        return stack;
    }

    public ItemStack stackFromEnum(Enum num) {
        return stackFromEnum(1, num);
    }

    /**
     * Gets the enum value from an ItemStack
     * @param stack The ItemStack to check
     * @return The enum value
     */
    public Enum getEnumFromStack(ItemStack stack) {
        int meta = 0;
        if (stack.hasTag() && stack.getTag().contains("Type")) {
            meta = stack.getTag().getInt("Type");
        }
        return EnumUtil.grabEnumSafely(theEnum, meta);
    }

    @Override
    public Component getName(ItemStack stack) {
        if(multiName) {
            Enum num = getEnumFromStack(stack);
            String enumName = num.name().toLowerCase(Locale.US);
            return Component.translatable(this.getDescriptionId() + "." + enumName);
        } else {
            return super.getName(stack);
        }
    }

    /**
     * Helper method to register models for this multi-enum item
     * @param modelProvider The item model provider
     */
    @OnlyIn(Dist.CLIENT)
    public void registerModels(ItemModelProvider modelProvider) {
        if(multiTexture) {
            Enum[] enums = theEnum.getEnumConstants();

            for(int i = 0; i < enums.length; i++) {
                Enum value = enums[i];
                String enumName = value.name().toLowerCase(Locale.US);
                String itemName = this.getDescriptionId().replace("item." + HBMNuclearTechCriticality.MODID + ".", "");

                // Create model for each enum value
                ModelFile model = modelProvider.getBuilder(itemName + "_" + enumName)
                        .parent(modelProvider.getExistingFile(modelProvider.mcLoc("item/generated")))
                        .texture("layer0", HBMNuclearTechCriticality.MODID + ":item/" + itemName + "/" + enumName);
            }
        } else {
            // Create a single model for the item
            String itemName = this.getDescriptionId().replace("item." + HBMNuclearTechCriticality.MODID + ".", "");
            ModelFile model = modelProvider.getBuilder(itemName)
                    .parent(modelProvider.getExistingFile(modelProvider.mcLoc("item/generated")))
                    .texture("layer0", HBMNuclearTechCriticality.MODID + ":item/" + itemName);
        }
    }
}