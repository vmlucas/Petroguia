/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.business.DAO;

import java.util.*;
import java.sql.*;
import org.apache.derby.jdbc.*;


/**
 * Class designed to manage the Database connections.
 *
 * @author hb47537
 */
public class DAO {

    //private Connection dbConnection = null;
    private static DAO dao = null;


    /**
     * Constructor responsible to load the database driver and set the environment.
     *
     * @throws Exception
     */
    private DAO()
      throws Exception
    {
        java.io.File path = new java.io.File(".");
        //java.net.URL url = getClass().getResource("/PetroguiaDB");
        String userHomeDir = path.getCanonicalPath()+"\\PetroguiaDB";
        System.out.println("Derby dir "+userHomeDir);
        String systemDir = userHomeDir;
        System.out.println(systemDir);
        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    }


    /**
     *
     * @return a database connection
     * @throws Exception
     */
    public Connection getConnection()
      throws Exception
    {
        String strUrl = "jdbc:derby:default;create=true";
        return DriverManager.getConnection(strUrl);
    }
    

    /*public void closeDB()
      throws Exception
    {
        dbConnection.close();
    }*/


    /**
     * Method responsible to allow the class object access.
     * @return
     * @throws Exception
     */
    public static DAO getInstance()
      throws Exception
    {
        if( dao == null)
            dao = new DAO();

        return dao;
    }


}
