package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SeasonsLearning extends AppCompatActivity {
    private ImageView seasonImage;
    private Button next;
    private Button previous;

    TextView textSeason;
    TextView textInfo;



    private String[] infos = {"Autumn is the season of the year between summer and winter during which temperatures gradually decrease",
    "Winter is the three calendar months with the lowest average temperatures which has snowy days in general",
            "Spring is the season during which the natural world revives and reinvigorates after the colder winter months",
            "Summer is the hottest of the four temperate seasons, occurring after spring and before autumn"};
    private int[] seasonImages = {R.drawable.autmn, R.drawable.winter, R.drawable.spring, R.drawable.summer};

    private int currentSeasonIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_learning);

        seasonImage = findViewById(R.id.seasonImage);
        seasonImage.setImageResource(seasonImages[0]);


        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        textSeason = findViewById(R.id.textSeason);
        textInfo = findViewById(R.id.textInfo);


        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeasonsLearning.this, SubMenu.class);
                startActivity(intent);
            }
        });

    }

    public void nextSeason(View view) {
        currentSeasonIndex++;
        if (currentSeasonIndex > 3) {
            currentSeasonIndex = 0;
        }
        seasonImage.setImageResource(seasonImages[currentSeasonIndex]);



        switch (currentSeasonIndex) {

            case 0:
                textSeason.setText("AUTUMN");
                textInfo.setText("Autumn is the season of the year between summer and winter during which temperatures gradually decrease");

                break;
            case 1:
                textSeason.setText("WINTER");
                textInfo.setText("Winter is the three calendar months with the lowest average temperatures which has snowy days in general");

                break;
            case 2:
                textSeason.setText("SPRING");
                textInfo.setText("Spring is the season during which the natural world revives and reinvigorates after the colder winter months");
                break;
            case 3:

                textSeason.setText("SUMMER");
                textInfo.setText("Summer is the hottest of the four temperate seasons, occurring after spring and before autumn");

                break;

        }
    }

    public void previousSeason (View view){
        currentSeasonIndex--;
        if (currentSeasonIndex < 0) {
            currentSeasonIndex = 3;
        }
        seasonImage.setImageResource(seasonImages[currentSeasonIndex]);


        switch (currentSeasonIndex) {

            case 0:
                textSeason.setText("AUTUMN");
                textInfo.setText("Autumn is the season of the year between summer and winter during which temperatures gradually decrease");

                break;
            case 1:
                textSeason.setText("WINTER");
                textInfo.setText("Winter is the three calendar months with the lowest average temperatures which has snowy days in general");
                break;
            case 2:
                textSeason.setText("SPRING");
                textInfo.setText("Spring is the season during which the natural world revives and reinvigorates after the colder winter months");
                break;
            case 3:

                textSeason.setText("SUMMER");
                textInfo.setText("Summer is the hottest of the four temperate seasons, occurring after spring and before autumn");

                break;

        }
    }



}