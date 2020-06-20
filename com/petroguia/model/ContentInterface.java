/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;


import java.io.*;
import java.net.*;
import java.util.*;


/**
 * Interface designed for access the petroguia content business features. It was
 * developed to be used by any kind of user graphic interface.
 *
 * @author hb47537
 */
public interface ContentInterface {

    public Vector<Chapter> fetchChapters() throws Exception;
    public Page fetchPage(String page_id) throws Exception;
    //public Page fetchPage(String chapter_id,int pageNumber) throws Exception;
    public LinkedList<Page> searchPages(String texto)
      throws Exception;

    public URL loadIndice(Chapter chapter)
            throws Exception;

    public String saveComment(Comment comment) throws Exception;
    public String updateComment(Comment comment) throws Exception;
    public String deleteComment(Comment comment) throws Exception;
    public LinkedList<Comment> fetchComments(String chapterID, Page page) throws Exception;
    public Page fetchCommentsListPage(String chapterID) throws Exception;

    public String saveMark(Mark mark) throws Exception;
    public Page fetchMarksListPage(String chapterID) throws Exception;
    public LinkedList<Mark> fetchMarks(String chapterID,Page page ) throws Exception;
    public void deleteMarks( String chapterID, Page page) throws Exception;

    public String saveFavorite(Favorite fav) throws Exception;
    public String deleteFavorite(Favorite fav) throws Exception;
    public boolean isBookmarked(String chapterID, Page page) throws Exception;
    public Page fetchFavoritesListPage(String chapterID) throws Exception;

    
}
