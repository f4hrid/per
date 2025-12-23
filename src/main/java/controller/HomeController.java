/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.print;
import entities.Estudiante;
import entities.Usuario;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import static model.config.REDUV;
import view.Home;
import static view.Home.selectCard;
import static view.Home.setHandCursor;
import static view.Home.setShaded;
import view.template.AccessControlButtonModel;
import static model.Cards.LOGIN;
import static model.Cards.SIGNUP;

/**
 *
 * @author Fahrid
 */
public class HomeController {
    
    private Home main;
    
    private final Usuario user;
    
    
    public HomeController (Home h) {
        this.main = h;
        this.user = main.login.controller.getUser();
    }

    public void init(){
        invitado();
        setUserName();
        //menuButtons();
    }
    
    private void invitado(){
        rbacLogin();
        rbacRecord();
    }
    
    private void rbacRecord(){
        AccessControlButtonModel b = createAccess("Registrarse");
        
        Runnable clicked = ()->{
            main.home.add(main.login, SIGNUP.getCard());
            selectCard(main.home, SIGNUP.getCard());
        };
        Runnable entered = ()->{
            setShaded(b.boton, Color.BLACK);
            setHandCursor(b.boton);
        };
        Runnable exited = ()->{
            setShaded(b.boton, REDUV);
        };
        
        bindMouseListener(b.boton, clicked, entered, exited);
    }
    
    private void rbacLogin(){
        AccessControlButtonModel b = createAccess("Iniciar Sesión");
        
        Runnable clicked = ()->{
            main.home.add(main.login, LOGIN.getCard());
            selectCard(main.home, LOGIN.getCard());
        };
        Runnable entered = ()->{
            setShaded(b.boton, Color.BLACK);
            setHandCursor(b.boton);
        };
        Runnable exited = ()->{
            setShaded(b.boton, REDUV);
        };
        
        bindMouseListener(b.boton, clicked, entered, exited);
    }
    
    private AccessControlButtonModel createAccess(String titulo){
        AccessControlButtonModel b = view.Home.createACBM(titulo, REDUV);
        main.accesscontrol.add(b);
        return b;
    }
    
    private void setUserName(){
        try{
            Estudiante e = user.getEstudiante();
            main.usuario.setText(e.getNombres()+" "+e.getApellidos());
        }catch(Exception e){
            main.usuario.setText(null);
        }
    }
    
    private void buttonsMenu(){
        print("ingrese al sistema");
        //buttonOffers(); // boton de visualizador de ofertas académicas
        //buttonCOffers(); // boton de diseño de ofertas
        //buttonUser(); //boton de usuario
        //exitButton(); // boton de guardado
        
        
        // bindMenu(main.boton_ofertas, );
    }
    
    public static void bindMouseListener(JComponent boton, Runnable clicked, Runnable entered, Runnable exited){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                clicked.run();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                entered.run();
            }
            @Override
            public void mouseExited(MouseEvent e){
                exited.run();
            }
        });
    }
    
    public static void bindMouseListener(JComponent boton, Runnable clicked, Runnable entered){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                clicked.run();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                entered.run();
            }
        });
    }

    public static void bindMouseListener(JComponent boton, Runnable clicked){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                clicked.run();
            }
        });
    }
    

    /*
    public void buttonOffers () {
        main.boton_ofertas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                //fun.print("UNIVALLE");
                main.selectCard(main.contentpanel, CARD_OFFERS);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                // cambiar cursor
                main.setHandCursor(main.boton_ofertas);
                
                // cambiar color
                main.setShaded(main.boton_ofertas, REDUV);
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
                
                
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                main.setHandCursor(main.boton_cofertas);
                
                main.setShaded(main.boton_cofertas, REDUV);
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
                // homecontrol total a la información
                // por card layout
                
                Home.selectCard(main.contentpanel, CARD_USERS);

            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                Home.setHandCursor(main.boton_usuarios);
                Home.setShaded(main.boton_usuarios, REDUV);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                Home.setShaded(main.boton_usuarios, main.getBackgroundMenu());
            }
            
        });
    }
    */
        
    
    /*
    public void exitButton(){
        main.boton_salida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                main.showConfirmPane(
                        "¿Esta seguro que desea salir y guardar los cambios hechos?", 
                        "Salir y Guardar Cambios",
                        JOptionPane.QUESTION_MESSAGE
                );
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                Home.setHandCursor(main.boton_salida);
                Home.setShaded(main.boton_salida, REDUV);

            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                Home.setShaded(main.boton_salida, main.getBackgroundMenu());
            }
            
        });
    }
    */
        
}
