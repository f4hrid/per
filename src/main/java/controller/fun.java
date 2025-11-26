/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Fahrid
 */
public class fun {
    public static void print(Object t) {
        System.out.println(t);
    }
    
    public static void replicate(JComponent comp, JPanel panel, int cantidad){
        for (int i = 0; i < cantidad; i++) {
            comp.add(panel);
        }
    }
    
    public static void getOpaque(JComponent comp, boolean bool){
        comp.setOpaque(bool);
        comp.repaint();
    }
    
    public static void setFullScreen(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public static void themeFlatLight() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Error de función Flatlaf()");
        }
    }
    
}
