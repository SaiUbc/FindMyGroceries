package ui;

import model.ShoppingList;
import model.Store;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreFrame extends JFrame {
    protected JFrame storeFrame;
    ShoppingList shoppingList;
    private JTextField textField;
    DefaultListModel<String> model;
    JList<String> list;
    JScrollPane scrollPane;

    //EFFECTS: initializes the constructor
    public StoreFrame(ShoppingList shoppingList) {
        initialize();
        showStoreList(shoppingList);
        this.shoppingList = shoppingList;
    }

    //MODIFIES: this
    //EFFECTS: creates a JFrame and initializes all the ui elements from JSwing
    private void initialize() {
        storeFrame = new JFrame();
        storeFrame.getContentPane().setBackground(SystemColor.menu);
        storeFrame.setBounds(100, 100, 653, 533);
        storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        storeFrame.getContentPane().setLayout(null);

        initializeButtons();
        initializeLabels();
        scrollPane();
        txtField();

        model = new DefaultListModel<>();
        list = new JList<>();
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        list.setBackground(SystemColor.textHighlight);
        list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        list.setBounds(73, 160, 494, 319);
        scrollPane.setViewportView(list);
    }

    //EFFECTS: initializes all the button
    private void initializeButtons() {
        addStoreButton();
        removeStoreButton();
        enterStoreButton();
        backButton();
    }

    //EFFECTS: initializes all the labels
    private void initializeLabels() {
        enterStoreLabel();
        topLabel();
        doughnutLabel();
        pearLabel();
    }

    //MODIFIES: this
    //EFFECTS: initializes a JTextField and adds it to the JFrame
    private void txtField() {
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBackground(SystemColor.window);
        textField.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        textField.setBounds(108, 79, 432, 35);
        storeFrame.getContentPane().add(textField);
        textField.setColumns(10);
    }

    //MODIFIES: this
    //EFFECTS: initializes an add store JButton and adds it to the JFrame
    private void addStoreButton() {
        JButton btnAddStore = new JButton("Add Store");
        btnAddStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter a valid store name");
                } else {
                    model.addElement(textField.getText());
                    Store store = new Store(textField.getText());
                    shoppingList.addStore(store);
                    textField.setText(null);
                }
            }
        });
        btnAddStore.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnAddStore.setBounds(73, 120, 117, 29);
        storeFrame.getContentPane().add(btnAddStore);
    }

    //MODIFIES: this
    //EFFECTS: initializes a remove store JButton and adds it to the JFrame
    private void removeStoreButton() {
        JButton btnRemoveStore = new JButton("Remove Store");
        btnRemoveStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    shoppingList.removeStore(shoppingList.findStore(list.getSelectedValue()));
                    model.removeElementAt(list.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a valid store");
                }
            }
        });
        btnRemoveStore.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnRemoveStore.setBounds(255, 119, 117, 29);
        storeFrame.getContentPane().add(btnRemoveStore);
    }

    //MODIFIES: this
    //EFFECTS: initializes an enter store JButton and adds it to the JFrame
    private void enterStoreButton() {
        JButton btnEnterStore = new JButton("Enter Store");
        btnEnterStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    Store store = shoppingList.findStore(list.getSelectedValue());
                    ItemFrame window = new ItemFrame(store);
                    window.itemFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a valid store");
                }
            }
        });
        btnEnterStore.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnEnterStore.setBounds(456, 119, 117, 29);
        storeFrame.getContentPane().add(btnEnterStore);
    }

    //MODIFIES: this
    //EFFECTS: initializes a back JButton and adds it to the JFrame
    private void backButton() {
        JButton btnback = new JButton();
        btnback.setIcon(new ImageIcon("./data/backButton.png"));
        btnback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storeFrame.dispose();
            }
        });
        btnback.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        btnback.setBounds(6, 6, 40, 40);
        storeFrame.getContentPane().add(btnback);
    }

    //MODIFIES: this
    //EFFECTS: initializes an enter store JLabel and adds it to the JFrame
    private void enterStoreLabel() {
        JLabel lblEnterStore = new JLabel("Enter Store:");
        lblEnterStore.setFont(new Font("American Typewriter", Font.PLAIN, 15));
        lblEnterStore.setBounds(18, 85, 87, 23);
        storeFrame.getContentPane().add(lblEnterStore);
    }

    //MODIFIES: this
    //EFFECTS: initializes a pear store JLabel and adds it to the JFrame
    private void pearLabel() {
        JLabel lblPear = new JLabel("New label");
        lblPear.setIcon(new ImageIcon("./data/PearImage.png"));
        lblPear.setBounds(-20, 374, 125, 125);
        storeFrame.getContentPane().add(lblPear);
    }

    //MODIFIES: this
    //EFFECTS: initializes a doughnut JLabel and adds it to the JFrame
    private void doughnutLabel() {
        JLabel lblDoughnut = new JLabel("New label");
        lblDoughnut.setIcon(new ImageIcon("./data/Doughnut.png"));
        lblDoughnut.setBounds(528, 380, 125, 125);
        storeFrame.getContentPane().add(lblDoughnut);
    }

    //MODIFIES: this
    //EFFECTS: initializes a top label JLabel and adds it to the JFrame
    private void topLabel() {
        JLabel lblTopLabelSign = new JLabel("");
        lblTopLabelSign.setIcon(new ImageIcon("./data/StoreMenu.png"));
        lblTopLabelSign.setBounds(73, 13, 500, 60);
        storeFrame.getContentPane().add(lblTopLabelSign);
    }

    //MODIFIES: this
    //EFFECTS: displays all the stores by adding it to JList Model
    private void showStoreList(ShoppingList shoppingList) {
        for (Store store : shoppingList.getStores()) {
            model.addElement(store.getStoreName());
        }
    }

    //THIS: this
    //EFFECTS: adds a JScrollPane to the JFrame
    private void scrollPane() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(73, 160, 494, 319);
        storeFrame.getContentPane().add(scrollPane);
    }
}
