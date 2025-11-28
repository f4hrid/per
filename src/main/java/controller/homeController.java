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
import view.oftModel;

/**
 *
 * @author Fahrid
 */
public class homeController {
    view.home main;
    
    
    public homeController (home home) {
        this.main = home;
    }

    public void init(){
        switch (fun.idchoose()){
            case 1 -> {
                expandMenu();
                topButtons();
                setImgs();
            }
            default -> expandMenu();
        }
    }
    
    public void expandMenu () {
        JLabel uv = main.icon_uv;
        JPanel buv = main.back_uv;
        uv.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                //fun.print("UNIVALLE");
                fun.addTest(main.contentcursos, new oftModel());
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
                main.boton_cursos.setBackground(fun.touchUp(true));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.boton_cursos.setBackground(fun.touchUp(false));
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
                main.boton_diplomados.setBackground(fun.touchUp(true));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.boton_diplomados.setBackground(fun.touchUp(false));
            }
        });
        
    }
    
    public void setImgs(){
        main.icon_uv.setSVGImage("svg/documento.svg", 50, 50);
    }
    
}
