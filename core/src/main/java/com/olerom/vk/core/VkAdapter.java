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
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.friends.FriendsGetQueryWithFields;
import com.vk.api.sdk.queries.users.UserField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olerom
 * Date: 01.02.17
 */
public class VkAdapter {
    private VkApiClient vkApiClient;
    private UserAuthResponse authResponse;
    private UserActor actor;

    private List<UserXtrLists> yourFriends;

    public VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    public UserAuthResponse getAuthResponse() {
        return authResponse;
    }

    public UserActor getUserActor() {
        return actor;
    }

    public VkAdapter(String code) throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vkApiClient = new VkApiClient(transportClient);
        authResponse = vkApiClient.oauth()
                .userAuthorizationCodeFlow(VkRequest.APP_ID, VkRequest.CLIENT_SECRET, VkRequest.REDIRECT_URI, code)
                .execute();
        actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        List<UserField> fields = createFields();
        FriendsGetQueryWithFields friendsGetQueryWithFields = vkApiClient.friends().get(actor, fields);
        GetFieldsResponse execute = friendsGetQueryWithFields.execute();
        yourFriends = execute.getItems();
    }

    public List<UserXtrLists> getFriends(){
        return yourFriends;
    }

    public List<UserXtrLists> getFriendsByFriendId(int id) throws ClientException, ApiException {
        List<UserField> fields = new ArrayList<>(5);
        fields.add(UserField.BDATE);
        fields.add(UserField.CITY);
        fields.add(UserField.CONTACTS);
        fields.add(UserField.IS_FRIEND);
        fields.add(UserField.FRIEND_STATUS);
        return vkApiClient.friends().get(fields).userId(id).execute().getItems();
    }

    public boolean isFriend(UserXtrLists checkFor, int actualId) throws ClientException, ApiException {
        List<UserXtrLists> x = getFriendsByFriendId(checkFor.getId());
        for (UserXtrLists user : x) {
            if (user.getId() == actualId)
                return true;
        }
        return false;
    }

    public List<Integer> getMutals(int id) throws ClientException, ApiException {
        return vkApiClient.friends().getMutual(actor).targetUid(id).execute();
    }

    // Critical problem, wrong return Type
    public List<Integer> getMutalNoEx(int id) throws ClientException, ApiException {
        List<Integer> ids = new ArrayList<>();
        for (UserXtrLists friend : yourFriends) {
            ids.add(friend.getId());
        }
        return vkApiClient.friends().getMutual(actor).targetUids(ids).execute();
    }


    public UserXtrLists getActualFriend(int id) throws ClientException, ApiException {
        List<UserXtrCounters> res = new ArrayList<>();
        for (UserXtrLists friend : yourFriends) {
            if (friend.getId() == id)
                return friend;
        }
        return null;
    }

    private List<UserField> createFields() {
        List<UserField> fields = new ArrayList<>(5);
        fields.add(UserField.BDATE);
        fields.add(UserField.CITY);
        fields.add(UserField.CONTACTS);
        fields.add(UserField.IS_FRIEND);
        fields.add(UserField.FRIEND_STATUS);
        return fields;
    }
}
