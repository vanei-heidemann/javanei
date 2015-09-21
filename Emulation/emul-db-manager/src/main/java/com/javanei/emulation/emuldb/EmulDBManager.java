package com.javanei.emulation.emuldb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Vanei
 */
public class EmulDBManager extends Application {

    private Stage stage;
    private Parent root;
    private TextField statusBar;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/fxml/EmulDBManager.fxml"));
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
        this.statusBar = (TextField) scene.lookup("#statusBar");

        this.statusBar.setText("Inicializando");
    }
}
