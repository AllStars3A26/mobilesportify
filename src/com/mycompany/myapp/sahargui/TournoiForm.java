/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.sahargui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;

import com.mycompany.myapp.entities.tournoi;

import com.mycompany.myapp.services.ServiceTournoi;
/**
 *
 * @author Lenovo
 */
public class TournoiForm extends Form{
    Resources theme = UIManager.initFirstTheme("/theme");

    Form Current;
    
    public TournoiForm(Form previous,Resources res)
    {
        
           super("Tournoi",BoxLayout.y());
           Current=this;
             this.add(new InfiniteProgress());
             
       
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Button skip = new Button("back");
                 skip.setUIID("back");
        skip.addActionListener(e -> new AjoutTournoiForm(res).show());
      add(skip);
        Button b7=new Button("REFRESH");
	b7.setUIID("refresh"); 
        b7.addActionListener(l->new TournoiForm(Current,res).show());
        add(b7);     
             for (tournoi c : new ServiceTournoi().getAllTournois()) {
      
            this.add(addItem_Publicite(c,res));

        }
             
              this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(true);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                  
                    /*boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;*/
                   mb.setHidden(false);
                    mb.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 7);
        
        
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
            new AjoutTournoiForm(res).show();
        });
                   this.getToolbar().addCommandToOverflowMenu("Ajouter", null, ev -> {
         new AjoutTournoiForm(res).show();
        });
                     /*this.getToolbar().addCommandToOverflowMenu("Stat", null, ev -> {
     new StatForm().createPieChartForm("Pubs", new ServicePublicite().findAllStat());
        });*/
                      
    }

   
   
  
    
     public MultiButton addItem_Publicite(tournoi c,Resources res) {
  
 MultiButton m = new MultiButton();

     
     m.setText(c.getNom_tournoi());
      
       m.setText(String.valueOf(c.getHeure()));
       m.setText(String.valueOf(c.getResultat_tournoi()));
       m.setText(String.valueOf(c.getNb_participants()));
       m.setText(c.getImage_tournoi());
       m.setEmblem(theme.getImage("arrow.png"));
         
      m.setVisible(true);
        Button b5=new Button("supprimer tournoi");
	setVisible(true); 
        Button b6=new Button("modifier tournoi");
	setVisible(true);  
        
         add(b5);
         add(b6);
      b6.addActionListener(l->new ModifierTournoiForm(res,c).show());
         
        
       
//Click delete icon
b5.addPointerPressedListener(l -> {
Dialog dig = new Dialog("Suprression");
if(dig.show("Suppression", "Vous voulez supprimezr ce tournoi ?","Annuler","Cui")) {
dig.dispose ();
}
else {
dig.dispose();
if (ServiceTournoi.getInstance().delete(c.getId_tournoi())) {     
new TournoiForm(Current,res); 
}
}
});



Button skip = new Button("back");
        m.addActionListener(e -> {
            Form f2 = new Form("Detail",BoxLayout.y());
            f2.add("nom_tournoi : "+c.getNom_tournoi()).add("heure : "+c.getHeure()).add("resultat : "+c.getResultat_tournoi()).add("Nb participants : "+c.getNb_participants()).add("image : "+c.getImage_tournoi()).add(skip);   
       
 f2.show();

        });
   
  skip.addActionListener(e -> new AjoutTournoiForm(res).show());
     
        return m;
}
}
