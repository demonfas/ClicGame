/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicgame;

import static clicgame.Conexion.con;
import static clicgame.Game.lbl_amount;
import static clicgame.Game.lbl_delay;
import static clicgame.Game.lbl_puntaje;
import static clicgame.Game.lbl_time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static clicgame.Game.start;
import static clicgame.Settings.Amount;
import static clicgame.Settings.Puntaje;
import static clicgame.Settings.txt_amount;

/**
 *
 * @author Demon
 */
public class Login {
    public static boolean logged;
    public void login(String useridd, String passwordd){
         try{
            Statement snt = con.createStatement();
            ResultSet rs = snt.executeQuery("SELECT * FROM users where userid='" + useridd + "' and password='" + passwordd + "'");
             if (rs.next() && !logged) {
                String st = "Logged!";
                System.out.println(st);
                logged = true;
             }
             else if(logged){
                String st = "Estás actualmente logeado!";
                System.out.println(st);
             }
            else 
            {
                String st = "username and password are wrong";
                System.out.println(st);
            }
        }
        catch (SQLException e){
            System.out.println("Error de conexion" + e);
        }
    }
    public void create(String useridd, String passwordd){
        if (useridd.equals("")) {
            String st = "Por favor llene el campo de userid";
            System.out.println(st);
            return;
        }
        if (passwordd.equals("")) {
            String st = "Por favor llene el campo de password";
            System.out.println(st);
            return;
        }
        try{
            Statement snt = con.createStatement();
            ResultSet rs = snt.executeQuery("SELECT * FROM users where userid='" + useridd + "'");
            if (rs.next()) {
                String st = "Existe otra cuenta con este userid";
                System.out.println(st);
            }
            else 
            {
                try{
                    PreparedStatement consulta;
                    consulta = con.prepareStatement("INSERT INTO users (userid, password) VALUES (?, ?)");
                    consulta.setString(1, useridd);
                    consulta.setString(2, passwordd);
                    String st = "Cuenta creada!";
                    System.out.println(st);
                    consulta.executeUpdate();
                }
                catch (SQLException e){
                    String st = "Error de conexion " + e;
                    System.out.println(st);
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error de conexion " + e);
        }
    }
    public void savegame(int puntaje, String useridd ){
        Pointer ptr = new Pointer();
        try{
                    PreparedStatement consulta;
                    consulta = con.prepareStatement("UPDATE users SET puntaje=? WHERE userid=?");
                    consulta.setInt(1, puntaje);
                    consulta.setString(2, useridd);
                    String st = "Puntaje actualizado!";
                    System.out.println(st);
                    consulta.executeUpdate();
                    int selection = JOptionPane.showOptionDialog( null,"¿Desea jugar otra vez?",
                        "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
                        new Object[] { "Si", "No"},"Si");
                    if (selection == 0) {
                        start = true;
                        ptr.setFrame(lbl_amount, lbl_time, lbl_delay, lbl_puntaje);
                        Amount = Integer.parseInt(txt_amount.getText());
                        Puntaje = 0;
                    } else if (selection == 1 || selection == -1) {
                        System.exit(0);
                    }
                }
                catch (SQLException e){
                    String st = "Error de conexion " + e;
                    System.out.println(st);
                }
    }
}
