/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.*;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import jilmar.PanelRound;
import model.*;
import static model.config.*;
import view.Home;

/**
 *
 * @author Fahrid
 */
public class HomeController {
    view.Home main;
    
    private String CARD_OFFERS = "card_controlofertas";
    private String CARD_COFFERS = "card_controlacademico";
    private String CARD_USERS = "card_controlusuarios";

    
    
    public HomeController (Home home) {
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
        main.boton_ofertas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                //fun.print("UNIVALLE");
                main.selectCard(main.contentpanel, CARD_OFFERS);
                
                main.setUsefulButtonVisible(false);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                // cambiar cursor
                main.setHandCursor(main.boton_ofertas);
                
                // cambiar color
                main.setShaded(main.boton_ofertas, reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.setShaded(main.boton_ofertas, main.getBackgroundMenu());
            }
        });
    }
        
    public void buttonCOffers(){
        main.boton_cofertas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                //boton de creación de ofertas cardlayout
                main.selectCard(main.contentpanel, CARD_COFFERS);
                
                main.setUsefulButtonVisible(true);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                main.setHandCursor(main.boton_cofertas);
                
                main.setShaded(main.boton_cofertas, reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.setShaded(main.boton_cofertas, main.getBackgroundMenu());
            }
            
        });
    }
        
    public void buttonUser(){
        main.boton_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                // boton de visualizador de usuarios del sistema: docentes, estudiante, administradores
                // control total a la información
                // por card layout
                
                main.selectCard(main.contentpanel, CARD_USERS);

                main.setUsefulButtonVisible(true);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                main.setHandCursor(main.boton_usuarios);
                main.setShaded(main.boton_usuarios, reduv);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.setShaded(main.boton_usuarios, main.getBackgroundMenu());
            }
            
        });
    }
        
    public void exitButton(){
        main.boton_salida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                int confirm = JOptionPane.showConfirmDialog(
                        null, 
                        "¿Estás seguro que deseas guardar los cambios en la base de datos?",
                        "Registrar Cambios",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                Functions.print(confirm);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                main.setHandCursor(main.boton_salida);
                main.setShaded(main.boton_salida, reduv);

            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.setShaded(main.boton_salida, main.getBackgroundMenu());
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
                main.boton_cursos.setBackground(Functions.touchUp(true));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.boton_cursos.setBackground(Functions.touchUp(false));
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
                main.boton_diplomados.setBackground(Functions.touchUp(true));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                main.boton_diplomados.setBackground(Functions.touchUp(false));
            }
        });
        
    }
    */


    
}
