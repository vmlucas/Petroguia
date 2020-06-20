/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainFrame.java
 *
 * Created on Jun 17, 2010, 11:11:15 AM
 */
package com.petroguia.ui;

import com.petroguia.model.*;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.event.*;
import java.util.*;


/**
 * Class designed to be the main project window. It implements some global buttons and a 
 * custom tabbedpane. This tabbedpane has a list of myPanel objects, that represents
 * each tab.
 *
 * @author hb47537
 */
public class MainFrame extends javax.swing.JFrame implements UInterface {

    public static String USER;
    private JTabbedPane jTabbedPane1;
    private BackgroundImagePanel backPanel;
    private RightPanel rightPanel;//jPanel4
    private ContentInterface model;
    

    /** Creates new form mainFrame */
    public MainFrame(String user) {

        USER = user;
        try{
        
        //defines the back ground image.
        BackGround backGround = new BackGround();
        //defines the left image, the one with notebook clip
        backPanel = new BackgroundImagePanel();
        //defines the right image, the one with pages in diferent levels.
        rightPanel = new RightPanel();

        jPanel6 = new javax.swing.JPanel();
        
        //configuring the tabbedpane for the chapters, represented by myPanel objects.
        jTabbedPane1 = new javax.swing.JTabbedPane();
        loadFiles();

        //customizing the tabbedpane.
        //customizing the selected and unselected tab color and the tabbedpane format.
        jTabbedPane1.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, -5, -5, -5));
        jTabbedPane1.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, h, h);
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected){
                
                if( isSelected){
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, w, h);
                }
                else{
                    g.setColor(new Color(230,230,230));
                    g.fillRect(x, y, w, h);
                }

            }
        });  

        UIManager.put("TabbedPane.selected", Color.white);
        //UIManager.put("TabbedPane.contentAreaColor ",Color.WHITE);
        // UIManager.put("TabbedPane.tabInsets", new Insets(0, 0, 0, 0));
                                        
        //configuring the frame
        buscaTF = new javax.swing.JTextField();
        buscaTF.setBorder(null);

        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        backPanel = new BackgroundImagePanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        JToggleButton minButton = new JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();

        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/br_logo.png"))); // NOI18N
        jToggleButton7.setBorder(null);
        jToggleButton7.setBorderPainted(false);
        jToggleButton7.setContentAreaFilled(false);

        jToggleButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unconnected.png"))); // NOI18N
        jToggleButton9.setBorder(null);
        jToggleButton9.setBorderPainted(false);
        jToggleButton9.setContentAreaFilled(false);

        jPanel6.setBackground(new java.awt.Color(56, 123, 79));
        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 695, Short.MAX_VALUE)
                .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
            
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(639, Short.MAX_VALUE))
            
        );

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        //this.getContentPane().setBackground(new java.awt.Color(67, 148, 95));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Busca");
        jLabel1.setBounds(22, 3, 44, 19);

        jToggleButton1.setBackground(new java.awt.Color(51, 102, 0));
        jToggleButton1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(245, 248, 245));
        jToggleButton1.setIcon(new javax.swing.ImageIcon( getClass().getResource("/images/notes.png"))); // NOI18N
        jToggleButton1.setText("Anotações");
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jToggleButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(245, 248, 245));
        jToggleButton2.setIcon(new javax.swing.ImageIcon( getClass().getResource("/images/favorites.png"))); // NOI18N
        jToggleButton2.setText("Favoritos");
        jToggleButton2.setBorder(null);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(245, 248, 245));
        jToggleButton3.setIcon(new javax.swing.ImageIcon( getClass().getResource("/images/conversion.png"))); // NOI18N
        jToggleButton3.setText("Conversão");
        jToggleButton3.setAlignmentY(0.0F);
        jToggleButton3.setBorder(null);
        jToggleButton3.setContentAreaFilled(false);
        jToggleButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(245, 248, 245));
        jToggleButton4.setIcon(new javax.swing.ImageIcon( getClass().getResource("/images/calculator.png"))); // NOI18N
        jToggleButton4.setText("Fórmulas");
        jToggleButton4.setAlignmentY(0.0F);
        jToggleButton4.setBorder(null);
        jToggleButton4.setContentAreaFilled(false);
        jToggleButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 102, 51));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(245, 248, 245));
        jLabel2.setText("e-Petroguia");

        jToggleButton5.setIcon(new javax.swing.ImageIcon( getClass().getResource("/images/close.png")));
        jToggleButton5.setBorder(null);
        jToggleButton5.setContentAreaFilled(false);
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        minButton.setBackground(new java.awt.Color(64, 147, 91));
        minButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        minButton.setText("_");
        minButton.setForeground(new java.awt.Color(245, 248, 245));
        minButton.setBorder(null);
        minButton.setContentAreaFilled(true);
        minButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setBackground(new java.awt.Color(52, 117, 75));
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 888, Short.MAX_VALUE)
                .addComponent(minButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToggleButton6.setIcon(new javax.swing.ImageIcon( getClass().getResource("/images/search.png"))); // NOI18N
        jToggleButton6.setBorder(null);
        jToggleButton6.setContentAreaFilled(false);
        jToggleButton6.setFocusable(true);
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        backPanel.setAlignmentX(0.0F);
        backPanel.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(backPanel);
        backPanel.setLayout(jPanel2Layout);
        backPanel.setBorder(null);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 47, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 662, Short.MAX_VALUE));

        jToggleButton8.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jToggleButton8.setForeground(new java.awt.Color(245, 248, 245));
        jToggleButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/marks.png"))); // NOI18N
        jToggleButton8.setText("Marcações");
        jToggleButton8.setAlignmentY(0.0F);
        jToggleButton8.setBorder(null);
        jToggleButton8.setContentAreaFilled(false);
        jToggleButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(245, 248, 245));
        jLabel1.setText("Busca");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        getContentPane().add(backGround, BorderLayout.CENTER);
        //javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        //getContentPane().setLayout(layout);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(backGround);
        backGround.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50,50,50)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        ))
                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(backPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))                    
                    .addComponent(rightPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        }
        catch(Exception e){
          e.printStackTrace();
        }

        //define the moviment of a bordless frame
        ComponentMover cm = new ComponentMover(this, jPanel1);

        getContentPane().setSize(1040, 780);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getContentPane().getSize().width;
        int h = getContentPane().getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        //this.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setUndecorated(true);
        //this.setResizable(true);
        pack();
        this.setEnabled(true);
        this.setLocation(x, y);
        this.setVisible(true);

    }

    private void loadFiles() {

        try {
            int cont = 0;
            ContentInterface model = ModelAcess.buildContentManager();
            Iterator it = model.fetchChapters().iterator();
            while (it.hasNext()) {
                Chapter chapter = (Chapter) it.next();

                myPanel panel = new myPanel(chapter, this, model);

                TabButton button = new TabButton(cont, jTabbedPane1, chapter.getId());
                jTabbedPane1.add(panel);
                jTabbedPane1.setTabComponentAt(cont, button);



                cont++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar os capítulos!");
        }
        /*Modelo modelB = new Modelo("B");
        panelA = new myPanel(modelA, r.x, r.y);
        panelB = new myPanel(modelB, r.x, r.y);

        jTabbedPane1.addTab("A", panelA);
        jTabbedPane1.addTab("B", panelB);*/

    }

    @Override
    public int getMainWindowHeight() {
        return getContentPane().getSize().height;

    }

    @Override
    public int getMainWindowWidth() {
        return getContentPane().getSize().width;
    }

    @Override
    public JFrame getMainWindow() {
        return this;
    }

    @Override
    public void closeCommentFrame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BreadCramp getBradCramp() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void displayCommentCountButton(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void hideCommentCountButton() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    @Override
    public Page getCurrentPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Chapter getChapter() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCurrentPage(Page page) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBookmarked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUnbookmarked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<Comment> verifyComments() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        buscaTF = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton8 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 0));
        setMinimumSize(new java.awt.Dimension(500, 400));
        setUndecorated(true);

        jToggleButton1.setBackground(new java.awt.Color(51, 102, 0));
        jToggleButton1.setFont(new java.awt.Font("Arial", 1, 16));
        jToggleButton1.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clip.png"))); // NOI18N
        jToggleButton1.setText("Anotações");
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jToggleButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jToggleButton2.setFont(new java.awt.Font("Arial", 1, 16));
        jToggleButton2.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/starGreen.png"))); // NOI18N
        jToggleButton2.setText("Favoritos");
        jToggleButton2.setBorder(null);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setFont(new java.awt.Font("Arial", 1, 16));
        jToggleButton3.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/conv.png"))); // NOI18N
        jToggleButton3.setText("Conversão");
        jToggleButton3.setAlignmentY(0.0F);
        jToggleButton3.setBorder(null);
        jToggleButton3.setContentAreaFilled(false);
        jToggleButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jToggleButton4.setFont(new java.awt.Font("Arial", 1, 16));
        jToggleButton4.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/formula.png"))); // NOI18N
        jToggleButton4.setText("Fórmulas");
        jToggleButton4.setAlignmentY(0.0F);
        jToggleButton4.setBorder(null);
        jToggleButton4.setContentAreaFilled(false);
        jToggleButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTabbedPane2.setAlignmentX(0.0F);
        jTabbedPane2.setAlignmentY(0.0F);
        jTabbedPane2.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        jTabbedPane2.setFocusable(false);

        jLabel2.setBackground(new java.awt.Color(0, 102, 51));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel2.setForeground(new java.awt.Color(200, 200, 200));
        jLabel2.setText("e-Petroguia");

        jToggleButton5.setFont(new java.awt.Font("Arial", 1, 11));
        jToggleButton5.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton5.setText("X");
        jToggleButton5.setBorder(null);
        jToggleButton5.setContentAreaFilled(false);
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 916, Short.MAX_VALUE)
                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToggleButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jToggleButton6.setBorder(null);
        jToggleButton6.setContentAreaFilled(false);

        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setPreferredSize(new java.awt.Dimension(42, 662));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        jToggleButton8.setFont(new java.awt.Font("Arial", 1, 16));
        jToggleButton8.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pencilGreen.png"))); // NOI18N
        jToggleButton8.setText("Marcações");
        jToggleButton8.setAlignmentY(0.0F);
        jToggleButton8.setBorder(null);
        jToggleButton8.setContentAreaFilled(false);
        jToggleButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16));
        jLabel1.setForeground(new java.awt.Color(200, 200, 200));
        jLabel1.setText("Busca");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setPreferredSize(new java.awt.Dimension(42, 662));

        jPanel5.setAlignmentX(0.0F);
        jPanel5.setAlignmentY(0.0F);
        jPanel5.setPreferredSize(new java.awt.Dimension(42, 662));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(640, 640, 640)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setAlignmentX(0.0F);
        jPanel6.setAlignmentY(0.0F);
        jPanel6.setPreferredSize(new java.awt.Dimension(42, 662));

        jPanel7.setAlignmentX(0.0F);
        jPanel7.setAlignmentY(0.0F);
        jPanel7.setPreferredSize(new java.awt.Dimension(42, 662));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jToggleButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/br_logo.png"))); // NOI18N
        jToggleButton7.setBorder(null);
        jToggleButton7.setBorderPainted(false);
        jToggleButton7.setContentAreaFilled(false);

        jToggleButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/unconnected.png"))); // NOI18N
        jToggleButton9.setBorder(null);
        jToggleButton9.setBorderPainted(false);
        jToggleButton9.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 695, Short.MAX_VALUE)
                .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(902, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(639, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(640, 640, 640)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jToggleButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        
        int index = jTabbedPane1.getSelectedIndex();
        myPanel panel = (myPanel)jTabbedPane1.getComponentAt(index);
        panel.fetchComments();
    }


    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {
         new ConvertDialog(  this );
    }

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {
         new FormulaDialog( this,null );
    }


    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        this.dispose();
      /*try{
        ModelAcess.closeDB();
        }
      catch(Exception e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(this, "Erro ao fechar o banco de dados!");
      }*/
        System.exit(0);
    }//GEN-LAST:event_jToggleButton5ActionPerformed


    private void minButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //this.minimumSize();
        this.setExtendedState(JFrame.ICONIFIED);
    }


    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed

        int index = jTabbedPane1.getSelectedIndex();
        myPanel panel = (myPanel)jTabbedPane1.getComponentAt(index);
        panel.fetchMarks();
        

    }//GEN-LAST:event_jToggleButton8ActionPerformed


    private void searchActionPerformed(java.awt.event.ActionEvent evt) {

        int index = jTabbedPane1.getSelectedIndex();
        myPanel panel = (myPanel)jTabbedPane1.getComponentAt(index);

        String text = buscaTF.getText();
        if( text != null){
            if( text.length() >2 ){
              panel.search(buscaTF.getText());
            }
            else{
              JOptionPane.showMessageDialog (this, "Entre com mais de 2 caracteres");
            }
        }
        else{
           JOptionPane.showMessageDialog (this, "Entre a palavra a ser buscada");
        }


    }


    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        
        int index = jTabbedPane1.getSelectedIndex();
        myPanel panel = (myPanel)jTabbedPane1.getComponentAt(index);
        panel.fetchFavorites();
        

        
    }//GEN-LAST:event_jToggleButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscaTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    // End of variables declaration//GEN-END:variables
}

