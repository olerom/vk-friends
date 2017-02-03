package com.olerom.vk.core;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.friends.UserXtrLists;
import com.vk.api.sdk.objects.friends.responses.GetFieldsResponse;
import com.vk.api.sdk.queries.friends.FriendsGetQueryWithFields;
import com.vk.api.sdk.queries.users.UserField;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserXtrLists> getFriends() throws ClientException, ApiException {
        List<UserField> fields = new ArrayList<>(5);
        fields.add(UserField.BDATE);
        fields.add(UserField.CITY);
        fields.add(UserField.CONTACTS);
        fields.add(UserField.IS_FRIEND);
        fields.add(UserField.FRIEND_STATUS);
        FriendsGetQueryWithFields friendsGetQueryWithFields = vkApiClient.friends().get(actor, fields);
        GetFieldsResponse execute = friendsGetQueryWithFields.execute();

        return execute.getItems();
    }

    public List<UserXtrLists> getFriendsByFriendId(int id) throws ClientException, ApiException {
        return vkApiClient.friends().get(UserField.MAIDEN_NAME).userId(id).execute().getItems();
    }

    public boolean isFriend(int id) {
        return true;
    }

    public boolean isFriend(UserXtrLists checkFor, int actualId) throws ClientException, ApiException {
        List<UserXtrLists> x = getFriendsByFriendId(actualId);
        return x.contains(checkFor);
    }

    public void test() {


        try {
            List<UserXtrLists> x = getFriendsByFriendId(1);
            System.out.println(isFriend(x.get(5), 1));
            System.out.println(isFriend(x.get(5), 154646434));
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
