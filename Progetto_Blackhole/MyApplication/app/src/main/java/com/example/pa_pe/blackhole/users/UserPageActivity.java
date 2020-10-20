package com.example.pa_pe.blackhole.users;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.post.Post;
import com.example.pa_pe.blackhole.post.PostAdapter;
import com.example.pa_pe.blackhole.post.PostList;
import com.mikhaellopez.circularimageview.CircularImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type User page activity.
 */
public class UserPageActivity extends AppCompatActivity {

    /**
     * The User img.
     */
    CircularImageView user_img;
    /**
     * The User name.
     */
    TextView user_name;
    /**
     * The User description.
     */
    TextView user_description;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle("Profilo Amico");

        recyclerView = findViewById(R.id.users_post);

        //Layout (LinearLayout) for RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);
        //recyclerview properties
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        user_img = findViewById(R.id.friend_img);
        user_name = findViewById(R.id.friend_name);
        user_description = findViewById(R.id.friend_desc);

        //Recupero il dato passato (nomeProfilo)
        Bundle nomeProfilo = getIntent().getExtras();
        final String nomePro = nomeProfilo.getString("userName");
        user_name.setText(nomePro);

        //Recupero il dato passato (immagineProfilo)
        Bundle immagineProfilo = getIntent().getExtras();
        final int nomeIma = immagineProfilo.getInt("userImage");
        user_img.setImageResource(nomeIma);

        //Recupero il dato passato (descrizione)
        Bundle descrizione = getIntent().getExtras();
        final String descrizione_utente = immagineProfilo.getString("userDesc");
        user_description.setText(descrizione_utente);

        PostList list = new PostList();

        if(user_name.getText().toString().equals("Aliz_en")){
            list.get_list().add(new Post(user_name.getText().toString(), "La vita è come la programmazione, ci sono degli errori ma non riesci a capire perché *OMEGALUL*", "3", "5", R.drawable.aliz_en));
            list.get_list().add(new Post(user_name.getText().toString(), "Ho appena finito di cucinare un ottimo plum cake, sono così soddisfatto che mi metterò a lavorare alla mia nuova app", "3", "5", R.drawable.aliz_en));
            list.get_list().add(new Post(user_name.getText().toString(), "Il mercato degli AP sta cadendo..non capisco dato che sono così ben fatti al livello hardware e software","3", "5", R.drawable.aliz_en));
            list.get_list().add(new Post(user_name.getText().toString(), "Mia madre mi ha dato una nuova ricetta per dei biscotti..non mi convince ma la proverò", "3", "5", R.drawable.aliz_en));
            list.get_list().add(new Post(user_name.getText().toString(), "Devo comprare un nuovo starphone, avete consigli?", "3", "5", R.drawable.aliz_en));
        }else if(user_name.getText().toString().equals("NashiRose")){
            list.get_list().add(new Post(user_name.getText().toString(), "Oggi a scuola abbiamo fatto le equazioni di Maxwell. CHE BELLA LA MATEMATICA!", "2", "4", R.drawable.nashirose));
            list.get_list().add(new Post(user_name.getText().toString(), "Nuovo record!! Libro comprato ieri pomeriggio già finito!!", "2", "4", R.drawable.nashirose));
            list.get_list().add(new Post(user_name.getText().toString(), "Oggi a scuola 2 ragazzi si sono picchiati, davvero non comprendo la stupidità maschile", "2", "4", R.drawable.nashirose));
            list.get_list().add(new Post(user_name.getText().toString(), "Ho buttato giù qualche idea per un nuovo romanzo, chissà come andrà a finire", "2", "4", R.drawable.nashirose));
            list.get_list().add(new Post(user_name.getText().toString(), "Oggi a scuola abbiamo studiato letteratura antica, mi sorprende come un libro come Harry Potter sia piaciuto a così tante persone.", "2", "4", R.drawable.nashirose));
        }else if(user_name.getText().toString().equals("NoobMaster69")){
            list.get_list().add(new Post(user_name.getText().toString(), "Mi hanno detto che in un certo film del 2019 c'è uno con il mio nome..strano", "1", "4", R.drawable.noobmaster69));
            list.get_list().add(new Post(user_name.getText().toString(), "Spero che qualche squadra di e-sports mi prenda per il torneo universale di petris", "1", "4", R.drawable.noobmaster69));
            list.get_list().add(new Post(user_name.getText().toString(), "Ma che problemi ha mia madre che mi dice di mettere pausa mentre gioco online in vr?", "1", "4", R.drawable.noobmaster69));
            list.get_list().add(new Post(user_name.getText().toString(), "Grande partita di petris oggi : Via lattea vs nana del Drago", "1", "4", R.drawable.noobmaster69));
        }else if(user_name.getText().toString().equals("Tentacool_Lino")){
            list.get_list().add(new Post(user_name.getText().toString(), "Per domani niente compiti, la prof è malata!!! Un po' come NoobMaster69 XDXD", "2", "3", R.drawable.ten));
            list.get_list().add(new Post(user_name.getText().toString(), "Ho voglia di farmi tanti nuovi amici, farò un gruppo con tutti quelli che commentano con \"gamberetti\"", "2", "3", R.drawable.ten));
            list.get_list().add(new Post(user_name.getText().toString(), "Devo comprare una crema per i miei tentacoli ahahaha", "2", "3", R.drawable.ten));
            list.get_list().add(new Post(user_name.getText().toString(), "Non capisco perché i ragazzi sentono il bisogno di picchiarsi...stupidi..", "2", "3", R.drawable.ten));
            list.get_list().add(new Post(user_name.getText().toString(), "Farò una gara di tentacoli più belli..chi vincerà? Io sicuramente!!", "2", "3", R.drawable.ten));
        }

        final PostAdapter chatAppMsgAdapter = new PostAdapter(list.get_list());
        chatAppMsgAdapter.notifyDataSetChanged();

        // Set data adapter to RecyclerView.
        recyclerView.setAdapter(chatAppMsgAdapter);
    }
}
