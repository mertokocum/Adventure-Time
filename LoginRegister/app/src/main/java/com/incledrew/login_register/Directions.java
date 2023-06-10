package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Directions extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private RadioButton[] radioButtons;
    private Button previousButton, nextButton;
    private String[] directions = {"LEFT", "RIGHT", "BEHIND", "BETWEEN", "IN FRONT OF", "ON","BESIDE","OUT","IN","INTO"};
    private int currentIndex = 0;

    private ImageView backbuttonDirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        backbuttonDirections = findViewById(R.id.backButtonDirections);

        textView = findViewById(R.id.textView);
        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        imageView = findViewById(R.id.imageView);
        radioButtons = new RadioButton[10];
        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);
        radioButtons[3] = findViewById(R.id.radioButton4);
        radioButtons[4] = findViewById(R.id.radioButton5);
        radioButtons[5] = findViewById(R.id.radioButton6);
        radioButtons[6] = findViewById(R.id.radioButton7);
        radioButtons[7] = findViewById(R.id.radioButton8);
        radioButtons[8] = findViewById(R.id.radioButton9);
        radioButtons[9] = findViewById(R.id.radioButton10);

        updateTextView();


        backbuttonDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Directions.this, SubMenu2.class);
                startActivity(intent);
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateTextView();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < directions.length - 1) {
                    currentIndex++;
                    updateTextView();
                }
            }
        });
    }

    private void updateTextView() {
        textView.setText(directions[currentIndex]);

        String imageName = directions[currentIndex].toLowerCase();
        int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
        } else {
            imageView.setImageResource(R.drawable.in_front_of);
        }

        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setChecked(i == currentIndex);
        }
    }
}