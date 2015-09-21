package com.javanei.emulation.common.hyperspin.util;

import com.javanei.emulation.common.hyperspin.HSGame;
import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.util.FileUtil;
import com.javanei.emulation.util.StringUtil;

import java.io.File;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Vanei on 06/09/2015.
 */
public class ValidateRomsUtil {
    public static void validateRoms(HSSystem system) throws Exception {
        List<HSGame> games = HSReadConfigs.listGames(system);
        List<File> files = FileUtil.listZipFilesRecursive(new File(system.romsDir));
        Map<String, String> roms = new HashMap<String, String>();
        Map<String, String> romsByName = new HashMap<String, String>();

        List<HSGame> romsFaltantes = new LinkedList<HSGame>();

        for (File f : files) {
            ZipFile zip = new ZipFile(f);
            Enumeration entries = zip.entries();
            while (entries.hasMoreElements()) {
                try {
                    ZipEntry ze = (ZipEntry) entries.nextElement();
                    roms.put(StringUtil.toStringCRC(ze.getCrc()), f.getName());
                    romsByName.put(f.getName(), StringUtil.toStringCRC(ze.getCrc()));
                } catch (Exception ex) {
                    System.err.println("ERRO: " + f + " -> " + ex.getMessage());
                }
            }
            zip.close();
        }

        int crcOk = 0;
        for (HSGame g : games) {
            if (system.validateCRC) {
                String f = roms.get(g.crc);
                if (f != null) {
                    roms.remove(g.crc);
                    romsByName.remove(g.name + ".zip");
                    crcOk++;
                } else {
                    romsFaltantes.add(g);
                }
            } else {
                romsFaltantes.add(g);
            }
        }
        int nomeOk = 0;
        if (crcOk < games.size()) {
            for (HSGame g : games) {
                if (romsByName.get(g.name + ".zip") != null) {
                    nomeOk++;
                    romsByName.remove(g.name + ".zip");
                    romsFaltantes.remove(g);
                }
            }
        }

        // Imprime o resultado
        System.out.println(":::: Numero de jogos: " + games.size());
        System.out.println(":::: Arquivos na pasta: " + files.size());
        System.out.println(":::: ROMs encontradas: CRC OK=" + crcOk + " , Nome OK=" + nomeOk);
        System.out.println(":::: ROMs nao encontradas: " + romsFaltantes.size());
        System.out.println("===================================");
        for (HSGame g : romsFaltantes) {
            System.out.println("      " + g.crc + " " + g.name);
        }
        System.out.println(":::: Aruivos sobrando: " + romsByName.size());
        System.out.println("===================================");
        for (String f : romsByName.values()) {
            System.out.println("      " + f);
        }
    }
}
