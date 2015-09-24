package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.config.Config;
import com.javanei.emulation.emuldb.config.ConfigManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vanei
 */
public class ConfigPanelController implements Initializable {

    private final MessageFactory messageFactory = MessageFactory.getInstance();

    @FXML
    private TextField repositoryBaseDirInput;
    @FXML
    private TextField hyperspinBaseDirInput;
    @FXML
    private TextField hyperspinROMBaseDirInput;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Config cfg = ConfigManager.getConfig();
        this.repositoryBaseDirInput.setText(cfg.getRepositoryBaseDir());
        this.hyperspinBaseDirInput.setText(cfg.getHyperspinBaseDir());
        this.hyperspinROMBaseDirInput.setText(cfg.getHyperspinROMBaseDir());
    }

    @FXML
    private void handleConfigSave(ActionEvent event) {
        Config cfg = ConfigManager.getConfig();
        cfg.setRepositoryBaseDir(this.repositoryBaseDirInput.getText().trim());
        cfg.setHyperspinBaseDir(this.hyperspinBaseDirInput.getText().trim());
        cfg.setHyperspinROMBaseDir(this.hyperspinROMBaseDirInput.getText().trim());
        try {
            ConfigManager.saveConfig();
        } catch (Exception ex) {
            messageFactory.showErrorMesssage(ex);
            ex.printStackTrace();
            //TODO: Tratar exception
        }
        ScreenController.goBack();
    }

    @FXML
    private void handleConfigCancel(ActionEvent event) {
        ScreenController.goBack();
    }
}
