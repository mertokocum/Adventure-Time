package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Days extends AppCompatActivity {

    private Button mondayButton, tuesdayButton, wednesdayButton, thursdayButton, fridayButton, saturdayButton, sundayButton;
    private boolean isSoundOn = false;
    ImageView backButtonDays;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);







        backButtonDays = findViewById(R.id.backButtonDays);
        mondayButton = findViewById(R.id.mondayButton);
        tuesdayButton = findViewById(R.id.tuesdayButton);
        wednesdayButton = findViewById(R.id.wednesdayButton);
        thursdayButton = findViewById(R.id.thursdayButton);
        fridayButton = findViewById(R.id.fridayButton);
        saturdayButton = findViewById(R.id.saturdayButton);
        sundayButton = findViewById(R.id.sundayButton);

        backButtonDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Days.this, SubMenu.class);
                startActivity(intent);
            }
        });


        mondayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Monday");
                if(isSoundOn){
                    playSound(R.raw.monday);
                }


            }
        });

        tuesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Tuesday");
                if(isSoundOn){
                    playSound(R.raw.tuesday);
                }

            }
        });

        wednesdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Wednesday");
                if(isSoundOn){
                    playSound(R.raw.wednesday);
                }

            }
        });

        thursdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Thursday");
                if(isSoundOn){
                    playSound(R.raw.thursday);
                }

            }
        });

        fridayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Friday");
                if(isSoundOn){
                    playSound(R.raw.friday);
                }

            }
        });

        saturdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Saturday");
                if(isSoundOn){
                    playSound(R.raw.saturday);
                }

            }
        });

        sundayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Sunday");
                if(isSoundOn){
                    playSound(R.raw.sunday);
                }

            }
        });
        ToggleButton soundToggle = findViewById(R.id.soundToggle);
        soundToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSoundOn = isChecked;
                if (isChecked) {
                    soundToggle.setBackgroundResource(R.drawable.sound_on_icon); //change button background
                } else {
                    soundToggle.setBackgroundResource(R.drawable.sound_off);
                }
            }

        });
    }


    private void showToast(String message) {

    }

    private void playSound(int soundResId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResId);
        mediaPlayer.start();
    }
}

