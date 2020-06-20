/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.business.DAO;


import java.util.*;
import com.petroguia.model.*;
import java.sql.*;
import org.apache.derby.jdbc.*;



/**
 * Class designed to manage the Chapter object with the database
 *
 * @author hb47537
 */
public class ChapterDAO {

    
    public ChapterDAO()
    {
          
    }


    /**
     * Fetch a collection of Chapter object. A chapter is an object with a list
     * of pages, and each page can have a list of pages node. The idea is to perform
     * a tree structure where each node represents a content. The chapter´s collection
     * represents an index of content, with only html content.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    public Vector<Chapter> fetchChapters()
      throws Exception
    {
        Vector<Chapter> chapters = new Vector<Chapter>();
        LinkedList lista = new LinkedList();
        PageDAO pageDAO = new PageDAO();
        Connection conn = DAO.getInstance().getConnection();

        String query = "select distinct P.PAGE_ID from PAGE P, CHILD C where P.PAGE_ID not in "+
                           "(select distinct C1.CHILD_ID from CHILD C1 ) and (P.TYPE='HTML' or P.TYPE='VIDEO')";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while( result.next()){
            String chap = result.getString(1);
            lista.add(chap);
        }
        result.close();

        Iterator it = lista.iterator();
        while( it.hasNext()){
           String chap = (String)it.next();
           Chapter chapter = new Chapter();
           chapter.setId(chap);
           Page page = pageDAO.fetchPage( chap );
           chapter.setDescription(page.getName());

           query = "select CHILD_ID from CHILD where PAGE_ID='"+chap+"'";
           result = stmt.executeQuery(query);
           int cont = 0;
           while( result.next()){

              page = pageDAO.fetchPage( result.getString(1));
              chapter.addPage(cont, page);
              cont++;
           }
           chapters.add(chapter);
        }

        result.close();
        stmt.close();
        conn.close();

        return chapters;
    }


    
}
