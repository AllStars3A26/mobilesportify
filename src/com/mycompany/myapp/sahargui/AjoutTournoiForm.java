/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.sahargui;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.tournoi;
import java.io.IOException;
import java.util.Date;
import com.mycompany.myapp.services.ServiceTournoi;

/**
 *
 * @author Lenovo
 */
public class AjoutTournoiForm extends Form{
      public AjoutTournoiForm(Resources res)  {
     super("Tournoi", BoxLayout.y());
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new NewsfeedForm(res).show());
      add(skip);
           
                TextField nom = new TextField("", "nom", 20, TextArea.TEXT_CURSOR);
                TextField heure = new TextField("", "heure ", 20, TextArea.TEXT_CURSOR); 
                TextField nb_participants = new TextField("", "Nb_participants ", 20, TextArea.TEXT_CURSOR);
                TextField image = new TextField("", "image", 20, TextArea.TEXT_CURSOR);
                TextField resultat = new TextField("", "resultat ", 20, TextArea.TEXT_CURSOR);
                
                       nom.setUIID("TextFieldBlack");
        heure.setUIID("TextFieldBlack");
           nb_participants.setUIID("TextFieldBlack");
            image.setUIID("TextFieldBlack");
            resultat.setUIID("TextFieldBlack");
                 
              
                 
         
                 /*Label lab = new Label("0 DT");
                 affichage.addActionListener(www->{
                     
                    lab.setText(String.valueOf( Integer.valueOf(affichage.getText())*  2000           )+" DT");
                 });
                 
                  Button upload = new Button("Upload Image");
                  
                         Validator val_firstname = new Validator();
                            val_firstname.addConstraint(Prenom, new LengthConstraint(8));
                            String text_saisir_des_caracteres = "^[0-9]+$";
                            val_firstname.addConstraint(Prenom, new RegexConstraint(text_saisir_des_caracteres, ""));
                            // val lastname   
                            Validator val_lastname = new Validator();
                            val_lastname.addConstraint(Nom, new LengthConstraint(8));
                            val_lastname.addConstraint(Nom, new RegexConstraint(text_saisir_des_caracteres, ""));
                  
                  
           String text_mail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                            
                             // val mail   
                            Validator val_mail = new Validator();
                            val_mail.addConstraint(mail, new LengthConstraint(8));
                            val_mail.addConstraint(mail, new RegexConstraint(text_mail, ""));*/
        Button save = new Button("Ajouter");
       Button b7=new Button("liste tournoi"); 
         setVisible(true);
         
       //btnsupp.addActionListener(w -> new NewsfeedForm(res).show())
               
      /*  upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/image/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });   */
           save.addActionListener(l
                                -> {

                          
                            if (nom.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide du nom   ", "OK", null);

                          

                            } 
                            else if (nb_participants.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nb_participants ", "OK", null);

                            } 
                              else if (image.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide d'image  ", "OK", null);

                              }
                              else if (heure.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide d'heure  ", "OK", null);

                              }
                              else if (resultat.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de resultat  ", "OK", null);

                             
                              }
                            else {
                                          
                                          
                               tournoi e = new tournoi();
                               e.setNom_tournoi(nom.getText());
                               e.setDate_tournoi(e.getDate_tournoi());
                               e.setHeure(Integer.valueOf(heure.getText())); 
                               e.setNb_participants(Integer.valueOf(nb_participants.getText()));
                               e.setResultat_tournoi(Integer.valueOf(resultat.getText()));
                               e.setImage_tournoi(image.getText());
                               
                               ServiceTournoi sp = new ServiceTournoi();
                                Form previous = null;
                               sp.Add(e, previous,res);
                                 Dialog.show("Ajout", "Ajout avec succés", "OK", null);
                                //  String url = "http://localhost/pdf/ex.php";
/*Button btn = new Button("hee");
this.add(btn);

btn.addActionListener(ll->{

});
*/
 ;
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
               /* String data = "Nom : " + Nom.getText() + "<br>  Prenom : " + DateD.getText() + " <br>  mail :" + mail.getText() + " <br> domaine : " + domaine.getText() + " <br> lien : " + lien.getText()+ " <br> Prix : " + String.valueOf( Integer.valueOf(affichage.getText())*  2000           )+" DT"+"<br> Merci pour votre confiance &#128525;";

                cnreq.addArgument("data", data);
                cnreq.setUrl(url);
                cnreq.addResponseListener(evx
                        -> {
                    String valeur = new String(cnreq.getResponseData());
                     Dialog.show("PDF", "PDF", "OK", null);
                

                }
                );*/
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                                                    
                            }
           });
        
         
                  
        
        
        
        
          this.add(nom).add(heure).add(resultat).add(image).add(nb_participants).add(save);
      
        
         
        
        add(b7);
         Form pre = null;
 b7.addActionListener(e -> new TournoiForm(pre,res).show());                 
     }
}
