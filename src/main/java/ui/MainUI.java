/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.HabitDAO;
import dao.HabitLogDAO;
import model.Habit;
import model.HabitLog;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MainUI extends JFrame {
    private JTextField titleField;
    private JTextArea descArea;
    private JButton saveButton;
    private JTextArea outputArea;
    private JComboBox<Habit> habitDropdown;
    private JButton logButton;
    private JTextArea logArea;

    private final HabitDAO habitDAO;
    private final HabitLogDAO habitLogDAO;

    public MainUI() {
        habitDAO = new HabitDAO();        // Uses Hibernate internally
        habitLogDAO = new HabitLogDAO();  // Uses Hibernate internally

        setTitle("Learning Habit Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Habit Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Description:"));
        descArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(descArea));

        saveButton = new JButton("Save Habit");
        inputPanel.add(saveButton);
        add(inputPanel, BorderLayout.NORTH);

        // Logging Panel
        JPanel logPanel = new JPanel(new BorderLayout());
        habitDropdown = new JComboBox<>();
        logButton = new JButton("Log Activity");
        logArea = new JTextArea(5, 30);
        logArea.setEditable(false);

        logPanel.add(habitDropdown, BorderLayout.NORTH);
        logPanel.add(logButton, BorderLayout.CENTER);
        logPanel.add(new JScrollPane(logArea), BorderLayout.SOUTH);
        add(logPanel, BorderLayout.SOUTH);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Button Listeners
        saveButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String desc = descArea.getText().trim();

            if (!title.isEmpty()) {
                Habit habit = new Habit(title, desc);
                habitDAO.addHabit(habit); // Hibernate saves entity
                showHabits();
                titleField.setText("");
                descArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Title cannot be empty!");
            }
        });

        logButton.addActionListener(e -> {
            Habit selected = (Habit) habitDropdown.getSelectedItem();
            if (selected != null) {
                HabitLog log = new HabitLog(selected, LocalDate.now());
                habitLogDAO.logHabit(log);
                updateLogs();
            }
        });

        habitDropdown.addActionListener(e -> updateLogs());

        showHabits();
    }

    private void showHabits() {
        List<Habit> habits = habitDAO.getAllHabits(); // Hibernate query
        outputArea.setText("");
        habitDropdown.removeAllItems();

        for (Habit h : habits) {
            outputArea.append(h.getId() + ": " + h.getTitle() + " - " + h.getDescription() + "\n");
            habitDropdown.addItem(h);
        }

        if (habitDropdown.getItemCount() > 0) {
            habitDropdown.setSelectedIndex(0);
            updateLogs();
        }
    }

    private void updateLogs() {
        Habit selected = (Habit) habitDropdown.getSelectedItem();
        if (selected == null) return;

        List<HabitLog> logs = habitLogDAO.getLogsForHabit(selected.getId()); // Hibernate query
        logArea.setText("Activity Log for: " + selected.getTitle() + "\n");

        for (HabitLog log : logs) {
            logArea.append("• " + log.getDateLogged() + "\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("Launching Habit Tracker...");
        try {
            MainUI ui = new MainUI();
            ui.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Startup failed: " + e.getMessage());
        }
    }

}
