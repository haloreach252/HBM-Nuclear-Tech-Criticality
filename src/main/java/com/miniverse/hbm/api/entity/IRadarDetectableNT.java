package com.miniverse.hbm.api.entity;

/**
 * Interface for radar-detectable entities in the Nuclear Tech mod.
 */
public interface IRadarDetectableNT {

    // Radar blip tiers
    int TIER0 = 0;
    int TIER1 = 1;
    int TIER2 = 2;
    int TIER3 = 3;
    int TIER4 = 4;
    int TIER10 = 5;
    int TIER10_15 = 6;
    int TIER15 = 7;
    int TIER15_20 = 8;
    int TIER20 = 9;
    int TIER_AB = 10;
    int PLAYER = 11;
    int ARTY = 12;

    /** Reserved type that shows a unique purple blip. Used when nothing else applies. */
    int SPECIAL = 13;

    /**
     * Gets the name used for radar display. Uses I18n for lookup.
     *
     * @return The unlocalized name of the entity.
     */
    String getUnlocalizedName();

    /**
     * Gets the type of blip to show on the radar and the redstone level in tier mode.
     *
     * @return The blip level.
     */
    int getBlipLevel();

    /**
     * Checks whether the object can be seen by this type of radar.
     *
     * @param radar The radar object performing the scan.
     * @return True if the object can be detected, false otherwise.
     */
    boolean canBeSeenBy(Object radar);

    /**
     * Determines whether the object is currently visible and whether the radar settings
     * allow for picking this up.
     *
     * @param params The radar scan parameters.
     * @return True if the parameters allow detection, false otherwise.
     */
    boolean paramsApplicable(RadarScanParams params);

    /**
     * Determines whether this radar entry should be counted for redstone output.
     *
     * @param params The radar scan parameters.
     * @return True if this entry supplies redstone output, false otherwise.
     */
    boolean suppliesRedstone(RadarScanParams params);

    /**
     * Represents the parameters used when performing a radar scan.
     */
    class RadarScanParams {
        public final boolean scanMissiles;
        public final boolean scanShells;
        public final boolean scanPlayers;
        public final boolean smartMode;

        /**
         * Constructs a new RadarScanParams object.
         *
         * @param m Whether missiles should be scanned.
         * @param s Whether shells should be scanned.
         * @param p Whether players should be scanned.
         * @param smart Whether smart mode is enabled.
         */
        public RadarScanParams(boolean m, boolean s, boolean p, boolean smart) {
            this.scanMissiles = m;
            this.scanShells = s;
            this.scanPlayers = p;
            this.smartMode = smart;
        }
    }
}
