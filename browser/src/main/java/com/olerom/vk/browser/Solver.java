package com.olerom.vk.browser;

import com.olerom.vk.core.User;

/**
 * Created by olerom on 01.02.17.
 */
public class Solver {
    private User user;



    public void run(String code) {
        try {
             user = new User(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }
}
