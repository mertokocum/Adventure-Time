package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainMenuTurkish extends AppCompatActivity {

    MediaPlayer player;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_turkish);



        CardView language = findViewById(R.id.language);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuTurkish.this,Language.class);
                startActivity(intent);
            }
        });

        CardView logout =findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"çıkış yapıldı",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainMenuTurkish.this, LoginTurkish.class);
                startActivity(intent);
            }
        });


        CardView learningActivities = findViewById(R.id.learningActivities1);

        learningActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuTurkish.this, SubMenuTurkish.class);
                startActivity(intent);
            }
        });




    }

    public void play(View V){
        if(player==null){
            player = MediaPlayer.create(this,R.raw.speech1);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    private void stopPlayer(){
        if(player!= null){
            player.release();
            player = null;
        }
    }
}