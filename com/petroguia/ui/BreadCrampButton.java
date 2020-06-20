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
public class BreadCrampButton extends JToggleButton {

    private final UInterface ui;
    private final Page page;
    private String text;
    private Image image;

    public BreadCrampButton(UInterface tempUI, Image img, Page paramPage) {
        //super("", icon);
        image = img;
        this.ui = tempUI;
        this.page = paramPage;
        setBorder(null);

        if (page != null) {
            text = page.getName();
            int width = this.getFontMetrics(new java.awt.Font("Arial", Font.BOLD, 13)).stringWidth(text);
            this.setPreferredSize(new Dimension(width,20));
            //setBorder(null);
            //setContentAreaFilled(false);
            //this.setBackground(new Color(241, 243, 240));
            //this.setHorizontalTextPosition(SwingConstants.RIGHT);

            addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        ui.getBradCramp().removePage(page);
                        ui.setCurrentPage(page);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "Error following link", "Link invalido",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } else {
            text = null;
            this.setPreferredSize(new Dimension(12,20));
            //setBorder(null);
            //setContentAreaFilled(false);
            //this.setBackground(new Color(255, 255, 255));
        }
        repaint();
        //this.setVisible(true);
    }

    public BreadCrampButton(Image img, String texto) {
        //super("", icon);
        ui = null;
        page = null;
        image = img;
         setBorder(null);

        if (texto != null) {
            text = texto;
            int width = this.getFontMetrics(new java.awt.Font("Arial", Font.BOLD, 13)).stringWidth(text);
            this.setPreferredSize(new Dimension(width,20));
            //setContentAreaFilled(false);
            //this.setBackground(new Color(241, 243, 240));
            //this.setHorizontalTextPosition(SwingConstants.RIGHT);

        } else {
            text = null;
            this.setPreferredSize(new Dimension(12,20));
            //setContentAreaFilled(false);
            //this.setBackground(new Color(255, 255, 255));
        }
       // this.setVisible(true);
    }

    public Page getPage() {
        return page;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (text != null) {
            int w = this.getWidth();
            int h = 20;
            g.setColor(new Color(241, 243, 240));
            g.fillRect(0, 0, w, h);
            g.setFont(new java.awt.Font("Arial", Font.BOLD, 13));
            g.setColor(new Color(102,102,102));
            g.drawString(text, (w - g.getFontMetrics().stringWidth(text)) / 2 + 1, (h + g.getFontMetrics().getAscent()) / 2 - 1);

        } else {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0,12, 20);
            g.drawImage(image, 0, 0, null);
        }
    }
}
