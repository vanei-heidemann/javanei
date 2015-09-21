package com.javanei.emulation.common.hyperspin.util;

import com.javanei.emulation.common.hyperspin.HSGame;
import com.javanei.emulation.common.hyperspin.HSMainMenu;
import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.common.hyperspin.HyperSpinConf;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vanei on 29/08/2015.
 */
public class HSReadConfigs {
    public static HSMainMenu readMainMenu() throws Exception {
        File mm = new File(HyperSpinConf.HS_MAIN_MENU);
        HSMainMenu result = new HSMainMenu();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(mm);

        NodeList gameList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < gameList.getLength(); i++) {
            Node node = gameList.item(i);
            if (node.getNodeName().equals("game")) {
                result.games.add(node.getAttributes().getNamedItem("name").getNodeValue());
            }
        }

        return result;
    }

    public static HSSystem getSystem(String systemName) throws Exception {
        File db = new File(HyperSpinConf.HS_DATABASE_DIR + "/" + systemName + "/" + systemName + ".xml");
        boolean validateCRC = true;

        // Procura pelo valor do CRC primeiro game no XML. Se estiver preenchido, valida CRC.
        HSSystem result = new HSSystem(systemName, validateCRC, true);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(db);

        NodeList gameList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < gameList.getLength(); i++) {
            Node node = gameList.item(i);
            if (node.getNodeName().equals("game")) {
                NodeList nlList = node.getChildNodes();
                for (int j = 0; j < nlList.getLength(); j++) {
                    Node tag = nlList.item(j);
                    if (tag.getNodeName().equals("crc")) {
                        validateCRC = (tag.getTextContent() != null && !tag.getTextContent().toUpperCase().trim().isEmpty());
                        break;
                    }
                }
                break;
            } else if (node.getNodeName().equals("header")) {
                NodeList nlList = node.getChildNodes();
                for (int j = 0; j < nlList.getLength(); j++) {
                    Node tag = nlList.item(j);
                    if (tag.getNodeName().equals("listname")) {
                        result.listname = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("lastlistupdate")) {
                        result.lastlistupdate = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("listversion")) {
                        result.listversion = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("exporterversion")) {
                        result.exporterversion = tag.getTextContent().trim();
                    }
                }
            }
        }

        return result;
    }

    public static List<HSGame> listGames(HSSystem system) throws Exception {
        File db = new File(system.dbFile);
        List<HSGame> result = new LinkedList<HSGame>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(db);

        NodeList gameList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < gameList.getLength(); i++) {
            Node node = gameList.item(i);
            if (node.getNodeName().equals("game")) {
                HSGame game = new HSGame();
                result.add(game);
                game.name = node.getAttributes().getNamedItem("name").getNodeValue();
                game.image = node.getAttributes().getNamedItem("image").getNodeValue();
                game.index = node.getAttributes().getNamedItem("index").getNodeValue();
                NodeList nlList = node.getChildNodes();
                for (int j = 0; j < nlList.getLength(); j++) {
                    Node tag = nlList.item(j);
                    if (tag.getNodeName().equals("description")) {
                        game.description = tag.getTextContent();
                    } else if (tag.getNodeName().equals("crc")) {
                        game.crc = tag.getTextContent().toUpperCase().trim();
                    } else if (tag.getNodeName().equals("cloneof")) {
                        game.cloneof = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("manufacturer")) {
                        game.manufacturer = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("year")) {
                        game.year = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("genre")) {
                        game.genre = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("rating")) {
                        game.rating = tag.getTextContent().trim();
                    } else if (tag.getNodeName().equals("enabled")) {
                        game.enabled = tag.getTextContent() != null &&
                                (tag.getTextContent().trim().equalsIgnoreCase("yes") || tag.getTextContent().trim().equalsIgnoreCase("true"));
                    }
                }
            }
        }

        return result;
    }
}
