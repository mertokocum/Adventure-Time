package com.incledrew.login_register;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MonthsGame extends AppCompatActivity {


    private static final String[] months = {"january", "february", "march", "april", "may", "june", "july","august", "september","october","november","december"};
    private List<String> shuffledMonths;
    private List<String> userSequence;

    private TextView questionTextView;
    private Button[] monthButtons;

    private ImageView backbuttonMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months_game);

        questionTextView = findViewById(R.id.questionTextView);

        backbuttonMonths = findViewById(R.id.backButtonMonths);

        backbuttonMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MonthsGame.this, MonthsLearning.class);
                startActivity(intent);
            }
        });

        Button januaryButton = findViewById(R.id.januaryButton);
        Button februaryButton = findViewById(R.id.februaryButton);
        Button marchButton = findViewById(R.id.marchButton);
        Button aprilButton = findViewById(R.id.aprilButton);
        Button mayButton = findViewById(R.id.mayButton);
        Button juneButton = findViewById(R.id.juneButton);
        Button julyButton = findViewById(R.id.julyButton);
        Button augustButton = findViewById(R.id.augustButton);
        Button septemberButton = findViewById(R.id.septemberButton);
        Button octoberButton = findViewById(R.id.octoberButton);
        Button novemberButton = findViewById(R.id.novemberButton);
        Button decemberButton = findViewById(R.id.decemberButton);


        monthButtons = new Button[]{januaryButton, februaryButton, marchButton, aprilButton, mayButton, juneButton, julyButton, augustButton, septemberButton, octoberButton, novemberButton, decemberButton};

        setShuffledMonths();
        startGame();

    }

    private void setShuffledMonths() {
        shuffledMonths = new ArrayList<>(Arrays.asList(months));
        Collections.shuffle(shuffledMonths);
    }

    private void startGame() {
        questionTextView.setText("Select the months in the correct order!");

        for (int i = 0; i < monthButtons.length; i++) {
            final int index = i;
            monthButtons[i].setText(shuffledMonths.get(i).toUpperCase());
            monthButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    daySelected(index);
                }
            });
        }

        userSequence = new ArrayList<>();
    }

    private void daySelected(int index) {
        String selectedDay = shuffledMonths.get(index).toLowerCase();
        userSequence.add(selectedDay);
        monthButtons[index].setEnabled(false);

        if (userSequence.size() == months.length) {
            checkIt();
        }
    }

    private void checkIt() {
        boolean trueSequence = true;
        for (int i = 0; i < months.length; i++) {
            if (!months[i].equals(userSequence.get(i))) {
                trueSequence = false;
                break;
            }
        }

        String resultMessage;
        if (trueSequence) {
            resultMessage = "Congratulations! You Win!";
            playSound(R.raw.clap);
        } else {
            resultMessage = "You Failed! The game is restarting!";
            playSound(R.raw.fail);
            userSequence.clear();
            for (Button button : monthButtons) {
                button.setEnabled(true);
            }
        }

        questionTextView.setText(resultMessage);
    }
    private void playSound(int soundResId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResId);
        mediaPlayer.start();
    }

}