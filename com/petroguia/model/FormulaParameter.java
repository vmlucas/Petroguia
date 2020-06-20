/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import java.util.List;

/**
 *
 * @author hb47537
 */
public class FormulaParameter {

    private String ID;
    private String name;
    private java.util.List<String> units;
    private double value;
    private String currentUnit;

    public FormulaParameter() {

        units = new java.util.LinkedList<String>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUnits() {
        return units;
    }

    public void addUnit( String unit) {
        units.add(unit);
    }

    

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit(String currentUnit) {
        this.currentUnit = currentUnit;
    }

    
    

}
