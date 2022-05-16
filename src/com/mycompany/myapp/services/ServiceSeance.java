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
//import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Map;
import java.util.List;

/**
 *
 * @author emnat
 */
public class ServiceSeance {
   /* public static ServiceSeance instance=null;
    
    private ConnectionRequest req;
    
    
    public static ServiceSeance getInstance(){
    if(instance==null)
            instance=new ServiceSeance();
    return instance;
            }
            
    public  ServiceSeance(){
    req=new ConnectionRequest();
   
    
    
    }
    public void ajoutSeance(Seance Seance){
    String url=Statics.BASE_URL+"/ajouterSeanceJSON?titre="+Seance.getTitre()+"&nome="
            +Seance.getNomE()+"&imc="+Seance.getImc()+"&nb_heure="+Seance.getNb_heure()+
            "&type="+Seance.getType();
    req.setUrl(url);
    req.addResponseListener((e)->{
    String str= new String(req.getResponseData());
        System.out.println("data=="+str);});
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    
    }
    
    
    public ArrayList<Seance>afficherSeance(){
    ArrayList<Seance> result= new ArrayList<>();
    String url=Statics.BASE_URL+"/listSeanceJSON";
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               jsonp=new JSONParser();
                try{
                
                Map<String,Object>mapSeance=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapSeance.get("root");
                for(Map<String,Object> obj:listOfMaps){
                Seance ce=new Seance();
                int id=Integer.parseInt(obj.get("is_Seance").toString());
                String titre=obj.get("titre").toString();
                 String type=obj.get("type").toString();
                  String imc=obj.get("imc").toString();
                   String nome=obj.get("nome").toString();
                    int nb_heure=Integer.parseInt(obj.get("nb_heure").toString());
                            ce.setId_Seance(id);
                            ce.setTitre(titre);
                            ce.setNb_heure((int)nb_heure);
                            ce.setImc(imc);
                            ce.setNomE(nome);
                            result.add(ce);
                            
                    
                
                }
                        }catch(Exception ex){ex.printStackTrace(); }
            }
        });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
    }*/
     public ArrayList<Seance> Seance;
    
    public static ServiceSeance instance=null;
    public boolean resultOK;
    private  final ConnectionRequest req;

    private ServiceSeance() {
         req = new ConnectionRequest();
    }

    public static ServiceSeance getInstance() {
        if (instance == null) {
            instance = new ServiceSeance();
        }
        return instance;
    }

    public boolean addSeance(Seance t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics.BASE_URL + "ajouterSeanceJSON?nomT=" + t.getNom_T()+ "&nomE=" + t.getNom_E()+ "&heureSeance=" + t.getHeure_seance();
       
    
       req.setUrl(url);
       
       req.addArgument("nomT", t.getNom_T());
       req.addArgument("nomE", t.getNom_E()+"");
       req.addArgument("heureSeance", t.getHeure_seance()+"");
       //req.addArgument("date_seance", t.getDate_seance()+"");
      // req.addArgument("nb_heure", t.getNb_heure()+"");
     
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Seance> parseSeance(String jsonText){
        try {
            Seance=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> SeanceListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)SeanceListJson.get("root");
            for(Map<String,Object> obj : list){
                Seance p = new Seance();
                System.out.println(obj.get("nomT"));
             // int id=Integer.parseInt(obj.get("id_Seance").toString());
               // System.out.println("id du Seance  "+obj.get("id_Seance"));
                String titre=obj.get("nomT").toString();
//                String id=obj.get("id_Seance").toString();
               // String nb_heure=obj.get("nb_heure").toString();
                 String nom_e=obj.get("nomE").toString();
                  String heure=obj.get("heureSeance").toString();
                //   Date date=obj.get("date_seance").getInstance().getTime();
                  
                   
                   // int nb_heure=;
                   
                         //   p.setId_Seance(id);
                            p.setNom_T(titre);
                           // System.out.println("ceci est le nb heure:  "+nb_heure);
                //            p.setNb_heure((int)Float.parseFloat(obj.get("nb_heure").toString()));
                            p.setHeure_seance(heure);
                            p.setNom_E(nom_e);
                            
                            

                    
                Seance.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Seance;
    }
    
    public ArrayList<Seance> getAllSeance(){
      String url = Statics.BASE_URL+"listSeanceJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Seance = parseSeance(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Seance;
    }
    
        public boolean supprimerSeance(String titre){
     String url = Statics.BASE_URL+"deleteSeanceJSON?nom_T="+titre;
     req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    
    }
    
    public boolean modifierSeance(Seance p) {
        System.out.println(p);
        System.out.println("********");
       String url = Statics.BASE_URL + "updateSeanceJSON?nom_T=" + p.getNom_T()+ "&heure_seance=" + p.getHeure_seance()+ "&nom_E=" + p.getNom_E();
       
    
       req.setUrl(url);
       
       
      // req.addArgument("nom_T", p.getNom_T()+"");
       req.addArgument("heure_seance", p.getHeure_seance()+"");
       req.addArgument("nom_E", p.getNom_E()+"");
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
     
    
    
    
    
    
    
    
}
