/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClipFrame.java
 *
 * Created on Jun 18, 2010, 2:34:47 PM
 */

package com.petroguia.ui;


import com.petroguia.model.*;
import java.awt.Color;
import javax.swing.*;
import com.petroguia.business.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.table.*;



/**
 * A frame do input new comments.
 *
 * @author hb47537
 */
public class CommentsListDialog extends javax.swing.JDialog {

    private ContentInterface model;
    private  UInterface userInterface;
    private LinkedList<Comment> comments;
    private JDialog frame = null;
    JPanel mainPanel = null;

    /** Creates new form ClipFrame */
    public CommentsListDialog( Page page, LinkedList<Comment> lista, UInterface ui, ContentInterface model, int x, int y) {
        super(ui.getMainWindow());

        this.model = model;
        this.userInterface = ui;
         Color backColor = new Color(252,216,18);
        try{
            JLabel name = new JLabel();
            name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            name.setText(page.getName());
           

            jScrollPane2 = new javax.swing.JScrollPane();
            jScrollPane2.setOpaque(true);
            jScrollPane2.getVerticalScrollBar().setBackground(backColor);
            /*UIManager.put("ScrollBar.thumb", backColor);
            UIManager.put("ScrollBar.thumbDarkShadow", backColor);
            UIManager.put("ScrollBar.thumbHighlight", backColor);
            UIManager.put("ScrollBar.thumbShadow", backColor);
            UIManager.put("ScrollBar.highlight", backColor);
            UIManager.put("ScrollBar.shadow", backColor);
            UIManager.put("ScrollBar.track", backColor);
            UIManager.put("ScrollBar.trackHighlight", backColor);*/

            jToggleButton3 = new javax.swing.JToggleButton();
            frame = this;

            initLayout(lista);
            jToggleButton3.setText("X");
            jToggleButton3.setBorder(null);
            jToggleButton3.setBorderPainted(false);
            jToggleButton3.setContentAreaFilled(false);
            jToggleButton3.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jToggleButton3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton3)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        }
        catch(Exception e){

        }
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(backColor);
        this.setUndecorated(true);
        setModal(true);
        setResizable(false);
        //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        ComponentMover cm = new ComponentMover(this, this);

