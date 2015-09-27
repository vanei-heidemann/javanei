package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Vanei
 */
public class DefaultPanelController implements Initializable {

    private final ObservableList<GamePlatformTableVO> platformMainList = FXCollections.<GamePlatformTableVO>observableArrayList();
    @FXML
    private TableView<GamePlatformTableVO> platformsMainTable;
    @FXML
    private TableColumn platformColName;

    private GamePlatformTableVO selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.platformColName.prefWidthProperty().bind(platformsMainTable.widthProperty().subtract(2));
        loadPlatforms();
    }

    @FXML
    private void handleVisibleChange(Event event) {
        if (((Node) event.getSource()).isVisible()) {
            loadPlatforms();
        }
    }

    private void loadPlatforms() {
        platformsMainTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends GamePlatformTableVO> observable, GamePlatformTableVO oldValue, GamePlatformTableVO newValue) -> {
            this.selectedItem = newValue;
        });

        List<GamePlatform> plats = DatabaseFactory.getInstance().getDatabase().getPlatforms();
        platformMainList.clear();
        plats.stream().forEach((plat) -> {
            this.platformMainList.add(new GamePlatformTableVO(plat));
        });
        platformsMainTable.setItems(this.platformMainList);
        if (!this.platformMainList.isEmpty()) {
            platformsMainTable.getSelectionModel().select(0);
        }
    }
}
