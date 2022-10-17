package model;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private int distance;
    private int rating;
    private List<Item> items;


    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public Store(String name, int distance, int rating) {
        this.name = name;
        this.distance = distance;
        this.rating = rating;
        this.items = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void storeItem(Item item) {
        this.items.add(item);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void removeItem(String itemname) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemName().equals(itemname)) {
                items.remove(items.get(i));
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public boolean containsItemName(String itemname) {
        for (Item item : items) {
            if (item.getItemName().equals(itemname)) {
                return true;
            }
        }
        return false;
    }

    //Setters
    public void setStoreName(String name) {
        this.name = name;
    }

    public void setStoreDistance(int distance) {
        this.distance = distance;
    }

    public void setStoreRating(int rating) {
        this.rating = rating;
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
