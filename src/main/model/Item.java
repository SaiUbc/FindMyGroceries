package model;

import org.json.JSONObject;
import persistence.Writable;

public class Item implements Writable {
    private float price;
    private String name;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // getters
    public float getItemPrice() {
        return price;
    }

    public String getItemName() {
        return name;
    }

    // setters
    public void setItemPrice(float price) {
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
