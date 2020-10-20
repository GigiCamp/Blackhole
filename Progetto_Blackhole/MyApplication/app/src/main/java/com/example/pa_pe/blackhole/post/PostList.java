package com.example.pa_pe.blackhole.post;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Post list.
 */
public class PostList implements Serializable {
    private ArrayList<Post> list;

    /**
     * Instantiates a new Post list.
     */
    public PostList(){
        list = new ArrayList<Post>();
    }

    /**
     * Get list array list.
     *
     * @return the array list
     */
    public ArrayList<Post> get_list(){
        return this.list;
    }
}
