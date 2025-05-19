package dao;

import java.util.List;
import model.Habit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HabitDAO {

    // Create (Save a new habit)
    public void saveHabit(Habit habit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(habit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Read (Get all habits)
    public List<Habit> getAllHabits() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Habit", Habit.class).list();
        }
    }

    // Update (Update existing habit)
    public void updateHabit(Habit habit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(habit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete (Remove habit)
    public void deleteHabit(Habit habit) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(habit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Optional: Find habit by id
    public Habit getHabitById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Habit.class, id);
        }
    }
}
