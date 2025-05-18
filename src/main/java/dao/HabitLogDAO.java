/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Habit;
import model.HabitLog;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class HabitLogDAO {

    public void logHabit(HabitLog log) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(log);
            tx.commit();
        }
    }

    public List<HabitLog> getLogsForHabit(int habitId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM HabitLog WHERE habit.id = :habitId", HabitLog.class)
                .setParameter("habitId", habitId)
                .list();
        }
    }
    
        public boolean isHabitLoggedToday(Habit habit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            LocalDate today = LocalDate.now();
            Long count = session.createQuery(
                "SELECT COUNT(*) FROM HabitLog WHERE habit = :habit AND dateLogged = :today", Long.class)
                .setParameter("habit", habit)
                .setParameter("today", today)
                .uniqueResult();
            return count != null && count > 0;
        }
    }
}
