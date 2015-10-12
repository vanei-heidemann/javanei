package com.javanei.emulation.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vanei
 */
public class DownloadC64Tapes {

    public static final String DOWNLOAD_BASE_URL = "http://tapes.c64.no/";
    public static final String DOWNLOAD_URL = DOWNLOAD_BASE_URL + "main_tapelist.php?group=all&orderby=publisher";
    public static final String DOWNLOAD_DIR = "F:\\Emulation\\tmp\\C64Tapes";

    public static void main(String[] args) {
        try {
            String html = downloadURL(DOWNLOAD_URL);
            List<String> lines = parseLines(html);
            List<C64TapeGame> games = parseGames(lines);
            List<String> publishers = new ArrayList<>();
            List<String> loaders = new ArrayList<>();
            for (C64TapeGame game : games) {
                if (game.publisher != null && !game.publisher.isEmpty() && !publishers.contains(game.publisher)) {
                    publishers.add(game.publisher);
                }
                if (game.loader != null && !game.loader.isEmpty() && !loaders.contains(game.loader)) {
                    loaders.add(game.loader);
                }
                downloadGame(game);
                downloadCover(game);
            }
            Collections.sort(publishers);
            Collections.sort(loaders);
            System.out.println("===============================================");
            System.out.println("Publishers");
            System.out.println("===============================================");
            for (String s : publishers) {
                System.out.println("publishers.put(\"" + s + "\", new GamePublisher(\"" + s + "\"));");
            }
            System.out.println("===============================================");
            System.out.println("Loaders");
            System.out.println("===============================================");
            for (String s : loaders) {
                System.out.println("loaders.put(\"" + s + "\", new GameLoader(\"" + s + "\"));");
            }
            System.out.println("===============================================");
            System.out.println("Publishers, years e loaders");
            System.out.println("===============================================");
            for (C64TapeGame game : games) {
                if (game.publisher != null && !game.publisher.isEmpty() && !game.publisher.equals("???")) {
                    System.out.println("publishers.put(\"" + game.name + "\", \"" + game.publisher + "\");");
                }
                if (game.year != null && !game.year.isEmpty()) {
                    System.out.println("years.put(\"" + game.name + "\", \"" + game.year + "\");");
                }
                if (game.loader != null && !game.loader.isEmpty()) {
                    System.out.println("loaders.put(\"" + game.name + "\", \"" + game.loader + "\");");
                }
            }
            System.out.println("===============================================");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<C64TapeGame> parseGames(List<String> lines) {
        System.out.println("INFO: Extraindo jogos");
        List<C64TapeGame> result = new LinkedList<>();
        for (String s : lines) {
            // Parse URI / Name
            C64TapeGame game = new C64TapeGame();
            int pos = s.indexOf("<a href");
            pos = s.indexOf("=", pos) + 2;
            int endPos = s.indexOf(">", pos) - 1;
            game.uri = s.substring(pos, endPos);
            pos = endPos + 2;
            endPos = s.indexOf("</a", pos);
            game.name = s.substring(pos, endPos);
            result.add(game);

            // Parse  year
            pos = s.indexOf(">", s.indexOf("<font", endPos)) + 1;
            endPos = s.indexOf("<", pos);
            game.year = s.substring(pos, endPos);
            if (!game.year.isEmpty() && game.year.length() <= 2) {
                game.year = "";
            }

            // Parse publisher
            pos = s.indexOf(">", s.indexOf("<font", endPos)) + 1;
            endPos = s.indexOf("<", pos);
            game.publisher = s.substring(pos, endPos);

            // Game added
            endPos = s.indexOf("</td>", endPos + 10);

            // Loader
            pos = s.indexOf(">", s.indexOf("<font", endPos)) + 1;
            endPos = s.indexOf("<", pos);
            game.loader = s.substring(pos, endPos);

            // Cover
            pos = s.indexOf("<td>", endPos);
            endPos = s.indexOf("</td>", pos) + 5;
            if (s.substring(pos, endPos).indexOf("href") > 0) {
                pos = s.indexOf("href", pos) + 6;
                endPos = s.indexOf(">", pos) - 1;
                game.coverURI = s.substring(pos, endPos);
            }
        }

        return result;
    }

    private static List<String> parseLines(String html) {
        System.out.println("INFO: Parseando HTML");
        List<String> result = new LinkedList<>();
        int pos = html.indexOf(">Title</a>");
        int end = html.indexOf("</table>", pos);

        pos = html.indexOf("<tr", pos);
        while (pos > 0 && pos < end) {
            int endPos = html.indexOf("</tr>", pos);
            String s = html.substring(pos, endPos + 5);
            result.add(s);

            pos = html.indexOf("<tr", endPos);
        }

        return result;
    }

    private static void downloadCover(C64TapeGame game) {
        if (game.coverURI == null || game.coverURI.isEmpty()) {
            return;
        }

        String coverUrl = DOWNLOAD_BASE_URL + game.coverURI;
        File file = new File(DOWNLOAD_DIR, game.name.replace(':', '_') + game.coverURI.substring(game.coverURI.lastIndexOf(".")));
        if (file.exists()) {
            // Já baixado
            return;
        }

        System.out.println("INFO: Baixando cover: " + file.getName());
        byte[] b = new byte[4096];
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            URL url = new URL(coverUrl);
            try (InputStream is = url.openStream()) {
                int read = is.read(b);
                while (read > 0) {
                    out.write(b, 0, read);
                    read = is.read(b);
                }
            }
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(out.toByteArray());
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + game.name + " -> " + ex.getMessage());
        }
    }

    private static void downloadGame(C64TapeGame game) {
        String gameUrl = DOWNLOAD_BASE_URL + game.uri;
        File file = new File(DOWNLOAD_DIR, game.name.replace(':', '_') + ".zip");
        if (file.exists()) {
            // Já baixado
            return;
        }

        System.out.println("INFO: Baixando game: " + game.name);
        byte[] b = new byte[4096];
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            URL url = new URL(gameUrl);
            try (InputStream is = url.openStream()) {
                int read = is.read(b);
                while (read > 0) {
                    out.write(b, 0, read);
                    read = is.read(b);
                }
            }
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(out.toByteArray());
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + game.name + " -> " + ex.getMessage());
        }
    }

    private static String downloadURL(String downloadURL) throws Exception {
        System.out.println("INFO: Baixando HTML");
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        StringBuilder sbPage = new StringBuilder();
        try {
            url = new URL(downloadURL);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                sbPage.append(line).append("\r\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception ioe) {
            }
        }
        return sbPage.toString();
    }

    static class C64TapeGame {

        private String name;
        private String uri;
        private String year = "";
        private String publisher = "";
        private String loader = "";
        private String coverURI;
        private String extraURI;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("<game name=\"").append(this.name).append("\"");
            sb.append(" year=\"").append(this.year).append("\"");
            sb.append(" publisher=\"").append(this.publisher).append("\"");
            sb.append(" uri=\"").append(this.uri).append("\"");
            sb.append(" loader=\"").append(this.loader).append("\"");
            if (this.coverURI != null) {
                sb.append(" coverURI=\"").append(this.coverURI).append("\"");
            }
            sb.append(" />");
            return sb.toString();
        }
    }
}
