/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import com.petroguia.model.Content;
import java.net.URL;


/**
 * Class that represents a mark made by the user for an chapter´s page.
 *
 * @author hb47537
 */
public class Mark extends Content{

    private String text;
    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
