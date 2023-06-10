package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SubMenu2 extends AppCompatActivity {

    private CardView directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu2);

        directions = findViewById(R.id.directions);

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu2.this, Directions.class);
                startActivity(intent);
            }
        });

        ImageView backButton = findViewById(R.id.backButton);
        CardView ballGame = findViewById(R.id.BallGame);

        ballGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu2.this, BallGame.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenu2.this, SubMenu.class);
                startActivity(intent);
            }
        });
    }
}