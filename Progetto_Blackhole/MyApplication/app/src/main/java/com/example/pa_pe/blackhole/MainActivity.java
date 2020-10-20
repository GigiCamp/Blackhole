    package com.example.pa_pe.blackhole;

    import android.content.Intent;
    import android.content.pm.ActivityInfo;
    import android.media.MediaPlayer;
    import android.os.Bundle;
    import android.os.Handler;
    import android.view.View;
    import android.widget.ProgressBar;

    import com.example.pa_pe.blackhole.menu.MenuActivity;

    import androidx.appcompat.app.AppCompatActivity;

    /**
     * The type Main activity.
     */
    public class MainActivity extends AppCompatActivity {


        private ProgressBar progressBar;
        private int progressStatus = 0;
        private Handler handler = new Handler();
        // Used to load the 'native-lib' library on application startup.
        static {
            System.loadLibrary("native-lib");
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loading);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            onWindowFocusChanged(true);

            MediaPlayer sound = MediaPlayer.create(this, R.raw.intro);
            sound.start();

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            // Start long running operation in a background thread
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sound.stop();
                    final Intent mainIntent = new Intent(MainActivity.this, MenuActivity.class);
                    MainActivity.this.startActivity(mainIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    MainActivity.this.finish();
                }
            }).start();
        }

        /**
         * A native method that is implemented by the 'native-lib' native library,
         * which is packaged with this application.
         *
         * @return the string
         */
        public native String stringFromJNI();

        @Override
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

    }
