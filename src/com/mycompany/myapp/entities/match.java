/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;
import javafx.scene.control.Button;
   
/**
 *
 * @author Sahar Zouari
 */
public class match {
 private int id_match ;
 private int id_equipe1 ;
 private int id_equipe2 ;
 private Date date_match ;
 private int resultat_match ;
 private Button modifier;
 private Button supprimer;

    public match() {
    }

    public match(int id_equipe1, int id_equipe2, Date date_match, int resultat_match) {
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
        this.date_match = date_match;
        this.resultat_match = resultat_match;
    }

    public match(int id_match, int id_equipe1, int id_equipe2, Date date_match, int resultat_match) {
        this.id_match = id_match;
        this.id_equipe1 = id_equipe1;
        this.id_equipe2 = id_equipe2;
        this.date_match = date_match;
        this.resultat_match = resultat_match;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public Button getModifier() {
        return modifier;
    }

    public Button getSupprimer() {
        return supprimer;
    }

    public int getId_match() {
        return id_match;
    }

    public int getId_equipe1() {
        return id_equipe1;
    }

    public int getId_equipe2() {
        return id_equipe2;
    }

    public Date getDate_match() {
        return date_match;
    }

    public int getResultat_match() {
        return resultat_match;
    }

    public void setId_match(int id_match) {
        this.id_match = id_match;
    }

    public void setId_equipe1(int id_equipe1) {
        this.id_equipe1 = id_equipe1;
    }

    public void setId_equipe2(int id_equipe2) {
        this.id_equipe2 = id_equipe2;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
    }

    public void setResultat_match(int resultat_match) {
        this.resultat_match = resultat_match;
    }

    @Override
    public String toString() {
        return "match{" + "id_match=" + id_match + ", id_equipe1=" + id_equipe1 + ", id_equipe2=" + id_equipe2 + ", date_match=" + date_match + ", resultat_match=" + resultat_match + '}';
    }
 
 
}
