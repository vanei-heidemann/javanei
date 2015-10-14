package com.javanei.emulation.common.game;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vanei.heidemann
 */
public class GameLanguage {

    private static final long serialVersionUID = 1L;

    private static final Map<String, GameLanguage> languages = new HashMap<>();
    private final String language;

    static {
        languages.put("En", new GameLanguage("En"));
        languages.put("Fr", new GameLanguage("Fr"));
        languages.put("De", new GameLanguage("De"));
        languages.put("Es", new GameLanguage("Es"));
        languages.put("It", new GameLanguage("It"));
        languages.put("Sv", new GameLanguage("Sv"));
        languages.put("No", new GameLanguage("No"));
        languages.put("Da", new GameLanguage("Da"));
        languages.put("Nl", new GameLanguage("Nl"));
        languages.put("Gd", new GameLanguage("Gd"));
        languages.put("Ja", new GameLanguage("Ja"));
    }

    public GameLanguage(String lang) {
        this.language = lang;
    }

    public static boolean isLanguage(String name) {
        return languages.containsKey(name);
    }

    public static GameLanguage fromName(String name) {
        GameLanguage lang = languages.get(name);
        if (lang == null) {
            //TODO: Criar uma exception
            throw new IllegalArgumentException(name);
        }
        return lang;
    }

    public static GameLanguage getLanguage(String name) {
        return languages.get(name);
    }

    @Override
    public String toString() {
        return this.language;
    }
}
