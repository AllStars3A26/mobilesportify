/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.SessionManager;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.io.IOException;
import com.mycompany.myapp.sahargui.BaseForm;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
 private static String i;
    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);


        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(
                           
                    )
                )
        ));

        TextField username = new TextField(SessionManager.getUserName());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField full_name = new TextField(SessionManager.getFullName(), "Full Name", 20, TextField.ANY);
        full_name.setUIID("TextFieldBlack");
        addStringValue("full_name", full_name);

        TextField numero = new TextField(SessionManager.getUserName());
        username.setUIID("TextFieldBlack");
        addStringValue("numero", numero);
        //update and edit
        Button modif = new Button("Modifier");
        Button picture = new Button("image");
        modif.setUIID("Edit");
        picture.setUIID("Update");
        addStringValue("", modif);
        addStringValue("", picture);
        
        TextField path =new TextField("");
        picture.addActionListener(e->{
            i= Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (i!=null){
                Image im;
                try{
                    im = Image.createImage(i);
                    im= im.scaled( res.getImage("profile-pic.jpg").getWidth(),  res.getImage("profile-pic.jpg").getHeight());
                    System.out.println(i);
                    path.setText(i);
                    
                    
                    
                    
                    
                }catch(IOException ex){
                    System.out.println("image couldn't load");
                }
            }
            
        });
        modif.addActionListener(edit->{
            InfiniteProgress ip = new InfiniteProgress();
            
            final Dialog ipDlg = ip.showInifiniteBlocking();
            ServiceUtilisateur.EditUser(username.getText(), full_name.getText(),email.getText(), path.getText(), numero.getText());
            
            SessionManager.setUserName(username.getText());
            SessionManager.setFullName(full_name.getText());
            SessionManager.setEmail(email.getText());
            SessionManager.setPhoto(path.getText());
            SessionManager.setUserName(username.getText());
            SessionManager.setNum(numero.getText());
            Dialog.show("Success"," modification terminer","OK",null);
            ipDlg.dispose();
            refreshTheme();
            
            
        });
        
        
        
        
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
