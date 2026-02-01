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
    private final Home main;
    private final Login login;
    
    private final HomeManager manager;

    public HomeController(view.Home h, Login u) {
        this.main = h;
        this.login = u;
        this.manager = new HomeManager(this, main);
    }
    
    public void init(){
        setupView();
    }
        
    private void setupView(){
        if (main.getUser()!=null){
            manager.showByRole();
        } else {
            manager.showInvitado();
        }
    }
    
    public void initLogin(){
        String title = "Iniciar Sesión";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            showCard(main.home, LOGIN.toString())
        );
    }
    
    public void initSignup(){
        String title = "Registrarse";
        JPanel container = main.accesscontrol;
        
        PanelRound b = setProperties(title, REDUV, BLACK, container);
        mouseListener(b, () ->
            //showCard(main.home, SIGNUP.getCard()); //cambiar de carta
            main.PROXIMAMENTE()
        );
    }
    
    public void initLogout(){
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
    
    /*
    public void setUser(Usuario u){
        main.user = u;
    }
    */
    
    private void setName() {
        main.usuario.setText(main.user.getEstudiante().getNombres()+" "+main.user.getEstudiante().getApellidos());
    }

}


