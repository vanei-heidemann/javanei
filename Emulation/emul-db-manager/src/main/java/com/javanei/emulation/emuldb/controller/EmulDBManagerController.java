package com.javanei.emulation.emuldb.controller;

import com.javanei.emulation.emuldb.MessageFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Vanei
 */
public class EmulDBManagerController implements Initializable {
    
    @FXML
    private TextField statusBar;
    @FXML
    private StackPane contentPane;
    
    private final MessageFactory messageFactory = MessageFactory.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO: Tem como fazer via CSS?
        statusBar.prefWidthProperty().bind(((Region) statusBar.getParent()).widthProperty().subtract(10));
        this.statusBar.textProperty().bind(messageFactory.messageProperty());
        
        new ScreenController(contentPane);
        try {
            ScreenController.loadPane("resources/fxml/panel/DefaultPanel.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
    }
    
    @FXML
    private void handleMenuGoBack(ActionEvent event) {
        try {
            ScreenController.goBack();
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
    }
    
    @FXML
    private void handleMenuConfig(ActionEvent event) {
        try {
            ScreenController.loadPane("resources/fxml/panel/ConfigPanel.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
    }
    
    @FXML
    private void handleMenuPlatforms(ActionEvent event) {
        try {
            ScreenController.loadPane("resources/fxml/panel/PlatformsPanel.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.messageFactory.showErrorMesssage(ex);
        }
    }
    
    @FXML
    private void handleMenuExit(ActionEvent event) {
        System.out.println("@@@@ Exit");
        Platform.exit();
    }
}
