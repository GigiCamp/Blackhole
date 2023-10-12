package com.example.pa_pe.blackhole.chat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * La classe ChatAppMsg definisce gli attributi e metodi associati ai messaggi.
 */
public class ChatAppMsg implements Serializable {

    /**
     * La costante associata ai messaggi inviati
     */
    public final static String MSG_TYPE_SENT = "MSG_TYPE_SENT";

    /**
     * La costante associata ai messaggi ricevuti
     *
     */
    public final static String MSG_TYPE_RECEIVED = "MSG_TYPE_RECEIVED";

    // Contenuto del messaggio
    private String msgContent;

    // Tipologia del messaggio
    private String msgType;

    // Data di invio o di ricezione del messaggio
    private String msgTime;

    // Nome dell'utente che ha inviato il messaggio
    private String msgName;

    //Immagine dell'utente che ha inviato il messaggio
    private int img;


    /**
     * Costruttore
     *
     * @param msgType    la tipologia di messaggio
     * @param msgContent il contenuto del messaggio
     * @param msgName    il nome dell'utente che ha inviato il messaggio
     */
    public ChatAppMsg(String msgType, String msgContent, String msgName) {
        this.msgType = msgType;
        this.msgName = msgName;
        this.msgContent = msgContent;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        this.msgTime = dateFormat.format(new Date());
    }

    /**
     * Costruttore
     *
     * @param msgType    la tipologia del messaggio
     * @param msgContent il contenuto del messaggio
     * @param msgName    il nome dell'utente che ha inviato il messaggio
     * @param msgTime    l'ora in cui è stato inviato il messaggio
     */
    public ChatAppMsg(String msgType, String msgContent, String msgName, String msgTime){
        this.msgType = msgType;
        this.msgName = msgName;
        this.msgContent = msgContent;
        this.msgTime = msgTime;
    }

    /**
     * @return il contenuto del messaggio
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent il contenuto del messaggio
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return la tipologia del messaggio
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * @param msgType la tipologia del messaggio
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return l'ora in cui è stato inviato il messaggio
     */
    public String getMsgTime(){
        return msgTime;
    }

    /**
     * @param msgTime l'ora in cui è stato inviato il messaggio
     */
    public void setMsgTime(String msgTime){
        this.msgTime = msgTime;
    }

    /**
     * @return il nome dell'utente che ha inviato il messaggio
     */
    public String getMsgName(){
        return msgName;
    }

    /**
     * @param msgName il nome dell'utente del messaggio
     */
    public void setMsgName(String msgName){
        this.msgName = msgName;
    }

    /**
     * @return l'immagine dell'utente che ha inviato il messaggio
     */
    public int getImg() {
        return img;
    }
}
