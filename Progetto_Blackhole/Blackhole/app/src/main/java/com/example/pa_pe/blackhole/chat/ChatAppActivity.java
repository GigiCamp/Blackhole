package com.example.pa_pe.blackhole.chat;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.users.ModelUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * La classe ChatAppActivity gestisce il comportamento delle chat con cui l'utente andrà ad interagire
 * nel corso della trama. Per sincronizzare le chat è stato utilizzato un sistema di checkpoint, che viene aggiornato
 * ogni volta che l'utente prosegue nella trama.
 */
public class ChatAppActivity extends AppCompatActivity {

        // qui sono contenuti i messaggi delle chat
        private RecyclerView recyclerView;

        // immagine della chat
        private ImageView profileIv;

        // nome della chat e stato dell'utente
        private TextView nameTv, userStatusTv;

        // EditText che se cliccata mostra le opzioni che l'utente ha per rispondere ai dialoghi
        private EditText messageEt;

        // utilizzato per sincronizzare le chat durante la trama
        private int checkpoint;

        // utilizzato per far accedere l'utente ai vari finali della trama
        private int karma = 0;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            onWindowFocusChanged(true);

            recyclerView = findViewById(R.id.chat_recyclerView);
            profileIv = findViewById(R.id.profileIv);
            nameTv = findViewById(R.id.nameTv);
            userStatusTv = findViewById(R.id.userStatusTv);
            messageEt = findViewById(R.id.messageEt);

            EditText msgInputText = (EditText) findViewById(R.id.messageEt);
            msgInputText.setEnabled(false);
            msgInputText.setHint("");
            LinearLayout options = findViewById(R.id.chatLayout);
            EditText et = findViewById(R.id.messageEt);
            ImageView send = findViewById(R.id.sendBtn);
            View divider1 = findViewById(R.id.divider);
            View divider2 = findViewById(R.id.divider2);

