/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package com.mycompany.myapp.guiemna;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.services.ServiceSeance;
import java.util.Date;

/**
 *
 * @author bhk
 */
 
 public class AddSeanceForm extends Form{

    public AddSeanceForm(Form previous) {
        
//         super("Upload Image", BoxLayout.y());
        setTitle("Ajouter une nouvelle Seance");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom de la Seance");
        TextField tfEmail= new TextField("", "Nom de l'entraineur ");
        TextField tfAdresse= new TextField("", "heure de la séance");
        Date today = new Date();
        //TextField tfMdp= new TextField("", "Nombre d'heure");
        
      
        Button btnValider = new Button("Ajouter Seance");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfAdresse.getText().length()==0) )
                    Dialog.show("Alert", "Il faut remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        //Seance(Date date_seance, String heure_seance, String nom_T, String nom_E, String nb_participants)
                        Seance p = new Seance(today,tfAdresse.getText().toString(),tfNom.getText().toString(),tfEmail.getText().toString(),0);
                        if( ServiceSeance.getInstance().addSeance(p))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                         Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                                          }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfEmail,tfAdresse,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}