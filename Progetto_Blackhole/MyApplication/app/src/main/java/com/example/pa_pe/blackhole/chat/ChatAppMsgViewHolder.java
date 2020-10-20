package com.example.pa_pe.blackhole.chat;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import androidx.recyclerview.widget.RecyclerView;


/**
 * The type Chat app msg view holder.
 */
public class ChatAppMsgViewHolder extends RecyclerView.ViewHolder {

    /**
     * The Left msg layout.
     */
    RelativeLayout leftMsgLayout;

    /**
     * The Right msg layout.
     */
    RelativeLayout rightMsgLayout;

    /**
     * The Left name text view.
     */
    TextView leftNameTextView;

    /**
     * The Left msg text view.
     */
    TextView leftMsgTextView;

    /**
     * The Right msg text view.
     */
    TextView rightMsgTextView;

    /**
     * The Left time text view.
     */
    TextView leftTimeTextView;

    /**
     * The Right time text view.
     */
    TextView rightTimeTextView;

    /**
     * The Avatar iv.
     */
    CircularImageView avatarIv;

    /**
     * Instantiates a new Chat app msg view holder.
     *
     * @param itemView the item view
     */
    public ChatAppMsgViewHolder(View itemView) {
        super(itemView);

        if(itemView!=null) {
            leftMsgLayout = (RelativeLayout) itemView.findViewById(R.id.chat_left_msg_layout);
            rightMsgLayout = (RelativeLayout) itemView.findViewById(R.id.chat_right_msg_layout);
            leftMsgTextView = (TextView) itemView.findViewById(R.id.chat_left_msg_text_view);
            rightMsgTextView = (TextView) itemView.findViewById(R.id.chat_right_msg_text_view);
            leftTimeTextView = (TextView) itemView.findViewById(R.id.timestamp_left);
            rightTimeTextView = (TextView) itemView.findViewById(R.id.timestamp_right);
            leftNameTextView = (TextView) itemView.findViewById(R.id.tv_user);
            avatarIv = (CircularImageView) itemView.findViewById(R.id.avatarIv);
        }
    }
}
