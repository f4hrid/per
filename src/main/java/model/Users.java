/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author Fahrid
 */
public enum Users {
    ADMINISTRADOR("administrador"),
    DOCENTE("docente"),
    ESTUDIANTE("estudiante"),
    INVITADO("invitado");
    
    private final String rol;

    private Users(String rol){
        this.rol = rol;
    }
    
    public String getRol() {
        return rol;
    }   
}
