package com.javanei.emulation.emuldb.controller;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * @author Vanei
 */
public class ScreenController {

    private static StackPane mainPane;
    private static Parent currentPanel = null;
    private static final Stack<Parent> stack = new Stack<>();
    private static final Stack<String> stackFile = new Stack<>();

    public ScreenController(StackPane pane) {
        mainPane = pane;
    }

    public static void loadPane(String fxmlFile) throws Exception {
        if (!stackFile.isEmpty() && stackFile.peek().equals(fxmlFile)) {
            return;
        }
        URL location = ScreenController.class.getClassLoader().getResource(fxmlFile);
        FXMLLoader loader = new FXMLLoader(location, ResourceBundle.getBundle("resources/i18n/EmulDB", Locale.getDefault()));
        Parent n = loader.load();

        if (currentPanel != null) {
            currentPanel.setVisible(false);
        }
        currentPanel = n;
        mainPane.getChildren().add(n);
        n.setVisible(true);
        stack.push(n);
        stackFile.push(fxmlFile);
    }

    public static void goBack() {
        if (stack.size() > 1) {
            mainPane.getChildren().remove(stack.pop());
            stackFile.pop();
            Parent p = stack.peek();
            if (p != currentPanel) {
                currentPanel.setVisible(false);
                p.setVisible(true);
                currentPanel = p;
            }
        }
    }
}
