package com.olerom.vk.core;

/**
 * Created by olerom on 01.02.17.
 */
public class VkRequest {
    final static int APP_ID = 5850646;
    final static String CLIENT_SECRET = "yFDcIlB3etwopMbPTpNV";
    final static String REDIRECT_URI = "https://oauth.vk.com/blank.html/";
    private final static String DISPLAY = "page";
    private final static String PERMISSIONS = "friends,groups";
    private final static String RESPONSE_TYPE = "code";

    private String AUTH_URL = "https://oauth.vk.com/authorize" + "?client_id={APP_ID}"
            + "&scope={PERMISSIONS}" + "&redirect_uri={REDIRECT_URI}" + "&response_type={RESPONSE_TYPE}";

    public VkRequest() {
        AUTH_URL = AUTH_URL
                .replace("{APP_ID}", "" + APP_ID)
                .replace("{PERMISSIONS}", PERMISSIONS)
                .replace("{REDIRECT_URI}", REDIRECT_URI)
                .replace("{DISPLAY}", DISPLAY)
                .replace("{RESPONSE_TYPE}", RESPONSE_TYPE);
    }

    public String getAuthURL() {
        return AUTH_URL;
    }
}
