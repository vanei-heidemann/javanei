/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.common.GameCatalog;
import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.config.ConfigManager;
import com.javanei.emulation.emuldb.factory.Database;
import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.game.GameImporter;
import com.javanei.emulation.emuldb.game.GameImporterMessage;
import com.javanei.emulation.emuldb.nointro.NoIntroImporter;
import com.javanei.emulation.util.FileUtil;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

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
    private static File lastDir = new File(ConfigManager.getHomeDir());

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
    @FXML
    private Button btChooseFile;

    private static Task<GameImporter> importWorker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.platformNameInput.setText(globalValues.getSelectedPlatform().nameProperty().get());
        this.database = DatabaseFactory.getInstance().getDatabase();
        this.platform = this.database.getPlatform(this.globalValues.getSelectedPlatform().nameProperty().get());
    }

    @FXML
    private void handleChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        if (this.datFilePathInput.getText() != null && !this.datFilePathInput.getText().isEmpty()) {
            File f = new File(this.datFilePathInput.getText().trim());
            if (f.exists()) {
                if (f.isDirectory()) {
                    fileChooser.setInitialDirectory(f);
                } else if (f.isFile()) {
                    fileChooser.setInitialDirectory(f.getParentFile());
                }
            }
        } else if (lastDir != null) {
            fileChooser.setInitialDirectory(lastDir);
        }
        //fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Dat Files", "*.dat")); //TODO: Internacionalizar
        File selectedFile = fileChooser.showOpenDialog(this.globalValues.getStage());
        if (selectedFile != null) {
            this.datFilePathInput.setText(selectedFile.getAbsolutePath());
            lastDir = selectedFile.getParentFile();
        }
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
                textArea.setText(textArea.getText() + "\n" + newValue);
                textArea.setScrollTop(Double.MAX_VALUE);
            });
            importWorker.runningProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                btImport.setDisable(newValue);
                btClose.setDisable(newValue);
                datFilePathInput.setDisable(newValue);
                if (oldValue && !newValue) {
                    try {
                        GameImporter gi = importWorker.get();
                        StringBuilder sb = new StringBuilder();
                        gi.sortMessagesByType().stream().forEach((msg) -> {
                            if (sb.length() > 0) {
                                sb.append("\n");
                            }
                            sb.append(msg.toString());
                        });
                        this.textArea.setText(sb.toString());
                        textArea.setScrollTop(Double.MAX_VALUE);
                        // Salva o catalog
                        String complement = null;
                        if (gi.getDescription().endsWith("(PP)")) {
                            complement = "(PP)";
                        } else if (gi.getDescription().endsWith("(Tapes)")) {
                            complement = "(Tapes)";
                        } else if (gi.getDescription().endsWith("(e-Cards)")) {
                            complement = "(e-Cards)";
                        }
                        DatabaseFactory.getInstance().getDatabase().addDatFile(platform, GameCatalog.GoodSet, gi.getVersion(), complement, FileUtil.readFile(datFile));
                        DatabaseFactory.getInstance().getDatabase().addGames(platform, gi.getGames());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        messageFactory.showErrorMesssage(ex);
                        this.textArea.setText(this.textArea.getText() + "ERROR: " + ex.toString());
                        textArea.setScrollTop(Double.MAX_VALUE);
                    }
                }
            });

            new Thread(importWorker).start();
        } catch (Exception ex) {
            this.messageFactory.showErrorMesssage(ex);
        }
    }
}
