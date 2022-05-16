package com.mycompany.myapp.guiskander;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Equipe;
import com.mycompany.myapp.services.ServiceEquipe;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListEquipeForm extends Form {
    public ListEquipeForm(Form previous) {
        setTitle("Liste des évènement");

        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceCollaborateur.getInstance().getAllCollaborateurs().toString);
        
         ArrayList<Equipe> list;
        list = new ArrayList<>();
        list = ServiceEquipe.getInstance().getAllEquipes();
         for ( Equipe ev : list) {
             
             
             
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("typer: " + "  " + ev.getType());
                SpanLabel sp7 = new SpanLabel("description: " + "  " + ev.getDescription());
                SpanLabel sp8 = new SpanLabel("nombre de joueur : " + "  " + ev.getNbr());
                SpanLabel sp10 = new SpanLabel("email: " + "  " + ev.getEmail());
       
             
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
                if(ServiceEquipe.getInstance().deleteEquipe(ev.getId())) {
                  
                    new ListEquipeForm(previous).show();
                }
           
        });
        
        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierEquipeForm(previous,ev).show();
        });
        
        
      
          addAll(spl,spl2,sp7,sp8,sp10,lSupprimer,lModifier);
        
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}