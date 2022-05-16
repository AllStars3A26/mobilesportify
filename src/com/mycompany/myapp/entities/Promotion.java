/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Ramez
 */
public class Promotion {
    
    private int id, scoreMin;
    private String Type, description, title;

    public Promotion() {
    }

    
    public Promotion(int scoreMin, String Type, String description, String title) {
        this.scoreMin = scoreMin;
        this.Type = Type;
        this.description = description;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(int scoreMin) {
        this.scoreMin = scoreMin;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", scoreMin=" + scoreMin + ", Type=" + Type + ", description=" + description + ", title=" + title + '}';
    }
        
}
