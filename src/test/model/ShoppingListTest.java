package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {
    ShoppingList myList;

    Store safeway;
    Store saveon;

    String apple;
    String orange;

    @BeforeEach
    void setup() {
        safeway = new Store("SafeWay");
        saveon = new Store("Save-Ons");
        apple = "Apple";
        orange = "Orange";
        myList = new ShoppingList("Shopping List Test 1");
    }

    @Test
    void constructorTest() {
        assertEquals("Shopping List Test 1", myList.getShoppingListName());
        assertEquals(0, myList.getShoppingList().size());
        assertEquals(0, myList.getStores().size());
    }

    @Test
    void addSingleStoreTest() {
        assertEquals(0, myList.getStores().size());
        myList.addStore(safeway);
        assertTrue(myList.getStores().contains(safeway));
    }

    @Test
    void addingExistingStoreTest() {
        assertEquals(0, myList.getStores().size());
        myList.addStore(safeway);
        assertTrue(myList.getStores().contains(safeway));
        assertEquals(1, myList.getStores().size());
        myList.addStore(safeway);
        assertEquals(1, myList.getStores().size());
    }
    
    @Test
    void addMultipleStoreTest() {
        assertEquals(0, myList.getStores().size());
        myList.addStore(safeway);
        assertTrue(myList.getStores().contains(safeway));
        assertEquals(1, myList.getStores().size());
        myList.addStore(saveon);
        assertTrue(myList.getStores().contains(saveon));
        assertEquals(2, myList.getStores().size());
    }

    @Test
    void removeSingleStoreTest() {
        assertEquals(0, myList.getStores().size());
        myList.addStore(safeway);
        assertTrue(myList.getStores().contains(safeway));
        assertEquals(1, myList.getStores().size());
        myList.removeStore(safeway);
        assertFalse(myList.getStores().contains(safeway));
        assertEquals(0, myList.getStores().size());
    }

    @Test
    void removeMultipleStoreTest() {
        assertEquals(0, myList.getStores().size());
        myList.addStore(safeway);
        myList.addStore(saveon);
        assertEquals(2, myList.getStores().size());
        assertTrue(myList.getStores().contains(safeway));
        assertTrue(myList.getStores().contains(saveon));
        myList.removeStore(safeway);
        assertFalse(myList.getStores().contains(safeway));
        assertEquals(1, myList.getStores().size());
        myList.removeStore(saveon);
        assertFalse(myList.getStores().contains(saveon));
        assertEquals(0, myList.getStores().size());
    }

    @Test
    void addSingleItemToListTest() {
        assertEquals(0, myList.getShoppingList().size());
        myList.addItemToList(apple);
        assertEquals(1, myList.getShoppingList().size());
        assertTrue(myList.getShoppingList().contains(apple));
    }

    @Test
    void addMultipleItemsToListTest() {
        assertEquals(0, myList.getShoppingList().size());
        myList.addItemToList(apple);
        myList.addItemToList(orange);
        assertEquals(2, myList.getShoppingList().size());
        assertTrue(myList.getShoppingList().contains(apple));
        assertTrue(myList.getShoppingList().contains(orange));
    }

    @Test
    void addDuplicatedItemsToListTest() {
        assertEquals(0, myList.getShoppingList().size());
        myList.addItemToList(apple);
        assertEquals(1, myList.getShoppingList().size());
        assertTrue(myList.getShoppingList().contains(apple));
        myList.addItemToList(apple);
        assertEquals(1, myList.getShoppingList().size());
        assertTrue(myList.getShoppingList().contains(apple));
    }

    @Test
    void removeItemFromListTest() {
        myList.addItemToList(apple);
        assertEquals(1, myList.getShoppingList().size());
        assertTrue(myList.getShoppingList().contains(apple));
        myList.removeItemFromList(apple);
        assertEquals(0, myList.getShoppingList().size());
        assertFalse(myList.getShoppingList().contains(apple));
    }

    @Test
    void removeMultipleItemsFromListTest() {
        myList.addItemToList(apple);
        myList.addItemToList(orange);
        assertEquals(2, myList.getShoppingList().size());
        assertTrue(myList.getShoppingList().contains(apple));
        assertTrue(myList.getShoppingList().contains(orange));
        myList.removeItemFromList(apple);
        assertEquals(1, myList.getShoppingList().size());
        assertFalse(myList.getShoppingList().contains(apple));
        assertTrue(myList.getShoppingList().contains(orange));
        myList.removeItemFromList(orange);
        assertEquals(0, myList.getShoppingList().size());
        assertFalse(myList.getShoppingList().contains(orange));
    }
}
