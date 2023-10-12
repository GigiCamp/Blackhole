package com.example.pa_pe.blackhole.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.dashboard.DashBoardActivity;
import com.example.pa_pe.blackhole.quiz.QuizActivity;
import com.google.android.material.card.MaterialCardView;

import java.io.File;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


/**
 * The type Menu activity.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onWindowFocusChanged(true);

        MediaPlayer sound = MediaPlayer.create(this, R.raw.menu_sound);
        sound.setLooping(true);
        sound.start();
        sound.setVolume(10, 10);

        MaterialCardView nuova_partita = (MaterialCardView) findViewById(R.id.nuova_partita);
        nuova_partita.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                File file = new File(getApplicationContext().getFilesDir(), "chat.dat");
                File file1 = new File(getApplicationContext().getFilesDir(), "post.dat");
                File file2 = new File(getApplicationContext().getFilesDir(), "users.dat");
                File file3 = new File(getApplicationContext().getFilesDir(), "checkpoint.dat");
                File file4 = new File(getApplicationContext().getFilesDir(), "name.dat");
                File file5 = new File(getApplicationContext().getFilesDir(), "karma.dat");

                if (file.exists() || file1.exists() || file2.exists() || file3.exists() || file4.exists() || file5.exists()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this, R.style.Theme_MaterialComponents_Dialog);
                    // Ask the final question
                    builder.setMessage("Sei sicuro di voler eliminare i tuoi progressi?");

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Sì", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when user clicked the Yes button
                            // Set the TextView visibility GONE
                            file.delete();
                            file1.delete();
                            file2.delete();
                            file3.delete();
                            file4.delete();
                            file5.delete();
                            findViewById(R.id.superhero1_progressbar).setVisibility(View.VISIBLE);
                            Intent intent = new Intent(v.getContext(), IntroActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                            sound.stop();
                        }
                    });

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                            Toast.makeText(getApplicationContext(),
                                    "Non hai eliminato i tuoi progressi", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();
                } else {
                    findViewById(R.id.superhero1_progressbar).setVisibility(View.VISIBLE);
                    Intent intent = new Intent(v.getContext(), IntroActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    sound.stop();
                }
            }
        });


        MaterialCardView continua = (MaterialCardView) findViewById(R.id.continua);
        continua.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                findViewById(R.id.superhero2_progressbar).setVisibility(View.VISIBLE);
                Intent intent = new Intent(v.getContext(), DashBoardActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                sound.stop();
            }
        });

        MaterialCardView extra = (MaterialCardView) findViewById(R.id.extra);
        extra.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                findViewById(R.id.superhero3_progressbar).setVisibility(View.VISIBLE);
                Intent intent = new Intent(v.getContext(), ExtraActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                sound.stop();
            }
        });

        MaterialCardView quiz = (MaterialCardView) findViewById(R.id.quiz);
        quiz.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                findViewById(R.id.superhero4_progressbar).setVisibility(View.VISIBLE);
                Intent intent = new Intent(v.getContext(), QuizActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                sound.stop();
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

    }
}
