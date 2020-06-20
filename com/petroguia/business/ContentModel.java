/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petroguia.business;

import com.petroguia.model.Mark;
import com.petroguia.model.Favorite;
import com.petroguia.model.Comment;
import com.petroguia.model.ContentInterface;
import java.io.*;
import java.net.*;
import java.util.*;
import com.petroguia.model.*;
import com.petroguia.business.DAO.*;


/**
 * Class that refers the business logic from the application. It represents the model business logic
 * from the MVC design pattern. This class uses the DAO package classes.
 *
 * @author hb47537
 */
public class ContentModel implements ContentInterface {


    String html = null;
    boolean flag = false;


    /**
     * Constructor
     */
    public ContentModel() {
    }


    /**
     * Fetch a collection of Chapter object. A chapter is an object with a list
     * of pages, and each page can have a list of pages node. The idea is to perform
     * a tree structure where each node represents a content. The chapter´s collection
     * represents an index of content, with only html content.
     *
     * @return
     * @throws Exception
     */
    public Vector<Chapter> fetchChapters()
            throws Exception {
        ChapterDAO dao = new ChapterDAO();
        return dao.fetchChapters();
    }


    /**
     * Method designed to build the Page object and its leaf pages. It builds the page
     * and leaves using recursivity.
     *
     * @param page_id
     * @return
     * @throws Exception
     */
    @Override
    public Page fetchPage(String page_id) throws Exception {
        PageDAO dao = new PageDAO();
        return dao.fetchPage( page_id);
    }



    /**
     * Method design to search pages that have a specific text.
     *
     * @param texto
     * @return
     * @throws Exception
     */
    @Override
    public LinkedList<Page> searchPages(String texto)
      throws Exception
    {
        PageDAO dao = new PageDAO();
        return dao.searchPages(texto);
    }


    /**
     * Build the referred chapter´s html index page, using the chapter and its pages node iteration.
     *
     * @param chapter     
     * @return the url with the temporary html index page.
     */
    public URL loadIndice(Chapter chapter)
            throws Exception {
        URL url = null;

        String path = new File(".").getCanonicalPath();

        URL css = getClass().getResource("/html/css/index.css");
        URL image = getClass().getResource("/images/link.png");

        html = "<html>"
                + "<head>"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
                + "<LINK rel=stylesheet type=text/css href=\""+css+"\">"
                +"<style type=\"text/css\"> "
                +".style1 { "
	        +   "font-size: 12px;"
	        +"   font-weight: bold;"
                +"}"
                +".style3 {font-size: 12px}"
                +"</style>"
                + "</head>"
                + "<body>"
                + "	<table width=\"567\">";
        Iterator it = chapter.getPages().iterator();
        while (it.hasNext()) {
            Page page = (Page) it.next();
            if (flag) {
                html = html + "<tr bgcolor='#F2F4F1'>";
                flag = false;
            } else {
                html = html + "<tr>";
                flag = true;
            }
            html = html + "        	<td width='508'><a href='" + page.getID() + "'>"
                    + "<strong><font color='#666666' face='arial'>" + page.getName() + "</font></strong></a></td>"
                    + "		<td width='23'><a href='" + page.getID() + "'><img src='"+image+"' width='12' height='10' border='0'></a></td>"
                    + "	</tr>";

            Iterator childIterator = page.getChilds().iterator();
            while ( childIterator.hasNext()){
                Page child = (Page)childIterator.next();

                loadChilds( child );
            }

        }
        html = html + "</table>"
                + "</body></html>";

        FileWriter writer = new FileWriter(path + "\\index.html");
        writer.write(html);
        writer.close();

        url = new URL("File:/"+path+"/index.html");

        return url;
    }

    private void loadChilds(Page child){
        URL image = getClass().getResource("/images/link.png");

        if (flag) {
            html = html + "<tr height='15' bgcolor='#F2F4F1'>";
            flag = false;
        } else {
            html = html + "<tr height='15'>";
            flag = true;
        }
        html = html + "<td width='508'><a href='" + child.getID() + "'>"
                + "<font color='#666666' face='arial' size='2'>&nbsp;&nbsp;&nbsp;" + child.getName() + "</font></a></td>"
                + "		<td width='23'><a href='" + child.getID() + "'><img src='"+image+"' width='12' height='10' border='0'></a></td>"
                + "	</tr>";

        Iterator childIterator = child.getChilds().iterator();
            while ( childIterator.hasNext()){
                Page temp = (Page)childIterator.next();

                loadChilds( temp );
        }
    }


    /**
     * Save a new user´s text mark.
     *
     * @param mark
     * @return
     * @throws Exception
     */
    @Override
    public String saveMark(Mark mark)
      throws Exception
    {
        MarkDAO mdao = new MarkDAO();
        return mdao.save(mark);
    }


