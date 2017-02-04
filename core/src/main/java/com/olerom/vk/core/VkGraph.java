package com.olerom.vk.core;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olerom on 03.02.17.
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
        for (UserXtrLists friend : vkAdapter.getYourFriends()) {
            List<Integer> ids = vkAdapter.getMutals(friend.getId());
            for (int id : ids) {
                addEdge((T) friend, (T) vkAdapter.getActualFriend(id));
            }
            Thread.sleep(500);
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
            stringBuilder.append(node.toString()).
                    append("\n");
        }
        return stringBuilder.toString();
    }

//    private class Node {
//        private UserXtrLists value;
//        private List<UserXtrLists> lines;
//
//        Node(UserXtrLists userXtrLists) {
//            lines = new ArrayList<>();
//            value = userXtrLists;
//        }
//
//        @Override
//        public String toString() {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(value.getFirstName() + " " + value.getLastName() + " = [");
//            for (UserXtrLists user : lines) {
//                stringBuilder.append(user.getFirstName() + " " + user.getLastName() + ", ");
//            }
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//            stringBuilder.append("]");
//            return stringBuilder.toString();
//        }
//
//        public void addNode(UserXtrLists userXtrLists) {
//            lines.add(userXtrLists);
//        }
//
//        public boolean hasInside(UserXtrLists userXtrLists, UserXtrLists duplicated) {
//            if (includes(userXtrLists)) {
//                for (UserXtrLists line : lines) {
//                    if (line.getId().equals(duplicated.getId())) {
//                        return false;
//                    }
//                }
//                return true;
//            }
//            return false;
//        }
//
//        private boolean includes(UserXtrLists userXtrLists) {
//            return value.equals(userXtrLists);
//        }
//    }
}
