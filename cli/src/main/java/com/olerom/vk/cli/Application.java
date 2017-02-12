package com.olerom.vk.cli;

import com.olerom.vk.core.VkAdapter;
import com.olerom.vk.core.VkGraph;
import com.olerom.vk.core.VkRequest;
import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.List;
import java.util.Scanner;

/**
 * Date: 01.02.17
 *
 * @author olerom
 */
public class Application {
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Application().switcher();
    }

    private void switcher() {
        System.out.print(">>");
        switch (scanner.next()) {
            case "run":
                run();
                break;
            case "help":
                help();
                break;
            case "exit":
                exit();
                break;
            default:
                System.out.println("Wrong command. Try again.");
                switcher();
                break;
        }
    }

    private void run() {
        System.out.println("CLI: vk_friends social graph");
        VkRequest vkRequest = new VkRequest();

        System.out.println("You can accept permissions at: ");
        System.out.println(vkRequest.getAuthURL());

        System.out.print("Enter code value: ");
        String code = scanner.next();

        VkAdapter vk = null;
        try {
            vk = new VkAdapter(code);
        } catch (Exception e) {
            System.out.println("Problem with creating VkAdapter, check code value.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            VkGraph<UserXtrLists> graph = new VkGraph<>(vk);
            graph.build();
            System.out.println(graph);
        } catch (Exception e) {
            System.out.println("Problem with graph building.");
            e.printStackTrace();
            System.exit(1);
        }
        switcher();
    }

    private void help() {
        System.out.println("Command to run application: run");
        System.out.println("Command to exit: exit");
        System.out.println("Code will be sent as a GET parameter.");
        System.out.println("Code can be used in one hour.");
        switcher();
    }

    private void exit() {
        System.exit(0);
    }
}
