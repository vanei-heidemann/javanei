package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.factory.GamePlatformFactory;
import com.javanei.emulation.emuldb.factory.StorageFormat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    
    private final MessageFactory messageFactory = MessageFactory.getInstance();
    private final GlobalValues globalValues = GlobalValues.getInstance();
    
    @FXML
    private TableView<GamePlatformTableVO> platformsTable;
    @FXML
    private TextField platformNameInput;
    @FXML
    private TextField platformReporitoryDirInput;
    @FXML
    private TextField validExtensionInput;
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
    
    private boolean isAdding = false;
    private GamePlatformTableVO item;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        platformsTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends GamePlatformTableVO> observable, GamePlatformTableVO oldValue, GamePlatformTableVO newValue) -> {
            this.globalValues.setSelectedPlatform(newValue);
            if (observable != null && observable.getValue() != null) {
                showSelectedItem(newValue);
                btPlatformDel.setDisable(false);
            } else {
                btPlatformDel.setDisable(true);
            }
        });
        
        platformsTable.setItems(this.globalValues.getPlatformList());
        if (!this.globalValues.getPlatformList().isEmpty()
                && this.globalValues.getSelectedPlatform() != null) {
            platformsTable.getSelectionModel().select(this.globalValues.getSelectedPlatform());
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
        this.isAdding = true;
    }
    
    @FXML
    private void handlePlatformDel(ActionEvent event) {
        try {
            GamePlatformFactory.getInstance().removePlatform(this.globalValues.getSelectedPlatform().nameProperty().get());
            this.platformsTable.getItems().remove(this.globalValues.getSelectedPlatform());
        } catch (Exception ex) {
            //TODO: Tratar exception
            this.messageFactory.showErrorMesssage(ex);
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handlePlatformCancel(ActionEvent event) {
        btPlatformNew.setDisable(false);
        btPlatformCancel.setDisable(true);
        btPlatformSave.setDisable(this.item == null);
        btPlatformDel.setDisable(this.item == null);
        if (item != null) {
            showSelectedItem(this.item);
        } else {
            this.showDefaultValues();
        }
        this.enableDisableFields(true);
        this.isAdding = false;
    }
    
    @FXML
    private void handlePlatformSave(ActionEvent event) {
        try {
            if (this.isAdding) {
                GamePlatform plat = GamePlatformFactory.getInstance().createPlatform(this.platformNameInput.getText(),
                        this.validExtensionInput.getText(),
                        this.platformReporitoryDirInput.getText(),
                        this.platformMultiFileInput.isSelected(),
                        this.platformAllowZipInput.isSelected(),
                        StorageFormat.valueOf(this.platformStorageFormatInput.getValue()));
                this.globalValues.getPlatformList().add(new GamePlatformTableVO(plat));
                this.enableDisableFields(true);
                
                btPlatformNew.setDisable(false);
                btPlatformDel.setDisable(false);
                btPlatformCancel.setDisable(true);
                this.isAdding = false;
            } else {
                GamePlatformFactory.getInstance().updatePlatform(item.nameProperty().get(), this.validExtensionInput.getText());
                this.item.validExtensionProperty().set(this.validExtensionInput.getText());
                this.btPlatformSave.setDisable(this.item == null);
            }
        } catch (Exception ex) {
            //TODO: Tratar exception
            this.messageFactory.showErrorMesssage(ex);
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void handlePlatformClose(ActionEvent event) {
        ScreenController.goBack();
    }
    
    @FXML
    private void handleValidExtensionChanged(Event e) {
        if (validExtensionInput.getText().contains("*")) {
            validExtensionInput.setText("*");
        }
    }
    
    private void showDefaultValues() {
        this.platformNameInput.setText("");
        this.validExtensionInput.setText("*");
        this.platformReporitoryDirInput.setText("./{PlatformName}");
        this.platformMultiFileInput.setSelected(false);
        this.platformAllowZipInput.setSelected(true);
        this.platformStorageFormatInput.setValue(StorageFormat.zip.name());
    }
    
    private void showSelectedItem(GamePlatformTableVO item) {
        this.item = item;
        this.platformNameInput.setText(item.nameProperty().get());
        this.validExtensionInput.setText(item.validExtensionProperty().get());
        this.platformReporitoryDirInput.setText(item.repositoryDirProperty().get());
        this.platformMultiFileInput.setSelected(item.multiFileProperty().get());
        this.platformAllowZipInput.setSelected(item.allowZipProperty().get());
        this.platformStorageFormatInput.setValue(item.storageFormatProperty().get());
    }
    
    private void enableDisableFields(boolean disable) {
        this.platformNameInput.setDisable(disable);
        this.platformNameInput.setEditable(!disable);
        this.validExtensionInput.setDisable(this.item == null && !this.isAdding);
        this.validExtensionInput.setEditable(this.item != null || this.isAdding);
        this.platformReporitoryDirInput.setDisable(disable);
        this.platformReporitoryDirInput.setEditable(!disable);
        this.platformMultiFileInput.setDisable(disable);
        this.platformAllowZipInput.setDisable(disable);
        this.platformStorageFormatInput.setDisable(disable);
        this.platformsTable.setDisable(!disable);
    }
}
