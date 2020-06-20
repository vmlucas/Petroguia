/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import java.util.*;

/**
 * Class that represents a petroguia chapter with a page collection. Each page has
 * a collection of page´s node.
 *
 * @author hb47537
 */
public class Chapter {

    private String id;
    private String description;
    private Vector<Page> pages;

    public Chapter() {
        pages = new Vector<Page>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector<Page> getPages() {
        return pages;
    }

    public Page getPage( int index){
        return (Page)pages.get(index);
    }

    public void addPage(int index, Page page){
        pages.add(index, page);
    }

}
