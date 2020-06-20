/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petroguia.ui;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.media.*;
//import com.sun.media.util.JMFSecurity;

/**
 *
 * @author hb47537
 */
public class MediaPlayer extends javax.swing.JDialog {

    private MediaLocator mediaLoc;
    public static Player player = null;
    private Component viewPanel, controlPanel;
    private JPanel panel;
    UInterface ui;

    /**
     * Read the file parameter and create the media
     * player.
     */
    public MediaPlayer(UInterface temp, java.net.URL url) {
        this.ui = temp;
        panel  = new JPanel();
        panel.setLayout( new BorderLayout() );
	add(panel);
	
        mediaLoc = new MediaLocator(url);

        try {
            player = Manager.createRealizedPlayer(mediaLoc);
            player.addControllerListener(new MovieHandler());
            }
        catch(NoPlayerException e1) {

                e1.printStackTrace();
            }
        catch(IOException e2) {
            e2.printStackTrace();
            }
        catch(CannotRealizeException e3) {
                e3.printStackTrace();
            }

        start();

    }

    private void initMediaComp(JPanel pane) { 
        viewPanel = player.getVisualComponent();
        if (viewPanel != null)
           pane.add(viewPanel, BorderLayout.CENTER);

        controlPanel = player.getControlPanelComponent();

        if (controlPanel != null)
            pane.add(controlPanel, BorderLayout.SOUTH);

        int w = viewPanel.getSize().width + controlPanel.getSize().width;
        int h = viewPanel.getSize().height + controlPanel.getSize().height;
        int x = (ui.getMainWindowWidth()-w)/2;
        int y = (ui.getMainWindowHeight()-h)/2;

        this.pack();
        this.setSize(new Dimension(w,h));
        this.setVisible(true);
    }

    public void start() {
        player.start();
        }

    public void startSequence(float bTime, float eTime) {
        player.stop();
        player.setMediaTime(new Time(bTime));
        player.setStopTime(new Time(eTime));
        player.start();
        }



    class MovieHandler extends ControllerAdapter {

        public void realizeComplete(RealizeCompleteEvent e) {
            System.out.println("Player Realized");
            player.prefetch();
        }

        public void prefetchComplete(PrefetchCompleteEvent e) {
            System.out.println("Player Prefetched");
            initMediaComp(panel);
        }

    }
}
