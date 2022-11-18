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

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
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

    // EFFECTS: parses workroom from JSON object and returns it
    private ShoppingList parseShoppingList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ShoppingList shoppingList = new ShoppingList(name);
        addStores(shoppingList, jsonObject);
        addList(shoppingList, jsonObject);
        return shoppingList;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addList(ShoppingList shoppingList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shopping list");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addStore(shoppingList, nextItem);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void nextItem(ShoppingList shoppingList, JSONObject jsonObject) {
        String item = jsonObject.getString("item");
        shoppingList.addItemToList(item);
    }



    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addStores(ShoppingList shoppingList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stores");
        for (Object json : jsonArray) {
            JSONObject nextStore = (JSONObject) json;
            addStore(shoppingList, nextStore);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addStore(ShoppingList shoppingList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int distance = jsonObject.getInt("distance");
        int rating = jsonObject.getInt("rating");

        Store store = new Store(name,distance,rating);
        shoppingList.addStore(store);
    }
}
