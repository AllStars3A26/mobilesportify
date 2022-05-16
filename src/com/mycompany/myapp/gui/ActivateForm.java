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

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;



import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.sun.mail.smtp.SMTPTransport;



import java.util.Date;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class ActivateForm extends BaseForm {
TextField email;
String Bypass="hKTfvp";
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );

       email = new TextField("", "Saisir votre email", 20, TextField.EMAILADDR);
       email.setSingleLineTextArea(false);
       Button Valider = new Button("valider");
       Label haveanaccount=new Label("retour de connecter?");
       Button SignIn = new Button("Renouveller votre mot de passe");
       SignIn.addActionListener(e->previous.showBack());
       SignIn.setUIID("CenterLink");
          Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                Valider,
                FlowLayout.encloseCenter(haveanaccount), 
                SignIn
        );
          content.setScrollableY(true);
          add(BorderLayout.SOUTH, content);
                  Valider.requestFocus();
                 

        Valider.addActionListener(e -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDialog = ip.showInfiniteBlocking();
            sendMail(res);
            //new Activev2Form(res).show();
            ipDialog.dispose();
            Dialog.show("Mot de passe ","Nous avons envoyer le mot de passe a votre email. Veuillez verifier votre boite de reception",new Command("ok"));
            new SignInForm(res).show();
            refreshTheme();
        });
    }
    
    public void sendMail(Resources rs){
        try{
            
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
		
            
            Session session = Session.getInstance(props, null);
             System.out.println("im here"+email.getText().toString());
            
            MimeMessage msg = new MimeMessage(session);
           
            msg.setFrom(new InternetAddress("Reintialisation mor de passe <monEmail@domaine.com>"));
            
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            
            msg.setSubject("mot de pass oublier ? ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
            String  mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), rs);
            String text="Bienvenu sur notre application : tapez ce mot de pass:"+Bypass+"dansle champs requis et appuiez sur confirmer";
            msg.setText(text);
            SMTPTransport st=(SMTPTransport)session.getTransport("smtps");
            System.out.println("mazelt mabaathtech1");
            st.connect("smtp.googlemail.com",465,"skanderhelmisportify@gmail.com","wajdinos15");
            System.out.println("mazelt mabaathtech2");
            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("hani baatht melghadi");
            System.out.println("server reponse : "+st.getLastServerResponse());
        }catch(Exception e){
            e.printStackTrace();
        }
          
    }
}
