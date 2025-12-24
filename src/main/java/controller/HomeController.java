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
import javax.swing.JOptionPane;
import static view.Home.createACBM;
import static model.config.REDUV;
import view.Home;
import static view.Home.setHandCursor;
import static model.Views.LOGIN;
import static model.Views.SIGNUP;
import static view.Home.setShade;
import static view.Home.showCard;
import view.Login;
import view.template.AccessControlButtonModel;

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
        if (login.controller.getUser()!=null){
            //setUpView_ByRole();
        }else{
            setUpView_Invitado();
        }
    }
    /*
    private void setUpView_ByRole(){
        switch(login.getRol()){
            case "administrador":
                //funciones para administrador
                //setUpView_Admin();
                setUserName();
                break;
            case "docente": 
                //setUpView_Docente();
                setUserName();
                break;
            case "estudiante": 
                setUpView_Estudiante();
                setUserName();
                break;
        }
    }
    */
    
    private void setUpView_Invitado(){
        rbacLogin();
        rbacSignup();
    }
    
    private void setUpView_Estudiante(){
        rbacLogout();
    }
    
    private void rbacSignup(){
        AccessControlButtonModel b = createAccess("Registrarse");
        bindMouseListener(b.boton, ()->{
            //showCard(main.home, SIGNUP.getCard()); //cambiar de carta
            main.PROXIMAMENTE();
        });
    }
    
    private void rbacLogout(){
        AccessControlButtonModel b = createAccess("Cerrar Sesión");
        bindMouseListener(b.boton, ()->{
            main.PROXIMAMENTE();
        });
    }
    
    private void rbacLogin(){
        AccessControlButtonModel b = createAccess("Iniciar Sesión");
        bindMouseListener(b.boton, () -> {
            showCard(main.home, LOGIN.toString());
        });
    }
    
    private AccessControlButtonModel createAccess(String titulo){
        AccessControlButtonModel b = createACBM(titulo, REDUV, Color.BLACK);
        main.accesscontrol.add(b);
        return b;
    }
    /*
    private void setUserName(){
        try{
            Estudiante e = login.getEstudiante();
            main.usuario.setText(e.getNombres()+" "+e.getApellidos());
        }catch(Exception e){
            main.usuario.setText(null);
        }
    }
    */

    public static void bindMouseListener(JComponent boton, Runnable clicked){
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                clicked.run();
            }
        });
    }      

}


