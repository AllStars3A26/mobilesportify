/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.guisnowyy;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;

/**
 *
 * @author bhk
 */
public class AddPoduitForm extends Form{

    public AddPoduitForm (Form previous) {
        setTitle("Ajouter un Produit");
        setLayout(BoxLayout.y());
        
       TextField tfNom = new TextField("","nom"); 
        TextField tfDesc= new TextField("", "decription");
        TextField tfprix= new TextField("", "prix");
        TextField tfquan= new TextField("", "quantité");
      
        


        Button btnValider = new Button("Ajouter un produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                       Produit t =new Produit(Integer.parseInt(tfprix.getText()),Integer.parseInt(tfquan.getText()), tfNom.getText(), tfDesc.getText());
                       // Event t = new Event(Integer.parseInt(tfLieuid.getText()),tfNom.getText(), tfType.getText(),tfDesc.getText(),tfEmail.getText());
                        if( ServiceProduit.getInstance().addProduit(t))
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
        
        addAll(tfNom,tfDesc,tfprix,tfquan,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}