            //Layout (LinearLayout) for RecyclerView
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setStackFromEnd(true);
            //recyclerview properties
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);

            // Recupero il campo titolo
            TextView titolo = (TextView) findViewById(R.id.nameTv);
            CircularImageView image = (CircularImageView) findViewById(R.id.profileIv);

            //Recupero il dato passato (nomeProfilo)
            Bundle nomeProfilo = getIntent().getExtras();
            final String nomePro = nomeProfilo.getString("userName");
            titolo.setText(nomePro);

            //Recupero il dato passato (immagineProfilo)
            Bundle immagineProfilo = getIntent().getExtras();
            final int nomeIma = immagineProfilo.getInt("userImage");
            image.setImageResource(nomeIma);

            TextView option1 = findViewById(R.id.option1);
            TextView option2 = findViewById(R.id.option2);
            TextView option3 = findViewById(R.id.option3);

            final RecyclerView msgRecyclerView = (RecyclerView) findViewById(R.id.chat_recyclerView);

            ArrayList<ModelUser> members = new ArrayList<ModelUser>();
            try {
                members = load_users(this, "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (nomePro.equals("Gli Intergalattici")) {
                String members_group = new String();
                for (int i = 0; i < members.get(1).getMembers().size(); i++) {
                    members_group += members.get(1).getMembers().get(i).getName();
                    if (i < members.get(1).getMembers().size() - 1) {
                        members_group += ", ";
                    }
                }
                userStatusTv.setText(members_group);
            }

            // Creo la lista delle chat iniziale
            Chat msgDtoList = new Chat();
            checkpoint = 0;
            TextView name = (TextView) findViewById(R.id.nameTv);
            File file = new File(getApplicationContext().getFilesDir(), "chat.dat");
            File file1 = new File(getApplicationContext().getFilesDir(), "checkpoint.dat");
            if (file.exists() && file1.exists()) {
                try {
                    checkpoint = load_checkpoint(this, "checkpoint.dat");
                    msgDtoList = load_chat(this, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if((checkpoint == 11 || checkpoint == 12 || checkpoint == 22 || checkpoint == 23 || checkpoint == 31 || checkpoint == 32 || checkpoint == 41 || checkpoint == 42 || checkpoint == 421 || checkpoint == 413 || checkpoint == 426 || checkpoint == 427 || checkpoint == 428 || checkpoint == 51 || checkpoint == 52 || checkpoint == 53 || checkpoint == 54 || checkpoint == 55 || checkpoint == 56 || checkpoint == 57 || checkpoint == 58 || checkpoint == 59 || checkpoint == 60 || checkpoint == 71 || checkpoint == 80) && (nomePro.equals("Gli Intergalattici"))){
                    msgInputText.setEnabled(true);
                    msgInputText.setHint("Scrivi un messaggio...");
                }else if((checkpoint == 417 || checkpoint == 418 || checkpoint == 421 || checkpoint == 423 || checkpoint == 424) && (nomePro.equals("Dark_Chad"))){
                    msgInputText.setEnabled(true);
                    msgInputText.setHint("Scrivi un messaggio...");
                }
            } else if (name.getText().toString().equals("Sconosciuto") && checkpoint == 0) {
                msgInputText.setEnabled(true);
                msgInputText.setHint("Scrivi un messaggio...");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non so come tu abbia avuto accesso a questo Aliephone, e non mi interessa. In questo momento sarò solo una guida.", name.getText().toString());
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Hai presente le tre icone nella parte inferiore dello schermo? Ti serviranno per navigare nel social network di BlackHole che da ora in poi chiamerò BH.", name.getText().toString());
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Home (icona a forma di casa) è la sezione principale di BH, qui gli alieni scrivono tutto quello che pensano.", name.getText().toString());
                msgDtoList.get_list(name.getText().toString()).add(msgDto2);
                ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Profilo(icona a forma di omino) è la sezione dedicata al profilo, potrai vedere i post di Simon, un povero ragazzo scomparso da giorni.", name.getText().toString());
                msgDtoList.get_list(name.getText().toString()).add(msgDto3);
                ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Chat(icona a forma di nuvola) è la sezione in cui probabilmente passerai più tempo la storia e la tua avventura su BH sarà principalmente su Direct, qui tutti gli altri account di BH, anche a te sconosciuti hanno la possibilità di inviarti messaggi tramite il tuo profilo sul social network.", name.getText().toString());
                msgDtoList.get_list(name.getText().toString()).add(msgDto4);
                ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Hai qualche domanda?", name.getText().toString());
                msgDtoList.get_list(name.getText().toString()).add(msgDto5);
                members.get(0).setNew_messages(View.GONE);
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (name.getText().toString().equals("Sconosciuto") && checkpoint == 54) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Guarda che ho visto cos’hai fatto, ricorda che non è bello insultare gli altri, anche se sei il primo ad essere aggredito verbalmente. In questi casi si cerca di calmare chi hai sullo schermo o segnalare la cosa a persone adulte come genitori o insegnanti, in casi estremi la segnalazione va fatta alle autorità, devi cercare sempre di non diffondere odio o si crea il cosiddetto fenomeno “flame”, dove la gente s’insulta a vicenda fino a creare disguidi.", "Sconosciuto");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(0).setNew_messages(View.GONE);
                try {
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto") && checkpoint == 61) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Corri e salva quel ragazzo, intanto voglio farti leggere qualcosa, la gravità delle cose scritte da voi giovani e che a volte non date peso… esci dai direct e va nelle chat.", "Sconosciuto");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "PS. Nasconderò i loro nomi per privacy ovviamente.", "Sconosciuto");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                members.get(0).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto1", R.drawable.ic_account, View.VISIBLE));
                checkpoint = 62;
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto") && checkpoint == 81) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non è stata una mossa furba la tua… dovevi aiutarli il più possibile… povero Simon… cosa hai fatto… guarda, proprio perché non sei arrivato in tempo ti farò vedere alcune cose, torna nella schermata delle chat, i nomi saranno “sconosciuto” e non avranno icone per questioni di privacy.", "Sconosciuto");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(0).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto1", R.drawable.ic_account, View.VISIBLE));
                checkpoint = 82;
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(name.getText().toString().equals("Sconosciuto") && (checkpoint == 425)){
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Guarda che ho visto cos’hai fatto, ricorda che non è bello insultare gli altri, anche se sei il primo ad essere aggredito verbalmente. In questi casi si cerca di calmare chi hai sullo schermo o segnalare la cosa a persone adulte come genitori o insegnanti, in casi estremi la segnalazione va fatta alle autorità, devi cercare sempre di non diffondere odio o si crea il cosiddetto fenomeno “flame”, dove la gente s’insulta a vicenda fino a creare disguidi. Senti, voglio aiutarti... che ne dici di rientrare nel gruppetto di amici di Simon? Va a scusarti...", "Sconosciuto");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(0).setNew_messages(View.GONE);
                members.get(1).setNew_messages(View.VISIBLE);
                checkpoint = 426;
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(name.getText().toString().equals("Sconosciuto") && checkpoint == 429){
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "MA CHE È OH, TI HO FATTO ENTRARE IN QUEL GRUPPO SOLO PER SCUSARTI E TU CHE FAI? NIENTE DI QUELLO CHE MI ASPETTAVO…. Va bene, se è così che vuoi che vada… controlla la home.", "Sconosciuto");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(0).setNew_messages(View.GONE);
                checkpoint = 430;
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (name.getText().toString().equals("Sconosciuto1") && (checkpoint == 62 || checkpoint == 82)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "FEMMINUCCIA VAI A MORIRE MALE TE E LE TARTARUGHE", "Sconosciuto1");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "NON APPARTENETE A QUESTA GALASSIA", "Sconosciuto1");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                members.get(3).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto2", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 62) {
                    checkpoint = 63;
                } else if (checkpoint == 82) {
                    checkpoint = 83;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto2") && (checkpoint == 63 || checkpoint == 83)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "SCARTO DELLA SOCIETA’ PERCHÉ NON SPARISCI?? SEI INUTILE", "Sconosciuto2");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(4).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto3", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 63) {
                    checkpoint = 64;
                } else if (checkpoint == 83) {
                    checkpoint = 84;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto3") && (checkpoint == 64 || checkpoint == 84)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "NON HO MAI VISTO UNO SPRECO D’OSSIGENO COSÌ", "Sconosciuto3");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(5).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto4", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 64) {
                    checkpoint = 65;
                } else if (checkpoint == 84) {
                    checkpoint = 85;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto4") && (checkpoint == 65 || checkpoint == 85)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "INIZIA A CERCARTI UN LAVORO MISTER “CHE BELLI GLI INSETTI”", "Sconosciuto4");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(6).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto5", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 65) {
                    checkpoint = 66;
                } else if (checkpoint == 85) {
                    checkpoint = 86;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto5") && (checkpoint == 66 || checkpoint == 86)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "AHAHAH IMMAGINA UN RAGAZZO A CUI POSSANO PIACERE GLI INSETTI ", "Sconosciuto5");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(7).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto6", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 66) {
                    checkpoint = 67;
                } else if (checkpoint == 86) {
                    checkpoint = 87;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto6") && (checkpoint == 67 || checkpoint == 87)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "BASTA COL TUO FINTO BUONISMO, FALSO, SEI TOTALMENTE FALSO", "Sconosciuto6");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(8).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto7", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 67) {
                    checkpoint = 68;
                } else if (checkpoint == 87) {
                    checkpoint = 88;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto7") && (checkpoint == 68 || checkpoint == 88)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "MA CE L’HAI UNA VITA? ESSERINO MINUSCOLO ", "Sconosciuto7");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(9).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto8", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 68) {
                    checkpoint = 69;
                } else if (checkpoint == 88) {
                    checkpoint = 89;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto8") && (checkpoint == 69 || checkpoint == 89)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "LA TUA RAZZA SOPRAVVIVE ANCORA? ", "Sconosciuto8");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(10).setNew_messages(View.GONE);
                members.add(new ModelUser("Sconosciuto9", R.drawable.ic_account, View.VISIBLE));
                if (checkpoint == 69) {
                    checkpoint = 70;
                } else if (checkpoint == 89) {
                    checkpoint = 90;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Sconosciuto9") && (checkpoint == 70 || checkpoint == 90)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI MUORI ", "Sconosciuto9");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                members.get(11).setNew_messages(View.GONE);
                members.get(1).setNew_messages(View.VISIBLE);
                if (checkpoint == 70) {
                    checkpoint = 71;
                    members.get(1).getMembers().add(new ModelUser("Simon", R.drawable.simon));
                } else if (checkpoint == 90) {
                    checkpoint = 91;
                }
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_users(this, members, "users.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (name.getText().toString().equals("Gli Intergalattici") && checkpoint == 2) {
                msgInputText.setEnabled(true);
                msgInputText.setHint("Scrivi un messaggio...");
                ChatAppMsg msgDto0 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_SENT, "***Sei entrato in chat***", "");
                msgDtoList.get_list(name.getText().toString()).add(msgDto0);
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Vedo che è entrato un intruso in chat", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Chi? Dai dai chi è chi è?", "Tentacool_Lino");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Proprio ora che stavo vincendo un game su Petris88?", "NoobMaster69");
                msgDtoList.get_list(name.getText().toString()).add(msgDto2);
                ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ragazzi, stavo studiando, che succede?", "NashiRose");
                msgDtoList.get_list(name.getText().toString()).add(msgDto3);
                ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Qualcuno oltre noi è nella chat, sta solo leggendo oppure vuole anche intervenire?", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto4);
                members.get(1).setNew_messages(View.GONE);
                checkpoint = 2;
                try {
                    save_users(this, members, "users.dat");
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Gli Intergalattici") && checkpoint == 71) {
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ragazzi… GRAZIE MILLE :’) non so cosa avrei fatto senza di voi", "Simon");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "SIMON HO AVUTO PAURA", "Nashi_rose");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non farci prendere uno spavento così di nuovo ti prego...", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto2);
                ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dì la verità… volevi provare che esiste il respawn anche nella vita reale eh?", "NoobMaster69");
                msgDtoList.get_list(name.getText().toString()).add(msgDto3);
                ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ti bacerei coi miei tentacoolini Simon, sono felice che tu sia vivo :D", "Tentacool_Lino");
                msgDtoList.get_list(name.getText().toString()).add(msgDto4);
                members.get(1).setNew_messages(View.GONE);
                try {
                    save_users(this, members, "users.dat");
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Gli Intergalattici") && checkpoint == 91) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non posso ancora crederci… quello che è successo è", "Nashi_rose");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Probabilmente non potevamo far nulla Rose, non è colpa nostra", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sapete, almeno in un certo senso, ha provato ad essere ciò che gli appassionava di più, una tartaruga marina", "Tentacool_Lino");
                msgDtoList.get_list(name.getText().toString()).add(msgDto2);
                ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Mi state facendo passare la voglia di giocare voi tutti…", "NoobMaster69");
                msgDtoList.get_list(name.getText().toString()).add(msgDto3);
                ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sto pensando di abbandonare il gruppo, è meglio per tutti noi riflettere su questa situazione", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto4);
                ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Io… io… meglio che vada a studiare", "Nashi_rose");
                msgDtoList.get_list(name.getText().toString()).add(msgDto5);
                ChatAppMsg msgDtoAbb = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Nashi_rose abbandona il gruppo***", "");
                msgDtoList.get_list(name.getText().toString()).add(msgDtoAbb);
                members.get(1).getMembers().remove(3);
                ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Raga... davvero? Abbandoniamo questo gruppo?", "Tentacool_Lino");
                msgDtoList.get_list(name.getText().toString()).add(msgDto6);
                ChatAppMsg msgDto7 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ho bisogno di una pausa, scusatemi", "NoobMaster69");
                msgDtoList.get_list(name.getText().toString()).add(msgDto7);
                ChatAppMsg msgDtoAbb1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***NoobMaster69 abbandona il gruppo***", "");
                msgDtoList.get_list(name.getText().toString()).add(msgDtoAbb1);
                members.get(1).getMembers().remove(1);
                ChatAppMsg msgDto8 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Scusa Lino, non riesco a stare in posto in cui lui era presente proprio qualche giorno fa…", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto8);
                ChatAppMsg msgDtoAbb2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Aliz_en abbandona il gruppo***", "");
                msgDtoList.get_list(name.getText().toString()).add(msgDtoAbb2);
                members.get(1).getMembers().remove(1);
                ChatAppMsg msgDto9 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Mh…", "Tentacool_Lino");
                msgDtoList.get_list(name.getText().toString()).add(msgDto9);
                ChatAppMsg msgDtoAbb3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Tentacool_Lino abbandona il gruppo***", "");
                msgDtoList.get_list(name.getText().toString()).add(msgDtoAbb3);
                members.get(1).getMembers().remove(0);
                userStatusTv.setText("");
                members.get(1).setNew_messages(View.GONE); //FINE BAD ENDING
                AlertDialog.Builder builder = new AlertDialog.Builder(msgInputText.getContext(), R.style.Theme_MaterialComponents_Dialog);
                // Ask the final question
                builder.setMessage("Complimenti hai completato il gioco! Clicca su Logout nel menu a tendina per tornare al menu principale.");
                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkpoint = 431;
                        try {
                            save_checkpoint(builder.getContext(), checkpoint, "checkpoint.dat");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
                try {
                    save_users(this, members, "users.dat");
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(name.getText().toString().equals("Gli Intergalattici") && (checkpoint == 426)){
                msgInputText.setEnabled(true);
                msgInputText.setHint("Scrivi un messaggio...");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Sei entrato nel gruppo***", "");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ehm... ok, come mai sei di nuovo dentro?", "Aliz_en");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                members.get(1).setNew_messages(View.GONE);
                try {
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                    save_users(this, members, "users.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (name.getText().toString().equals("Dark_Chad") && (checkpoint == 411 || checkpoint == 412)) {
                msgInputText.setEnabled(false);
                msgInputText.setHint("");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "MA CHE IMMAGINI POSTI SU BH? EH? AHAHAHAHA RIDICOLE, SEI SFIGATO, SENZA UN MINIMO DI MUSCOLI E GLI UNICI AMICI CHE HAI SONO SFIGATI COME TE.", "Dark_Chad", "Qualche giorno fa");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "ROSINO, COME VA? HO SENTITO CHE TI PIACE LA TIPA DELLA TERZA, NON TE LA DARÀ MAI, SFIGATO", "Dark_Chad", "Qualche giorno fa");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "CHE BELLI GLI INSETTI, SONO NELLA TUA STESSA LINEA EVOLUTIVA, SOLO CHE TU VIVI PIÙ A LUNGO DI LORO, CHE SCHIFO", "Dark_Chad", "Ieri");
                msgDtoList.get_list(name.getText().toString()).add(msgDto2);
                ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "SONO GIORNI CHE NON TI FAI VEDERE, NON HAI NEMMENO IL CORAGGIO DI USCIRE DI CASA? GUARDA CHE TI PESTO APPENA TI VEDO", "Dark_Chad", "Ieri");
                msgDtoList.get_list(name.getText().toString()).add(msgDto3);
                members.get(2).setNew_messages(View.GONE);
                members.get(1).setNew_messages(View.VISIBLE);
                checkpoint = 413;
                try {
                    save_users(this, members, "users.dat");
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (name.getText().toString().equals("Dark_Chad") && (checkpoint == 416 || checkpoint == 414)) {
                msgInputText.setEnabled(true);
                msgInputText.setHint("Scrivi un messaggio...");
                members.get(2).setNew_messages(View.GONE);
                try {
                    save_users(this, members, "users.dat");
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(name.getText().toString().equals("Dark_Chad") && (checkpoint == 422)){
                msgInputText.setEnabled(true);
                msgInputText.setHint("Scrivi un messaggio...");
                ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "MA CHE IMMAGINI POSTI SU BH? EH? AHAHAHAHA RIDICOLE, SEI SFIGATO, SENZA UN MINIMO DI MUSCOLI E GLI UNICI AMICI CHE HAI SONO SFIGATI COME TE.", "Dark_Chad", "Qualche giorno fa");
                msgDtoList.get_list(name.getText().toString()).add(msgDto);
                ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "ROSINO, COME VA? HO SENTITO CHE TI PIACE LA TIPA DELLA TERZA, NON TE LA DARÀ MAI, SFIGATO", "Dark_Chad", "Qualche giorno fa");
                msgDtoList.get_list(name.getText().toString()).add(msgDto1);
                ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "CHE BELLI GLI INSETTI, SONO NELLA TUA STESSA LINEA EVOLUTIVA, SOLO CHE TU VIVI PIÙ A LUNGO DI LORO, CHE SCHIFO", "Dark_Chad", "Ieri");
                msgDtoList.get_list(name.getText().toString()).add(msgDto2);
                ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "SONO GIORNI CHE NON TI FAI VEDERE, NON HAI NEMMENO IL CORAGGIO DI USCIRE DI CASA? GUARDA CHE TI PESTO APPENA TI VEDO", "Dark_Chad", "Ieri");
                msgDtoList.get_list(name.getText().toString()).add(msgDto3);
                checkpoint = 423;
                try {
                    save_users(this, members, "users.dat");
                    save_checkpoint(this, checkpoint, "checkpoint.dat");
                    save_chat(this, msgDtoList, "chat.dat");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Create the data adapter with above data list.
            final ChatAppMsgAdapter chatAppMsgAdapter = new ChatAppMsgAdapter(msgDtoList.get_list(name.getText().toString()));
            chatAppMsgAdapter.notifyDataSetChanged();


            // Set data adapter to RecyclerView.
            msgRecyclerView.setAdapter(chatAppMsgAdapter);

            StoryController story = new StoryController(nomePro);

            msgInputText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    msgInputText.setEnabled(false);
                    option1.setEnabled(true);
                    option2.setEnabled(true);
                    option3.setEnabled(true);
                    try {
                        checkpoint = load_checkpoint(option1.getContext(), "checkpoint.dat");
                        if (checkpoint == 0 && name.getText().toString().equals("Sconosciuto")) {
                            option1.setText("Sì");
                            option2.setText("No");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setText("Non disponibile");
                            option3.setEnabled(false);
                        } else if (checkpoint == 2 && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Ciao a tutti!");
                            option2.setText("Dove sono?");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setText("Non disponibile");
                            option3.setEnabled(false);
                        } else if ((checkpoint == 11 || checkpoint == 12) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Ho acceso l’Aliephone e ho trovato questa chat.");
                            option2.setText("Ho trovato questo Aliephone a terra.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setText("Non disponibile");
                            option3.setEnabled(false);
                        } else if ((checkpoint == 22 || checkpoint == 23) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Se così posso aiutarvi a trovare il vostro amico e il proprietario di questo AP, ne sarei felice.");
                            option2.setText("Cosa guadagnerei a far tutto cio’? Impegnarmi per individui che non conosco, ho solo trovato questo AP e volevo solo vedere se funzionava, non sono interessato a cederlo. Molto probabilmente non era importante per il proprietario.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 31 || checkpoint == 32) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Ok vi aiuterò.");
                            option2.setText("Non vi aiuterò, non sono interessato alle vostre faccende personali.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 41) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Si (questo aliephone l’ho appena trovato, non ho ancora dati personali registrati)");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("No, non posso ancora fidarmi di voi.");
                            option3.setText("Non disponibile");
                            option3.setEnabled(false);
                        } else if (checkpoint == 42) {
                            //STRADA CATTIVA
                            option1.setText("Va bene, ho cambiato idea, ma non contateci troppo.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Vi ho appena conosciuti non so quanto potrei fidarmi.");
                            divider2.setVisibility(View.VISIBLE);
                            option3.setVisibility(View.VISIBLE);
                            option3.setText("Non voglio avere nulla a che fare di questa storia.");
                        }else if((checkpoint == 421) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("D’accordo mi hai convinto");
                            option2.setText("Volevo essere gentile ma dopo tutte queste parole devo dirlo… A me non importa nulla del vostro amico e non voglio avere a che fare con voi");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        }else if((checkpoint == 423) && name.getText().toString().equals("Dark_Chad")) {
                            option1.setText("Ma finiscila, uno che si chiama DarkChad non voglio immaginare che faccia abbia, no aspetta… sei quello nella foto profilo? AHAHAHAHAHAAH oddio, ti permetti anche il lusso di insultare gli altri buffone.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Ahahah belli st’insulti, potresti insegnarmeli un po’? È che il mio fratellino di 5 anni vuole qualche modello d’insulti per i bambini della sua scuola");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        }else if((checkpoint == 424) && name.getText().toString().equals("Dark_Chad")) {
                            option1.setText("Ma chi lo conosce e chi TI conosce? Va a morire male");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Coniglio a chi COGLIONE? Fai tanto il forte ma vivi ancora con la mamma, se ti trovo in giro ti faccio nero");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        }else if((checkpoint == 426) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Ragazzi… scusate il mio comportamento di prima, voglio dare una mano");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Ciao.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        }else if((checkpoint == 427) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Si");
                            option2.setText("No");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        }else if((checkpoint == 428) && name.getText().toString().equals("Gli Intergalattici")){
                            option1.setText("Volevo solo dirti che NON MI ABBASSERÒ AL VOSTRO LIVELLO sfigati di merda");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Non vi aiuterò mai, non cercatemi perché sarebbe inutile");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 413) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Un certo DarkChad insultava Simon in chat.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Chi è DarkChad?");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 415) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Ha insultato Simon in chat, ho appena letto delle cose bruttissime.");
                            divider1.setVisibility(View.GONE);
                            option2.setVisibility(View.GONE);
                            option2.setText("Non disponibile");
                            option2.setEnabled(false);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 414 || checkpoint == 416) && name.getText().toString().equals("Dark_Chad")) {
                            option1.setText("Ti piace bullizzare chi non ti può rispondere? Questa chat è registrata, andrò a denunciarti.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Ahahah belli st’insulti, potresti insegnarmeli un po’? È che il mio fratellino di 5 anni vuole qualche modello d’insulti per i bambini della sua scuola.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 417) && name.getText().toString().equals("Dark_Chad")) {
                            option1.setText("Ok, lo hai voluto tu.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Mi sono stancato di parlare con te.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 418) && name.getText().toString().equals("Dark_Chad")) {
                            option1.setText("Meglio della tua immensa solitudine, sir “ho bisogno di aggredire gente in chat per sentirmi soddisfatto”");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Meglio la compagnia dell’anello del Lupo solitario del cazzo mio, torna a fumare per farti figo davanti i tuoi amichetti, coglione.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 51) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("L’ho zittito, a quanto pare non ha più voglia di parlare.");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("L’ho denunciato.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if (((checkpoint == 52) || (checkpoint == 53) || (checkpoint == 54)) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("L’ho zittito, a quanto pare non ha più voglia di parlare.");
                            divider1.setVisibility(View.GONE);
                            option2.setVisibility(View.GONE);
                            option2.setText("Non disponibile");
                            option2.setEnabled(false);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 55) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("No, l’ho solo mandato a quel paese gentilmente ^^");
                            divider1.setVisibility(View.GONE);
                            option2.setVisibility(View.GONE);
                            option2.setText("Non disponibile");
                            option2.setEnabled(false);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 56) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("No, l’ho solo mandato a quel paese gentilmente ^^");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Si, mi sta sul cazzo se lo meritava.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 57) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Gli devono piacere molto gli insetti eh?");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Noto della malinconia nelle sue parole, è preoccupante.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 58 || checkpoint == 59) && name.getText().toString().equals("Gli Intergalattici")) {
                            karma = load_karma(option1.getContext());
                            if (karma >= 0) {
                                option1.setText("Fa pure.");
                                divider1.setVisibility(View.GONE);
                                option2.setVisibility(View.GONE);
                                option2.setText("Non disponibile");
                                option2.setEnabled(false);
                                option3.setText("Non disponibile");
                                divider2.setVisibility(View.GONE);
                                option3.setVisibility(View.GONE);
                                option3.setEnabled(false);
                            } else if (karma < 0) {
                                option1.setText("Non voglio.");
                                divider1.setVisibility(View.GONE);
                                option2.setVisibility(View.GONE);
                                option2.setText("Non disponibile");
                                option2.setEnabled(false);
                                option3.setText("Non disponibile");
                                divider2.setVisibility(View.GONE);
                                option3.setVisibility(View.GONE);
                                option3.setEnabled(false);
                            }
                        } else if ((checkpoint == 60) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Corro!");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("D’accordo ci vediamo lì allora.");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 71) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Menomale che siamo arrivati in tempo");
                            divider1.setVisibility(View.VISIBLE);
                            option2.setVisibility(View.VISIBLE);
                            option2.setText("Non provarci più, a provare a vivere come le tartarughe intendo");
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        } else if ((checkpoint == 80) && name.getText().toString().equals("Gli Intergalattici")) {
                            option1.setText("Allora io andrò all’acquario… dove si trova?");
                            divider1.setVisibility(View.GONE);
                            option2.setVisibility(View.GONE);
                            option2.setText("Non disponibile");
                            option2.setEnabled(false);
                            option3.setText("Non disponibile");
                            divider2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option3.setEnabled(false);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    LinearLayout linearLayout = findViewById(R.id.chatLayout);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    et.setVisibility(View.GONE);
                    send.setVisibility(View.GONE);
                    option1.setVisibility(View.VISIBLE);
                    return false;
                }
            });

            Chat finalMsgDtoList = msgDtoList;
            option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_SENT, option1.getText().toString(), name.getText().toString());
                    finalMsgDtoList.get_list(name.getText().toString()).add(msgDto);

                    int newMsgPosition = finalMsgDtoList.get_list(name.getText().toString()).size() - 1;

                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    LinearLayout linearLayout = findViewById(R.id.chatLayout);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    et.setVisibility(View.VISIBLE);
                    send.setVisibility(View.VISIBLE);
                    option1.setVisibility(View.GONE);
                    divider1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    divider2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);

                    ArrayList<ChatAppMsg> risposta = null;
                    try {
                        risposta = story.rispondi(option1.getContext(), option1.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<ChatAppMsg> finalRisposta = risposta;

                    final int[] t = {0};
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finalMsgDtoList.get_list(name.getText().toString()).add(finalRisposta.get(t[0]));
                            int newMsgPosition1 = finalMsgDtoList.get_list(name.getText().toString()).size() - 1;
                            chatAppMsgAdapter.notifyItemInserted(newMsgPosition1);
                            msgRecyclerView.scrollToPosition(newMsgPosition1);
                            if (t[0] < finalRisposta.size() - 1) {
                                handler.postDelayed(this, 4000);
                            }
                            t[0]++;
                            try {
                                checkpoint = load_checkpoint(option1.getContext(), "checkpoint.dat");
                                save_chat(option1.getContext(), finalMsgDtoList, "chat.dat");
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            if((t[0] == finalRisposta.size()) && (checkpoint == 11 || checkpoint == 12 || checkpoint == 0 || checkpoint == 22 || checkpoint == 23 || checkpoint == 31 || checkpoint == 32 || checkpoint == 41 || checkpoint == 426 || checkpoint == 427 || checkpoint == 428 || checkpoint == 55 || checkpoint == 56 || checkpoint == 57 || checkpoint == 58 || checkpoint == 59 || checkpoint == 60 || checkpoint == 80 || checkpoint == 42 || checkpoint == 423 || checkpoint == 424) && (nomePro.equals("Gli Intergalattici") || nomePro.equals("Sconosciuto") || nomePro.equals("Dark_Chad"))) {
                                msgInputText.setEnabled(true);
                                msgInputText.setHint("Scrivi un messaggio...");
                            }else if((t[0] == finalRisposta.size()) && (checkpoint == 417 || checkpoint == 418) && (nomePro.equals("Dark_Chad"))){
                                msgInputText.setEnabled(true);
                                msgInputText.setHint("Scrivi un messaggio...");
                            }else if((t[0] == finalRisposta.size()) && (checkpoint == 421 || checkpoint == 60 || checkpoint == 61 || checkpoint == 411 || checkpoint == 412 || checkpoint == 414 || checkpoint == 416 || checkpoint == 51 || checkpoint == 52 || checkpoint == 53 || checkpoint == 54 || checkpoint == 511) && (nomePro.equals("Gli Intergalattici"))) {
                                msgInputText.setEnabled(false);
                                msgInputText.setHint("");
                            }else{
                                msgInputText.setEnabled(false);
                                msgInputText.setHint("");
                            }

                            if((t[0] == finalRisposta.size()) && checkpoint == 100) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(msgInputText.getContext(), R.style.Theme_MaterialComponents_Dialog);
                                // Ask the final question
                                builder.setMessage("Complimenti hai completato il gioco! Clicca su Logout nel menu a tendina per tornare al menu principale.");
                                // Set the alert dialog yes button click listener
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        checkpoint = 431;
                                        try {
                                            save_checkpoint(builder.getContext(), checkpoint, "checkpoint.dat");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                // Display the alert dialog on interface
                                dialog.show();
                            }
                        }

                    }, 2000);
                }
            });


            Chat finalMsgDtoList1 = msgDtoList;
            option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_SENT, option2.getText().toString(), name.getText().toString());
                    finalMsgDtoList1.get_list(name.getText().toString()).add(msgDto);

                    int newMsgPosition = finalMsgDtoList1.get_list(name.getText().toString()).size() - 1;

                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    LinearLayout linearLayout = findViewById(R.id.chatLayout);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    et.setVisibility(View.VISIBLE);
                    send.setVisibility(View.VISIBLE);
                    option1.setVisibility(View.GONE);
                    divider1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    divider2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);

                    ArrayList<ChatAppMsg> risposta = null;
                    try {
                        risposta = story.rispondi(option2.getContext(), option2.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ArrayList<ChatAppMsg> finalRisposta = risposta;

                    final int[] t = {0};
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finalMsgDtoList.get_list(name.getText().toString()).add(finalRisposta.get(t[0]));
                            int newMsgPosition1 = finalMsgDtoList.get_list(name.getText().toString()).size() - 1;
                            chatAppMsgAdapter.notifyItemInserted(newMsgPosition1);
                            msgRecyclerView.scrollToPosition(newMsgPosition1);
                            if (t[0] < finalRisposta.size() - 1) {
                                handler.postDelayed(this, 4000);
                            }
                            t[0]++;
                            try {
                                save_chat(option2.getContext(), finalMsgDtoList, "chat.dat");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if((t[0] == finalRisposta.size()) && (checkpoint == 11 || checkpoint == 12 || checkpoint == 2 || checkpoint == 22 || checkpoint == 23 || checkpoint == 31 || checkpoint == 32 || checkpoint == 41 || checkpoint == 426 || checkpoint == 427 || checkpoint == 428 || checkpoint == 413 || checkpoint == 55 || checkpoint == 57 || checkpoint == 56 || checkpoint == 58 || checkpoint == 59 || checkpoint == 60 || checkpoint == 80 || checkpoint == 42 || checkpoint == 423 || checkpoint == 424) && (nomePro.equals("Gli Intergalattici") || nomePro.equals("Sconosciuto") || nomePro.equals("Dark_Chad"))) {
                                msgInputText.setEnabled(true);
                                msgInputText.setHint("Scrivi un messaggio...");
                            }else if((t[0] == finalRisposta.size()) && (checkpoint == 417 || checkpoint == 418) && (nomePro.equals("Dark_Chad"))){
                                msgInputText.setEnabled(true);
                                msgInputText.setHint("Scrivi un messaggio...");
                            }else if((t[0] == finalRisposta.size()) && (checkpoint == 421 || checkpoint == 60 || checkpoint == 411 || checkpoint == 412 || checkpoint == 414 || checkpoint == 416 || checkpoint == 51 || checkpoint == 52 || checkpoint == 53 || checkpoint == 54 || checkpoint == 511 || checkpoint == 61) && (nomePro.equals("Gli Intergalattici"))){
                                msgInputText.setEnabled(false);
                                msgInputText.setHint("");
                            }else{
                                msgInputText.setEnabled(false);
                                msgInputText.setHint("");
                            }

                            if((t[0] == finalRisposta.size()) && checkpoint == 100) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(msgInputText.getContext(), R.style.Theme_MaterialComponents_Dialog);
                                // Ask the final question
                                builder.setMessage("Complimenti hai completato il gioco! Clicca su Logout nel menu a tendina per tornare al menu principale.");
                                // Set the alert dialog yes button click listener
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        checkpoint = 431;
                                        try {
                                            save_checkpoint(builder.getContext(), checkpoint, "checkpoint.dat");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                // Display the alert dialog on interface
                                dialog.show();
                            }
                        }
                    }, 2000);
                }
            });

            Chat finalMsgDtoList2 = msgDtoList;
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_SENT, option3.getText().toString(), name.getText().toString());
                    finalMsgDtoList2.get_list(name.getText().toString()).add(msgDto);

                    int newMsgPosition = finalMsgDtoList2.get_list(name.getText().toString()).size() - 1;

                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    LinearLayout linearLayout = findViewById(R.id.chatLayout);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    et.setVisibility(View.VISIBLE);
                    send.setVisibility(View.VISIBLE);
                    option1.setVisibility(View.GONE);
                    divider1.setVisibility(View.GONE);
                    option2.setVisibility(View.GONE);
                    divider2.setVisibility(View.GONE);
                    option3.setVisibility(View.GONE);

                    ArrayList<ChatAppMsg> risposta = null;
                    try {
                        risposta = story.rispondi(option3.getContext(), option3.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<ChatAppMsg> finalRisposta = risposta;

                    final int[] t = {0};
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finalMsgDtoList.get_list(name.getText().toString()).add(finalRisposta.get(t[0]));
                            int newMsgPosition1 = finalMsgDtoList.get_list(name.getText().toString()).size() - 1;
                            chatAppMsgAdapter.notifyItemInserted(newMsgPosition1);
                            msgRecyclerView.scrollToPosition(newMsgPosition1);
                            if (t[0] < finalRisposta.size() - 1) {
                                handler.postDelayed(this, 4000);
                            }
                            t[0]++;
                            try {
                                checkpoint = load_checkpoint(option3.getContext(), "checkpoint.dat");
                                save_chat(option3.getContext(), finalMsgDtoList, "chat.dat");
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            if((t[0] == finalRisposta.size()) && (checkpoint == 11 || checkpoint == 12 || checkpoint == 0 || checkpoint == 22 || checkpoint == 23 || checkpoint == 31 || checkpoint == 32 || checkpoint == 41 || checkpoint == 426 || checkpoint == 427 || checkpoint == 428 || checkpoint == 55 || checkpoint == 56 || checkpoint == 57 || checkpoint == 58 || checkpoint == 59 || checkpoint == 60 || checkpoint == 80 || checkpoint == 42 || checkpoint == 423 || checkpoint == 424) && (nomePro.equals("Gli Intergalattici") || nomePro.equals("Sconosciuto") || nomePro.equals("Dark_Chad"))) {
                                msgInputText.setEnabled(true);
                                msgInputText.setHint("Scrivi un messaggio...");
                            }else if((t[0] == finalRisposta.size()) && (checkpoint == 417 || checkpoint == 418) && (nomePro.equals("Dark_Chad"))){
                                msgInputText.setEnabled(true);
                                msgInputText.setHint("Scrivi un messaggio...");
                            }else if((t[0] == finalRisposta.size()) && (checkpoint == 421 || checkpoint == 60 || checkpoint == 61 || checkpoint == 411 || checkpoint == 412 || checkpoint == 414 || checkpoint == 416 || checkpoint == 51 || checkpoint == 52 || checkpoint == 53 || checkpoint == 54 || checkpoint == 511) && (nomePro.equals("Gli Intergalattici"))){
                                msgInputText.setEnabled(false);
                                msgInputText.setHint("");
                            }else{
                                msgInputText.setEnabled(false);
                                msgInputText.setHint("");
                            }

                            if((t[0] == finalRisposta.size()) && checkpoint == 100) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(msgInputText.getContext(), R.style.Theme_MaterialComponents_Dialog);
                                // Ask the final question
                                builder.setMessage("Complimenti hai completato il gioco! Clicca su Logout nel menu a tendina per tornare al menu principale.");
                                // Set the alert dialog yes button click listener
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        checkpoint = 431;
                                        try {
                                            save_checkpoint(builder.getContext(), checkpoint, "checkpoint.dat");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                // Display the alert dialog on interface
                                dialog.show();
                            }
                        }

                    }, 2000);
                }
            });
        }


    /**
     * Utilizzato per salvare il contenuto delle chat
     *
     * @param context  il context
     * @param chat     la chat da serializzare
     * @param filename il nome in cui salvare la chat
     * @throws IOException
     */
    static public void save_chat(Context context, Chat chat, String filename) throws IOException {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(chat);
            os.close();
            fos.close();
        }

    /**
     * Utilizzato per caricare le chat
     *
     * @param context  il context
     * @param filename il nome del file da cui deserializzare le chat
     * @return la chat ottenuta dal file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static public Chat load_chat(Context context, String filename) throws IOException, ClassNotFoundException {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            Chat simpleClass = (Chat) is.readObject();
            is.close();
            fis.close();
            return simpleClass;
        }

    /**
     * Utilizzato per salvare il checkpoint corrente
     *
     * @param context    il context
     * @param checkpoint il checkpoint corrente
     * @param filename   il nome del file in cui salvare il checkpoint
     * @throws IOException
     */
    static public void save_checkpoint(Context context, int checkpoint, String filename) throws IOException {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(checkpoint);
            os.close();
            fos.close();
        }

    /**
     * Utilizzato per caricare il checkpoint corrente
     *
     * @param context  il context
     * @param filename file da cui caricare il checkpoint corrente
     * @return the int
     * @throws IOException
     * @throws ClassNotFoundException
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
     * Utilizzato per caricare la lista degli utenti
     *
     * @param c        il context
     * @param filename il nome del file da cui caricare la lista degli utenti
     * @return la lista degli utenti caricati dal file
     * @throws IOException
     * @throws ClassNotFoundException
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
     * Utilizzato per salvare la lista degli utenti
     *
     * @param c        il context
     * @param users    la lista degli utenti da salvare nel file
     * @param filename il nome del file in cui salvare la lista degli utenti
     * @throws IOException
     */
    static public void save_users(Context c, ArrayList<ModelUser> users, String filename) throws IOException {
            FileOutputStream fos = c.openFileOutput(filename, c.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(users);
            os.close();
            fos.close();
    }

        //Utilizzato per adattare la schermata di gioco al FullScreen
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
    }


    /**
     * Utilizzato per caricare il karma corrente del giocatore. Il karma dipende dalle scelte fatte in gioco
     *
     * @param context il context
     */
    static private int load_karma(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput("karma.dat");
        ObjectInputStream is = new ObjectInputStream(fis);
        int simpleClass = (int) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }
}

