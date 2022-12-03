package persistence;

import model.ShoppingList;
import model.Store;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingList shoppingList = new ShoppingList("user");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShoppingList() {
        try {
            ShoppingList shoppingList = new ShoppingList("user");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingList.json");
            writer.open();
            writer.write(shoppingList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingList.json");
            shoppingList = reader.read();
            assertEquals("user", shoppingList.getShoppingListName());
            assertEquals(0, shoppingList.getShoppingList().size());
            assertEquals(0, shoppingList.getStores().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingList() {
        try {
            ShoppingList shoppingList = new ShoppingList("user");
            shoppingList.addItemToList("Milk");
            shoppingList.addItemToList("Rice");
            Item milk = new Item("Milk", 4);
            Store saveons = new Store("Saveons");
            Store noFrills = new Store("No Frills");
            saveons.storeItem(milk);
            noFrills.storeItem(milk);
            shoppingList.addStore(saveons);
            shoppingList.addStore(noFrills);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingList.json");
            writer.open();
            writer.write(shoppingList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingList.json");
            shoppingList = reader.read();
            assertEquals("user", shoppingList.getShoppingListName());
            List<String> order = shoppingList.getShoppingList();
            assertEquals(2, order.size());
            checkStore("Saveons", saveons);
            checkStore("No Frills", noFrills);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}