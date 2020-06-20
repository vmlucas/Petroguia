/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * myPanel.java
 *
 * Created on Jun 17, 2010, 11:13:27 AM
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;




//Write a inner class
/**
 *
 * @author hb47537
 */
public class myPanel extends javax.swing.JPanel implements UInterface{

    private Editor editor;
    private CommentDialog clip = null;
    private BreadCramp cramp;
    private myPanel panel;
    private CommentCountButton countButton;//jToggleButton4
    private UInterface ui;
    private ContentInterface model;
    private Chapter chapter;
    int x;
    int y;

    /** Creates new form myPanel */
    public myPanel( Chapter chapter, UInterface ui, ContentInterface modelo) {
        super(new GridLayout(1, 1));
        panel = this;
        this.ui = ui;
        this.model = modelo;
        this.chapter = chapter;

        this.setBackground(Color.WHITE);
        this.setBorder(null);
       
        
        //init panel conf
        jScrollPane1 = new javax.swing.JScrollPane();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        countButton = new CommentCountButton(this);

        cramp = new BreadCramp(this);
        jScrollPane1.setBorder(null);
        
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_notes_unselected.png"))); // NOI18N
        jToggleButton1.setToolTipText("Gravar comentário da página");
        jToggleButton1.setBorder(null);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_marks_unselected.png"))); // NOI18N
        jToggleButton2.setToolTipText("Habilitar marcação");
        jToggleButton2.setBorder(null);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_favorites_unselected.png"))); // NOI18N
        jToggleButton3.setToolTipText("Salvar como favorito");
        jToggleButton3.setBorder(null);
        jToggleButton3.setContentAreaFilled(false);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        /*javax.swing.GroupLayout panelTesteLayout = new javax.swing.GroupLayout(cramp);
        cramp.setLayout(panelTesteLayout);
        panelTesteLayout.setHorizontalGroup(
            panelTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        panelTesteLayout.setVerticalGroup(
            panelTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );*/

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cramp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(countButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cramp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );
        
       //end panel conf
        this.setPreferredSize(new Dimension(800, 600));
        //initComponents();

        this.x = 200;
        this.y = 200;

        try{
           initEditor(null);
        }
        catch(Exception e){
              JOptionPane.showMessageDialog(this, "Erro ao processar URL do editor!");
              e.printStackTrace();
        }
    }

    public void initEditor(Page page)
      throws Exception
    {
        getBradCramp().removeAllPages();
        validate();
        editor = new Editor(this, model);
        if( page == null){
           Page temp = new Page();
           temp.setName(chapter.getDescription());
           temp.setID(chapter.getId());
           temp.setFile_content("");
           temp.setType("HTML");
           temp.setVersion(1);
           temp.setUrl(model.loadIndice(chapter));
           setCurrentPage(temp);
        }
        else{
          setCurrentPage(page);
        }
        jScrollPane1.setViewportView(editor);
    }


   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        panelTeste = new javax.swing.JPanel();
        jToggleButton4 = new javax.swing.JToggleButton();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        jScrollPane1.setBorder(null);

        jEditorPane1.setBorder(null);
        jScrollPane1.setViewportView(jEditorPane1);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clip2.png"))); // NOI18N
        jToggleButton1.setBorder(null);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pencil2.png"))); // NOI18N
        jToggleButton2.setBorder(null);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/star2.png"))); // NOI18N
        jToggleButton3.setBorder(null);
        jToggleButton3.setContentAreaFilled(false);
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTesteLayout = new javax.swing.GroupLayout(panelTeste);
        panelTeste.setLayout(panelTesteLayout);
        panelTesteLayout.setHorizontalGroup(
            panelTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        panelTesteLayout.setVerticalGroup(
            panelTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jToggleButton4.setBackground(new java.awt.Color(255, 204, 0));
        jToggleButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(200, 200, 200));
        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clip.png"))); // NOI18N
        jToggleButton4.setText("0");
        jToggleButton4.setBorder(null);
        jToggleButton4.setOpaque(true);
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Chapter getChapter(){
        return chapter;
    }


    @Override
    public int getMainWindowHeight() {
        return ui.getMainWindowWidth();
    }

    @Override
    public int getMainWindowWidth() {
        return ui.getMainWindowHeight();
    }

    @Override
    public JFrame getMainWindow() {
        return ui.getMainWindow();
    }

    @Override
    public BreadCramp getBradCramp() {
        return cramp;
    }

    @Override
    public void displayCommentCountButton(String value) {
        this.countButton.setText(value);
        this.countButton.setVisible(true);
    }

    @Override
    public void hideCommentCountButton() {
        this.countButton.setVisible(false);
    }

    @Override
    public void setBookmarked() {
        editor.setBookMarked(true);
          jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_favorites_selected.png")));
        
    }

    @Override
    public void setUnbookmarked(){
           editor.setBookMarked(false);
          jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_favorites_unselected.png")));
          jToggleButton3.setEnabled(true);
        
    }

    /*@Override
    public void processURL(Page page)
       throws Exception
    {
        editor.processURL(page);
    }*/


    @Override
    public Page getCurrentPage(){
       return editor.getCurrentPage();
    }

    @Override
    public void setCurrentPage(Page page)
      throws Exception
    {
       editor.processURL(page);
    }

    @Override
    public LinkedList<Comment> verifyComments(){
      editor.verifyComments();
      return editor.getComments();
    }


    @Override
    public void closeCommentFrame(){
        this.clip = null;
    }


    /**
     * Create a new frame to input comment.
     * @param evt
     */
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        
        int x = this.x+20;
        int y = this.y+20;
        
          clip = new CommentDialog(getCurrentPage(),this,model, x, y,true);
        

        //JOptionPane.showInputDialog(this, "Input");
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * Enables or disables the text mark option
     * @param evt
     */
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed

       
         if(editor.isMark()){
             editor.setMark(false);
             jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_marks_unselected.png"))); // NOI18N
         }
         else{
             editor.setMark(true);
             jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_marks_selected.png"))); // NOI18N
         }
       
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    /**
     * If there are comments associated to this chapter, it will open frames with the
     * comments.
     * @param evt
     */
    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed

             

    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        Favorite favorite = new Favorite();
            favorite.setChapterID(chapter.getId());
            favorite.setBeginPos(0);
            favorite.setEndpos(0);
            favorite.setName(editor.getCurrentPage().getName());
            favorite.setPage(editor.getCurrentPage());

       try{
        if (editor.isBookMarked()) {
            model.deleteFavorite(favorite);
            this.setUnbookmarked();
        } else {
            
            model.saveFavorite(favorite);
            this.setBookmarked();
        }
        }
       catch(Exception e){
           e.printStackTrace();
       }
    }//GEN-LAST:event_jToggleButton3ActionPerformed


