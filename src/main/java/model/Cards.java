/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author Fahrid
 */
public enum Cards {
    OFERTAS("ofertas_academicas"),
    PERFIL("perfil"),
    HOME("homepanel"),
    LOGIN("loginpanel"),
    SIGNUP("signuppanel");

    private final String card;
    private Cards(String cardlayout){
        this.card = cardlayout;
    }
    
    public String getCard(){
        return card;
    }
}
