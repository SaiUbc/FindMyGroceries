package ui;

import model.Item;
import model.ShoppingList;
import model.Store;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp { //runs the app
    ShoppingList shoppingList;
    private Scanner input;
    ArrayList<String> storenames = new ArrayList<>();

    // EFFECTS: runs the console application
    public ConsoleApp() {
        runConsoleApp();
    }

    // MODIFIES: this
    // EFFECTS: inputs users name and creates new shopping list
    private void runConsoleApp() {
        System.out.print("Enter Name: \n");
        Scanner entry = new Scanner(System.in);
        String name = entry.next();
        shoppingList = new ShoppingList(name);
        runConsole();
    }

    // MODIFIES: this
    // EFFECTS: runs the console and takes user input
    private void runConsole() {
        while (true) {
            displayMainMenu();
            input = new Scanner(System.in);
            int command = input.nextInt();
            if (command == 3) {
                break;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(int command) {
        if (command == 1) {
            runShoppingMenu();
        }
        if (command == 2) {
            runStoreMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: runs the shopping menue and takes user input
    private void runShoppingMenu() {
        displayShoppingMenu();
        input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 3) {
            runConsole();
        } else {
            processShoppingMenuCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for the shopping menu
    private void processShoppingMenuCommand(int command) {
        if (command == 1) {
            displayShoppingList();
        } else if (command == 2) {
            addOrRemoveShoppingItems();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the shopping list of the user
    private void displayShoppingList() {
        System.out.println("*******(Shopping List)*******");
        for (String item : shoppingList.getShoppingList()) {
            System.out.println(item);
        }
        System.out.println(" ");
    }

    // MODIFIES: this
    // EFFECTS: processes user input to either add or remove items from shopping list
    private void addOrRemoveShoppingItems() {
        System.out.println("1 -> Add Item to Shopping List");
        System.out.println("2 -> Remove Item from Shopping List");
        input = new Scanner(System.in);
        int command = input.nextInt();

        if (command == 1) {
            enterShoppingListItem();
        } else if (command == 2) {
            removeShoppingListItem();
        } else {
            System.out.println("Wrong Input!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an item to the shopping list
    private void enterShoppingListItem() {
        System.out.print("Enter Item: ");
        Scanner sc = new Scanner(System.in);
        String item = sc.next();
        shoppingList.addItemToList(item);

        while (true) {
            System.out.print("Enter Item or exit: ");
            sc = new Scanner(System.in);
            item = sc.next();
            if (item.equals("exit")) {
                break;
            } else {
                shoppingList.addItemToList(item);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an item from the shopping list
    private void removeShoppingListItem() {
        while (shoppingList.getShoppingList().size() != 0) {
            System.out.print("Enter Item to Remove or exit: ");
            Scanner sc = new Scanner(System.in);
            String command = sc.next();
            if (command.equals("exit")) {
                runShoppingMenu();
            } else if (shoppingList.getShoppingList().contains(command)) {
                shoppingList.removeItemFromList(command);
                System.out.println("The item: " + command + " was successfully removed from the list\n");
            } else {
                System.out.println("Item not found to be removed\n");
            }
        }
        System.out.println("There are zero items in the shopping list\n");
    }

    // MODIFIES: this
    // EFFECTS: runs the store menu and takes input from user
    private void runStoreMenu() {
        displayStoreMenu();
        input = new Scanner(System.in);
        int command = input.nextInt();

        if (command == 5) {
            runConsole();
        } else if (command >= 1 && command <= 4) {
            processStoreMenuCommand(command);
        } else {
            System.out.println("Wrong input");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input from store menu
    private void processStoreMenuCommand(int command) {
        if (command == 1) {
            storesPreview();
        } else if (command == 2) {
            addStores();
        } else if (command == 3) {
            addItems();
        } else if (command == 4) {
            removeItems();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays a list of stores with the items in them
    private void storesPreview() {
        for (Store store : shoppingList.getStores()) {
            System.out.println("Store: " + store.getStoreName());
            System.out.println(" ");
            for (Item item : store.getStoreItems()) {
                System.out.println("Item name: " + item.getItemName() + " " + item.getItemPrice() + "$");
            }
            System.out.println(" ");
        }
    }

    // REQUIRES: distance > 0, rating >=0 & rating <= 5
    // MODIFIES: this
    // EFFECTS: adds a store to the shopping list
    private void addStores() {
        while (true) {
            System.out.println("Enter Store name or exit: ");
            input = new Scanner(System.in);
            String name = input.next();

            if (name.equals("exit")) {
                break;
            } else {
                System.out.println("Enter Distance: ");
                input = new Scanner(System.in);
                int distance = input.nextInt();

                System.out.println("Enter Rating: ");
                input = new Scanner(System.in);
                int rating = input.nextInt();

                Store store = new Store(name, distance, rating);
                shoppingList.addStore(store);
                System.out.println("Store: " + name + " has been added!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays a list of stores in the shopping list
    private String displayAvailableStores() {
        if (shoppingList.getStores().isEmpty()) {
            System.out.println("No stores are added!");
        } else {
            System.out.println("****** (Available Stores) ******");
            for (Store store : shoppingList.getStores()) {
                storenames.add(store.getStoreName());
                System.out.println(store.getStoreName());
            }
            System.out.println(" ");
            System.out.println("Enter Name of Store: ");
            input = new Scanner(System.in);
            String name = input.next();
            if (!storenames.contains(name)) {
                System.out.println("INVALID!");
                return null;
            }
            return name;
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds an item to a specific store in the shopping list
    private void addItems() {
        String name = displayAvailableStores();
        Store store = getStoreFromName(name);
        while (true) {
            System.out.print("Enter Item Name or exit: ");
            input = new Scanner(System.in);
            String itemname = input.next();
            if (itemname.equals("exit")) {
                break;
            } else {
                System.out.print("Enter Item Price: ");
                input = new Scanner(System.in);
                int itemprice = input.nextInt();
                Item item = new Item(itemname,itemprice);
                store.storeItem(item);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an item from a specific store in the shopping list
    private void removeItems() {
        String name = displayAvailableStores();
        Store store = getStoreFromName(name);
        while (store.getStoreItems().size() != 0) {
            System.out.print("Enter Item Name or exit: ");
            input = new Scanner(System.in);
            String itemname = input.next();
            if (itemname.equals("exit")) {
                break;
            } else if (store.containsItemName(itemname)) {
                store.removeItem(itemname);
                System.out.println(itemname + " has been removed from " + store.getStoreName());
            } else {
                System.out.println("Item not Found!");
            }
            {
                System.out.print("Enter Item Price: ");
                input = new Scanner(System.in);
                int itemprice = input.nextInt();
                Item item = new Item(itemname,itemprice);
                store.storeItem(item);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: returns a store if the given store name is found in the shopping list
    private Store getStoreFromName(String name) {
        for (Store store : shoppingList.getStores()) {
            if (store.getStoreName().equals(name)) {
                return store;
            }
        }
        return null;
    }

    // EFFECTS: displays Store menu
    private void displayStoreMenu() {
        System.out.println("***** Welcome to Store Menu *****");
        System.out.println("1 -> Stores Preview");
        System.out.println("2 -> Add Store");
        System.out.println("3 -> Add Item");
        System.out.println("4 -> Remove Item");
        System.out.println("5 -> Return");
    }

    // EFFECTS: displays Shopping menu
    private void displayShoppingMenu() {
        System.out.println("***** Welcome to Shopping List *****");
        System.out.println("1 -> Shopping List Preview");
        System.out.println("2 -> Add/Remove Shopping Items");
        System.out.println("3 -> Return");
    }

    // EFFECTS: displays Main menu
    private void displayMainMenu() {
        System.out.println("***** Welcome to FindMyGroceries *****");
        System.out.println("1 -> Shopping List");
        System.out.println("2 -> Stores");
        System.out.println("3 -> Quit");
    }
}
