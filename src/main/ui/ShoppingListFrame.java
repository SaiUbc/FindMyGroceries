package ui;

import model.ShoppingList;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingListFrame extends JFrame {
    protected JFrame shoppingListFrame;
    private JTextField textField;
    ShoppingList shoppingList;
    DefaultListModel<String> model;
    JList<String> list;
    JScrollPane scrollPane;

    //EFFECTS: initializes constructor
    public ShoppingListFrame(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        initialize();
    }

    //MODIFIES: this
    //EFFECTS: initializes JFrame and all the ui elements
    private void initialize() {
        shoppingListFrame = new JFrame();
        shoppingListFrame.getContentPane().setBackground(SystemColor.menu);
        shoppingListFrame.setBounds(100, 100, 653, 533);
        shoppingListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shoppingListFrame.getContentPane().setLayout(null);

        scrollPane();
        allLabelsInitialize();
        allButtonsInitialize();
        initializeTextField();
        initializeJList();
    }

    //MODIFIES: this
    //EFFECTS: initializes a JTextField and adds to the JFrame
    private void initializeTextField() {
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBackground(SystemColor.window);
        textField.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        textField.setBounds(108, 79, 432, 35);
        shoppingListFrame.getContentPane().add(textField);
        textField.setColumns(10);
    }

    //MODIFIES: this
    //EFFECTS: initializes the JList and adds to the JFrame
    private void initializeJList() {
        list = new JList<>();
        model = new DefaultListModel<>();
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        list.setBackground(SystemColor.textHighlight);
        list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        list.setBounds(73, 160, 494, 319);
        showShoppingList(shoppingList);
        scrollPane.setViewportView(list);
    }

    //MODIFIES: this
    //EFFECTS: initializes all the labels and adds to the Jframe
    private void allLabelsInitialize() {
        JLabel lblTopLabel = new JLabel("");
        lblTopLabel.setIcon(new ImageIcon("./data/ShoppingListMenuLabel.png"));
        lblTopLabel.setBounds(73, -21, 500, 100);
        shoppingListFrame.getContentPane().add(lblTopLabel);

        JLabel lblEnterItem = new JLabel("Enter Item:");
        lblEnterItem.setFont(new Font("American Typewriter", Font.PLAIN, 15));
        lblEnterItem.setBounds(18, 85, 83, 23);
        shoppingListFrame.getContentPane().add(lblEnterItem);

        JLabel lblGreenVeggie = new JLabel("New label");
        lblGreenVeggie.setIcon(new ImageIcon("./data/GreenVeggie.png"));
        lblGreenVeggie.setBounds(-12, 354, 125, 125);
        shoppingListFrame.getContentPane().add(lblGreenVeggie);

        JLabel lblCheese = new JLabel("New label");
        lblCheese.setIcon(new ImageIcon("./data/Cheese.png"));
        lblCheese.setBounds(522, 354, 125, 125);
        shoppingListFrame.getContentPane().add(lblCheese);
    }

    //EFFECTS: initializes all the buttons
    private void allButtonsInitialize() {
        addItemButton();
        deleteItemButton();
        processListButton();
        backButton();
    }

    //MODIFIES: this
    //EFFECTS: adds the add item JButton to JFrame
    private void addItemButton() {
        JButton btnAddItem = new JButton("Add Item");
        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Item name can't be empty!");
                } else {
                    model.addElement(textField.getText());
                    shoppingList.addItemToList(textField.getText());
                    textField.setText(null);
                }
            }
        });
        btnAddItem.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnAddItem.setBounds(73, 120, 117, 29);
        shoppingListFrame.getContentPane().add(btnAddItem);
    }

    //MODIFIES: this
    //EFFECTS: adds a delete item JButton to JFrame
    private void deleteItemButton() {
        JButton btnDeleteItem = new JButton("Delete Item");
        btnDeleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    if (shoppingList.getShoppingList().contains(list.getSelectedValue())) {
                        shoppingList.removeItemFromList(list.getSelectedValue());
                        model.removeElementAt(list.getSelectedIndex());
                    } else {
                        JOptionPane.showMessageDialog(null, "improper save: can't find item");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select a valid item");
                }
            }
        });
        btnDeleteItem.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnDeleteItem.setBounds(255, 119, 117, 29);
        shoppingListFrame.getContentPane().add(btnDeleteItem);
    }

    //MODIFIES: this
    //EFFECTS: adds the process list JButton to JFrame
    private void processListButton() {
        JButton btnProcessList = new JButton("Process list");
        btnProcessList.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnProcessList.setBounds(456, 119, 117, 29);
        shoppingListFrame.getContentPane().add(btnProcessList);
        btnProcessList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FinalFrame window = new FinalFrame(shoppingList);
                window.finalFrame.setVisible(true);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds the back JButton to JFrame
    private void backButton() {
        JButton btnback = new JButton();
        btnback.setIcon(new ImageIcon("./data/backButton.png"));
        btnback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shoppingListFrame.dispose();
            }
        });
        btnback.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnback.setBounds(6, 6, 40, 40);
        shoppingListFrame.getContentPane().add(btnback);
    }

    //MODIFIES: this
    //EFFECTS: displays the shopping list items by adding it to JList
    private void showShoppingList(ShoppingList shoppingList) {
        int n = shoppingList.getShoppingList().size();
        for (int i = 0; i < n; i++) {
            model.addElement(shoppingList.getShoppingList().get(i));
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the JScrollPane to the JFrame
    private void scrollPane() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(73, 160, 494, 319);
        shoppingListFrame.getContentPane().add(scrollPane);
    }
}