package org.anstreth.todo;

import org.anstreth.todo.ToDoItem;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by roman on 14.06.2016.
 */
public class ToDoItemTest {

    ToDoItem item1 = new ToDoItem("Test TODO");
    ToDoItem item2 = new ToDoItem("Another TODO");

    @Test
    public void constructorTest() {
        assertEquals("First constructor check", "Test TODO", item1.getTask());
        assertEquals("Second constructor check", "Another TODO", item2.getTask());
    }

    @Test
    public void isNotCompletedAfterCreationTest() {
        assertFalse("First must not be completed", item1.isCompleted());
        assertFalse("Second must not be completed", item2.isCompleted());
    }

    @Test
    public void isCompletedAfterDone() {
        item1.done();
        item2.done();
        assertTrue("First must be completed", item1.isCompleted());
        assertTrue("Second must be completed", item2.isCompleted());
    }

    @Test
    public void isNotCompletedAfterUndone() {
        item1.done();
        item1.undone();
        assertFalse("First must not be completed", item1.isCompleted());

        item2.done();
        item2.undone();
        assertFalse("Second must not be completed", item2.isCompleted());
    }
}