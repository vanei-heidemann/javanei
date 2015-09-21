package com.javanei.emulation.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;

/**
 * Created by Vanei on 05/09/2015.
 */
public final class FileUtil {
    public static final List<File> listZipFilesRecursive(File srcDir) throws Exception {
        List<File> result = new LinkedList<File>();
        listZipFilesRecursive(srcDir, result);
        return result;
    }

    private static final void listZipFilesRecursive(File srcDir, List<File> result) throws Exception {
        File[] files = srcDir.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                listZipFilesRecursive(f, result);
            } else if (f.isFile() && f.getName().toLowerCase().endsWith(".zip")) {
                result.add(f);
            }
        }
    }

    public static final String getFileExtension(String fileName) {
        if (fileName.indexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    public static final long getCRC(File f) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(f);
        byte[] b = new byte[4096];
        int len = fis.read(b);
        while (len > 0) {
            out.write(b, 0, len);
            len = fis.read(b);
        }
        out.close();
        return getCRC(out.toByteArray());
    }

    private static long getCRC(byte[] b) {
        CRC32 crc = new CRC32();
        crc.update(b);
        System.out.println("CRC=" + crc.getValue() + " -> " + StringUtil.toStringCRC(crc.getValue()));
        return crc.getValue();
    }
}
