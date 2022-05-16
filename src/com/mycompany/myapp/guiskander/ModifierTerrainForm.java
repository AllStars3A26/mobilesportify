/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiskander;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.services.ServiceTerrain;

/**
 *
 * @author Ramez
 */
public class ModifierTerrainForm extends Form{
    
    String[] PromoTypes = {"Football", "Basket"};
    
    public ModifierTerrainForm(Form res, Terrain p) {
        super("Modifer terrain",BoxLayout.y()); 
    
        Toolbar tb = new Toolbar(true);
       
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier terrain");
        getContentPane().setScrollVisible(false);
        
        ///////////////////
       
        
        TextField Fnom = new TextField(p.getNom(), "Donner nom du terrain", 99, TextArea.NUMERIC);
        TextField Ftypee = new TextField(p.getType(), "Donner type ", 20, TextArea.ANY);
        TextField Fdescription = new TextField(p.getDescription(), "Donner description", 20, TextArea.ANY);
        TextField Fadresse = new TextField(p.getAdresse(), "Donner adresse", 99, TextArea.NUMERIC);
        TextField Fdispo = new TextField(String.valueOf(p.getDisponibilite()), "Donner disponi", 20, TextArea.ANY);
        /*Form picker = new Form();
        AutoCompleteTextField Ftype = new AutoCompleteTextField(PromoTypes);
        
        Button down = new Button();
        FontImage.setMaterialIcon(down, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
        picker.add(
            BorderLayout.center(Ftype).
            add(BorderLayout.EAST, down));
            down.addActionListener(e -> Ftype.showPopup());*/
        /////////////////////
 
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
        btnModifier.addPointerPressedListener(l-> {
           p.setType(Ftypee.getText());
           p.setNom(Fnom.getText());
           p.setDisponibilite(Integer.parseInt(Fdispo.getText()));
           p.setDescription(Fdescription.getText());
           p.setAdresse(Fadresse.getText());
           
           try{
               if(ServiceTerrain.getInstance().updateTerrain(p)){
                   new ListTerrainForm(res).show();
               }
           }
           catch(Exception e){
               e.printStackTrace();
           }
       });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
           new ListTerrainForm(res).show();
       });
        
        addAll(Fnom, Ftypee,Fdescription,Fadresse,Fdispo, btnModifier, btnAnnuler);
        show();
    }
}
