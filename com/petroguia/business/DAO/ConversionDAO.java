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
 * Class designed to manage with the Conversions parameters on the database
 * @author hb47537
 */
public class ConversionDAO {

    public ConversionDAO() {
    }


    /**
     * Method designed to retrieve all conversion types, such as TEMPERATURA, PRESSÃO...
     *
     * @return
     * @throws Exception
     */
    public String[] fetchTypes( )
      throws Exception
    {

        Connection conn = DAO.getInstance().getConnection();
        ArrayList<String> list = new ArrayList<String>();

        String query = "select distinct type from CONVERSION";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while( result.next()){
           list.add(result.getString(1));
        }


        result.close();
        stmt.close();
        conn.close();

        Object[] o = list.toArray();
        String[] formulas = new String[o.length];
        for( int i=0; i < o.length; i++){
            formulas[i] = (String)o[i];
        }
        return formulas;
    }


    /**
     * Method designed to retrieve all conversion names, specified by the parameter type.
     * Ex: CELSIUS, FAHRENHEIT (TEMPERATURA type)...
     *
     * @param type
     * @return
     * @throws Exception
     */
    public String[] fetchDescriptions( String type )
      throws Exception
    {

        Connection conn = DAO.getInstance().getConnection();
        ArrayList<String> list = new ArrayList<String>();

        String query = "select distinct from_IN from conversion where TYPE = '"+type+"'";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while( result.next()){
           list.add(result.getString(1));
        }


        result.close();
        stmt.close();
        conn.close();

        Object[] o = list.toArray();
        String[] formulas = new String[o.length];
        for( int i=0; i < o.length; i++){
            formulas[i] = (String)o[i];
        }
        return formulas;
    }


    /**
     * Method designed to retrieve the page name that represents the conversion.
     * It is useful to build the formula business object that will manage the
     * conversion itself.
     *
     * @param fromIN
     * @param toOUT
     * @return
     * @throws Exception
     */
    public String fetchPageName( String fromIN, String toOUT)
            throws Exception
    {

        Connection conn = DAO.getInstance().getConnection();
        String name = "";
        String query = "select p.PAGE_NAME from Page p, conversion c "+
                         "where p.page_id = c.page_id and c.from_in = '"+fromIN+"' "+
                         "and c.to_out = '"+toOUT+"'";

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        if( result.next()){
           name = result.getString(1);
        }


        result.close();
        stmt.close();
        conn.close();

        return name;
    }

}
