/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guisnowyy;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author Ramez
 */
public class HomeProduitForm extends Form{
    
    Form current;
    
    public HomeProduitForm(Form previous){
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        //Button btnAddPromo = new Button("");
        //Button btnListPromo = new Button("");
        Button btnAddCollaborateur = new Button("Ajouter Produit");
        Button btnListCollaborateurs = new Button("Afficher les Produits");
        
        //btnAddPromo.addActionListener(e-> new AddPromotionForm(current).show());
        //btnListPromo.addActionListener(e-> new ListPromotionForm(current).show());
        btnAddCollaborateur.addActionListener(e-> new AddPoduitForm(current).show());
        btnListCollaborateurs.addActionListener(e-> new ListProduitForm(current).show());
        
        addAll( btnAddCollaborateur,btnListCollaborateurs);
        getToolbar().addMaterialCommandToLeftBar("here", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}



        
    