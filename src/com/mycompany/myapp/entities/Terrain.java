/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Skander BELHASSEN
 */
public class Terrain {
     private String nom,type,description,adresse;
     private int id , disponibilite;


  

    public Terrain() {
    }

    public Terrain(String nom, String type, String description, String adresse, int id, int disponibilite) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.adresse = adresse;
        this.id = id;
        this.disponibilite = disponibilite;
        
    }

    public Terrain(String nom, String type, String description, String adresse, int disponibilite) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.adresse = adresse;
        this.disponibilite = disponibilite;
    }


 

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Terrain{" + "nom=" + nom + ", type=" + type + ", description=" + description + ", adresse=" + adresse + ", disponibilite=" + disponibilite + '}';
    }
     
}
