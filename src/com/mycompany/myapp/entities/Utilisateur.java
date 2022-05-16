/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Lenovo
 */
public class Utilisateur {
     private int id;
    private String username;
    private String email;
    private String photo;
    private String fullname;
    private String motdepasse;
    private String adresse;
    private String num;


  

    public Utilisateur(String username, String email, String photo, String fullname, String motdepasse, String adresse, String num) {
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.fullname = fullname;
        this.motdepasse = motdepasse;
        this.adresse = adresse;
        this.num = num;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

 
    

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return fullname;
    }

    public void setNom(String nom) {
        this.fullname = nom;
    }

  

    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String username, String email) {
        this.username = username;
        this.email = email;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", adresse=" + email + ", photoDeProfil=" + photo + '}';
    }

}
