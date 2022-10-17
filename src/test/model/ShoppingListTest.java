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

    Item apple1;
    Item apple2;
    Item orange1;
    Item orange2;

    @BeforeEach
    void setup() {
        safeway = new Store("SafeWay", 20, 5);
        saveon = new Store("Save-Ons", 10, 2);
        apple = "Apple";
        orange = "Orange";
        apple1 = new Item("Apple", 34);
        apple2 = new Item("Apple", 44);
        orange1 = new Item("Orange", 20);
        orange2 = new Item("Orange", 24);
        myList = new ShoppingList("Shopping List Test 1");
    }

    @Test
    void constructorTest() {
        //TODO
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
        //TODO
    }

    @Test
    void addSingleItemToListTest() {
        //TODO
    }

    @Test
    void addMultipleItemsToListTest() {
        //TODO
    }

    @Test
    void addDuplicatedItemsToListTest() {
        //TODO
    }

    @Test
    void removeItemFromListTest() {
        //TODO
    }

    @Test
    void removeMultipleItemsFromListTest() {
        //TODO
    }

    @Test
    void removeItemFromEmptyListTest() {
        //TODO
    }
}