        pack();
        this.setPreferredSize(new Dimension(400,250));
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        this.setVisible(true);
    }


    private void initLayout(LinkedList<Comment> lista)
       throws Exception
    {
        mainPanel = new JPanel();
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        final Color backColor = new Color(255,255,131);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backColor);
        String path = new java.io.File(".").getCanonicalPath();
        Iterator it = lista.iterator();

            while (it.hasNext()) {
                javax.swing.JScrollPane scroll = new javax.swing.JScrollPane();

                final JTextArea text = new JTextArea();
                final JToggleButton saveButton = new javax.swing.JToggleButton();

                JPanel comentPanel = new JPanel();
                comentPanel.setBackground(backColor);
                comentPanel.setLayout(new BoxLayout(comentPanel, BoxLayout.Y_AXIS));
                Comment temp = (Comment) it.next();
                final Comment finalComment = temp;
                JLabel label = new JLabel();
                label.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
                label.setForeground(Color.BLACK);
                label.setText(formato.format(temp.getTimeStamp()));

                JToggleButton editButton = new javax.swing.JToggleButton();
                editButton.setIcon(new ImageIcon( getClass().getResource("/images/edit.png")));
                editButton.setBorder(null);
                editButton.setContentAreaFilled(false);
                editButton.addActionListener(new java.awt.event.ActionListener() {

                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                      text.setEditable(true);
                      text.setBackground(Color.white);
                      text.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                      saveButton.setVisible(true);
                   }
                });

                JToggleButton delButton = new javax.swing.JToggleButton();
                delButton.setBorder(null);
                delButton.setContentAreaFilled(false);
                delButton.setIcon(new ImageIcon( getClass().getResource("/images/delete.png")));
                delButton.addActionListener(new java.awt.event.ActionListener() {

                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     try{
                       String returnText = model.deleteComment(finalComment);
                       LinkedList<Comment> list = userInterface.verifyComments();
                       //userInterface.closeCommentFrame();
                       if( list.isEmpty()){
                          frame.dispose();
                       }
                       else{
                         initLayout( list );
                       }
                       //JOptionPane.showMessageDialog(frame, returnText);

                       }
                     catch(Exception e){
                         e.printStackTrace();
                         JOptionPane.showMessageDialog(frame, e.getMessage());
                     }
                   }
                });

                saveButton.setBorder(null);
                saveButton.setContentAreaFilled(false);
                saveButton.setVisible(false);
                saveButton.setIcon(new ImageIcon( getClass().getResource("/images/save.png")));
                saveButton.addActionListener(new java.awt.event.ActionListener() {

                   public void actionPerformed(java.awt.event.ActionEvent evt) {
                     try{
                       finalComment.setText(text.getText());
                       String returnText = model.updateComment(finalComment);
                       text.setEditable(false);
                       saveButton.setVisible(false);
                       text.setBackground(backColor);
                       text.setBorder(null);
                       //initLayout( userInterface.verifyComments() );

                       //JOptionPane.showMessageDialog(frame, returnText);

                       }
                     catch(Exception e){
                         e.printStackTrace();
                         JOptionPane.showMessageDialog(frame, e.getMessage());
                     }
                   }
                });
               

                //text.setBackground(new java.awt.Color(246, 236, 132));
                text.setEditable(false);
                text.setBackground(backColor);
                text.setBorder(null);
                text.setText(temp.getText());
                text.setRows(3);

                scroll.setBorder(null);
                scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scroll.setViewportView(text);

                JPanel tpanel = new JPanel();
                tpanel.setBackground(backColor);
                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(tpanel);
                tpanel.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                  jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton))
            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
               );
                 jPanel2Layout.setVerticalGroup(
                  jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                     .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label)
                    .addComponent(editButton)
                    .addComponent(delButton)
                    .addComponent(saveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
                
                comentPanel.add(tpanel);

                mainPanel.add(comentPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(5,0)));

            }

            jScrollPane2.setViewportView(mainPanel);
    }
   

    
  /*  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton3 = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton5 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton6 = new javax.swing.JToggleButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton7 = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton8 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(252, 216, 18));
        setUndecorated(true);

        jToggleButton3.setText("X");
        jToggleButton3.setBorder(null);
        jToggleButton3.setBorderPainted(false);
        jToggleButton3.setContentAreaFilled(false);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setMaximumSize(new java.awt.Dimension(418, 147));
        jPanel1.setMinimumSize(new java.awt.Dimension(418, 147));

        jPanel2.setMaximumSize(new java.awt.Dimension(418, 147));
        jPanel2.setMinimumSize(new java.awt.Dimension(418, 147));

        jToggleButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\hb47537\\Documents\\Development\\Petroguia\\images\\edit.png")); // NOI18N
        jToggleButton5.setBorder(null);
        jToggleButton5.setBorderPainted(false);
        jToggleButton5.setContentAreaFilled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("01/01/2010");

        jToggleButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\hb47537\\Documents\\Development\\Petroguia\\images\\delete.png")); // NOI18N
        jToggleButton6.setBorder(null);
        jToggleButton6.setBorderPainted(false);
        jToggleButton6.setContentAreaFilled(false);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("nonononononoonon\nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn.....\nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnlllllllllllllllllllllllllllllllllllllllllll");
        jTextArea2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton6))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jToggleButton5)
                    .addComponent(jToggleButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setMaximumSize(new java.awt.Dimension(418, 147));
        jPanel3.setMinimumSize(new java.awt.Dimension(418, 147));

        jToggleButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\hb47537\\Documents\\Development\\Petroguia\\images\\edit.png")); // NOI18N
        jToggleButton7.setBorder(null);
        jToggleButton7.setBorderPainted(false);
        jToggleButton7.setContentAreaFilled(false);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setText("01/01/2010");

        jToggleButton8.setIcon(new javax.swing.ImageIcon("C:\\Users\\hb47537\\Documents\\Development\\Petroguia\\images\\delete.png")); // NOI18N
        jToggleButton8.setBorder(null);
        jToggleButton8.setBorderPainted(false);
        jToggleButton8.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton8)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3)
            .addComponent(jToggleButton7)
            .addComponent(jToggleButton8)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("texto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        
        userInterface.verifyComments();
        userInterface.closeCommentFrame();
        this.dispose();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    // End of variables declaration//GEN-END:variables

    

}


