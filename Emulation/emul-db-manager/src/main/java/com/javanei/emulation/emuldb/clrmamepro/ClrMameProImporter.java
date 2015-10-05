package com.javanei.emulation.emuldb.clrmamepro;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.emuldb.GameNameParserFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.InvalidDatFileFormatException;
import com.javanei.emulation.emuldb.UnknownTagException;
import com.javanei.emulation.emuldb.game.Game;
import com.javanei.emulation.emuldb.game.GameFile;
import com.javanei.emulation.emuldb.game.GameImporter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.concurrent.Task;

/**
 * @author Vanei
 */
public class ClrMameProImporter extends Task<GameImporter> {

    private final File datFile;
    private final GamePlatform platform;
    private final GameCatalog catalog;

    private final GameImporter gameImporter;
    private final StringBuilder sbMessage;
    private final List<String> erros = new ArrayList<>();

    public ClrMameProImporter(GamePlatform platform, GameCatalog catalog, File file) {
        this.platform = platform;
        this.datFile = file;
        this.catalog = catalog;
        this.gameImporter = new GameImporter();
        sbMessage = new StringBuilder();
    }

    @Override
    protected GameImporter call() throws Exception {
        List<Game> games = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(datFile))) {
            String line = reader.readLine().trim();
            String[] ss = line.split(" ");
            if (!ss[0].trim().equals("clrmamepro")) {
                throw new InvalidDatFileFormatException(ss[0].trim());
            }
            while (line != null && !line.isEmpty() && !line.equals(")")) {
                if (line.startsWith("name")) {
                    this.gameImporter.setName(line.substring(line.indexOf("\"") + 1, line.length() - 1));
                } else if (line.startsWith("description")) {
                    this.gameImporter.setDescription(line.substring(line.indexOf("\"") + 1, line.length() - 1));
                } else if (line.startsWith("version")) {
                    if (line.indexOf("\"") > 0) {
                        this.gameImporter.setVersion(line.substring(line.indexOf("\"") + 1, line.length()));
                    } else {
                        this.gameImporter.setVersion(line.split(" ")[1]);
                    }
                } else if (line.startsWith("comment")) {
                    this.gameImporter.setComment(line.substring(line.indexOf("\"") + 1, line.length() - 1));
                }
                line = reader.readLine().trim();
            }

            line = reader.readLine();
            List<String> lines = new LinkedList<>();
            while (line != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
                line = reader.readLine();
            }

            Game game = null;
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);
                try {
                    if (line.startsWith("game")) {
                        game = new Game();
                    } else if (line.startsWith("name")) {
                        if (game != null) {
                            game.setName(line.substring(line.indexOf("\"") + 1, line.length() - 1));
                        }
                    } else if (line.startsWith("description")) {
                        if (game != null) {
                            game.setDescription(line.substring(line.indexOf("\"") + 1, line.length() - 1));
                        }
                    } else if (line.startsWith("rom")) {
                        if (game != null) {
                            String romLine = line.substring(line.indexOf("(") + 1, line.length() - 1).trim();
                            game.addRom(processROMLine(romLine));
                        }
                    } else if (line.equals(")")) {
                        if (game != null) {
                            try {
                                game.setCatalog(this.catalog);
                                game.setCatalogVersion(gameImporter.getVersion());
                                GameNameParserFactory.getParser(this.catalog).parseGameName(this.platform.getName(), game);
                                fireMessage(game.getName() + ": OK");
                                games.add(game);
                            } catch (Exception ex) {
                                fireMessageError(game.getName(), ex);
                            }
                        }
                    } else {
                        fireMessageError(null, new UnknownTagException(line));
                    }
                } catch (Exception ex) {
                    fireMessageError(null, ex);
                    game = null;
                }
                updateProgress(i + 1, lines.size()); // (progress, max)
            }

            updateProgress(100, 100);
        } catch (Exception ex) {
            this.fireMessageError(null, ex);
        }
        this.gameImporter.setGames(games);
        if (!this.erros.isEmpty()) {
            this.sbMessage.append("=================================================\n");
            this.erros.stream().forEach((s) -> {
                this.sbMessage.append(s);
            });
        }
        updateMessage(this.sbMessage.toString());

        return this.gameImporter;
    }

    private GameFile processROMLine(String romLine) throws Exception {
        int pos = romLine.indexOf("\"") + 1;
        int endpos = romLine.indexOf("\"", pos + 1) - 1;
        GameFile gf = new GameFile(romLine.substring(pos, endpos));

        String[] ss = romLine.substring(endpos + 2).trim().split(" ");
        for (int i = 0; i < ss.length; i++) {
            switch (ss[i]) {
                case "size":
                    gf.setSize(Long.parseLong(ss[++i]));
                    break;
                case "crc":
                    gf.setCrc(ss[++i]);
                    break;
                case "md5":
                    gf.setMd5(ss[++i]);
                    break;
                case "sha1":
                    gf.setSha1(ss[++i]);
                    break;
                default:
                    throw new UnknownTagException(ss[i]);
            }
        }

        return gf;
    }

    private void fireMessageError(String game, Exception ex) {
        StringBuilder sb = new StringBuilder();
        if (game != null) {
            sb.append(game).append(": ");
        }
        sb.append("ERROR: ").append(ex.getClass().getName()).append(": ").append(ex.getLocalizedMessage()).append("\n");
        this.sbMessage.append(sb.toString());
        this.erros.add(sb.toString());
        updateMessage(this.sbMessage.toString());
    }

    private void fireMessage(String message) {
        this.sbMessage.append(message).append("\n");
        updateMessage(this.sbMessage.toString());
    }
}
