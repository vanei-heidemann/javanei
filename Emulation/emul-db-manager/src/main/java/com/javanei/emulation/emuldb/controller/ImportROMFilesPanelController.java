package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import com.javanei.emulation.emuldb.factory.Database;
import com.javanei.emulation.emuldb.factory.DatabaseFactory;
import com.javanei.emulation.emuldb.factory.GamePlatform;
import com.javanei.emulation.emuldb.factory.RepositoryManager;
import com.javanei.emulation.util.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
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
public class ImportROMFilesPanelController implements Initializable {

    private final MessageFactory messageFactory = MessageFactory.getInstance();
    private final GlobalValues globalValues = GlobalValues.getInstance();
    private GamePlatform platform;
    private List<File> files;
    private Database database;

    @FXML
    private TextField platformNameInput;
    @FXML
    private TextField romPathInput;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextArea textArea;
    @FXML
    private Button btImport;
    @FXML
    private Button btClose;

    private static Task copyWorker;

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
        textArea.setText(""); //TODO: Por uma mensagem de iniciando lendo arquivos.

        progressBar.progressProperty().unbind();
        this.progressBar.progressProperty().set(0);
        this.files = new ArrayList();
        try {
            File f = new File(this.romPathInput.getText().trim());
            this.createFileList(f);
        } catch (Exception ex) {
            this.messageFactory.showErrorMesssage(ex);
            return;
        }

        copyWorker = createWorker();
        progressBar.progressProperty().bind(copyWorker.progressProperty());

        copyWorker.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            textArea.setText(newValue);
            textArea.setScrollTop(Double.MAX_VALUE);
        });
        copyWorker.runningProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            btImport.setDisable(newValue);
            btClose.setDisable(newValue);
            romPathInput.setDisable(newValue);
        });

        new Thread(copyWorker).start();
    }

    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < files.size(); i++) {
                    File f = files.get(i);
                    saveFileToRepo(sb, f);
                    // queue up status
                    updateMessage(sb.toString());
                    updateProgress(i + 1, files.size()); // (progress, max)
                }
                return true;
            }
        };
    }

    private void saveFileToRepo(StringBuilder sb, File f) {
        sb.append(f.getAbsolutePath()).append(":");
        if (f.getName().toLowerCase().endsWith(".zip")) {
            try (ZipFile zip = new ZipFile(f)) {
                Enumeration entries = zip.entries();
                sb.append("\n");
                while (entries.hasMoreElements()) {
                    ZipEntry ze = (ZipEntry) entries.nextElement();
                    sb.append("    ").append(ze.getName()).append(": ");
                    try {
                        this.validateROMFileName(ze.getName());
                        InputStream is = zip.getInputStream(ze);
                        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                            byte[] b = new byte[4096];
                            int size = is.read(b);
                            while (size > 0) {
                                out.write(b, 0, size);
                                size = is.read(b);
                            }
                            b = out.toByteArray();
                            database.addROMFile(this.platform, b);
                            sb.append(" OK\n");
                        } catch (FileAlreadyExistsException ex) {
                            sb.append(" OK: ").append(ex.getClass().getName()).append("\n");
                        } catch (Exception ex) {
                            this.appendError(sb, ex);
                        }
                    } catch (Exception ex) {
                        this.appendError(sb, ex);
                    }
                }
            } catch (Exception ex) {
                this.appendError(sb, ex);
            }
        } else {
            try {
                this.validateROMFileName(f.getName());
                try (InputStream is = new FileInputStream(f)) {
                    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                        byte[] b = new byte[4096];
                        int size = is.read(b);
                        while (size > 0) {
                            out.write(b, 0, size);
                            size = is.read(b);
                        }
                        b = out.toByteArray();
                        database.addROMFile(this.platform, b);
                        sb.append(" OK\n");
                    } catch (FileAlreadyExistsException ex) {
                        sb.append(" OK: ").append(ex.getClass().getName()).append("\n");
                    } catch (Exception ex) {
                        this.appendError(sb, ex);
                    }
                } catch (Exception ex) {
                    this.appendError(sb, ex);
                }
            } catch (Exception ex) {
                this.appendError(sb, ex);
            }
        }
    }

    private void validateROMFileName(String name) throws Exception {
        boolean result = false;
        for (String s : this.globalValues.getSelectedPlatform().validExtensionProperty().get().split(",")) {
            if (s.equals("*")) {
                result = true;
                break;
            } else if (name.toLowerCase().endsWith(s)) {
                result = true;
                break;
            }
        }
        if (!result) {
            throw new UnknownROMFileExtensionException(FileUtil.getFileExtension(name));
        }
    }

    private void createFileList(File srcDir) throws Exception {
        File[] fs = srcDir.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                this.createFileList(f);
            } else {
                this.files.add(f);
            }
        }
    }

    private void appendError(StringBuilder sb, Exception ex) {
        sb.append("ERROR: ").append(ex.getClass().getName()).append(": ").append(ex.getLocalizedMessage()).append("\n");
    }
}
