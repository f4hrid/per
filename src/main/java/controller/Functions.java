/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Fahrid
 */
public class Functions {
    public static view.archetype.PanelModel mod;
    
    public static void print(Object t) {
        System.out.println(t);
    }
    

    
    //replica un objeto en un componente swing
    public static void replicate(JComponent comp, JPanel panel, int cantidad){
        for (int i = 0; i < cantidad; i++) {
            comp.add(panel);
        }
    }
    
    //hace todo lo de la pantalla
    public static void setScreen(JFrame frame, String titulo) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle(titulo);
        frame.setVisible(true);
    }
    
    //tema claro
    public static void themeFlatLight() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Error de función Flatlaf()");
        }
    }
    
    //temas negros
    public static void themeFlatDark() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("Error de función Flatlaf()");
        }
    }
    
}
