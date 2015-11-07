package com.javanei.emulation.emuldb.nointro;

import com.javanei.emulation.common.ThreeStates;
import com.javanei.emulation.common.game.GameDeveloper;
import com.javanei.emulation.common.game.GameLanguage;
import com.javanei.emulation.common.game.GamePublisher;
import com.javanei.emulation.common.game.GameRegion;
import com.javanei.emulation.emuldb.GameNameParser;
import com.javanei.emulation.emuldb.game.Game;
import com.javanei.emulation.emuldb.game.GameImporterMessage;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vanei
 */
public final class NoIntroNameParser implements GameNameParser {

    private final List<GameImporterMessage> messages = new LinkedList<>();

    @Override
    public final List<GameImporterMessage> parseGameName(String platform, Game game) throws Exception {
        String s = game.getName();
        int pos = s.indexOf("(");
        if (pos < 0) {
            game.setMainName(game.getName());
            return this.messages;
        }
        String mainName = s.substring(0, pos).trim();
        while (pos >= 0) {
            int endPos = s.indexOf(")", pos);
            String tag = s.substring(pos + 1, endPos);
            validate_block:
            {
                // Identifica a região
                if (game.getRegions().isEmpty()) {
                    if (GameRegion.isRegion(tag)) {
                        game.setRegions(GameRegion.fromNames(tag));
                        break validate_block;
                    } else {
                        mainName = s.substring(0, pos).trim();
                        break validate_block;
                    }
                }
                // Identifica o publisher
                if (parsePublisher(game, tag)) {
                    break validate_block;
                }
                // Identifica o developer
                if (parseDeveloper(game, tag)) {
                    break validate_block;
                }
                // Identifica a linguagem
                if (parseLanguage(game, tag)) {
                    break validate_block;
                }
                // Identifica se é uma ROM alternativa
                if (parseAlternateROM(game, tag)) {
                    break validate_block;
                }
                // Verifica se é uma compilação
                if (parseCompilation(game, tag)) {
                    break validate_block;
                }
                // Identifica uma data
                if (parseYear(game, tag)) {
                    break validate_block;
                }
                // Identifica a versão
                if (parseVersion(game, tag)) {
                    break validate_block;
                }
                // Identifica se e uma versão especial (Proto, Demo, Beta, Promo, Unl)
                if (parseSpecialVersion(game, tag)) {
                    break validate_block;
                }
                /*
                 // Valida alguns complementos
                 if (GameComplements.isComplement(platform, tag)) {
                 if (game.getComplement() == null) {
                 game.setComplement(tag);
                 break;
                 } else if (game.getComplement2() == null) {
                 game.setComplement2(tag);
                 break;
                 }
                 }
                 throw new UnknownGameNamePartException(tag);
                 */
                parseUnknowTag(platform, game, tag);
            }

            pos = s.indexOf("(", endPos + 1);
        }

        //TODO: Terminar
        game.setMainName(mainName);
        return this.messages;
    }

    private static boolean parsePublisher(Game game, String tag) {
        if (game.getPublisher() == null) {
            GamePublisher pub = GamePublisher.getPublisher(tag);
            if (pub != null) {
                game.setPublisher(pub);
                return true;
            }
        }
        return false;
    }

    private static boolean parseDeveloper(Game game, String tag) {
        if (game.getDeveloper() == null) {
            GameDeveloper dev = GameDeveloper.getDeveloper(tag);
            if (dev != null) {
                game.setDeveloper(dev);
                return true;
            }
        }
        return false;
    }

    private static boolean parseLanguage(Game game, String tag) {
        if ((game.getLanguages().isEmpty())
                && GameLanguage.isLanguages(tag)) {
            game.setLanguages(GameLanguage.fromNames(tag));
            return true;
        }
        return false;
    }

    private static boolean parseAlternateROM(Game game, String tag) {
        if (game.getAlternate() == null && tag.matches("Alt.+?")) {
            game.setAlternate(tag);
            return true;
        }
        return false;
    }

    private static boolean parseCompilation(Game game, String tag) {
        if (game.getCompilation() == null && tag.startsWith("Compilation")
                || tag.endsWith("Compilation")) {
            game.setCompilation(tag);
            return true;
        }
        return false;
    }

