/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

/**
 * Abstract Class that represents a primitive petroguia content. It is the former for the classes
 * Comment, Favorite and Mark.
 *
 * @author hb47537
 */
public abstract class Content {

    private int ID;
    private int beginPos;
    private int endpos;
    private String chapterID;
    private Page page;
    

    public Content() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public int getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(int beginPos) {
        this.beginPos = beginPos;
    }

    public int getEndpos() {
        return endpos;
    }

    public void setEndpos(int endpos) {
        this.endpos = endpos;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    
}
