package com.miniverse.hbm.interfaces;

// Universal annotation for temporary classes.
// This can be used for things where I'm testing a system, but other systems
// aren't developed. I.e. the current radioactivity hazard system hasn't been
// ported, but I want to make sure I can generate a bunch of material types
// for each material using a temporary RadioactiveItem class.
public @interface ITemporary {
    public String value();
}
