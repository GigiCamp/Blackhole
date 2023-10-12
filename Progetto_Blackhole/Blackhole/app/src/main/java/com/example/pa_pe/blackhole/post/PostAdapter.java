package com.example.pa_pe.blackhole.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pa_pe.blackhole.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


/**
 * The type Post adapter.
 */
public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<Post> msgDtoList = null;

    /**
     * Instantiates a new Post adapter.
     *
     * @param msgDtoList the msg dto list
     */
    public PostAdapter(ArrayList<Post> msgDtoList) {
        this.msgDtoList = msgDtoList;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = this.msgDtoList.get(position);
            holder.post_layout.setVisibility(LinearLayout.VISIBLE);
            holder.user_name.setText(post.getUser_name());
            holder.post_description.setText(post.getDescription());
            holder.post_img.setImageResource(post.getImg());
            holder.post_time.setText(post.getDate_time());
            holder.numero_commenti.setText(post.numero_commenti);
            holder.numero_likes.setText(post.numero_likes);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(msgDtoList==null)
        {
            msgDtoList = new ArrayList<Post>();
        }
        return msgDtoList.size();
    }
}

