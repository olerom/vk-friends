package com.olerom.vk.core;

import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olerom on 03.02.17.
 */
public class VkGraph {
    private List<Node> nodes;

    public VkGraph() {
        nodes = new ArrayList<>();
    }

    public VkGraph(int numberOfVertexes) {
        nodes = new ArrayList<>(numberOfVertexes);
    }

    public void addVertex(UserXtrLists vertex) {
        nodes.add(new Node(vertex));
    }


    public void addEdge(UserXtrLists from, UserXtrLists to) {
        if (findNode(from, to) != null) {
            findNode(from, to).addNode(to);
        }
        if (findNode(to, from) != null) {
            findNode(to, from).addNode(from);
        }
    }

    private Node findNode(UserXtrLists find, UserXtrLists duplicated) {
        for (Node node : nodes) {
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
            stringBuilder.append(node.toString() + "\n");
        }
        return stringBuilder.toString();
    }

    private class Node {
        private UserXtrLists value;
        private List<UserXtrLists> lines;

        Node(UserXtrLists userXtrLists) {
            lines = new ArrayList<>();
            value = userXtrLists;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(value.getFirstName() + " " + value.getLastName() + " = [");
            for (UserXtrLists user : lines) {
                stringBuilder.append(user.getFirstName() + " " + user.getLastName() + ", ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        public void addNode(UserXtrLists userXtrLists) {
            lines.add(userXtrLists);
        }

        public boolean hasInside(UserXtrLists userXtrLists, UserXtrLists duplicated) {
            if (includes(userXtrLists)) {
                for (UserXtrLists line : lines) {
                    if (line.getId().equals(duplicated.getId())) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        private boolean includes(UserXtrLists userXtrLists) {
            return value.equals(userXtrLists);
        }
    }
}
