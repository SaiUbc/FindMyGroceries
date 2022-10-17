package model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final String name;
    private final int distance;
    private final int rating;
    private final List<Item> items;

    //REQUIRES: distance > 0, rating >= 0 && rating <= 5
    //EFFECTS: Constructs a store with a given name, distance and rating with zero items in it
    public Store(String name, int distance, int rating) {
        this.name = name;
        this.distance = distance;
        this.rating = rating;
        this.items = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: adds item to the stores items
    public void storeItem(Item item) {
        this.items.add(item);
    }

    //REQUIRES: stores items shouldn't be empty
    //MODIFIES: this
    //EFFECTS: removes a given item from the store if found, otherwise doesn't remove anything.
    public void removeItem(String itemname) {
        while (items.size() != 0) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getItemName().equals(itemname)) {
                    items.remove(items.get(i));
                }
            }
        }
    }

    //REQUIRES: stores items shouldn't be empty
    //EFFECTS: returns true if a given item is found in the store, false otherwise
    public boolean containsItemName(String itemname) {
        while (items.size() != 0) {
            for (Item item : items) {
                if (item.getItemName().equals(itemname)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Getters
    public String getStoreName() {
        return this.name;
    }

    public int getStoreDistance() {
        return this.distance;
    }

    public int getStoreRating() {
        return this.rating;
    }

    public List<Item> getStoreItems() {
        return items;
    }
}
