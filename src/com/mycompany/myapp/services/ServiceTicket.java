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
import com.codename1.location.GeofenceListener;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.push.PushAction;
import com.codename1.push.PushActionCategory;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Ticket;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.IOException;


/**
 *
 * @author bhk
 */
public class ServiceTicket {

    public ArrayList<Ticket> Tickets;
    
    public static ServiceTicket instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTicket() {
         req = new ConnectionRequest();
    }

    public static ServiceTicket getInstance() {
        if (instance == null) {
            instance = new ServiceTicket();
        }
        return instance;
    }

 
    public boolean addTicket(Ticket t) {
        System.out.println(t);
        System.out.println("********");
      String url = Statics.BASE_URL + "addTicketE?titre=" + t.getLibelle()+"&type="+t.getType()+"&prix="+t.getPrix();
       //String url = Statics.BASE_URL + "addEventE";
    
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
      // req.addArgument("nom", t.getNom());
       //req.addArgument("type", t.getType()+"");
        //req.addArgument("description", t.getDescription());
       //req.addArgument("lieuid", t.getLieuid()+"");
       //req.addArgument("email", t.getEmail()+"");

       
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

    public ArrayList<Ticket> parseEvents(String jsonText){
        try {
            Tickets=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)EventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Ticket t = new Ticket();
                 System.out.println(t);
        System.out.println("********");
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                t.setId((int)id);
                t.setLibelle((obj.get("libelle").toString()));
                t.setType(obj.get("type").toString());
                t.setPrix((int)prix);  

                
                Tickets.add(t);
            }
            /*
            
            Students=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> StudentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)StudentsListJson.get("root");
            for(Map<String,Object> obj : list){
                Student t = new Student();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setEmail((obj.get("email").toString()));
                    t.setNsc(obj.get("nsc").toString());
                Students.add(t);
            }
            */
            
        } catch (IOException ex) {
            
        }
        return Tickets;
    }
    
    public ArrayList<Ticket> getAllTicket(){
        //String url = Statics.BASE_URL+"/Events/";
        String url = Statics.BASE_URL +"AllTicket";
        req.setUrl(url);
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Tickets = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Tickets;
    }

    
        //Delete 
    public boolean deleteTicket(int id ) {
        String url = Statics.BASE_URL +"deleteTicketE?id_Ticket="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    
    
    //Update 
    public boolean modifierTicket(Ticket event) {
        String url = Statics.BASE_URL +"updateTicket1?id_Ticket="+event.getId()+"&titre="+event.getLibelle()+"&type="+event.getType()+"&prix="+event.getPrix();
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
    
    
}
