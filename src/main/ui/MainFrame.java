package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    protected JFrame frmFindmygroceriesexe;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        initializeMainPanel();
        initializeAllButtons();
    }

    private void initializeMainPanel() {
        frmFindmygroceriesexe = new JFrame();
        frmFindmygroceriesexe.getContentPane().setBackground(SystemColor.menu);
        frmFindmygroceriesexe.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
        frmFindmygroceriesexe.setTitle("FindMyGroceries.exe");
        frmFindmygroceriesexe.setBounds(100, 100, 709, 616);
        frmFindmygroceriesexe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmFindmygroceriesexe.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("Welcome to Find My Groceries");
        lblNewLabel.setBounds(209, 65, 309, 25);
        lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 20));
        frmFindmygroceriesexe.getContentPane().add(lblNewLabel);
    }

    private void initializeAllButtons() {
        JButton btnShoppingList = new JButton("Shopping List");
        btnShoppingList.setBounds(282, 123, 158, 59);
        btnShoppingList.setBackground(SystemColor.activeCaptionBorder);
        frmFindmygroceriesexe.getContentPane().add(btnShoppingList);

        JButton btnStores = new JButton("Stores");
        btnStores.setBounds(282, 194, 158, 59);
        frmFindmygroceriesexe.getContentPane().add(btnStores);

        JButton btnSaveList = new JButton("Save List");
        btnSaveList.setBounds(282, 265, 158, 59);
        frmFindmygroceriesexe.getContentPane().add(btnSaveList);

        JButton btnLoadList = new JButton("Load List");
        btnLoadList.setBounds(282, 336, 158, 59);
        frmFindmygroceriesexe.getContentPane().add(btnLoadList);

        JButton btnQuit = new JButton("Quit");
        btnQuit.setBounds(301, 489, 117, 29);
        frmFindmygroceriesexe.getContentPane().add(btnQuit);
    }
}
