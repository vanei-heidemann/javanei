package com.javanei.emulation.emuldb.game;

/**
 * @author Vanei
 */
public enum GameRegion {

    Japan_Korea("Japan & Korea"), //GoodSet
    Australia("Australia"), //GoodSet
    China("China"), //GoodSet
    Europe("Europe"), //GoodSet
    France("France"), //GoodSet
    World("World"), //GoodSet
    French_Canadian("French Canadian"), //GoodSet
    Finland("Finland"), //GoodSet
    Germany("Germany"), //GoodSet
    Greece("Greece"), //GoodSet
    Hong_Kong("Hong Kong"), //GoodSet
    Holland("Holland"), //GoodSet
    USA_BrazilNTSC("USA & BrazilNTSC"), //GoodSet
    Japan("Japan"), //GoodSet
    Korea("Korea"), //GoodSet
    Netherlands("Netherlands"), //GoodSet
    Public_Domain("Public Domain"), //GoodSet
    Spain("Spain"), //GoodSet
    Sweden("Sweden"), //GoodSet
    USA("USA"), //GoodSet, No-Intro
    England("England"), //GoodSet
    Italy("Italy"), //GoodSet
    non_USA("non USA"), //GoodSet
    Unknown_Country("Unknown Country"), //GoodSet
    Unlicensed("Unlicensed"), //GoodSet
    Unknown("Unknown"); //No-Intro

    private final String name;

    private GameRegion(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
