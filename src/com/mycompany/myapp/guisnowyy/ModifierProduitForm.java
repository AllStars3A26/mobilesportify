/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guisnowyy;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;

/**
 *
 * @author Yassine
 */
public class ModifierProduitForm extends Form{
    
     public ModifierProduitForm(Form res , Produit r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
       
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
     
        
        TextField nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
          
    
         TextField description = new TextField(r.getDesc(), "Description" , 20 , TextField.ANY);
         TextField tfprix= new TextField(String.valueOf(r.getPrix()), "prix" ,20 , TextField.ANY);
           TextField tfquan = new TextField(String.valueOf(r.getQuan()), "quantité" , 20 , TextField.ANY);
          
    
        
    
        
    
        
        
        
       
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(nom.getText());
           
          
           r.setDesc(description.getText());
           r.setPrix(Integer.parseInt(tfprix.getText()));
        r.setQuan(Integer.parseInt(tfquan.getText()));
       
       //appel fonction modfier reclamation men service
           try {
                 if(ServiceProduit.getInstance().modifierProduit(r)) { // if true
           new ListProduitForm(res).show();
       }
           } catch (Exception e) {
               e.printStackTrace();
           }
     
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListProduitForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
    
        
        addAll(nom,description,tfprix,tfquan,btnModifier,btnAnnuler);
        show();
        
        
    }
}
