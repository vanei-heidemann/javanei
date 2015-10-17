package com.javanei.emulation.common.game.segacd;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Vanei
 */
public class SegaCDGame {

    private String name;
    private String developer;
    private String publisher;
    private String ntscj;
    private String ntsc;
    private String pal;
    private String year;

    private static final Map<String, SegaCDGame> games = new LinkedHashMap<>();

    static {
        games.put("3 Ninjas Kick Back", new SegaCDGame("3 Ninjas Kick Back", "Malibu Interactive", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("A-Rank Thunder Tanjouhen", new SegaCDGame("A-Rank Thunder Tanjouhen", "Riot", "Nihon Telenet", "true", null, null, "1993"));
        games.put("Adventures of Batman &amp; Robin, The", new SegaCDGame("Adventures of Batman &amp; Robin, The", "Clockwork Games", "Sega", null, "true", "true", "1995"));
        games.put("Adventures of Willy Beamish, The", new SegaCDGame("Adventures of Willy Beamish, The", "Sierra", "Dynamix", null, "true", null, "1993"));
        games.put("After Armageddon Gaiden", new SegaCDGame("After Armageddon Gaiden", "Micro Design", "Sega", "true", null, null, "1994"));
        games.put("After Burner III", new SegaCDGame("After Burner III", "CSK Research Institute", "Sega", "true", "true", "true", "1992"));
        games.put("Aisle Lord", new SegaCDGame("Aisle Lord", "Wolf Team", "Wolf Team", "true", null, null, "1992"));
        games.put("Alshark", new SegaCDGame("Alshark", "Popcorn Software", "Polydor", "true", null, null, "1993"));
        games.put("Amazing Spider-Man vs. The Kingpin, The", new SegaCDGame("Amazing Spider-Man vs. The Kingpin, The", "Sega", "Sega", null, "true", "true", "1993"));
        games.put("Android Assault: The Revenge of Bari-Arm", new SegaCDGame("Android Assault: The Revenge of Bari-Arm", "Human Entertainment", "Big Fun Games", "true", "true", null, "1994"));
        games.put("Anett Futatabi", new SegaCDGame("Anett Futatabi", "Wolf Team", "Wolf Team", "true", null, null, "1993"));
        games.put("Animals, The", new SegaCDGame("Animals, The", "Arnowitz Studios", "Software Toolworks", null, "true", "true", "1994"));
        games.put("Arcus 1-2-3", new SegaCDGame("Arcus 1-2-3", "Wolf Team", "Wolf Team", "true", null, null, "1993"));
        games.put("Arslan Senki", new SegaCDGame("Arslan Senki", "Sega", "Sega", "true", null, null, "1993"));
        games.put("A/X-101", new SegaCDGame("A/X-101", "Micronet", "Absolute Entertainment", "true", "true", null, "1994"));
        games.put("Bakuden: The Unbalanced Zone", new SegaCDGame("Bakuden: The Unbalanced Zone", "Sony Music Entertainment", "Sony Music Entertainment", "true", null, null, "1994"));
        games.put("Batman Returns", new SegaCDGame("Batman Returns", "Malibu Interactive", "Sega", null, "true", "true", "1993"));
        games.put("Battle Frenzy", new SegaCDGame("Battle Frenzy", "Domark Software", "Acclaim Entertainment", null, null, "true", "1995"));
        games.put("Battlecorps", new SegaCDGame("Battlecorps", "Core Design", "Victor Interactive Software", "true", "true", "true", "1993"));
        games.put("BC Racers", new SegaCDGame("BC Racers", "Core Design", "Time Warner Interactive", null, "true", "true", "1995"));
        games.put("Bill Walsh College Football", new SegaCDGame("Bill Walsh College Football", "High Score Productions", "Electronic Arts", null, "true", "true", "1993"));
        games.put("Black Hole Assault", new SegaCDGame("Black Hole Assault", "Micronet", "Bignet USA", "true", "true", "true", "1992"));
        games.put("Bouncers", new SegaCDGame("Bouncers", "Dynamix", "Sega", null, "true", null, "1995"));
        games.put("Bram Stoker's Dracula", new SegaCDGame("Bram Stoker's Dracula", "Psygnosis", "Sony Imagesoft", null, "true", null, "1993"));
        games.put("Brutal: Paws of Fury", new SegaCDGame("Brutal: Paws of Fury", "Imagitec Design", "GameTek", null, "true", "true", "1994"));
        games.put("Burai", new SegaCDGame("Burai", "Pandora's Box", "Sega", "true", null, null, "1992"));
        games.put("Cadillacs and Dinosaurs: The Second Cataclysm", new SegaCDGame("Cadillacs and Dinosaurs: The Second Cataclysm", "Capcom", "Rocket Science Games", null, "true", null, "1994"));
        games.put("Capcom no Quiz: Tonosama no Yabou", new SegaCDGame("Capcom no Quiz: Tonosama no Yabou", "Sims Computing Inc.", "Capcom", "true", null, null, "1992"));
        games.put("Captain Tsubasa", new SegaCDGame("Captain Tsubasa", "Tecmo", "Tecmo", "true", null, null, "1994"));
        games.put("Sensible Soccer", new SegaCDGame("Sensible Soccer", "Sensible Software", "Sony Imagesoft", null, "true", "true", "1993"));
        games.put("Chuck Rock", new SegaCDGame("Chuck Rock", "Core Design", "Sony Imagesoft", null, "true", "true", "1992"));
        games.put("Chuck Rock II: Son of Chuck", new SegaCDGame("Chuck Rock II: Son of Chuck", "Core Design", "Virgin Games", null, "true", "true", "1992"));
        games.put("Cliffhanger", new SegaCDGame("Cliffhanger", "Malibu Interactive", "Sony Imagesoft", null, "true", null, "1993"));
        games.put("Cobra Command", new SegaCDGame("Cobra Command", "Wolf Team", "Sega", "true", "true", "true", "1992"));
        games.put("Colors of Modern Rock", new SegaCDGame("Colors of Modern Rock", "Sega", "Sega", null, "true", null, "1993"));
        games.put("Compton's Interactive Encyclopedia", new SegaCDGame("Compton's Interactive Encyclopedia", "The Learning Company", "Sega", null, "true", null, "1994"));
        games.put("Corpse Killer", new SegaCDGame("Corpse Killer", "Digital Pictures", "Sega", null, "true", "true", "1994"));
        games.put("Cosmic Fantasy Stories", new SegaCDGame("Cosmic Fantasy Stories", "Riot", "Nihon Telenet", "true", null, null, "1992"));
        games.put("Crime Patrol", new SegaCDGame("Crime Patrol", "American Laser Games", "American Laser Games", null, "true", null, "1994"));
        games.put("Cyborg 009", new SegaCDGame("Cyborg 009", "Riot", "Nihon Telenet", "true", null, null, "1993"));
        games.put("Daihoushinden", new SegaCDGame("Daihoushinden", "Victor Interactive Software", "Sega", "true", null, null, "1994"));
        games.put("Dark Wizard", new SegaCDGame("Dark Wizard", "Renovation Products", "Sega", "true", "true", null, "1993"));
        games.put("Death Bringer: The Knight of Darkness", new SegaCDGame("Death Bringer: The Knight of Darkness", "Riot", "Nihon Telenet", "true", null, null, "1992"));
        games.put("Demolition Man", new SegaCDGame("Demolition Man", "Alexandria, Inc.", "Acclaim Entertainment", null, "true", null, "1995"));
        games.put("Detonator Organ", new SegaCDGame("Detonator Organ", "Hot B", "Hot B", "true", null, null, "1992"));
        games.put("Devastator", new SegaCDGame("Devastator", "Wolf Team", "Wolf Team", "true", null, null, "1993"));
        games.put("Double Switch", new SegaCDGame("Double Switch", "Digital Pictures", "Sega", "true", "true", "true", "1993"));
        games.put("Dracula Unleashed", new SegaCDGame("Dracula Unleashed", "ICOM Simulations", "Sega", null, "true", "true", "1993"));
        games.put("Dragon's Lair", new SegaCDGame("Dragon's Lair", "Don Bluth Studios", "Readysoft", "true", "true", "true", "1994"));
        games.put("Dune", new SegaCDGame("Dune", "Cryo Interactive", "Virgin Interactive", null, "true", "true", "1993"));
        games.put("Dungeon Explorer", new SegaCDGame("Dungeon Explorer", "Hudson Soft", "Sega", null, "true", "true", "1994"));
        games.put("Dungeon Master II: The Legend of Skullkeep", new SegaCDGame("Dungeon Master II: The Legend of Skullkeep", "JVC", "JVC", "true", "true", "true", "1994"));
        games.put("Dynamic Country Club", new SegaCDGame("Dynamic Country Club", "Sega", "Sega", "true", null, null, "1993"));
        games.put("Earnest Evans", new SegaCDGame("Earnest Evans", "Wolf Team", "Nihon Telenet", "true", null, null, "1991"));
        games.put("Earthworm Jim: Special Edition", new SegaCDGame("Earthworm Jim: Special Edition", "Shiny Entertainment", "Interplay", null, "true", "true", "1995"));
        games.put("Ecco the Dolphin", new SegaCDGame("Ecco the Dolphin", "Novotrade", "Sega", "true", "true", "true", "1993"));
        games.put("Ecco: The Tides of Time", new SegaCDGame("Ecco: The Tides of Time", "Novotrade", "Sega", "true", "true", "true", "1994"));
        games.put("Egawa Suguro no Super League", new SegaCDGame("Egawa Suguro no Super League", "Hudson Soft", "Sega", "true", null, null, "1993"));
        games.put("ESPN Baseball Tonight", new SegaCDGame("ESPN Baseball Tonight", "Park Place Productions", "Sony Imagesoft", null, "true", "true", "1994"));
        games.put("ESPN National Hockey Night", new SegaCDGame("ESPN National Hockey Night", "Park Place Productions", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("ESPN NBA HangTime '95", new SegaCDGame("ESPN NBA HangTime '95", "Sony Imagesoft", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("ESPN Sunday Night NFL", new SegaCDGame("ESPN Sunday Night NFL", "Ringler Studios", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("Eternal Champions: Challenge from the Dark Side", new SegaCDGame("Eternal Champions: Challenge from the Dark Side", "Sega Interactive", "Sega", null, "true", "true", "1994"));
        games.put("Eye of the Beholder", new SegaCDGame("Eye of the Beholder", "Westwood Associates", "Strategic Simulations, Inc.", "true", "true", "true", "1994"));
        games.put("F-1 Circus CD", new SegaCDGame("F-1 Circus CD", "Nihon Bussan", "Nichibutsu", "true", null, null, "1994"));
        games.put("Fahrenheit", new SegaCDGame("Fahrenheit", "Infogrames", "Sega", "true", "true", "true", "1995"));
        games.put("Fatal Fury Special", new SegaCDGame("Fatal Fury Special", "SNK", "JVC", "true", "true", "true", "1995"));
        games.put("Fhey Area", new SegaCDGame("Fhey Area", "Wolf Team", "Wolf Team", "true", null, null, "1992"));
        games.put("FIFA International Soccer", new SegaCDGame("FIFA International Soccer", "Extended Play Productions", "Electronic Arts", null, "true", "true", "1994"));
        games.put("Final Fight CD", new SegaCDGame("Final Fight CD", "Capcom", "Sega", "true", "true", "true", "1993"));
        games.put("Flashback: The Quest for Identity", new SegaCDGame("Flashback: The Quest for Identity", "Delphine Software", "U.S. Gold", null, "true", null, "1993"));
        games.put("Flink", new SegaCDGame("Flink", "Psygnosis", "Vic Tokai", null, "true", "true", "1994"));
        games.put("Formula One World Championship: Beyond the Limit", new SegaCDGame("Formula One World Championship: Beyond the Limit", "Fuji Television", "Sega", "true", "true", "true", "1994"));
        games.put("Frog Feast", new SegaCDGame("Frog Feast", "Rastersoft", "Oldergames", null, "true", null, "2005"));
        games.put("Funky Horror Band", new SegaCDGame("Funky Horror Band", "JVC", "Sega", "true", null, null, "1991"));
        games.put("Gambler Jikko Chuushinha 2", new SegaCDGame("Gambler Jikko Chuushinha 2", "Game Arts", "Game Arts", "true", null, null, "1992"));
        games.put("Game no Kanzume Vol. 1", new SegaCDGame("Game no Kanzume Vol. 1", "Sega", "Sega", "true", null, null, "1994"));
        games.put("Game no Kanzume Vol. 2", new SegaCDGame("Game no Kanzume Vol. 2", "Sega", "Sega", "true", null, null, "1994"));
        games.put("Genei Toshi: Illusion City", new SegaCDGame("Genei Toshi: Illusion City", "Micro Cabin", "Micro Cabin", "true", null, null, "1993"));
        games.put("Genghis Khan II: Clan of the Gray Wolf", new SegaCDGame("Genghis Khan II: Clan of the Gray Wolf", "Koei", "Koei", "true", null, null, "1993"));
        games.put("Ground Zero: Texas", new SegaCDGame("Ground Zero: Texas", "Digital Pictures", "Sony Imagesoft", null, "true", "true", "1993"));
        games.put("Heart of the Alien", new SegaCDGame("Heart of the Alien", "Delphine Software", "Virgin Interactive", null, "true", null, "1994"));
        games.put("Heavy Nova", new SegaCDGame("Heavy Nova", "Holocronet", "Micronet", "true", null, null, "1991"));
        games.put("Heimdall", new SegaCDGame("Heimdall", "Core Design", "JVC", "true", "true", null, "1994"));
        games.put("Hook", new SegaCDGame("Hook", "Core Design", "Sony Imagesoft", null, "true", "true", "1992"));
        games.put("Hot Hits", new SegaCDGame("Hot Hits", "Sega", "Sega", null, "true", null, "1992"));
        games.put("INXS: Make My Video", new SegaCDGame("INXS: Make My Video", "Digital Pictures", "Sega", null, "true", "true", "1992"));
        games.put("Iron Helix", new SegaCDGame("Iron Helix", "Drew Pictures", "Spectrum HoloByte", null, "true", null, "1994"));
        games.put("Ishii Hisaichi no Daisekai", new SegaCDGame("Ishii Hisaichi no Daisekai", "Sega", "Sega", "true", null, null, "1994"));
        games.put("Jaguar XJ220", new SegaCDGame("Jaguar XJ220", "Core Design", "JVC", "true", "true", "true", "1993"));
        games.put("Jango World Cup", new SegaCDGame("Jango World Cup", "JVC", "JVC", "true", null, null, "1993"));
        games.put("Jeopardy!", new SegaCDGame("Jeopardy!", "Absolute Entertainment", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("Joe Montana Football", new SegaCDGame("Joe Montana Football", "Electronic Arts", "Sega", null, "true", null, "1993"));
        games.put("Jurassic Park", new SegaCDGame("Jurassic Park", "Archer Communications", "Sega", "true", "true", "true", "1993"));
        games.put("Keio Flying Squadron", new SegaCDGame("Keio Flying Squadron", "Victor Entertainment", "Sega", "true", "true", "true", "1993"));
        games.put("Kids on Site", new SegaCDGame("Kids on Site", "Digital Pictures", "Sega", null, "true", "true", "1994"));
        games.put("Kris Kross: Make My Video", new SegaCDGame("Kris Kross: Make My Video", "Digital Pictures", "Sega", null, "true", "true", "1992"));
        games.put("Lawnmower Man, The", new SegaCDGame("Lawnmower Man, The", "Sales Curve", "Time Warner", null, "true", "true", "1994"));
        games.put("Lethal Enforcers", new SegaCDGame("Lethal Enforcers", "Konami", "Konami", "true", "true", "true", "1993"));
        games.put("Lethal Enforcers II: Gun Fighters", new SegaCDGame("Lethal Enforcers II: Gun Fighters", "Konami", "Konami", "true", "true", "true", "1994"));
        games.put("Links: The Challenge of Golf", new SegaCDGame("Links: The Challenge of Golf", "Access Software", "Virgin Interactive", null, "true", null, "1995"));
        games.put("Loadstar: The Legend of Tully Bodine", new SegaCDGame("Loadstar: The Legend of Tully Bodine", "Rocket Science Games", "Rocket Science Games", null, "true", null, "1994"));
        games.put("Lords of Thunder", new SegaCDGame("Lords of Thunder", "Hudson Soft", "Sega", null, "true", "true", "1995"));
        games.put("Lunar: Eternal Blue", new SegaCDGame("Lunar: Eternal Blue", "Game Arts", "Working Designs", "true", "true", null, "1994"));
        games.put("Lunar: The Silver Star", new SegaCDGame("Lunar: The Silver Star", "Game Arts", "Working Designs", "true", "true", null, "1992"));
        games.put("Mad Dog McCree", new SegaCDGame("Mad Dog McCree", "American Laser Games", "American Laser Games", null, "true", null, "1993"));
        games.put("Mad Dog II: The Lost Gold", new SegaCDGame("Mad Dog II: The Lost Gold", "American Laser Games", "American Laser Games", null, "true", null, "1994"));
        games.put("Mahou no Shoujo: Silky Lip", new SegaCDGame("Mahou no Shoujo: Silky Lip", "Riot", "Nihon Telenet", "true", null, null, "1992"));
        games.put("Mansion of Hidden Souls", new SegaCDGame("Mansion of Hidden Souls", "System Sacom", "Vic Tokai", "true", "true", "true", "1993"));
        games.put("Marky Mark and the Funky Bunch: Make My Video", new SegaCDGame("Marky Mark and the Funky Bunch: Make My Video", "Digital Pictures", "Sega", null, "true", null, "1992"));
        games.put("Mary Shelley's Frankenstein", new SegaCDGame("Mary Shelley's Frankenstein", "Psygnosis", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("Masked Rider", new SegaCDGame("Masked Rider", "Toei Animation", "Toei Animation", "true", "true", null, "1994"));
        games.put("MegaRace", new SegaCDGame("MegaRace", "Cryo Interactive", "Software Toolworks", null, "true", null, "1994"));
        games.put("Mega Schwarzschild", new SegaCDGame("Mega Schwarzschild", "Kogado Studio", "Sega", "true", null, null, "1993"));
        games.put("Mickey Mania: The Timeless Adventures of Mickey Mouse", new SegaCDGame("Mickey Mania: The Timeless Adventures of Mickey Mouse", "Traveller's Tales", "Sony Imagesoft", null, "true", "true", "1994"));
        games.put("Microcosm", new SegaCDGame("Microcosm", "Psygnosis", "Sony Imagesoft", "true", "true", "true", "1994"));
        games.put("Midnight Raiders", new SegaCDGame("Midnight Raiders", "Stargate Productions", "Sega", null, "true", "true", "1994"));
        games.put("Might and Magic III: Isles of Terra", new SegaCDGame("Might and Magic III: Isles of Terra", "New World Computing", "CSK", "true", null, null, "1993"));
        games.put("Mighty Morphin Power Rangers", new SegaCDGame("Mighty Morphin Power Rangers", "Saban Entertainment", "Sega", null, "true", "true", "1994"));
        games.put("Mortal Kombat", new SegaCDGame("Mortal Kombat", "Midway Games", "Arena Entertainment", "true", "true", "true", "1994"));
        games.put("My Paint: The Animated Paint Program", new SegaCDGame("My Paint: The Animated Paint Program", "Saddleback Graphics", "Bridgestone Multimedia", null, "true", null, "1993"));
        games.put("NBA Jam", new SegaCDGame("NBA Jam", "Midway Games", "Acclaim Entertainment", "true", "true", "true", "1993"));
        games.put("NFL Football Trivia Challenge", new SegaCDGame("NFL Football Trivia Challenge", "CapDisc", "Phillips Interactive", null, "true", null, "1994"));
        games.put("NFL's Greatest: San Francisco vs. Dallas 1978-1993", new SegaCDGame("NFL's Greatest: San Francisco vs. Dallas 1978-1993", "Park Place Productions", "Sega", null, "true", null, "1993"));
        games.put("NHL '94", new SegaCDGame("NHL '94", "High Score Productions", "Electronic Arts", null, "true", "true", "1993"));
        games.put("Night Striker", new SegaCDGame("Night Striker", "Taito", "Taito", "true", null, null, "1993"));
        games.put("Night Trap", new SegaCDGame("Night Trap", "Digital Pictures", "Sega", "true", "true", "true", "1992"));
        games.put("Ninja Warriors, The", new SegaCDGame("Ninja Warriors, The", "Natsume", "Taito", "true", null, null, "1993"));
        games.put("Nobunaga's Ambition 3: The Rising Sun", new SegaCDGame("Nobunaga's Ambition 3: The Rising Sun", "Koei", "Koei", "true", null, null, "1994"));
        games.put("Nostalgia 1907", new SegaCDGame("Nostalgia 1907", "Sur de Wave", "TakerUCO", "true", null, null, "1993"));
        games.put("Novastorm", new SegaCDGame("Novastorm", "Psygnosis", "Sony Imagesoft", null, "true", "true", "1993"));
        games.put("Panic!", new SegaCDGame("Panic!", "Data East", "Sega", "true", "true", null, "1993"));
        games.put("Pitfall: The Mayan Adventure", new SegaCDGame("Pitfall: The Mayan Adventure", "Activision", "Activision", null, "true", "true", "1994"));
        games.put("Popful Mail: Magical Fantasy Adventure", new SegaCDGame("Popful Mail: Magical Fantasy Adventure", "Falcom", "Working Designs", "true", "true", null, "1994"));
        games.put("Power Factory Featuring C+C Music Factory", new SegaCDGame("Power Factory Featuring C+C Music Factory", "Digital Pictures", "Sony Imagesoft", null, "true", "true", "1992"));
        games.put("Powermonger", new SegaCDGame("Powermonger", "Bullfrog Games", "Electronic Arts", null, "true", "true", "1994"));
        games.put("Prince of Persia", new SegaCDGame("Prince of Persia", "JVC", "Sega", "true", "true", "true", "1993"));
        games.put("Prize Fighter", new SegaCDGame("Prize Fighter", "Digital Pictures", "Sega", "true", "true", "true", "1995"));
        games.put("Pro Yakyuu Super League CD", new SegaCDGame("Pro Yakyuu Super League CD", "Hudson Soft", "Sega", "true", null, null, "1992"));
        games.put("Psychic Detective vol. 3: Psychic Detective Aya", new SegaCDGame("Psychic Detective vol. 3: Psychic Detective Aya", "DAPS", "Data West", "true", null, null, "1993"));
        games.put("Psychic Detective vol. 4: Psychic Detective Orgel", new SegaCDGame("Psychic Detective vol. 4: Psychic Detective Orgel", "DAPS", "Data West", "true", null, null, "1993"));
        games.put("Puggsy", new SegaCDGame("Puggsy", "Psygnosis", "Sony Imagesoft", null, "true", "true", "1993"));
        games.put("Quiz Scramble Special", new SegaCDGame("Quiz Scramble Special", "Sega", "Sega", "true", null, null, "1992"));
        games.put("Racing Aces", new SegaCDGame("Racing Aces", "Sega", "Sega", null, "true", null, "1993"));
        games.put("Radical Rex", new SegaCDGame("Radical Rex", "Beam Software", "Activision", null, "true", null, "1994"));
        games.put("Ranma ½: Byukuran Aika", new SegaCDGame("Ranma ½: Byukuran Aika", "NCS", "Masaya", "true", null, null, "1993"));
        games.put("RDF Global Conflict", new SegaCDGame("RDF Global Conflict", "Imagineering", "Absolute Entertainment", null, "true", null, "1995"));
        games.put("Record of Lodoss War", new SegaCDGame("Record of Lodoss War", "JASPAC", "Sega", "true", null, null, "1994"));
        games.put("Revenge of the Ninja", new SegaCDGame("Revenge of the Ninja", "Wolf Team", "Renovation Products", null, "true", null, "1993"));
        games.put("Revengers of Vengeance", new SegaCDGame("Revengers of Vengeance", "Micronet", "Extreme Entertainment", "true", "true", null, "1993"));
        games.put("Rise of the Dragon", new SegaCDGame("Rise of the Dragon", "Dynamix", "Sega", "true", "true", null, "1992"));
        games.put("Road Avenger", new SegaCDGame("Road Avenger", "Wolf Team", "Renovation Products", "true", "true", "true", "1992"));
        games.put("Road Rash", new SegaCDGame("Road Rash", "New Level Software, Inc.", "Electronic Arts", null, "true", null, "1995"));
        games.put("Robo Aleste", new SegaCDGame("Robo Aleste", "Compile", "Tengen", "true", "true", "true", "1992"));
        games.put("Romance of the Three Kingdoms III: Dragon of Destiny", new SegaCDGame("Romance of the Three Kingdoms III: Dragon of Destiny", "Koei", "Koei", "true", null, null, "1993"));
        games.put("Samurai Shodown", new SegaCDGame("Samurai Shodown", "SNK", "JVC", "true", "true", "true", "1995"));
        games.put("Secret of Monkey Island, The", new SegaCDGame("Secret of Monkey Island, The", "LucasArts", "JVC", "true", "true", null, "1993"));
        games.put("Sega Classics Arcade Collection", new SegaCDGame("Sega Classics Arcade Collection", "Sega", "Sega", "true", "true", "true", "1993"));
        games.put("Sega Classics Arcade Collection", new SegaCDGame("Sega Classics Arcade Collection", "Sega", "Sega", "true", "true", "true", "1993"));
        games.put("Seima Densetsu 3×3 Eyes", new SegaCDGame("Seima Densetsu 3×3 Eyes", "Sega", "Sega", "true", null, null, "1993"));
        games.put("Sengoku Denshou", new SegaCDGame("Sengoku Denshou", "SNK", "Sammy Studios", "true", null, null, "1993"));
        games.put("Sewer Shark", new SegaCDGame("Sewer Shark", "Digital Pictures", "Sony Imagesoft", null, "true", "true", "1992"));
        games.put("Shadow of the Beast II", new SegaCDGame("Shadow of the Beast II", "Psygnosis", "JVC", "true", "true", "true", "1994"));
        games.put("Shadowrun", new SegaCDGame("Shadowrun", "Compile", "FASA Interactive", "true", null, null, "1996"));
        games.put("Sherlock Holmes: Consulting Detective", new SegaCDGame("Sherlock Holmes: Consulting Detective", "ICOM Simulations", "Sega", null, "true", "true", "1992"));
        games.put("Sherlock Holmes: Consulting Detective Vol. II", new SegaCDGame("Sherlock Holmes: Consulting Detective Vol. II", "ICOM Simulations", "Sega", null, "true", "true", "1992"));
        games.put("Shin Megami Tensei", new SegaCDGame("Shin Megami Tensei", "Atlus Software", "SIMS", "true", null, null, "1994"));
        games.put("Shining Force CD", new SegaCDGame("Shining Force CD", "Sonic Co.", "Sega", "true", "true", "true", "1994"));
        games.put("Silpheed", new SegaCDGame("Silpheed", "Game Arts", "Sega", "true", "true", "true", "1993"));
        games.put("SimEarth", new SegaCDGame("SimEarth", "Maxis", "Sega", "true", null, null, "1993"));
        games.put("Slam City with Scottie Pippen", new SegaCDGame("Slam City with Scottie Pippen", "Digital Pictures", "Acclaim Entertainment", null, "true", "true", "1992"));
        games.put("Smurfs, The", new SegaCDGame("Smurfs, The", "Virtual Studio", "Infogrames", null, null, "true", "1995"));
        games.put("Snatcher", new SegaCDGame("Snatcher", "Konami", "Konami", "true", "true", "true", "1994"));
        games.put("Sol-Feace", new SegaCDGame("Sol-Feace", "Wolf Team", "Sega", "true", "true", "true", "1991"));
        games.put("Sonic the Hedgehog CD", new SegaCDGame("Sonic the Hedgehog CD", "Sonic Team", "Sega", "true", "true", "true", "1993"));
        games.put("Soulstar", new SegaCDGame("Soulstar", "Core Design", "Time Warner Interactive", "true", "true", "true", "1994"));
        games.put("Space Ace", new SegaCDGame("Space Ace", "Epicenter Interactive", "Readysoft", "true", "true", "true", "1993"));
        games.put("Space Adventure - Cobra, The: The Legendary Bandit", new SegaCDGame("Space Adventure - Cobra, The: The Legendary Bandit", "Hudson Soft", "Sega", "true", "true", "true", "1995"));
        games.put("Starblade", new SegaCDGame("Starblade", "Namco", "Namco", "true", "true", "true", "1994"));
        games.put("Star Wars Chess", new SegaCDGame("Star Wars Chess", "Software Toolworks", "Software Toolworks", "true", "true", "true", "1993"));
        games.put("Star Wars: Rebel Assault", new SegaCDGame("Star Wars: Rebel Assault", "LucasArts", "JVC", "true", "true", "true", "1994"));
        games.put("Stellar Fire", new SegaCDGame("Stellar Fire", "Sierra On-Line", "Dynamix", "true", "true", "true", "1993"));
        games.put("Supreme Warrior", new SegaCDGame("Supreme Warrior", "Digital Pictures", "Digital Pictures", null, "true", "true", "1994"));
        games.put("Surgical Strike", new SegaCDGame("Surgical Strike", "The Code Monkeys", "Sega", "true", "true", "true", "1995"));
        games.put("Syndicate", new SegaCDGame("Syndicate", "Bullfrog Games", "Electronic Arts", null, null, "true", "1993"));
        games.put("Tenbu: Mega CD Special", new SegaCDGame("Tenbu: Mega CD Special", "Wolf Team", "Wolf Team", "true", null, null, "1992"));
        games.put("Tenka Fubu", new SegaCDGame("Tenka Fubu", "Game Arts", "Game Arts", "true", null, null, "1991"));
        games.put("Terminator, The", new SegaCDGame("Terminator, The", "Probe Software", "Virgin Games", "true", "true", "true", "1994"));
        games.put("Theme Park", new SegaCDGame("Theme Park", "Bullfrog Games", "Electronic Arts", null, null, "true", "1995"));
        games.put("Third World War, The", new SegaCDGame("Third World War, The", "Micronet", "Extreme Entertainment", "true", "true", null, "1993"));
        games.put("Thunderhawk", new SegaCDGame("Thunderhawk", "Core Design", "JVC Musical Industries", "true", "true", "true", "1993"));
        games.put("Time Gal", new SegaCDGame("Time Gal", "Wolf Team", "Renovation Products", "true", "true", "true", "1992"));
        games.put("Tomcat Alley", new SegaCDGame("Tomcat Alley", "The Code Monkeys", "Sega", "true", "true", "true", "1994"));
        games.put("Trivial Pursuit", new SegaCDGame("Trivial Pursuit", "Western Technologies", "Virgin Games", null, "true", null, "1994"));
        games.put("Ultraverse Prime", new SegaCDGame("Ultraverse Prime", "Malibu Interactive", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("Urusei Yatsura: My Dear Friends", new SegaCDGame("Urusei Yatsura: My Dear Friends", "Game Arts", "Game Arts", "true", null, null, "1994"));
        games.put("Vay", new SegaCDGame("Vay", "SIMS", "Working Designs", "true", "true", null, "1993"));
        games.put("Warau Salesman", new SegaCDGame("Warau Salesman", "Sega", "Sega", "true", null, null, "1993"));
        games.put("Wheel of Fortune", new SegaCDGame("Wheel of Fortune", "Sony Imagesoft", "Sony Imagesoft", null, "true", null, "1994"));
        games.put("Who Shot Johnny Rock?", new SegaCDGame("Who Shot Johnny Rock?", "American Laser Games", "American Laser Games", null, "true", null, "1993"));
        games.put("Wild Woody", new SegaCDGame("Wild Woody", "Sega", "Sega", "true", "true", null, "1995"));
        games.put("Wing Commander", new SegaCDGame("Wing Commander", "Origin Systems", "Sega", "true", "true", null, "1994"));
        games.put("Winning Post", new SegaCDGame("Winning Post", "Koei", "Koei", "true", null, null, "1993"));
        games.put("Wirehead", new SegaCDGame("Wirehead", "The Code Monkeys", "Sega", null, "true", null, "1995"));
        games.put("Wolfchild", new SegaCDGame("Wolfchild", "Core Design", "JVC", "true", "true", "true", "1993"));
        games.put("Wonder Dog", new SegaCDGame("Wonder Dog", "Core Design", "JVC", "true", "true", "true", "1992"));
        games.put("Wondermega Collection", new SegaCDGame("Wondermega Collection", "Sega", "JVC", "true", null, null, "1992"));
        games.put("World Cup USA '94", new SegaCDGame("World Cup USA '94", "Tiertex Design Studios", "U.S. Gold", null, "true", "true", "1994"));
        games.put("WWF Rage in the Cage", new SegaCDGame("WWF Rage in the Cage", "Sculptured Software", "Acclaim Entertainment", "true", "true", "true", "1993"));
        games.put("Yumimi Mix", new SegaCDGame("Yumimi Mix", "Game Arts", "Game Arts", "true", null, null, "1993"));
    }

    public SegaCDGame() {
    }

    public SegaCDGame(String name, String developer, String publisher, String ntscj, String ntsc, String pal, String year) {
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.ntscj = ntscj;
        this.ntsc = ntsc;
        this.pal = pal;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getNtscj() {
        return ntscj;
    }

    public void setNtscj(String ntscj) {
        this.ntscj = ntscj;
    }

    public String getNtsc() {
        return ntsc;
    }

    public void setNtsc(String ntsc) {
        this.ntsc = ntsc;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
