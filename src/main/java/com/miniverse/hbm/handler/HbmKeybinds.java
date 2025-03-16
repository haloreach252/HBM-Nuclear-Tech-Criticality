package com.miniverse.hbm.handler;

import com.miniverse.hbm.capabilities.CustomCapabilities;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.capabilities.HbmPlayerProps;
import com.miniverse.hbm.capabilities.HbmPlayerPropsProvider;
import com.miniverse.hbm.packet.PacketDispatcher;
import com.miniverse.hbm.packet.toserver.KeybindPacket;
import com.miniverse.hbm.inventory.gui.GUICalculator;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = HBMNuclearTechCriticality.MODID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class HbmKeybinds {

    public static final String CATEGORY = "key.categories.hbm";

    public static KeyMapping calculatorKey;
    public static KeyMapping jetpackKey;
    public static KeyMapping hudKey;
    public static KeyMapping dashKey;
    public static KeyMapping trainKey;

    public static KeyMapping copyToolAlt;
    public static KeyMapping copyToolCtrl;

    public static KeyMapping reloadKey;
    public static KeyMapping gunPrimaryKey;
    public static KeyMapping gunSecondaryKey;
    public static KeyMapping gunTertiaryKey;

    public static KeyMapping craneUpKey;
    public static KeyMapping craneDownKey;
    public static KeyMapping craneLeftKey;
    public static KeyMapping craneRightKey;
    public static KeyMapping craneLoadKey;

    public static void init() {
        // For keyboard keys
        calculatorKey = new KeyMapping("key.hbm.calculator", GLFW.GLFW_KEY_N, CATEGORY);
        jetpackKey = new KeyMapping("key.hbm.toggleBack", GLFW.GLFW_KEY_C, CATEGORY);
        hudKey = new KeyMapping("key.hbm.toggleHUD", GLFW.GLFW_KEY_V, CATEGORY);
        dashKey = new KeyMapping("key.hbm.dash", GLFW.GLFW_KEY_LEFT_SHIFT, CATEGORY);
        trainKey = new KeyMapping("key.hbm.trainInv", GLFW.GLFW_KEY_R, CATEGORY);

        copyToolAlt = new KeyMapping("key.hbm.copyToolAlt", GLFW.GLFW_KEY_LEFT_ALT, CATEGORY);
        copyToolCtrl = new KeyMapping("key.hbm.copyToolCtrl", GLFW.GLFW_KEY_LEFT_CONTROL, CATEGORY);

        reloadKey = new KeyMapping("key.hbm.reload", GLFW.GLFW_KEY_R, CATEGORY);

        // For mouse buttons, you might need to use InputConstants.Type.MOUSE
        // The import you need is: import com.mojang.blaze3d.platform.InputConstants;
        gunPrimaryKey = new KeyMapping("key.hbm.gunPrimary", InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_1, CATEGORY);
        gunSecondaryKey = new KeyMapping("key.hbm.gunSecondary", InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_2, CATEGORY);
        gunTertiaryKey = new KeyMapping("key.hbm.gunTertiary", InputConstants.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_3, CATEGORY);

        craneUpKey = new KeyMapping("key.hbm.craneMoveUp", GLFW.GLFW_KEY_UP, CATEGORY);
        craneDownKey = new KeyMapping("key.hbm.craneMoveDown", GLFW.GLFW_KEY_DOWN, CATEGORY);
        craneLeftKey = new KeyMapping("key.hbm.craneMoveLeft", GLFW.GLFW_KEY_LEFT, CATEGORY);
        craneRightKey = new KeyMapping("key.hbm.craneMoveRight", GLFW.GLFW_KEY_RIGHT, CATEGORY);
        craneLoadKey = new KeyMapping("key.hbm.craneLoad", GLFW.GLFW_KEY_ENTER, CATEGORY);
    }

    @Mod.EventBusSubscriber(modid = HBMNuclearTechCriticality.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class KeyRegistry {
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(calculatorKey);
            event.register(jetpackKey);
            event.register(hudKey);
            event.register(dashKey);
            event.register(trainKey);

            event.register(copyToolAlt);
            event.register(copyToolCtrl);

            event.register(reloadKey);
            event.register(gunPrimaryKey);
            event.register(gunSecondaryKey);
            event.register(gunTertiaryKey);

            event.register(craneUpKey);
            event.register(craneDownKey);
            event.register(craneLeftKey);
            event.register(craneRightKey);
            event.register(craneLoadKey);
        }
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton event) {
        handleKeyState();
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (calculatorKey.consumeClick()) {
            Minecraft.getInstance().setScreen(new GUICalculator());
        }

        handleKeyState();
    }

    private static void handleKeyState() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        minecraft.player.getCapability(CustomCapabilities.HBM_PLAYER_CAPABILITY).ifPresent(props -> {
            for (EnumKeybind key : EnumKeybind.values()) {
                boolean lastState = props.getKeyPressed(key);
                boolean currentState = isKeyPressed(key);

                if (lastState != currentState) {
                    PacketDispatcher.wrapper.sendToServer(new KeybindPacket(key, currentState));
                    props.setKeyPressed(key, currentState);
                }
            }
        });
    }

    private static boolean isKeyPressed(EnumKeybind key) {
        return switch (key) {
            case JETPACK, TOGGLE_JETPACK -> jetpackKey.isDown();
            case TOGGLE_HEAD -> hudKey.isDown();
            case DASH -> dashKey.isDown();
            case TRAIN -> trainKey.isDown();
            case CRANE_UP -> craneUpKey.isDown();
            case CRANE_DOWN -> craneDownKey.isDown();
            case CRANE_LEFT -> craneLeftKey.isDown();
            case CRANE_RIGHT -> craneRightKey.isDown();
            case CRANE_LOAD -> craneLoadKey.isDown();
            case TOOL_ALT -> copyToolAlt.isDown();
            case TOOL_CTRL -> copyToolCtrl.isDown();
            case GUN_PRIMARY -> gunPrimaryKey.isDown();
            case GUN_SECONDARY -> gunSecondaryKey.isDown();
            case GUN_TERTIARY -> gunTertiaryKey.isDown();
            case RELOAD -> reloadKey.isDown();
        };
    }

    public enum EnumKeybind {
        JETPACK,
        TOGGLE_JETPACK,
        TOGGLE_HEAD,
        DASH,
        TRAIN,
        CRANE_UP,
        CRANE_DOWN,
        CRANE_LEFT,
        CRANE_RIGHT,
        CRANE_LOAD,
        TOOL_ALT,
        TOOL_CTRL,
        GUN_PRIMARY,
        GUN_SECONDARY,
        GUN_TERTIARY,
        RELOAD
    }
}