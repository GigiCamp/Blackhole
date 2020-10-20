package com.example.pa_pe.blackhole.users;

import android.view.View;

import com.example.pa_pe.blackhole.post.Post;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Model user.
 */
public class ModelUser implements Serializable {

    /**
     * The Name.
     */
    String name;
    /**
     * The Image.
     */
    int image;
    /**
     * The Post list.
     */
    ArrayList<Post> post_list;
    /**
     * The Members.
     */
    ArrayList<ModelUser> members;
    /**
     * The New messages.
     */
    int new_messages;
    /**
     * The Descrizione.
     */
    String descrizione;

    /**
     * Instantiates a new Model user.
     */
    public ModelUser() {
    }

    /**
     * Instantiates a new Model user.
     *
     * @param name         the name
     * @param image        the image
     * @param new_messages the new messages
     */
    public ModelUser(String name, int image, int new_messages) {
        this.name = name;
        this.image = image;
        this.new_messages = new_messages;
    }

    /**
     * Instantiates a new Model user.
     *
     * @param name  the name
     * @param image the image
     */
    public ModelUser(String name, int image) {
        this.name = name;
        this.image = image;
    }

    /**
     * Instantiates a new Model user.
     *
     * @param name      the name
     * @param image     the image
     * @param post_list the post list
     */
    public ModelUser(String name, int image, ArrayList<Post> post_list) {
        this.name = name;
        this.image = image;
        this.post_list = post_list;
    }

    /**
     * Instantiates a new Model user.
     *
     * @param image        the image
     * @param name         the name
     * @param members      the members
     * @param new_messages the new messages
     */
    public ModelUser(int image, String name, ArrayList<ModelUser> members, int new_messages){
        this.name = name;
        this.image = image;
        this.members = members;
        this.new_messages = new_messages;
    }

    /**
     * Instantiates a new Model user.
     *
     * @param name        the name
     * @param image       the image
     * @param descrizione the descrizione
     */
    public ModelUser(String name, int image, String descrizione){
        this.name = name;
        this.image = image;
        this.descrizione = descrizione;
        this.new_messages = View.GONE;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public int getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     * Sets new messages.
     *
     * @param new_messages the new messages
     */
    public void setNew_messages(int new_messages) {
        this.new_messages = new_messages;
    }

    /**
     * Get new messages int.
     *
     * @return the int
     */
    public int getNew_messages(){
        return this.new_messages;
    }

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Gets members.
     *
     * @return the members
     */
    public ArrayList<ModelUser> getMembers() {
        return members;
    }
}
