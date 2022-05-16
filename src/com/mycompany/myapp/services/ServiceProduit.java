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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.twilio.rest.proxy.v1.service.Session;

/**
 *
 * @author bhk
 */
public class ServiceProduit {

    public ArrayList<Produit> Produits;
    
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

 
    public boolean addProduit(Produit t) {
        System.out.println(t);
        System.out.println("********");
      String url = Statics.BASE_URL + "addProduitE?nom=" + t.getNom()+"&desc="+t.getDesc()+"&prix="+t.getPrix()+"&quan="+t.getQuan();
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
                String subject = "Ajout d'un produit";
                
                Email to = new Email(SessionManager.getEmail());
                Content content = new Content("text/plain", "Votre produit a ete ajouté sous le nom "+t.getNom());
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

    public ArrayList<Produit> parseEvents(String jsonText){
        try {
            Produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)EventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit t = new Produit();
                 System.out.println(t);
        System.out.println("********");
                float id = Float.parseFloat(obj.get("idprod").toString());
                float quan = Float.parseFloat(obj.get("quantprod").toString());
                float prix = Float.parseFloat(obj.get("prixprod").toString());
                t.setId((int)id);
                t.setNom((obj.get("nomprod").toString()));
                t.setDesc(obj.get("descprod").toString());
                t.setPrix((int) prix);  
                t.setQuan((int)quan);
               
                
               Produits.add(t);
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
        return Produits;
    }
    
    public ArrayList<Produit> getAllProduit(){
        //String url = Statics.BASE_URL+"/Events/";
        String url = Statics.BASE_URL +"AllProduit";
        req.setUrl(url);
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }

    
        //Delete 
    public boolean deleteProduit(int id ) {
        String url = Statics.BASE_URL +"deleteProduitE?id_Produit="+id;
        
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
    public boolean modifierProduit(Produit t) {
        String url = Statics.BASE_URL +"updateProduit1?id_Produit="+t.getId()+"&nom=" + t.getNom()+"&desc="+t.getDesc()+"&prix="+t.getPrix()+"&quan="+t.getQuan();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK= req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
       /* Email from = new Email("CulturaApplication@gmail.com");
                String subject = "Evenement";
                Email to = new Email(event.getEmail());
                Content content = new Content("text/plain", "Votre email a ete modifié");
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
                }*/
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    
    
}
