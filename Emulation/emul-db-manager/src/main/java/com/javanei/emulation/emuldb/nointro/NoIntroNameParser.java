package com.javanei.emulation.emuldb.nointro;

import com.javanei.emulation.common.ThreeStates;
import com.javanei.emulation.emuldb.GameNameParser;
import com.javanei.emulation.emuldb.UnknownGameNamePartException;
import com.javanei.emulation.emuldb.game.Game;
import com.javanei.emulation.emuldb.game.GameRegion;

/**
 * @author Vanei
 */
public final class NoIntroNameParser implements GameNameParser {

    @Override
    public final void parseGameName(Game game) throws Exception {
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
                // Identifica a versão
                if (tag.matches("v.+?")) {
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
                // Identifica se é um jogo não licenciado
                if (tag.equals("Unl")) {
                    game.setUnlicensed(ThreeStates.True);
                    break validate_block;
                }
                throw new UnknownGameNamePartException(tag);
            }

            pos = s.indexOf("(", endPos + 1);
        }

        //TODO: Terminar
        game.setMainName(mainName);
    }

    private static GameRegion parseRegion(String name) {
        for (GameRegion region : GameRegion.values()) {
            if (region.getName().equals(name)) {
                return region;
            }
        }
        return null;
    }
}
