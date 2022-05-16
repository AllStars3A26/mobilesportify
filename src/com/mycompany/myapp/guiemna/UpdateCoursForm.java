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
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.ServiceCours;
import static java.lang.Integer.parseInt;

/**
 *
 * @author mondh
 */
public class UpdateCoursForm extends Form {
    
    public UpdateCoursForm(Form previous, Cours soc) {
        setTitle("Modifier Cours");
        setLayout(BoxLayout.y());
        
       
        TextField tfImc= new TextField(soc.getImc(), "Valeur");
        TextField tfNome = new TextField(soc.getNomE(),"Nom de l'entraineur");
        TextField tfnbheure= new TextField(String.valueOf(soc.getNb_heure()), "Nombre d'heure");
        TextField tfType= new TextField(soc.getType(),"Type");
        Button btnValider = new Button("modifier Cours");
        
        
        btnValider.addActionListener(e -> {
         
         soc.setImc(tfImc.getText());
         soc.setNomE(tfNome.getText());
         soc.setNb_heure(parseInt((tfnbheure.getText())));
         soc.setType(tfType.getText());
         if (ServiceCours.getInstance().modifierCours(soc)){
           System.out.println(soc.getId_cours());
           new ListCoursForm(previous).show();}
       
        });
        
        addAll(tfImc,tfNome,tfnbheure,tfType,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
