/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static model.config.*;
import view.Home;
import view.archetype.ButtonModel;

/**
 *
 * @author Fahrid
 */
public class HomeController {
    view.Home main;
    
    private static final String CARD_OFFERS = "card_controlofertas";
    private static final String CARD_COFFERS = "card_controlacademico";
    private static final String CARD_USERS = "card_controlusuarios";

    
    
    public HomeController (Home home) {
        this.main = home;
    }

    public void init(){
        buttonsMenu();
        //menuButtons();
    }
    
    public void buttonsMenu(){
        buttonOffers(); // boton de visualizador de ofertas académicas
        buttonCOffers(); // boton de diseño de ofertas
        buttonUser(); //boton de usuario
        exitButton(); // boton de guardado
        
        
        // bindMenu(main.boton_ofertas, );
    }
    
    /*
    public void bindMenu(JComponent c, Runnable event){
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                event.run();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                Home.setHandCursor(c);
                Home.setShaded(c, reduv);
            }
            @Override
            public void mouseExited(MouseEvent e){
                Home.setShaded(c, main.getBackgroundMenu());
            }
        });
    }
    
    public void gh(JComponent parent, String card){
        new Runnable(){
            @Override
            public void run() {
                Home.selectCard((javax.swing.JPanel) parent, card);
            }
        };
    }
    */
    
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
                
                buttonHandy(); //probando
                
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

    public void buttonHandy(){
        ButtonModel boton = new ButtonModel("Crear Extensión Académica","svg/add.svg",25);

        if (main.usefulbutton_panel.getComponentCount() == 1) {
            return;
        } else {
            boton.addMouseListener(new java.awt.event.MouseAdapter(){
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e){
                    main.setShaded(boton.boton, reduv);
                }
            });
            main.usefulbutton_panel.add(boton);
        }
                

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
                Home.showConfirmPane(
                        null,
                        "¿Esta seguro que desea salir y guardar los cambios hechos?", 
                        "Salir y Guardar Cambios",
                        JOptionPane.QUESTION_MESSAGE
                );
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
        
}
