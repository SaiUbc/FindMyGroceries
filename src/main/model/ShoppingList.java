package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private final String name;
    private final List<Store> stores;
    private final List<String> shoppingList;

    //EFFECTS: constructs a shopping list with a list of stores and shopping list
    public ShoppingList(String name) {
        this.name = name;
        this.stores = new ArrayList<>();
        this.shoppingList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a store to the list of stores in the shopping list
    public void addStore(Store store) {
        stores.add(store);
    }

    //MODIFIES: this
    //EFFECTS: removes a store from the list of stores in the shopping list
    public void removeStore(Store store) {
        stores.remove(store);
    }

    //MODIFIES: this
    //EFFECTS: adds item to the shopping list
    public void addItemToList(String item) {
        shoppingList.add(item);
    }

    //MODIFIES: this
    //EFFECTS: removes item from the shopping list
    public void removeItemFromList(String item) {
        shoppingList.remove(item);
    }

    // getters
    public String getShoppingListName() {
        return this.name;
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<String> getShoppingList() {
        return shoppingList;
    }
}
