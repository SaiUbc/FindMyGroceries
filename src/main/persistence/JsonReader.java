package persistence;

import model.Item;
import model.ShoppingList;
import model.Store;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads shopping list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shopping list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses shopping list from JSON object and returns it
    private ShoppingList parseShoppingList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ShoppingList shoppingList = new ShoppingList(name);
        addStores(shoppingList, jsonObject);
        addList(shoppingList, jsonObject);
        return shoppingList;
    }

    // MODIFIES: wr
    // EFFECTS: parses list from JSON object and adds them to shopping list
    private void addList(ShoppingList shoppingList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shopping list");
        for (Object json : jsonArray) {
            String item = ((JSONObject) json).getString("item");
            shoppingList.addItemToList(item);
        }
    }



    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to shopping list
    private void addStores(ShoppingList shoppingList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stores");
        for (Object json : jsonArray) {
            JSONObject nextStore = (JSONObject) json;
            addStore(shoppingList, nextStore);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses stores from JSON object and adds it to shopping list
    private void addStore(ShoppingList shoppingList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Store store = new Store(name);
        shoppingList.addStore(store);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json: jsonArray) {
            String itemName = ((JSONObject) json).getString("name");
            Float itemPrice = ((JSONObject) json).getFloat("price");
            Item item = new Item(itemName, itemPrice);
            store.storeItem(item);
        }
    }
}
