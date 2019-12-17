/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**
 *
 * @author Demon
 */
public class Conexion {
    public static Connection con;
    public static final String driver="com.mysql.jdbc.Driver";
    public static final String user="root";
    public static final String pass="";
    public static final String url="jdbc:mysql://localhost:3306/ClicGame";
    public static boolean ldng=true;
    public void conector() {
        // Reseteamos a null la conexion a la bd
        con=null;
        
        try{
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){
                String st = "Conexión establecida con el coordinador de juego";
                //JOptionPane.showMessageDialog(null, st);
                System.out.println(st);
                ldng=false;
            }
            
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            
                String st = "Conexión no establecida con el coordinador de juego";
                JOptionPane.showMessageDialog(null, st);
                ldng=true;
        }
    }
}


    
