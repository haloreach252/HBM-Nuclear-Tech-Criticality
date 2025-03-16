package com.miniverse.hbm.items;

import com.miniverse.hbm.items.weapon.ItemAmmo.AmmoItemTrait;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class ItemAmmoEnums {

    public enum AmmoFireExt implements IAmmoItemEnum {
        WATER("ammo_fireext"),
        FOAM("ammo_fireext_foam"),
        SAND("ammo_fireext_sand");

        private final Set<AmmoItemTrait> traits;
        private final String unloc;

        private AmmoFireExt(String unloc, AmmoItemTrait... traits) {
            this.traits = safeAssign(traits);
            this.unloc = unloc;
        }

        @Override
        public Set<AmmoItemTrait> getTraits() {
            return traits;
        }

        @Override
        public String getInternalName() {
            return unloc;
        }
    }

    public enum AmmoMisc implements IAmmoItemEnum {
        DGK("ammo_dkg");

        private final Set<AmmoItemTrait> traits;
        private final String unloc;

        private AmmoMisc(String unloc, AmmoItemTrait... traits) {
            this.traits = safeAssign(traits);
            this.unloc = unloc;
        }

        @Override
        public Set<AmmoItemTrait> getTraits() {
            return traits;
        }

        @Override
        public String getInternalName() {
            return unloc;
        }
    }

    public enum Ammo240Shell implements IAmmoItemEnum {
        STOCK("ammo_shell"),
        EXPLOSIVE("ammo_shell_explosive"),
        APFSDS_T("ammo_shell_apfsds_t"),
        APFSDS_DU("ammo_shell_apfsds_du"),
        W9("ammo_shell_w9");

        private final Set<AmmoItemTrait> traits;
        private final String unloc;

        private Ammo240Shell(String unloc, AmmoItemTrait... traits) {
            this.traits = safeAssign(traits);
            this.unloc = unloc;
        }

        @Override
        public Set<AmmoItemTrait> getTraits() {
            return traits;
        }

        @Override
        public String getInternalName() {
            return unloc;
        }
    }

    public interface IAmmoItemEnum {
        public Set<AmmoItemTrait> getTraits();
        public String getInternalName();
    }

    static Set<AmmoItemTrait> safeAssign(AmmoItemTrait[] traits) {
        return traits == null ? ImmutableSet.of() : ImmutableSet.copyOf(traits);
    }

}
