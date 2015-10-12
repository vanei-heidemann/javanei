package com.javanei.emulation.common.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vanei
 */
public class GameRegion implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, GameRegion> regions = new HashMap<>();
    private final String region;

    static {
        regions.put("USA", new GameRegion("USA")); //GoodSet
        regions.put("World", new GameRegion("World")); //GoodSet
        regions.put("USA, Europe", new GameRegion("USA, Europe")); //GoodSet
        regions.put("Japan, USA", new GameRegion("Japan, USA")); //GoodSet
        regions.put("Europe", new GameRegion("Europe")); //GoodSet
        regions.put("Japan & Korea", new GameRegion("Japan & Korea")); //GoodSet
        regions.put("Australia", new GameRegion("Australia")); //GoodSet
        regions.put("China", new GameRegion("China")); //GoodSet
        regions.put("France", new GameRegion("France")); //GoodSet
        regions.put("French Canadian", new GameRegion("French Canadian")); //GoodSet
        regions.put("Finland", new GameRegion("Finland")); //GoodSet
        regions.put("Germany", new GameRegion("Germany")); //GoodSet
        regions.put("Greece", new GameRegion("Greece")); //GoodSet
        regions.put("Hong Kong", new GameRegion("Hong Kong")); //GoodSet
        regions.put("Holland", new GameRegion("Holland")); //GoodSet
        regions.put("USA & BrazilNTSC", new GameRegion("USA & BrazilNTSC")); //GoodSet
        regions.put("Japan", new GameRegion("Japan")); //GoodSet
        regions.put("Korea", new GameRegion("Korea")); //GoodSet
        regions.put("Netherlands", new GameRegion("Netherlands")); //GoodSet
        regions.put("Public Domain", new GameRegion("Public Domain")); //GoodSet
        regions.put("Spain", new GameRegion("Spain")); //GoodSet
        regions.put("Sweden", new GameRegion("Sweden")); //GoodSet
        regions.put("England", new GameRegion("England")); //GoodSet
        regions.put("Italy", new GameRegion("Italy")); //GoodSet
        regions.put("non USA", new GameRegion("non USA")); //GoodSet
        regions.put("Asia", new GameRegion("Asia")); //GoodSet
        regions.put("Europe, Australia", new GameRegion("Europe, Australia")); //GoodSet
        regions.put("Unknown", new GameRegion("Unknown")); //GoodSet
        regions.put("Unlicensed", new GameRegion("Unlicensed")); //No-Intro
        //Unknown_Country("Unknown Country"), //GoodSet
    }

    private GameRegion(String region) {
        this.region = region;
    }

    public static boolean isRegion(String name) {
        return regions.containsKey(name);
    }

    public static GameRegion fromName(String name) {
        GameRegion reg = regions.get(name);
        if (reg == null) {
            //TODO: Criar uma exception
            throw new IllegalArgumentException(name);
        }
        return reg;
    }

    public static GameRegion getRegion(String name) {
        return regions.get(name);
    }

    @Override
    public String toString() {
        return this.region;
    }
}
