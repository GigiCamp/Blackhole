package com.example.pa_pe.blackhole.dashboard;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.post.Post;
import com.example.pa_pe.blackhole.post.PostAdapter;
import com.example.pa_pe.blackhole.post.PostList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    private RecyclerView recyclerView;
    private int checkpoint;


    /**
     * Instantiates a new Home fragment.
     */
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.all_users_posts_list);

        //Layout (LinearLayout) for RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setStackFromEnd(false);
        //recyclerview properties
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        PostList list = new PostList();
        File file = new File(getContext().getFilesDir(), "post.dat");
        if(file.exists()) {
            try {
                list = load_post(view.getContext(), "post.dat");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            list.get_list().add(create_post());
        }else{
            list.get_list().add(new Post("Tentacool_Lino", "Per domani niente compiti, la prof è malata!!! Un po' come NoobMaster69 XDXD", "2", "10", R.drawable.ten));
            list.get_list().add(new Post("Nashi_rose", "Oggi a scuola abbiamo fatto le equazioni di Maxwell. CHE BELLA LA MATEMATICA!", "1", "4", R.drawable.nashirose));
            list.get_list().add(new Post("Aliz_en", "La vita è come la programmazione, ci sono degli errori ma non riesci a capire perché *OMEGALUL*", "2", "6", R.drawable.aliz_en));
            try {
                save_post(view.getContext(), list, "post.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            checkpoint = load_checkpoint(view.getContext(), "checkpoint.dat");
            if(checkpoint == 430){
                File file1 = new File(getContext().getFilesDir(), "post.dat");
                file1.delete();
                list.get_list().add(new Post("Aliz_en", "Mi dispiace che sia dovuto capitare a lui..era un bravo ragazzo", "0", "0", R.drawable.aliz_en));
                list.get_list().add(new Post("Nashi_rose", "Sono troppo turbata non riesco neanche più a studiare", "0", "0", R.drawable.nashirose));
                list.get_list().add(new Post("NoobMaster69", "Amico mio...", "0", "0", R.drawable.noobmaster69));
                list.get_list().add(new Post("Tentacool_Lino", "Oggi avremmo dovuto battere il record insieme.. povero Simon", "0", "0", R.drawable.ten));
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.Theme_MaterialComponents_Dialog);
                // Ask the final question
                builder.setMessage("Complimenti hai completato il gioco! Clicca su Logout nel menu a tendina per tornare al menu principale.");
                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     checkpoint = 431;
                        try {
                            save_checkpoint(view.getContext(), checkpoint, "checkpoint.dat");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            save_post(view.getContext(), list, "post.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the data adapter with above data list.
        final PostAdapter chatAppMsgAdapter = new PostAdapter(list.get_list());
        chatAppMsgAdapter.notifyDataSetChanged();

        // Set data adapter to RecyclerView.
        recyclerView.setAdapter(chatAppMsgAdapter);

        return  view;
    }

    private Post create_post(){
        Random random = new Random();
        int index_names = random.nextInt(10);
        String[] names = {"Pubblicità Starphone", "Pubblicità Aliephone", "Pubblicità PaySpace", "Pubblicità BurgerGalaxy", "Avvocato Saul Goodalien", "Pubblicità CyberTruck", "Aliz_en", "Nashi_rose", "NoobMaster69", "Tentacool_Lino"};
        String name = names[index_names];
        if(name.equals("Pubblicità Starphone")){
            return new Post(name, new String("Non fidatevi della concorrenza. Il nuovo Starphone Nova è il migliore sul mercato!"), "1.234", "10.987", R.drawable.ads);
        }else if(name.equals("Pubblicità Aliephone")){
            return new Post(name, new String("La presentazione per il nuovo Aliephone X è prevista per il mese prossimo. Aprite le porte ad un nuovo futuro!"), "1.118", "12.456",  R.drawable.ads);
        }else if(name.equals("Pubblicità PaySpace")){
            return new Post(name, new String("Fai acquisti con la PaySpace per ricevere 10 crediti da spendere per il prossimo acquisto!"), "1.023", "8.543", R.drawable.ads);
        }else if(name.equals("Pubblicità BurgerGalaxy")){
            return new Post(name, new String("Vieni a provare il gusto indimenticabile del 3055. Da oggi disponibile in tutti i ristoranti della galassia."), "3.512", "15.232", R.drawable.ads);
        }else if(name.equals("Avvocato Saul Goodalien")){
            return new Post(name, new String("Io sono Saul Goodalien e credo che ogni essere vivente meriti i propri diritti dinanzi alla Costituzione Galattica. Better Call Saul!"), "115", "510", R.drawable.ads);
        }else if(name.equals("Pubblicità CyberTruck")){
            return new Post(name, new String("Vuoi sicurezza per i tuoi bambini? Vuoi portarti dietro la tua CyberBike? Allora devi scegliere la nuova CyberTruck."), "890", "7.342", R.drawable.ads);
        }else if(name.equals("Aliz_en")){
            Random random1 = new Random();
            int index_msg = random.nextInt(4);
            String[] msg = {"Ho appena finito di cucinare un ottimo plum cake, sono così soddisfatto che mi metterò a lavorare alla mia nuova app", "Il mercato degli AP sta crollando...non capisco dato che sono così ben fatti al livello hardware e software", "Mia madre mi ha dato una nuova ricetta per dei biscotti...non mi convince ma la proverò", "Devo comprare un nuovo starphone, avete consigli?"};
            return new Post(name, msg[index_msg], "3", "5", R.drawable.aliz_en);
        }else if(name.equals("Nashi_rose")){
            Random random1 = new Random();
            int index_msg = random.nextInt(4);
            String[] msg = {"Nuovo record!! Libro comprato ieri pomeriggio già finito!!", "Oggi a scuola 2 ragazzi si sono picchiati, davvero non comprendo la stupidità maschile", "Ho buttato giù qualche idea per un nuovo romanzo, chissà come andrà a finire", "Oggi a scuola abbiamo studiato letteratura antica, mi soprende come un libro come Harry Potter sia piaciuto a così tante persone"};
            return new Post(name, msg[index_msg], "2", "4", R.drawable.nashirose);
        }else if(name.equals("NoobMaster69")){
            Random random1 = new Random();
            int index_msg = random.nextInt(4);
            String[] msg = {"Mi hanno detto che in un certo film del 2019 c'è uno con il mio nome...strano", "Spero che qualche squadra di e-sports mi prenda per il torneo universale di Petris", "Ma che problemi ha mia madre che mi dice di mettere pausa mentre gioco online in VR","Grande partita di Petris oggi: Via Lattea vs Nana del Drago"};
            return new Post(name, msg[index_msg], "1", "4", R.drawable.noobmaster69);
        }else if(name.equals("Tentacool_Lino")){
            Random random1 = new Random();
            int index_msg = random.nextInt(4);
            String[] msg = {"Ho voglia di farmi tanti nuovi amici, farò un gruppo con tutti quelli che commentano con 'gamberetti'", "Devo comprare una crema per i miei tentacoli ahahaha", "Non capisco perché i ragazzi sentano il bisogno di picchiarsi...stupidi...", "Farò una gara di tentacoli più belli...chi vincerà? Io sicuramente!!"};
            return new Post(name, msg[index_msg], "2", "3", R.drawable.ten);
        }
        return new Post();
    }


    /**
     * Save post.
     *
     * @param context  the context
     * @param list     the list
     * @param filename the filename
     * @throws IOException the io exception
     */
    static public void save_post(Context context, PostList list, String filename) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(list);
        os.close();
        fos.close();
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


}