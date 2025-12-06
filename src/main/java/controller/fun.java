/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to changeCard this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import custom.LabelSVG;
import java.awt.CardLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
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
    
    // agregar botones logicos y practicos
    public static void handyButtons(JComponent panel, LabelSVG icon, String texto){
        //proximamente
    }
    
    //para cambiar los diseños por cardlayout
    public static void changeCard(JPanel parent, String nombre){
        CardLayout card = (CardLayout) parent.getLayout();
        card.show(parent, nombre);
    }
    
    // proxima a borrar
    public static void addMix(JPanel contenedor, JPanel modelo){
        contenedor.add(modelo);
        contenedor.repaint();
        contenedor.revalidate();
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
