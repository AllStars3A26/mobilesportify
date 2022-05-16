/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiskander;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.services.ServiceEquipe;

/**
 *
 * @author Yassine
 */
public class ModifierEquipeForm extends BaseForm{
    
     public ModifierEquipeForm(Form res , Equipe r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
       
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout reclamation equipe");
        getContentPane().setScrollVisible(false);
        
     
        
        TextField nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
        TextField type = new TextField(r.getType() , "Type" , 20 , TextField.ANY);
          
    
         TextField description = new TextField(r.getDescription(), "Description" , 20 , TextField.ANY);
         TextField tfnbr= new TextField(String.valueOf(r.getNbr()), "nombre de joueur " , 20 , TextField.ANY);
           TextField email = new TextField(r.getEmail(), "Email" , 20 , TextField.ANY);
          
                nom.setUIID("TextFieldBlack");
                type.setUIID("TextFieldBlack");
                description.setUIID("TextFieldBlack");
                tfnbr.setUIID("TextFieldBlack");
                email.setUIID("TextFieldBlack");
        
    
        


        
        
        
       
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           
           r.setType(type.getText());
           r.setDescription(description.getText());
           r.setNbr(Integer.valueOf(tfnbr.getText()));
           r.setEmail(email.getText());
       
       
       //appel fonction modfier reclamation men service
           try {
                 if(ServiceEquipe.getInstance().modifierEquipe(r)) { // if true
           new ListEquipeForm(res).show();
       }
           } catch (Exception e) {
               e.printStackTrace();
           }
     
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListEquipeForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
    
        
        addAll(nom,type,description,tfnbr,email,btnModifier,btnAnnuler);
        show();
        
        
    }
      private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
