package com.olerom.vk.gui.controllers;

import com.olerom.vk.core.VkAdapter;
import com.olerom.vk.core.VkGraph;
import com.olerom.vk.core.VkRequest;
import com.vk.api.sdk.objects.friends.UserXtrLists;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    private TextArea testOutput;

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
            testOutput.appendText("Problem with creating VkAdapter, check code value.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            List<UserXtrLists> myFriends = vk.getFriends();
            VkGraph<UserXtrLists> graph = new VkGraph<>(myFriends);
            graph.build(vk);
            testOutput.appendText(graph.toString());
        } catch (Exception e) {
            testOutput.appendText("Problem with graph building.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
