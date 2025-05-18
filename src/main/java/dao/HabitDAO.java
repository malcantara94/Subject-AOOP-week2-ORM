/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Habit;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitDAO {

    // Add a new habit to the database
    public void addHabit(Habit habit) {
        String sql = "INSERT INTO habit (title, description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, habit.getTitle());
            stmt.setString(2, habit.getDescription());
            stmt.executeUpdate();

            System.out.println("Habit added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding habit:");
            e.printStackTrace();
        }
    }

    // Get all habits from the database
    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        String sql = "SELECT id, title, description FROM habit";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Habit habit = new Habit(id, title, description);
                habits.add(habit);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving habits:");
            e.printStackTrace();
        }
        return habits;
    }
}
