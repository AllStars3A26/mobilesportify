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
public class Equipe {
   private String nom,type,description,email;
    private int id,nbr;

    public Equipe(String nom, String type, String description, String email, int id, int nbr) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.email = email;
        this.id = id;
        this.nbr = nbr;
    }

    public Equipe( int nbr,String nom, String type, String description, String email) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.email = email;
        this.nbr = nbr;
    }

    public Equipe() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "Equipe{" + "nom=" + nom + ", type=" + type + ", description=" + description + ", email=" + email + ", nbr=" + nbr + '}';
    }
    
    
    
}
