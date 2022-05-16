/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.guiayadi;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.services.ServiceEvent;

/**
 *
 * @author bhk
 */
public class AddEventForm extends Form{

    public AddEventForm (Form previous) {
        setTitle("Ajouter un nouveau evenement");
        setLayout(BoxLayout.y());
        
       TextField tfNom = new TextField("","nom");
        TextField tfType= new TextField("", "type");
        TextField tfDesc= new TextField("", "decription");
      
                tfNom.setUIID("TextFieldBlack");
        tfType.setUIID("TextFieldBlack");
            tfDesc.setUIID("TextFieldBlack");
       



        Button btnValider = new Button("Ajouter un evenement");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfType.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        Event t = new Event(tfNom.getText(), tfType.getText(),tfDesc.getText());
                        if( ServiceEvent.getInstance().addEvent(t))
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
        
        addAll(tfNom,tfType,tfDesc,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}