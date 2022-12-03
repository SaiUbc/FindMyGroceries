package persistence;

import model.Store;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkStore(String name, Store store) {
        assertEquals(name, store.getStoreName());
        assertEquals("Milk", store.getStoreItems().get(0).getItemName());
        assertEquals(4, store.getStoreItems().get(0).getItemPrice());
    }
}
