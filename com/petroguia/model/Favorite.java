/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.petroguia.model;

import com.petroguia.model.Content;


/**
 * Class that represents a favorite, a bookmarked page, made by the user for an chapter´s page.
 * @author hb47537
 */
public class Favorite extends Content{

    private String name;
    private Favorite favorite;

    public Favorite() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    
    
}
