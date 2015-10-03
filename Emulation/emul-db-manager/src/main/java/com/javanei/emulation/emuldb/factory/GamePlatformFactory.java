package com.javanei.emulation.emuldb.factory;

import com.javanei.emulation.emuldb.config.ConfigManager;

import java.io.File;

/**
 * Created by Vanei on 18/09/2015.
 */
public class GamePlatformFactory {

    private static final GamePlatformFactory instance = new GamePlatformFactory();

    private GamePlatformFactory() {
    }

    public static final GamePlatformFactory getInstance() {
        return instance;
    }

    public GamePlatform createPlatform(String name, String validExtension, String repositoryDir,
            boolean multiFile, boolean allowZip, StorageFormat storageFormat) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Database db = DatabaseFactory.getInstance().getDatabase();

        if (repositoryDir == null || repositoryDir.trim().isEmpty()) {
            repositoryDir = "./" + name;
        }
        String rDir = repositoryDir.replace("{PlatformName}", name);
        GamePlatform sys = new GamePlatform(name.trim());
        sys.setValidExtension(validExtension);
        sys.setRepositoryDir(rDir);
        sys.setMultiFile(multiFile);
        sys.setAllowZip(allowZip);
        sys.setStorageFormat(storageFormat);
        db.addPlatform(sys);
        DatabaseFactory.saveDatabase();

        File repoDir = resolveFile(sys);

        // Cria a pasta do repositorio
        if (!repoDir.exists()) {
            repoDir.mkdirs();
        } else {
            // A pasta já existe, é provável que esteja compartilhando uma pasta entre sistemas.
            System.out.println("WARN: o repositório já existe: " + repoDir.getAbsolutePath());
        }

        return sys;
    }

    public GamePlatform updatePlatform(String name, String validExtension) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Database db = DatabaseFactory.getInstance().getDatabase();
        GamePlatform platform = db.getPlatform(name);
        if (platform == null) {
            throw new GamePlatformDoesNotExistsException();
        }
        platform.setValidExtension(validExtension);
        DatabaseFactory.saveDatabase();
        return platform;
    }

    public void removePlatform(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        Database db = DatabaseFactory.getInstance().getDatabase();
        db.removePlatform(name);
        DatabaseFactory.saveDatabase();
    }

    private File resolveFile(GamePlatform platform) {
        File repoDir;
        if (platform.getRepositoryDir().startsWith(".")) {
            // Usando caminho relativo a pasta default de repositórios.
            repoDir = new File(ConfigManager.getConfig().getRepositoryBaseDir(), platform.getRepositoryDir());
        } else {
            // Usando um caminho absoluto.
            repoDir = new File(platform.getRepositoryDir());
        }
        return repoDir;
    }
}
