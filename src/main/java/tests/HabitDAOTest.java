/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;
import dao.HabitDAO;
import model.Habit;

import java.util.List;

public class HabitDAOTest {
    public static void main(String[] args) {
        HabitDAO habitDAO = new HabitDAO();

        // Add a test habit
        Habit newHabit = new Habit("Read Books", "Read 30 pages every day");
        habitDAO.addHabit(newHabit);

        // Retrieve and print all habits
        List<Habit> habits = habitDAO.getAllHabits();
        for (Habit habit : habits) {
            System.out.println(habit.getId() + ": " + habit.getTitle() + " - " + habit.getDescription());
        }
    }
}

