/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.ui;

import com.petroguia.model.*;
import javax.swing.JToggleButton;
import java.util.*;
import com.petroguia.business.*;

/**
 *
 * @author hb47537
 */
public interface UInterface {

    public int getMainWindowWidth();
    public int getMainWindowHeight();
    public javax.swing.JFrame getMainWindow();
    public void validate();
    public BreadCramp getBradCramp();
    public void setBookmarked();
    public void setUnbookmarked();
    public void displayCommentCountButton(String value);
    public void hideCommentCountButton();
    public Chapter getChapter();
    public Page getCurrentPage();
    public void setCurrentPage(Page page) throws Exception;
    
    //public void processURL(Page page) throws Exception;
   //public java.net.URL getCurrentURL();
    public LinkedList<Comment> verifyComments();
    public void closeCommentFrame();
}
