/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.guiskander;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.Terrain;
import com.mycompany.myapp.services.ServiceTerrain;
import java.util.ArrayList;

/**
 *
 * @author Ramez
 */
public class ListTerrainForm extends Form{

    
    public ListTerrainForm(Form previous ) {
        setTitle("Afficher les terrain");
        setLayout(BoxLayout.y());
        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceUser.getInstance().getAllUsers().toString);
        
        Container searchContainer=new Container(BoxLayout.x());
        /*TextField queryTextField = new TextField("", "Search term", 10, TextField.ANY) ;
        Button findPromotionButton = new Button("Search");
        
        Validator val = new Validator();
                val.addConstraint(queryTextField, new LengthConstraint(3));
                
              findPromotionButton.addActionListener(search->{
                  if (!val.isValid()) {
                ToastBar.showErrorMessage("Query has to be minimum 3 characters");
                }else{
                  new InfiniteProgress().showInifiniteBlocking();
                  new SearchResultsForm(queryTextField.getText(), previous).show();
                  }
              });
        searchContainer.addAll(queryTextField, findPromotionButton);
        add(searchContainer);*/
        
        
        ArrayList<Terrain> list;
        //list = new ArrayList<>();
        list = ServiceTerrain.getInstance().getAllTerrain();
        
         for ( Terrain pr : list) { 
                SpanLabel spl = new SpanLabel("nom: " + "  " + pr.getNom());
                SpanLabel sp2 = new SpanLabel("Type: " + "  " + pr.getType());
                SpanLabel sp3 = new SpanLabel("Description: " + "  " + pr.getDescription());
                SpanLabel sp4 = new SpanLabel("adresse: " + "  " + pr.getAdresse());
                SpanLabel sp5 = new SpanLabel("disponibilite: " + "  " + pr.getDisponibilite());
              
         
      
                Label lSupprimer = new Label(" ");
                lSupprimer.setUIID("NewsTopLine");
                Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
                supprmierStyle.setFgColor(0xf21f1f);
        
                FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
                lSupprimer.setIcon(suprrimerImage);
                lSupprimer.setTextPosition(RIGHT);
          
                
                lSupprimer.addPointerPressedListener(l-> {
                    
                    Dialog dig = new Dialog("Suppression");
                    if(dig.show("Suppression","Vous voulez supprimer ce promotion ?","Annuler","Oui")) {
                        dig.dispose();
                    }
                    /*else {
                        dig.dispose();
                    }*/
                    
                    if(ServiceTerrain.getInstance().deleteTerrain(pr.getId())){
                        new ListTerrainForm(previous).show();
                    }
                });
                
                Label lModifier = new Label(" ");
                lModifier.setUIID("NewsTopLine");
                Style modifierStyle = new Style(lModifier.getUnselectedStyle());
                modifierStyle.setFgColor(0xf7ad02);
        
                FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
                lModifier.setIcon(mFontImage);
                lModifier.setTextPosition(LEFT);
                
                lModifier.addPointerPressedListener(l -> {
                new ModifierTerrainForm(previous,pr).show();
        });
                
                
                
                
                
               addAll(spl,sp2,sp3,sp4,sp5,lSupprimer,lModifier);
                
                
        
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.show());
        getToolbar().setUIID("TextFieldBlack");
    }
    
    
}
