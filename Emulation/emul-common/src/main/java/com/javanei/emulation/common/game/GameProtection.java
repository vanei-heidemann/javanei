package com.javanei.emulation.common.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vanei
 */
public class GameProtection implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, GameProtection> protections = new HashMap<>();
    private final String protection;

    static {
        protections.put("RapidLok 1", new GameProtection("RapidLok 1")); //GoodSet
        protections.put("RapidLok 5", new GameProtection("RapidLok 5")); //GoodSet
        protections.put("RapidLok 6", new GameProtection("RapidLok 6")); //GoodSet
        protections.put("V-MAX", new GameProtection("V-MAX")); //GoodSet
        protections.put("V-MAX 2", new GameProtection("V-MAX 2")); //GoodSet
        protections.put("Vorpal", new GameProtection("Vorpal")); //GoodSet
        // Mindscape?
        // Datasoft?
    }

    private GameProtection(String protection) {
        this.protection = protection;
    }

    public static boolean isProtection(String names) {
        boolean result = true;
        for (String s : names.split(",")) {
            if (!protections.containsKey(s.trim())) {
                result = false;
            }
        }
        return result;
    }

    public static GameProtection fromName(String name) {
        GameProtection prot = protections.get(name);
        if (prot == null) {
            //TODO: Criar uma exception
            throw new IllegalArgumentException(name);
        }
        return prot;
    }

    public static GameProtection getProtection(String name) {
        return protections.get(name);
    }

    @Override
    public String toString() {
        return this.protection;
    }
}
