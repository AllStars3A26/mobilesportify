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
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.ServiceCours;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListCoursForm extends Form {
   Resources theme;
    public ListCoursForm(Form previous) {
       theme = UIManager.initFirstTheme("/theme_1");
        setTitle("List Cours");
        TextField tfPrenom= new TextField("", "recherche");
         Button rechercher = new Button ("rechercher");
         add(tfPrenom);
         add(rechercher);
        ArrayList<Cours> list =ServiceCours.getInstance().getAllCours();
        for(Cours soc : list) {
        setLayout(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.x());
        Container cnt2 = new Container(BoxLayout.y());
//        Container cnt3 = new Container(new FlowLayout(CENTER,CENTER));
//        ImageViewer img = new ImageViewer(theme.getImage("oeufs.jpg").scaled(150, 150));
//        cnt3.add(img);
        
        Label titre = new Label ("Nom de la séance : " + soc.getTitre());
        Label nome = new Label ("Nom de l'entraineur : " + soc.getNomE()); 
        Label imc = new Label("Valeur : " + soc.getImc());
        Label type = new Label ("Type : " + soc.getType());
        Label nbheure = new Label ("Nombre d'heure : " + soc.getNb_heure());
 
         //Label photo = new Label ("photo : " + soc.getPhoto()); 
       cnt2.addAll(titre,nome,imc,type,nbheure);
       Button modifier = new Button ("modifier");
       Button supprimer = new Button ("supprimer");
       cnt1.addAll(cnt2,modifier,supprimer);
        add(cnt1);
       supprimer.addActionListener(e->{
       Dialog dig = new Dialog("supression");
       if (dig.show("suppression","veuillez supprimer une Cours","annuler","oui"))
            dig.dispose();
       else {
       dig.dispose();
       if (ServiceCours.getInstance().supprimerCours(soc.getTitre())){
           System.out.println(soc.getTitre());
           new ListCoursForm(previous).show();}
       }
       }); 
       
       modifier.addActionListener(m-> {
       new UpdateCoursForm(previous, soc).show();
       });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
                
            
       
       }
//          rechercher.addActionListener(m-> {
//       new RechercherCoursForm(previous, tfPrenom.getText().toString()).show();
//       });
//       /* SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceCours.getInstance().getAllCours().toString());
//        add(sp); */
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
   }

}