/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

public class HabitLog {
    private int id;
    private int habitId;
    private LocalDate dateLogged;

    public HabitLog(int habitId, LocalDate dateLogged) {
        this.habitId = habitId;
        this.dateLogged = dateLogged;
    }

    public HabitLog(int id, int habitId, LocalDate dateLogged) {
        this(habitId, dateLogged);
        this.id = id;
    }

    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public LocalDate getDateLogged() {
        return dateLogged;
    }

    public void setDateLogged(LocalDate dateLogged) {
        this.dateLogged = dateLogged;
    }
    
}
