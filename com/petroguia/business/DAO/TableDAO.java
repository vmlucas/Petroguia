/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.business.DAO;


import java.util.*;
import com.petroguia.model.*;
import java.sql.*;
import org.apache.derby.jdbc.*;
import javax.swing.*;
import javax.swing.table.*;


/**
 *
 * @author hb47537
 */
public class TableDAO {



    public JTable generateTable( String page_id )
      throws Exception
    {
        Connection conn = DAO.getInstance().getConnection();
        Statement stm = conn.createStatement();
        String query ="select t.name,c.name,c.id from petro_table t, petro_table_column c where t.page_id = '"+page_id+"' "+
                        "and t.id = c.table_id   ";
        ResultSet result = stm.executeQuery(query);
        
        JTable table = new JTable();
        DefaultTableColumnModel model = new DefaultTableColumnModel();

        while(result.next()){
            table.setName(result.getString("t.name"));

            TableColumn column = new TableColumn();
            column.setHeaderValue(result.getString("c.name"));
            column.setIdentifier(result.getString("c.id"));

            model.addColumn(column);
        }
        table.setColumnModel(model);

        return table;
    }


    public JTable searchTable( JTable table )
      throws Exception
    {
        Connection conn = DAO.getInstance().getConnection();
        Statement stm = conn.createStatement();
        String query ="select r.column_id, r.value form petro_table t, petro_table_column c, petro_table_row r "+
                       "where t.name = '"+table.getName()+"' "+
                        "and t.id = c.table_id and c.table_id = r.table_id ";
        
        int numberColumns = table.getColumnModel().getColumnCount();
        for( int i=1; i < numberColumns +1; i++){
            Object value = table.getValueAt(1, i);
            if( value != null)
            {
                query = query +"and r.column_id = "+i+" "+
                               "and r.value = '"+value.toString()+"' ";
            }
        }
        query = query +"order by r.column_id";

        ResultSet result = stm.executeQuery(query);
        int rowIndex = 1;
        int columnIndex = 1;
        
        while(result.next()){
            int column = result.getInt("r.column_id");
            if( column != columnIndex){
                columnIndex = column;
                rowIndex = 1;
            }
            table.setValueAt(result.getString("r.value"), rowIndex, columnIndex);

            rowIndex++;
        }
        
        return table;
    }
}
