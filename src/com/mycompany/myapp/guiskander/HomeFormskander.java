/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiskander;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.BaseForm;

/**
 *
 * @author Ramez
 */
public class HomeFormskander extends BaseForm{
    
    Form current;
    
    public HomeFormskander(Form previous){
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        Button btnAddPromo = new Button("Ajouter Terrain");
        Button btnListPromo = new Button("Afficher Terrain");
        Button btnAddCollaborateur = new Button("Ajouter une equipe ");
        Button btnListCollaborateurs = new Button("Afficher equipes");
        
        btnAddPromo.addActionListener(e-> new AddTerrainForm(current).show());
        btnListPromo.addActionListener(e-> new ListTerrainForm(current).show());
        btnAddCollaborateur.addActionListener(e-> new AddEquipeForm(current).show());
        btnListCollaborateurs.addActionListener(e-> new ListEquipeForm(current).show());
        
        addAll(btnAddPromo, btnAddCollaborateur,btnListPromo ,btnListCollaborateurs);
          getToolbar().addMaterialCommandToLeftBar("here", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }      


    
}



        
    