package com.javanei.emulation.emuldb.factory;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.common.ThreeStates;
import com.javanei.emulation.common.game.GameDeveloper;
import com.javanei.emulation.common.game.GameLanguage;
import com.javanei.emulation.common.game.GameLoader;
import com.javanei.emulation.common.game.GameProtection;
import com.javanei.emulation.common.game.GamePublisher;
import com.javanei.emulation.common.game.GameRegion;
import com.javanei.emulation.emuldb.config.ConfigManager;
import com.javanei.emulation.emuldb.game.Game;
import com.javanei.emulation.emuldb.game.GameFile;
import com.javanei.emulation.util.FileUtil;
import com.javanei.emulation.util.StringUtil;
import com.javanei.emulation.util.ZipUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    protected void saveDatFile(GamePlatform platform, GameCatalog catalog, String catalogVersion, String complement, byte[] b) throws Exception {
        File datDir = new File(ConfigManager.getPlatformDatabaseDir(platform), "datfiles");
        if (!datDir.exists()) {
            datDir.mkdirs();
        }
        String fileName = catalog.name() + "_" + catalogVersion + (complement != null && !complement.isEmpty() ? "_" + complement : "") + ".dat";
        File destFile = new File(datDir, fileName);
        try (FileOutputStream out = new FileOutputStream(destFile)) {
            out.write(b);
        }
    }

    protected void saveROMFile(GamePlatform platform, byte[] b) throws Exception {
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
                try (FileOutputStream out = new FileOutputStream(destFile)) {
                    out.write(b);
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

    protected Set<Game> readGamesFile(GamePlatform platform) throws Exception {
        Set<Game> result = new LinkedHashSet<>();
        File xmlFile = getPlatformGameXml(platform);
        if (xmlFile.exists()) {
            // Já existe o arquivo, le.
            System.out.println("Lendo arquivo de games: " + xmlFile.getAbsolutePath());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("game")) {
                    Game game = new Game();
                    for (int ni = 0; ni < node.getAttributes().getLength(); ni++) {
                        Node nv = node.getAttributes().item(ni);
                        switch (nv.getNodeName()) {
                            case "name":
                                game.setName(nv.getNodeValue());
                                break;
                            case "mainName":
                                game.setMainName(nv.getNodeValue());
                                break;
                            case "description":
                                game.setDescription(nv.getNodeValue());
                                break;
                            case "version":
                                game.setVersion(nv.getNodeValue());
                                break;
                            case "year":
                                game.setYear(Integer.parseInt(nv.getNodeValue()));
                                break;
                            case "language":
                                game.setLanguages(GameLanguage.fromNames(nv.getNodeValue()));
                                break;
                            case "region":
                                game.setRegions(GameRegion.fromNames(nv.getNodeValue()));
                                break;
                            case "publisher":
                                game.setPublisher(GamePublisher.fromName(nv.getNodeValue()));
                                break;
                            case "developer":
                                game.setDeveloper(GameDeveloper.fromName(nv.getNodeValue()));
                                break;
                            case "protection":
                                game.setProtection(GameProtection.fromName(nv.getNodeValue()));
                                break;
                            case "loader":
                                game.setLoader(GameLoader.fromName(nv.getNodeValue()));
                                break;
                            case "alternate":
                                game.setAlternate(nv.getNodeValue());
                                break;
                            case "compilation":
                                game.setCompilation(nv.getNodeValue());
                                break;
                            case "complement":
                                for (String s : nv.getNodeValue().split("\t")) {
                                    game.addComplement(s);
                                }
                                break;
                            case "badDump":
                                game.setBadDump(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "fixed":
                                game.setFixed(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "hack":
                                game.setHack(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "overdump":
                                game.setOverdump(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "pirate":
                                game.setPirate(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "trained":
                                game.setTrained(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "oldTranslation":
                                game.setOldTranslation(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "newerTranslation":
                                game.setNewerTranslation(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "verifiedGoodDump":
                                game.setVerifiedGoodDump(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "unlicensed":
                                game.setUnlicensed(nv.getNodeValue().equalsIgnoreCase("true") ? ThreeStates.True : ThreeStates.False);
                                break;
                            case "catalogVersion":
                                game.setCatalogVersion(nv.getNodeValue());
                                break;
                            case "catalog":
                                game.setCatalog(GameCatalog.valueOf(nv.getNodeValue()));
                                break;
                            case "proto":
                                game.setProto(nv.getNodeValue());
                                break;
                            case "beta":
                                game.setBeta(nv.getNodeValue());
                                break;
                            case "demo":
                                game.setDemo(nv.getNodeValue().equalsIgnoreCase("true"));
                                break;
                            case "promo":
                                game.setPromo(nv.getNodeValue().equalsIgnoreCase("true"));
                                break;
                            case "sample":
                                game.setSample(nv.getNodeValue().equalsIgnoreCase("true"));
                                break;
                            case "preview":
                                game.setPreview(nv.getNodeValue().equalsIgnoreCase("true"));
                                break;
                            case "serial":
                                game.setSerial(nv.getNodeValue().trim());
                                break;
                            default:
                                throw new Exception(nv.getNodeName());
                        }
                        NodeList roms = node.getChildNodes();
                        for (int j = 0; j < roms.getLength(); j++) {
                            Node nrom = roms.item(j);
                            if (nrom.getNodeName().equals("rom")) {
                                for (int nir = 0; nir < nrom.getAttributes().getLength(); nir++) {
                                    Node nvr = nrom.getAttributes().item(nir);
                                    GameFile rom = new GameFile(nrom.getAttributes().getNamedItem("name").getNodeValue());
                                    switch (nvr.getNodeName()) {
                                        case "name":
                                            break;
                                        case "size":
                                            rom.setSize(Integer.parseInt(nvr.getNodeValue()));
                                            break;
                                        case "crc":
                                            rom.setCrc(nvr.getNodeValue());
                                            break;
                                        case "md5":
                                            rom.setMd5(nvr.getNodeValue());
                                            break;
                                        case "sha1":
                                            rom.setSha1(nvr.getNodeValue());
                                            break;
                                        case "flags":
                                            rom.setFlags(nvr.getNodeValue());
                                            break;
                                        default:
                                            throw new Exception(nvr.getNodeName());
                                    }
                                    game.addRom(rom);
                                }
                            }
                        }
                    }
                    result.add(game);
                }
            }
        }

        return result;
    }

    protected void addGames(GamePlatform platform, List<Game> newGames) throws Exception {
        Set<Game> games = this.readGamesFile(platform);
        newGames.stream().map((g) -> {
            if (games.contains(g)) {
                games.remove(g);
            }
            return g;
        }).forEach((g) -> {
            games.add(g);
        });
        this.saveGamesFile(platform, games);
    }

    protected void saveGamesFile(GamePlatform platform, Set<Game> games) throws Exception {
        File xmlFile = getPlatformGameXml(platform);
        List<Game> l = new LinkedList<>();
        l.addAll(games);
        Collections.sort(l);
        games.clear();
        games.addAll(l);
        try (FileOutputStream out = new FileOutputStream(xmlFile)) {
            out.write("<?xml version=\"1.0\"?>\n".getBytes());
            out.write("<games>\n".getBytes());
            for (Game g : games) {
                out.write(g.toString().replaceAll(" & ", " &amp; ").replaceAll("M&M", "M&amp;M").replaceAll("W&T", "W&amp;T").getBytes());
            }
            out.write("</games>".getBytes());
        }
    }

    private File getPlatformBaseDir(GamePlatform platform) {
        if (platform.getRepositoryDir().startsWith(".")) {
            return new File(getBaseDir(), platform.getRepositoryDir());
        } else {
            return new File(platform.getRepositoryDir());
        }
    }

    private File getPlatformGameXml(GamePlatform platform) {
        return new File(ConfigManager.getPlatformDatabaseDir(platform), "games.xml");
    }
}
