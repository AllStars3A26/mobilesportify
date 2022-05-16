/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Yassine
 */
public class Ticket{
      private int id,prix;
    private String type,libelle;

    public Ticket(int id, int prix, String type, String libelle) {
        this.id = id;
        this.prix = prix;
        this.type = type;
        this.libelle = libelle;
    }

    public Ticket(int prix, String type, String libelle) {
        this.prix = prix;
        this.type = type;
        this.libelle = libelle;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Ticket{" + "prix=" + prix + ", type=" + type + ", libelle=" + libelle + '}';
    }

 
 



   


    
    
    
   
    
    
      
}
