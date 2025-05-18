/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HibernateGuiTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hibernate Connection Tester");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JLabel statusLabel = new JLabel("Click the button to test Hibernate connection.", SwingConstants.CENTER);
            JButton testButton = new JButton("Test Hibernate Connection");

            testButton.addActionListener((ActionEvent e) -> {
                try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
                    Session session = factory.openSession();
                    statusLabel.setText("✅ Hibernate connected successfully!");
                    session.close();
                } catch (Exception ex) {
                    statusLabel.setText("❌ Connection failed. See console for details.");
                    ex.printStackTrace();
                }
            });

            frame.add(statusLabel, BorderLayout.CENTER);
            frame.add(testButton, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }
}
