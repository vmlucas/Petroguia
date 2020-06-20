/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import java.util.Date;


/**
 * Class that represents a comment made by the user for an chapter´s page.
 *
 * @author hb47537
 */
public class Comment extends Content{

    private String text;
    private java.util.Date timeStamp;
    

    public Comment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    
    
}
