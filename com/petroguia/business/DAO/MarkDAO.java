/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.business.DAO;

import com.petroguia.model.*;
import java.util.*;
import java.sql.*;


/**
 * Class designed to manage with the Mark object on the database.
 * @author hb47537
 */
public class MarkDAO {

    public MarkDAO() {
    }


    /**
     * Save a new Mark object on the database.
     * 
     * @param mark
     * @return
     * @throws Exception
     */
    public String save(Mark mark)
       throws Exception
    {
        String sql = "insert into MARK(CHAPTER_ID, PAGE_ID, BEGINPOS, ENDPOS, TEXT) "+
                "values('"+mark.getChapterID()+"','"+mark.getPage().getID()+"',"+
                mark.getBeginPos()+","+mark.getEndpos()+",'"+mark.getText()+"')";

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();
        return "marcação salva!";
    }


    /**
     * * Fetch a collection of Mark object. This marks are from a specific chapter.
     *
     * @param chapterID
     * @return
     * @throws Exception
     */
    public LinkedList<Mark> fetchMarks( String chapterID)
      throws Exception
    {
        String sql = "select * from MARK "+
                "where CHAPTER_ID ='"+chapterID+"'";

        PageDAO dao = new PageDAO();
        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        LinkedList<Mark> list = new LinkedList<Mark>();
        while( result.next()){
            Mark mark = new Mark();
            mark.setBeginPos(result.getInt("BEGINPOS"));
            mark.setEndpos(result.getInt("ENDPOS"));
            mark.setChapterID(chapterID);
            mark.setPage(dao.fetchPage( result.getString("PAGE_ID")));
            mark.setText(result.getString("TEXT"));

            list.add(mark);
        }

        result.close();
        stmt.close();
        conn.close();

        return list;
    }


    /**
     * Fetch a collection of Mark object. This marks are from a specific chapter
     * and page.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    public LinkedList<Mark> fetchMarks( String chapterID, Page page)
            throws Exception
    {
        String sql = "select * from MARK "+
                "where CHAPTER_ID ='"+chapterID+"' "+
                "and PAGE_ID = '"+page.getID()+"'";

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        LinkedList<Mark> list = new LinkedList<Mark>();
        while( result.next()){
            Mark mark = new Mark();
            mark.setBeginPos(result.getInt("BEGINPOS"));
            mark.setEndpos(result.getInt("ENDPOS"));
            mark.setChapterID(chapterID);
            mark.setPage(page);
            mark.setText(result.getString("TEXT"));

            list.add(mark);
        }

        result.close();
        stmt.close();
        conn.close();

        return list;
    }


    /**
     * Delete a collection of Mark object. This marks are from a specific chapter
     * and page.
     *
     * @param chapterID
     * @param page
     * @throws Exception
     */
    public void deleteMarks(String chapterID,Page page)
       throws Exception
    {

        String sql = "delete from MARK "+
                "where CHAPTER_ID ='"+chapterID+"' "+
                "and PAGE_ID = '"+page.getID()+"'";

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();
    }

}
