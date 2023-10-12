package com.example.pa_pe.blackhole.quiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * The type Text activity.
 */
public class TextActivity extends AppCompatActivity {

    private Text Testi= new Text();
    private AppCompatTextView Continua;
    private TextView Testo;
    private int Punteggio = 0;
    private int domanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onWindowFocusChanged(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            domanda = extras.getInt("Domanda");
            Punteggio = extras.getInt("Punteggio");
            //The key argument here must match that used in the other activity
        }

        if(domanda == 5){
            Intent intent = new Intent(TextActivity.this, EndQuizActivity.class);
            intent.putExtra("Punteggio", Punteggio);
            startActivity(intent);
            finish();
        }

        Continua = (AppCompatTextView) findViewById(R.id.continua);
        Testo = (TextView) findViewById(R.id.Testo);
        AggiornaTesto();


        Continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TextActivity.this, QuizActivity.class);
                intent.putExtra("Domanda", domanda);
                intent.putExtra("Punteggio", Punteggio);
                startActivity(intent);
                finish();
            }
        });


    }

    /**
     * Aggiorna testo.
     */
    public void AggiornaTesto(){ ;
        Testo.setText(Testi.getTesto(domanda-1));
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
