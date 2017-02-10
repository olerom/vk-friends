package com.olerom.vk.gui;

/**
 * Date: 10.02.17
 *
 * @author olerom
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Main extends Application {

    private static ResourceBundle bundle;
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setCharset(StandardCharsets.UTF_8);
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/main.fxml"));

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Social graph builder");
        primaryStage.setMinWidth(1050);
        primaryStage.setMinHeight(600);
        primaryStage.setWidth(1050);
        primaryStage.setHeight(700);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
