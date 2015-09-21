package com.javanei.emulation.common.hyperspin.util;

import com.javanei.emulation.common.hyperspin.HSGame;
import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.util.FileUtil;
import com.javanei.emulation.util.StringUtil;
import com.javanei.emulation.util.ZipUtil;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Vanei on 05/09/2015.
 */
public class CopyRomsUtil {
    public static void copyRoms(HSSystem system, File srcDir, File dstDir) throws Exception {
        List<HSGame> games = HSReadConfigs.listGames(system);
        Map<String, File> roms = new HashMap<String, File>();
        Map<String, String> romsName = new HashMap<String, String>();
        List<File> files = FileUtil.listZipFilesRecursive(srcDir);
        for (File f : files) {
            ZipFile zip = new ZipFile(f);
            Enumeration entries = zip.entries();
            while (entries.hasMoreElements()) {
                try {
                    ZipEntry ze = (ZipEntry) entries.nextElement();
                    roms.put(StringUtil.toStringCRC(ze.getCrc()), f);
                    romsName.put(StringUtil.toStringCRC(ze.getCrc()), ze.getName());
                } catch (Exception ex) {
                    System.err.println("ERRO: " + f + " -> " + ex.getMessage());
                }
            }
            zip.close();
        }
        int ok = 0;
        int nok = 0;
        for (HSGame g : games) {
            File f = roms.get(g.crc);
            if (f != null) {
                String nameOnZip = romsName.get(g.crc);
                System.out.println("ACHOU: " + g.name + " -> " + roms.get(g.crc).getName());
                ok++;
                if (new File(dstDir, g.name + FileUtil.getFileExtension(nameOnZip)).exists()) {
                    // Não devia entrar aqui, pois não uso ROM fora do zip.
                    System.err.println("O Jogo " + g.name + " existe e está descompactado");
                    continue;
                }
                File fZip = new File(dstDir, g.name + ".zip");
                if (fZip.exists()) {
                    System.out.println("EXISTE: " + g.name);
                    // Confere o CRC
                    String oldCrc = StringUtil.toStringCRC(ZipUtil.getCRCFromFirstFile(fZip));
                    if (oldCrc.equals(g.crc)) {
                        //OK, é o mesmo CRC, mantém.
                        continue;
                    } else {
                        // Agora encontrou o jogo com o CRC correto, usa ele!
                        System.out.println("WARN: Subtituindo o arquivo " + fZip + " por um novo com mesmo CRC!");
                        fZip.delete();
                    }
                }
                File romFile = new File(dstDir, g.name + "." + FileUtil.getFileExtension(nameOnZip).toLowerCase());
                ZipUtil.extractByCRC(roms.get(g.crc), romFile, g.crc);
                ZipUtil.createZipFile(fZip, romFile);
                romFile.delete();
            } else {
                System.out.println("NAO ACHOU: " + g.name + " (" + g.crc + ")");
                nok++;
            }
        }

        if (ok < games.size()) {
            // Procura pelo nome
            for (HSGame g : games) {
                File zip = new File(system.romsDir + "/" + g.name + ".zip");
                if (!zip.exists()) {
                    System.out.println(" ### Procurando pelo nome do arquivo: " + zip);
                    for (File f : roms.values()) {
                        if (zip.getName().equalsIgnoreCase(f.getName())) {
                            System.out.println("   @@@@ Achou: " + f);
                        }
                    }
                }
            }
        }
        System.out.println("::: Arquivos Lidos: " + files.size());
        System.out.println("::: Games: " + games.size());
        System.out.println("::: OK= " + ok);
        System.out.println("::: NOK= " + nok);
    }
}
