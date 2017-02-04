package com.olerom.vk.core;

import com.vk.api.sdk.objects.friends.UserXtrLists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olerom
 * Date 04.02.17
 */
class Node<T extends UserXtrLists> {
    private T value;
    private List<T> lines;

    Node(T value) {
        lines = new ArrayList<>();
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(value.getFirstName())
                .append(" ")
                .append(value.getLastName())
                .append(" = [");
        for (T user : lines) {
            stringBuilder
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void addNode(T line) {
        lines.add(line);
    }

    public boolean hasInside(T check, T duplicated) {
        if (includes(check)) {
            if (!lines.contains(duplicated)) {
                return true;
            }
        }
        return false;
    }

    private boolean includes(T value) {
        return this.value.equals(value);
    }
}
