/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Sahar Zouari
 */
public class tournoi {
    private int id_tournoi ;
    private String nom_tournoi ;
    private String date_tournoi ;
    private int nb_participants ;
    private String image_tournoi ;
    private int resultat_tournoi;
    private int heure;
    //private Button supprimer;
    //private ImageView img;
    
  

    public tournoi() {
    }

    public tournoi(int id_tournoi, String nom_tournoi, String date_tournoi, int nb_participants, String image_tournoi,int resultat_tournoi,int heure) {
        this.id_tournoi = id_tournoi;
        this.nom_tournoi = nom_tournoi;
        this.date_tournoi = date_tournoi;
        this.nb_participants = nb_participants;
        this.image_tournoi = image_tournoi;
        this.resultat_tournoi = resultat_tournoi;
        this.heure=heure;
    }

    public tournoi(int id_tournoi, String nom_tournoi, String date_tournoi, int nb_participants, int resultat_tournoi, int heure) {
        this.id_tournoi = id_tournoi;
        this.nom_tournoi = nom_tournoi;
        this.date_tournoi = date_tournoi;
        this.nb_participants = nb_participants;
        this.resultat_tournoi = resultat_tournoi;
        this.heure = heure;
    }

    public tournoi(String nom_tournoi, String date_tournoi, int nb_participants, String image_tournoi, int resultat_tournoi,int heure) {
        this.nom_tournoi = nom_tournoi;
        this.date_tournoi = date_tournoi;
        this.nb_participants = nb_participants;
        this.image_tournoi = image_tournoi;
        this.resultat_tournoi = resultat_tournoi;
        this.heure=heure;
    }
    

    public void setResultat_tournoi(int resultat_tournoi) {
        this.resultat_tournoi = resultat_tournoi;
    }

    public int getResultat_tournoi() {
        return resultat_tournoi;
    }


    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }
    
    

    

   

    public int getId_tournoi() {
        return id_tournoi;
    }

    public String getNom_tournoi() {
        return nom_tournoi;
    }

    public String getDate_tournoi() {
        return date_tournoi;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public String getImage_tournoi() {
        return image_tournoi;
    }

    public void setId_tournoi(int id_tournoi) {
        this.id_tournoi = id_tournoi;
    }

    public void setNom_tournoi(String nom_tournoi) {
        this.nom_tournoi = nom_tournoi;
    }

    public void setDate_tournoi(String date_tournoi) {
        this.date_tournoi = date_tournoi;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public void setImage_tournoi(String image_tournoi) {
        this.image_tournoi = image_tournoi;
    }

    @Override
    public String toString() {
        return "tournoi{" + "id_tournoi=" + id_tournoi + ", nom_tournoi=" + nom_tournoi + ", date_tournoi=" + date_tournoi + ", nb_participants=" + nb_participants + ", image_tournoi=" + image_tournoi + ", resultat_tournoi=" + resultat_tournoi + ", heure=" + heure + '}';
    }

    
}
