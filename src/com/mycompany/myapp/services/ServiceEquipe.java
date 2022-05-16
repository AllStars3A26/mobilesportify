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
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;



/**
 *
 * @author bhk
 */
public class ServiceEquipe {

    public ArrayList<Equipe> Equipes;
    
    public static ServiceEquipe instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEquipe() {
         req = new ConnectionRequest();
    }

    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }

 
    public boolean addEquipe(Equipe t) {
        System.out.println(t);
        
      String url = Statics.BASE_URL + "ajouterEquipeJSON?nom=" +t.getNom()+"&type="+t.getType()+"&description="+t.getDescription()+"&nbre="+t.getNbr()+"&email="+t.getEmail();
    
       req.setUrl(url);


       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
       
       
Email from = new Email("CulturaApplication@gmail.com");
                String subject = "Ajout d'un equipe";
                Email to = new Email(t.getEmail());
                Content content = new Content("text/plain", "Votre equipe a ete ajouté sous le nom "+t.getNom());
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

    public ArrayList<Equipe> parseEquipe(String jsonText){
        try {
            Equipes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> EventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)EventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Equipe t = new Equipe();
                 System.out.println(t);
        System.out.println("********");
                float id = Float.parseFloat(obj.get("id").toString());
                 System.out.println("********");
                 
                float nbr = Float.parseFloat(obj.get("nbre_joueur").toString());
                 System.out.println("********");
                t.setId((int)id);
                 System.out.println("********");
                t.setNom((obj.get("nom_equipe").toString()));
                 System.out.println("********");
                t.setType(obj.get("type_equipe").toString());
                t.setDescription(obj.get("description_equipe").toString());  
               t.setNbr((int)nbr);
                t.setEmail(obj.get("mail_equipe").toString());
                
                Equipes.add(t);
            }
       
   
            
        } catch (IOException ex) {
            
        }
        return Equipes;
    }
    
    public ArrayList<Equipe> getAllEquipes(){
        //String url = Statics.BASE_URL+"/Events/";
        String url = Statics.BASE_URL+"Allequipes";
        req.setUrl(url);
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Equipes = parseEquipe(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Equipes;
    }

    
        //Delete 
    public boolean deleteEquipe(int id ) {
        String url = Statics.BASE_URL +"deleteEquipeE?id="+id;
        
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
    public boolean modifierEquipe(Equipe event) {
        String url = Statics.BASE_URL +"updateequipe1?id="+event.getId()+"&nom="+event.getNom()+"&type="+event.getType()+"&description="+event.getDescription()+"&nbre="+event.getNbr()+"&email="+event.getEmail();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK= req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
       Email from = new Email("CulturaApplication@gmail.com");
                String subject = "equipe";
                Email to = new Email(event.getEmail());
                Content content = new Content("text/plain", "Votre equipe a ete modifié");
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
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    
    
}
