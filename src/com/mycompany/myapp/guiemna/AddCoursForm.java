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
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.ServiceCours;

/**
 *
 * @author bhk
 */
 
 public class AddCoursForm extends Form{

    public AddCoursForm(Form previous) {
        
//         super("Upload Image", BoxLayout.y());
        setTitle("Ajouter une nouvelle Cours");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom du cours");
        TextField tfEmail= new TextField("", "Nom de l'entraineur ");
        TextField tfAdresse= new TextField("", "Valeur");
        TextField tfImg= new TextField("", "Type");
        //TextField tfMdp= new TextField("", "Nombre d'heure");
        
      
        Button btnValider = new Button("Ajouter Cours");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfImg.getText().length()==0) )
                    Dialog.show("Alert", "Il faut remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        //public Cours(int nb_heure, String type, String imc, String titre, String nomE)
                        Cours p = new Cours(0,tfImg.getText().toString(),tfAdresse.getText().toString(),tfNom.getText().toString(),tfEmail.getText().toString());
                        if( ServiceCours.getInstance().addCours(p))
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
        
        addAll(tfNom,tfEmail,tfAdresse,tfImg,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}