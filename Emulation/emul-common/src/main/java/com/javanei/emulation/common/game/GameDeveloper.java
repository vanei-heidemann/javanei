package com.javanei.emulation.common.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vanei.heidemann
 *
 * @see https://pt.wikipedia.org/wiki/Lista_de_jogos_para_Sega_CD
 * @see https://en.wikipedia.org/wiki/List_of_Sega_CD_games
 * @see http://gamesdbase.com/list.aspx
 * @see http://www.oldgamesfinder.com/
 */
public class GameDeveloper implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, GameDeveloper> developers = new HashMap<>();
    private final String developer;

    static {
        developers.put("Absolute Entertainment", new GameDeveloper("Absolute Entertainment"));
        developers.put("Access Software", new GameDeveloper("Access Software"));
        developers.put("Activision", new GameDeveloper("Activision"));
        developers.put("Alexandria, Inc.", new GameDeveloper("Alexandria, Inc."));
        developers.put("American Laser Games", new GameDeveloper("American Laser Games"));
        developers.put("Archer Communications", new GameDeveloper("Archer Communications"));
        developers.put("Arnowitz Studios", new GameDeveloper("Arnowitz Studios"));
        developers.put("Atlus Software", new GameDeveloper("Atlus Software"));
        developers.put("Beam Software", new GameDeveloper("Beam Software"));
        developers.put("Bullfrog Games", new GameDeveloper("Bullfrog Games"));
        developers.put("CSK Research Institute", new GameDeveloper("CSK Research Institute"));
        developers.put("CapDisc", new GameDeveloper("CapDisc"));
        developers.put("Capcom", new GameDeveloper("Capcom"));
        developers.put("Clockwork Games", new GameDeveloper("Clockwork Games"));
        developers.put("Compile", new GameDeveloper("Compile"));
        developers.put("Core Design", new GameDeveloper("Core Design"));
        developers.put("Cryo Interactive", new GameDeveloper("Cryo Interactive"));
        developers.put("DAPS", new GameDeveloper("DAPS"));
        developers.put("Data East", new GameDeveloper("Data East"));
        developers.put("Delphine Software", new GameDeveloper("Delphine Software"));
        developers.put("Digital Pictures", new GameDeveloper("Digital Pictures"));
        developers.put("Domark Software", new GameDeveloper("Domark Software"));
        developers.put("Don Bluth Studios", new GameDeveloper("Don Bluth Studios"));
        developers.put("Drew Pictures", new GameDeveloper("Drew Pictures"));
        developers.put("Dynamix", new GameDeveloper("Dynamix"));
        developers.put("Electronic Arts", new GameDeveloper("Electronic Arts"));
        developers.put("Epicenter Interactive", new GameDeveloper("Epicenter Interactive"));
        developers.put("Extended Play Productions", new GameDeveloper("Extended Play Productions"));
        developers.put("Falcom", new GameDeveloper("Falcom"));
        developers.put("Fuji Television", new GameDeveloper("Fuji Television"));
        developers.put("Game Arts", new GameDeveloper("Game Arts"));
        developers.put("High Score Productions", new GameDeveloper("High Score Productions"));
        developers.put("Holocronet", new GameDeveloper("Holocronet"));
        developers.put("Hot B", new GameDeveloper("Hot B"));
        developers.put("Hudson Soft", new GameDeveloper("Hudson Soft"));
        developers.put("Human Entertainment", new GameDeveloper("Human Entertainment"));
        developers.put("ICOM Simulations", new GameDeveloper("ICOM Simulations"));
        developers.put("Imagineering", new GameDeveloper("Imagineering"));
        developers.put("Imagitec Design", new GameDeveloper("Imagitec Design"));
        developers.put("Infogrames", new GameDeveloper("Infogrames"));
        developers.put("JASPAC", new GameDeveloper("JASPAC"));
        developers.put("JVC", new GameDeveloper("JVC"));
        developers.put("Koei", new GameDeveloper("Koei"));
        developers.put("Kogado Studio", new GameDeveloper("Kogado Studio"));
        developers.put("Konami", new GameDeveloper("Konami"));
        developers.put("LucasArts", new GameDeveloper("LucasArts"));
        developers.put("Malibu Interactive", new GameDeveloper("Malibu Interactive"));
        developers.put("Maxis", new GameDeveloper("Maxis"));
        developers.put("Micro Cabin", new GameDeveloper("Micro Cabin"));
        developers.put("Micro Design", new GameDeveloper("Micro Design"));
        developers.put("Micronet", new GameDeveloper("Micronet"));
        developers.put("Midway Games", new GameDeveloper("Midway Games"));
        developers.put("NCS", new GameDeveloper("NCS"));
        developers.put("Namco", new GameDeveloper("Namco"));
        developers.put("Natsume", new GameDeveloper("Natsume"));
        developers.put("New Level Software, Inc.", new GameDeveloper("New Level Software, Inc."));
        developers.put("New World Computing", new GameDeveloper("New World Computing"));
        developers.put("Nihon Bussan", new GameDeveloper("Nihon Bussan"));
        developers.put("Novotrade", new GameDeveloper("Novotrade"));
        developers.put("Origin Systems", new GameDeveloper("Origin Systems"));
        developers.put("Pandora's Box", new GameDeveloper("Pandora's Box"));
        developers.put("Park Place Productions", new GameDeveloper("Park Place Productions"));
        developers.put("Popcorn Software", new GameDeveloper("Popcorn Software"));
        developers.put("Probe Software", new GameDeveloper("Probe Software"));
        developers.put("Psygnosis", new GameDeveloper("Psygnosis"));
        developers.put("Rastersoft", new GameDeveloper("Rastersoft"));
        developers.put("Renovation Products", new GameDeveloper("Renovation Products"));
        developers.put("Ringler Studios", new GameDeveloper("Ringler Studios"));
        developers.put("Riot", new GameDeveloper("Riot"));
        developers.put("Rocket Science Games", new GameDeveloper("Rocket Science Games"));
        developers.put("SIMS", new GameDeveloper("SIMS"));
        developers.put("SNK", new GameDeveloper("SNK"));
        developers.put("Saban Entertainment", new GameDeveloper("Saban Entertainment"));
        developers.put("Saddleback Graphics", new GameDeveloper("Saddleback Graphics"));
        developers.put("Sales Curve", new GameDeveloper("Sales Curve"));
        developers.put("Sculptured Software", new GameDeveloper("Sculptured Software"));
        developers.put("Sega", new GameDeveloper("Sega"));
        developers.put("Sega Interactive", new GameDeveloper("Sega Interactive"));
        developers.put("Sensible Software", new GameDeveloper("Sensible Software"));
        developers.put("Shiny Entertainment", new GameDeveloper("Shiny Entertainment"));
        developers.put("Sierra", new GameDeveloper("Sierra"));
        developers.put("Sierra On-Line", new GameDeveloper("Sierra On-Line"));
        developers.put("Sims Computing Inc.", new GameDeveloper("Sims Computing Inc."));
        developers.put("Software Toolworks", new GameDeveloper("Software Toolworks"));
        developers.put("Sonic Co.", new GameDeveloper("Sonic Co."));
        developers.put("Sonic Team", new GameDeveloper("Sonic Team"));
        developers.put("Sony Imagesoft", new GameDeveloper("Sony Imagesoft"));
        developers.put("Sony Music Entertainment", new GameDeveloper("Sony Music Entertainment"));
        developers.put("Stargate Productions", new GameDeveloper("Stargate Productions"));
        developers.put("Sur de Wave", new GameDeveloper("Sur de Wave"));
        developers.put("System Sacom", new GameDeveloper("System Sacom"));
        developers.put("Taito", new GameDeveloper("Taito"));
        developers.put("Tecmo", new GameDeveloper("Tecmo"));
        developers.put("The Code Monkeys", new GameDeveloper("The Code Monkeys"));
        developers.put("The Learning Company", new GameDeveloper("The Learning Company"));
        developers.put("Tiertex Design Studios", new GameDeveloper("Tiertex Design Studios"));
        developers.put("Toei Animation", new GameDeveloper("Toei Animation"));
        developers.put("Traveller's Tales", new GameDeveloper("Traveller's Tales"));
        developers.put("Victor Entertainment", new GameDeveloper("Victor Entertainment"));
        developers.put("Victor Interactive Software", new GameDeveloper("Victor Interactive Software"));
        developers.put("Virtual Studio", new GameDeveloper("Virtual Studio"));
        developers.put("Western Technologies", new GameDeveloper("Western Technologies"));
        developers.put("Westwood Associates", new GameDeveloper("Westwood Associates"));
        developers.put("Wolf Team", new GameDeveloper("Wolf Team"));
        // Commodore
        developers.put("Software Country", new GameDeveloper("Software Country"));
        // NES
        developers.put("Hwang Shinwei", new GameDeveloper("Hwang Shinwei"));
        // Game Boy
        developers.put("Sachen", new GameDeveloper("Sachen"));
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
