package com.example.pa_pe.blackhole.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.users.AdapterUsers;
import com.example.pa_pe.blackhole.users.ModelUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment {

    /**
     * The Recycler view.
     */
    RecyclerView recyclerView;
    /**
     * The Adapter users.
     */
    AdapterUsers adapterUsers;
    /**
     * The User list.
     */
    List<ModelUser> userList;
    /**
     * The New message.
     */
    ImageView newMessage;

    /**
     * Instantiates a new Chats fragment.
     */
    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        recyclerView = view.findViewById(R.id.users_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVerticalScrollBarEnabled(true);

        //init user list
        userList = new ArrayList<>();

        //getAll users
        try {
            getAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        newMessage = view.findViewById(R.id.newMessage);
        return view;
    }

    private void getAllUsers() throws IOException {

        File file = new File(recyclerView.getContext().getApplicationContext().getFilesDir(), "users.dat");
        if(file.exists()) {
            try {
                userList = load_users(recyclerView.getContext(), "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            ModelUser m1 = new ModelUser("Sconosciuto", R.drawable.ic_account, View.VISIBLE);
            userList.add(m1);
            save_users(recyclerView.getContext(), (ArrayList<ModelUser>) userList, "users.dat");
        }

        //adapter
        adapterUsers = new AdapterUsers(getActivity(), userList);
        adapterUsers.notifyDataSetChanged();

        //set adapter to recycler view
        recyclerView.setAdapter(adapterUsers);
    }

    /**
     * Save users.
     *
     * @param context  the context
     * @param chat     the chat
     * @param filename the filename
     * @throws IOException the io exception
     */
    static public void save_users(Context context, ArrayList<ModelUser> chat, String filename) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(chat);
        os.close();
        fos.close();
    }

    /**
     * Load users array list.
     *
     * @param context  the context
     * @param filename the filename
     * @return the array list
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    static public ArrayList<ModelUser> load_users(Context context, String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(filename);
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList<ModelUser> simpleClass = (ArrayList<ModelUser>) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            getAllUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
