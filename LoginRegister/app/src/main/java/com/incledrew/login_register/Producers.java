package com.incledrew.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Producers extends AppCompatActivity {

    private ImageView backButtonProducers;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producers);


        videoView = findViewById(R.id.videoView);
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.mertvideo;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);

        videoView.start();


    }
}