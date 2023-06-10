package com.incledrew.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTurkish extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView hesabinYok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login_turkish);

        hesabinYok = findViewById(R.id.hesabinYok);

        hesabinYok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginTurkish.this, RegisterTurkish.class);
                startActivity(intent);
            }
        });

        ImageView england = findViewById(R.id.englandflag);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView forgotPassword = (TextView) findViewById(R.id.forgotpassword);

        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.loginbtn);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginTurkish.this, Login.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String username1, password1;


                username1 = (username.getText().toString().trim());
                password1 = (password.getText().toString().trim());


                if (TextUtils.isEmpty(username1)) {
                    Toast.makeText(LoginTurkish.this, "Enter an username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(LoginTurkish.this, "Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(username1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginTurkish.this, "Giriş başarılı.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginTurkish.this, MainMenuTurkish.class);
                                    startActivity(intent);
                                    finish();

                                } else {

                                    Toast.makeText(LoginTurkish.this, "Doğrulama başarısız.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }


        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginTurkish.this, "Şifre yenileme sayfası gösterilecek", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginTurkish.this, ForgotPassword.class);
                startActivity(intent);
            }
        });




    }


}