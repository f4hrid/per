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
import javax.swing.JPanel;
import static model.config.REDUV;
import view.Home;
import static model.Views.LOGIN;
import static view.Home.showCard;
import view.Login;
import jilmar.PanelRound;
import static view.Home.setProperties;

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
        setupView();
    }
        
    private void setupView(){
        if (login.user!=null){
            setupViewByRole();
            print("hola desde el setup");
        } else {
            setupView_Invitado();
        }
    }
    
    private void setupViewByRole(){
        switch(login.user.getRol()){
            case "administrador" ->{
                //funciones para administrador
                print("hola desde la vista de administrador");
            } case "docente" ->{
                print("hola desde la vista de docente");
            } case "estudiante" ->{
                setUpView_Estudiante();
                print("hola desde la vista de estudiante");
            }
        }
    }
    
    private void setupView_Invitado(){
        rbacLogin();
        rbacSignup();
    }
    
    private void setUpView_Estudiante(){
        rbacLogout();
    }
    
    private void rbacSignup(){
        String title = "Registrarse";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            //showCard(main.home, SIGNUP.getCard()); //cambiar de carta
            main.PROXIMAMENTE()
        );
    }
    
    private void rbacLogout(){
        String title = "Cerrar Sesión";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            main.PROXIMAMENTE()
        );
    }
    
    private void rbacLogin(){
        String title = "Iniciar Sesión";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            showCard(main.home, LOGIN.toString())
        );
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


