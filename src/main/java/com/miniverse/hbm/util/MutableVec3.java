package com.miniverse.hbm.util;

/**
 * A simple mutable vector class to hold impact position data.
 */
public class MutableVec3 {
    public double x;
    public double y;
    public double z;

    public MutableVec3() {
        this(0, 0, 0);
    }

    public MutableVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
