/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.guiskander;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.gui.BaseForm;

import com.mycompany.myapp.services.ServiceEquipe;

/**
 *
 * @author bhk
 */
public class AddEquipeForm extends BaseForm{

    public AddEquipeForm (Form previous) {
        setTitle("Ajouter une equipe");
        setLayout(BoxLayout.y());
        
       TextField tfNom = new TextField("","nom");
       
        TextField tfType= new TextField("", "type");
        TextField tfDesc= new TextField("", "decription");
        TextField tfNbr= new TextField("", "nombre de joueur");
        TextField tfEmail= new TextField("", "email");
        tfNom.setUIID("TextFieldBlack");
        tfType.setUIID("TextFieldBlack");
            tfDesc.setUIID("TextFieldBlack");
            tfNbr.setUIID("TextFieldBlack");
            tfEmail.setUIID("TextFieldBlack");




        Button btnValider = new Button("Ajouter une equipe");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfType.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        Equipe t = new Equipe(Integer.parseInt(tfNbr.getText()),tfNom.getText(), tfType.getText(),tfDesc.getText(),tfEmail.getText());
                        if( ServiceEquipe.getInstance().addEquipe(t))
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
        
        addAll(tfNom,tfType,tfDesc,tfNbr,tfEmail,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}