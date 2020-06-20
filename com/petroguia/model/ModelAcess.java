/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;


import com.petroguia.business.*;
import java.util.*;
import com.petroguia.business.DAO.*;



/**
 * Class designed to be the interface between the petroguia business core and the
 * Petroguia GUI, or any kind of GUI.
 *
 * @author hb47537
 */
public class ModelAcess {


    /**
     * Static method used to build a content Manager. A content manager is an object
     * that manages the content business logic. A content means every petroguia pages,
     * favorites, marks and comments.
     *
     * @return ContentInterface object
     */
    public static ContentInterface buildContentManager(){

        ContentModel model = new ContentModel();
        return model;
    }


    /**
     * Static method designed to retrieve an array of formula names.
     *
     * @return an array with the petroguia formula names.
     * @throws Exception
     */
    public static String[] fetchFormulaNames()
      throws Exception
    {
       FormulaDAO dao = new FormulaDAO();
       return dao.fetchFormulas();
    }


    /**
     * Static method designed to retrieve an array of conversion type names.
     *
     * @return an array with the petroguia conversion types.
     * @throws Exception
     */
    public static String[] fetchConversionTypes()
      throws Exception
    {
       ConversionDAO dao = new ConversionDAO();
       return dao.fetchTypes();
    }


    /**
     * Static method designed to retrieve an array of strings, defined by the
     * parameter type name.
     *
     * @return an array with the petroguia conversion types.
     * @throws Exception
     */
    public static String[] fetchConversionDescriptions(String type)
      throws Exception
    {
       ConversionDAO dao = new ConversionDAO();
       return dao.fetchDescriptions(type);
    }


    /**
     * Static method designed to build an object that implements the Formula interface.
     *
     * @param formula name
     * @return
     */
    public static FormulaModel buildFormula(String formula){

        return new FormulaModel(formula);
        
    }


    /**
     * Static method designed to build an object that implements the Formula interface, implements
     * all formula conversions.
     *
     * @param fromIN
     * @param toOUT
     * @return
     * @throws Exception
     */
    public static FormulaModel buildConversionFormula(String fromIN, String toOUT)
      throws Exception
    {

        ConversionDAO dao = new ConversionDAO();

        return new FormulaModel(dao.fetchPageName(fromIN, toOUT));

    }


    
}
