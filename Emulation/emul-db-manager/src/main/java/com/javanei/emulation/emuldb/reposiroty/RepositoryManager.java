package com.javanei.emulation.emuldb.reposiroty;

import com.javanei.emulation.emuldb.config.ConfigManager;

import java.io.File;

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
}
