/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.guiemna;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author bhk
 */
public class HomeEmnaForm extends Form{
Form current;
    public HomeEmnaForm(Resources rs, Form previous) {
        current=this; //Back 
        setTitle("SPORTIFY");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisissez une option"));
     
        Button btnAjouterCours = new Button("Ajouter un cours");
      
        
        Button btnAfficherCours = new Button("Afficher les cours");
        Button btnAfficherSeance = new Button("Afficher les Seances");
        Button btnAjouterSeance = new Button("Ajouter une séance");
        Button Map = new Button("map");
       
       
     
        btnAjouterCours.addActionListener(e-> new AddCoursForm(current).show());
          btnAfficherCours.addActionListener(e-> new ListCoursForm(current).show());
          btnAjouterSeance.addActionListener(e-> new AddSeanceForm(current).show());
          btnAfficherSeance.addActionListener(e-> new ListSeanceForm(current).show());
        
        addAll(btnAjouterCours,btnAfficherCours,btnAjouterSeance,btnAfficherSeance);
        getToolbar().addMaterialCommandToLeftBar("here", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    }
    
    
}
