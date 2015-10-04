package com.javanei.emulation.emuldb.game;

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

    @Override
    public String toString() {
        return "GameImporter {" + "name=" + name + ", description=" + description + ", version=" + version + ", comment=" + comment + ", games=" + games + '}';
    }
}
