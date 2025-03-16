package com.miniverse.hbm.capabilities;

import com.miniverse.hbm.entity.train.EntityRailCarBase;
import com.miniverse.hbm.handler.ArmorModHandler;
import com.miniverse.hbm.handler.HbmKeybinds.EnumKeybind;
import com.miniverse.hbm.items.armor.ItemModShield;
import com.miniverse.hbm.HBMNuclearTechCriticality;
import com.miniverse.hbm.tileentity.IGUIProvider;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Capability that stores mod-specific player properties such as jetpack/HUD settings,
 * dash cooldowns, shield values, and reputation.
 * <p>
 * This replaces the old IExtendedEntityProperties-based HbmPlayerProps.
 * You must attach this capability to player entities (for example, using an AttachCapabilitiesEvent).
 */
public class HbmPlayerProps {

    // The player instance (set externally, for example during capability attachment)
    private Player player;

    // State fields (ported from the original mod)
    public boolean hasReceivedBook = false;
    public boolean enableHUD = true;
    public boolean enableBackpack = true;
    private boolean[] keysPressed = new boolean[EnumKeybind.values().length];
    public boolean dashActivated = true;
    public static final int dashCooldownLength = 5;
    public int dashCooldown = 0;
    public int totalDashCount = 0;
    public int stamina = 0;
    public static final int plinkCooldownLength = 10;
    public int plinkCooldown = 0;
    public float shield = 0;
    public float maxShield = 0;
    public int lastDamage = 0;
    public static final float shieldCap = 100;
    public int reputation;
    public boolean isOnLadder = false;

    // You can provide a no-arg constructor; the player field should be set during capability attachment.
    public HbmPlayerProps() { }

    /**
     * Optionally set the player instance for this capability.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Checks if the given key (as defined by the mod's keybind enum) is pressed.
     */
    public boolean getKeyPressed(EnumKeybind key) {
        return keysPressed[key.ordinal()];
    }

    /**
     * Returns whether the jetpack is active.
     */
    public boolean isJetpackActive() {
        return this.enableBackpack && getKeyPressed(EnumKeybind.JETPACK);
    }

    /**
     * Sets the key pressed state. Also handles toggling of jetpack and HUD settings,
     * and opens a GUI when the TRAIN key is pressed.
     */
    public void setKeyPressed(EnumKeybind key, boolean pressed) {
        // Only trigger actions on key-down events
        if (!getKeyPressed(key) && pressed) {

            // Toggle jetpack enabled
            if (key == EnumKeybind.TOGGLE_JETPACK) {
                if (!player.level().isClientSide()) {
                    this.enableBackpack = !this.enableBackpack;
                    if (this.enableBackpack)
                        HBMNuclearTechCriticality.proxy.displayTooltip("§aJetpack ON", HBMNuclearTechCriticality.proxy.ID_JETPACK);
                    else
                        HBMNuclearTechCriticality.proxy.displayTooltip("§cJetpack OFF", HBMNuclearTechCriticality.proxy.ID_JETPACK);
                }
            }

            // Toggle HUD enabled
            if (key == EnumKeybind.TOGGLE_HEAD) {
                if (!player.level().isClientSide()) {
                    this.enableHUD = !this.enableHUD;
                    if (this.enableHUD)
                        HBMNuclearTechCriticality.proxy.displayTooltip("§aHUD ON", HBMNuclearTechCriticality.proxy.ID_HUD);
                    else
                        HBMNuclearTechCriticality.proxy.displayTooltip("§cHUD OFF", HBMNuclearTechCriticality.proxy.ID_HUD);
                }
            }

            // Open GUI for rail cars if the TRAIN key is pressed
            if (key == EnumKeybind.TRAIN) {
                if (!player.level().isClientSide() && player.getVehicle() != null) {
                    Entity riding = player.getVehicle();
                    if (riding instanceof EntityRailCarBase && riding instanceof IGUIProvider) {
                        // Use NetworkHooks.openGui instead of FMLNetworkHandler.openGui.
                        NetworkHooks.openGui((ServerPlayer) player, (IGUIProvider) riding, player.blockPosition());
                    }
                }
            }
        }
        keysPressed[key.ordinal()] = pressed;
    }

    public void setDashCooldown(int cooldown) {
        this.dashCooldown = cooldown;
    }

    public int getDashCooldown() {
        return this.dashCooldown;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStamina() {
        return this.stamina;
    }

    public void setDashCount(int count) {
        this.totalDashCount = count;
    }

    public int getDashCount() {
        return this.totalDashCount;
    }

    /**
     * Plays a mod-specific sound (plink) if the cooldown allows.
     */
    public static void plink(Player player, String sound, float volume, float pitch) {
        HbmPlayerProps props = get(player);
        if (props.plinkCooldown <= 0) {
            SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound));
            if (soundEvent != null)
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), soundEvent, SoundSource.PLAYERS, volume, pitch);
            props.plinkCooldown = plinkCooldownLength;
        }
    }

    /**
     * Returns the maximum effective shield. If the player's chest armor has a shield mod,
     * its shield bonus is added.
     */
    public float getEffectiveMaxShield() {
        float max = this.maxShield;
        // In 1.20.1 the armor is retrieved via getItemBySlot
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!chest.isEmpty()) {
            ItemStack[] mods = ArmorModHandler.pryMods(chest);
            if (mods[ArmorModHandler.kevlar] != null && mods[ArmorModHandler.kevlar].getItem() instanceof ItemModShield) {
                ItemModShield mod = (ItemModShield) mods[ArmorModHandler.kevlar].getItem();
                max += mod.shield;
            }
        }
        return max;
    }

    /**
     * Serializes capability data to NBT.
     */
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("hasReceivedBook", this.hasReceivedBook);
        tag.putFloat("shield", this.shield);
        tag.putFloat("maxShield", this.maxShield);
        tag.putBoolean("enableBackpack", this.enableBackpack);
        tag.putBoolean("enableHUD", this.enableHUD);
        tag.putInt("reputation", this.reputation);
        tag.putBoolean("isOnLadder", this.isOnLadder);
        tag.putInt("dashCooldown", this.dashCooldown);
        tag.putInt("totalDashCount", this.totalDashCount);
        tag.putInt("stamina", this.stamina);
        tag.putInt("plinkCooldown", this.plinkCooldown);
        tag.putInt("lastDamage", this.lastDamage);
        return tag;
    }

    /**
     * Deserializes capability data from NBT.
     */
    public void deserializeNBT(CompoundTag nbt) {
        this.hasReceivedBook = nbt.getBoolean("hasReceivedBook");
        this.shield = nbt.getFloat("shield");
        this.maxShield = nbt.getFloat("maxShield");
        this.enableBackpack = nbt.getBoolean("enableBackpack");
        this.enableHUD = nbt.getBoolean("enableHUD");
        this.reputation = nbt.getInt("reputation");
        this.isOnLadder = nbt.getBoolean("isOnLadder");
        this.dashCooldown = nbt.getInt("dashCooldown");
        this.totalDashCount = nbt.getInt("totalDashCount");
        this.stamina = nbt.getInt("stamina");
        this.plinkCooldown = nbt.getInt("plinkCooldown");
        this.lastDamage = nbt.getInt("lastDamage");
    }

    /**
     * Helper method to get the capability from a player.
     */
    public static HbmPlayerProps get(Player player) {
        return player.getCapability(CustomCapabilities.HBM_PLAYER_CAPABILITY)
                .orElseThrow(() -> new IllegalArgumentException("HbmPlayerProps capability not attached"));
    }
}