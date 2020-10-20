package com.example.pa_pe.blackhole.quiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pa_pe.blackhole.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Quiz activity.
 */
public class QuizActivity extends AppCompatActivity {

    private Domande Domande = new Domande();
    private int Cont = 0;
    private TextView ScrittaPunteggio;
    private TextView Domanda;
    private TextView Scelta1;
    private TextView Scelta2;
    private TextView Scelta3;

    private String Risposta;
    private int Punteggio = 0;
    private int NumeroDomanda = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            NumeroDomanda = extras.getInt("Domanda");
            Punteggio = extras.getInt("Punteggio");
        }

        setContentView(R.layout.activity_quiz);
        onWindowFocusChanged(true);
        ScrittaPunteggio = (TextView) findViewById(R.id.score);
        ScrittaPunteggio.setText(String.valueOf(Punteggio));
        Domanda = (TextView) findViewById(R.id.domanda);
        Scelta1 = (TextView) findViewById(R.id.c1);
        Scelta2 = (TextView) findViewById(R.id.c2);
        Scelta3 = (TextView) findViewById(R.id.c3);

        AggiornaDomanda();

        //Inizio listener per bottone 1
        Scelta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Scelta1.getText().equals(Risposta)) {
                    Punteggio = Punteggio+1;
                    AggiornaPunteggio(Punteggio);
                    Toast.makeText(QuizActivity.this, "Risposta corretta!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, TextActivity.class);
                    intent.putExtra("Domanda", NumeroDomanda);
                    intent.putExtra("Punteggio", Punteggio);
                    startActivity(intent);
                    finish();
                }else{
                        Toast.makeText(QuizActivity.this, "Risposta sbagliata",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QuizActivity.this, TextActivity.class);
                        intent.putExtra("Domanda", NumeroDomanda);
                        intent.putExtra("Punteggio", Punteggio);
                    startActivity(intent);
                    finish();
                    }
            }

        });

        //Fine listener per bottone 1



        //Inizio listener per bottone 2
        Scelta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Scelta2.getText().equals(Risposta)) {
                    Punteggio = Punteggio+1;
                    AggiornaPunteggio(Punteggio);
                    Toast.makeText(QuizActivity.this, "Risposta corretta!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, TextActivity.class);
                    intent.putExtra("Domanda", NumeroDomanda);
                    intent.putExtra("Punteggio", Punteggio);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(QuizActivity.this, "Risposta sbagliata",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, TextActivity.class);
                    intent.putExtra("Domanda", NumeroDomanda);
                    intent.putExtra("Punteggio", Punteggio);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //Fine listener per bottone 2

        //Inizio listener per bottone 3
        Scelta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Scelta3.getText() == Risposta) {
                    Punteggio = Punteggio+1;
                    AggiornaPunteggio(Punteggio);
                    Toast.makeText(QuizActivity.this, "Risposta corretta!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, TextActivity.class);
                    intent.putExtra("Domanda", NumeroDomanda);
                    intent.putExtra("Punteggio", Punteggio);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(QuizActivity.this, "Risposta sbagliata",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, TextActivity.class);
                    intent.putExtra("Domanda", NumeroDomanda);
                    intent.putExtra("Punteggio", Punteggio);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //Fine listener per bottone 1
    }

    private void AggiornaDomanda()
    {
        Domanda.setText(Domande.getDomanda(NumeroDomanda));
        Scelta1.setText(Domande.getRisposta1(NumeroDomanda));
        Scelta2.setText(Domande.getRisposta2(NumeroDomanda));
        Scelta3.setText(Domande.getRisposta3(NumeroDomanda));

        Risposta = Domande.RispostaCorretta(NumeroDomanda);
        NumeroDomanda++;
    }


    private void AggiornaPunteggio(int punteggio)
    {
        ScrittaPunteggio.setText("" + punteggio);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onBackPressed(){

    }
}
