/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Ramez
 */
public class ServiceTerrain {
    
    public ArrayList<Terrain> Terrains;
    public static ServiceTerrain instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceTerrain() {
        req = new ConnectionRequest();
    }
    
    public static ServiceTerrain getInstance(){
        if(instance == null)
            instance = new ServiceTerrain();
        return instance;
    }
    
    public boolean addTerrain(Terrain p){
        /*String url = Statics.BASE_URL+ "/promotion/Promotion.php?service=create"+
                "&Type="+p.getType()+
                "&title="+p.getTitle()+
                "&description="+p.getDescription()+
                "&scoreMin="+ p.getScoreMin();*/
        
        String url = Statics.BASE_URL+"ajouterterrainJSON?nom="+p.getNom()+"&type="+p.getType()+"&description="+p.getDescription()+"&adresse="+p.getAdresse()+"&disponibilite="+p.getDisponibilite();
        req.setUrl(url);
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
    
    public boolean deleteTerrain(int id){
        String url = Statics.BASE_URL+ "deleteterrain?id="+id;
        
        req.setUrl(url);
        /*req.addResponseListener((e)->{
            String str = new String (req.getResponseData());
            Dialog.show("Confirmation", "Suppression est effectué", "OK", null);
        });*/
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean updateTerrain(Terrain pr){
        String url = Statics.BASE_URL +"updateterrain1?id="+pr.getId()+"&nom="+pr.getNom()+"&type="+pr.getType()+"&description="+pr.getDescription()+"&adresse="+pr.getAdresse()+"&disponibilite="+pr.getDisponibilite();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    public ArrayList<Terrain> parseTerrain(String jsonText){
        try {
            Terrains=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson =  j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            //System.out.println("ttttttttttttt"+tasksListJson.get("root"));
            for(Map<String,Object> obj : list){
                Terrain p = new  Terrain();
                                 System.out.println(p);
        System.out.println("********");
                float id = Float.parseFloat(obj.get("id").toString());
                 System.out.println("********");
                float disponibilite = Float.parseFloat( obj.get("disponibilite").toString());
                // float Nom = Float.parseFloat(obj.get("nom").toString());  
        System.out.println("********");
                
                p.setId((int)id);
                 System.out.println("********");
                p.setNom(obj.get("nom_terrain").toString());
                 System.out.println("********");
                p.setAdresse(obj.get("adresse_terrain").toString());
                 System.out.println("********");
                p.setType(obj.get("type_terrain").toString());
                p.setDescription(obj.get("description_terrain").toString());
                p.setDisponibilite((int) disponibilite);
                        System.out.println(p);
                      
                Terrains.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return Terrains;
    }
    
    public ArrayList<Terrain> getAllTerrain(){
       
        String url = Statics.BASE_URL+"Allterrain";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Terrains = parseTerrain(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Terrains;
    }
}
