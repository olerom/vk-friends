package com.olerom.vk.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by olerom on 01.02.17.
 */
public class RequestVK {
    final static int APP_ID = 5850646;
    final static String CLIENT_SECRET = "yFDcIlB3etwopMbPTpNV";
    final static String REDIRECT_URI = "http://localhost:8080/";
    private final static String DISPLAY = "page";
    private final static String PERMISSIONS = "friends,offline";

    private String AUTH_URL = "https://oauth.vk.com/authorize" + "?client_id={APP_ID}"
            + "&scope={PERMISSIONS}" + "&redirect_uri={REDIRECT_URI}";

    public RequestVK() {
        AUTH_URL = AUTH_URL.replace("{APP_ID}", "" + APP_ID)
                .replace("{PERMISSIONS}", PERMISSIONS)
                .replace("{REDIRECT_URI}", REDIRECT_URI).replace("{DISPLAY}", DISPLAY);
    }

    public void makeRequest() throws IOException {
        URL url = new URL(AUTH_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
    }

    public String getAuthURL() {
        return AUTH_URL;
    }
}
