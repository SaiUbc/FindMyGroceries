package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList implements Writable {
    private String name;
    private List<Store> stores;
    private List<String> shoppingList;
    ArrayList<String> storenames;

    //EFFECTS: constructs a shopping list with a list of stores and shopping list
    public ShoppingList(String name) {
        this.name = name;
        this.stores = new ArrayList<>();
        this.shoppingList = new ArrayList<>();
        this.storenames = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a store to the list of stores in the shopping list
    public void addStore(Store store) {
        if (!stores.contains(store)) {
            stores.add(store);
            EventLog.getInstance().logEvent(new Event("Store: " + store.getStoreName() + " has been added"));
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a store from the list of stores in the shopping list
    public void removeStore(Store store) {
        stores.remove(store);
        EventLog.getInstance().logEvent(new Event("Store: " + store.getStoreName() + " has been removed"));
    }

    //MODIFIES: this
    //EFFECTS: adds item to the shopping list
    public void addItemToList(String item) {
        if (!shoppingList.contains(item)) {
            shoppingList.add(item);
            EventLog.getInstance().logEvent(new Event(item + " has been added to shopping list"));
        }
    }

    //MODIFIES: this
    //EFFECTS: removes item from the shopping list
    public void removeItemFromList(String item) {
        shoppingList.remove(item);
        EventLog.getInstance().logEvent(new Event(item + " has been removed from shopping list"));
    }

    //EFFECTS: converts shopping list data into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("stores", storesToJson());
        json.put("shopping list", shoppingListToJson());
        return json;
    }

    //EFFECTS: converts stores from shopping list and stores in JSONArray format
    private JSONArray storesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Store store: stores) {
            jsonArray.put(store.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: converts shopping list data and stores in JSONArray format
    private JSONArray shoppingListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String item: shoppingList) {
            JSONObject json = new JSONObject();
            json.put("item", item);
            jsonArray.put(json);
        }
        return jsonArray;
    }


    //MODIFIES: this
    //EFFECTS: returns the name of the store with a given storeName
    public Store findStore(String storeName) {
        for (Store store : stores) {
            storenames.add(store.getStoreName());
            if (storenames.contains(storeName)) {
                return store;
            }
        }
        return null;
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

    //setters
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public void setShoppingList(List<String> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
