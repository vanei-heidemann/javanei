package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.platformColName.prefWidthProperty().bind(platformsMainTable.widthProperty().subtract(2));
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
}
