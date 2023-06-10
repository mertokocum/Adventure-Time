package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.Scanner;

public class Spelling extends AppCompatActivity {
    private TextView wordTextView;
    private EditText spellingEditText;
    private Button checkButton;
    private Button exitButton;

    private Button nextButton;
    private TextView feedbackTextView;
    private int counterIndex = 0;

    private String[] targetWord = {"playground","bubble","princess","game","happy","friend","explore","learn","smile","adventure"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling);

        // Initialize views
        wordTextView = findViewById(R.id.wordTextView);
        spellingEditText = findViewById(R.id.spellingEditText);
        checkButton = findViewById(R.id.checkButton);
        feedbackTextView = findViewById(R.id.feedbackTextView);
        nextButton = findViewById(R.id.nextButtonSpell);
        wordTextView.setText(targetWord[0]);
        exitButton = findViewById(R.id.exitButtonSpell);


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Spelling.this, SubMenu.class);
                startActivity(intent);
            }
        });



        // Set the target word
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterIndex++;
                wordTextView.setText(targetWord[counterIndex]);

            }
        });

        // Set onClickListener for the check button
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userSpelling = spellingEditText.getText().toString().trim();

                switch (counterIndex) {
                    case 0:

                        if (userSpelling.equalsIgnoreCase("PLAY-GROUND")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 1:

                        if (userSpelling.equalsIgnoreCase("BUB-BLE")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 2:
                        if (userSpelling.equalsIgnoreCase("PRIN-CESS")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;

                    case 3:

                        if (userSpelling.equalsIgnoreCase("GAME")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 4:

                        if (userSpelling.equalsIgnoreCase("HAP-PY")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 5:

                        if (userSpelling.equalsIgnoreCase("FRIEND")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 6:
                        if (userSpelling.equalsIgnoreCase("EX-PLORE")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;

                    case 7:

                        if (userSpelling.equalsIgnoreCase("LEARN")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 8:

                        if (userSpelling.equalsIgnoreCase("SMILE")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;
                    case 9:

                        if (userSpelling.equalsIgnoreCase("AD-VEN-TURE")) {
                            feedbackTextView.setText("Correct spelling!");
                        } else {
                            feedbackTextView.setText("Incorrect spelling. Try again!");
                        }
                        break;




                }
            }
        });
        }




    }





