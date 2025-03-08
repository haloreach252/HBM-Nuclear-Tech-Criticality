package com.miniverse.hbm.api.entity;

import net.minecraft.world.damagesource.DamageSource;

/**
 * Allows custom entities to specify threshold and resistance values based on incoming damage, type, and piercing values.
 * This interface is meant for HBM's custom entities implementing custom damage resistance logic.
 *
 * @author hbm
 */
public interface IResistanceProvider {

    /**
     * Gets the current damage threshold and damage resistance (DTDR) values for an entity.
     *
     * @param damage The source of the damage.
     * @param amount The amount of damage being dealt.
     * @param pierceDT The piercing value affecting damage threshold.
     * @param pierce The general piercing value.
     * @return An array representing the DTDR values.
     */
    float[] getCurrentDTDR(DamageSource damage, float amount, float pierceDT, float pierce);

    /**
     * Called when damage is successfully dealt to the entity, allowing for additional logic.
     *
     * @param damage The source of the damage.
     * @param amount The amount of damage taken.
     */
    void onDamageDealt(DamageSource damage, float amount);
}
