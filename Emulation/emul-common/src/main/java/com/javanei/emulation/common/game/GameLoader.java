package com.javanei.emulation.common.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilizado no Commodore 64
 *
 * @author Vanei
 */
public class GameLoader implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, GameLoader> loaders = new HashMap<>();
    private final String loader;

    static {
        loaders.put("Ace Of Aces Loader", new GameLoader("Ace Of Aces Loader"));
        loaders.put("Anirog", new GameLoader("Anirog"));
        loaders.put("Atlantis Loader", new GameLoader("Atlantis Loader"));
        loaders.put("Audiogenic Loader", new GameLoader("Audiogenic Loader"));
        loaders.put("Bleepload", new GameLoader("Bleepload"));
        loaders.put("Burner", new GameLoader("Burner"));
        loaders.put("CHR Loader", new GameLoader("CHR Loader"));
        loaders.put("Cyberload", new GameLoader("Cyberload"));
        loaders.put("Cyberload+ROM Loader", new GameLoader("Cyberload+ROM Loader"));
        loaders.put("Enigma Loader", new GameLoader("Enigma Loader"));
        loaders.put("Firebird", new GameLoader("Firebird"));
        loaders.put("Flashload", new GameLoader("Flashload"));
        loaders.put("Freeload", new GameLoader("Freeload"));
        loaders.put("Graftgold", new GameLoader("Graftgold"));
        loaders.put("Hi TEC Loader", new GameLoader("Hi TEC Loader"));
        loaders.put("Hit-load", new GameLoader("Hit-load"));
        loaders.put("IK Tape Loader", new GameLoader("IK Tape Loader"));
        loaders.put("Invade-A-Load!", new GameLoader("Invade-A-Load!"));
        loaders.put("Jet-Load", new GameLoader("Jet-Load"));
        loaders.put("Load N Play", new GameLoader("Load N Play"));
        loaders.put("Martech", new GameLoader("Martech"));
        loaders.put("Mastertronic", new GameLoader("Mastertronic"));
        loaders.put("Microload", new GameLoader("Microload"));
        loaders.put("Novaload", new GameLoader("Novaload"));
        loaders.put("Ocean Loader (New)", new GameLoader("Ocean Loader (New)"));
        loaders.put("Ocean-Imagine Loader", new GameLoader("Ocean-Imagine Loader"));
        loaders.put("Palace Loader", new GameLoader("Palace Loader"));
        loaders.put("Pavloda", new GameLoader("Pavloda"));
        loaders.put("ROM Loader", new GameLoader("ROM Loader"));
        loaders.put("Rack-it", new GameLoader("Rack-it"));
        loaders.put("Rasterload", new GameLoader("Rasterload"));
        loaders.put("SEUCK Loader", new GameLoader("SEUCK Loader"));
        loaders.put("Snakeload", new GameLoader("Snakeload"));
        loaders.put("Super Pavloda", new GameLoader("Super Pavloda"));
        loaders.put("Super Tape", new GameLoader("Super Tape"));
        loaders.put("TDI Loader", new GameLoader("TDI Loader"));
        loaders.put("Tengen-Domark Loader", new GameLoader("Tengen-Domark Loader"));
        loaders.put("Turbo-Tape Compatible", new GameLoader("Turbo-Tape Compatible"));
        loaders.put("Turrican Loader", new GameLoader("Turrican Loader"));
        loaders.put("U.S. Gold & Cyberload Loaders", new GameLoader("U.S. Gold & Cyberload Loaders"));
        loaders.put("U.S. Gold Loader", new GameLoader("U.S. Gold Loader"));
        loaders.put("Unknown", new GameLoader("Unknown"));
        loaders.put("Virgin Loader", new GameLoader("Virgin Loader"));
        loaders.put("Visiload", new GameLoader("Visiload"));
        loaders.put("Wildsave", new GameLoader("Wildsave"));
    }

    private GameLoader(String loader) {
        this.loader = loader;
    }

    public static boolean isLoader(String name) {
        return loaders.containsKey(name);
    }

    public static GameLoader fromName(String name) {
        GameLoader load = loaders.get(name);
        if (load == null) {
            //TODO: Criar uma exception
            throw new IllegalArgumentException(name);
        }
        return load;
    }

    public static GameLoader getLoader(String name) {
        return loaders.get(name);
    }

    @Override
    public String toString() {
        return this.loader;
    }

}
