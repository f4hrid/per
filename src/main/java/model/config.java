/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.EntityManagerFactory;
import static jakarta.persistence.Persistence.createEntityManagerFactory;
import java.awt.Color;
import view.Home;

/**
 *
 * @author Fahrid
 */
public class config {
    private static final EntityManagerFactory emf = createEntityManagerFactory("pornhub");
    
    public static final String TITULO = "Gestion de Extensión Académica";
    
    public static final Color REDUV = new Color(195,22,26); // rojo univalluno

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
  
}
