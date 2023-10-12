package com.example.pa_pe.blackhole.users;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Adapter friends.
 */
public class AdapterFriends extends RecyclerView.Adapter<AdapterFriends.MyHolder>{

    /**
     * The Context.
     */
    Context context;
    /**
     * The User list.
     */
    List<ModelUser> userList;


    /**
     * Instantiates a new Adapter friends.
     *
     * @param context  the context
     * @param userList the user list
     */
    public AdapterFriends(Context context, List<ModelUser> userList) {
            this.context = context;
            this.userList = userList;
        }

        @NonNull
        @Override
        public AdapterFriends.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent, false);

            return new AdapterFriends.MyHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull AdapterFriends.MyHolder holder, int i) { //Chiama questo metodo per vedere i dati in una data posizione

            //Get data
            final int userImage = userList.get(i).getImage();
            final String userName = userList.get(i).getName();
            final String userDesc = userList.get(i).getDescrizione();
            final int new_messages = userList.get(i).getNew_messages();

            //Set data
            holder.mNameTv.setText(userName);
        /*String url = "https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg";
        Picasso.get().load(url).placeholder(R.drawable.ic_android_black_24dp).into(holder.mAvatarIv);*/
            holder.mAvatarIv.setImageResource(userImage);
            holder.mNewMessage.setVisibility(new_messages);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    richiamoPagina(userName, userImage, userDesc);
                }
            });
        }

        private void richiamoPagina(String nomeProfilo, int immagineProfilo, String descrizione){
            Intent intent = new Intent(context, UserPageActivity.class);
            intent.putExtra("userName", nomeProfilo);
            intent.putExtra("userImage", immagineProfilo);
            intent.putExtra("userDesc", descrizione);
            context.startActivity(intent);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

    /**
     * The type My holder.
     */
    class MyHolder extends RecyclerView.ViewHolder{

        /**
         * The M avatar iv.
         */
        ImageView mAvatarIv;
        /**
         * The M name tv.
         */
        TextView mNameTv;
        /**
         * The M new message.
         */
        ImageView mNewMessage;

        /**
         * Instantiates a new My holder.
         *
         * @param itemView the item view
         */
        public MyHolder(@NonNull View itemView) {
                super(itemView);

                mAvatarIv = itemView.findViewById(R.id.avatarIv);
                mNameTv = itemView.findViewById(R.id.nameTv);
                mNewMessage = itemView.findViewById(R.id.newMessage);
            }
        }
    }
