package com.incledrew.login_register;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DaysGame extends AppCompatActivity {

    private static final String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    private List<String> shuffledDays;
    private List<String> userSort;

    private TextView soruTextview;
    private Button[] daysButtons;

    ImageView backButtonDaysGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_game);

        backButtonDaysGame = findViewById(R.id.backButtonDaysGame);

        backButtonDaysGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaysGame.this, SubMenu.class);
                startActivity(intent);
            }
        });
        soruTextview = findViewById(R.id.soruTextView);

        Button mondayButton = findViewById(R.id.pazartesiButton);
        Button tuesdayButton = findViewById(R.id.saliButton);
        Button wednesdayButton = findViewById(R.id.carsambaButton);
        Button thursdayButton = findViewById(R.id.persembeButton);
        Button fridayButton = findViewById(R.id.cumaButton);
        Button saturdayButton = findViewById(R.id.cumartesiButton);
        Button sundayButton = findViewById(R.id.pazarButton);


        daysButtons = new Button[]{mondayButton, tuesdayButton, wednesdayButton, thursdayButton, fridayButton, saturdayButton, sundayButton};

        setShuffledGames();
        startGame();

    }

    private void setShuffledGames() {
        shuffledDays = new ArrayList<>(Arrays.asList(days));
        Collections.shuffle(shuffledDays);
    }

    private void startGame() {
        soruTextview.setText("Sort the days of the week!");

        for (int i = 0; i < daysButtons.length; i++) {
            final int index = i;
            daysButtons[i].setText(shuffledDays.get(i).toUpperCase());
            daysButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    daySelected(index);
                }
            });
        }

        userSort = new ArrayList<>();
    }

    private void daySelected(int index) {
        String selectedDay = shuffledDays.get(index).toLowerCase();
        userSort.add(selectedDay);
        daysButtons[index].setEnabled(false);

        if (userSort.size() == days.length) {
            checkIt();
        }
    }

    private void checkIt() {
        boolean correctSort = true;
        for (int i = 0; i < days.length; i++) {
            if (!days[i].equals(userSort.get(i))) {
                correctSort = false;
                break;
            }
        }

        String resultMessage;
        if (correctSort) {
            resultMessage = "Congratulations! You've sorted the days correctly.";
            playSound(R.raw.clap);
        } else {
            resultMessage = "Sorry, you've sorted the days wrong. The game is restarting.";
            playSound(R.raw.fail);
            userSort.clear();
            for (Button button : daysButtons) {
                button.setEnabled(true);
            }
        }

        soruTextview.setText(resultMessage);
    }
    private void playSound(int soundResId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResId);
        mediaPlayer.start();
    }

}