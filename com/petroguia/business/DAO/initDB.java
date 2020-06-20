/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petroguia.business.DAO;

import java.sql.*;
import org.apache.derby.jdbc.*;
import java.io.*;
import java.net.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.*;
/**
 *
 * @author hb47537
 */
public class initDB {

    private Connection dbConnection = null;

    public initDB()
            throws Exception {
        //java.net.URL url = getClass().getResource("/PetroguiaDB");
        String userHomeDir = "C:\\Users\\hb47537\\Documents\\Development\\JSE\\ePetroGuia\\src\\PetroguiaDB";
        String systemDir = userHomeDir;
        System.out.println(systemDir);
        // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);


        String strUrl = "jdbc:derby:default;create=true";


        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        dbConnection = DriverManager.getConnection(strUrl);


    }

    public void createTables()
            throws Exception {
        String contentTable = "CREATE table CONTENT ( "
                + "ID   INTEGER NOT NULL "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1, INCREMENT BY 1),"
                + "CHAPTER_ID VARCHAR(1) NOT NULL,"
                + "CHAPTER_NAME VARCHAR(500) NOT NULL,"
                + "PAGE_NUMBER INTEGER NOT NULL,"
                + "PAGE_NAME VARCHAR(500) NOT NULL,"
                + "HTML VARCHAR(10000) NOT NULL,"
                + "TEXT VARCHAR(10000) NOT NULL)";
        this.createTable(contentTable);

        String commentTable = "CREATE table COMMENT ( "
                + "ID   INTEGER NOT NULL "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1, INCREMENT BY 1),"
                + "CHAPTER_ID VARCHAR(1) NOT NULL,"
                + "PAGE_NUMBER INTEGER NOT NULL,"
                + "BEGINPOS   INTEGER NOT NULL,"
                + "ENDPOS   INTEGER NOT NULL,"
                + "TEXT VARCHAR(100) NOT NULL,"
                + "TIMESTAMP TIMESTAMP NOT NULL)";

        this.createTable(commentTable);

        String favTable = "CREATE table FAVORITE ( "
                + "ID   INTEGER NOT NULL "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1, INCREMENT BY 1),"
                + "CHAPTER_ID VARCHAR(1) NOT NULL,"
                + "PAGE_NUMBER INTEGER NOT NULL,"
                + "BEGINPOS   INTEGER,"
                + "ENDPOS   INTEGER,"
                + "NAME VARCHAR(500) NOT NULL)";

        this.createTable(favTable);

        String markTable = "CREATE table MARK ( "
                + "ID   INTEGER NOT NULL "
                + "PRIMARY KEY GENERATED ALWAYS AS IDENTITY"
                + "(START WITH 1, INCREMENT BY 1),"
                + "CHAPTER_ID VARCHAR(1) NOT NULL,"
                + "PAGE_NUMBER INTEGER NOT NULL,"
                + "BEGINPOS   INTEGER NOT NULL,"
                + "ENDPOS   INTEGER NOT NULL,"
                + "TEXT VARCHAR(1000) NOT NULL)";

        this.createTable(markTable);


    }

    public void loadContentA()
            throws Exception {
        String path = "C:\\Users\\hb47537\\Documents\\Development\\JSE\\ePetroGuia\\src\\html\\";

        String query = "select ID,FILE from page p where p.file like 'A%'";
        
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        Statement stmt = dbConnection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while( result.next()){
            File file = new File(path + result.getString(2));


            fis = new FileInputStream(file);

            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            JEditorPane editor = new JEditorPane();
            editor.setContentType("text/html");
            editor.setPage("file:/"+path + result.getString(2));
            Thread.sleep(1000);
            Document doc = editor.getDocument();
            String texto = doc.getText(0, doc.getLength());

            String sql = "update PAGE set FILE_CONTENT = '"+texto+"' where ID = "+result.getInt(1);
            Statement stm = dbConnection.createStatement();
            stm.executeUpdate(sql);

            stm.close();
            System.out.println("pagina "+result.getString(2)+" OK");
        }

        dbConnection.commit();

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();

        stmt.close();
        dbConnection.close();
    }

    private void createTable(String strCreateAddressTable)
            throws Exception {
        boolean bCreatedTables = false;
        Statement statement = null;

        statement = dbConnection.createStatement();
        statement.execute(strCreateAddressTable);
        bCreatedTables = true;

        statement.close();
        if (bCreatedTables) {
            System.out.println("Table has been created!");
        }
    }

    public void close()
            throws Exception {
        dbConnection.close();
    }

    public static void main(String[] args) {

        try {
            initDB init = new initDB();
            //init.createTables();

            init.loadContentA();

            init.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
