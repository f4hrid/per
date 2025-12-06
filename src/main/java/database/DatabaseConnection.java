/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Fahrid
 */
public class DatabaseConnection {
    public Connection conexion;
    public static String user = "admin";
    public static String pass = "123admin456pro";
    public static String db = "bd_gea";
    public static String host = "localhost";
    public static String port = "3306";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://"+host+":"+port+"/"+db+"?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    public Connection connection(){
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Conexión fallida");
        }
        return conexion;
    }
    
}