class RightPanel extends JPanel {

    public Image backgroundImage;

    public RightPanel() {

        try {
            this.backgroundImage = ImageIO.read(getClass().getResource("/images/page_end.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setSize(13, 662);
    }

    protected void paintComponent(Graphics g) {
        int h = 27;
        while( h < 646){
           g.drawImage(backgroundImage, 0, h, null);
           h = h + 17;
        }
    }
}


class BackgroundImagePanel extends JPanel {

    public Image backgroundImage;

    public BackgroundImagePanel() {
        try {
            this.backgroundImage = ImageIO.read( getClass().getResource("/images/page_clip.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setSize(55, 662);
    }

    protected void paintComponent(Graphics g) {
        int h = 28;
        g.setColor(Color.WHITE);
        g.fillRect(28, h, 55, 660);

        while( h < 646){
           g.drawImage(backgroundImage, 0, h, null);
           h = h + 25;
        }
    }
}

class TabButton extends JPanel {

    private final JTabbedPane pane;
    private final int index;
    public Image backgroundImage;

    public TabButton(int index1, JTabbedPane pane1, String title) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.pane = pane1;
        index = index1;
        try {
            this.backgroundImage = ImageIO.read( getClass().getResource("/images/tab_selected.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
            //setContentAreaFilled(false);
            setOpaque(false);
            this.setBackground(new Color(255, 255, 255));
            this.setPreferredSize(new Dimension(20,20));
            setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

            JButton button = new JButton( title );
            button.setForeground(new java.awt.Color(67, 148, 95));
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setPreferredSize(new Dimension(15, 15));
            button.setToolTipText("Seção "+title);
            
            //Make it transparent
            button.setContentAreaFilled(false);
            //No need to be focusable
            button.setFocusable(false);
            button.setBorder(BorderFactory.createEtchedBorder());
            button.setBorderPainted(false);


            button.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                           pane.setSelectedIndex(index);
                           myPanel panel = (myPanel)pane.getComponentAt(index);

                           panel.initEditor(null);

                        }
                    catch (Exception e) {
                       e.printStackTrace();
                       JOptionPane.showMessageDialog(pane,
                              "Erro", "Erro no botão da TabbedPane",
                                   JOptionPane.ERROR_MESSAGE);

                    }

                }
            });
           add(button);
           this.setVisible(true);
    }

}

class BackGround extends JPanel{

    Image image;

    public BackGround( ){
        try{
           image = ImageIO.read(getClass().getResource("/images/body_bg.png"));

        }
        catch(Exception e){
          e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        int x = 0;
        while (x < 1065) {
            g.drawImage(image, x, 20, null);
            x = x + 6;
        }
    }
}