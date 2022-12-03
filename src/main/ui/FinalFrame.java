package ui;

import model.Item;
import model.ShoppingList;
import model.Store;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;

public class FinalFrame {
    ShoppingList shoppingList;

    public FinalFrame(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    private void processData() {
        List<Store> stores = shoppingList.getStores();
        List<String> items = shoppingList.getShoppingList();

        for (String item : items) {
            Store store = findCheapestStore(item, stores);
        }
    }

    private Store findCheapestStore(String item, List<Store> stores) {
        //returns cheapest store for an item.
        return null;
    }
}
