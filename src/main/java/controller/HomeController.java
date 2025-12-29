/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.print;
import static java.awt.Color.BLACK;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import static model.config.REDUV;
import view.Home;
import static model.Views.LOGIN;
import static view.Home.showCard;
import view.Login;
import jilmar.PanelRound;
import static view.Home.getButton;

/**
 *
 * @author Fahrid
 */
public class HomeController {
    Home main;
    Login login;

    public HomeController(Home h, Login u) {
        this.main = h;
        this.login = u;
    }
    

    public void init(){
        setUpView();
    }
        
    private void setUpView(){
        print(login.user);
        setUpView_Invitado();
        
        /*
        if (login.user.getRol().isEmpty()){
            setUpView_ByRole();
        } else{
            setUpView_Invitado();
        }
        */
    }
    
    private void setUpView_ByRole(){
        switch(login.user.getRol()){
            case "administrador" ->{
                //funciones para administrador
                //setUpView_Admin();
                //setUserName();
            } case "docente" ->{
                
            } case "estudiante" ->{
                setUpView_Estudiante();
            }
        }
    }
    
    private void setUpView_Invitado(){
        rbacLogin();
        rbacSignup();
    }
    
    private void setUpView_Estudiante(){
        rbacLogout();
    }
    
    private void rbacSignup(){
        PanelRound b = getButton("Registrarse", REDUV, BLACK);
        mouseListener(b, ()->{
            //showCard(main.home, SIGNUP.getCard()); //cambiar de carta
            main.PROXIMAMENTE();
        });
    }
    
    private void rbacLogout(){
        PanelRound b = getButton("Cerrar Sesión", REDUV, BLACK);
        mouseListener(b, ()->{
            main.PROXIMAMENTE();
        });
    }
    
    private void rbacLogin(){
        PanelRound b = getButton("Iniciar Sesión", REDUV, BLACK);
        mouseListener(b, () -> {
            showCard(main.home, LOGIN.toString());
        });
    }
    
    public static void mouseListener(JComponent boton, Runnable clicked){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                clicked.run();
            }
        });
    }      

}


