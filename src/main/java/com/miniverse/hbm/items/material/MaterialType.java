package com.miniverse.hbm.items.material;

public enum MaterialType {
    INGOT("_ingot"),
    NUGGET("_nugget"),
    BILLET("_billet"),
    WASTE("_waste"),
    WASTE_PLATE("_waste_plate"),
    PLATE_FUEL("_plate_fuel"),
    CRYSTAL("_crystal"),
    POWDER("_powder");

    private final String suffix;

    MaterialType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}