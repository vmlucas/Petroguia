/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.business.DAO;


import com.petroguia.model.*;
import java.util.*;
import java.sql.*;


/**
 * Class designed to manage with the Comment object on the database.
 *
 * @author hb47537
 */
public class CommentDAO {

    public CommentDAO() {
    }


    /**
     * Save a new Comment object on the database
     *
     * @param comment
     * @return
     * @throws Exception
     */
    public String save(Comment comment)
      throws Exception
    {
        String sql = "insert into COMMENT(CHAPTER_ID,PAGE_ID,BEGINPOS,ENDPOS,TEXT,TIMESTAMP) "+
                "values('"+comment.getChapterID()+"','"+comment.getPage().getID()+"',"+
                comment.getBeginPos()+","+comment.getEndpos()+",'"+comment.getText()+"',"+
                "CURRENT_TIMESTAMP)";

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();
        return "Comentário salvo!";
    }


    /**
     * Updates the referred Comment object on the database
     * @param comment
     * @return
     * @throws Exception
     */
    public String update(Comment comment)
      throws Exception
    {

        String sql = "update COMMENT set "+
                "BEGINPOS = "+comment.getBeginPos()+","+
                "ENDPOS = "+comment.getEndpos()+","+
                "TEXT = '"+comment.getText()+"',"+
                "TIMESTAMP =CURRENT_TIMESTAMP "+
                "where ID ="+comment.getID();

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();
        return "Comentário alterado!";
    }


    /**
     * Delete the referred Comment object on the database
     * @param comment
     * @return
     * @throws Exception
     */
    public String delete(Comment comment)
      throws Exception
    {
        String sql = "delete from COMMENT "+
                "where ID ="+comment.getID();

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();
        return "Comentário apagado!";
    }


    /**
     * Fetch a collection of Comment object. This comments are from a specific chapter
     * and page.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    public LinkedList<Comment> fetchComments( String chapterID, Page page)
      throws Exception
    {
        String sql = "select * from COMMENT "+
                "where CHAPTER_ID ='"+chapterID+"' "+
                "and PAGE_ID = '"+page.getID()+"'";

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        LinkedList<Comment> list = new LinkedList<Comment>();
        while( result.next()){
            Comment comment = new Comment();
            comment.setID(result.getInt("ID"));
            comment.setBeginPos(result.getInt("BEGINPOS"));
            comment.setEndpos(result.getInt("ENDPOS"));
            comment.setChapterID(chapterID);
            comment.setPage(page);
            comment.setText(result.getString("TEXT"));
            comment.setTimeStamp(result.getDate("TIMESTAMP"));

            list.add(comment);
        }
        

          Collections.sort(list, new Comparator()
            {
              public int compare(Object o1, Object o2)
              {
                Comment c1 =
                   (Comment)o1;
                Comment c2 =
                   (Comment)o2;

                java.util.Date d1 = c1.getTimeStamp();
                java.util.Date d2 = c2.getTimeStamp();
                    return d1.compareTo(d2);
              }
           });

        return list;
    }


    /**
     * Fetch a collection of Comment object. These comments are from a specific chapter.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    public LinkedList<Comment> fetchComments( String chapterID )
            throws Exception
    {
        String sql = "select * from COMMENT "+
                "where CHAPTER_ID ='"+chapterID+"'";

        PageDAO dao = new PageDAO();
        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        LinkedList<Comment> list = new LinkedList<Comment>();
        while( result.next()){
            Comment comment = new Comment();
            comment.setBeginPos(result.getInt("BEGINPOS"));
            comment.setEndpos(result.getInt("ENDPOS"));
            comment.setChapterID(chapterID);
            comment.setPage(dao.fetchPage( result.getString("PAGE_ID")));
            comment.setText(result.getString("TEXT"));
            comment.setTimeStamp(result.getDate("TIMESTAMP"));

            list.add(comment);
        }


          Collections.sort(list, new Comparator()
            {
              public int compare(Object o1, Object o2)
              {
                Comment c1 =
                   (Comment)o1;
                Comment c2 =
                   (Comment)o2;

                java.util.Date d1 = c1.getTimeStamp();
                java.util.Date d2 = c2.getTimeStamp();
                    return d1.compareTo(d2);
              }
           });

        return list;
    }


}
