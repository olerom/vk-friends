package com.olerom.vk.core;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.UserXtrLists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Date: 04.02.17
 *
 * @author olerom
 */
public class VkAdapterTest {
    //TODO: do it correct, not this shit
    private VkAdapter vkAdapter = mock(VkAdapter.class);
    private List<UserXtrLists> basicFriends = new ArrayList<>();

    private UserXtrLists friend01 = mock(UserXtrLists.class);
    private UserXtrLists friend02 = mock(UserXtrLists.class);
    private UserXtrLists friend03 = mock(UserXtrLists.class);
    private List<UserXtrLists> friendsFor01 = new ArrayList<>();
    private List<UserXtrLists> friendsFor02 = new ArrayList<>();
    private List<UserXtrLists> friendsFor03 = new ArrayList<>();


    @Before
    public void configure() throws ClientException, ApiException {
        vkAdapter = mock(VkAdapter.class);
        friend01 = mock(UserXtrLists.class);
        friend02 = mock(UserXtrLists.class);
        friend03 = mock(UserXtrLists.class);

        when(friend01.getId()).thenReturn(1);
        when(friend02.getId()).thenReturn(2);
        when(friend03.getId()).thenReturn(3);

        when(vkAdapter.getFriends()).thenReturn(basicFriends);
        when(vkAdapter.getFriendsByFriendId(1)).thenReturn(friendsFor01);
        when(vkAdapter.getFriendsByFriendId(2)).thenReturn(friendsFor02);
        when(vkAdapter.getFriendsByFriendId(3)).thenReturn(friendsFor03);

        basicFriends.add(friend01);
        basicFriends.add(friend02);
        basicFriends.add(friend03);

        friendsFor01.add(friend02);
        friendsFor01.add(friend03);

        friendsFor02.add(friend01);
        friendsFor02.add(friend03);

        friendsFor03.add(friend01);
    }

    @Test
    public void isFriendTest() throws ClientException, ApiException {
        when(vkAdapter.isFriend(friend01, 1)).thenCallRealMethod();
        when(vkAdapter.isFriend(friend01, 2)).thenCallRealMethod();
        when(vkAdapter.isFriend(friend01, 3)).thenCallRealMethod();

        when(vkAdapter.isFriend(friend02, 1)).thenCallRealMethod();
        when(vkAdapter.isFriend(friend02, 2)).thenCallRealMethod();
        when(vkAdapter.isFriend(friend02, 3)).thenCallRealMethod();

        when(vkAdapter.isFriend(friend03, 1)).thenCallRealMethod();
        when(vkAdapter.isFriend(friend03, 2)).thenCallRealMethod();
        when(vkAdapter.isFriend(friend03, 3)).thenCallRealMethod();

        assertFalse(vkAdapter.isFriend(friend01, 1));
        assertTrue(vkAdapter.isFriend(friend01, 2));
        assertTrue(vkAdapter.isFriend(friend01, 3));

        assertTrue(vkAdapter.isFriend(friend02, 1));
        assertFalse(vkAdapter.isFriend(friend02, 2));
        assertTrue(vkAdapter.isFriend(friend02, 3));

        assertTrue(vkAdapter.isFriend(friend03, 1));
        assertFalse(vkAdapter.isFriend(friend03, 2));
        assertFalse(vkAdapter.isFriend(friend03, 3));
    }

}
