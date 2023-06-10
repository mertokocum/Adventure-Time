package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Typeface;
import android.media.MediaPlayer;

import android.view.View;

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MonthsLearning extends AppCompatActivity {

    private Button januaryButton, februaryButton, marchButton, aprilButton, mayButton, juneButton, julyButton, augustButton, septemberButton, octoberButton, novemberButton, decemberButton;
    private boolean isSoundOn = false;

    private ImageView backbuttonMonths;
    private ImageView nextbuttonMonths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_learning);

        backbuttonMonths = findViewById(R.id.backButtonMonths);
        nextbuttonMonths = findViewById(R.id.nextButtonMonths);

        nextbuttonMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthsLearning.this, MonthsGame.class);
                startActivity(intent);
            }
        });


        backbuttonMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthsLearning.this, SubMenu.class);
                startActivity(intent);
            }
        });

        januaryButton = findViewById(R.id.januaryButton);
        februaryButton = findViewById(R.id.februaryButton);
        marchButton = findViewById(R.id.marchButton);
        aprilButton = findViewById(R.id.aprilButton);
        mayButton = findViewById(R.id.mayButton);
        juneButton = findViewById(R.id.juneButton);
        julyButton = findViewById(R.id.julyButton);
        augustButton = findViewById(R.id.augustButton);
        septemberButton = findViewById(R.id.septemberButton);
        octoberButton = findViewById(R.id.octoberButton);
        novemberButton = findViewById(R.id.novemberButton);
        decemberButton = findViewById(R.id.decemberButton);


        januaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("january");
                if (isSoundOn) {

                    playSound(R.raw.january);
                }
            }
        });

        februaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("february");
                if (isSoundOn) {

                    playSound(R.raw.february);
                }
            }
        });

        marchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("march");
                if (isSoundOn) {

                    playSound(R.raw.march);
                }
            }
        });

        aprilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("april");
                if (isSoundOn) {

                    playSound(R.raw.april);
                }
            }
        });

        mayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("may");
                if (isSoundOn) {

                    playSound(R.raw.may);
                }
            }
        });

        juneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("june");
                if (isSoundOn) {

                    playSound(R.raw.june);
                }
            }
        });

        julyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("july");
                if (isSoundOn) {

                    playSound(R.raw.july);
                }
            }
        });
        augustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("august");
                if (isSoundOn) {

                    playSound(R.raw.august);
                }
            }
        });
        septemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("september");
                if (isSoundOn) {

                    playSound(R.raw.september);
                }
            }
        });
        octoberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("october");
                if (isSoundOn) {

                    playSound(R.raw.october);
                }
            }
        });
        novemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("november");
                if (isSoundOn) {

                    playSound(R.raw.november);
                }
            }
        });
        decemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("december");
                if (isSoundOn) {

                    playSound(R.raw.december);
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
