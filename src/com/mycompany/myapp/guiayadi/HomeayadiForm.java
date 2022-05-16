/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiayadi;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author Ramez
 */
public class HomeayadiForm extends Form{
    
    Form current;
    
    public HomeayadiForm(Form previous){
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choisir une option"));
        Button btnTicket= new Button("Ajouter ticket");
        Button btnTicketr = new Button("Afficher ticket");
        Button btnAddCollaborateur = new Button("Ajouter un évènement");
        Button btnListCollaborateurs = new Button("Liste des évènements");
        
     btnTicket.addActionListener(e-> new AddTicketForm(current).show());
      btnTicketr.addActionListener(e-> new ListTicketForm(current).show());
        btnAddCollaborateur.addActionListener(e-> new AddEventForm(current).show());
        btnListCollaborateurs.addActionListener(e-> new ListEventForm(current).show());
        
        addAll( btnTicket,btnTicketr,btnAddCollaborateur,btnListCollaborateurs);
         getToolbar().addMaterialCommandToLeftBar("here", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}



        
    