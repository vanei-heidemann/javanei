package com.javanei.emulation.common.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vanei.heidemann
 *
 * @see https://pt.wikipedia.org/wiki/Lista_de_jogos_para_Sega_CD
 */
public class GameDeveloper implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, GameDeveloper> developers = new HashMap<>();
    private final String developer;

    static {
        developers.put("Absolute Entertainment", new GameDeveloper("Absolute Entertainment"));
        developers.put("Access Software", new GameDeveloper("Access Software"));
        developers.put("Activision", new GameDeveloper("Activision"));
    }

    private GameDeveloper(String developer) {
        this.developer = developer;
    }

    public static boolean isDeveloper(String name) {
        return developers.containsKey(name);
    }

    public static GameDeveloper fromName(String name) {
        GameDeveloper dev = developers.get(name);
        if (dev == null) {
            //TODO: Criar uma exception
            throw new IllegalArgumentException(name);
        }
        return dev;
    }

    public static GameDeveloper getDeveloper(String name) {
        return developers.get(name);
    }

    @Override
    public String toString() {
        return this.developer;
    }
}
