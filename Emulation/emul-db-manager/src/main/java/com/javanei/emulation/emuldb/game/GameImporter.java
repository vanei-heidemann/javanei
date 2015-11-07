package com.javanei.emulation.emuldb.game;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vanei
 */
public class GameImporter {

    private String name;
    private String description;
    private String version;
    private String comment;
    private List<Game> games;
    private final List<GameImporterMessage> messages = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<GameImporterMessage> getMessages() {
        return messages;
    }

    public void addMessage(GameImporterMessage msg) {
        this.messages.add(msg);
    }

    public void addMessages(List<GameImporterMessage> msgs) {
        this.messages.addAll(msgs);
    }

    public List<GameImporterMessage> sortMessagesByType() {
        Collections.sort(this.messages, new Comparator<GameImporterMessage>() {

            @Override
            public int compare(GameImporterMessage o1, GameImporterMessage o2) {
                int result = 0;
                if (o1.isError()) {
                    if (o2.isError()) {
                        result = 0;
                    } else {
                        result = 1;
                    }
                } else if (o1.isWarn()) {
                    if (o2.isError()) {
                        result = -1;
                    } else if (o2.isWarn()) {
                        result = 0;
                    } else {
                        result = 1;
                    }
                } else {
                    if (o2.isInfo()) {
                        result = 0;
                    } else {
                        result = -1;
                    }
                }
                return result;
            }
        });
        return this.messages;
    }

    @Override
    public String toString() {
        return "GameImporter {" + "name=" + name + ", description=" + description + ", version=" + version + ", comment=" + comment + ", games=" + games + '}';
    }
}
