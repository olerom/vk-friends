package com.olerom.vk.cli;

import com.olerom.vk.core.RequestVK;
import com.olerom.vk.core.User;
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
            List<UserXtrLists> myFriends = null;
            myFriends = user.getFriends();
            for (UserXtrLists friend : myFriends) {
                System.out.println(friend.getFirstName() + " " +
                        friend.getLastName() + ": " +
                        (friend.getCity() == null ? friend.getCity() : friend.getCity().getTitle()) + ", " +
                        friend.getBdate() + ", " + friend.isIsFriend());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void help() {
    }
}
