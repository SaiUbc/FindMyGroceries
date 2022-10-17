package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    Item item1;
    Item item2;

    @BeforeEach
    void setup() {
        item1 = new Item("Apple", 40);
        item2 = new Item("Orange", 20);
    }

    @Test
    void constructorTest() {
        assertEquals(40, item1.getItemPrice());
        assertEquals(20, item2.getItemPrice());
        assertEquals("Apple", item1.getItemName());
        assertEquals("Orange", item2.getItemName());
    }

    @Test
    void itemPriceGetterTest() {
        assertEquals(40, item1.getItemPrice());
        assertEquals(20, item2.getItemPrice());
    }

    @Test
    void itemNameGetterTest() {
        assertEquals("Apple", item1.getItemName());
        assertEquals("Orange", item2.getItemName());
    }

    @Test
    void itemPriceSetterTest() {
        item1.setItemPrice(50);
        item2.setItemPrice(30);
        assertEquals(item1.getItemPrice(), 50);
        assertEquals(item2.getItemPrice(), 30);
    }

    @Test
    void itemNameSetterTest() {
        item1.setItemName("PineApple");
        item2.setItemName("Grape");
        assertEquals("PineApple", item1.getItemName());
        assertEquals("Grape", item2.getItemName());
    }
}
