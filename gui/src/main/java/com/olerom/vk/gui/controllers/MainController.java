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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
    private ImageView imageView;

    @FXML
    private void initialize() {
        VkRequest request = new VkRequest();
        authUri.setText(request.getAuthURL());
        buildButton.setOnAction(e -> run(codeField.getText()));
        authUri.setOnAction(t -> {
            try {
                new ProcessBuilder("x-www-browser", authUri.getText()).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(authUri.getText());
        });

    }

    private void run(String code) {
        imageView.setImage(new Image("img/ripple.gif"));
        new Thread(() -> {
            VkAdapter vk = null;
            try {
                vk = new VkAdapter(code);
            } catch (Exception e) {
                System.err.println("Problem with creating VkAdapter, check code value.");
                e.printStackTrace();
                System.exit(1);
            }

            try {
                List<UserXtrLists> myFriends = vk.getFriends();
                VkGraph<UserXtrLists> graph = new VkGraph<>(myFriends, vk);

                graph.build();
                imageView.setImage(null);

//                GraphicsContext owner = canvas.getGraphicsContext2D();
//                owner.setFill(Color.rgb(87, 128, 217));
//                owner.fillOval(canvas.getWidth() / 2 - 25, canvas.getHeight() / 2 - 25, 50, 50);

                List<GraphicsContext> friends = new ArrayList<>();

                for (int i = 0; i < graph.size(); i++) {
                    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                    graphicsContext.setFill(Color.rgb(80, 173, 244));
//                    graphicsContext.setLineWidth(7);
                    UserXtrLists friend = graph.getNode(i);
                    handleAngle(graphicsContext, friend);
                    friends.add(graphicsContext);

                    graphicsContext.beginPath();
                }

            } catch (Exception e) {
                System.err.println("Problem with graph building.");
                e.printStackTrace();
                System.exit(1);
            }
        }).start();
    }


    private void handleAngle(GraphicsContext graphicsContext, UserXtrLists friend) {
        double d = Math.random();

        double x = canvas.getWidth() / 2 +
                Math.sin(Math.random() * 2 * Math.PI) * 230
                - Math.random() * 130;
        double y = canvas.getHeight() / 2 +
                Math.cos(Math.random() * 2 * Math.PI) * 180
                - Math.random() * 80;
        graphicsContext.fillOval(x,
                y,
                4, 4);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFont(Font.font(5));
        graphicsContext.fillText(friend.getFirstName() + " " + friend.getLastName(), x, y);


        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.moveTo(canvas.getWidth() / 2 + 2, canvas.getHeight() / 2 + 2);
        gc.lineTo(x, y);
        gc.setFill(Color.rgb(201, 244, 242));
        gc.stroke();
    }
}
