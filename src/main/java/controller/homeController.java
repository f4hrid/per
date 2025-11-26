/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.CardLayout;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.home;
import view.ofertamodpanel;

/**
 *
 * @author Fahrid
 */
public class homeController {
    view.home main;
    view.ofertamodpanel ofertas;
    
    public homeController (home home, ofertamodpanel oferta) {
        this.main = home;
        this.ofertas = oferta;
    }

    public void expandMenu () {
        JLabel uv = main.icon_uv;
        JPanel buv = main.back_uv;
        uv.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                fun.print("UNIVALLE");
                fun.addTest(main.contentcursos, ofertas);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                // cambiar cursor
                uv.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // repintar
                //fun.getOpaque(uv, true);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                //fun.getOpaque(uv, false);

            }
        });
    }
    
    public void topButtons(){
        
        main.boton_cursos.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                CardLayout card = (CardLayout) main.contentpanel.getLayout();
                card.show(main.contentpanel, "curso");
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                main.boton_cursos.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
        main.boton_diplomados.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                CardLayout card = (CardLayout) main.contentpanel.getLayout();
                card.show(main.contentpanel, "diplomado");
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                main.boton_diplomados.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        
    }
}
