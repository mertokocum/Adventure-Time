package com.incledrew.login_register;

import static com.incledrew.login_register.R.id.language;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Callable;

public class MainMenu extends AppCompatActivity {


    private MediaPlayer player;
    private int currentIndex = 0;
    private int[] audioFiles = {R.raw.speech1, R.raw.speech2, R.raw.speech3, R.raw.speech4, R.raw.speech5};

    private ImageView markingButton1, markingButton2, markingButton3, markingButton4;

    private TextView vaDescription;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        vaDescription = findViewById(R.id.vaDescription);

        markingButton1 = findViewById(R.id.markingButton1);
        markingButton2 = findViewById(R.id.markingButton2);
        markingButton3 = findViewById(R.id.markingButton3);
        markingButton4 = findViewById(R.id.markingButton4);





        CardView language = findViewById(R.id.language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,Language.class);
                startActivity(intent);
            }
        });

        ImageView virtualAssistant = findViewById(R.id.virtualAssistant);
        virtualAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null && player.isPlaying()) {
                    stopAudioPlayback();
                    vaDescription.setVisibility(View.GONE);
                    markingButton1.setVisibility(View.GONE);
                    markingButton2.setVisibility(View.GONE);
                    markingButton3.setVisibility(View.GONE);
                    markingButton4.setVisibility(View.GONE);

                } else {
                    playSequentialAudio();
                }
            }
        });


         CardView producers = findViewById(R.id.producers);

        producers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,Producers.class);
                startActivity(intent);
            }
        });

        CardView logout =findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"logged out",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainMenu.this, Login.class);
                startActivity(intent);
            }
        });



        CardView learningActivities = findViewById(R.id.learningActivities);
        learningActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, SubMenu.class);
                startActivity(intent);
            }
        });





    }

    public void playSequentialAudio() {
        if (currentIndex < audioFiles.length) {
            int audioFile = audioFiles[currentIndex];
            player = MediaPlayer.create(this, audioFile);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.release();
                    player = null;
                    currentIndex++;
                    playSequentialAudio();
                }
            });

            player.start();

            if(audioFile == R.raw.speech1){
                vaDescription.setVisibility(View.VISIBLE);
            }

            else if (audioFile == R.raw.speech2) {
                vaDescription.setVisibility(View.GONE);
                markingButton1.setVisibility(View.VISIBLE);
            } else if (audioFile == R.raw.speech3) {
                markingButton1.setVisibility(View.GONE);
                markingButton2.setVisibility(View.VISIBLE);
            } else if (audioFile == R.raw.speech4) {
                markingButton2.setVisibility(View.GONE);
                markingButton3.setVisibility(View.VISIBLE);
            } else if (audioFile == R.raw.speech5) {
                markingButton3.setVisibility(View.GONE);
                markingButton4.setVisibility(View.VISIBLE);
            }
            else {
                markingButton4.setVisibility(View.GONE);
            }



        } else {
            currentIndex = 0;
            // Perform any necessary actions after playing all files
        }
    }

    public void stopAudioPlayback() {
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
        }
    }



}




