package com.olerom.vk.cli;

import com.olerom.vk.core.RequestVK;
import com.olerom.vk.core.User;
import com.olerom.vk.core.VkGraph;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.List;
import java.util.Scanner;

/**
 * Created by olerom on 01.02.17.
 */
public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        System.out.println("CLI: vk_friends graph");
        RequestVK requestVK = new RequestVK();

        System.out.println("You can accept permissions at: ");
        System.out.println(requestVK.getAuthURL());

        System.out.println("Enter code value: ");
        System.out.print(">>");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();

        User user = null;
        try {
            user = new User(code);
        } catch (Exception e) {
            System.out.println("Problem with getting UserActor.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            List<UserXtrLists> myFriends = user.getFriends();
            VkGraph graph = new VkGraph();
            for (UserXtrLists friend : myFriends) {
                graph.addVertex(friend);
            }

            for (int i = 0; i < myFriends.size(); i++){
                System.out.println("\n" + myFriends.get(i).getLastName() + " has so much friends: ");
                for (int j = 0; j < myFriends.size(); j++){
                    if (user.isFriend(myFriends.get(i), myFriends.get(j).getId())){
//                        graph.addEdge(myFriends.get(i), myFriends.get(j));
                        System.out.print(myFriends.get(j).getLastName() + ", ");
                    }
                }
            }

            user.test();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void help() {
    }
}
