/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiayadi;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Event;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceEvent;

/**
 *
 * @author Yassine
 */
public class ModifierEventForm extends Form{
    
     public ModifierEventForm(Form res , Event r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
       
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier event");
        getContentPane().setScrollVisible(false);
        
     
        
        TextField nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
        TextField type = new TextField(r.getType() , "Type" , 20 , TextField.ANY);
          
    
         TextField description = new TextField(r.getDescription(), "Description" , 20 , TextField.ANY);
      
   
             nom.setUIID("TextFieldBlack");
                type.setUIID("TextFieldBlack");
                description.setUIID("TextFieldBlack");
    
        
    
        
    
        
        
        
       
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           
           r.setType(type.getText());
           r.setDescription(description.getText());
        
       
       
       //appel fonction modfier reclamation men service
           try {
                 if(ServiceEvent.getInstance().modifierReclamation(r)) { // if true
           new ListEventForm(res).show();
       }
           } catch (Exception e) {
               e.printStackTrace();
           }
     
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListEventForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
    
        
        addAll(nom,type,description,btnModifier,btnAnnuler);
        show();
        
        
    }
}