package com.javanei.emulation.emuldb.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * @author Vanei
 */
public class GlobalValues {

    private static final GlobalValues instance = new GlobalValues();

    private final ObservableList<GamePlatformTableVO> platformList = FXCollections.<GamePlatformTableVO>observableArrayList();
    private final ObservableList<GameTableVO> gameList = FXCollections.<GameTableVO>observableArrayList();
    private final ObservableList<GameRegionTableVO> gameRegionList = FXCollections.<GameRegionTableVO>observableArrayList();
    private GamePlatformTableVO selectedPlatform;
    private GameTableVO selectedGame;
    private Stage stage;

    private GlobalValues() {
    }

    public static GlobalValues getInstance() {
        return instance;
    }

    public GamePlatformTableVO getSelectedPlatform() {
        return selectedPlatform;
    }

    public void setSelectedPlatform(GamePlatformTableVO selectedPlatform) {
        this.selectedPlatform = selectedPlatform;
    }
    
    public GameTableVO getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(GameTableVO selectedGame) {
        this.selectedGame = selectedGame;
    }

    public ObservableList<GamePlatformTableVO> getPlatformList() {
        return platformList;
    }
    
    public ObservableList<GameTableVO> getGameList() {
        return gameList;
    }
    
    public ObservableList<GameRegionTableVO> getGameRegionList() {
        return gameRegionList;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
