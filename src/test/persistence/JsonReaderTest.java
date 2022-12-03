package persistence;

import model.ShoppingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingList shoppingList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingList() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingList.json");
        try {
            ShoppingList shoppingList = reader.read();
            assertEquals("user", shoppingList.getShoppingListName());
            assertEquals(0, shoppingList.getShoppingList().size());
            assertEquals(0, shoppingList.getStores().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingList() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingList.json");
        try {
            ShoppingList shoppingList = reader.read();
            assertEquals("user", shoppingList.getShoppingListName());
            List<String> shoppingOrder = shoppingList.getShoppingList();
            assertEquals(2, shoppingOrder.size());
            assertEquals("Milk", shoppingOrder.get(0));
            assertEquals("Rice", shoppingOrder.get(1));
            checkStore("Saveons", shoppingList.getStores().get(0));
            checkStore("No Frills", shoppingList.getStores().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}