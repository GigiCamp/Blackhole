package com.example.pa_pe.blackhole.chat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * La classe Chat definisce la lista delle chat con gli utenti
 */
public class Chat implements Serializable {
    /**
     * E' stata usata la struttura del TreeMap per associare al nome della chat una lista dei messaggi associati
     */
    TreeMap<String, ArrayList<ChatAppMsg>> chat;

    /**
     * Costrutture
     *
     * In questo costrutture vengono inizializzate le chat con cui l'utente andrà ad interagire
     */
    public Chat(){
        chat = new TreeMap<String, ArrayList<ChatAppMsg>>();
        chat.put("Sconosciuto", new ArrayList<ChatAppMsg>());
        chat.put("Gli Intergalattici", new ArrayList<ChatAppMsg>());
        chat.put("Dark_Chad", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto1", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto2", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto3", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto4", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto5", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto6", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto7", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto8", new ArrayList<ChatAppMsg>());
        chat.put("Sconosciuto9", new ArrayList<ChatAppMsg>());
    }

    /**
     * @param n il nome della chat
     * @return la lista dei messaggi associati alla chat
     */
    public ArrayList<ChatAppMsg> get_list(String n){
        return chat.get(n);
    }
}
