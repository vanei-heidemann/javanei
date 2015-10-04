package com.javanei.emulation.emuldb;

import com.javanei.emulation.emuldb.controller.GlobalValues;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Vanei
 */
public class EmulDBManager extends Application {

    private final MessageFactory messageFactory = MessageFactory.getInstance();
    private final GlobalValues globalValues = GlobalValues.getInstance();

    private Stage stage;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.globalValues.setStage(stage);
        this.stage.setTitle("EmulDB Manager");

        URL location = getClass().getClassLoader().getResource("resources/fxml/EmulDBManager.fxml");
        FXMLLoader loader = new FXMLLoader(location, ResourceBundle.getBundle("resources/i18n/EmulDB", Locale.getDefault()));
        root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setOnShown((we) -> initialize());
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initialize() {
        System.out.println("############## initialize");
        Scene scene = stage.getScene();
        this.messageFactory.showInfoMessage("Inicializando");
        /*
         this.statusBar = (TextField) scene.lookup("#statusBar");

         this.statusBar.setText("Inicializando");
         */
    }
}
