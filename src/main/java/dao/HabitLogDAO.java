/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.HabitLog;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitLogDAO {

    public void logHabit(HabitLog log) {
        String sql = "INSERT INTO habit_log (habit_id, date_logged) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, log.getHabitId());
            stmt.setDate(2, Date.valueOf(log.getDateLogged()));
            stmt.executeUpdate();
            System.out.println("Habit log saved.");
        } catch (SQLException e) {
            System.out.println("Error logging habit:");
            e.printStackTrace();
        }
    }

    public List<HabitLog> getLogsForHabit(int habitId) {
        List<HabitLog> logs = new ArrayList<>();
        String sql = "SELECT id, habit_id, date_logged FROM habit_log WHERE habit_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, habitId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HabitLog log = new HabitLog(
                        rs.getInt("id"),
                        rs.getInt("habit_id"),
                        rs.getDate("date_logged").toLocalDate()
                );
                logs.add(log);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving logs:");
            e.printStackTrace();
        }
        return logs;
    }
}
