package org.anstreth.todo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by roman on 14.06.2016.
 */
public class ToDoList {

    private Set<String> items = new LinkedHashSet<>();

    public int getNumberOfItems() {
        return items.size();
    }

    public void addItem(String todoTask) {
        items.add(todoTask);
    }

    public void clean() {
        items.clear();
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public void removeItem(String itemName) {
        if (!items.remove(itemName)) {
            throw new IllegalArgumentException("TODO List does not contain element '" + itemName+"'");
        }
    }
}
