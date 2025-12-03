/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import custom.LabelSVG;
import java.awt.CardLayout;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import view.archetype.*;
import jilmar.PanelRound;
import model.*;
import view.home;
import view.archetype.panelModel;

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
        buttonsMenu();
        //menuButtons();
    }
    /*
    public void test(){
        JPanel model = new buttonModel()
        PanelRound boton = new buttonModel().boton;
        LabelSVG icono = new buttonModel().icono;
        JLabel titulo = new buttonModel().titulo;
        
        main.menupanel.add(model)
    }
    */
    
    public void buttonsMenu(){
        buttonOffers(); // boton de visualizador de ofertas académicas
        buttonCOffers(); // boton de diseño de ofertas
        buttonUser(); //boton de usuario
        exitButton(); // boton de guardado
    }
    
    public void buttonOffers () {
        PanelRound boa = main.boton_ofertas;
        boa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                //fun.print("UNIVALLE");
                //fun.addMix(main.contentpanel_ofertas, new panelModel());
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                // cambiar cursor
                boa.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // cambiar color
                boa.setBackground(config.reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                boa.setBackground(main.menupanel.getBackground());
            }
        });
    }
        
    public void buttonCOffers(){
        PanelRound bcoa = main.boton_cofertas;
        bcoa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                //boton de creación de ofertas cardlayout
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                bcoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
                bcoa.setBackground(config.reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                bcoa.setBackground(main.menupanel.getBackground());
            }
            
        });
    }
        
    public void buttonUser(){
        PanelRound bua = main.boton_usuarios;
        bua.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                // boton de visualizador de usuarios del sistema: docentes, estudiante, administradores
                // control total a la información
                // por card layout
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                bua.setCursor(new Cursor(Cursor.HAND_CURSOR));
                bua.setBackground(config.reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                bua.setBackground(main.menupanel.getBackground());
            }
            
        });
    }
        
    public void exitButton(){
        PanelRound bes = main.boton_salida;
        bes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                int confirm = JOptionPane.showConfirmDialog(
                        null, 
                        "¿Estás seguro que deseas guardar los cambios en la base de datos?",
                        "Registrar Cambios",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                fun.print(confirm);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                bes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                bes.setBackground(config.reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                bes.setBackground(main.menupanel.getBackground());
            }
            
        });
    }
    
    /*
    public void menuButtons(){
        
        main.boton_cursos.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                CardLayout card = (CardLayout) main.contentpanel_ofertas.getLayout();
                card.show(main.contentpanel_ofertas, "curso");
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
                CardLayout card = (CardLayout) main.contentpanel_ofertas.getLayout();
                card.show(main.contentpanel_ofertas, "diplomado");
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
    */


    
}
