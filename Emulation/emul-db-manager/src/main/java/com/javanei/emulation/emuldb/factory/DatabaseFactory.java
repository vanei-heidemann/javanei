package com.javanei.emulation.emuldb.factory;

import com.javanei.emulation.emuldb.config.ConfigManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Vanei on 18/09/2015.
 */
public class DatabaseFactory {
    private static final String DATABASE_FILE_NAME = "database.xml";
    private static DatabaseFactory instance;
    private static Database database;

    private DatabaseFactory() {
    }

    public static synchronized DatabaseFactory getInstance() {
        if (instance == null) {
            instance = new DatabaseFactory();
            database = new Database();
            try {
                readDatabase();
            } catch (Exception ex) {
                //TODO: Tratar Exception
                instance = null;
                throw new RuntimeException(ex);
            }
        }
        return instance;
    }

    public final Database getDatabase() {
        return database;
    }

    private static void readDatabase() throws Exception {
        File dbFile = getDatabaseFile();
        boolean needSave = false;
        if (dbFile.exists()) {
            // Já existe o arquivo, le.
            System.out.println("Lendo arquivo de banco de dados: " + dbFile.getAbsolutePath());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(dbFile);

            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("platform")) {
                    GamePlatform sys = new GamePlatform();
                    sys.setName(node.getAttributes().getNamedItem("name").getNodeValue());
                    sys.setValidExtension(node.getAttributes().getNamedItem("validExtension").getNodeValue());
                    sys.setRepositoryDir(node.getAttributes().getNamedItem("repositoryDir").getNodeValue());
                    sys.setMultiFile(node.getAttributes().getNamedItem("multiFile").getNodeValue().equalsIgnoreCase("true"));
                    sys.setAllowZip(node.getAttributes().getNamedItem("allowZip").getNodeValue().equalsIgnoreCase("true"));
                    sys.setStorageFormat(StorageFormat.valueOf(node.getAttributes().getNamedItem("storageFormat").getNodeValue()));
                    database.addPlatform(sys);
                }
            }
            System.out.println("Banco de dados lido:\n==============================");
            System.out.println(database);
        } else {
            // Não existe ainda o arquivo, cria um novo.
            needSave = true;
        }

        if (needSave) {
            saveDatabase();
        }
    }

    protected static final synchronized void saveDatabase() throws Exception {
        File dbFile = getDatabaseFile();
        System.out.println("Salvando arquivo de banco de dados:\n=====================================\n" + database);
        try (FileOutputStream out = new FileOutputStream(dbFile)) {
            out.write("<?xml version=\"1.0\"?>\n".getBytes());
            out.write(database.toString().getBytes());
        }
    }

    protected static final File getDatabaseFile() {
        File dbDir = new File(ConfigManager.getHomeDir(), "database");
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }
        return new File(dbDir, DATABASE_FILE_NAME);
    }
}
