package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.factory.GamePlatformFactory;
import com.javanei.emulation.emuldb.factory.StorageFormat;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vanei
 */
public class PlatformsPanelController implements Initializable {

    private final ObservableList<GamePlatformTableVO> platformList = FXCollections.<GamePlatformTableVO>observableArrayList();
    @FXML
    private TableView<GamePlatformTableVO> platformsTable;
    @FXML
    private TextField platformNameInput;
    @FXML
    private TextField platformReporitoryDirInput;
    @FXML
    private CheckBox platformMultiFileInput;
    @FXML
    private CheckBox platformAllowZipInput;
    @FXML
    private ComboBox<String> platformStorageFormatInput;
    @FXML
    private Button btPlatformNew;
    @FXML
    private Button btPlatformSave;
    @FXML
    private Button btPlatformCancel;
    @FXML
    private Button btPlatformDel;

    private GamePlatformTableVO selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        platformsTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends GamePlatformTableVO> observable, GamePlatformTableVO oldValue, GamePlatformTableVO newValue) -> {
            this.selectedItem = newValue;
            if (observable != null && observable.getValue() != null) {
                showSelectedItem(newValue);
                btPlatformDel.setDisable(false);
            } else {
                btPlatformDel.setDisable(true);
            }
        });

        List<GamePlatform> plats = DatabaseFactory.getInstance().getDatabase().getPlatforms();
        plats.stream().forEach((plat) -> {
            this.platformList.add(new GamePlatformTableVO(plat));
        });
        platformsTable.setItems(this.platformList);
        if (!this.platformList.isEmpty()) {
            platformsTable.getSelectionModel().select(0);
        }
    }

    @FXML
    private void handlePlatformNew(ActionEvent event) {
        btPlatformNew.setDisable(true);
        btPlatformDel.setDisable(true);
        btPlatformSave.setDisable(false);
        btPlatformCancel.setDisable(false);
        this.enableDisableFields(false);
        this.showDefaultValues();
        this.platformNameInput.requestFocus();
    }

    @FXML
    private void handlePlatformDel(ActionEvent event) {
        try {
            GamePlatformFactory.getInstance().removePlatform(this.selectedItem.nameProperty().get());
            this.platformsTable.getItems().remove(this.selectedItem);
        } catch (Exception ex) {
            //TODO: Tratar exception
            ex.printStackTrace();
        }
    }

    @FXML
    private void handlePlatformCancel(ActionEvent event) {
        btPlatformNew.setDisable(false);
        btPlatformCancel.setDisable(true);
        btPlatformSave.setDisable(true);
        GamePlatformTableVO item = platformsTable.getSelectionModel().getSelectedItem();
        btPlatformDel.setDisable(item == null);
        if (item != null) {
            showSelectedItem(item);
        } else {
            this.showDefaultValues();
        }
        this.enableDisableFields(true);
    }

    @FXML
    private void handlePlatformSave(ActionEvent event) {
        try {
            GamePlatform plat = GamePlatformFactory.getInstance().createPlatform(this.platformNameInput.getText(),
                    this.platformReporitoryDirInput.getText(),
                    this.platformMultiFileInput.isSelected(),
                    this.platformAllowZipInput.isSelected(),
                    StorageFormat.valueOf(this.platformStorageFormatInput.getValue()));
            this.platformList.add(new GamePlatformTableVO(plat));
            this.enableDisableFields(true);

            btPlatformNew.setDisable(false);
            btPlatformDel.setDisable(false);
            btPlatformSave.setDisable(true);
            btPlatformCancel.setDisable(true);
        } catch (Exception ex) {
            //TODO: Tratar exception
            ex.printStackTrace();
        }
    }
    @FXML
    private void handlePlatformClose(ActionEvent event) {
        ScreenController.goBack();
    }
    
    private void showDefaultValues() {
        this.platformNameInput.setText("");
        this.platformReporitoryDirInput.setText("./{PlatformName}");
        this.platformMultiFileInput.setSelected(false);
        this.platformAllowZipInput.setSelected(true);
        this.platformStorageFormatInput.setValue(StorageFormat.zip.name());
    }

    private void showSelectedItem(GamePlatformTableVO item) {
        this.platformNameInput.setText(item.nameProperty().get());
        this.platformReporitoryDirInput.setText(item.repositoryDirProperty().get());
        this.platformMultiFileInput.setSelected(item.multiFileProperty().get());
        this.platformAllowZipInput.setSelected(item.allowZipProperty().get());
        this.platformStorageFormatInput.setValue(item.storageFormatProperty().get());
    }

    private void enableDisableFields(boolean disable) {
        this.platformNameInput.setDisable(disable);
        this.platformNameInput.setEditable(!disable);
        this.platformReporitoryDirInput.setDisable(disable);
        this.platformReporitoryDirInput.setEditable(!disable);
        this.platformMultiFileInput.setDisable(disable);
        this.platformAllowZipInput.setDisable(disable);
        this.platformStorageFormatInput.setDisable(disable);
        this.platformsTable.setDisable(!disable);
    }
}
