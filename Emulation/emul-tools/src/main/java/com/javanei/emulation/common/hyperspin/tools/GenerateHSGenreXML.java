package com.javanei.emulation.common.hyperspin.tools;

import com.javanei.emulation.common.hyperspin.HSGame;
import com.javanei.emulation.common.hyperspin.HSMenu;
import com.javanei.emulation.common.hyperspin.HSMainMenu;
import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.common.hyperspin.util.HSReadConfigs;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Vanei on 13/09/2015.
 */
public class GenerateHSGenreXML {
    public static void main(String[] args) {
        try {
            generateGenreXML();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void generateGenreXML() throws Exception {
        // Le o menu.
        System.out.println("Lendo Main Menu");
        HSMainMenu mm = HSReadConfigs.readMainMenu();

        // Processa cada um dos sistemas.
        for (String systemName : mm.games) {
            System.out.println("  Lendo e classificando os jogos para o sistema " + systemName);
            HSSystem system = HSReadConfigs.getSystem(systemName);

            List<HSGame> games = HSReadConfigs.listGames(system);
            List<String> genres = new ArrayList<String>();
            Map<String, HSMenu> genreGames = new HashMap<String, HSMenu>();
            for (HSGame game : games) {
                if (!genres.contains(game.genre)) {
                    genres.add(game.genre);
                }
                HSMenu menu = genreGames.get(game.genre);
                if (menu == null) {
                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    menu = new HSMenu();
                    menu.listname = systemName;
                    menu.lastlistupdate = df.format(new Date());
                    genreGames.put(game.genre, menu);
                }
                menu.games.add(game);
            }

            // Ordena os generos
            Collections.sort(genres, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });

            System.out.println("    Gerando arquivo genre.xml");
            // Salva o arquivo genres.xml
            FileOutputStream fos = new FileOutputStream(new File(system.dbDir + "/genre.xml"));
            fos.write("<menu>\r\n".getBytes());
            for (String g : genres) {
                fos.write(("\t<game name=\"" + g + "\" />\r\n").getBytes());
            }
            fos.write("</menu>".getBytes());
            fos.flush();
            fos.close();

            System.out.println("    Gerando xml por genero");
            // Salva a lista de jogos de cada genero
            for (String g : genres) {
                HSMenu menu = genreGames.get(g);
                fos = new FileOutputStream(new File(system.dbDir + "/" + g.replace('/', '-') + ".xml"));
                fos.write(menu.toString().getBytes());
                fos.flush();
                fos.close();
            }
        }
    }
}
