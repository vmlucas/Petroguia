/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petroguia.ui;

import com.petroguia.model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import com.petroguia.business.*;

/**
 *
 * @author hb47537
 */
class BreadCramp extends JPanel {

    private Vector<BreadCrampButton> buttons;
    private final UInterface userInterface;
    FlowLayout layout;
    private int x;
    int width = 421;
    int height = 25;
    

    public BreadCramp(UInterface ui) {
        buttons = new Vector<BreadCrampButton>();
        this.userInterface = ui;
        layout = new FlowLayout();
        layout.setHgap(0);
        this.setLayout(layout);
        this.setBackground(Color.white);


    }

    public void addPage(String text)
       throws Exception
    {
        if (buttons.isEmpty()) {

            BreadCrampButton button1 =
                    new BreadCrampButton(
                    ImageIO.read(getClass().getResource("/images/begin.gif")),
                    null);
            buttons.add(button1);

            BreadCrampButton button =
                    new BreadCrampButton(
                    null,
                    text);

            buttons.add(button);
            BreadCrampButton button2 =
                    new BreadCrampButton(
                    ImageIO.read(getClass().getResource("/images/end.gif")),
                    null);
            buttons.add(button2);

        } else {
            BreadCrampButton button1 =
                    new BreadCrampButton(
                    ImageIO.read(getClass().getResource("/images/middle.gif")),
                    null);

            BreadCrampButton button2 =
                    new BreadCrampButton(
                    null,
                    text);

            buttons.insertElementAt(button2, buttons.size()-1);
            buttons.insertElementAt(button1, buttons.size()-2);

        }
        this.removeAll();
        for(int i=0;i<buttons.size();i++){
            add(buttons.elementAt(i));
        }

    }


    public void addPage(Page page)
       throws Exception
    {

        if (buttons.isEmpty()) {

            BreadCrampButton button1 =
                    new BreadCrampButton(userInterface,
                    ImageIO.read(getClass().getResource("/images/begin.gif")),
                    null);
            buttons.add(button1);

            BreadCrampButton button2 =
                    new BreadCrampButton(userInterface,
                    null,
                    page);
            
            buttons.add(button2);

            BreadCrampButton button3 =
                    new BreadCrampButton(userInterface,
                    ImageIO.read(getClass().getResource("/images/end.gif")),
                    null);
            buttons.add(button3);
            
        } else {
            
                BreadCrampButton button1 =
                    new BreadCrampButton(userInterface,
                    ImageIO.read(getClass().getResource("/images/middle.gif")),
                    null);

               
            
            BreadCrampButton button2 =
                    new BreadCrampButton(userInterface,
                    null,
                    page);

            buttons.insertElementAt(button2, buttons.size()-1);
             buttons.insertElementAt(button1, buttons.size()-2);
            

        }
        this.removeAll();
        for(int i=0;i<buttons.size();i++){
            add(buttons.elementAt(i));
        }


    }

    public void removePage(Page page)
       throws Exception
    {
        int pos=-1;
        for (int i = 0; i < buttons.size()-1; i++) {
            BreadCrampButton button = buttons.elementAt(i);

            Page temp = button.getPage();
            if( temp != null ){
              if (temp.getID() == page.getID()) {
                pos = i;
              }
            }
        }
        if(pos == -1){
            pos = 1;
        }

        buttons = new Vector(buttons.subList(0, pos-1));
        BreadCrampButton button2 =
                    new BreadCrampButton(userInterface,
                    ImageIO.read(getClass().getResource("/images/end.gif")),
                    null);
        buttons.add(button2);
        
    }

    public void removeAllPages(){
        buttons.removeAllElements();
    }
}
