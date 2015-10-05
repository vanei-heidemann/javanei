package com.javanei.emulation.emuldb.config;

import com.javanei.emulation.emuldb.factory.GamePlatform;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Vanei on 18/09/2015.
 */
public final class ConfigManager {

    private static Config config;

    public static final synchronized Config getConfig() {
        if (config == null) {

            config = new Config();
            File cfg = getConfigFile();
            boolean needSave = false;
            if (cfg.exists()) {
                // Le as configurações
                try {
                    readConfigFile(cfg);
                } catch (Exception ex) {
                    //TODO: Tratar Exception
                    throw new RuntimeException(ex);
                }
            } else {
                // Cria configurações default e marca como necessário salvar.
                needSave = true;
            }
            // Valida as configurações default
            if (config.getRepositoryBaseDir() == null || config.getRepositoryBaseDir().isEmpty()) {
                config.setRepositoryBaseDir("./repository");
                needSave = true;
            }

            if (needSave) {
                // Salva as configurações novas/alteradas.
                try {
                    saveConfig();
                } catch (Exception ex) {
                    //TODO: Tratar Exception
                    throw new RuntimeException(ex);
                }
            }
        }

        return config;
    }

    public static final synchronized void saveConfig() throws Exception {
        File cfg = getConfigFile();
        System.out.println("Salvando arquivo de configuração: " + cfg.getAbsolutePath());
        try (FileOutputStream out = new FileOutputStream(cfg)) {
            out.write("<?xml version=\"1.0\"?>\n".getBytes());
            out.write(config.toString().getBytes());
        }
    }

    private static File getConfigFile() {
        File baseDir = new File(getHomeDir());
        File cfgDir = new File(baseDir, "config");
        if (!cfgDir.exists()) {
            cfgDir.mkdirs();
        }
        File cfg = new File(cfgDir, "EmuDB.xml");
        return cfg;
    }

    private static void readConfigFile(File cfg) throws ParserConfigurationException, SAXException, IOException,
            InvalidConfigurationFormatException {
        System.out.println("Lendo arquivo de configuração: " + cfg.getAbsolutePath());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(cfg);
        if (!document.getDocumentElement().getNodeName().equals("EmulDB")) {
            throw new InvalidConfigurationFormatException();
        }

        NodeList cfgList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < cfgList.getLength(); i++) {
            Node node = cfgList.item(i);
            if (node.getNodeName().equals("repositoryBaseDir")) {
                config.setRepositoryBaseDir(node.getTextContent().trim());
            } else if (node.getNodeName().equals("hyperspinBaseDir")) {
                config.setHyperspinBaseDir(node.getTextContent().trim());
            } else if (node.getNodeName().equals("hyperspinROMBaseDir")) {
                config.setHyperspinROMBaseDir(node.getTextContent().trim());
            }
        }
        System.out.println("Configurações lidas:\n==============================");
        System.out.println(config);
    }

    public static final String getHomeDir() {
        return System.getProperty("user.dir");
    }

    public static final File getDatabaseDir() {
        File dbDir = new File(getHomeDir(), "database");
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }
        return dbDir;
    }

    public static final File getPlatformDatabaseDir(GamePlatform platform) {
        File dir = new File(getDatabaseDir(), platform.getName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
