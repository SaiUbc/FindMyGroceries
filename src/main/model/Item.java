package model;

import org.json.JSONObject;
import persistence.Writable;

public class Item implements Writable {
    private int price;
    private String name;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // getters
    public int getItemPrice() {
        return price;
    }

    public String getItemName() {
        return name;
    }

    // setters
    public void setItemPrice(int price) {
        this.price = price;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        return json;
    }
}
