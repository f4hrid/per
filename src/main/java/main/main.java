/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// curso capitulo 41
package main;

import controller.fun;
import javax.swing.JFrame;
import model.config;
import view.home;

/**
 *
 * @author Fahrid
 */
public class main {
    public static void main(String[] args) {
        fun.themeFlatLight();
        JFrame home = new home();
        fun.setScreen(home, config.titulo);
    }
}
