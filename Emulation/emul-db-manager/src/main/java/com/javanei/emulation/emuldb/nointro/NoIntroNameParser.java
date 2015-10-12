package com.javanei.emulation.emuldb.nointro;

import com.javanei.emulation.common.ThreeStates;
import com.javanei.emulation.common.game.GamePublisher;
import com.javanei.emulation.common.game.GameRegion;
import com.javanei.emulation.emuldb.GameComplements;
import com.javanei.emulation.emuldb.GameNameParser;
import com.javanei.emulation.emuldb.UnknownGameNamePartException;
import com.javanei.emulation.emuldb.game.Game;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanei
 */
public final class NoIntroNameParser implements GameNameParser {

    private static final List<String> languages = new ArrayList<>();

    static {
        languages.add("En,Fr,De,Es,It,Sv");
        languages.add("En,Fr,De,Sv,No,Da");
        languages.add("En,Fr,De,It,Nl");
        languages.add("En,Fr,De,Es,It");
        languages.add("En,Fr,De,It,Es");
        languages.add("En,Fr,De,It");
        languages.add("En,Fr,It,Gd");
        languages.add("En,Fr,De,Es");
        languages.add("En,De,It,Nl");
        languages.add("Fr,De,Es,It");
        languages.add("En,Fr,De");
        languages.add("En,De,It");
        languages.add("En,Fr,It");
        languages.add("En,Fr");
        languages.add("En,De");
        languages.add("En,Ja");
        languages.add("En,It");
        languages.add("En");
        languages.add("Fr");
        languages.add("Es");
    }

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
                    GameRegion region = parseRegion(tag);
                    if (region != null) {
                        game.setRegion(region);
                        break validate_block;
                    }
                }
                if (game.getPublisher() == null) {
                    GamePublisher pub = parsePublisher(tag);
                    if (pub != null) {
                        game.setPublisher(pub);
                        break validate_block;
                    }
                }
                // Identifica a linguagem
                if (languages.contains(tag)) {
                    game.setLanguage(tag);
                    break;
                }
                // Identifica se é uma ROM alternativa
                if (tag.matches("Alt.+?")) {
                    game.setAlternate(tag);
                    break;
                }
                // Verifica se é uma compilação
                if (tag.startsWith("Compilation")
                        || tag.endsWith("Compilation")) {
                    game.setCompilation(tag);
                    break;
                }
                // Identifica uma data
                if (tag.matches("\\d\\d-\\d\\d-\\d\\d")) {
                    int year = Integer.parseInt(tag.substring(0, 2));
                    if (year > 20) {
                        year += 1900;
                    } else {
                        year += 2000;
                    }
                    game.setYear(year);
                    break validate_block;
                }
                if (tag.matches("\\d.\\d.\\d\\d")) {
                    int year = Integer.parseInt(tag.substring(4));
                    if (year > 20) {
                        year += 1900;
                    } else {
                        year += 2000;
                    }
                    game.setYear(year);
                    break validate_block;
                }
                if (tag.matches("\\d\\d.\\d.\\d\\d\\d\\d")) {
                    int year = Integer.parseInt(tag.substring(5));
                    if (year > 20) {
                        year += 1900;
                    } else {
                        year += 2000;
                    }
                    game.setYear(year);
                    break validate_block;
                }
                if (tag.matches("\\w\\w\\w \\d\\d")) {
                    int year = Integer.parseInt(tag.substring(5));
                    game.setYear(year);
                    break validate_block;
                }
                // Identifica a versão
                if (tag.matches("v.+?")
                        || tag.matches("Rev.+?")
                        || tag.matches("r.+?")
                        || tag.matches("R\\d\\d")
                        || tag.matches("R\\d")
                        || tag.matches("A\\d\\d")
                        || tag.matches("Release \\d\\d")) {
                    game.setVersion(tag);
                    break validate_block;
                }
                // Identifica se é um Proto
                if (tag.equals("Proto")) {
                    game.setProto(Boolean.TRUE);
                    break validate_block;
                }
                // Identifica se é um Demo
                if (tag.equals("Demo")) {
                    game.setDemo(Boolean.TRUE);
                    break validate_block;
                }
                // Identifica se é um Beta
                if (tag.equals("Beta")) {
                    game.setBeta(Boolean.TRUE);
                    break;
                }
                // Identifica se se é um Promo
                if (tag.equals("Promo")) {
                    game.setPromo(Boolean.TRUE);
                    break;
                }
                // Identifica se é um jogo não licenciado
                if (tag.equals("Unl")) {
                    game.setUnlicensed(ThreeStates.True);
                    break validate_block;
                }
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
            }

            pos = s.indexOf("(", endPos + 1);
        }

        //TODO: Terminar
        game.setMainName(mainName);
    }

    private static GameRegion parseRegion(String name) {
        return GameRegion.getRegion(name);
    }

    private static GamePublisher parsePublisher(String name) {
        return GamePublisher.getPublisher(name);
    }
}
