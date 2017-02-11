package com.olerom.vk.gui.controllers;

import com.olerom.vk.gui.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Date: 11.02.17
 *
 * @author olerom
 */
public class GraphDrawController {
    @FXML
    private Canvas canvas;

    private Stage stage;

    private Parent parent;


    public void load() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setCharset(StandardCharsets.UTF_8);

        fxmlLoader.setLocation(getClass().getClassLoader().getResource("fxml/graph.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        canvas.getGraphicsContext2D().fillOval(30, 30, 30, 30);
    }

}
