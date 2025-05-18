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
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HabitDAO {

    // Add a new habit to the database
    public void addHabit(Habit habit) {
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Transaction tx = session.beginTransaction();
        session.save(habit);
        tx.commit();
      }
    }

    // Get all habits from the database
    public List<Habit> getAllHabits() {
      try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        return session.createQuery("FROM Habit", Habit.class).list();
      }
    }

}
