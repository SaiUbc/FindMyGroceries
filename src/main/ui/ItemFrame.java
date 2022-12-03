package ui;

import model.Item;
import model.Store;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemFrame extends JFrame {
    protected JFrame itemFrame;
    private JTextField txtItemName;
    private JTable table;
    private JTextField txtItemPrice;
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scrollPane;
    Store store;

    //MODIFIES: this
    //EFFECTS: constructs the ItemFrame
    public ItemFrame(Store store) {
        this.store = store;
        initialize();
    }

    //MODIFIES: this
    //EFFECTS: initializes all the JSwing elements
    private void initialize() {
        itemFrame = new JFrame();
        itemFrame.getContentPane().setBackground(SystemColor.menu);
        itemFrame.setBounds(100, 100, 653, 533);
        itemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemFrame.getContentPane().setLayout(null);

        initializeLabels();
        initializeButtons();
        txtFields();
        scrollPane();
        table();
        showItems();
    }

    //EFFECTS: initializes all the labels in the frame
    private void initializeLabels() {
        itemName();
        topLabel();
        itemPrice();
        dollarSymbol();
        lemon();
        milk();
        pretzel();
        avocado();
        storeName();
        itemPrice();
        storeNamePreview();
    }

    //EFFECTS: initializes all the buttons in the frame
    private void initializeButtons() {
        addItemButton();
        deleteItemButton();
        updateItemButton();
        clearItemButton();
        backButton();
    }

    //MODIFIES: this
    //EFFECTS: initializes the add button in the frame
    private void addItemButton() {
        JButton btnAddItem = new JButton("Add Item");
        btnAddItem.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtItemName.getText().equals("") || txtItemPrice.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
                }
                try {
                    Float.parseFloat(txtItemPrice.getText());
                    Item item = new Item(txtItemName.getText(), Float.parseFloat(txtItemPrice.getText()));
                    store.storeItem(item);
                    model.addRow(new Object[]{txtItemName.getText(), txtItemPrice.getText()});
                    txtItemName.setText(null);
                    txtItemPrice.setText(null);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please ensure to enter a number for price");
                }
            }
        });
        btnAddItem.setBounds(31, 235, 117, 29);
        itemFrame.getContentPane().add(btnAddItem);
    }

    //MODIFIES: this
    //EFFECTS: initializes the update button in the frame
    private void updateItemButton() {
        JButton btnUpdateItem = new JButton("Update Item");
        btnUpdateItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = table.getSelectedRow();
                for (Item item : store.getStoreItems()) {
                    if (item.getItemName().equals(model.getValueAt(n, 0))) {
                        item.setItemName(txtItemName.getText());
                        item.setItemPrice(Float.parseFloat(txtItemPrice.getText()));
                    }
                }
                model.setValueAt(txtItemName.getText(), n, 0);
                model.setValueAt(txtItemPrice.getText(), n, 1);
            }
        });
        btnUpdateItem.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnUpdateItem.setBounds(160, 235, 117, 29);
        itemFrame.getContentPane().add(btnUpdateItem);
    }

    //MODIFIES: this
    //EFFECTS: initializes the delete button in the frame
    private void deleteItemButton() {
        JButton btnDeleteItem = new JButton("Delete Item");
        btnDeleteItem.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnDeleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = table.getSelectedRow();
                if (n >= 0) {
                    store.removeItem((String) model.getValueAt(n, 0));
                    model.removeRow(n);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select a Row");
                }
            }
        });
        btnDeleteItem.setBounds(31, 276, 117, 29);
        itemFrame.getContentPane().add(btnDeleteItem);
    }

    //MODIFIES: this
    //EFFECTS: initializes the clear button in the frame
    private void clearItemButton() {
        JButton btnClearItem = new JButton("Clear Item");
        btnClearItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtItemName.setText(null);
                txtItemPrice.setText(null);
            }
        });
        btnClearItem.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnClearItem.setBounds(160, 276, 117, 29);
        itemFrame.getContentPane().add(btnClearItem);
    }

    //MODIFIES: this
    //EFFECTS: initializes the back button in the frame
    private void backButton() {
        JButton btnback = new JButton();
        btnback.setIcon(new ImageIcon("./data/backButton.png"));
        btnback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemFrame.dispose();
            }
        });
        btnback.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnback.setBounds(6, 6, 40, 40);
        itemFrame.getContentPane().add(btnback);
    }

    //MODIFIES: this
    //EFFECTS: initializes the item name label in the frame
    private void itemName() {
        JLabel lblItemName = new JLabel("Item name: ");
        lblItemName.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        lblItemName.setBounds(45, 153, 103, 16);
        itemFrame.getContentPane().add(lblItemName);
    }

    //MODIFIES: this
    //EFFECTS: initializes the top label in the frame
    private void topLabel() {
        JLabel lblTopLabelSign = new JLabel("");
        lblTopLabelSign.setIcon(new ImageIcon("./data/StoreMenuList.png"));
        lblTopLabelSign.setBounds(73, 13, 500, 60);
        itemFrame.getContentPane().add(lblTopLabelSign);
    }

    //MODIFIES: this
    //EFFECTS: initializes the item price label in the frame
    private void itemPrice() {
        JLabel lblItemPrice = new JLabel("Item price: ");
        lblItemPrice.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        lblItemPrice.setBounds(45, 182, 86, 26);
        itemFrame.getContentPane().add(lblItemPrice);
    }

    //MODIFIES: this
    //EFFECTS: initializes the dollar symbol label in the frame
    private void dollarSymbol() {
        JLabel lblDollarSymbol = new JLabel("$");
        lblDollarSymbol.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        lblDollarSymbol.setBounds(203, 187, 22, 16);
        itemFrame.getContentPane().add(lblDollarSymbol);
    }

    //MODIFIES: this
    //EFFECTS: initializes the lemon label in the frame
    private void lemon() {
        JLabel lblLemon = new JLabel("lemon");
        lblLemon.setIcon(new ImageIcon("./data/Lemon.png"));
        lblLemon.setBounds(296, 335, 86, 125);
        itemFrame.getContentPane().add(lblLemon);
    }

    //MODIFIES: this
    //EFFECTS: initializes the milk label in the frame
    private void milk() {
        JLabel lblMilk = new JLabel("milk");
        lblMilk.setIcon(new ImageIcon("./data/Milk.png"));
        lblMilk.setBounds(75, 335, 103, 135);
        itemFrame.getContentPane().add(lblMilk);
    }

    //MODIFIES: this
    //EFFECTS: initializes the pretzel label in the frame
    private void pretzel() {
        JLabel lblPretzel = new JLabel("pretzel");
        lblPretzel.setIcon(new ImageIcon("./data/Pretzel.png"));
        lblPretzel.setBounds(183, 340, 117, 125);
        itemFrame.getContentPane().add(lblPretzel);
    }

    //MODIFIES: this
    //EFFECTS: initializes the avocado label in the frame
    private void avocado() {
        JLabel lblAvocado = new JLabel("avocado");
        lblAvocado.setIcon(new ImageIcon("./data/Avocado.png"));
        lblAvocado.setBounds(-22, 335, 103, 125);
        itemFrame.getContentPane().add(lblAvocado);
    }

    //MODIFIES: this
    //EFFECTS: initializes the store name label in the frame
    private void storeName() {
        JLabel lblStoreItem = new JLabel("Store name:");
        lblStoreItem.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        lblStoreItem.setBounds(40, 104, 91, 37);
        itemFrame.getContentPane().add(lblStoreItem);
    }

    //MODIFIES: this
    //EFFECTS: initializes the store name label for the selected store in the frame
    private void storeNamePreview() {
        String storeName = store.getStoreName();
        JLabel lblStoreName = new JLabel(storeName);
        lblStoreName.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        lblStoreName.setBounds(143, 115, 226, 16);
        itemFrame.getContentPane().add(lblStoreName);
    }

    //MODIFIES: this
    //EFFECTS: initializes all the textfields in the frame
    private void txtFields() {
        txtItemName = new JTextField();
        txtItemName.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        txtItemName.setBackground(SystemColor.window);
        txtItemName.setBounds(143, 147, 226, 26);
        itemFrame.getContentPane().add(txtItemName);
        txtItemName.setColumns(10);

        txtItemPrice = new JTextField();
        txtItemPrice.setFont(new Font("American Typewriter", Font.PLAIN, 16));
        txtItemPrice.setColumns(10);
        txtItemPrice.setBackground(SystemColor.window);
        txtItemPrice.setBounds(143, 182, 55, 26);
        itemFrame.getContentPane().add(txtItemPrice);
    }

    //MODIFIES: this
    //EFFECTS: initializes the table for this JFrame
    private void table() {
        table = new JTable();
        table.setBackground(SystemColor.textHighlight);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        Object[] column = {"Name", "Price"};
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);
        table.setBorder(new SoftBevelBorder(BevelBorder.RAISED,
                null, null, null, null));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int n = table.getSelectedRow();
                if (n >= 0) {
                    txtItemName.setText(model.getValueAt(n, 0).toString());
                    txtItemPrice.setText(model.getValueAt(n, 1).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select a Row");
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: initializes the scroll plane
    private void scrollPane() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(382, 95, 236, 324);
        itemFrame.getContentPane().add(scrollPane);
    }

    //MODIFIES: this
    //EFFECTS: It shows all the store items in the JFrame
    private void showItems() {
        for (Item item : store.getStoreItems()) {
            model.addRow(new Object[]{item.getItemName(), item.getItemPrice()});
        }
    }
}