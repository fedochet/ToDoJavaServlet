package org.anstreth.todo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by roman on 14.06.2016.
 */
public class ToDoList {

    private Map<String, Boolean> items = new LinkedHashMap<>();

    public int getNumberOfItems() {
        return items.size();
    }

    public void addItem(String todoTask) {
        items.put(todoTask, false);
    }

    public void clean() {
        items.clear();
    }

    public List<String> getItems() {
        return new ArrayList<>(items.keySet());
    }

    public void removeItem(String itemName) {
        if (items.remove(itemName) == null) {
            throw new IllegalArgumentException(String.format("TODO List does not contain element '%s'", itemName));
        }
    }

    public boolean isCompleted(String taskName) {
        if (!items.containsKey(taskName))
            throw new IllegalArgumentException(String.format("TODO List does not contain element '%s'", taskName));
        return items.get(taskName);
    }

    public void complete(String taskName) {
        if (!isCompleted(taskName)) {
            items.put(taskName, true);
        } else throw new IllegalArgumentException(String.format("Task '%s' is already completed", taskName));
    }

    public void uncomplete(String taskName) {
        if (isCompleted(taskName)) {
            items.put(taskName, false);
        } else throw new IllegalArgumentException(String.format("Task '%s' is not completed", taskName));
    }
}
