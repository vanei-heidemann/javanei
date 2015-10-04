package com.javanei.emulation.emuldb.nointro;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.emuldb.InvalidDatFileFormatException;
import com.javanei.emulation.emuldb.clrmamepro.ClrMameProImporter;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.game.GameImporter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.concurrent.Task;

/**
 * @author Vanei
 */
public class NoIntroImporter {

    public Task<GameImporter> getImporter(GamePlatform platform, File datFile) throws Exception {
        if (!datFile.exists()) {
            throw new FileNotFoundException(datFile.getAbsolutePath());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(datFile))) {
            String line = reader.readLine().trim();
            String[] ss = line.split(" ");
            if (ss[0].trim().equals("clrmamepro")) {
                return new ClrMameProImporter(platform, GameCatalog.NoIntro, datFile);
            } else {
                throw new InvalidDatFileFormatException(ss[0]);
            }
        }
    }
}
