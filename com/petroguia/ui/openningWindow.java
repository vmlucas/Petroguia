/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.ui;


import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.event.*;
import com.petroguia.business.*;
import java.util.*;


/**
 *
 * @author hb47537
 */
public class openningWindow extends JWindow{

    
    public openningWindow() {

        ImagePanel panel = new ImagePanel();

        this.setLayout(new BorderLayout());
        this.add(panel,BorderLayout.CENTER);
        
        this.setSize(new Dimension(549, 736));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        this.setLocation(x, y);

        //this.setBounds(0, 0, this.getWidth(), this.getHeight());


        this.setVisible(true);
    }


}

class ImagePanel extends JPanel{

    Image image;

    public ImagePanel( ){
        try{
           image = ImageIO.read(getClass().getResource("/images/capa_Petroguia.png"));

        }
        catch(Exception e){
           e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
