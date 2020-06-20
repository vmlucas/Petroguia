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
 * Class designed to manage with the Page object on the database.
 * @author hb47537
 */
public class PageDAO {

    public PageDAO() {
    }


    /**
     * Method designed to build the Page object, and its leaf pages, from the 
     * database PAGE and CHILD tables. It builds the page and leaves using recursivity.
     *
     * @param page_id
     * @return
     * @throws Exception
     */
    public Page fetchPage( String page_id)
      throws Exception
    {

        Page page = new Page();
        Connection conn = DAO.getInstance().getConnection();

        String query = "select * from Page where PAGE_ID = '"+page_id+"'";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        if( result.next()){
           page.setID(result.getString("PAGE_ID"));
           page.setName(result.getString("PAGE_NAME"));
           page.setType(result.getString("TYPE"));
           page.setFile_content(result.getString("FILE_CONTENT"));
           page.setUrl(getClass().getResource("/html/"+result.getString("FILE")));
           page.setVersion(result.getInt("VERSION"));

        }
        result.close();

        query = "select CHILD_ID from CHILD where PAGE_ID='"+page_id+"'";
        result = stmt.executeQuery(query);
        while( result.next()){
            page.addChild(fetchPage(result.getString(1)));

        }

        result.close();
        stmt.close();
        conn.close();

        return page;
    }

     /*public Page fetchPage(String chapter_id, String htmlPage)
      throws Exception
    {
        
        Page page = null;

        String query = "select * from CONTENT where CHAPTER_ID = '"+chapter_id+"' and HTML='"+htmlPage+"'";
        Statement stmt = DAO.getInstance().getConnection().createStatement();
        ResultSet result = stmt.executeQuery(query);
        if( result.next()){
            page = new Page();
            page.setName(result.getString("PAGE_NAME"));
            page.setNumber(result.getInt("PAGE_NUMBER"));
            page.setText(result.getString("TEXT"));
            page.setUrl(getClass().getResource("/html/"+result.getString("HTML")));
        }
        result.close();
        stmt.close();

        return page;
    }*/


     /*public Page fetchPage(String chapter_id, int pageNumber)
      throws Exception
    {

        Page page = null;

        String query = "select * from CONTENT where CHAPTER_ID = '"+chapter_id+"' and PAGE_NUMBER="+pageNumber;
        Statement stmt = DAO.getInstance().getConnection().createStatement();
        ResultSet result = stmt.executeQuery(query);
        if( result.next()){
            page = new Page();
            page.setName(result.getString("PAGE_NAME"));
            page.setNumber(result.getInt("PAGE_NUMBER"));
            page.setText(result.getString("TEXT"));
            page.setUrl(getClass().getResource("/html/"+result.getString("HTML")));
        }
        result.close();
        stmt.close();

        return page;
    }*/


    /**
     * Method design to search pages that have a specific text. Those pages are formed with
     * the fetchpage class method.
     *
     * @param texto
     * @return a collection of pages
     * @throws Exception
     */
    public LinkedList<Page> searchPages(String texto)
      throws Exception
    {
        LinkedList<Page> lista = new LinkedList<Page>();

        Connection conn = DAO.getInstance().getConnection();
        String query = "select PAGE_ID from PAGE where UPPER(PAGE_NAME) like '%"+texto.toUpperCase()
                            +"%' or UPPER(FILE_CONTENT) like '%"+texto.toUpperCase()+"%' order by PAGE_ID";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while( result.next()){
            
            lista.add(this.fetchPage(result.getString(1)));
        }
        result.close();
        stmt.close();
        conn.close();

        return lista;
    }
}
