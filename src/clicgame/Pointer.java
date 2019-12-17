/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clicgame;
import static clicgame.Game.start;
import static clicgame.Game.delay;
import static clicgame.Settings.Amount;
import static clicgame.Settings.Delay;
import static clicgame.Settings.Puntaje;
import static clicgame.Settings.Time;
import static clicgame.Game.lbl_amount;
import static clicgame.Game.lbl_puntaje;
import static clicgame.Game.lbl_time;
import static clicgame.Game.lbl_delay;
import static clicgame.Game.lbl_pointer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Demon
 */
public class Pointer extends JLabel {
    //Game gm = new Game();
    //public JLabel lbl_amount, lbl_time, lbl_delay, lbl_puntaje, lbl_pointer;
    public JLabel puntero(JLabel lbl_pointer){
        String path = "/Img/point.png";  
        URL url = this.getClass().getResource(path);  
        ImageIcon icon = new ImageIcon(url);  
        lbl_pointer.setBounds(10, 10, 10, 10);
        lbl_pointer.setIcon(icon);
        lbl_pointer.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
        }
        
        @Override
        public void mouseEntered(MouseEvent arg0) {/*vacio*/}

        @Override
        public void mouseExited(MouseEvent arg0) {/*vacio*/}
        @Override
        public void mousePressed(MouseEvent arg0) {
            if (start) {
                Puntaje = Puntaje + 1;
                System.out.println("La etiqueta ha sido clickeada: " /*+ Puntaje*/);
                //updateLocation();
                updateLocation(lbl_pointer);
                cleanPoint(lbl_pointer);
                setFrame(lbl_amount, lbl_time, lbl_delay, lbl_puntaje);
                delay = true;
            }
        }
        @Override
        public void mouseReleased(MouseEvent arg0) {/*vacio*/}
       });
        add( lbl_pointer ); 
        //setFrame();
        return lbl_pointer;
    }
    public long convertTime(double time){
        double d = time * 1000;
        long v = (long) d;
        long vd = v;
        return vd;
    }
    public void setFrame(JLabel a,JLabel t,JLabel d,JLabel p){
        a.setText("Amount: " + Amount);
        t.setText("Time: " + Time);
        d.setText("Delay: " + Delay);
        p.setText("Puntaje: " + Puntaje);
    }
    public void updateLabel(JLabel a, JLabel p){
                    lbl_amount.setText("Amount: " + Integer.toString(Amount));
                    lbl_puntaje.setText("Puntaje: " + Integer.toString(Puntaje));
                    Amount = Amount - 1;
                    lbl_pointer.setVisible(true);
    }
    public void updateLocation(JLabel j){
                    Random rand = new Random(); 
                    int x = rand.nextInt(370); 
                    int y = rand.nextInt(145); 
                    j.setLocation(x, y);
                    
    }
    public void cleanPoint(JLabel j){
        j.setVisible(false);
    }
}
