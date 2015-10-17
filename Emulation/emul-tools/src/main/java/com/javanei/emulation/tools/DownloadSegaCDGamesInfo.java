package com.javanei.emulation.tools;

import com.javanei.emulation.common.game.segacd.SegaCDGame;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vanei
 *
 * @see https://en.wikipedia.org/wiki/List_of_Sega_CD_games
 * @see https://pt.wikipedia.org/wiki/Lista_de_jogos_para_Sega_CD
 */
public class DownloadSegaCDGamesInfo {

    public static final String DOWNLOAD_URL = "https://en.wikipedia.org/wiki/List_of_Sega_CD_games";

    public static void main(String[] args) {
        try {
            String html = downloadURL(DOWNLOAD_URL);
            List<String> lines = parseLines(html);
            List<SegaCDGame> games = parseGames(lines);

            List<String> developers = new LinkedList<>();
            for (SegaCDGame game : games) {
                System.out.println("games.put(\"" + game.getName() + "\", new SegaCDGame(\""
                        + game.getName() + "\", \"" + game.getDeveloper() + "\", \"" + game.getPublisher() + "\", "
                        + (game.getNtscj() != null ? ("\"" + game.getNtscj() + "\"") : "null") + ", "
                        + (game.getNtsc() != null ? ("\"" + game.getNtsc() + "\"") : "null") + ", "
                        + (game.getPal() != null ? ("\"" + game.getPal() + "\"") : "null") + ", "
                        + "\"" + game.getYear() + "\"));"
                );
                if (!developers.contains(game.getDeveloper())) {
                    developers.add(game.getDeveloper());
                }
            }

            System.out.println("=============================================");
            Collections.sort(developers);
            for (String s : developers) {
                System.out.println("developers.put(\"" + s + "\", new GameDeveloper(\"" + s + "\"));");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

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

    private static List<String> parseLines(String html) {
        System.out.println("INFO: Parseando HTML");
        List<String> result = new LinkedList<>();
        int pos = html.indexOf("Title(s)");
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

    private static List<SegaCDGame> parseGames(List<String> lines) {
        List<SegaCDGame> result = new LinkedList<>();
        for (String s : lines) {
            // Parse do nome
            int pos = s.indexOf("<th");
            int endPos = s.indexOf("</th>", pos);
            String name = extractName(s.substring(pos, endPos) + 5);

            // Parse do Developer
            pos = s.indexOf("<td", endPos);
            endPos = s.indexOf("</td", pos);
            String developer = extractDeveloper(s.substring(pos, endPos + 5));

            // Parse do Publisher
            pos = s.indexOf("<td", endPos);
            endPos = s.indexOf("</td", pos);
            String publisher = extractPublisher(s.substring(pos, endPos + 5));

            // Parse do NTSC-J
            pos = s.indexOf("<td", endPos);
            endPos = s.indexOf("</td", pos);
            String ntscj = extractNTSCJ(s.substring(pos, endPos + 5));

            // Parse do NTSC
            pos = s.indexOf("<td", endPos);
            endPos = s.indexOf("</td", pos);
            String ntsc = extractNTSC(s.substring(pos, endPos + 5));

            // Parse do PAL
            pos = s.indexOf("<td", endPos);
            endPos = s.indexOf("</td", pos);
            String pal = extractPAL(s.substring(pos, endPos + 5));

            // Parse do Year
            pos = s.indexOf("<td", endPos);
            endPos = s.indexOf("</td", pos);
            String year = extractYear(s.substring(pos, endPos + 5));

            SegaCDGame game = new SegaCDGame(name, developer, publisher, ntscj, ntsc, pal, year);
            result.add(game);
        }

        return result;
    }

    private static String extractName(String s) {
        int pos = s.indexOf("<i") + 3;
        if (s.indexOf("title", pos) > 0) {
            pos = s.indexOf("title", pos);
            pos = s.indexOf(">", pos) + 1;
        }
        int endPos = s.indexOf("<", pos);
        String name = s.substring(pos, endPos);
        //System.out.println(name);
        return name;
    }

    private static String extractDeveloper(String s) {
        int pos = s.indexOf("title");
        if (pos > 0) {
            pos = s.indexOf("title", pos);
            pos = s.indexOf(">", pos) + 1;
        } else {
            pos = s.indexOf(">") + 1;
        }
        int endPos = s.indexOf("<", pos);
        String dev = s.substring(pos, endPos);
        //System.out.println(" - " + dev);
        return dev;
    }

    private static String extractPublisher(String s) {
        int pos = s.indexOf("title");
        if (pos > 0) {
            pos = s.indexOf("title", pos);
            pos = s.indexOf(">", pos) + 1;
        } else {
            pos = s.indexOf(">") + 1;
        }
        int endPos = s.indexOf("<", pos);
        String pub = s.substring(pos, endPos);
        //System.out.println(" - " + pub);
        return pub;
    }

    private static String extractNTSCJ(String s) {
        if (s.length() < 10) {
            return null;
        }
        //System.out.println(" - NTSC-J");
        return "true";
    }

    private static String extractNTSC(String s) {
        if (s.length() < 10) {
            return null;
        }
        //System.out.println(" - NTSC");
        return "true";
    }

    private static String extractPAL(String s) {
        if (s.length() < 10) {
            return null;
        }
        //System.out.println(" - PAL");
        return "true";
    }

    private static String extractYear(String s) {
        String year = s.substring(4, 8);
        //System.out.println(" - " + year);
        return year;
    }
}
