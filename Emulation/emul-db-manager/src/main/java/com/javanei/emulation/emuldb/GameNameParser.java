package com.javanei.emulation.emuldb;

import com.javanei.emulation.emuldb.game.Game;

/**
 * @author Vanei
 */
public interface GameNameParser {

    public void parseGameName(String platform, Game game) throws Exception;
}
