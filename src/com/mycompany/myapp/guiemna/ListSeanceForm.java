/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.guiemna;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.services.ServiceSeance;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListSeanceForm extends Form {
   Resources theme;
    public ListSeanceForm(Form previous) {
       theme = UIManager.initFirstTheme("/theme_1");
        setTitle("List Seance");
        TextField tfPrenom= new TextField("", "recherche");
         Button rechercher = new Button ("rechercher");
         add(tfPrenom);
         add(rechercher);
        ArrayList<Seance> list =ServiceSeance.getInstance().getAllSeance();
        for(Seance soc : list) {
        setLayout(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.x());
        Container cnt2 = new Container(BoxLayout.y());
//        Container cnt3 = new Container(new FlowLayout(CENTER,CENTER));
//        ImageViewer img = new ImageViewer(theme.getImage("oeufs.jpg").scaled(150, 150));
//        cnt3.add(img);
        
        Label nomt = new Label ("Nom de la séance : " + soc.getNom_T());
        Label nome = new Label ("Nom de l'entraineur : " + soc.getNom_E()); 
        Label heure = new Label("heure de la séance  : " + soc.getHeure_seance());
     
 
         //Label photo = new Label ("photo : " + soc.getPhoto()); 
       cnt2.addAll(nomt,nome,heure);
       Button modifier = new Button ("modifier");
       Button supprimer = new Button ("supprimer");
       cnt1.addAll(cnt2,modifier,supprimer);
        add(cnt1);
       supprimer.addActionListener(e->{
       Dialog dig = new Dialog("supression");
       if (dig.show("suppression","veuillez supprimer une Seance","annuler","oui"))
            dig.dispose();
       else {
       dig.dispose();
       if (ServiceSeance.getInstance().supprimerSeance(soc.getNom_T())){
           System.out.println(soc.getNom_T());
           new ListSeanceForm(previous).show();}
       }
       }); 
       
       modifier.addActionListener(m-> {
       new UpdateSeanceForm(previous, soc).show();
       });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
                
            
       
       }
//          rechercher.addActionListener(m-> {
//       new RechercherSeanceForm(previous, tfPrenom.getText().toString()).show();
//       });
//       /* SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceSeance.getInstance().getAllSeance().toString());
//        add(sp); */
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
   }

}