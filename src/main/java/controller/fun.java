/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Fahrid
 */
public class fun {
    public static view.archetype.panelModel mod;
    
    public static void print(Object t) {
        System.out.println(t);
    }
    
    
    public static void addMix(JPanel contenedor, JPanel modelo){
        contenedor.add(modelo);
        contenedor.repaint();
        contenedor.revalidate();
    }
    
    public static void replicate(JComponent comp, JPanel panel, int cantidad){
        for (int i = 0; i < cantidad; i++) {
            comp.add(panel);
        }
    }
    
    
    
    public static void setScreen(JFrame frame, String titulo) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle(titulo);
        frame.setVisible(true);
    }
    
    public static void themeFlatLight() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Error de función Flatlaf()");
        }
    }
    
    public static void themeFlatDark() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("Error de función Flatlaf()");
        }
    }
    
}
