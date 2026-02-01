/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.Functions.print;

/**
 *
 * @author Fahrid
 */
public class HomeManager {
    private final controller.HomeController controller;
    private final view.Home view;

    public HomeManager(controller.HomeController c, view.Home v) {
        this.controller = c;
        this.view = v;
    }
    
    public void showByRole(){
        print("hola desde la selección por rol");
        switch(view.getUser().getRol()){
            case "administrador" -> {
                showAdministrador();
                print("hola desde la vista de administrador");
            } case "docente" -> {
                showDocente();
                print("hola desde la vista de docente");
            } case "estudiante" -> {
                showEstudiante();
                print("hola desde la vista de estudiante");
            }
        }
    }
    
    public void showInvitado(){
        clear();
        controller.initLogin();
        controller.initSignup();
    }
    
    private void showEstudiante(){
        clear();
        controller.initLogout();
    }
    
    private void showDocente(){
        clear();
    }
    
    private void showAdministrador(){
        clear();
    }
    
    private void clear(){
        view.accesscontrol.removeAll();
        view.accesscontrol.revalidate();
        view.accesscontrol.repaint();
    }
}
