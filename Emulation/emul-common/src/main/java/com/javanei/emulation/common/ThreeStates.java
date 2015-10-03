package com.javanei.emulation.common;

/**
 * @author Vanei
 */
public enum ThreeStates {

    True("true"),
    False("false"),
    Unknown("unknown");

    private final String name;

    private ThreeStates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
