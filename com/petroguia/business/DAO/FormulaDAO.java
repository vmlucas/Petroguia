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
 * Class designed to manage with the formula objects on the database
 * @author hb47537
 */
public class FormulaDAO {

    public FormulaDAO() {
    }

    
    /**
     * Method designed to fetch the formulas types and descriptions from the database
     * @return
     * @throws Exception
     */
    public String[] fetchFormulas( )
      throws Exception
    {

        Connection conn = DAO.getInstance().getConnection();
        ArrayList<String> list = new ArrayList<String>();

        String query = "select PAGE_NAME from Page where TYPE = 'FORMULA'";
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


    public double fetchConstantValue(String paramID, String paramUnits)
      throws Exception
    {
        double value = 0.0;
        Connection conn = DAO.getInstance().getConnection();
        String query = "select value from Formula_constant f "+
                "where f.constant='"+paramID+"' and f.params = '"+paramUnits+"'";
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery(query);
        if( result.next()){
            value = result.getDouble(1);
        }

        result.close();
        stm.close();
        conn.close();

        return value;
    }

     public Collection getinParameters(String formulaName)
       throws Exception
     {
         Connection conn = DAO.getInstance().getConnection();
        ArrayList<FormulaParameter> list = new ArrayList<FormulaParameter>();

        String query = "select fp.param, fp.description, fp.unit from Page p, Formula f, FORMULA_PARAM fp "+
                          "where f.PAGE_ID = p.PAGE_ID and f.id = fp.formula_ID and fp.param <>'OUT' "+
                          "and p.PAGE_NAME = '"+formulaName+"'";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while( result.next()){
           FormulaParameter param = new FormulaParameter();
           param.setID(result.getString(1));
           param.setName(result.getString(2));

           String units = result.getString(3);
           StringTokenizer tok = new StringTokenizer(units,",");
           while( tok.hasMoreTokens()){
               param.addUnit(tok.nextToken());
           }


           list.add( param);
        }


        result.close();
        stmt.close();
        conn.close();

       
        return list;
    }


       public FormulaParameter getoutParameter(String formulaName)
       throws Exception
     {
         Connection conn = DAO.getInstance().getConnection();
        FormulaParameter param = null;

        String query = "select fp.param, fp.description, fp.unit from Page p, Formula f, FORMULA_PARAM fp "+
                          "where f.PAGE_ID = p.PAGE_ID and f.id = fp.formula_ID and fp.param ='OUT' "+
                          "and p.PAGE_NAME = '"+formulaName+"'";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);

        if( result.next()){
           param = new FormulaParameter();
           param.setID(result.getString(1));
           param.setName(result.getString(2));

           String units = result.getString(3);
           StringTokenizer tok = new StringTokenizer(units,",");
           while( tok.hasMoreTokens()){
               param.addUnit(tok.nextToken());
           }

        }


        result.close();
        stmt.close();
        conn.close();


        return param;
    }

    //fetchMathFormula(formula);
    public String fetchMathFormula(String formulaName)
       throws Exception
     {
         Connection conn = DAO.getInstance().getConnection();
         String formula = null;

         String query = "select f.math_function from Page p, Formula f "+
                          "where f.PAGE_ID = p.PAGE_ID "+
                          "and p.PAGE_NAME = '"+formulaName+"'";
         Statement stmt = conn.createStatement();
         ResultSet result = stmt.executeQuery(query);

        if( result.next()){
           formula = result.getString(1);
        }


        result.close();
        stmt.close();
        conn.close();


        return formula;
    }
}
