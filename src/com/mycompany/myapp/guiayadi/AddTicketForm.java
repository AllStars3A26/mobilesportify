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
import com.mycompany.myapp.entities.Ticket;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceTicket;

/**
 *
 * @author bhk
 */
public class AddTicketForm extends Form{

    public AddTicketForm (Form previous) {
        setTitle("Ajouter ticket");
        setLayout(BoxLayout.y());
        
       TextField tfNom = new TextField("","libelle");
        TextField tfType= new TextField("", "type");
        TextField tfprix= new TextField("", "prix");
      
                        tfNom.setUIID("TextFieldBlack");
        tfType.setUIID("TextFieldBlack");
            tfprix.setUIID("TextFieldBlack");


        Button btnValider = new Button("Ajouter un ticket");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfType.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        Ticket t = new Ticket(Integer.parseInt(tfprix.getText()), tfType.getText(),tfNom.getText());
                        if( ServiceTicket.getInstance().addTicket(t))
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
        
        addAll(tfNom,tfType,tfprix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}