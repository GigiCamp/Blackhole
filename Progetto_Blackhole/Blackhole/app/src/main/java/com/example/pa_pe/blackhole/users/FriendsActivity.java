package com.example.pa_pe.blackhole.users;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.pa_pe.blackhole.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Friends activity.
 */
public class FriendsActivity extends AppCompatActivity {
    /**
     * The Recycler view.
     */
    RecyclerView recyclerView;
    /**
     * The Adapter users.
     */
    AdapterFriends adapterUsers;
    /**
     * The User list.
     */
    List<ModelUser> userList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle("Lista Amici");

        recyclerView = findViewById(R.id.users_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //init user list
        userList = new ArrayList<>();

        //getAll users
        getAllUsers();
    }

    private void getAllUsers() {

        ModelUser m1 = new ModelUser("Aliz_en", R.drawable.aliz_en,  "Ciao a tutti, sono Aliz_en! Mi piace l'informatica e tutte le cose tecnologiche...ma la cosa che non sa nessuno è che amo cucinare dolci");
        userList.add(m1);

        ModelUser m2 = new ModelUser("NashiRose", R.drawable.nashirose, "Io adoro i libri, mi piacciono così tanto che non smetterei mai di leggerli. Da grande voglio diventare una grande scrittrice!");
        userList.add(m2);

        ModelUser m3 = new ModelUser("NoobMaster69", R.drawable.noobmaster69,"Giocare giocare giocare giocare giocare giocare giocare giocare giocare giocareeeeeeeee");
        userList.add(m3);

        ModelUser m4 = new ModelUser("Tentacool_Lino", R.drawable.ten, "Sono piccolo, viola e simpatico, ma la parte migliore di me sono i miei tentacoli! Ahahahah li adoro");
        userList.add(m4);

        //adapter
        adapterUsers = new AdapterFriends(this, userList);

        //set adapter to recycler view
        recyclerView.setAdapter(adapterUsers);
    }
}
