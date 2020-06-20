/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;


/**
 * TEMPORARY CLASS
 * Class used to apply any petroguia conversion.
 *
 * @author hb47537
 */
public class Conversion {

    public enum Type{
        TEMPERATURA, VOLUME
    }

    public enum Conversions{
        Celsius,Fahrenheit,
        litro, m3, barril
    }

    /**
     * Conversions
     */
    public double convert( Conversion.Conversions from, Conversion.Conversions to ,double value  ){

        double result = 0.0;
        if(( from == Conversions.Celsius)&&(to == Conversions.Fahrenheit)){
           result = (value * 1.8)+32.0;
        }
        else if((from == Conversions.Fahrenheit) && (to == Conversions.Celsius))
        {
           result = (value - 32.0)/1.8;
        }
        else if((from == Conversions.litro) && (to == Conversions.m3))
        {
           result = value / 1000.0;
        }
        else if((from == Conversions.m3) && (to == Conversions.litro))
        {
           result = value * 1000.0;
        }
        else if((from == Conversions.litro) && (to == Conversions.barril))
        {
           result = value * 0.00628981077;
        }
        else if((from == Conversions.barril) && (to == Conversions.litro))
        {
           result = value/ 0.00628981077;
        }
        else if((from == Conversions.m3) && (to == Conversions.barril))
        {
           double temp = value * 1000.0;
           result = temp* 0.00628981077;
        }
        else if((from == Conversions.barril) && (to == Conversions.m3))
        {
           double temp = value / 0.00628981077;
           result = temp / 1000.0;
        }
        else result = value;

        return result;

    }
    
}
