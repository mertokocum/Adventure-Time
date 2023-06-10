package com.incledrew.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Random;

public class RememberDigits extends AppCompatActivity {
    Random random = new Random();
    int[] randomDigits;
    TextView digitText;
    int level = 0;
    int currentDigit;
    int[] userInput;
    int i = 0;
    CheckBox backwardCheckbox;
    int requiredInputCount;
    boolean isBackward = false;

    private DatabaseReference databaseReference;
    private String userId;

    private TextView pointsText;
    private int points;

    private boolean isMessageShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_digits);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        } else {

        }


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        digitText = findViewById(R.id.digitText);
        ImageView backButtonDigits = findViewById(R.id.backButtonDigits);

        randomDigits = new int[10];
        userInput = new int[10];
        backwardCheckbox = findViewById(R.id.backwardCheckbox);
        Button restartButton = findViewById(R.id.restartButton);
        pointsText = findViewById(R.id.pointsText);
        displayPoints();

        backButtonDigits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RememberDigits.this, SubMenu.class);
                startActivity(intent);
            }
        });



        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        backwardCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isBackward = isChecked;
                restartGame();
            }
        });

        // Buttons for digits 0 to 9
        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(0);
            }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(1);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(2);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(3);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(4);
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(5);
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(6);
            }
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(7);
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(8);
            }
        });

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDigit(9);
            }
        });

        play(level); // Start the game
    }

    private void setCurrentDigit(int digit) {
        currentDigit = digit;

        if (waitingForUserInput()) {
            userInput[i] = currentDigit; // Store the input in the correct index
            i++;
            requiredInputCount--;

            if (requiredInputCount == 0) {
                i = 0;
                checkUserInput();
            }
        }
    }

    private boolean waitingForUserInput() {
        return requiredInputCount > 0;
    }

    private void checkUserInput() {
        boolean isCorrect = true; // Assume the input is correct
        if (isBackward) {
            for (int j = level; j >= 0; j--) {
                if (userInput[j] != randomDigits[level - j]) {
                    isCorrect = false; // If any digit doesn't match, set isCorrect to false
                    break;
                }
            }
        } else {
            for (int j = 0; j <= level; j++) {
                if (userInput[j] != randomDigits[j]) {
                    isCorrect = false; // If any digit doesn't match, set isCorrect to false
                    break;
                }
            }
        }

        if (isCorrect) {
            updatePoints(10);
            showResponseMessage("You won!");
            level++;
            if (level > 9) {
                showResponseMessage("You won!");
                return;
            }
            play(level);
        } else {
            showResponseMessage("Wrong! Try again.");
            restartGame();
        }
    }

    private void updatePoints(final int pointsToAdd) {
        points += pointsToAdd;
        pointsText.setText("Points: " + points);

        // Update points in Firebase Realtime Database
        DatabaseReference userRef = databaseReference.child(userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int storedPoints = dataSnapshot.child("points").getValue(Integer.class);
                    int newPoints = storedPoints + pointsToAdd;
                    userRef.child("points").setValue(newPoints);
                } else {
                    // User data doesn't exist, handle accordingly
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
    private void displayPoints() {
        // Retrieve the points value from the Firebase Realtime Database
        databaseReference.child(userId).child("points").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve the points value from the snapshot
                    int points = dataSnapshot.getValue(Integer.class);
                    pointsText.setText("Points: " + points);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur while retrieving the data
            }
        });
    }
    private void play(int startLevel) {
        requiredInputCount = startLevel + 1;
        resetUserInput();

        generateRandomDigits(startLevel);

        digitText.postDelayed(new Runnable() {
            @Override
            public void run() {
                showRandomDigits(startLevel);
                waitForUserInput();
            }
        }, (startLevel + 1) * 1500 + 100); // Add an extra delay of 1000ms before showing random digits
    }

    private void resetUserInput() {
        Arrays.fill(userInput, 0);
    }

    private void generateRandomDigits(int level) {
        for (int i = 0; i <= level; i++) {
            randomDigits[i] = random.nextInt(10);
        }
    }

    private void showRandomDigits(int level) {
        digitText.setText("");
        enableDigitButtons(false); // Disable digit buttons during display
        for (int i = 0; i <= level; i++) {
            final int index = i;
            digitText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    digitText.setText(String.valueOf(randomDigits[index]));
                    digitText.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            digitText.setText("");
                            if (index == level) {
                                enableDigitButtons(true); // Re-enable digit buttons after display
                                waitForUserInput(); // Call waitForUserInput after enabling the buttons
                            }
                        }
                    }, 500); // Delay for 500ms to make the text invisible
                }
            }, (i + 1) * 1500); // Delay between digits is 1500ms (1000ms + 500ms)
        }
    }

    private void waitForUserInput() {
        if (!isMessageShown) {
            showResponseMessage("Enter the digits you remembered");
            isMessageShown = true;
        }

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.VISIBLE);

        // Reset requiredInputCount to the appropriate value
        requiredInputCount = level + 1;
    }

    private void restartGame() {
        level = 0;
        play(level);
    }

    private void enableDigitButtons(boolean enable) {
        Button button0 = findViewById(R.id.button0);
        button0.setEnabled(enable);

        Button button1 = findViewById(R.id.button1);
        button1.setEnabled(enable);

        Button button2 = findViewById(R.id.button2);
        button2.setEnabled(enable);

        Button button3 = findViewById(R.id.button3);
        button3.setEnabled(enable);

        Button button4 = findViewById(R.id.button4);
        button4.setEnabled(enable);

        Button button5 = findViewById(R.id.button5);
        button5.setEnabled(enable);

        Button button6 = findViewById(R.id.button6);
        button6.setEnabled(enable);

        Button button7 = findViewById(R.id.button7);
        button7.setEnabled(enable);

        Button button8 = findViewById(R.id.button8);
        button8.setEnabled(enable);

        Button button9 = findViewById(R.id.button9);
        button9.setEnabled(enable);
    }

    private void showResponseMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}