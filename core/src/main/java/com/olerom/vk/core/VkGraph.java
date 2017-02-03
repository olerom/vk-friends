package com.olerom.vk.core;

import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olerom on 03.02.17.
 */
public class VkGraph {
    private List<Node> vertexes;
    private int currentPosition;

    public VkGraph() {
        vertexes = new ArrayList<>();
        currentPosition = 0;
    }

    public VkGraph(int numberOfVertexes) {
        vertexes = new ArrayList<>(numberOfVertexes);
        currentPosition = 0;
    }

    public void addVertex(UserXtrLists vertex) {
        vertexes.add(new Node(vertex));
    }

    public void addEdge(UserXtrLists from, UserXtrLists to) {
        findNode(from).addNode(to);
        findNode(to).addNode(from);
    }

    private Node findNode(UserXtrLists find) {
        for (Node node : vertexes) {
            if (node.hasInside(find)){
                return node;
            }
        }
        return null;
    }

    private class Node {
        List<UserXtrLists> nodes;

        Node() {
            nodes = new ArrayList<>();
        }

        Node(UserXtrLists userXtrLists) {
            nodes = new ArrayList<>();
            nodes.add(userXtrLists);
        }

        public void addNode(UserXtrLists userXtrLists){
            nodes.add(userXtrLists);
        }
        public boolean hasInside(UserXtrLists userXtrLists){
            return nodes.get(0).equals(userXtrLists);
        }
    }
}
