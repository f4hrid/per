/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author Fahrid
 */
public enum Roles {
    ADMINISTRADOR("administrador"),
    DOCENTE("docente"),
    ESTUDIANTE("estudiante");
    
    private final String role;

    private Roles(String r){
        this.role = r;
    }
    
    public String getRole() {
        return role;
    }   
}

