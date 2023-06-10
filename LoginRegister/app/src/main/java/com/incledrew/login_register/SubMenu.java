package com.incledrew.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SubMenu extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private String userId;
    private TextView pointsText;
    private ValueEventListener pointsListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        CardView spelling = findViewById(R.id.spelling);
        CardView rememberDigits = findViewById(R.id.rememberDigits);








        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenu.this, MainMenu.class);
                startActivity(intent);
            }
        });

        ImageView nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenu.this, SubMenu2.class);
                startActivity(intent);
            }
        });
        CardView learningActivities = findViewById(R.id.seasons);
        learningActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenu.this, SeasonsLearning.class);
                startActivity(intent);
            }
        });

        CardView monthsLearning = findViewById(R.id.months);
        monthsLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, MonthsLearning.class);
                startActivity(intent);

            }
        });





        CardView daysLearning = findViewById(R.id.daysLearning);
        daysLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, Days.class);
                startActivity(intent);

            }
        });

        CardView daysGame = findViewById(R.id.daysGame);
        daysGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, DaysGame.class);
                startActivity(intent);

            }
        });

        spelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, Spelling.class);
                startActivity(intent);
            }
        });

        rememberDigits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, RememberDigits.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        // Get the current user's ID
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        } else {
            // Handle the case when the current user is null
            // For example, display an error message or redirect to a login screen
        }

        pointsText = findViewById(R.id.pointsText);

        // Create a ValueEventListener to retrieve and display the user's points
        pointsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve the points value from the snapshot
                    int points = dataSnapshot.child("points").getValue(Integer.class);
                    pointsText.setText("        " + points );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur while retrieving the data
            }
        };

        // Attach the listener to the appropriate database reference
        databaseReference.child(userId).addValueEventListener(pointsListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Remove the ValueEventListener to avoid memory leaks
        if (databaseReference != null && userId != null && pointsListener != null) {
            databaseReference.child(userId).removeEventListener(pointsListener);
        }
    }

    private void redirectToLogin() {
        // Handle the case when the current user is null
        // For example, display an error message or redirect to a login screen
        Intent intent = new Intent(SubMenu.this, Login.class);
        startActivity(intent);
        finish();
    }


    }
