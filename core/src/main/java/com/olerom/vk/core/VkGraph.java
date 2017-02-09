package com.olerom.vk.core;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olerom
 *         Date: 03.02.17
 */
public class VkGraph<T extends UserXtrLists> {
    private List<Node<T>> nodes;

    public VkGraph() {
        nodes = new ArrayList<>();
    }

    public VkGraph(int numberOfVertexes) {
        nodes = new ArrayList<>(numberOfVertexes);
    }

    public VkGraph(List<T> nodes) {
        this(nodes.size());
        for (T friend : nodes) {
            this.addVertex(friend);
        }
    }

    public void addVertex(T vertex) {
        nodes.add(new Node<>(vertex));
    }

    public void build(VkAdapter vkAdapter) throws ClientException, ApiException, InterruptedException {
        for (UserXtrLists friend : vkAdapter.getFriends()) {
            if (!vkAdapter.isDeactivated(friend)) {
                List<Integer> ids = vkAdapter.getMutals(friend.getId());
                for (int id : ids) {
                    addEdge((T) friend, (T) vkAdapter.getActualFriend(id));
                }
                Thread.sleep(500);
            }
        }

    }

    public void addEdge(T from, T to) {
        if (findNode(from, to) != null) {
            //noinspection ConstantConditions
            findNode(from, to).addNode(to);
        }
    }

    private Node<T> findNode(T find, T duplicated) {
        for (Node<T> node : nodes) {
            if (node.hasInside(find, duplicated)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : nodes) {
            stringBuilder
                    .append(node.toString())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
