package com.example.pa_pe.blackhole.chat;

import android.content.Context;
import android.view.View;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.users.ModelUser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * La classe StoryController serve a gestire il modo in cui i personaggi del gioco rispondono al giocatore.
 * Le diramazioni dei vari percorsi della trama vengono gestite mediante l'utilizzo dei checkpoint e del karma.
 */
public class StoryController {
    private TreeMap<String, String> map;
    private String name;
    private int checkpoint;
    private String user_name;
    private int karma = 0;

    /**
     * Instantiates a new Story controller.
     *
     * @param n the n
     */
    public StoryController(String n){
        name = n;
        map = new TreeMap<String, String>();
        map.put("No", "Vedo che sei un tipo in gamba, apprendi molto in fretta. Ora va’ e divertiti, vivi al meglio questa opportunità e ricorda che ogni azione ha delle conseguenze.");
        map.put("Sì", "Bene, o forse no, imparerai nel corso della tua esperienza. Ora va’ e divertiti, vivi al meglio questa opportunità e ricorda che ogni azione ha delle conseguenze.");
    }

    /**
     * E' il metodo che restituisce all'utente delle risposte in base alla scelta che ha fatto
     *
     * @param c il context
     * @param k la scelta fatta dall'utente
     * @return la lista dei messaggi con cui i personaggi rispondono all'utente
     * @throws IOException
     */
    public ArrayList<ChatAppMsg> rispondi(Context c, String k) throws IOException {
        ArrayList<ChatAppMsg> risposte = new ArrayList<ChatAppMsg>();
        ArrayList<ModelUser> users = new ArrayList<ModelUser>();
        try {
            checkpoint = load_checkpoint(c, "checkpoint.dat");
            users = load_users(c,"users.dat");
            user_name = load_name(c);
            karma = load_karma(c);
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if((checkpoint == 0) && (k.equals("Sì") || k.equals("No"))) {
            ArrayList<ModelUser> members = new ArrayList<>();
            members.add(new ModelUser("Tentacool_Lino", R.drawable.ten));
            members.add(new ModelUser("NoobMaster69", R.drawable.noobmaster69));
            members.add(new ModelUser("Aliz_en", R.drawable.aliz_en));
            members.add(new ModelUser("NashiRose", R.drawable.nashirose));
            users.add(new ModelUser(R.drawable.intergalattici, "Gli Intergalattici", members, View.VISIBLE));
            try {
                save_users(c, users, "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, this.map.get(k), "Sconosciuto");
            risposte.add(msgDto);
            checkpoint = 2;
        }
        if(checkpoint == 2 && k.equals("Ciao a tutti!")){
            ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Almeno è educato, allora, cosa ti porta qui?", "Aliz_en");
            risposte.add(msgDto5);
            ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "E' VIVOOOO", "Tentacool_Lino");
            risposte.add(msgDto6);
            ChatAppMsg msgDto7 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non sapevo che ci fosse ingresso libero per il gruppo o che fossero aperte le iscrizioni.", "NoobMaster69");
            risposte.add(msgDto7);
            ChatAppMsg msgDto8 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non esiste nessuna iscrizione per entrare, nessuno dovrebbe entrare tranne noi 4.", "Aliz_en");
            risposte.add(msgDto8);
            ChatAppMsg msgDto9 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Forse potrebbe essere...", "NashiRose");
            risposte.add(msgDto9);
            ChatAppMsg msgDto10 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "No, Nashi, non è lui, sono sicuro", "Aliz_en");
            risposte.add(msgDto10);
            checkpoint = 11;
        }else if((checkpoint == 2) && (k.equals("Dove sono?"))){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "In un gruppo in cui non dovresti trovarti. Come fai ad essere in possesso dell'AP del nostro amico Simon?", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 12;
        }else if((checkpoint == 11 || checkpoint == 12) && k.equals("Ho acceso l’Aliephone e ho trovato questa chat.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Come se potessi crederci", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "E' ancora confuso, non essere così aggressivo.", "Nashi_rose");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Vi dico la mia versione dei fatti: Ha trovato un AliePhone a terra, e voi sapete a chi possa appartenere, si è registrato a BH ma l’AP ha associato questi nuovi dati a quelli vecchi e quindi dandogli il permesso di vedere le chat e di poterci entrare... solo uno di noi non si fa sentire da tanto tempo e questo nuovo tizio ha trovato l’AP che apparteneva a lui.", "Aliz_en");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Quindi prima ci avevo quasi preso eh? Può essere un indizio fondamentale per trovare Simon.", "Nashi_rose");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Penso che quanto meno può essere un punto di partenza per iniziare a cercarlo e sapere dov’è finito.", "Aliz_en");
            risposte.add(msgDto4);
            ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "E' entrato un altro membro degli intergalattici yuppi!!", "Tentacool_Lino");
            risposte.add(msgDto5);
            ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non prendetela così alla leggera.", "Aliz_en");
            risposte.add(msgDto6);
            ChatAppMsg msgDto7 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Da quanto tempo non entrava qualcuno di nuovo?", "Nashi_rose");
            risposte.add(msgDto7);
            ChatAppMsg msgDto8 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Parlate come se avesse già accettato di restare, non starete accelerando un po’ troppo?", "NoobMaster69");
            risposte.add(msgDto8);
            ChatAppMsg msgDto9 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "E' da un po’ che non spiccichi parola, ci aiuti o no?", "Aliz_en");
            risposte.add(msgDto9);
            checkpoint = 22;
        }else if((checkpoint == 12 || checkpoint == 11) && k.equals("Ho trovato questo Aliephone a terra.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "OK Allora sono arrivato ad una soluzione: Ha trovato un AliePhone a terra, e voi sapete a chi possa appartenere, si è registrato a BH ma l’AP ha associato questi nuovi dati a quelli vecchi e quindi dandogli il permesso di vedere le chat e di poterci entrare... solo uno di noi non si fa sentire da tanto tempo e questo nuovo tizio ha trovato l’AP che apparteneva a lui.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Quindi prima ci avevo quasi preso eh? Può essere un indizio fondamentale per trovare Simon.", "Nashi_rose");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Penso che quanto meno può essere un punto di partenza per iniziare a cercarlo e sapere dov’è finito.", "Aliz_en");
            risposte.add(msgDto4);
            ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "E' entrato un altro membro degli intergalattici yuppi!!", "Tentacool_Lino");
            risposte.add(msgDto5);
            ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non prendetela così alla leggera.", "Aliz_en");
            risposte.add(msgDto6);
            ChatAppMsg msgDto7 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Da quanto tempo non entrava qualcuno di nuovo?", "Nashi_rose");
            risposte.add(msgDto7);
            ChatAppMsg msgDto8 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Parlate come se avesse già accettato di restare, non starete accelerando un po’ troppo?", "NoobMaster69");
            risposte.add(msgDto8);
            ChatAppMsg msgDto9 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "E' da un po’ che non spiccichi parola, sei dentro o no?", "Aliz_en");
            risposte.add(msgDto9);
            checkpoint = 23;
        }else if((checkpoint == 22 || checkpoint == 23) && k.equals("Se così posso aiutarvi a trovare il vostro amico e il proprietario di questo AP, ne sarei felice.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ci saresti di grande aiuto, in più potrei ottenere informazioni direttamente dall’AP o da te riguardo le chat di Simon, magari possiamo trovarci indizi su dove possa essere adesso.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Grazie, grazie mille!! Per noi è importantissimo avere quanto più aiuto possibile!", "Nashi_rose");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "A me ancora non convince…", "NoobMaster69");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "SI! CERCHIAMO SIMON TUTTI INSIEME!", "Tentacool_Lino");
            risposte.add(msgDto3);
            checkpoint = 31;
        }else if((checkpoint == 23 || checkpoint == 22) && k.equals("Cosa guadagnerei a far tutto cio’? Impegnarmi per individui che non conosco, ho solo trovato questo AP e volevo solo vedere se funzionava, non sono interessato a cederlo. Molto probabilmente non era importante per il proprietario.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sei davvero sicuro? Prima di tutto non parlare così di una persona che non conosci… ci guadagneresti un’azione buona in più da aggiungere alla lista che dato a come parli non credo sia così lunga e in più potresti avvicinarti a persone magari a te distanti.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "DAI TI PREGO, AIUTACI.", "Nashi_rose");
            risposte.add(msgDto1);
            checkpoint = 32;
        }else if((checkpoint == 31 || checkpoint == 32) && k.equals("Ok vi aiuterò.")){
            //INIZIO PERCORSO BUONO
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Direi che mi disconnetto dalla chat, devo ripetere per l’interrogazione.", "Nashi_rose");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Io vado a rankare un po’ su petris, ci si sente più tardi! :D", "NoobMaster69");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ho visto dei gattini carinissimi vado ad accarezzarli  > _<", "Tentacool_Lino");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Quindi" + " " + user_name + " " + "per iniziare… devo indagare nei log dell’Aliephone, mi dai il consenso per accedere?", "Aliz_en");
            risposte.add(msgDto3);
            checkpoint = 41;
        }else if((checkpoint == 32 || checkpoint == 31) && k.equals("Non vi aiuterò, non sono interessato alle vostre faccende personali.")) {
            //INIZIO PERCORSO CATTIVO
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dai, non pensiamoci più per ora… vado a ripetere per l’interrogazione.", "Nashi_rose");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Già, hai ragione, prima che inizio a innervosirmi vado su petris.", "NoobMaster69");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "PERCHÉ NON VUOI AIUTARCI :’C", "Tentacool_Lino");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Beh, non immaginavo che ci avessi dato una mano, ma nemmeno che ti rifiutassi così", "Aliz_en");
            risposte.add(msgDto3);
            checkpoint = 42;
        }else if((checkpoint == 42) && k.equals("Va bene, ho cambiato idea, ma non contateci troppo.")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Grazie, penso che anche gli altri siano felici della tua decisione, davvero… arrivati a questo punto: devo indagare nei log dell’Aliephone, mi dai il consenso per accedere?", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 41;
            karma++;
        }else if((checkpoint == 42) && k.equals("Vi ho appena conosciuti non so quanto potrei fidarmi.")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Hai perfettamente ragione, specie di questi tempi dove chiunque può sapere chi sei, dove vai e cosa fai, diventa pericoloso fidarsi di altra gente appena conosciuta. Però... noi avremmo davvero bisogno del tuo aiuto, Simon, il nostro amico, sono giorni che non si fa sentire e sei l’unico indizio che abbiamo per poterlo rintracciare…", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 421;
        }else if((checkpoint == 42) && k.equals("Non voglio avere nulla a che fare di questa storia.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_SENT, "***Sei uscito dal gruppo***", "");
            risposte.add(msgDto);
            users.add(new ModelUser("Dark_Chad", R.drawable.dark_chad, View.VISIBLE));
            try {
                save_users(c, users, "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
            checkpoint = 422;
        }else if((checkpoint == 421) && k.equals("D’accordo mi hai convinto")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Grazie, penso che anche gli altri siano felici della tua decisione, davvero… arrivati a questo punto: devo indagare nei log dell’Aliephone, mi dai il consenso per accedere?", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 41;
            karma++;
        }else if((checkpoint == 421) && k.equals("Volevo essere gentile ma dopo tutte queste parole devo dirlo… A me non importa nulla del vostro amico e non voglio avere a che fare con voi")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ok ho afferrato il concetto, scusa se ti ho fatto perdere tempo.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_SENT, "***Sei uscito dal gruppo***", "");
            risposte.add(msgDto1);
            checkpoint = 422;
            karma--;
            users.add(new ModelUser("Dark_Chad", R.drawable.dark_chad, View.VISIBLE));
            try {
                save_users(c, users, "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if((checkpoint == 423) && (k.equals("Ma finiscila, uno che si chiama DarkChad non voglio immaginare che faccia abbia, no aspetta… sei quello nella foto profilo? AHAHAHAHAHAAH oddio, ti permetti anche il lusso di insultare gli altri buffone.") || k.equals("Ahahah belli st’insulti, potresti insegnarmeli un po’? È che il mio fratellino di 5 anni vuole qualche modello d’insulti per i bambini della sua scuola"))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "PFFT, NON SEI QUEL CONIGLIO? ANDATE A FANCULO ENTRAMBI", "Dark_Chad");
            risposte.add(msgDto);
            users.get(2).setNew_messages(View.GONE);
            checkpoint = 424;
        }else if((checkpoint == 424) && (k.equals("Ma chi lo conosce e chi TI conosce? Va a morire male") || k.equals("Coniglio a chi COGLIONE? Fai tanto il forte ma vivi ancora con la mamma, se ti trovo in giro ti faccio nero"))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Dark_Chad ti ha bloccato***", "");
            risposte.add(msgDto);
            checkpoint = 425;
            users.get(0).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 426) && (k.equals("Ragazzi… scusate il mio comportamento di prima, voglio dare una mano"))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Va bene apprezziamo, sto continuando a cercare Simon, il modo per aiutarci è darci il consenso di accedere al GPS dell’Aliephone, o ci aiuti così o puoi anche uscire dal gruppo… di nuovo", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 427;
        }else if((checkpoint == 426) && (k.equals("Ciao."))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ciao, allora, hai intenzione di rispondere alla mia domanda?", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 428;
        }else if((checkpoint == 427) && (k.equals("Si"))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sicuro?", "Aliz_en");
            risposte.add(msgDto);
            karma += 20;
            checkpoint = 58;
        }else if((checkpoint == 427) && (k.equals("No"))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Perfetto allora non c’è ragione per te di rimanere giusto?", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Sei stato espulso dalla chat***", "");
            risposte.add(msgDto1);
            checkpoint = 429;
            users.get(0).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 428) && (k.equals("Volevo solo dirti che NON MI ABBASSERÒ AL VOSTRO LIVELLO sfigati di merda"))) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Nemmeno io voglio abbassarmi al tuo", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Sei stato espulso dalla chat***", "");
            risposte.add(msgDto1);
            checkpoint = 429;
            users.get(0).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 428) && (k.equals("Non vi aiuterò mai, non cercatemi perché sarebbe inutile"))){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Non ne avevo intenzione", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Sei stato espulso dalla chat***", "");
            risposte.add(msgDto1);
            checkpoint = 429;
            users.get(0).setNew_messages(View.VISIBLE);
        }else if(checkpoint == 41 && k.equals("Si (questo aliephone l’ho appena trovato, non ho ancora dati personali registrati)")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Allora…", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dovresti poter vedere una chat passata di Simon ora. Controlla cosa c’è scritto e torna qui.", "Aliz_en");
            risposte.add(msgDto1);
            checkpoint = 411;
            users.add(new ModelUser("Dark_Chad", R.drawable.dark_chad, View.VISIBLE));
            try {
                save_users(c, users, "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(checkpoint == 41 && k.equals("No, non posso ancora fidarmi di voi.")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Va bene, non è che c’è qualche chat passata di Simon registrata nell’Aliephone? Se dovessi trovare qualcosa torna qui.", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 412;
            users.add(new ModelUser("Dark_Chad", R.drawable.dark_chad, View.VISIBLE));
            try {
                save_users(c, users, "users.dat");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(checkpoint == 413 && k.equals("Un certo DarkChad insultava Simon in chat.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ma certo, se lo prende in giro quando siamo a scuola, non voglio immaginare su BH quando nessuno può difendere il povero Simo, devi rispondergli, fagli capire che non sei Simon.", "Aliz_en");
            risposte.add(msgDto);
            users.get(1).setNew_messages(View.GONE);
            users.get(2).setNew_messages(View.VISIBLE);
            checkpoint = 414;
        }else if(checkpoint == 413 && k.equals("Chi è DarkChad?")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "È un coglione a cui piace fare il bulletto, nessuno di troppo importante, perché me lo chiedi?", "Aliz_en");
            risposte.add(msgDto);
            users.get(1).setNew_messages(View.GONE);
            checkpoint = 415;
        }else if((checkpoint == 415) && k.equals("Ha insultato Simon in chat, ho appena letto delle cose bruttissime.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ma certo, se lo prende in giro quando siamo a scuola, non voglio immaginare su BH quando nessuno può difendere il povero Simo, devi rispondergli, fagli capire che non sei Simon.", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 416;
            users.get(2).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 416 || checkpoint == 414) && k.equals("Ti piace bullizzare chi non ti può rispondere? Questa chat è registrata, andrò a denunciarti.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "PFFT, NON SEI QUEL CONIGLIO? ANDATE A FANCULO ENTRAMBI, ME NE SBATTO DELLE TUE MINACCE, TANTO NON AVRAI IL CORAGGIO DI DENUNCIARMI.", "Dark_Chad");
            risposte.add(msgDto);
            checkpoint = 417;
            karma++;
            users.get(2).setNew_messages(View.GONE);
        }else if((checkpoint == 416 || checkpoint == 414) && k.equals("Ahahah belli st’insulti, potresti insegnarmeli un po’? È che il mio fratellino di 5 anni vuole qualche modello d’insulti per i bambini della sua scuola.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ma va a morire, tu e tuo fratello, cos’è… petali di rosa non può rispondere visto che ha bisogno dell’intera compagnia dell’anello?", "Dark_Chad");
            risposte.add(msgDto);
            checkpoint = 418;
            karma--;
            users.get(2).setNew_messages(View.GONE);
        }else if((checkpoint == 417) && k.equals("Ok, lo hai voluto tu.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "FANCULO!", "Dark_Chad");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Dark_Chad ti ha bloccato***", "Dark_Chad");
            risposte.add(msgDto1);
            checkpoint = 51;
            karma += 2;
            users.get(1).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 417) && k.equals("Mi sono stancato di parlare con te.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "FANCULO!", "Dark_Chad");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Dark_Chad ti ha bloccato***", "Dark_Chad");
            risposte.add(msgDto1);
            checkpoint = 52;
            users.get(1).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 418) && k.equals("Meglio della tua immensa solitudine, sir “ho bisogno di aggredire gente in chat per sentirmi soddisfatto”")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "FANCULO!", "Dark_Chad");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Dark_Chad ti ha bloccato***", "Dark_Chad");
            risposte.add(msgDto1);
            checkpoint = 53;
            karma++;
            users.get(1).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 418) && k.equals("Meglio la compagnia dell’anello del Lupo solitario del cazzo mio, torna a fumare per farti figo davanti i tuoi amichetti, coglione.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "FANCULO!", "Dark_Chad");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "***Dark_Chad ti ha bloccato***", "Dark_Chad");
            risposte.add(msgDto1);
            checkpoint = 54;
            karma--;
            users.get(1).setNew_messages(View.VISIBLE);
            users.get(0).setNew_messages(View.VISIBLE);
        }else if((checkpoint == 51 || checkpoint == 52 || checkpoint == 53) && k.equals("L’ho zittito, a quanto pare non ha più voglia di parlare.")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Che gli hai detto? Non l’avrai mica insultato?", "Aliz_en");
            risposte.add(msgDto);
            users.get(1).setNew_messages(View.GONE);
            checkpoint = 55;
        }else if((checkpoint == 51) && k.equals("L’ho denunciato.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Hai fatto la cosa migliore, nessuno aveva il coraggio di farlo, si vede che sei uno che vuol fare sempre la cosa giusta.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Finalmente quel tipo losco la smetterà di importunarci, grazie di cuore, davvero <3", "Nashi_rose");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Boss abbattuto? Wow sei di un altro livello rispetto a noi bro, chiamami se vuoi giocare insieme a qualcosa xP", "NoobMaster69");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Hey nub, non mi hai mai invitato a giocare ed ora inviti lui? Guarda che mi offendo ù.ù", "Tentacool_Lino");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "OKOK, vieni anche tu, ci facciamo qualche bella partita a Omega Smash Sis tutti insieme che ne dite? LUL", "NoobMaster69");
            risposte.add(msgDto4);
            ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ancora pensate a giocare? Dobbiamo studiare per un compito in classe!! Sveglia!", "Nashi_rose");
            risposte.add(msgDto5);
            ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Falli divertire solo per oggi, se lo meritano, io continuo a indagare su Simon…", "Aliz_en");
            risposte.add(msgDto6);
            ChatAppMsg msgDto7 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "A proposito che ne pensate degli ultimi post che ha messo prima di sparire?", "Aliz_en");
            risposte.add(msgDto7);
            users.get(1).setNew_messages(View.GONE);
            checkpoint = 511;
        }else if((checkpoint == 54) && k.equals("L’ho zittito, a quanto pare non ha più voglia di parlare.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Che gli hai detto? Non l’avrai mica insultato?", "Aliz_en");
            risposte.add(msgDto);
            users.get(1).setNew_messages(View.GONE);
            checkpoint = 56;
        }else if((checkpoint == 55 || checkpoint == 56) && k.equals("No, l’ho solo mandato a quel paese gentilmente ^^")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dai, se lo meritava, l’importante è che non l’hai insultato pesantemente, non mi sorprenderebbe se andasse a denunciarti, ricorda che l’Aliephone non è nemmeno tuo e possono trovare il pretesto per farti male: multe o espulsione da scuola.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Continuiamo a cercare Simon…", "Aliz_en");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "A proposito che ne pensate degli ultimi post che ha messo prima di sparire?", "Aliz_en");
            risposte.add(msgDto2);
            checkpoint = 511;
            karma++;
        }else if((checkpoint == 56) && k.equals("Si, mi sta sul cazzo se lo meritava.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ma sei matto? Ok che si merita tutti gli insulti ma ricorda che l’Aliephone non è il tuo, possono trovare il pretesto per multarti ed espellerti da scuola, anche renderti colpevole della scomparsa di Simon se vogliono, devi essere cauto quando fai queste sciocchezze.", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Continuiamo a cercare Simon…", "Aliz_en");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "A proposito che ne pensate degli ultimi post che ha messo prima di sparire?", "Aliz_en");
            risposte.add(msgDto2);
            checkpoint = 511;
            karma--;
        }else if((checkpoint == 57) && k.equals("Gli devono piacere molto gli insetti eh?")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sì, pensa che si eccitava anche vedendo una semplice farfalla a 3 antenne, non mi sorprenderebbe se decidesse di fare ricerche in quel ramo di scienza, la sua passione per gli insetti è immensa… eppure, gli ultimi post sono così tristi… dobbiamo trovarlo alla svelta.", "Nashi_rose");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "In effetti gli ultimi post non sono di certo rassicuranti… senti" + " " + user_name + " " + "ti dà fastidio se traccio la tua posizione sul GPS? Se si, torna sul posto preciso dove hai trovato l’Aliephone.", "Aliz_en");
            risposte.add(msgDto1);
            checkpoint = 58;
        }else if((checkpoint == 57) && k.equals("Noto della malinconia nelle sue parole, è preoccupante.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sono preoccupato anch’io dobbiamo trovarlo il prima possibile, vi prego, prima era così felice delle sue passioni e si è rattristito così tanto, ho paura che faccia qualche sciocchezza.", "Tentacool_Lino");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "In effetti gli ultimi post non sono di certo rassicuranti… senti" + " " + user_name + " " + "ti dà fastidio se traccio la tua posizione sul GPS? Se si, torna sul posto preciso dove hai trovato l’Aliephone.", "Aliz_en");
            risposte.add(msgDto1);
            checkpoint = 59;
        }else if((checkpoint == 58 || checkpoint == 59) && k.equals("Fa pure.")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Perfetto, allora…", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Trovato! La posizione in cui hai trovato il telefono, conoscendo Simon non mi sorprenderei se si fosse nascosto all’acquario locale, però… tu sei quello più vicino devi andare a controllare, io inizio a correrti incontro.", "Aliz_en");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Vengo anch’io! Andiamo a prendere il nostro amico.", "Nashi_rose");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Anch’io! Anch’io! Arrivo subitissimo corro alla velocità della luce!", "Tentacool_Lino");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dovrò spegnere la console ma sto arrivando", "NoobMaster69");
            risposte.add(msgDto4);
            checkpoint = 60;
        }else if((checkpoint == 60) && ((k.equals("Corro!") || k.equals("D’accordo ci vediamo lì allora.")))){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ti aspettiamo!", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 61;
            users.get(0).setNew_messages(View.VISIBLE);
            users.get(1).setNew_messages(View.GONE);
        }else if((checkpoint == 71 ) && k.equals("Menomale che siamo arrivati in tempo")) {
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Grazie, non ti conosco ma so che hai aiutato gli altri e hai aiutato anche me quindi… grazie ancora grazie e benvenuto nel gruppo ^^", "Simon");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dai Sim, vieni a giocare che stavamo organizzando una partita a Omega Smash Sis tutti insieme", "NoobMaster69");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "VI RICORDO IL COMPITO IN CLASSE DI DOMANI", "Nashi_rose");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Vengo anch’io a giocare!", "Aliz_en");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "OK OK ho capito… fatemi cambiare e arrivo", "Nashi_rose");
            risposte.add(msgDto4);
            ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Va bene, faccio mangiare i bruchi e arrivo", "Simon");
            risposte.add(msgDto5);
            ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, user_name + " " + "naturalmente sei invitato anche tu! ^_^", "NoobMaster69");
            risposte.add(msgDto6); //FINE GOOD ENDING
            checkpoint = 100;
        }else if((checkpoint == 71) && k.equals("Non provarci più, a provare a vivere come le tartarughe intendo")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Sarebbe bello, a parte le cannucce di plastica diffuse negli oceani, e il fatto che sono umano quindi non posso respirare sott’acqua", "Simon");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Mi sono appena immaginato Simon con un guscio sulla schiena e la pelle verdastra ahahaha", "Tentacool_Lino");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Dai Sim, vieni a giocare che stavamo organizzando una partita a Omega Smash Sis tutti insieme", "NoobMaster69");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "VI RICORDO IL COMPITO IN CLASSE DI DOMANI", "Nashi_rose");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Vengo anch’io a giocare!", "Aliz_en");
            risposte.add(msgDto4);
            ChatAppMsg msgDto5 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "OK OK ho capito… fatemi cambiare e arrivo", "Nashi_rose");
            risposte.add(msgDto5);
            ChatAppMsg msgDto6 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Va bene, faccio mangiare i bruchi e arrivo", "Simon");
            risposte.add(msgDto6);
            ChatAppMsg msgDto7 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, user_name + " " + "naturalmente sei invitato anche tu! ^_^", "NoobMaster69");
            risposte.add(msgDto7); //FINE GOOD ENDING
            checkpoint = 100;
        }else if((checkpoint == 58 || checkpoint == 59) && k.equals("Non voglio.")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ehm, ok, non ti preoccupare, cerchiamo dove potrebbe essere….", "Aliz_en");
            risposte.add(msgDto);
            ChatAppMsg msgDto1 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "C’è la biblioteca, la serra e l’acquario. Dovremmo cercare in ogni angolo di questi 3 posti", "Aliz_en");
            risposte.add(msgDto1);
            ChatAppMsg msgDto2 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Io vado in biblioteca, conosco la bibliotecaria potrei chiedere a lei se ha visto Simon ", "Nashi_rose");
            risposte.add(msgDto2);
            ChatAppMsg msgDto3 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Io vado alla serra, noob vuoi venire con me? ", "Tentacool_Lino");
            risposte.add(msgDto3);
            ChatAppMsg msgDto4 = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Va bene, va bene passo sotto casa tua tra 5 minuti", "NoobMaster69");
            risposte.add(msgDto4);
            checkpoint = 80;
        }else if((checkpoint == 80) && k.equals("Allora io andrò all’acquario… dove si trova?")){
            ChatAppMsg msgDto = new ChatAppMsg(ChatAppMsg.MSG_TYPE_RECEIVED, "Ti mando le coordinate, non so dove ti trovi quindi…", "Aliz_en");
            risposte.add(msgDto);
            checkpoint = 81;
            users.get(0).setNew_messages(View.VISIBLE);
            users.get(1).setNew_messages(View.GONE);
        }
        save_karma(c, karma);
        System.out.println(karma);
        save_checkpoint(c, checkpoint, "checkpoint.dat");
        save_users(c, users, "users.dat");
        return risposte;
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

    static private String load_name(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput("name.dat");
        ObjectInputStream is = new ObjectInputStream(fis);
        String simpleClass = (String) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }

    static private int load_karma(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput("karma.dat");
        ObjectInputStream is = new ObjectInputStream(fis);
        int simpleClass = (int) is.readObject();
        is.close();
        fis.close();
        return simpleClass;
    }

    /**
     * Save karma.
     *
     * @param context the context
     * @param karma   the karma
     * @throws IOException the io exception
     */
    static public void save_karma(Context context, int karma) throws IOException {
        FileOutputStream fos = context.openFileOutput("karma.dat", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(karma);
        os.close();
        fos.close();
    }
}
