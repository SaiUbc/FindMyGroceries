package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingListFrame {
    protected JFrame frame;

    public ShoppingListFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 687, 645);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("SHOPPING LIST PREVIEW");
        lblNewLabel.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 40));
        lblNewLabel.setBounds(129, 57, 431, 86);
        frame.getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(90, 155, 505, 409);
        frame.getContentPane().add(scrollPane);

        showReturnButton();
        JButton btnBack = new JButton("Return");
        btnBack.setBounds(6, 6, 117, 29);
        frame.getContentPane().add(btnBack);

        JButton btnNewButton = new JButton("Print");
        btnNewButton.setBounds(306, 576, 67, 29);
        frame.getContentPane().add(btnNewButton);
    }

    private void showReturnButton() {
        JButton btnBack = new JButton("Return");
        btnBack.setBounds(6, 6, 117, 29);
        frame.getContentPane().add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
}
