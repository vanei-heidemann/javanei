package com.javanei.emulation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by Vanei on 03/09/2015.
 */
public class ZipUtil {
    public static void listFiles(File zipFile) throws Exception {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            System.out.println(ze.getName() + " : " + ze.getCrc());
        }
        zip.close();
    }

    public static void extract(File zipFile, File destDir) throws Exception {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            System.out.println(ze.getName() + " : " + ze.getCrc());
            File dest = new File(destDir, ze.getName());
            if (ze.isDirectory()) {
                dest.mkdir();
            } else {
                InputStream is = zip.getInputStream(ze);
                FileOutputStream fos = new FileOutputStream(dest);
                byte[] b = new byte[4096];
                int size = is.read(b);
                while (size > 0) {
                    fos.write(b, 0, size);
                    size = is.read(b);
                }
                fos.close();
            }
        }
        zip.close();
    }

    public static void extractByCRC(File zipFile, File destFile, String crc) throws Exception {
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            if (StringUtil.toStringCRC(ze.getCrc()).equals(crc)) {
                InputStream is = zip.getInputStream(ze);
                FileOutputStream fos = new FileOutputStream(destFile);
                byte[] b = new byte[4096];
                int size = is.read(b);
                while (size > 0) {
                    fos.write(b, 0, size);
                    size = is.read(b);
                }
                fos.close();
            }
        }
        zip.close();
    }

    public static long getCRCFromFirstFile(File zipFile) throws Exception {
        long r = 0;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration entries = zip.entries();
        if (entries.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) entries.nextElement();
            r = ze.getCrc();
        }
        zip.close();
        return r;
    }

    public static void createZipFile(File zip, File file) throws Exception {
        // input file
        FileInputStream in = new FileInputStream(file);
        // out put file
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
        // name the file inside the zip  file
        out.putNextEntry(new ZipEntry(file.getName()));
        // buffer size
        byte[] b = new byte[1024];
        int count;
        while ((count = in.read(b)) > 0) {
            out.write(b, 0, count);
        }
        out.close();
        in.close();
    }
}
