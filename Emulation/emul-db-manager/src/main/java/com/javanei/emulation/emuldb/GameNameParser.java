package com.javanei.emulation.emuldb;

import com.javanei.emulation.emuldb.game.Game;
import com.javanei.emulation.emuldb.game.GameImporterMessage;
import java.util.List;

/**
 * @author Vanei
 */
public interface GameNameParser {

    public List<GameImporterMessage> parseGameName(String platform, Game game) throws Exception;
}
