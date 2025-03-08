package com.miniverse.hbm.api.entity;

/**
 * @deprecated Use {@link IRadarDetectableNT} instead. This old interface will still work but is not recommended.
 */
@Deprecated
public interface IRadarDetectable {

    /**
     * Enum representing different radar target types.
     */
    enum RadarTargetType {
        MISSILE_TIER0("Micro Missile"),             // Tier 0 missile (micro missile assemblies)
        MISSILE_TIER1("Tier 1 Missile"),            // Tier 1 missiles
        MISSILE_TIER2("Tier 2 Missile"),            // Tier 2 missiles
        MISSILE_TIER3("Tier 3 Missile"),            // Tier 3 missiles
        MISSILE_TIER4("Tier 4 Missile"),            // Tier 4 missiles (nuclear, thermo, doomsday)
        MISSILE_10("Size 10 Custom Missile"),       // Size 10 custom missiles
        MISSILE_10_15("Size 10/15 Custom Missile"), // Size 10/15 custom missiles
        MISSILE_15("Size 15 Custom Missile"),       // Size 15 custom missiles
        MISSILE_15_20("Size 15/20 Custom Missile"), // Size 15/20 custom missiles
        MISSILE_20("Size 20 Custom Missile"),       // Size 20 custom missiles
        MISSILE_AB("Anti-Ballistic Missile"),       // Anti-ballistic missile
        PLAYER("Player"),                           // Airborne players
        ARTILLERY("Artillery Shell");               // Artillery shells

        private final String name;

        RadarTargetType(String name) {
            this.name = name;
        }

        /**
         * Gets the display name of the radar target type.
         *
         * @return The target type name.
         */
        public String getDisplayName() {
            return name;
        }
    }

    /**
     * Gets the radar target type of the entity.
     *
     * @return The target type.
     */
    RadarTargetType getTargetType();
}
