package com.miniverse.hbm.inventory.fluid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.miniverse.hbm.config.GeneralConfig;
import com.miniverse.hbm.inventory.fluid.tank.FluidTank;
import com.miniverse.hbm.inventory.fluid.trait.*;
import com.miniverse.hbm.inventory.fluid.trait.FluidTraitSimple.*;
import com.miniverse.hbm.lib.RefStrings;
import com.miniverse.hbm.render.util.EnumSymbol;
import com.miniverse.hbm.uninos.INetworkProvider;
import com.miniverse.hbm.util.I18nUtil;

import com.miniverse.hbm.api.fluidmk2.FluidNetMK2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.lwjgl.glfw.GLFW;

/**
 * Defines properties and behaviors of fluids in the HBM mod.
 */
public class FluidType {

    // The numeric ID of the fluid
    private final int id;
    // The internal name
    private final String stringId;
    // Approximate HEX Color of the fluid, used for pipe rendering
    private final int color;
    // Unlocalized string ID of the fluid
    private final String unlocalized;
    // Localization override for custom fluids
    private final String localizedOverride;
    private final int guiTint;

    public int poison;
    public int flammability;
    public int reactivity;
    public EnumSymbol symbol;
    public boolean customFluid = false;

    public static final int ROOM_TEMPERATURE = 20;
    public int temperature = ROOM_TEMPERATURE;

    public final Map<Class<?>, Object> containers = new HashMap<>();
    public final Map<Class<? extends FluidTrait>, FluidTrait> traits = new HashMap<>();

    private final ResourceLocation texture;

    public FluidType(String name, int color, int p, int f, int r, EnumSymbol symbol) {
        this.stringId = name;
        this.color = color;
        this.unlocalized = "hbmfluid." + name.toLowerCase(Locale.US);
        this.poison = p;
        this.flammability = f;
        this.reactivity = r;
        this.symbol = symbol;
        this.texture = new ResourceLocation(RefStrings.MODID, "textures/gui/fluids/" + name.toLowerCase(Locale.US) + ".png");
        this.guiTint = 0xffffff;
        this.localizedOverride = null;

        this.id = Fluids.registerSelf(this);
    }

    public FluidType(String name, int color, int p, int f, int r, EnumSymbol symbol, String texName, int tint, int id, String displayName) {
        this.stringId = name;
        this.color = color;
        this.unlocalized = "hbmfluid." + name.toLowerCase(Locale.US);
        this.poison = p;
        this.flammability = f;
        this.reactivity = r;
        this.symbol = symbol;
        this.texture = new ResourceLocation(RefStrings.MODID, "textures/gui/fluids/" + texName + ".png");
        this.guiTint = tint;
        this.localizedOverride = displayName;
        this.customFluid = true;

        this.id = id;
        Fluids.register(this, id);
    }

    public FluidType setTemp(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public FluidType addContainers(Object... containers) {
        for (Object container : containers) {
            this.containers.put(container.getClass(), container);
        }
        return this;
    }

    public <T> T getContainer(Class<? extends T> container) {
        return (T) this.containers.get(container);
    }

    public FluidType addTraits(FluidTrait... traits) {
        for (FluidTrait trait : traits) {
            this.traits.put(trait.getClass(), trait);
        }
        return this;
    }

    public boolean hasTrait(Class<? extends FluidTrait> trait) {
        return this.traits.containsKey(trait);
    }

    public <T extends FluidTrait> T getTrait(Class<? extends T> trait) {
        return (T) this.traits.get(trait);
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.stringId;
    }

    public int getColor() {
        return this.color;
    }

    public int getTint() {
        return this.guiTint;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public String getUnlocalizedName() {
        return this.unlocalized;
    }

    public String getLocalizedName() {
        return this.localizedOverride != null ? this.localizedOverride : I18nUtil.resolveKey(this.unlocalized);
    }

    public String getConditionalName() {
        return this.localizedOverride != null ? this.localizedOverride : this.unlocalized;
    }

    public boolean isHot() {
        return this.temperature >= 100;
    }

    public boolean isCorrosive() {
        return this.hasTrait(FT_Corrosive.class);
    }

    public boolean isAntimatter() {
        return this.hasTrait(FT_Amat.class);
    }

    public boolean hasNoContainer() {
        return this.hasTrait(FT_NoContainer.class);
    }

    public boolean hasNoID() {
        return this.hasTrait(FT_NoID.class);
    }

    public boolean needsLeadContainer() {
        return this.hasTrait(FT_LeadContainer.class);
    }

    public boolean isDispersable() {
        return !(this.hasTrait(FT_Amat.class) || this.hasTrait(FT_NoContainer.class) || this.hasTrait(FT_Viscous.class));
    }

    public void onTankBroken(BlockEntity entity, FluidTank tank) { }

    public void onTankUpdate(BlockEntity entity, FluidTank tank) { }

    public void onFluidRelease(BlockEntity entity, FluidTank tank, int overflowAmount) {
        this.onFluidRelease(entity.getLevel(), entity.getBlockPos(), tank, overflowAmount);
    }

    public void onFluidRelease(Level level, BlockPos pos, FluidTank tank, int overflowAmount) { }

    public INetworkProvider<FluidNetMK2> getNetworkProvider() {
        return null; // To be implemented
    }

    @SideOnly(Side.CLIENT)
    public void addInfo(List<Component> info) {
        if (temperature != ROOM_TEMPERATURE) {
            TextColor color = temperature < 0 ? TextColor.fromRgb(0x0000FF) : TextColor.fromRgb(0xFF0000);
            info.add(Component.literal(temperature + "°C").withStyle(style -> style.withColor(color)));
        }

        boolean shiftHeld = GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS;

        for (FluidTrait trait : traits.values()) {
            trait.addInfo(info);
            if (shiftHeld) {
                trait.addInfoHidden(info);
            }
        }
    }
}
