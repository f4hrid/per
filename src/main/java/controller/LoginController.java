/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.print;
import entities.Usuario;
import entities.controllers.UsuarioJpaController;
import javax.swing.JOptionPane;
import static controller.HomeController.bindMouseListener;
import model.Views;
import static model.config.getEntityManagerFactory;
import view.Home;
import static view.Home.setHandCursor;
import view.Login;
import static view.Home.showCard;

/**
 *
 * @author Fahrid
 */
public class LoginController {
    UsuarioJpaController jpa = new UsuarioJpaController(getEntityManagerFactory());
    
    Home home;
    Login main;
    Usuario user;
 
    
    public LoginController(Login l, Home h){
        this.main = l;
        this.home = h;
        this.user = null;
    }
    
    public void init(){
        login();
        backButton();
        signup();
    }
    
    private void login(){
        bindMouseListener(
                main.boton, 
                ()->{confirmUser();}
        );
    }
    
    private void backButton(){
        bindMouseListener(
                main.regresar, 
                ()->{showCard(home.home, Views.HOME.getCard());}
        );
    }
    
    private void signup(){
        bindMouseListener(
                main.registrar, 
                ()->{home.PROXIMAMENTE();} 
        );
    }
    
    
    // Verifica las credenciales
    private Usuario confirmUser() {
        Usuario u = jpa.findUsuario(getUserSet());
        
        if (u==null || !u.getPasswordHash().equals(getPasswordSet())){
            main.contraseña.setText(null);
            home.showMessage(
                "Código y contraseña incorrecta. Inténtelo de nuevo.",
                "Acceso a cuenta",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        //limpiar datos
        main.usuario.setText(null);
        main.contraseña.setText(null);
        return user = u; //retornar el USUARIO
    }
    
    public Usuario getUser(){
        return user;
    }
    
    private String getUserSet(){
        return main.usuario.getText().trim();
    }

    private String getPasswordSet(){
        return main.contraseña.getText();
    }
}
