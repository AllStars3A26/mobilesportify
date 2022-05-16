/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.SessionManager;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.sahargui.NewsfeedForm;

import com.mycompany.myapp.homefront.HomeForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceUtilisateur {
        
  //singleton 
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    
    //sign up 
        //Signup
    public void signup(TextField username,TextField password,TextField email,TextField confirmPassword,TextField full_name,TextField adresse,TextField num , Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"apiuser/signup?username="+username.getText().toString()+"&email="+email.getText().toString()+
                "&password="+password.getText().toString()+"&full_name="+full_name.getText().toString()+"&adresse="+adresse.getText().toString()+"&num="+num.getText().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(username.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ") && adresse.getText().equals(" ") && num.getText().equals(" ") && full_name.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
}
    
       //SignIn
    
    public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"apiuser/signin?username="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
 
                if(user.size()>0 ){
                    
                    new NewsfeedForm(rs).show();
                   
                }else{
                     Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
                }
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                               
                 
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
               
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("psudo").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setFullName(user.get("fullname").toString());
                SessionManager.setNum(user.get("num").toString());
                SessionManager.setPhoto("profile-pic.jpg");
                //photo 
                
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("image").toString());
                
                
               // if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                 //   new AjoutReclamationForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public String getPasswordByEmail(String email, Resources rs ) {
        
     
        String url = Statics.BASE_URL+"apiuser/getpasswordbyemail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
// edit user 
     public static void EditUser(String usr, String name , String email, String image,String nume){
         String url = Statics.BASE_URL+"apiuser/edituser?username="+usr+"&email="+email+
                "&full_name="+name+"&image="+image+"&num="+nume;
         MultipartRequest req = new MultipartRequest();
         
         
         req.setUrl(url);
         req.setPost(true);
         req.addArgument("id", String.valueOf(SessionManager.getId()));
         req.addArgument("psudo",usr);
         req.addArgument("fullname",name);
         req.addArgument("email", email);
         req.addArgument("image",image);
         req.addArgument("num", nume);
         System.out.println(email);
         req.addResponseListener((response)->{
             byte[] data = (byte[]) response.getMetaData();
             String s = new String (data);
             System.out.println(s);
            
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         

     }
        public ArrayList<Utilisateur> affichage(){
   ArrayList<Utilisateur> resault = new ArrayList<Utilisateur>();
    String url = Statics.BASE_URL+"apiuser/afficher";
    req.setUrl(url);
     System.out.println("affichage el lowel");
   req.addResponseListener(new ActionListener<NetworkEvent>(){
      @Override
      public void actionPerformed(NetworkEvent evt){
          JSONParser jsonp;
          jsonp=new JSONParser();
          
          try{
             Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader( new String(req.getResponseData()).toCharArray()));
             List<Map<String,Object>> listofmaps = (List<Map<String,Object>>) mapReclamations.get("root");
             for(Map<String,Object> obj : listofmaps){

              Utilisateur re = new Utilisateur();
                //dima 1d fi codename one fioat Southouha
                
                String username=obj.get("psudo").toString();
                String fullName=obj.get("fullname").toString();
                String numero = obj.get("num").toString();
                String email = obj.get("email").toString();
                String adresse = obj.get("adresse").toString();
                   
                   re.setAdresse(adresse);
                   re.setNom(fullName);
                   re.setEmail(email);
                   re.setNum(numero);
                   re.setUsername(username);
                  resault.add(re);
                
                
                        }
          }catch(Exception ex){
              ex.printStackTrace();
          }
      }
   });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resault;
           }
}
