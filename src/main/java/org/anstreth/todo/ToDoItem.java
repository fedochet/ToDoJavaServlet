package org.anstreth.todo;

/**
 * Created by roman on 14.06.2016.
 */
public class ToDoItem {

    private final String task;
    private boolean isCompleted = false;

    public ToDoItem(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void done() {
        isCompleted = true;
    }

    public void undone() {
        isCompleted = false;
    }
}
