package com.javanei.emulation.common.game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
        languages.put("Hr", new GameLanguage("Hr"));
        languages.put("Pt", new GameLanguage("Pt"));
        languages.put("Pl", new GameLanguage("Pl"));
        languages.put("Zh", new GameLanguage("Zh"));
        languages.put("Fi", new GameLanguage("Fi"));
        languages.put("Ca", new GameLanguage("Ca"));
        languages.put("Ko", new GameLanguage("Ko"));
    }

    public GameLanguage(String lang) {
        this.language = lang;
    }

    public static boolean isLanguage(String name) {
        return languages.containsKey(name);
    }

    public static boolean isLanguages(String langList) {
        boolean result = langList != null && !langList.trim().isEmpty();
        if (result) {
            String[] ss = langList.split(",");
            for (String s : ss) {
                if (s.indexOf("+") > 0) {
                    String[] ss2 = s.split("\\+");
                    for (String s2 : ss2) {
                        if (!isLanguage(s2.trim())) {
                            result = false;
                            break;
                        }
                    }
                } else {
                    if (!isLanguage(s.trim())) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static GameLanguage fromName(String name) {
        GameLanguage lang = languages.get(name);
        if (lang == null) {
            //TODO: Criar uma exception
            throw new IllegalArgumentException(name);
        }
        return lang;
    }

    public static List<GameLanguage> fromNames(String langList) {
        String[] ss = langList.split(",");
        List<GameLanguage> result = new LinkedList<>();
        for (String s : ss) {
            if (s.indexOf("+") > 0) {
                String[] ss2 = s.split("\\+");
                for (String s2 : ss2) {
                    GameLanguage gl = fromName(s2.trim());
                    if (!result.contains(gl)) {
                        result.add(gl);
                    }
                }
            } else {
                GameLanguage gl = fromName(s.trim());
                if (!result.contains(gl)) {
                    result.add(gl);
                }
            }
        }
        return result;
    }

    public static GameLanguage getLanguage(String name) {
        return languages.get(name);
    }

    @Override
    public String toString() {
        return this.language;
    }
}
