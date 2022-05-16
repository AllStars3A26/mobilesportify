/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Codename One/guibuilderForm.java to edit this template
 */
package com.mycompany.myapp.guiayadi;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;
//import models.Coach;

/**
 * GUI builder created Form
 *
 * @author Lord Ramez
 */
public class SearchResultsForm extends Form {

    private String urlSearchCoaches = "http://127.0.0.1:8000/promotion/searchJson";
            private ConnectionRequest cnxSearchCoaches;
            //private String urlDeleteCoach = "http://127.0.0.1:8000/api/coach/delete";
            private ConnectionRequest cnxDeleteCoach;
   
            
    public SearchResultsForm(String query, Form previous) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        setTitle("Search Results");
        setScrollableY(false);
         Toolbar tb = getToolbar();
             
             Style s = UIManager.getInstance().getComponentStyle("Title");
            FontImage backIcon=FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,s);
             getToolbar().addCommandToLeftBar("",backIcon,e->{
//                    new ListPromotionForm(previous).show();
                });
        Container coachesContainer=new Container( BoxLayout.y());
        coachesContainer.setScrollableX(true);
        
        Container searchContainer=new Container(BoxLayout.x());
             TextField queryTextField = new TextField("", "Search term", 10, TextField.ANY) ;
              Button findCoachButton = new Button("Search");
              
               Validator val = new Validator();
                val.addConstraint(queryTextField, new LengthConstraint(3));
                
              findCoachButton.addActionListener(search->{
                  if (!val.isValid()) {
                ToastBar.showErrorMessage("Query has to be minimum 3 characters");
                }else{
                  new InfiniteProgress().showInifiniteBlocking();
                  new SearchResultsForm(queryTextField.getText(), previous).show();
                  }
              });
            
              searchContainer.add(queryTextField);
              searchContainer.add(findCoachButton);
              coachesContainer.add(searchContainer);
              
            cnxSearchCoaches = new ConnectionRequest(urlSearchCoaches);
            cnxSearchCoaches.setPost(false);
            cnxSearchCoaches.addArgument("query", query);
            
            cnxSearchCoaches.addResponseListener(ev->{
                
              try {
                Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(cnxSearchCoaches.getResponseData()), "UTF-8"));
              
                
               java.util.List<Map<String, Object>> response = (java.util.List<Map<String, Object>>)result.get("promotions"); 
              
                for(Map<String, Object> pr : response) {
             
                
                SpanLabel spl = new SpanLabel("Type: " + "  " + pr.get("Type").toString()   );
                SpanLabel sp2 = new SpanLabel("Title: " + "  " + pr.get("title").toString());
                SpanLabel sp3 = new SpanLabel("Description: " + "  " + pr.get("description").toString());
                SpanLabel sp4 = new SpanLabel("Score Minimum: " + "  " + pr.get("scoreMin").toString());
      
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
                    
//                    if(ServicePromotion.getInstance().deletePromotion(Integer.parseInt(pr.get("id").toString()))){
                     //   new ListPromotionForm(previous).show();
                   // }
                });
                
                Label lModifier = new Label(" ");
                lModifier.setUIID("NewsTopLine");
                Style modifierStyle = new Style(lModifier.getUnselectedStyle());
                modifierStyle.setFgColor(0xf7ad02);
        
                FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
                lModifier.setIcon(mFontImage);
                lModifier.setTextPosition(LEFT);
                
//                Promotion p = new Promotion(Integer.parseInt(pr.get("scoreMin").toString()), pr.get("type").toString(), pr.get("description").toString(), pr.get("title").toString());
                lModifier.addPointerPressedListener(l -> {
          //      new ModifierPromotionForm(previous,p).show();
        });
                
                
                
                
                
                Container entity = new Container(BoxLayout.x());
                entity.getUnselectedStyle().setBorder(Border.createLineBorder(1));
                entity.addAll(spl,sp2,sp3,sp4,lSupprimer,lModifier);
                
                add(entity);
                }
		 } 
                catch(Exception err) {
                 Dialog.show("Error", "Error parsing result", new Command("OK"));
                 System.out.println(err);
                
                   }

		});
                NetworkManager.getInstance().addToQueueAndWait(cnxSearchCoaches);
                coachesContainer.setScrollableY(true);
                add(coachesContainer);
        
    }
    
    public SearchResultsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("SearchResultsForm");
        setName("SearchResultsForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
