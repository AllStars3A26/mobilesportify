/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author emnat
 */
public class Seance {
  private  int id_seance,nb_participants;
  private Date date_seance;
  private String heure_seance,nom_T,nom_E;

    public Seance() {
    }

    public Seance(Date date_seance, String heure_seance, String nom_T, String nom_E, int nb_participants) {
        this.date_seance = date_seance;
        this.heure_seance = heure_seance;
        this.nom_T = nom_T;
        this.nom_E = nom_E;
        this.nb_participants = nb_participants;
    }

    public Seance(int id_seance, Date date_seance, String heure_seance, String nom_T, String nom_E, int nb_participants) {
        this.id_seance = id_seance;
        this.date_seance = date_seance;
        this.heure_seance = heure_seance;
        this.nom_T = nom_T;
        this.nom_E = nom_E;
        this.nb_participants = nb_participants;
    }

    public int getId_seance() {
        return id_seance;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public Date getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(Date date_seance) {
        this.date_seance = date_seance;
    }

    public String getHeure_seance() {
        return heure_seance;
    }

    public void setHeure_seance(String heure_seance) {
        this.heure_seance = heure_seance;
    }

    public String getNom_T() {
        return nom_T;
    }

    public void setNom_T(String nom_T) {
        this.nom_T = nom_T;
    }

    public String getNom_E() {
        return nom_E;
    }

    public void setNom_E(String nom_E) {
        this.nom_E = nom_E;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }
  
   
    
}
