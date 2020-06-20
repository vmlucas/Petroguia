/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.business.DAO;

import com.petroguia.model.*;
import java.util.*;
import java.sql.*;


/**
 * Class designed to manage the Favorite object with the database
 *
 * @author hb47537
 */
public class FavoriteDAO {

    public FavoriteDAO() {
    }


    /**
     * Save a new Favorite object on the database
     *
     * @param fav
     * @return
     * @throws Exception
     */
    public String save(Favorite fav)
      throws Exception
    {
        String sql = "insert into FAVORITE(CHAPTER_ID,PAGE_ID,BEGINPOS,ENDPOS,NAME) "+
                "values('"+fav.getChapterID()+"','"+fav.getPage().getID()+"',"+
                fav.getBeginPos()+","+fav.getEndpos()+",'"+fav.getName()+"')";

        Connection conn = DAO.getInstance().getConnection();;
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();

        return "Favorito salvo!";
    }


    /**
     * Delete a Favorite object on the database
     * @param favorite
     * @return
     * @throws Exception
     */
    public String delete(Favorite favorite)
      throws Exception
    {
        String sql = "delete from FAVORITE "+
                "where ID ='"+favorite.getID();

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        conn.close();
        return "Favorito Apagado!";
    }


    /**
     * Method designed to verify if the page has already been bookmarked or not.
     *
     * @param chapterID
     * @param page
     * @return
     * @throws Exception
     */
    public boolean isBookmarked(String chapterID, Page page)
       throws Exception
    {
        String sql = "select * from FAVORITE "+
                "where CHAPTER_ID ='"+chapterID+"' "+
                "and PAGE_ID = '"+page.getID()+"'";

        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        if( result.next())
            return true;
        else return false;
    }


    /**
     * Fetch a collection of Favorite object. These favorites are from a specific chapter.
     * @param chapterID
     * @return
     * @throws Exception
     */
    public LinkedList<Favorite> fetchFavotites( String chapterID)
            throws Exception
    {
        String sql = "select * from FAVORITE "+
                "where CHAPTER_ID ='"+chapterID+"'";

        PageDAO dao = new PageDAO();
        Connection conn = DAO.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        LinkedList<Favorite> list = new LinkedList<Favorite>();
        while( result.next()){
            Favorite fav = new Favorite();
            fav.setBeginPos(result.getInt("BEGINPOS"));
            fav.setEndpos(result.getInt("ENDPOS"));
            fav.setChapterID(chapterID);
            fav.setPage(dao.fetchPage( result.getString("PAGE_ID")));
            fav.setName(result.getString("NAME"));

            list.add(fav);
        }


          Collections.sort(list, new Comparator()
            {
              public int compare(Object o1, Object o2)
              {
                Favorite c1 =
                   (Favorite)o1;
                Favorite c2 =
                   (Favorite)o2;

                String s1 = c1.getName();
                String s2 = c2.getName();
                    return s1.compareTo(s2);
              }
           });

        return list;
         
        
    }

}
