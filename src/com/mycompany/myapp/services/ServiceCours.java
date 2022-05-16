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
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author emnat
 */
public class ServiceCours {
   /* public static ServiceCours instance=null;
    
    private ConnectionRequest req;
    
    
    public static ServiceCours getInstance(){
    if(instance==null)
            instance=new ServiceCours();
    return instance;
            }
            
    public  ServiceCours(){
    req=new ConnectionRequest();
   
    
    
    }
    public void ajoutCours(Cours cours){
    String url=Statics.BASE_URL+"/ajouterCoursJSON?titre="+cours.getTitre()+"&nome="
            +cours.getNomE()+"&imc="+cours.getImc()+"&nb_heure="+cours.getNb_heure()+
            "&type="+cours.getType();
    req.setUrl(url);
    req.addResponseListener((e)->{
    String str= new String(req.getResponseData());
        System.out.println("data=="+str);});
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    
    }
    
    
    public ArrayList<Cours>afficherCours(){
    ArrayList<Cours> result= new ArrayList<>();
    String url=Statics.BASE_URL+"/listCoursJSON";
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               jsonp=new JSONParser();
                try{
                
                Map<String,Object>mapCours=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapCours.get("root");
                for(Map<String,Object> obj:listOfMaps){
                Cours ce=new Cours();
                int id=Integer.parseInt(obj.get("is_cours").toString());
                String titre=obj.get("titre").toString();
                 String type=obj.get("type").toString();
                  String imc=obj.get("imc").toString();
                   String nome=obj.get("nome").toString();
                    int nb_heure=Integer.parseInt(obj.get("nb_heure").toString());
                            ce.setId_cours(id);
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
     public ArrayList<Cours> Cours;
    
    public static ServiceCours instance=null;
    public boolean resultOK;
    private  final ConnectionRequest req;
       public static final String ACCOUNT_SID = "AC67f52567b92218cc3d58ce3232ad4fff";
  public static final String AUTH_TOKEN = "ce46bde5fe24e5e18e165c001d2050d9";

    private ServiceCours() {
         req = new ConnectionRequest();
    }

    public static ServiceCours getInstance() {
        if (instance == null) {
            instance = new ServiceCours();
        }
        return instance;
    }

    public boolean addCours(Cours t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics.BASE_URL + "ajouterCoursJSON?titre=" + t.getTitre()+ "&type=" + t.getType()+ "&imc=" + t.getImc()+ "&nome=" + t.getNomE()+ "&nb_heure=" + t.getNb_heure();
       
    
       req.setUrl(url);
       
       req.addArgument("titre", t.getTitre());
       req.addArgument("type", t.getType()+"");
       req.addArgument("imc", t.getImc()+"");
       req.addArgument("nome", t.getNomE()+"");
      

  
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+21620742823"),
        new PhoneNumber("+19379187956"), 
        "This is the ship that made the Kessel Run in fourteen parsecs?").create();

    System.out.println(message.getSid());
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

    public ArrayList<Cours> parseCours(String jsonText){
        try {
            Cours=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CoursListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)CoursListJson.get("root");
            for(Map<String,Object> obj : list){
                Cours p = new Cours();
             // int id=Integer.parseInt(obj.get("id_cours").toString());
               // System.out.println("id du cours  "+obj.get("id_cours"));
                String titre=obj.get("titre").toString();
//                String id=obj.get("id_cours").toString();
               // String nb_heure=obj.get("nb_heure").toString();
                 String type=obj.get("type").toString();
                  String imc=obj.get("imc").toString();
                   String nome=obj.get("nome").toString();
                   
                   // int nb_heure=;
                   
                         //   p.setId_cours(id);
                            p.setTitre(titre);
                           // System.out.println("ceci est le nb heure:  "+nb_heure);
                //            p.setNb_heure((int)Float.parseFloat(obj.get("nb_heure").toString()));
                            p.setImc(imc);
                            p.setNomE(nome);
                            p.setType(type);
                            

                    
                Cours.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Cours;
    }
    
    public ArrayList<Cours> getAllCours(){
     
       String url = Statics.BASE_URL+"listCoursJSON";
        req.setUrl(url);
       // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Cours = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Cours;
    }
    
        public boolean supprimerCours(String titre){
     String url = Statics.BASE_URL+"deleteCoursJSON?titre="+titre;
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
    
    public boolean modifierCours(Cours p) {
        System.out.println(p);
        System.out.println("********");
       String url = Statics.BASE_URL + "updateCoursJSON?titre=" + p.getTitre()+ "&type=" + p.getType()+ "&imc=" + p.getImc()+ "&nome=" + p.getNomE()+ "&nb_heure=" + p.getNb_heure();
       
    
       req.setUrl(url);
       
       
       req.addArgument("type", p.getType()+"");
       req.addArgument("imc", p.getImc()+"");
       req.addArgument("nome", p.getNomE()+"");
       req.addArgument("nb_heure", p.getNb_heure()+"");
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
