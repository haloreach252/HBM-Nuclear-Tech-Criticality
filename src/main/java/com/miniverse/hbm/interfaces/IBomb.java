package com.miniverse.hbm.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface IBomb {
    /////////////people
    //Months later I found this joke again
    //I'm not even sorry
    /**
     * Triggers the bomb and generates a return code. Since most bombs have a serverside inventory, the return code
     * should only be processed serverside; what's returned on the client should be ignored.
     * Often invoked by neighbor updates, so ensure server-side checks.
     *
     * @param level the Level instance
     * @param pos the BlockPos position of the bomb
     * @return the BombReturnCode result
     */
    BombReturnCode explode(Level level, BlockPos pos);

    enum BombReturnCode {
        UNDEFINED(false, ""),                                       // non-null type for clients that don't process the return code
        DETONATED(true, "bomb.detonated"),                          // successful detonation of bombs
        TRIGGERED(true, "bomb.triggered"),                          // successfully triggered other mechanisms
        LAUNCHED(true, "bomb.launched"),                            // missile successfully launched
        ERROR_MISSING_COMPONENT(false, "bomb.missingComponent"),    // missing required bomb components
        ERROR_INCOMPATIBLE(false, "bomb.incompatible"),             // incompatible target (implements IBomb incorrectly, e.g., locked doors)
        ERROR_NO_BOMB(false, "bomb.nobomb");                        // generic error when no bomb is present

        private final String unloc;
        private final boolean success;

        BombReturnCode(boolean success, String unloc) {
            this.unloc = unloc;
            this.success = success;
        }

        public String getUnlocalizedMessage() {
            return this.unloc;
        }

        public boolean wasSuccessful() {
            return this.success;
        }
    }
}
