/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.homefront;

import com.codename1.components.FloatingHint;
import com.mycompany.myapp.guiskander.*;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;

import com.mycompany.myapp.sahargui.BaseForm;
import com.mycompany.myapp.guiskander.HomeFormskander;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.ActivateForm;
import com.mycompany.myapp.gui.SignUpForm;
import com.mycompany.myapp.guiayadi.HomeayadiForm;
import com.mycompany.myapp.guiemna.HomeEmnaForm;
//import com.mycompany.myapp.guisahar.TournoiFront;
import com.mycompany.myapp.guisnowyy.HomeProduitForm;
import com.mycompany.myapp.services.ServiceUtilisateur;
/**
 *
 * @author Ramez
 */
public class HomeForm extends BaseForm {
HomeForm current = this;
Resources theme_1;
    public HomeForm(Resources res) {
        super(new BorderLayout());
        super.addSideMenu(res);
       setTitle("Home");
        setLayout(BoxLayout.y());
        theme_1 = UIManager.initFirstTheme("/theme_1");
        add(new Label("Choisir une option"));
        Button btnskander = new Button("Gestion skander");
        Button btnayadi = new Button("gestion ayadi");
        Button btnemna = new Button("gestion emna ");
        Button btnsahar = new Button("gestion sahar");
        Button btnsnowy = new Button("gestion snowy");
        btnskander.addActionListener(e-> new HomeFormskander(current).show());
        btnayadi .addActionListener(e-> new HomeayadiForm(current).show());
        btnemna.addActionListener(e-> new HomeEmnaForm(res,current).show());
        btnsnowy.addActionListener(e-> new HomeProduitForm(current).show());
       // btnsahar.addActionListener(e-> new TournoiFront(current,res).show());
        addAll(btnskander,btnayadi,btnemna,btnsnowy);
        
    }
    
}


        
    