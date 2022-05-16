/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.sahargui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.mycompany.myapp.sahargui.BaseForm;
import com.mycompany.myapp.entities.tournoi;
import com.mycompany.myapp.services.ServiceTournoi;
import java.util.ArrayList;

/**
 *
 * @author MSI GAMMING
 */
public class PDF extends BaseForm {

    public PDF(Resources res) {
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste des tournois");
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

       
   

        //this.theme=theme;
        SpanLabel sp = new SpanLabel();

        sp.setText(ServiceTournoi.getInstance().getAllTournois().toString());
        add(sp);
        //// f twig 
        Button pdf = new Button("pdf");
        add(pdf);
        pdf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {


                    String path = "";
                    Document document = new Document();
                    try {

                    //    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + "reclamation.pdf"));

                        document.open();
                        PdfPTable tb1 = new PdfPTable(10);
                        tb1.addCell("nom");
                        tb1.addCell("resultat");
                        tb1.addCell("date");

                        //tb1.addCell("img");
                        tb1.addCell("heure");
                        tb1.addCell("nb participants");

                        ServiceTournoi es = new ServiceTournoi();
                        ArrayList<tournoi> list = es.getAllTournois();
                        for (tournoi m : list) {

                            String nom = m.getNom_tournoi();
                            String resultat = String.valueOf(m.getResultat_tournoi());
                            String heure = String.valueOf(m.getHeure());

                            String nbp = String.valueOf(m.getNb_participants());
                            

                            tb1.addCell(nom );
                            tb1.addCell(resultat);
                            tb1.addCell(heure);
                            //tb1.addCell(image);
                            tb1.addCell(nbp);
                          

                        }
                        document.add(new Paragraph("Produit"));

                        document.add(tb1);
                        document.close();
         //writer.close();
                        com.codename1.io.File file = new com.codename1.io.File("tournoi.pdf");
                        new NewsfeedForm(res).show();

 //   desktop.open(file);
                    } 
                    catch (Exception e){
                        e.printStackTrace();
                    
                  
                   
      
              }}
                    //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);

                    //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);
                    //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

             


        });}
}
