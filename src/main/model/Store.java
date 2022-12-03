package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Store implements Writable {
    private final String name;
    private final List<Item> items;

    //REQUIRES: distance > 0, rating >= 0 && rating <= 5
    //EFFECTS: Constructs a store with a given name, distance and rating with zero items in it
    public Store(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: adds item to the stores items
    public void storeItem(Item item) {
        this.items.add(item);
        EventLog.getInstance().logEvent(new Event("Item: " + item.getItemName() + " has been added to " + name));
    }

    //REQUIRES: stores items shouldn't be empty
    //MODIFIES: this
    //EFFECTS: removes a given item from the store if found, otherwise doesn't remove anything.
    public void removeItem(String itemname) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemName().equals(itemname)) {
                items.remove(items.get(i));
                EventLog.getInstance().logEvent(new Event("Item: " + itemname + " has been removed from " + name));
            }
        }
    }

    //REQUIRES: stores items shouldn't be empty
    //EFFECTS: returns true if a given item is found in the store, false otherwise
    public boolean containsItemName(String itemname) {
        if (items.size() != 0) {
            for (Item item : items) {
                if (item.getItemName().equals(itemname)) {
                    return true;
                }
            }
        }
        return false;
    }

    //EFFECTS: converts Store data and stores it into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("items", itemsToJson());
        return json;
    }

    //EFFECTS: stores items of a store in an JSONArray
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Item item: items) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }

    //Getters
    public String getStoreName() {
        return this.name;
    }

    public List<Item> getStoreItems() {
        return items;
    }
}
