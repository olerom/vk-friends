package com.olerom.vk.core;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.junit.Test;

/**
 * Created by olerom on 04.02.17.
 */
public class VkAdapterTest {
    @Test
    public void friendTest() throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vkApiClient = new VkApiClient(transportClient);



    }
}
