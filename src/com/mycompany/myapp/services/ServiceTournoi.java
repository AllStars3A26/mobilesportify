/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.tournoi;
import com.mycompany.myapp.sahargui.AjoutTournoiForm;
import com.mycompany.myapp.sahargui.TournoiForm;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map; 
import java.util.List; 

import java.io.IOException;




/**
 *
 * @author MAC
 */
public class ServiceTournoi {
    public ArrayList<tournoi> Tournois;
  public static boolean resultOk;
    public static ServiceTournoi instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceTournoi() {
        req = new ConnectionRequest();
    }

    public static ServiceTournoi getInstance() {
        if (instance == null) {
            instance = new ServiceTournoi();
        }
        return instance;
    }
public ArrayList<tournoi> parseTournois(String jsonText) {
        try {
            Tournois = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> TournoiListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) TournoiListJson.get("root");
            for(Map<String,Object> obj :list){
           
                tournoi h = new tournoi();
             
//                float heure = Float.parseFloat(obj.get("heure").toString());
                 float resultat_tournoi = Float.parseFloat(obj.get("resultatTournoi").toString());
                 float nb_participants = Float.parseFloat(obj.get("nbParticipants").toString());
                 float heure = Float.parseFloat(obj.get("heure").toString());
                h.setNom_tournoi(obj.get("nomTournoi").toString());
//                h.setDate_tournoi( obj.get("dateTournoi").toString());
                h.setHeure((int) heure);
                h.setResultat_tournoi((int) resultat_tournoi);
                h.setNb_participants((int) nb_participants);
                Tournois.add(h);
            }

        } catch (IOException ex) {

        }
        return Tournois;
    } 
 public ArrayList<tournoi> getAllTournois() {
        
 req = new ConnectionRequest();
        String url = Statics.BASE_URL +"JSON/tournoi/liste";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Tournois = parseTournois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Tournois;
    }
 public void Add(tournoi p ,Form previous,Resources res) {
     
        String url = Statics.BASE_URL + "JSON/tournoi/ajout?nom_tournoi="+p.getNom_tournoi()+"&resultat_tournoi="+p.getResultat_tournoi()+"&image_tournoi="+p.getImage_tournoi()+"&nb_participants="+p.getNb_participants()+"&heure="+p.getHeure();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
      // new TournoiForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
  public boolean delete(int id) {
        String url = Statics.BASE_URL + "JSON/tournoi/delete/"+id;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }
   public void Update(tournoi p ,Form previous,Resources res) {
        String url = Statics.BASE_URL + "JSON/tournoi/modif?id_tournoi="+p.getId_tournoi()+"&nom_tournoi="+p.getNom_tournoi()+"&resultat_tournoi="+p.getResultat_tournoi()+"&image_tournoi="+p.getImage_tournoi()+"&nb_participants="+p.getNb_participants()+"&heure="+p.getHeure();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        
        //new TournoiForm(previous,res).show();
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
}
