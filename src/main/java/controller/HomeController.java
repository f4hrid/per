/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.print;
import entities.Usuario;
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
        if (main.getUser()!=null){
            setupViewByRole();
            print("hola desde el setup");
        } else {
            setupView_Invitado();
        }
    }
    
    private void setupViewByRole(){
        switch(main.getUser().getRol()){
            case "administrador" -> {
                //funciones para administrador
                print("hola desde la vista de administrador");
            } case "docente" -> {
                print("hola desde la vista de docente");
            } case "estudiante" -> {
                setUpView_Estudiante();
                print("hola desde la vista de estudiante");
            }
        }
    }
    
    private void setupView_Invitado(){
        initLogin();
        initSignup();
    }
    
    private void setUpView_Estudiante(){
        initLogout();
        setName();
    }
    
    private void initLogin(){
        String title = "Iniciar Sesión";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            showCard(main.home, LOGIN.toString())
        );
    }
    
    private void initSignup(){
        String title = "Registrarse";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            //showCard(main.home, SIGNUP.getCard()); //cambiar de carta
            main.PROXIMAMENTE()
        );
    }
    
    private void initLogout(){
        String title = "Cerrar Sesión";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            main.PROXIMAMENTE()
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

    private void setName() {
        main.usuario.setText(main.user.getEstudiante().getNombres()+" "+main.user.getEstudiante().getApellidos());
    }

}


