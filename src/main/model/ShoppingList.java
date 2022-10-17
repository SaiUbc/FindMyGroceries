package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private String name;
    private List<Store> stores;
    private List<String> shoppingList;

    //EFFECTS: constructs a shopping list with a list of stores and shopping list
    public ShoppingList(String name) {
        this.name = name;
        this.stores = new ArrayList<>();
        this.shoppingList = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void addStore(Store store) {
        stores.add(store);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void removeStore(Store store) {
        stores.remove(store);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void addItemToList(String item) {
        shoppingList.add(item);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void removeItemFromList(String item) {
        shoppingList.remove(item);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public String getShoppingListName() {
        return this.name;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public List<Store> getStores() {
        return stores;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public List<String> getShoppingList() {
        return shoppingList;
    }
}
