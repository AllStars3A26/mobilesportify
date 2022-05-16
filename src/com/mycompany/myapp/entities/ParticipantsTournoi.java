/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Sahar Zouari
 */
public class ParticipantsTournoi {
    private int id_participants;
    private int id_tournoi ;
    private int id_equipe;
    private String nom_equipe;
    private String nom_tournoi;

    public ParticipantsTournoi(String nom_equipe, String nom_tournoi) {
        this.nom_equipe = nom_equipe;
        this.nom_tournoi = nom_tournoi;
    }

    public ParticipantsTournoi(int id_participants, int id_tournoi, int id_equipe) {
        this.id_participants = id_participants;
        this.id_tournoi = id_tournoi;
        this.id_equipe = id_equipe;
    }

    public ParticipantsTournoi(int id_tournoi, int id_equipe) {
        this.id_tournoi = id_tournoi;
        this.id_equipe = id_equipe;
    }

    public ParticipantsTournoi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_participants() {
        return id_participants;
    }

    public int getId_tournoi() {
        return id_tournoi;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public String getNom_tournoi() {
        return nom_tournoi;
    }

    public void setId_participants(int id_participants) {
        this.id_participants = id_participants;
    }

    public void setId_tournoi(int id_tournoi) {
        this.id_tournoi = id_tournoi;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public void setNom_tournoi(String nom_tournoi) {
        this.nom_tournoi = nom_tournoi;
    }

    @Override
    public String toString() {
        return "ParticipantsTournoi{" + "id_participants=" + id_participants + ", id_tournoi=" + id_tournoi + ", id_equipe=" + id_equipe + ", nom_equipe=" + nom_equipe + ", nom_tournoi=" + nom_tournoi + '}';
    }
    
}
