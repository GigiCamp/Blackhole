package com.example.pa_pe.blackhole.quiz;

/**
 * The type Domande.
 */
public class Domande {
    private String Domande [] = {
            "Sai cos’è il cyber-bullismo?",
            "Secondo te quali sono i rischi maggiori nell’utilizzo dei social network?",
            "Se incontrassi una persona vittima del cyber bullismo cosa faresti?",
            "Da chi andresti se fossi stato vittima di cyber bullismo?",
            "Oltre al bullo e alla vittima, quali sono gli attori del cyberbullismo?"
    };

    private String Risposte [][] = {
            {"Si, me lo hanno spiegato","No, non ne ho mai sentito parlare","Non sono sicuro di cosa si tratti"},
            {"Essere contattati da sconosciuti","Dare libero accesso ai miei dati per chiunque voglia utilizzarli","Non ci sono rischi"},
            {"Lo direi ad un adulto","Parlerei con il diretto interessato","Non farei nulla per paura"},
            {"Un insegnante","I miei genitori","Un amico"},
            {"Osservatori passivi","Osservatori attivi","chi sostiene il bullo, chi osserva e chi difende"}

    };
    private String RisposteGiuste [] = {
            "Si, me lo hanno spiegato",
            "Dare libero accesso ai miei dati per chiunque voglia utilizzarli",
            "Lo direi ad un adulto",
            "I miei genitori",
            "chi sostiene il bullo, chi osserva e chi difende"

    };


    /**
     * Gets domanda.
     *
     * @param a the a
     * @return the domanda
     */
    public String getDomanda(int a)
    {
        String domanda = Domande[a];
        return domanda;
    }

    /**
     * Gets risposta 1.
     *
     * @param a the a
     * @return the risposta 1
     */
    public String getRisposta1(int a)
    {
       String scelta0 = Risposte[a][0];
       return scelta0;
    }

    /**
     * Gets risposta 2.
     *
     * @param a the a
     * @return the risposta 2
     */
    public String getRisposta2(int a)
    {
        String scelta1 = Risposte[a][1];
        return scelta1;
    }

    /**
     * Gets risposta 3.
     *
     * @param a the a
     * @return the risposta 3
     */
    public String getRisposta3(int a)
    {
        String scelta2 = Risposte[a][2];
        return scelta2;
    }

    /**
     * Risposta corretta string.
     *
     * @param a the a
     * @return the string
     */
    public String RispostaCorretta(int a)
    {
       String risposta = RisposteGiuste[a];
       return risposta;
    }
}
