package org.anstreth.todo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
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

    @Test
    public void itemsCompletingTest() {
        list.addItem("One");
        list.addItem("Two");

        assertFalse("New item1 is not completed", list.isCompleted("One"));
        assertFalse("New item2 is not completed", list.isCompleted("Two"));

        list.complete("One");
        assertTrue("Item1 is completed after completion", list.isCompleted("One"));
        assertFalse("Item2 is still not completed", list.isCompleted("Two"));

        list.complete("Two");
        list.uncomplete("One");
        assertThat("Item1 is not completed after uncompletion", list.isCompleted("One"), is(false));
        assertThat("Item2 is completed after completion", list.isCompleted("Two"), is(true));

        list.uncomplete("Two");
        assertThat("Item1 is still not completed", list.isCompleted("One"), is(false));
        assertThat("Item2 is not completed after uncompletion", list.isCompleted("Two"), is(false));
    }

    @Test
    public void testCompletedAndUncompletedItemsWithCompletions() {
        list.addItem("One");
        list.addItem("Two");
        list.addItem("Three");
        List<String> expectedUncompleted1 = Arrays.asList("One","Two", "Three");
        List<String> expectedUncompleted2 = Arrays.asList("Two","Three");
        List<String> expectedUncompleted3 = Collections.singletonList("Two");
        List<String> expectedUncompleted4 = Collections.emptyList();


        List<String> expectedCompleted1 = Collections.emptyList();
        List<String> expectedCompleted2 = Arrays.asList("One");
        List<String> expectedCompleted3 = Arrays.asList("One","Three");
        List<String> expectedCompleted4 = Arrays.asList("One","Two", "Three");

        assertThat("Uncompleted items are 'One','Two','Three'", list.getUncompletedItems(), is(expectedUncompleted1));
        assertThat("Completed items are empty", list.getCompletedItems(), is(expectedCompleted1));

        list.complete("One");
        assertThat("Uncompleted items are 'Two','Three'", list.getUncompletedItems(), is(expectedUncompleted2));
        assertThat("Completed items are 'One'", list.getCompletedItems(), is(expectedCompleted2));

        list.complete("Three");
        assertThat("Uncompleted items are 'Two'", list.getUncompletedItems(), is(expectedUncompleted3));
        assertThat("Completed items are 'One', 'Three'", list.getCompletedItems(), is(expectedCompleted3));

        list.complete("Two");
        assertThat("Uncompleted items are empty", list.getUncompletedItems(), is(expectedUncompleted4));
        assertThat("Completed items are 'One','Two','Three'", list.getCompletedItems(), is(expectedCompleted4));
    }

    @Test
    public void testCompletedAndUncompletedItemsWithUncompletions() {
        list.addItem("One");
        list.addItem("Two");
        list.addItem("Three");

        List<String> expectedUncompleted1 = Arrays.asList("Two");
        List<String> expectedUncompleted2 = Arrays.asList("One","Two");
        List<String> expectedUncompleted3 = Arrays.asList("Two","Three");

        list.complete("One");
        list.complete("Three");
        list.complete("Two");

        list.uncomplete("Two");
        assertThat("Uncompleted items are 'Two'", list.getUncompletedItems(), is(expectedUncompleted1));

        list.uncomplete("One");
        assertThat("Uncompleted items are 'One','Two'", list.getUncompletedItems(), is(expectedUncompleted2));

        list.uncomplete("Three");
        list.complete("One");
        assertThat("Uncompleted items are 'Two', 'Three'", list.getUncompletedItems(), is(expectedUncompleted3));
    }
}