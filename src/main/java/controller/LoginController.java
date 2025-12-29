/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.print;
import entities.Usuario;
import entities.controllers.UsuarioJpaController;
import javax.swing.JOptionPane;
import model.Views;
import view.Home;
import view.Login;
import static view.Home.showCard;
import static controller.HomeController.mouseListener;

/**
 *
 * @author Fahrid
 */
public class LoginController {
    
    private Usuario user;
    
    private final Home home;
    private final Login main;
    private final UsuarioJpaController jpa;
 
    
    public LoginController(Login l, Home h, UsuarioJpaController jpa){
        this.main = l;
        this.home = h;
        this.jpa = jpa;
    }
    
    public void init(){
        login();
        backButton();
        signup();
    }
    
    private void login(){
        mouseListener(
                main.boton, 
                this::confirm
        );
    }
    
    private void backButton(){
        mouseListener(
                main.regresar, 
                ()->showCard(home.home, Views.HOME.getCard())
        );
    }
    
    private void signup(){
        mouseListener(
                main.registrar, 
                ()->home.PROXIMAMENTE()
        );
    }
    
    private void confirm(){
        Usuario u = authenticate();
        
        if (u==null){
            onLoginError();
            return;
        }
        
        user = u;
        onLoginSuccess();
    }
    
    private Usuario authenticate(){
        String usuario = main.getUsername();
        String contraseña = main.getPassword();
        
        Usuario u = jpa.findUsuario(usuario);
        
        if (u==null){
            return null;
        }
        
        if (!u.getPasswordHash().equals(contraseña)) {
            return null;
        }
        
        return u;
    }
    
    private void onLoginSuccess(){
        onEmptyFields(true);
        showCard(home.home, model.Views.HOME.getCard());
    }

    private void onLoginError(){
        onEmptyFields(false);
        home.showMessage(
            "Código y contraseña incorrecta. Inténtelo de nuevo.",
            "Acceso a cuenta",
            JOptionPane.ERROR_MESSAGE);
    }
        
    public Usuario getUser(){
        return user;
    }
    
    private void onEmptyFields(boolean b){
        main.onEmptyFieldPassword();
        if (b){
            main.onEmptyFieldUser();
        }
    }
}
