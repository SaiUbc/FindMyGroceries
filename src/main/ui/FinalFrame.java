package ui;

import model.Item;
import model.ShoppingList;
import model.Store;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FinalFrame extends JFrame {
    protected JFrame finalFrame;
    JScrollPane scrollPane;
    DefaultListModel<String> model;
    JList<String> list;
    ShoppingList shoppingList;
    Map<String, Float> set = new HashMap<>();

    //EFFECTS: initializes a constructor
    public FinalFrame(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        initialize();
    }

    //MODIFIES: this
    //EFFECTS: initializes the JFrame elements and processData() functions
    private void initialize() {
        finalFrame = new JFrame();
        finalFrame.getContentPane().setBackground(SystemColor.menu);
        finalFrame.setBounds(100, 100, 653, 533);
        finalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finalFrame.getContentPane().setLayout(null);

        JLabel lblTopLabel = new JLabel("");
        lblTopLabel.setIcon(new ImageIcon("./data/ProcessedFrame.png"));
        lblTopLabel.setBounds(73, -21, 500, 100);
        finalFrame.getContentPane().add(lblTopLabel);

        JLabel btnBottom = new JLabel("");
        btnBottom.setIcon(new ImageIcon("./data/saveMoney.png"));
        btnBottom.setBounds(83, 91, 463, 51);
        finalFrame.getContentPane().add(btnBottom);

        scrollPane();
        initializeJList();
        processData();
    }

    //MODIFIES: this
    //EFFECTS: initializes a scroll plane
    private void scrollPane() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(73, 160, 494, 319);
        finalFrame.getContentPane().add(scrollPane);
    }

    //MODIFIES: this
    //EFFECTS: initializes a JList plane
    private void initializeJList() {
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        list.setBackground(SystemColor.textHighlight);
        list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        list.setBounds(73, 160, 494, 319);
        scrollPane.setViewportView(list);
    }


    //MODIFIES: this
    //EFFECTS: stores processed data into JList
    private void processData() {
        List<Store> stores = shoppingList.getStores();
        List<String> items = shoppingList.getShoppingList();

        for (String item : items) {
            String store = findCheapestStore(item, stores);
            model.addElement(item + " from " + store);
        }
    }

    //MODIFIES: this
    //EFFECTS: returns the store name for the cheapest store
    private String findCheapestStore(String itemname, List<Store> stores) {
        for (Store store: stores) {
            if (store.containsItemName(itemname)) {
                for (Item item : store.getStoreItems()) {
                    if (item.getItemName().equals(itemname)) {
                        float price = item.getItemPrice();
                        String name = store.getStoreName();
                        set.put(name, price);
                    }
                }
            }
        }

        float min = Collections.min(set.values());

        for (Map.Entry<String, Float> entry: set.entrySet()) {
            if (entry.getValue() == min) {
                String storeName = entry.getKey();
                return storeName;
            }
        }
        return null;
    }
}
