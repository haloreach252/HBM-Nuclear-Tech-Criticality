package com.miniverse.hbm.interfaces;

// Helps me track which things need to be reimplemented in a way that needs
// some more boilerplate code (e.g. achievements in an achievement manager)
public @interface ITodo {
    public String value();
}
