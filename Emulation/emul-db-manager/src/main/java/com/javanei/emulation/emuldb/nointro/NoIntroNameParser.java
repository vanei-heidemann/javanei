package com.javanei.emulation.emuldb.nointro;

import com.javanei.emulation.common.ThreeStates;
import com.javanei.emulation.common.game.GameLanguage;
import com.javanei.emulation.common.game.GamePublisher;
import com.javanei.emulation.common.game.GameRegion;
import com.javanei.emulation.emuldb.GameNameParser;
import com.javanei.emulation.emuldb.game.Game;

/**
 * @author Vanei
 */
public final class NoIntroNameParser implements GameNameParser {

    @Override
    public final void parseGameName(String platform, Game game) throws Exception {
        String s = game.getName();
        int pos = s.indexOf("(");
        if (pos < 0) {
            game.setMainName(game.getName());
            return;
        }
        String mainName = s.substring(0, pos).trim();
        while (pos >= 0) {
            int endPos = s.indexOf(")", pos);
            String tag = s.substring(pos + 1, endPos);
            validate_block:
            {
                // Identifica a região
                if (game.getRegion() == null) {
                    GameRegion region = GameRegion.getRegion(tag);
                    if (region != null) {
                        game.setRegion(region);
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
                System.err.println("Tag desconhecida: " + tag);
            }

            pos = s.indexOf("(", endPos + 1);
        }

        //TODO: Terminar
        game.setMainName(mainName);
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

    private static boolean parseLanguage(Game game, String tag) {
        if ((game.getLanguages() == null || game.getLanguages().isEmpty())
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
        if (tag.matches("\\d.\\d.\\d\\d")) {
            int year = Integer.parseInt(tag.substring(4));
            if (year > 20) {
                year += 1900;
            } else {
                year += 2000;
            }
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\d\\d.\\d.\\d\\d\\d\\d")) {
            int year = Integer.parseInt(tag.substring(5));
            if (year > 20) {
                year += 1900;
            } else {
                year += 2000;
            }
            game.setYear(year);
            return true;
        }
        if (tag.matches("\\w\\w\\w \\d\\d")) {
            int year = Integer.parseInt(tag.substring(5));
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
        if (tag.equals("Proto")) {
            game.setProto(Boolean.TRUE);
            return true;
        }
        // Identifica se é um Demo
        if (tag.equals("Demo")) {
            game.setDemo(Boolean.TRUE);
            return true;
        }
        // Identifica se é um Beta
        if (tag.equals("Beta")) {
            game.setBeta(Boolean.TRUE);
            return true;
        }
        // Identifica se se é um Promo
        if (tag.equals("Promo")) {
            game.setPromo(Boolean.TRUE);
            return true;
        }
        // Identifica se é um jogo não licenciado
        if (tag.equals("Unl")) {
            game.setUnlicensed(ThreeStates.True);
            return true;
        }
        return false;
    }
}
