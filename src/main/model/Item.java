package model;

public class Item {
    private int price;
    private String name;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getItemPrice() {
        return price;
    }

    public String getItemName() {
        return name;
    }

    public void setItemPrice(int price) {
        this.price = price;
    }

    public void setItemName(String name) {
        this.name = name;
    }
}
