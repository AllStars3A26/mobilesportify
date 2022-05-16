/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.guiemna;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.services.ServiceSeance;
import static java.lang.Integer.parseInt;

/**
 *
 * @author mondh
 */
public class UpdateSeanceForm extends Form {
    
    public UpdateSeanceForm(Form previous, Seance soc) {
        setTitle("Modifier Seance");
        setLayout(BoxLayout.y());
        
       
       // TextField tfnomt= new TextField(soc.getNom_T(), "Valeur");
        TextField tfNome = new TextField(soc.getNom_E(),"Nom de l'entraineur");
        TextField tfheure= new TextField(soc.getHeure_seance(), "heure de la séance");
        
        Button btnValider = new Button("modifier Seance");
        
        
        btnValider.addActionListener(e -> {
         
        // soc.setNom_T(tfnomt.getText());
         soc.setNom_E(tfNome.getText());
         
         soc.setHeure_seance(tfheure.getText());
         if (ServiceSeance.getInstance().modifierSeance(soc)){
           System.out.println(soc.getId_seance());
           new ListSeanceForm(previous).show();}
       
        });
        
        addAll(tfNome,tfheure,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
