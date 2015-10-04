package com.javanei.emulation.emuldb;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.emuldb.nointro.NoIntroNameParser;

/**
 * @author Vanei
 */
public final class GameNameParserFactory {

    public static GameNameParser getParser(GameCatalog catalog) throws Exception {
        if (catalog == GameCatalog.NoIntro) {
            return new NoIntroNameParser();
        }
        //TODO: Criar Exception
        throw new Exception(catalog.toString());
    }
}
