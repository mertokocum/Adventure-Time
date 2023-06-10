package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Language extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        CardView turkey = findViewById(R.id.turkey);
        CardView england = findViewById(R.id.england);

        turkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Language.this, MainMenuTurkish.class);
                startActivity(intent);
            }
        });


        england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Language.this, MainMenu.class);
                startActivity(intent);
            }
        });


    }
}