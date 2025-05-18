/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Habit {
    private int id;
    private String title;
    private String description;

    public Habit(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Habit(int id, String title, String description) {
        this(title, description);
        this.id = id;
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
}
