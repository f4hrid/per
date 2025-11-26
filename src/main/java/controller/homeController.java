/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.home;

/**
 *
 * @author Fahrid
 */
public class homeController {
    view.home main;
    
    public homeController (home home) {
        this.main = home;
    }

    public void expandMenu () {
        JLabel uv = main.icon_uv;
        JPanel buv = main.back_uv;
        uv.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                fun.print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e){
                // cambiar cursor
                uv.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // repintar
                fun.getOpaque(uv, true);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e){
                fun.getOpaque(uv, false);

            }
        });
    }
}
