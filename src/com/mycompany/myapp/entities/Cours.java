/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author emnat
 */
public class Cours {
    private int id_cours,nb_heure;

    @Override
    public String toString() {
        return "Cours{" + "nb_heure=" + nb_heure + ", type=" + type + ", imc=" + imc + ", titre=" + titre + ", nomE=" + nomE + '}';
    }
    private String type , imc ,titre , nomE;

    public Cours(int nb_heure, String type, String imc, String titre, String nomE) {
        this.nb_heure = nb_heure;
        this.type = type;
        this.imc = imc;
        this.titre = titre;
        this.nomE = nomE;
    }

    public Cours(int id_cours, int nb_heure, String type, String imc, String titre, String nomE) {
        this.id_cours = id_cours;
        this.nb_heure = nb_heure;
        this.type = type;
        this.imc = imc;
        this.titre = titre;
        this.nomE = nomE;
    }

    public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public int getNb_heure() {
        return nb_heure;
    }

    public void setNb_heure(int nb_heure) {
        this.nb_heure = nb_heure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public Cours() {
    }
}
