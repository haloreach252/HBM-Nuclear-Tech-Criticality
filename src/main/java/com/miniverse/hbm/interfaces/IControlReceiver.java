package com.miniverse.hbm.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

/**
 * For receiving (sort of) complex control data via NBT from clients
 *
 * @author hbm
 */
public interface IControlReceiver {

    boolean hasPermission(Player player);

    void receiveControl(CompoundTag data);

    /**
     * Default method to receive control data, preserving compatibility with multiple implementors.
     *
     * @param player the player sending the control data
     * @param data   the CompoundTag containing the control data
     */
    default void receiveControl(Player player, CompoundTag data) {}
}
