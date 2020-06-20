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
import java.io.*;
import javax.script.*;
//import com.sun.javafx.api.JavaFXScriptEngine;



/**
 * The editor is responsible to open url and html pages. It also implements some events, such as
 * mouse drag event and hyperlink listener.
 *
 * @author hb47537
 */
public class Editor extends JEditorPane implements HyperlinkListener, MouseListener {

    //private Editor editor;
    private boolean mark = false;
    private boolean bookMarked = false;
    private ContentInterface model;
    private UInterface ui;
    private LinkedList<Comment> comments;
    private final JPopupMenu menu = new JPopupMenu();
    private int begin, end;
    private String mySelectedText;
    private Editor editor;
    private Page currentPage;


    /**
     * Constructor
     * defines the two interfaces used on the class. Those interfaces is used to access
     * some business logic ( ContentInterface ) such as delete and save content; and
     * GUI methods ( UInterface ) such as get the current chapter, manage the breadcramp
     * and manage the bookmark button.
     *
     * @param ui
     * @param model
     */
    public Editor(UInterface ui, ContentInterface model) {
        init(ui, model);
    }

    /**
     * Initialize the editor components. Defines the popup menu,
     * add a hyperlink listener, remove the highlights.
     *
     * @param ui
     * @param paramModel
     */
    private void init(UInterface ui, ContentInterface paramModel) {
        this.model = paramModel;
        this.ui = ui;
        final UInterface finalUI = ui;
        this.editor = this;
        setContentType("text/html");
        setEditable(false);
        setText("");
        getHighlighter().removeAllHighlights();

        addMouseListener(this);
        this.addHyperlinkListener(this);

        // Create and add a menu item
        JMenuItem item = new JMenuItem("Marcar");
        item.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if ((mySelectedText != null) && (!mySelectedText.equals(""))) {

                    try {
                        Mark mark = new Mark();
                        mark.setChapterID(finalUI.getChapter().getId());
                        mark.setText(mySelectedText);
                        mark.setPage(currentPage);
                        mark.setBeginPos(begin);
                        mark.setEndpos(end);

                        model.saveMark(mark);
                        mySelectedText = "";
                        menu.setVisible(false);

                        editor.getHighlighter().addHighlight(begin, end, new DefaultHighlighter.DefaultHighlightPainter(new Color(145, 209, 254)));
                        //JOptionPane.showMessageDialog(null, "Marcacao salva");
                    } catch (Exception e) {
                    }
                }
            }
        });
        JMenuItem item2 = new JMenuItem("Cancelar");
        item2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu.setVisible(false);
            }
        });
        JMenuItem item3 = new JMenuItem("Apagar Marcações");
        item3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    model.deleteMarks(finalUI.getChapter().getId(), currentPage);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao apagar marcações");
                }
                editor.getHighlighter().removeAllHighlights();
                menu.setVisible(false);
            }
        });

        menu.add(item);
        menu.add(item2);
        menu.add(item3);

    }

    public void mouseReleased(MouseEvent evt) {
        if (mark) {

            begin = getSelectionStart();
            end = getSelectionEnd();
            //String myPaneText = editor.getText();
            //String mySelectedText = myPaneText.substring(begin, end);
            mySelectedText = getSelectedText();

            if ((mySelectedText != null) && (!mySelectedText.equals(""))) {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }


        }
    }

    public void processURL(Page page)
            throws Exception {
        if (page != null) {

            //setText(null);
            getHighlighter().removeAllHighlights();
            //panel.validate();

            //verify the marks for the chapter and highlights the marks
            LinkedList list = model.fetchMarks(ui.getChapter().getId(), page);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Mark m = (Mark) it.next();
                //if(m.getURL().getFile().equals(url.getFile())){
                getHighlighter().addHighlight(m.getBeginPos(), m.getEndpos(),
                        new DefaultHighlighter.DefaultHighlightPainter(new Color(145, 209, 254)));
                //}
            }

            //set the new page in the editor
            //System.out.println(url);
            currentPage = page;
            //System.out.print("url "+page.getUrl());
            this.setPage(page.getUrl());
            ui.validate();

            if (page.getName() != null) {
                ui.getBradCramp().addPage(page);
            }

            ui.setUnbookmarked();

            //verify if the page is bookmarked
            if (model.isBookmarked(ui.getChapter().getId(), page)) {
                bookMarked = true;
                ui.setBookmarked();
            }

            //verify the comments for the link
            verifyComments();
            //ui.validate();

        }
    }

    public void verifyComments() {

        try {
            Collection col = model.fetchComments(ui.getChapter().getId(), currentPage);
            comments = new LinkedList<Comment>();
            if (col.size() > 0) {
                comments.addAll(col);
                ui.displayCommentCountButton(String.valueOf(col.size()));

            } else {
                ui.hideCommentCountButton();
            }
            ui.validate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar comentários");
        }
    }

    /*public void processText(String text, String name)
    throws Exception
    {
    if( text != null){

    setText(null);
    getHighlighter().removeAllHighlights();
    panel.validate();

    //set the new page in the editor
    setText(text);
    panel.validate();




    panel.getjToggleButton3().setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn_favorites_unselected.png")));
    panel.getjToggleButton3().setEnabled(false);

    //verify the comments for the link
    panel.getjToggleButton4().setVisible(false);
    }
    }*/
    /*public boolean search(String text)
    throws Exception
    {

    boolean flag = false;
    Document doc = getDocument ();
    Matcher matcher = Pattern.compile (text)
    .matcher (doc.getText (0,doc.getLength () ));
    while( matcher.find() ){
    flag = true;
    int begin = matcher.start ();
    int end = matcher.end ();

    getHighlighter().addHighlight(begin,end,
    new DefaultHighlighter.DefaultHighlightPainter(new Color(221,221,0)));

    }


    this.validate();
    return flag;

    }*/
    public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
        HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
        //final URL url = hyperlinkEvent.getURL();
        final String pageID = hyperlinkEvent.getDescription();
        //turning better the name to be printed in the breadcramp
        //Only the name has to be shown, not the complete name such as "pagina-a3.html".
        //Instead of this, the name "pagina-a3" will be shown.
        
        if (type == HyperlinkEvent.EventType.ACTIVATED) {

            Runnable runner = new Runnable() {

                public void run() {
                    // Retain reference to original
                    Document doc = getDocument();
                    try {

                        /*String name = "";
                        StringTokenizer tok = new StringTokenizer(linkName, "/");
                        while (tok.hasMoreTokens()) {//getting the final part of the link, only the page itself.
                            name = tok.nextToken();
                        }*/
                        final Page page = model.fetchPage(pageID);

                        //tok = new StringTokenizer(name, ".");//eliminating the file type, ex:.html
                        //final String nameFile = tok.nextToken();
                        //final String fileType = tok.nextToken();

                        if (page.getType().equals("VIDEO")) {
                            //new MediaPlayer(ui, url);
                            //build code to use page.getUrl();
                            File file = new File(".");
                            String filepath = file.getCanonicalPath() + "\\html\\base_torpedo.mpg";
                            System.out.println("video "+filepath);
                            //String file = url.getFile();
                            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + filepath);
                            System.out.println("passou");
                        }
                        else if(page.getType().equals("FORMULA")) {
                            new FormulaDialog(ui, page);

                        }
                        else {
                            processURL(page);
                        }
                        /*else if(page.getType().equals("JFX")) {
                            try {
                                //String path = new File(".").getCanonicalPath();
                                ScriptEngineManager manager = new ScriptEngineManager();

                                JavaFXScriptEngine engine = (JavaFXScriptEngine) manager.getEngineByName("javafx");
                                InputStreamReader reader =
                                        new InputStreamReader(Editor.class.getResourceAsStream("Book.fx"));
                                engine.eval(reader);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }*/
                        

                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "Link invalido", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        setDocument(doc);
                    }
                }
            };
            SwingUtilities.invokeLater(runner);
        }
    }

    public LinkedList<Comment> getComments() {
        return comments;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public void mouseClicked(MouseEvent e) {
        Point pt = new Point(e.getX(), e.getY());
        final int x = e.getX();
        final int y = e.getY();
        final JEditorPane editor = this;
        final int pos = this.viewToModel(pt);
        JPopupMenu m = new JPopupMenu();
        try {
            //String header = this.getText(pos,5 );
            HTMLDocument hdoc = (HTMLDocument) this.getDocument();
            Element element = hdoc.getCharacterElement(pos);
            if ((element.getName().equals("img"))
                    && (currentPage.getName().equals("Capacidade dos tubos de perfuração"))) {

                JMenuItem item1 = new JMenuItem("bbl/m");
                item1.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {


                        try {
                            URL url = getClass().getResource("/html/A13.html");
                            editor.setPage(url);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                JMenuItem item2 = new JMenuItem("Pé/m");
                item2.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {


                        try {
                            URL url = getClass().getResource("/html/A13a.html");
                            editor.setPage(url);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                JMenuItem item3 = new JMenuItem("m/Pé");
                item3.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {


                        try {
                            URL url = getClass().getResource("/html/A13b.html");
                            editor.setPage(url);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                JMenuItem item4 = new JMenuItem("m/bbl");
                item4.addActionListener(new java.awt.event.ActionListener() {

                    public void actionPerformed(java.awt.event.ActionEvent evt) {


                        try {
                            URL url = getClass().getResource("/html/A13c.html");
                            editor.setPage(url);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                m.add(item1);
                m.add(item2);
                m.add(item3);
                m.add(item4);
                m.show(e.getComponent(), e.getX(), e.getY());
            } else {
                m.setVisible(false);

            }
        } catch (Exception ex) {
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public boolean isBookMarked() {
        return bookMarked;
    }

    public void setBookMarked(boolean bookMarked) {
        this.bookMarked = bookMarked;
    }

    public Page getCurrentPage() {
        return currentPage;
    }



}
