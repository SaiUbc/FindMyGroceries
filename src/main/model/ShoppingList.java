package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList implements Writable {
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
        if (!stores.contains(store)) {
            stores.add(store);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a store from the list of stores in the shopping list
    public void removeStore(Store store) {
        stores.remove(store);
    }

    //MODIFIES: this
    //EFFECTS: adds item to the shopping list
    public void addItemToList(String item) {
        if (!shoppingList.contains(item)) {
            shoppingList.add(item);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes item from the shopping list
    public void removeItemFromList(String item) {
        shoppingList.remove(item);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("stores", storesToJSON());
        json.put("shopping list", shoppingListToJSON());
        return json;
    }

    private JSONArray storesToJSON() {
        JSONArray jsonArray = new JSONArray();

        for (Store store: stores) {
            jsonArray.put(store.toJson());
        }
        return jsonArray;
    }

    private JSONArray shoppingListToJSON() {
        JSONArray jsonArray = new JSONArray();

        for (String item: shoppingList) {
            JSONObject json = new JSONObject();
            json.put("item", item);
            jsonArray.put(json);
        }
        return jsonArray;
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
