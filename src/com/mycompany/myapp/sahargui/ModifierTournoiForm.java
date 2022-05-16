/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.sahargui;

/**
 *
 * @author Lenovo
 */
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

public class ModifierTournoiForm extends Form{
    
     public ModifierTournoiForm (Resources res,tournoi e)  {
                super("Tournoi", BoxLayout.y());
        
               TextField id = new TextField(String.valueOf(e.getId_tournoi()), "id", 20, TextArea.TEXT_CURSOR);
                TextField nom = new TextField(e.getNom_tournoi(), "nom", 20, TextArea.TEXT_CURSOR);
                TextField heure = new TextField(String.valueOf(e.getHeure()), "heure ", 20, TextArea.TEXT_CURSOR); 
                TextField nb_participants = new TextField(String.valueOf(e.getNb_participants()), "Nb_participants ", 20, TextArea.TEXT_CURSOR);
                TextField image = new TextField(e.getImage_tournoi(), "image", 20, TextArea.TEXT_CURSOR);
                TextField resultat = new TextField(String.valueOf(e.getResultat_tournoi()), "resultat ", 20, TextArea.TEXT_CURSOR);
                
                       //id.setUIID("TextFieldBlack");
                    nom.setUIID("TextFieldBlack");
                    heure.setUIID("TextFieldBlack");
                    nb_participants.setUIID("TextFieldBlack");
                    image.setUIID("TextFieldBlack");
                 resultat.setUIID("TextFieldBlack");
                  Button modif = new Button("modifier");
                  Button b8=new Button("liste tournoi"); 
                  setVisible(true);
                  
                  modif.addActionListener(l
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
                                   e.setId_tournoi(Integer.valueOf(id.getText()));
                               e.setNom_tournoi(nom.getText());
                               e.setDate_tournoi(e.getDate_tournoi());
                               e.setHeure(Integer.valueOf(heure.getText())); 
                               e.setNb_participants(Integer.valueOf(nb_participants.getText()));
                               e.setResultat_tournoi(Integer.valueOf(resultat.getText()));
                               e.setImage_tournoi(image.getText());
                               
                               
                               ServiceTournoi sp = new ServiceTournoi();
                                Form previous = null;
                               sp.Update(e, previous,res);
                                 Dialog.show("modifier", "modifier avec succés", "OK", null);

 ;
                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
             
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
               }
           });
            this.add(id).add(nom).add(nb_participants).add(heure).add(resultat).add(image).add(modif);
      
        
         
        
        add(b8);
         Form pre = null;
        b8.addActionListener(l->new TournoiForm(pre,res).show());
      
     }     
        
}
