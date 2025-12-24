/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author Fahrid
 */
public enum Views {
    /* VISTAS ASIGNADAS */
    OFERTAS("mainofferspanel"), // vista principal de ofertas académicas (inv, est & doc)
    PERFIL("profilepanel"),     // vista de administración de perfiles (est & doc)
    HOME("homepanel"),          // vista
    LOGIN("loginpanel"),        // vista de control RBAC LOGIN
    SIGNUP("signuppanel"),      // vista de control RBAC SIGNUP
    DASHBOARD("dashboardpanel");// vista de administrador

    private final String card;
    private Views(String cardlayout){
        this.card = cardlayout;
    }
    
    public String getCard(){
        return card;
    }
}
