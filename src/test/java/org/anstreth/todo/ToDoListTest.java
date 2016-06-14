package org.anstreth.todo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by roman on 14.06.2016.
 */
public class ToDoListTest {
    private ToDoList list = new ToDoList();

    @Test
    public void constructorAndInitialStateTest() {
        assertEquals("New list is empty", 0, list.getNumberOfItems());
    }

    @Test
    public void addingItemsTest() {
        list.addItem("Test TODO");
        assertEquals("Size is one after one adding", 1, list.getNumberOfItems());

        list.addItem("Second TODO Test");
        assertEquals("Size is two after two addings", 2, list.getNumberOfItems());
    }

    @Test
    public void cleaningTodoList() {
        list.addItem("One");
        list.addItem("Two");
        list.addItem("Three");
        assertEquals("List is not empty", 3, list.getNumberOfItems());

        list.clean();
        assertEquals("List is empty after cleaning", 0, list.getNumberOfItems());
    }

    @Test
    public void listSavesItems() {
        assertEquals("List is empty on start", 0, list.getItems().size());

        list.addItem("One");
        list.addItem("Two");
        list.addItem("Three");

        assertEquals("List has three items", 3, list.getItems().size());
        assertTrue("List has items 'One'", list.getItems().contains("One"));
        assertTrue("List has items 'Two'", list.getItems().contains("Two"));
        assertTrue("List has items 'Three'", list.getItems().contains("Three"));
    }

    @Test
    public void duplicateValues() {
        list.addItem("One");
        list.addItem("Two");
        list.addItem("One");
        list.addItem("Two");
        list.addItem("Three");

        assertEquals("List has 3 unique todos", 3, list.getItems().size());
        assertEquals("Number of items is 3", 3, list.getNumberOfItems());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void itemsRemoving() {
        list.addItem("One");
        list.addItem("Two");
        list.addItem("Three");

        list.removeItem("One");
        assertEquals("Number of items after first removal", 2, list.getNumberOfItems());
        list.removeItem("Two");
        assertEquals("Number of items after second removal", 1, list.getNumberOfItems());

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("TODO List does not contain element 'Four'");
        list.removeItem("Four");
    }
}