    private static boolean parseYear(Game game, String tag) {
        if (game.getYear() > 0) {
            return false;
        }
        if (tag.matches("\\d\\d\\d\\d")) {
            int year = Integer.parseInt(tag);
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d.\\d.\\d\\d\\d\\d") || tag.matches("\\d-\\d-\\d\\d\\d\\d")) {
            int year = Integer.parseInt(tag.substring(4));
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d\\d.\\d.\\d\\d\\d\\d")) {
            int year = Integer.parseInt(tag.substring(5));
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
            int year = Integer.parseInt(tag.substring(0, 4));
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d\\d.\\d\\d.\\d\\d-\\d\\d\\d\\d")) {
            int year = Integer.parseInt(tag.substring(6));
            game.setYear(year);
            return true;
        }

        if (tag.matches("\\d\\d-\\d\\d-\\d\\d")) {
            int year = Integer.parseInt(tag.substring(0, 2));
            if (year > 20) {
                year += 1900;
            } else {
                year += 2000;
            }
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d.\\d.\\d\\d") || tag.matches("\\w\\w\\w \\d\\d")) {
            int year = Integer.parseInt(tag.substring(4));
            if (year > 20) {
                year += 1900;
            } else {
                year += 2000;
            }
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d.\\d\\d.\\d\\d") || tag.matches("\\d\\d.\\d.\\d\\d")) {
            int year = Integer.parseInt(tag.substring(5));
            if (year > 20) {
                year += 1900;
            } else {
                year += 2000;
            }
            game.setYear(year);
            return true;
        }
        return false;
    }

    private static boolean parseVersion(Game game, String tag) {
        if (game.getVersion() == null && tag.matches("v.+?")
                || tag.matches("Rev.+?")
                || tag.matches("r.+?")
                || tag.matches("R\\d\\d")
                || tag.matches("R\\d")
                || tag.matches("A\\d\\d")
                || tag.matches("Release \\d\\d")) {
            game.setVersion(tag);
            return true;
        }
        return false;
    }

    private static boolean parseSpecialVersion(Game game, String tag) {
        // Identifica se é um Proto
        boolean result = false;
        String[] ss = tag.split(",");
        for (String s : ss) {
            if (s.trim().startsWith("Proto")) {
                game.setProto(s.trim());
                result = true;
            }
            // Identifica se é um Demo
            if (s.trim().equals("Demo")) {
                game.setDemo(Boolean.TRUE);
                result = true;
            }
            // Identifica se é um Beta
            if (s.trim().startsWith("Beta")) {
                game.setBeta(s.trim());
                result = true;
            }
            // Identifica se se é um Promo
            if (s.trim().equals("Promo")) {
                game.setPromo(Boolean.TRUE);
                result = true;
            }
            // Identifica se é um jogo não licenciado
            if (s.trim().equals("Unl")) {
                game.setUnlicensed(ThreeStates.True);
                result = true;
            }
            // Identifica se se é um Sample
            if (s.trim().equals("Sample")) {
                game.setSample(Boolean.TRUE);
                result = true;
            }
        }
        return result;
    }

    private void parseUnknowTag(String platform, Game game, String tag) {
        switch (platform) {
            case "Atari ST":
                if (tag.startsWith("Budget")) {
                    String[] ss = tag.split("-");
                    if (ss.length == 2) {
                        game.setVersion(ss[0].trim());
                        game.setPublisher(GamePublisher.fromName(ss[1].trim()));
                        return;
                    }
                }
                break;
            case "Commodore - 64":
                //TODO: O que fazer?
                switch (tag) {
                    case "Preview":
                        break;
                    case "Unreleased":
                        break;
                    case "Program":
                        break;
                    case "Budget":
                        break;
                    case "New Release":
                        break;
                    case "Addon":
                        break;
                    case "Newer":
                        break;
                    case "Diskmag":
                        break;
                    case "RG":
                        break;
                    case "RS":
                        break;
                    case "MK":
                        break;
                    case "CK":
                        break;
                    case "CHR":
                        break;
                    case "HLS":
                        break;
                    case "SCI":
                        break;
                    case "OCS, AGA":
                    case "OCS, ECS":
                    case "ECS, AGA":
                        // OCS: Original Chipset
                        // ECS: Enhanced Chipset (segunda geração) (Amiga 3000, Amiga 500+, Amiga 600)
                        // AGA: Terceira geraçao (Amiga 4000)
                        break;
                    case "Vorpal":
                        break;
                    case "Burner":
                        break;
                    case "ROM":
                        break;
                    case "Visiload":
                    case "Freeload":
                    case "Bleepload":
                    case "Novaload":
                    case "Cyberload":
                    case "Rasterload":
                        break;
                    case "Book Club":
                        break;
                }
            case "Nintendo Entertainment System":
                switch (tag) {
                    case "ArchiMENdes Hen": // Versao especial do jogo Gradius
                    case "Genteiban!":
                        break;
                    case "PAL":
                    case "NTSC":
                    case "NTSC Demo":
                    case "RAM":
                        break;
                }
            case "Game Boy":
                switch (tag) {
                    case "SGB Enhanced":
                        break;
                    default:
                        if (tag.indexOf("Sachen") > 0) {
                            game.setDeveloper(GameDeveloper.getDeveloper("Sachen"));
                        }
                        break;
                }
        }
        game.addComplement(tag);
        GameImporterMessage msg = new GameImporterMessage(GameImporterMessage.Type.WARN, "Unknown string in '" + game.getName() + "' ===> '" + tag + "'");
        this.messages.add(msg);
    }
}
