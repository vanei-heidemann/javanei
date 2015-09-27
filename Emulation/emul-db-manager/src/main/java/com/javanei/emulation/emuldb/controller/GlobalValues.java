package com.javanei.emulation.emuldb.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Vanei
 */
public class GlobalValues {

    private static final GlobalValues instance = new GlobalValues();

    private final ObservableList<GamePlatformTableVO> platformList = FXCollections.<GamePlatformTableVO>observableArrayList();
    private GamePlatformTableVO selectedPlatform;

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

    public ObservableList<GamePlatformTableVO> getPlatformList() {
        return platformList;
    }
}
