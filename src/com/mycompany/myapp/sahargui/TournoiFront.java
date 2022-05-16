/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.sahargui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.tournoi;
import com.mycompany.myapp.services.ServiceTournoi;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class TournoiFront extends BaseForm {
    Form current;
    tournoi c;
public ArrayList<tournoi> tournois;
    public TournoiFront(Form previous,Resources res) {
        super("liste Tournoi", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste Tournoi");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
     
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(res,swipe, res.getImage("news-item.jpg"), spacer1, "15 Likes  ", "85 Comments", "Integer ut placerat purued non dignissim neque. ");
        addTab(res,swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        tb.addMaterialCommandToRightBar("Back", FontImage.MATERIAL_EXIT_TO_APP, e -> new NewsfeedForm(res).show());
        ButtonGroup barGroup = new ButtonGroup();
       // RadioButton all = RadioButton.createToggle("All", barGroup);
        //all.setUIID("SelectBar");
        RadioButton Tournoi = RadioButton.createToggle("Tournoi", barGroup);
        Tournoi.setUIID("SelectBar");
       // RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        //popular.setUIID("SelectBar");
       // RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        //myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, Tournoi),
                FlowLayout.encloseBottom(arrow)
        ));
        
       // all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            //updateArrowPosition(all, arrow);
        });
        //bindButtonSelection(all, arrow);
        bindButtonSelection(Tournoi, arrow);
       // bindButtonSelection(popular, arrow);
        //bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
 
/*this.getToolbar().addSearchCommand(e -> {
    String text = (String) e.getSource(); 
    if(text==c.getNom()){
    ServiceEvenement sp = new ServiceEvenement();          
    sp.rechercheEvent(c);
    addButton(res.getImage("news-item-1.jpg"),c.getNom().toString()+ "\n" +c.getDateD().toString()+ "\n" +c.getDateF().toString()+ "\n" +c.getLieu().toString()+"\n" +c.getType().toString()+"\n" +c.getNb_participants()+"\n" +c.getNb_places(), false, 26, 32,res);
    Dialog.show("modifier", "modifier avec succés", "OK", null);
}    
}
    );*/

  this.show();

       SpanLabel sp = new SpanLabel();
tournois =ServiceTournoi.getInstance().getAllTournois();
 
for (tournoi e :tournois)
{ addButton(res.getImage("logo.png"),"Nom: "+e.getNom_tournoi().toString()+ "\n" + "\n" + "\nNb_participants: " +e.getNb_participants()+"\nResultat: "+e.getResultat_tournoi()+ "\n" , false, 26, 32,res,+e.getId_tournoi());
      //  sp.setText(sp.getText()+"\n"+e.getDescription().toString());
    
}
 // add(sp);   

    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Resources res,Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
          Button pdf = new Button("PDF");

                       pdf.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                         
                                            if (Dialog.show("Confirmation", "Voulez vous imprimer cette produit en pdf ?", "Oui", "Annuler")) {
                                                
                                                                
                                                                      
                                                                      
                                                                        Dialog.show("Success","imprimer",new Command("OK"));
                                                                        new PDF(res).show();
                                                           // new AllReclamation(res).show();
                                                                      
                                                                  

                                            }
                                }
                            });
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments,pdf),
                            spacer
                        )
                        
                )
                    
            );
 
        swipe.addTab("", page1);
    }
   
  private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount, Resources res,int idevent) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
        
       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
     
        
                       
                      
                    cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));   
       add(cnt);
        
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
        
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

  

  
}
