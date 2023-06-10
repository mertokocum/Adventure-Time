package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SubMenuTurkish extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu_turkish);


        ImageView backButton1 = findViewById(R.id.backButton1);


        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenuTurkish.this, MainMenuTurkish.class);
                startActivity(intent);
            }
        });


        ImageView nextButton = findViewById(R.id.nextButton1);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubMenuTurkish.this, SubMenuTurkish2.class);
                startActivity(intent);
            }
        });



    }
}