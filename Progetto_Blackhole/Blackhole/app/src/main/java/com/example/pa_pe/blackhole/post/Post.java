package com.example.pa_pe.blackhole.post;

import java.io.Serializable;

/**
 * The type Post.
 */
public class Post implements Serializable {
    /**
     * The User name.
     */
    String user_name;
    /**
     * The Date time.
     */
    String date_time;
    /**
     * The Description.
     */
    String description;
    /**
     * The Numero commenti.
     */
    String numero_commenti;
    /**
     * The Numero likes.
     */
    String numero_likes;
    /**
     * The Img.
     */
    int img;

    /**
     * Instantiates a new Post.
     */
    public Post(){

    }

    /**
     * Instantiates a new Post.
     *
     * @param user_name       the user name
     * @param description     the description
     * @param numero_commenti the numero commenti
     * @param numero_likes    the numero likes
     * @param image           the image
     */
    public Post(String user_name, String description, String numero_commenti, String numero_likes, int image){
        this.user_name = user_name;
        this.description = description;
        this.numero_commenti = numero_commenti;
        this.numero_likes = numero_likes;
        this.img = image;
    }

    /**
     * Instantiates a new Post.
     *
     * @param user_name       the user name
     * @param description     the description
     * @param risp_user       the risp user
     * @param numero_commenti the numero commenti
     * @param numero_likes    the numero likes
     * @param image           the image
     */
    public Post(String user_name, String description, String risp_user, String numero_commenti, String numero_likes, int image){
        this.user_name = user_name;
        this.date_time = date_time;
        this.description = description;
        this.numero_commenti = risp_user;
        this.numero_likes = numero_likes;
        this.img = image;
    }

    /**
     * Instantiates a new Post.
     *
     * @param user_name   the user name
     * @param description the description
     * @param date_time   the date time
     */
    public Post(String user_name, String description, String date_time){
        this.user_name = user_name;
        this.description = description;
        this.date_time = date_time;
    }

    /**
     * Instantiates a new Post.
     *
     * @param user_name   the user name
     * @param description the description
     * @param image       the image
     */
    public Post(String user_name, String description, int image){
        this.user_name = user_name;
        this.description = description;
        this.img = image;
    }


    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * Sets user name.
     *
     * @param user_name the user name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Gets date time.
     *
     * @return the date time
     */
    public String getDate_time() {
        return date_time;
    }

    /**
     * Sets date time.
     *
     * @param date_time the date time
     */
    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    /**
     * Gets img.
     *
     * @return the img
     */
    public int getImg() {
        return img;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
