package com.javanei.emulation.common.hyperspin.validateroms;

import com.javanei.emulation.common.hyperspin.HSGame;
import com.javanei.emulation.common.hyperspin.util.HSReadConfigs;
import com.javanei.emulation.common.hyperspin.HSSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Vanei on 06/09/2015.
 */
public class ValidateAAERoms {
    private static final String GAME_LIST_FILE = "J:\\Emulation\\Emulators\\AAE-2008-12-13\\AAE All Game Roms List.txt";

    public static void main(String[] args) {
        try {
            validate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void validate() throws Exception {
        HSSystem system = HSReadConfigs.getSystem("AAE");

        // Busca os jogos suportados pelo HS
        List<HSGame> hsGames = HSReadConfigs.listGames(system);

        // Busca os jogos suportados pelo AAE
        File src = new File(GAME_LIST_FILE);
        BufferedReader reader = new BufferedReader(new FileReader(src));
        reader.readLine();
        String line = reader.readLine();
        Map<String, List<String>> games = new LinkedHashMap<String, List<String>>();
        List<String> gameFiles = null;
        while (line != null) {
            if (line.isEmpty()) {
                line = reader.readLine();
                String game = line.trim().substring(0, line.trim().length() - 1);
                gameFiles = new LinkedList<String>();
                games.put(game, gameFiles);
            } else {
                gameFiles.add(line.trim());
            }
            line = reader.readLine();
        }

        // Valida
        int ok = 0;
        int nok = 0;
        for (HSGame game : hsGames) {
            System.out.println("Validando game: " + game.name);
            File f = new File(system.romsDir, game.name + ".zip");
            if (f.exists()) {
                // Encontrou o zip, entao valida o conteúdo
                // Primeiro le os arquivos do zip
                List<String> romFiles = readZipROMsContent(f);
                gameFiles = games.get(game.name);
                boolean erro = false;
                for (String s : gameFiles) {
                    if (!romFiles.contains(s)) {
                        System.out.println("  Nao encontrado arquivo: " + s);
                        erro = true;
                    }
                }
                if (erro) {
                    nok++;
                } else {
                    ok++;
                }

                // Valida se tem arquivo sobrando no zip
                for (String g : gameFiles) {
                    if (!gameFiles.contains(g)) {
                        System.out.println("  WARN: sobrando arquivo no zip: " + g);
                    }
                }
            } else {
                System.out.println("  Nao encontrado arquivo zip da rom");
                nok++;
            }
        }

        System.out.println(":::: Numero de jogos: " + hsGames.size());
        //System.out.println(":::: Arquivos na pasta: " + files.size());
        System.out.println(":::: ROMs encontradas e OK: " + ok);
        System.out.println(":::: ROMs NAO encontradas.: " + nok);
    }

    private static List<String> readZipROMsContent(File f) throws Exception {
        List<String> result = new LinkedList<String>();
        ZipFile zip = new ZipFile(f);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            try {
                ZipEntry ze = (ZipEntry) entries.nextElement();
                result.add(ze.getName());
            } catch (Exception ex) {
                System.err.println("ERRO: " + f + " -> " + ex.getMessage());
            }
        }
        zip.close();
        return result;
    }
}
