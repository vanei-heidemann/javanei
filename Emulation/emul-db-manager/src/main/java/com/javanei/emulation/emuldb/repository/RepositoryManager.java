package com.javanei.emulation.emuldb.repository;

import com.javanei.emulation.emuldb.config.ConfigManager;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.util.FileUtil;
import com.javanei.emulation.util.StringUtil;
import com.javanei.emulation.util.ZipUtil;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Vanei on 18/09/2015.
 */
public class RepositoryManager {

    private static final RepositoryManager instance = new RepositoryManager();
    private static String baseDir;

    private RepositoryManager() {
    }

    public static final RepositoryManager getInstance() {
        return instance;
    }

    public final File getBaseDir() {
        if (baseDir == null) {
            baseDir = ConfigManager.getConfig().getRepositoryBaseDir();
            if (ConfigManager.getConfig().getRepositoryBaseDir().startsWith(".")) {
                baseDir = (new File(ConfigManager.getHomeDir(), baseDir).getAbsolutePath());
            }
        }
        return new File(baseDir);
    }

    public void saveROMFile(GamePlatform platform, byte[] b) throws Exception {
        File romsDir = new File(getPlatformBaseDir(platform), "roms");
        if (!romsDir.exists()) {
            romsDir.mkdirs();
        }
        String crc = StringUtil.toStringCRC(FileUtil.getCRC(b));
        File destFile;
        switch (platform.getStorageFormat()) {
            case file:
                destFile = new File(romsDir, crc);
                if (destFile.exists()) {
                    //TODO: Criar uma exception
                    throw new FileAlreadyExistsException(destFile.getAbsolutePath());
                }
                break;
            case zip:
                destFile = new File(romsDir, crc + ".zip");
                if (destFile.exists()) {
                    //TODO: Criar uma exception
                    throw new FileAlreadyExistsException(destFile.getAbsolutePath());
                }
                ZipUtil.addFileToZip(destFile, crc, b);
                break;
            case singleZip:
                destFile = new File(romsDir, "roms.zip");
                ZipUtil.validateExistFileInZip(destFile, crc);
                ZipUtil.addFileToZip(destFile, crc, b);
                break;
        }
    }

    private File getPlatformBaseDir(GamePlatform platform) {
        if (platform.getRepositoryDir().startsWith(".")) {
            return new File(getBaseDir(), platform.getRepositoryDir());
        } else {
            return new File(platform.getRepositoryDir());
        }
    }
}
