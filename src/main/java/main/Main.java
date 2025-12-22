/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 */
// curso capitulo 41
package main;

import static controller.Functions.setScreen;
import static controller.Functions.themeFlatLight;
import model.config;
import view.Home;

/**
 *
 * @author Fahrid
 */
public class Main {
    public static void main(String[] args) {
        themeFlatLight();
        Home home = new Home();
        setScreen(home, config.TITULO);
    }
}
