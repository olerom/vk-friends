package com.olerom.vk.core;

import com.vk.api.sdk.actions.Friends;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.queries.friends.FriendsGetQuery;

/**
 * Created by olerom on 01.02.17.
 */
public class User {
    private VkApiClient vkApiClient;
    private UserAuthResponse authResponse;
    private UserActor actor;

    public VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    public UserAuthResponse getAuthResponse() {
        return authResponse;
    }

    public UserActor getUserActor() {
        return actor;
    }

    public User(String code) throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vkApiClient = new VkApiClient(transportClient);
        authResponse = vkApiClient.oauth()
                .userAuthorizationCodeFlow(RequestVK.APP_ID, RequestVK.CLIENT_SECRET, RequestVK.REDIRECT_URI, code)
                .execute();
        actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
    }

    public void getFriends() {
        Friends friends = vkApiClient.friends();
        FriendsGetQuery friendsGetQuery = friends.get(actor);

        System.out.println(actor);
    }

    public void getJsonData() {
//        JsonParser parser = new JsonParser();
//        JsonObject mainObject = parser.parse().getAsJsonObject();
//        JsonArray pItem = mainObject.getAsJsonArray("p_item");
//        for (JsonElement user : pItem) {
//            JsonObject userObject = user.getAsJsonObject();
//            if (userObject.get("p_id").getAsInt() == 132) {
//                System.out.println(userObject.get("p_name"));
//                return;
//            }
//        }
    }
}
