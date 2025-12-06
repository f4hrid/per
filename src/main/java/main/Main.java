/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 */
// curso capitulo 41
package main;

import controller.Functions;
import javax.swing.JFrame;
import model.config;
import view.Home;

/**
 *
 * @author Fahrid
 */
public class Main {
    public static void main(String[] args) {
        Functions.themeFlatLight();
        JFrame home = new Home();
        Functions.setScreen(home, config.titulo);
    }
}
