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
import java.net.*;

/**
 * A frame do input new comments.
 *
 * @author hb47537
 */
public class CommentDialog extends javax.swing.JDialog {

    private ContentInterface model;
    private  UInterface userInterface;
    private Page currentPage;
        

    /** Creates new form ClipFrame */
    public CommentDialog( Page page, UInterface ui,ContentInterface model, int x, int y, boolean saveFlag) {
        super(ui.getMainWindow());

        this.model = model;
        this.userInterface = ui;
        currentPage = page;
        JLabel name = new JLabel();
            name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
            name.setText(page.getName());
        
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new java.awt.Color(252,216,18));
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(5);
        jTextArea1.setRows(5);
        jTextArea1.setEditable(saveFlag);
        
        jScrollPane1.setViewportView(jTextArea1);

        try{
           String path = new java.io.File(".").getCanonicalPath();
           jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N

        }
        catch(Exception e){

        }
        
        jToggleButton2.setBorder(null);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.setVisible(saveFlag);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        //jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trash.png"))); // NOI18N
        jToggleButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jToggleButton3.setForeground(Color.BLACK);
        jToggleButton3.setText("x");
        jToggleButton3.setBorder(null);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

       //this.jTextArea1.setText(text);

        //this.add(jTextArea1);
        setModal(true);
        setResizable(false);
        this.setUndecorated(true);
        //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        ComponentMover cm = new ComponentMover(this, this);

        pack();
        this.setBounds(x, y, this.getWidth(), this.getHeight());
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  /*  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(252, 216, 18));
        setUndecorated(true);

        jTextArea1.setBackground(new java.awt.Color(255, 255, 131));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jToggleButton2.setBorder(null);
        jToggleButton2.setBorderPainted(false);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trash.png"))); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        
        //userInterface.verifyComments();
        //userInterface.closeCommentFrame();
        this.dispose();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed

        String text = jTextArea1.getText();
        if(( text != null)&&(!text.equals(""))){            
            
            try{
                
                    Comment tempComment = new Comment();
                    tempComment.setText(text);
                    tempComment.setChapterID(userInterface.getChapter().getId());
                    tempComment.setBeginPos(0);
                    tempComment.setEndpos(0);
                    tempComment.setPage(currentPage);
                    tempComment.setTimeStamp(new java.util.Date(System.currentTimeMillis()));

                    String returnText = model.saveComment(tempComment);
                    java.util.LinkedList<Comment> list = userInterface.verifyComments();
                    userInterface.displayCommentCountButton(String.valueOf(list.size()));
                   // userInterface.closeCommentFrame();
                    userInterface.validate();
                    this.dispose();

              //JOptionPane.showMessageDialog(this, returnText);
            }
            catch(Exception e){
              JOptionPane.showMessageDialog(this, "Erro ao tentar salvar o comentário!");
              e.printStackTrace();
            }
        }
        else{
           JOptionPane.showMessageDialog(this, "Entre com um comentário antes!");
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables

    public JTextArea getjTextArea1() {
        return jTextArea1;
    }

}
