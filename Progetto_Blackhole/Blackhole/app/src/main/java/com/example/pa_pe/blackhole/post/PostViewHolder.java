package com.example.pa_pe.blackhole.post;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import androidx.recyclerview.widget.RecyclerView;


/**
 * The type Post view holder.
 */
public class PostViewHolder extends RecyclerView.ViewHolder {

    /**
     * The Post layout.
     */
    RelativeLayout post_layout;

    /**
     * The User name.
     */
    TextView user_name;

    /**
     * The Post img.
     */
    CircularImageView post_img;

    /**
     * The Post time.
     */
    TextView post_time;

    /**
     * The Post description.
     */
    TextView post_description;

    /**
     * The Numero commenti.
     */
    TextView numero_commenti;

    /**
     * The Numero likes.
     */
    TextView numero_likes;


    /**
     * Instantiates a new Post view holder.
     *
     * @param itemView the item view
     */
    public PostViewHolder(View itemView) {
        super(itemView);

        if(itemView!=null) {
            post_layout = (RelativeLayout) itemView.findViewById(R.id.post_layout);
            user_name = (TextView) itemView.findViewById(R.id.post_user_name);
            post_time = (TextView) itemView.findViewById(R.id.post_time);
            post_description = (TextView) itemView.findViewById(R.id.post_description);
            numero_commenti = (TextView) itemView.findViewById(R.id.numero_commenti);
            numero_likes = (TextView) itemView.findViewById(R.id.numero_likes);
            post_img = (CircularImageView) itemView.findViewById(R.id.post_profile_image);

        }
    }
}

