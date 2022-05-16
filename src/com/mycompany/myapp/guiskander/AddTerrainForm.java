/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiskander;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.services.ServiceTerrain;

/**
 *
 * @author Ramez
 */
public class AddTerrainForm extends Form{

    String[] PromoTypes = {"Football", "Basket"};
    
    public AddTerrainForm(Form previous) {
        setTitle("Ajouter un terrain");
        setLayout(BoxLayout.y());
        
        //TextField Ftype = new TextField("", "Type");
        Form picker = new Form();
        AutoCompleteTextField Ftype = new AutoCompleteTextField(PromoTypes);
        
        Button down = new Button();
        FontImage.setMaterialIcon(down, FontImage.MATERIAL_KEYBOARD_ARROW_DOWN);
        picker.add(
            BorderLayout.center(Ftype).
            add(BorderLayout.EAST, down));
            down.addActionListener(e -> Ftype.showPopup());
            
            
        TextField Fnom = new TextField("", "Donner nom du terrain", 99, TextArea.NUMERIC);
        //TextField Ftypee = new TextField("", "Donner type ");
        TextField Fdescription = new TextField("", "Donner description", 20, TextArea.ANY);
        TextField Fadresse = new TextField("", "Donner adresse", 99, TextArea.NUMERIC);
        TextField Fdispo = new TextField("", "Donner disponi");
           Fnom.setUIID("TextFieldBlack");
        //Ftypee.setUIID("TextFieldBlack");
            Fdescription.setUIID("TextFieldBlack");
            Fadresse.setUIID("TextFieldBlack");
            Fdispo.setUIID("TextFieldBlack");
        Button btnValider = new Button("Ajouter terrain");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                if( /*(Ftype.getText().length() == 0) ||*/ (Fdescription.getText().length() == 0) || (Ftype.getText().length() == 0) ){
                    Dialog.show("Alert", "Veuillez remplir les cases vides", new Command("OK"));
                }
                else{
                        try {
                                Terrain p = new Terrain(Fnom.getText(),Ftype.getText(), Fdescription.getText(), Fadresse.getText(),Integer.parseInt(Fdispo.getText()));
                                if(ServiceTerrain.getInstance().addTerrain(p)) {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
                
            }
        });
        
        addAll( Fnom,/*Ftype*/picker,Fadresse ,Fdescription,Fdispo,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.show());
    }
    
}
