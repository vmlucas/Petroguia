/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.petroguia.ui.*;

import javax.swing.*;
/**
 *
 * @author hb47537
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try{
           UIManager.setLookAndFeel(new com.incors.plaf.kunststoff.KunststoffLookAndFeel());
                //new login().setVisible(true);
           openningWindow open = new openningWindow();
           Thread.sleep(3000);
           open.dispose();
           new MainFrame("VICTOR");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    

}
