package com.example.pa_pe.blackhole.quiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.menu.MenuActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The type End quiz activity.
 */
public class EndQuizActivity extends AppCompatActivity {

    /**
     * The End quiz.
     */
    TextView end_quiz;
    /**
     * The Punteggio.
     */
    int punteggio = 0;
    /**
     * The Final score.
     */
    TextView final_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endquiz);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onWindowFocusChanged(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            punteggio = extras.getInt("Punteggio");
            //The key argument here must match that used in the other activity
        }

        final_score = findViewById(R.id.final_score);
        final_score.setText(String.valueOf(punteggio));

        end_quiz = findViewById(R.id.end_quiz);
        end_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndQuizActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
        super.onBackPressed();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}
