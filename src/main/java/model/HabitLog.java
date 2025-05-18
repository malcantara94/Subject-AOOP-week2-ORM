package model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "habit_log")
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    private LocalDate dateLogged;

    public HabitLog() {}

    public HabitLog(Habit habit, LocalDate dateLogged) {
        this.habit = habit;
        this.dateLogged = dateLogged;
    }

    public HabitLog(int id, Habit habit, LocalDate dateLogged) {
        this(habit, dateLogged);
        this.id = id;
    }

    // Getters and setters...

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Habit getHabit() { return habit; }

    public void setHabit(Habit habit) { this.habit = habit; }

    public LocalDate getDateLogged() { return dateLogged; }

    public void setDateLogged(LocalDate dateLogged) { this.dateLogged = dateLogged; }
}
