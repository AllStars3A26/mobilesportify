package com.mycompany.myapp.guiayadi;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;
import com.mycompany.myapp.entities.Event;

/**
 *
 * @author bhk
 */
public class ListEventForm extends Form {
    public ListEventForm(Form previous) {
        setTitle("Liste des évènement");

        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceCollaborateur.getInstance().getAllCollaborateurs().toString);
        
         ArrayList<Event> list;
        list = new ArrayList<>();
        list = ServiceEvent.getInstance().getAllEvents();
         for ( Event ev : list) {
             
             
             
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("typer: " + "  " + ev.getType());
                SpanLabel sp7 = new SpanLabel("description: " + "  " + ev.getDescription());

       
             
 Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceEvent.getInstance().deleteReclamation(ev.getId())) {
                  
                    new ListEventForm(previous).show();
                }
           
        });
        
        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("modifier");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierEventForm(previous,ev).show();
        });
        
        
      
          addAll(spl,spl2,sp7,lSupprimer,lModifier);
        
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}