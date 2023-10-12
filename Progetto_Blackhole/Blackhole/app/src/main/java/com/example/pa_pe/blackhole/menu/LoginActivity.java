package com.example.pa_pe.blackhole.menu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;

import com.example.pa_pe.blackhole.R;
import com.example.pa_pe.blackhole.dashboard.DashBoardActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The type Login activity.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * The Input.
     */
    MaterialCardView input;
    /**
     * The Name.
     */
    TextInputEditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        onWindowFocusChanged(true);

        input = findViewById(R.id.button);
        name = findViewById(R.id.name);

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };


        name.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(10)});

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().equals("")){
                    try {
                        save_name(name.getText().toString());
                        Intent intent = new Intent(v.getContext(), DashBoardActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{

                }
            }
        });
    }

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

    private void save_name(String name) throws IOException {
        FileOutputStream fos = this.openFileOutput("name.dat", Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(name);
        os.close();
        fos.close();
    }

    @Override
    public void onBackPressed(){

    }
}
