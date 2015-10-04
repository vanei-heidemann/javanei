/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.factory.Database;
import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.game.GameImporter;
import com.javanei.emulation.emuldb.nointro.NoIntroImporter;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vanei
 */
public class ImportNoIntroDatPanelController implements Initializable {

    private final MessageFactory messageFactory = MessageFactory.getInstance();
    private final GlobalValues globalValues = GlobalValues.getInstance();
    private GamePlatform platform;
    private Database database;

    @FXML
    private TextField platformNameInput;
    @FXML
    private TextField datFilePathInput;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btImport;
    @FXML
    private Button btClose;

    private static Task<GameImporter> importWorker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.platformNameInput.setText(globalValues.getSelectedPlatform().nameProperty().get());
        this.database = DatabaseFactory.getInstance().getDatabase();
        this.platform = this.database.getPlatform(this.globalValues.getSelectedPlatform().nameProperty().get());
    }

    @FXML
    private void handleClose(ActionEvent event) {
        ScreenController.goBack();
    }

    @FXML
    private void handleImport(ActionEvent event) {
        try {
            textArea.setText(""); //TODO: Por uma mensagem de iniciando lendo arquivos.

            progressBar.progressProperty().unbind();
            this.progressBar.progressProperty().set(0);

            File datFile = new File(this.datFilePathInput.getText().trim());

            importWorker = new NoIntroImporter().getImporter(platform, datFile);

            progressBar.progressProperty().bind(importWorker.progressProperty());
            importWorker.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                textArea.setText(newValue);
                textArea.setScrollTop(Double.MAX_VALUE);
            });
            importWorker.runningProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                btImport.setDisable(newValue);
                btClose.setDisable(newValue);
                datFilePathInput.setDisable(newValue);
                if (oldValue && !newValue) {
                    try {
                        System.out.println(importWorker.get());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            new Thread(importWorker).start();
        } catch (Exception ex) {
            this.messageFactory.showErrorMesssage(ex);
        }
    }
}
