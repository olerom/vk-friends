package com.olerom.vk.browser;

/**
 * Created by olerom on 01.02.17.
 */
public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        MyServer myServer = new MyServer();
//        myServer.start();
        try {
            myServer.initServer("4fab94e265cbf8d41e");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