    /**
     * Save a new user´s comment text.
     *
     * @param comment
     * @return
     * @throws Exception
     */
    @Override
    public String saveComment(Comment comment)
      throws Exception
    {
        CommentDAO cdao = new CommentDAO();
        return cdao.save(comment);
    }


    /**
     * Updates the user´s comment
     *
     * @param comment
     * @return
     * @throws Exception
     */
    @Override
    public String updateComment(Comment comment)
    throws Exception{
        CommentDAO cdao = new CommentDAO();
        return cdao.update(comment);
    }


    /**
     * Delete the user´s comment.
     *
     * @param comment
     * @return
     * @throws Exception
     */
    @Override
    public String deleteComment(Comment comment)
    throws Exception{
        CommentDAO cdao = new CommentDAO();
        return cdao.delete(comment);
    }


    /**
     * Method designed to verify if the chapter´s page has already been bookmarked or not.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public boolean isBookmarked(String chapterID, Page page)
    throws Exception{
        FavoriteDAO fdao = new FavoriteDAO();
        return fdao.isBookmarked(chapterID, page);
    }


    /**
     * Put the page on the Bookmarks.
     *
     * @param fav
     * @return
     * @throws Exception
     */
    @Override
    public String saveFavorite(Favorite fav)
    throws Exception{
        FavoriteDAO fdao = new FavoriteDAO();
        return fdao.save(fav);
    }


    /**
     * remove the page from the bookmark.
     *
     * @param fav
     * @return
     * @throws Exception
     */
    @Override
    public String deleteFavorite(Favorite fav)
    throws Exception{
        FavoriteDAO fdao = new FavoriteDAO();
        return fdao.delete(fav);
    }


    /**
     * Build the referred chapter´s marks html temporary page, using the referred chapter.
     *
     * @return a Page object representing the temporary html page.     
     * @throws Exception
     */
    @Override
    public Page fetchMarksListPage(String chapterID)
    throws Exception{


         String path = new File(".").getCanonicalPath();

             URL css = getClass().getResource("/html/css/index.css");

            String text="<html><head>"+
                         "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
                         "<LINK rel=stylesheet type=text/css href=\""+css+"\">"+
                         "</head>"+
                         "<body>"+
                         "<table>";

            MarkDAO mdao = new MarkDAO();
            LinkedList list = mdao.fetchMarks(chapterID);
            Iterator inIt = list.iterator();
            int cont =1;
            boolean flag = true;
            while(inIt.hasNext()){
                    Mark m = (Mark)inIt.next();

                    Page page = m.getPage();
                    String temp = m.getText();
                    if( temp.length() > 100)
                    {
                        temp = temp.substring(0, 100);
                        temp = temp +"...";
                    }
                    if( flag ){
                        text = text +"<tr>";
                        flag = false;
                    }
                    else{
                       text = text +"<tr bordercolor=\"#FFFFFF\" bgcolor=\"#F2F4F1\">";
                       flag = true;
                    }
                    URL image = getClass().getResource("/images/link.png");
                    text = text +"<td width=\"20\"><strong>"+
                    "<font color=\"#295B80\" face=\"Arial, Helvetica, sans-serif\">"+cont+".</font></strong></td>"+
                    "<td width=\"508\"><strong><font color=\"#666666\" face=\"arial\"><a href=\""+page.getID()+"\">"+temp+"</a></font></strong></td>"+
                    "<td width=\"23\"><a href=\""+page.getID()+"\"><img src=\""+image+"\" width=\"12\" height=\"10\" border=\"0\"></a></td>"+
                    "</tr>";

                    cont++;
            }
            //marcacoes.addAll(list);
            text = text + "</table></body></html>";

            FileWriter writer = new FileWriter(path+"\\temp.html");
            writer.write(text);
            writer.close();

            URL url = new URL("File:/"+path+"/temp.html");

            //getClass().getResource("/html/temp.html");
            Page page = new Page();
            page.setName("Marcações");
            page.setID("M1");
            page.setFile_content("");
            page.setType("HTML");
            page.setUrl(url);
            page.setVersion(1);

         return page;

    }


    /**
     * Fetch a list of marks from the referred chapter´s page.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public LinkedList<Mark> fetchMarks(String chapterID,Page page)
    throws Exception{
        MarkDAO mdao = new MarkDAO();
        return mdao.fetchMarks(chapterID, page);

    }


    /**
     * Delete a collection of Mark object. This marks are from a specific chapter
     * and page.
     *
     * @param chapterID
     * @param page
     * @throws Exception
     */
    @Override
    public void deleteMarks(String chapterID,Page page)
    throws Exception{
        MarkDAO mdao = new MarkDAO();
        mdao.deleteMarks(chapterID, page);
    }


    /**
     * Fetch a list of comments from the referred chapter´s page.
     *
     * @return
     * @throws Exception
     */
    @Override
    public LinkedList<Comment> fetchComments(String chapterID, Page page)
    throws Exception{
        CommentDAO cdao = new CommentDAO();
        return cdao.fetchComments(chapterID, page);
    }


