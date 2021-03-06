package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.factory.Database;
import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.game.Game;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @author Vanei
 */
public class DefaultPanelController implements Initializable {

    private final MessageFactory messageFactory = MessageFactory.getInstance();
    private final GlobalValues globalValues = GlobalValues.getInstance();

    @FXML
    private TableView<GamePlatformTableVO> platformsMainTable;
    @FXML
    private TableColumn platformColName;

    @FXML
    private TableView<GameTableVO> gamesMainTable;
    @FXML
    private TableColumn gameNameCol;
    
    @FXML
    private TableView<GameRegionTableVO> gameRegionsMainTable;
    @FXML
    private TableColumn gameRegionCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.platformColName.prefWidthProperty().bind(platformsMainTable.widthProperty().subtract(2));
        this.gameNameCol.prefWidthProperty().bind(gamesMainTable.widthProperty().subtract(2));
        this.gameRegionCol.prefWidthProperty().bind(gameRegionsMainTable.widthProperty().subtract(2));
        loadPlatforms();
    }

    @FXML
    private void handleVisibleChange(Event event) {
        if (((Node) event.getSource()).isVisible()) {
            if (!this.globalValues.getPlatformList().isEmpty() && this.globalValues.getSelectedPlatform() != null) {
                platformsMainTable.getSelectionModel().select(this.globalValues.getSelectedPlatform());
            }
        }
    }

    @FXML
    private void handleImportROMFiles(ActionEvent event) {
        try {
            ScreenController.loadPane("resources/fxml/panel/ImportROMFilesPanel.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
    }

    @FXML
    private void handleImportNoIntroDAt(ActionEvent event) {
        try {
            ScreenController.loadPane("resources/fxml/panel/ImportNoIntroDatPanel.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
    }

    private void loadPlatforms() {
        platformsMainTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends GamePlatformTableVO> observable, GamePlatformTableVO oldValue, GamePlatformTableVO newValue) -> {
            this.globalValues.setSelectedPlatform(newValue);
            this.loadGames();
        });

        List<GamePlatform> plats = DatabaseFactory.getInstance().getDatabase().getPlatforms();
        this.globalValues.getPlatformList().clear();
        plats.stream().forEach((plat) -> {
            this.globalValues.getPlatformList().add(new GamePlatformTableVO(plat));
        });
        platformsMainTable.setItems(this.globalValues.getPlatformList());
        if (!this.globalValues.getPlatformList().isEmpty()) {
            platformsMainTable.getSelectionModel().select(0);
        }
    }

    private void loadGames() {
        gamesMainTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends GameTableVO> observable, GameTableVO oldValue, GameTableVO newValue) -> {
            this.globalValues.setSelectedGame(newValue);
            this.loadGameRegions();
        });
        
        Set<Game> games = null;
        
        try {
            Database database = DatabaseFactory.getInstance().getDatabase();            
            //this.platform = this.database.getPlatform(this.globalValues.getSelectedPlatform().nameProperty().get());            
            games = database.getGames(database.getPlatform(this.globalValues.getSelectedPlatform().nameProperty().get()));
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
        
        this.globalValues.getGameList().clear();
        if (games != null) {
            for (Iterator<Game> iterator = games.iterator(); iterator.hasNext();) {
                Game game = iterator.next();
                this.globalValues.getGameList().add(new GameTableVO(game.getMainName()));
            }
            gamesMainTable.setItems(this.globalValues.getGameList());
            if (!this.globalValues.getGameList().isEmpty()) {
                gamesMainTable.getSelectionModel().select(0);
            }
        }
        
    }
    
    private void loadGameRegions() {
        this.globalValues.getGameRegionList().clear();
        for (int i = 0; i < 10; i++) {
            this.globalValues.getGameRegionList().add(new GameRegionTableVO("Region " + i));
        }
        gameRegionsMainTable.setItems(this.globalValues.getGameRegionList());
            if (!this.globalValues.getGameRegionList().isEmpty()) {
                gameRegionsMainTable.getSelectionModel().select(0);
            }
    }
}
