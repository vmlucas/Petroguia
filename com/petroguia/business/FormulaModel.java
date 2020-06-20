/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petroguia.business;


import com.petroguia.model.*;
import com.petroguia.business.DAO.*;
import java.util.*;


/**
 * Class designed to manage all system formula. Manage formulas parameters, and
 * calculate the out parameter based on formulas stored on the database.
 *
 * @author hb47537
 */
public class FormulaModel implements Formula{

    private String formula;
    private FormulaDAO dao;
    private Collection inParams;
    private FormulaParameter outParam;


    /**
     * Constructor
     * Sets the formula name that will be managed by the class object.
     *
     * @param formula name
     */
    public FormulaModel(String formula) {
       this.formula = formula;
       dao = new FormulaDAO();

    }


    /**
     * Method designed to calculate the output value, using the inputs values,
     * current units and the current formula.
     *
     * @param inParams
     * @param outParam
     * @return
     * @throws Exception
     */
    @Override
    public FormulaParameter calculate(Collection inParams, FormulaParameter outParam) throws Exception {

        this.inParams = inParams;
        this.outParam = outParam;
        String mathFormula = dao.fetchMathFormula(formula);
        double value = calculate( mathFormula);

        outParam.setValue(value);
        return outParam;
    }


    private double calculate( String mathFormula)
      throws Exception
    {
        double value = 0.0;
        String temp = "";
        char oper=' ';
        char[] chars = mathFormula.toCharArray();
        for(int i=0; i<mathFormula.length();i++){
            
            if( chars[i] == '('){
              int index = mathFormula.indexOf(")");
              String texto = mathFormula.substring(i+1, index+1);
              i = index;
              if( oper == ' '){
                 value = calculate( texto );
              }
              else{
                 if( oper == '+')
                         value = value + calculate( texto );
                 else if(oper == '-')
                         value = value - calculate( texto );
                 else if(oper == '*')
                         value = value * calculate( texto );
                 else
                         value = value / calculate( texto );

              }
            }//verificar se o caracter não é uma letra ou numero
            else if(( chars[i] == '+')||
                    (chars[i] == '-')||
                    (chars[i] == '*')||
                    (chars[i] == '/')
                    ){
                oper = chars[i];
            }
            else if(chars[i] == ')')
            {
              return value;
            }
            else{
               temp = temp + chars[i];
            }

            if( temp.length() > 1){
                   if( oper == ' '){
                      FormulaParameter param1 = searchParameter(temp);
                      if( param1 == null){
                          value = fetchConstantValue(temp);
                      }
                      else
                      {
                         value = param1.getValue();
                      }
                      temp = "";
                   }
                   else{
                      double valorParam = 0.0;
                      FormulaParameter param1 = searchParameter(temp);
                      if( param1 == null){
                          valorParam = fetchConstantValue(temp);
                      }
                      else{
                          valorParam = param1.getValue();
                      }
                      if( oper == '+')
                         value = value + valorParam;
                      else if(oper == '-')
                         value = value - valorParam;
                      else if(oper == '*')
                         value = value * valorParam;
                       else
                         value = value / valorParam;                         

                      temp = "";
                   }
            }

        }
        
        return value;
    }


    private FormulaParameter searchParameter( String id ){
        FormulaParameter param = null;
        Iterator it = inParams.iterator();
          while( it.hasNext()){
              FormulaParameter temp = (FormulaParameter)it.next();
              if( temp.getID().equals(id)){
                  param = temp;
                  break;
              }
          }


        return param;
    }


    private double fetchConstantValue(String id )
      throws Exception
    {

        Iterator it = inParams.iterator();
        FormulaParameter temp = (FormulaParameter)it.next();
        String paramUnits = temp.getCurrentUnit();
        while( it.hasNext()){
              temp = (FormulaParameter)it.next();
              paramUnits = paramUnits+","+temp.getCurrentUnit();
        }
        paramUnits = paramUnits+","+outParam.getCurrentUnit();
        double value = dao.fetchConstantValue(id, paramUnits);

        /*if( value == 0.0){
            throw new Exception("Configuração de unidades erradas");
        }*/
        return value;
    }
    

    /**
     * Method designed to retrieve a collection of parameters that represents
     * the formula input.
     *
     * @return collection of FormulaParameter
     * @throws Exception
     */
    @Override
    public Collection getinParameters() throws Exception{
       return dao.getinParameters(formula);
    }


    /**
     * Method designed to retrieve the formula output parameter object.
     *
     * @return FormulaParameter object
     * @throws Exception
     */
    @Override
    public FormulaParameter getoutParameter() throws Exception{
        return dao.getoutParameter(formula);
    }


}
