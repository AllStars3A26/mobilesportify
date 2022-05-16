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
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.SessionManager;
import com.mycompany.myapp.utils.Statics;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.IOException;


/**
 *
 * @author bhk
 */
public class ServiceEvent {

    public ArrayList<Event> Events;
    
    public static ServiceEvent instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
         req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

 
    public boolean addEvent(Event t) {
        System.out.println(t);
        System.out.println("********");
      String url = Statics.BASE_URL + "addEventE?titre=" + t.getNom()+"&type="+t.getType()+"&desc="+t.getDescription();
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
       
Email from = new Email("CulturaApplication@gmail.com");
                String subject = "Ajout d'un evenement";
                
                Email to = new Email(SessionManager.getEmail());
                Content content = new Content("text/plain", "Votre evenement a ete ajouté sous le nom "+t.getNom());
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();

                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println(response.getStatusCode());
                    System.out.println(response.getBody());
                    System.out.println(response.getHeaders());
                } catch (IOException ex) {
                    System.out.println("message non envoyé");
                }

       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Event> parseEvents(String jsonText){
        try {
            Events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)EventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Event t = new Event();
                 System.out.println(t);
        System.out.println("********");
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println("********");
                t.setId((int)id);
                System.out.println("********");
                t.setNom((obj.get("libelle").toString()));
                System.out.println("********");
                t.setType(obj.get("type").toString());
                System.out.println("********");
                t.setDescription("best event ever");  
                System.out.println("********");
                
                Events.add(t);
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
        return Events;
    }
    
    public ArrayList<Event> getAllEvents(){
        //String url = Statics.BASE_URL+"/Events/";
        String url = Statics.BASE_URL +"Allevents";
        req.setUrl(url);
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }

    
        //Delete 
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"deleteEventE?id_evenement="+id;
        
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
    public boolean modifierReclamation(Event event) {
        String url = Statics.BASE_URL +"updateEvent1?id_evenement="+event.getId()+"&titre="+event.getNom()+"&type="+event.getType()+"&desc="+event.getDescription();
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
