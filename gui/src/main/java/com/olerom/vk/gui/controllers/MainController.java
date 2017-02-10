package com.olerom.vk.gui.controllers;

import com.olerom.vk.core.VkAdapter;
import com.olerom.vk.core.VkGraph;
import com.olerom.vk.core.VkRequest;
import com.vk.api.sdk.objects.friends.UserXtrLists;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 10.02.17
 *
 * @author olerom
 */
public class MainController {
    @FXML
    private Hyperlink authUri;

    @FXML
    private Button buildButton;

    @FXML
    private TextField codeField;

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        VkRequest request = new VkRequest();
        authUri.setText(request.getAuthURL());
        buildButton.setOnAction(e -> run(codeField.getText()));
        authUri.setOnAction(t -> System.out.println(authUri.getText()));
    }

    private void run(String code) {

        VkAdapter vk = null;
        try {
            vk = new VkAdapter(code);
        } catch (Exception e) {
            System.out.println("Problem with creating VkAdapter, check code value.");
            e.printStackTrace();
//            System.exit(1);
        }

        try {
//            List<UserXtrLists> myFriends = vk.getFriends();
//            VkGraph<UserXtrLists> graph = new VkGraph<>(myFriends);

//            graph.build(vk);

            GraphicsContext owner = canvas.getGraphicsContext2D();
            owner.setFill(Color.CYAN);
            owner.fillOval(canvas.getWidth() / 2, canvas.getHeight() / 2, 50, 50);

            List<GraphicsContext> friends = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.setFill(Color.GREEN);
                graphicsContext.setLineWidth(7);
                handleAngle(graphicsContext);
//                graphicsContext.fillOval(50, 110, 30, 30);
                friends.add(graphicsContext);
            }

        } catch (Exception e) {
            System.out.println("Problem with graph building.");
            e.printStackTrace();
            System.exit(1);
        }
    }


    private void handleAngle(GraphicsContext graphicsContext) {
        double d = Math.random();
        graphicsContext.fillOval(canvas.getWidth() / 2 + Math.sin(d * 2 * Math.PI) * 200,
                canvas.getHeight() / 2 + Math.cos(d * 2 * Math.PI) * 200,
                20, 20);
    }
}
