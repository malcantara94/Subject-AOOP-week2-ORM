/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

@Entity
@Table(name = "habit")
public class Habit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitLog> logs;
        
    // Constructors
    public Habit() {
        // Hibernate needs this - no-argument constructor
    }
        
    public Habit(String title, String description) {
        this.title = title;
        this.description = description;
    }

    
    public Habit(int id, String title, String description) {
        this(title, description);
        this.id = id;
    }
    
    public void updateHabit(Habit habit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            session.update(habit); //make sure the habit has an ID set
            tx.commit();
        }
    }
    
    public void deleteHabit(Habit habit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(habit);  // Habit must be managed (attached) or fetched first
            tx.commit();
        }
    }


    // Getters and setters...
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<HabitLog> getLogs() {
        return logs;
    }

    public void setLogs(List<HabitLog> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return title; // for display in JComboBox
    }
}