  /**
   * 
   * @param text
   */
  public void search(String text){

        try {
             String path = new File(".").getCanonicalPath();

             URL css = getClass().getResource("/html/css/index.css");

            String html="<html><head>"+
                         "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\" />"+
                         "<LINK rel=stylesheet type=text/css href=\""+css+"\">"+
                         "</head>"+
                         "<body>"+
                         "<table>";

            LinkedList list = model.searchPages(text);
            Iterator inIt = list.iterator();
            int cont =1;
            boolean flag = true;
            while(inIt.hasNext()){
                    Page page = (Page)inIt.next();
                    String pageText = page.getFile_content();
                    int index = pageText.indexOf(text);
                    int begin = index - 10;
                    if( begin < 0)
                        begin = 0;
                    int end = index + text.length() + 10;
                    if( end > pageText.length())
                        end  = pageText.length();
                    pageText = pageText.substring(begin, end);

                    if( flag ){
                        html = html +"<tr>";
                        flag = false;
                    }
                    else{
                       html = html +"<tr bordercolor=\"#FFFFFF\" bgcolor=\"#F2F4F1\">";
                       flag = true;
                    }
                    URL image = getClass().getResource("/images/link.png");
                    html = html +"<td width=\"20\"><strong>"+
                    "<font color=\"#295B80\" face=\"Arial, Helvetica, sans-serif\">"+cont+".</font></strong></td>"+
                    "<td width=\"450\">"+
                    "<font color=\"#295B80\" face=\"Arial, Helvetica, sans-serif\">"+page.getName()+".</font></td>"+
                    "<td width=\"258\"><strong><font color=\"#666666\" face=\"arial\"><a href=\""+page.getID()+"\">"+pageText+"</a></font></strong></td>"+
                    "<td width=\"23\"><a href=\""+page.getID()+"\"><img src=\""+image+"\" width=\"12\" height=\"10\" border=\"0\"></a></td>"+
                    "</tr>";

                    cont++;
            }
            //marcacoes.addAll(list);
            html = html + "</table></body></html>";

            FileWriter writer = new FileWriter(path+"\\temp.html");
            writer.write(html);
            writer.close();

            URL url = new URL("File:/"+path+"/temp.html");

            //getClass().getResource("/html/temp.html");
            Page page = new Page();
            page.setName("Busca");
            page.setID("B1");
            page.setFile_content("");
            page.setType("HTML");
            page.setUrl(url);
            page.setVersion(1);

            initEditor( page );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void countButtonAction(){
               int x = this.x+20;
               int y = this.y+20;
               
               CommentsListDialog frame = new CommentsListDialog(getCurrentPage(), editor.getComments(),this ,model, x, y);
              
  }


  public void fetchMarks(){
        try {
            

            initEditor( model.fetchMarksListPage(chapter.getId()) );
                        

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Search for the comments in the chapter
     * @return
     */
    public void fetchComments(){
        
        try {

            initEditor( model.fetchCommentsListPage(chapter.getId()) );

           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fetchFavorites(){
        try {            

            initEditor( model.fetchFavoritesListPage(chapter.getId()));
            this.validate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JPanel panelTeste;
    // End of variables declaration//GEN-END:variables
}


class MyController implements MouseListener {

    public void mouseClicked(MouseEvent e) {
       JEditorPane editor = (JEditorPane) e.getSource();
       Document doc = editor.getDocument();

       System.out.print(e.getX());
    }

    public void mousePressed(MouseEvent e) {

       }

    public void mouseReleased(MouseEvent e) {

       }

      public void mouseEntered(MouseEvent e) {

       }

       public void mouseExited(MouseEvent e) {

       }

}

class CommentCountButton extends JToggleButton{

    private Image image;
    private String text;
    private myPanel panel;

    public CommentCountButton(myPanel p) {

        text = "0";
        this.panel = p;

        try{
          image = ImageIO.read(getClass().getResource("/images/btn_notes_count.png"));
        }
        catch(Exception e){

        }
        setPreferredSize(new Dimension(image.getWidth(this),image.getHeight(this)));
        //setBackground(new java.awt.Color(255, 204, 0));
        //setForeground(new java.awt.Color(245, 248, 245));

        setToolTipText("Abrir comentário Salvos");

        setBorder(null);
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.countButtonAction();
            }
        });

        this.setVisible(false);
    }

    @Override
    public void setText(String text){
        this.text = text;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0,29, 24);
        g.drawImage(image, 0, 0, null);
        if (text != null) {
            int w = this.getWidth();
            int h = this.getHeight();
            g.setFont(new java.awt.Font("Arial", Font.BOLD, 11));
            g.setColor(new Color(255,255,255));
            g.drawString(text, 21,11);

        }
    }


}
