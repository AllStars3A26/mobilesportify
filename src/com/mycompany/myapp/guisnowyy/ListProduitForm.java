package com.mycompany.myapp.guisnowyy;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class ListProduitForm extends Form {
    public ListProduitForm(Form previous) {
        setTitle("Liste des Produit");

        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceCollaborateur.getInstance().getAllCollaborateurs().toString);
        
         ArrayList<Produit> list;
        list = new ArrayList<>();
        list = ServiceProduit.getInstance().getAllProduit();
         for ( Produit ev : list) {
             
             
             
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
              
                SpanLabel sp7 = new SpanLabel("description: " + "  " + ev.getDesc());
                SpanLabel sp8 = new SpanLabel("Prix: " + "  " + ev.getPrix());
                SpanLabel sp10 = new SpanLabel("quantité: " + "  " + ev.getQuan());
       
             
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
                if(ServiceProduit.getInstance().deleteProduit(ev.getId())) {
                  
                    new ListProduitForm(previous).show();
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
            new ModifierProduitForm(previous,ev).show();
        });
        
        
      
          addAll(spl,sp7,sp8,sp10,lSupprimer,lModifier);
        
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}