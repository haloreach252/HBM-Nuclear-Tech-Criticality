package com.miniverse.hbm.api.block;

/**
 * Represents a mining drill that interacts with IDrillInteraction blocks.
 */
public interface IMiningDrill {

    /**
     * Gets the era or technological tier of the drill. This is used by IDrillInteraction
     * to adjust outputs based on the drill's capabilities.
     *
     * @return The drill's tier.
     */
    DrillType getDrillTier();

    /**
     * Gets the arbitrary power rating of the drill.
     * - Hand-powered pre-industrial drills would be <10.
     * - The auto mining drill is rated at 50.
     * - The laser miner is rated at 100.
     *
     * @return The drill's power rating.
     */
    int getDrillRating();

    /**
     * Represents different types of drills based on technological advancements.
     */
    enum DrillType {
        PRIMITIVE,  // Basic, hand-powered drills.
        INDUSTRIAL, // Mid-tier industrial drills.
        HITECH      // Advanced, high-tech drills like laser miners.
    }
}
