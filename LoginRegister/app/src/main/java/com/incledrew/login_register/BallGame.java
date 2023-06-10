package com.incledrew.login_register;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BallGame extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private Paint paint;
    private int ballX, ballY;
    private int ballSpeedX, ballSpeedY;
    private ImageView exitButton;
    private BallAnimationThread animationThread; // Declare the variable here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_game);

        exitButton = findViewById(R.id.exitButtonGame);

        surfaceView = findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setColor(Color.RED);

        ballX = 100; // initial position of the ball
        ballY = 100;
        ballSpeedX = 10; // initial speed of the ball
        ballSpeedY = 10;

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BallGame.this, SubMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Start the animation thread
        animationThread = new BallAnimationThread(); // Initialize the variable here
        animationThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Not needed for this example
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Clean up resources
        boolean retry = true;
        while (retry) {
            try {
                animationThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class BallAnimationThread extends Thread {
        private boolean isRunning = true;

        @Override
        public void run() {
            while (isRunning) {
                // Update ball position
                ballX += ballSpeedX;


                // Reverse direction if the ball hits the screen edges
                if (ballX <= 0 || ballX >= surfaceView.getWidth()) {
                    ballX = 100;
                    ballY += 100;
                }
                if (ballY <= 0 || ballY >= surfaceView.getHeight()) {
                    ballY = 100;
                }

                // Draw the ball on the canvas
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    canvas.drawColor(Color.WHITE); // clear the canvas
                    canvas.drawCircle(ballX, ballY, 50, paint);
                    holder.unlockCanvasAndPost(canvas);
                }

                try {
                    Thread.sleep(15); // Adjust this value to control the animation speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}