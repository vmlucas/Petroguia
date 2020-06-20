/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import java.net.URL;
import java.util.*;


/**
 *
 * @author hb47537
 */
public class Page {

    private String name;
    private String ID;
    private java.net.URL url;
    private String type;
    private String file_content;
    private int version;
    private LinkedList<Page> childs;

    public Page() {
        childs = new LinkedList<Page>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFile_content() {
        return file_content;
    }

    public void setFile_content(String file_content) {
        this.file_content = file_content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LinkedList<Page> getChilds() {
        return childs;
    }

    public void addChild(Page child) {
        childs.add( child );
    }


    

}
