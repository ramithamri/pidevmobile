/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author SALMA
 */
public class Reviews {
      private int id ;
   private int rating ;  
   private String title ; 
   private String content ; 

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reviews(int rating, String title, String content) {
        this.rating = rating;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reviews(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reviews{" + "rating=" + rating + ", title=" + title + ", content=" + content + '}';
    }
   
   
   
           
}
