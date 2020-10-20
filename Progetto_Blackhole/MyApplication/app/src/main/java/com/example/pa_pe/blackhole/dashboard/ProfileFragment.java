package com.example.pa_pe.blackhole.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pa_pe.blackhole.post.Post;
import com.example.pa_pe.blackhole.post.PostAdapter;
import com.example.pa_pe.blackhole.post.PostList;
import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.users.ModelUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private int checkpoint;
    /**
     * The Image.
     */
    CircularImageView image;

    /**
     * Instantiates a new Profile fragment.
     */
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView = view.findViewById(R.id.users_post);

        //Layout (LinearLayout) for RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setStackFromEnd(false);
        //recyclerview properties
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        image = view.findViewById(R.id.user_image);
        image.setImageResource(R.drawable.simon);


        ArrayList<ModelUser> users = new ArrayList<ModelUser>();
        try {
            users = load_users(view.getContext(), "users.dat");
            checkpoint = load_checkpoint(view.getContext(), "checkpoint.dat");
            if(checkpoint == 511){
                checkpoint = 57;
                users.get(1).setNew_messages(View.VISIBLE);
                save_users(view.getContext(), users, "users.dat");
                save_checkpoint(view.getContext(), checkpoint, "checkpoint.dat");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PostList list = new PostList();

        list.get_list().add(new Post("Simon", "E' arrivato il momento di salutarci!", "4", "0", R.drawable.simon));
        list.get_list().add(new Post("Simon", "Penso che gli insetti abbiano una vita più interessante della mia, sto iniziando ad invidiare la vita delle farfalle", "2", "0", R.drawable.simon));
        list.get_list().add(new Post("Simon", "Ho trovato uno strano scarafaggio con un solo occhio nel mio giardino...l'ho schiacciato...",  "3", "0", R.drawable.simon));
        list.get_list().add(new Post("Simon", "Oggi ho visto un delfino!! Grazie mamma per la bellissima gita all'acquario!", "2", "4", R.drawable.simon));

        // Create the data adapter with above data list.
        final PostAdapter chatAppMsgAdapter = new PostAdapter(list.get_list());
        chatAppMsgAdapter.notifyDataSetChanged();

        // Set data adapter to RecyclerView.
        recyclerView.setAdapter(chatAppMsgAdapter);

        return view;
    }

    /**
     * Load post post list.
     *
     * @param context  the context
     * @param filename the filename
     * @return the post list
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    static public PostList load_post(Context context, String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        ObjectInputStream is = new ObjectInputStream(fis);
        PostList simpleClass = (PostList) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }

    /**
     * Save checkpoint.
     *
     * @param context    the context
     * @param checkpoint the checkpoint
     * @param filename   the filename
     * @throws IOException the io exception
     */
    static public void save_checkpoint(Context context, int checkpoint, String filename) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(checkpoint);
        os.close();
        fos.close();
    }

    /**
     * Load checkpoint int.
     *
     * @param context  the context
     * @param filename the filename
     * @return the int
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    static public int load_checkpoint(Context context, String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        ObjectInputStream is = new ObjectInputStream(fis);
        int simpleClass = (int) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }

    /**
     * Load users array list.
     *
     * @param c        the c
     * @param filename the filename
     * @return the array list
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    static public ArrayList<ModelUser> load_users(Context c, String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = c.openFileInput(filename);
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList<ModelUser> simpleClass = (ArrayList<ModelUser>) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }

    /**
     * Save users.
     *
     * @param c        the c
     * @param chat     the chat
     * @param filename the filename
     * @throws IOException the io exception
     */
    static public void save_users(Context c, ArrayList<ModelUser> chat, String filename) throws IOException {
        FileOutputStream fos = c.openFileOutput(filename, c.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(chat);
        os.close();
        fos.close();
    }


}