    /**
     * Build the referred chapter´s comments html temporary page, using the referred chapter .
     *
     * @param chapterID
     * @return a Page object representing the temporary html page.
     * @throws Exception
     */
    @Override
    public Page fetchCommentsListPage(String chapterID)
    throws Exception{

         String path = new File(".").getCanonicalPath();
             URL css = getClass().getResource("/html/css/index.css");
            String text="<html><head>"+
                         "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
                         "<LINK rel=stylesheet type=text/css href=\""+css+"\">"+
                         "</head>"+
                         "<body>"+
                         "<table>";

            CommentDAO cdao = new CommentDAO();
            LinkedList list = cdao.fetchComments(chapterID);
            Iterator inIt = list.iterator();
            int cont =1;
            boolean flag = true;
            while(inIt.hasNext()){

                    Comment comm = (Comment)inIt.next();
                    Page page = comm.getPage();

                    String temp = comm.getText();
                    if( temp.length() > 100)
                    {
                        temp = temp.substring(0, 100);
                        temp = temp +"...";
                    }
                    if( flag ){
                        text = text +"<tr>";
                        flag = false;
                    }
                    else{
                       text = text +"<tr bordercolor=\"#FFFFFF\" bgcolor=\"#F2F4F1\">";
                       flag = true;
                    }
                    URL image = getClass().getResource("/images/link.png");
                    text = text +"<td width=\"20\"><strong>"+
                    "<font color=\"#295B80\" face=\"Arial, Helvetica, sans-serif\">"+cont+".</font></strong></td>"+
                    "<td width=\"508\"><strong><font color=\"#666666\" face=\"arial\"><a href=\""+page.getID()+"\">"+temp+"</a></font></strong></td>"+
                    "<td width=\"23\"><a href=\""+page.getID()+"\"><img src=\""+image+"\" width=\"12\" height=\"10\" border=\"0\"></a></td>"+
                    "</tr>";

                    cont++;
            }
            text = text + "</table></body></html>";
            //comments.addAll(list);

            FileWriter writer = new FileWriter(path+"\\temp.html");
            writer.write(text);
            writer.close();

            URL url = new URL("File:/"+path+"/temp.html");
            Page page = new Page();
            page.setName("Anotações");
            page.setID("A1");
            page.setFile_content("");
            page.setType("HTML");
            page.setUrl(url);
            page.setVersion(1);

            return page;
    }


    /**
     * Build the referred chapter´s favorites html temporary page, using the referred chapter .
     *
     * @param chapterID
     * @return a Page object representing the temporary html page.
     * @throws Exception
     */
    @Override
    public Page fetchFavoritesListPage(String chapterID)
    throws Exception{

        //LinkedList<Favorite> favoritos = new java.util.LinkedList<Favorite>();
             String path = new File(".").getCanonicalPath();

             URL css = getClass().getResource("/html/css/index.css");
            String text="<html><head>"+
                         "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"+
                         "<LINK rel=stylesheet type=text/css href=\""+css+"\">"+
                         "</head>"+
                         "<body>"+
                         "<table>";

            FavoriteDAO fdao = new FavoriteDAO();
            LinkedList list =  fdao.fetchFavotites(chapterID);
            Iterator inIt = list.iterator();
             int cont =1;
            boolean flag = true;
            while(inIt.hasNext()){
                    Favorite fav = (Favorite)inIt.next();
                    Page page = fav.getPage();
                    String name = fav.getName();

                    if( flag ){
                        text = text +"<tr>";
                        flag = false;
                    }
                    else{
                       text = text +"<tr bordercolor=\"#FFFFFF\" bgcolor=\"#F2F4F1\">";
                       flag = true;
                    }
                    URL image = getClass().getResource("/images/link.png");
                    text = text +"<td width=\"20\"><strong>"+
                    "<font color=\"#295B80\" face=\"Arial, Helvetica, sans-serif\">"+cont+".</font></strong></td>"+
                    "<td width=\"508\"><strong><font color=\"#666666\" face=\"arial\"><a href=\""+page.getID()+"\">"+name+"</a></font></strong></td>"+
                    "<td width=\"23\"><a href=\""+page.getID()+"\"><img src=\""+image+"\" width=\"12\" height=\"10\" border=\"0\"></a></td>"+
                    "</tr>";

                    cont++;
            }

            text = text + "</table></body></html>";

            FileWriter writer = new FileWriter(path+"\\temp.html");
            writer.write(text);
            writer.close();

            URL url = new URL("File:/"+path+"/temp.html");
            Page page = new Page();
            page.setName("Favoritos");
            page.setID("F1");
            page.setFile_content("");
            page.setType("HTML");
            page.setUrl(url);
            page.setVersion(1);

        
        return page;
    }
}
