package ui;

import model.Event;
import model.EventLog;
import model.ShoppingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuFrame {
    protected JFrame frame;
    ShoppingList shoppingList;
    private static final String JSON_STORE = "./data/shoppingList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: constructos a JFrame and initializes it
    public MenuFrame() {
        initialize();
    }

    //EFFECTS: initializes all the ui elements for the JFrame from jSwing library
    private void initialize() {
        initializeMainPanel();
        initializeAllButtons();
    }

    //MODIFIES: this
    //EFFECTS: initializes the JFrame, top label and images
    private void initializeMainPanel() {
        frame = new JFrame();
        frame.getContentPane().setBackground(UIManager.getColor("menu"));
        frame.setBounds(100, 100, 653, 533);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTopLabelSign = new JLabel("");
        lblTopLabelSign.setIcon(new ImageIcon("./data/TopLabel.png"));
        lblTopLabelSign.setBounds(82, 51, 500, 60);
        frame.getContentPane().add(lblTopLabelSign);

        JLabel lblLeftImage = new JLabel("");
        lblLeftImage.setIcon(new ImageIcon("./data/LeftImage.png"));
        lblLeftImage.setBounds(17, 171, 200, 200);
        frame.getContentPane().add(lblLeftImage);

        JLabel lblRightImage = new JLabel("");
        lblRightImage.setIcon(new ImageIcon("./data/RightImage.png"));
        lblRightImage.setBounds(430, 171, 200, 200);
        frame.getContentPane().add(lblRightImage);
        shoppingList = new ShoppingList("user");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: initializes all the buttons
    private void initializeAllButtons() {
        initializeBtnShoppingList();
        initializeBtnStores();
        initializeBtnSaveList();
        initializeBtnLoadList();
        initializeBtnQuit();
    }

    //MODIFIES: this
    //EFFECTS: adds Shopping List button to the JFrame
    private void initializeBtnShoppingList() {
        JButton btnShoppingList = new JButton("Shopping List Menu ");
        btnShoppingList.setBackground(new Color(255, 255, 255));
        btnShoppingList.setForeground(new Color(0, 0, 0));
        btnShoppingList.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnShoppingList.setBounds(242, 140, 166, 57);
        frame.getContentPane().add(btnShoppingList);
        btnShoppingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShoppingListFrame window = new ShoppingListFrame(shoppingList);
                window.shoppingListFrame.setVisible(true);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds Store button to the JFrame
    private void initializeBtnStores() {
        JButton btnStoreMenu = new JButton("Stores Menu");
        btnStoreMenu.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnStoreMenu.setBounds(242, 216, 166, 57);
        frame.getContentPane().add(btnStoreMenu);
        btnStoreMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent s) {
                StoreFrame window = new StoreFrame(shoppingList);
                window.storeFrame.setVisible(true);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds Save button to the JFrame
    private void initializeBtnSaveList() {
        JButton btnSaveList = new JButton("Save List");
        btnSaveList.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnSaveList.setBounds(242, 373, 166, 57);
        btnSaveList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(shoppingList);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "Saved");
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Didn't Save");
                }
            }
        });
        frame.getContentPane().add(btnSaveList);
    }

    //MODIFIES: this
    //EFFECTS: adds Load button to the JFrame
    private void initializeBtnLoadList() {
        JButton btnLoadList = new JButton("Load List");
        btnLoadList.setFont(new Font("American Typewriter", Font.PLAIN, 14));
        btnLoadList.setBounds(242, 291, 166, 57);
        btnLoadList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    shoppingList = jsonReader.read();
                    JOptionPane.showMessageDialog(null, "Loaded");
                } catch (IOException f) {
                    JOptionPane.showMessageDialog(null, "Error Loading");
                }
            }
        });
        frame.getContentPane().add(btnLoadList);
    }

    //MODIFIES: this
    //EFFECTS: adds Quit button to the JFrame
    private void initializeBtnQuit() {
        JButton btnQuit = new JButton("Quit");
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printLog(EventLog.getInstance());
                frame.dispose();
            }
        });
        btnQuit.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        btnQuit.setBounds(294, 457, 66, 29);
        frame.getContentPane().add(btnQuit);
    }

    //MODIFIES: this
    //EFFECTS: prints all the Event from EventLog to the console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }
}