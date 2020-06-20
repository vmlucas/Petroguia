/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import java.util.*;


/**
 *
 * @author hb47537
 */
public interface Formula {

    public Collection getinParameters() throws Exception;
    public FormulaParameter getoutParameter() throws Exception;
    public FormulaParameter calculate(Collection inParams, FormulaParameter outParam) throws Exception;


}
