package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    Store safeway;

    Item apple;
    Item orange;

    @BeforeEach
    void setup() {
        safeway = new Store("SafeWay", 20, 5);
        apple = new Item("Apple", 30);
        orange = new Item("Orange", 40);
    }

    @Test
    // Test the constructor
    void testStoreConstructor() {
        assertEquals("SafeWay", safeway.getStoreName());
        assertEquals(20, safeway.getStoreDistance());
        assertEquals(5, safeway.getStoreRating());
    }

    @Test
    // store a single item into the list of items of the store
    void testStoringSingleItem() {
        assertFalse(safeway.containsItemName("Apple"));
        safeway.storeItem(apple);
        assertEquals(1, safeway.getStoreItems().size());
        assertTrue(safeway.containsItemName("Apple"));
    }

    @Test
    // store multiple items in the store and check if the items are there in the store
    void testStoringMultipleItems() {
        assertFalse(safeway.containsItemName("Apple"));
        assertFalse(safeway.containsItemName("Orange"));
        safeway.storeItem(apple);
        safeway.storeItem(orange);
        assertEquals(2, safeway.getStoreItems().size());
        assertTrue(safeway.containsItemName("Apple"));
        assertTrue(safeway.containsItemName("Orange"));
    }

    @Test
    // remove a single item from the store
    void removeSingleItem() {
        assertFalse(safeway.containsItemName("Apple"));
        safeway.storeItem(apple);
        assertEquals(1, safeway.getStoreItems().size());
        assertTrue(safeway.containsItemName("Apple"));
        safeway.removeItem("Apple");
        assertFalse(safeway.containsItemName("Apple"));
    }

    @Test
        // remove multiple items from the store
    void removeMultipleItems() {
        assertFalse(safeway.containsItemName("Apple"));
        assertFalse(safeway.containsItemName("Orange"));
        safeway.storeItem(apple);
        safeway.storeItem(orange);

        assertEquals(2, safeway.getStoreItems().size());
        assertTrue(safeway.containsItemName("Orange"));
        assertTrue(safeway.containsItemName("Apple"));
        safeway.removeItem("Apple");
        assertFalse(safeway.containsItemName("Apple"));
        assertTrue(safeway.containsItemName("Orange"));
        safeway.removeItem("Orange");
        assertFalse(safeway.containsItemName("Apple"));
        assertFalse(safeway.containsItemName("Orange"));
    }

    @Test
    // Contains item in an empty store
    void containItemInEmptyStore() {
        assertEquals(safeway.getStoreItems().size(), 0);
        assertFalse(safeway.containsItemName("Apple"));
        assertFalse(safeway.containsItemName("Orange"));
    }

    @Test
    // Contains item in the store
    void containsItemInTheStore() {
        assertEquals(safeway.getStoreItems().size(), 0);
        assertFalse(safeway.containsItemName("Apple"));
        safeway.storeItem(apple);
        assertEquals(safeway.getStoreItems().size(), 1);
        assertTrue(safeway.containsItemName("Apple"));
    }

    @Test
    // Does not contain item in the store
    void doesNotContainItemInStore() {
        assertFalse(safeway.containsItemName("Apple"));
        assertFalse(safeway.containsItemName("Orange"));
    }
}
