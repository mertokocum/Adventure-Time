package com.incledrew.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class RegisterTurkish extends AppCompatActivity {

    FirebaseAuth mAuth;

    private ImageView backButtonRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_turkish);

        backButtonRegister = findViewById(R.id.backButtonRegister);

        backButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterTurkish.this, LoginTurkish.class);
                startActivity(intent);
            }
        });
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView confirmPassword = (TextView) findViewById(R.id.confirmPassword);
        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String username1, password1, confirmPassword1;


                username1 = (username.getText().toString().trim());
                password1 = (password.getText().toString().trim());
                confirmPassword1 = (confirmPassword.getText().toString().trim());

                if (TextUtils.isEmpty(username1)) {
                    Toast.makeText(RegisterTurkish.this, "Bir kullanıcı adı girin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(RegisterTurkish.this, "Bir parola girin", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(username1, password1)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful() && password1.equals(confirmPassword1)) {
                                    // Sign in success, update UI with the signed-in user's information
                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(RegisterTurkish.this, "Hesap oluşturuldu.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(RegisterTurkish.this, Login.class);
                                    startActivity(intent);

                                } else if(task.isSuccessful() && !password1.equals(confirmPassword1)){
                                    Toast.makeText(RegisterTurkish.this, "Parolalar uyuşmuyor,lütfen yeniden deneyin.",
                                            Toast.LENGTH_SHORT).show();
                                }else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterTurkish.this, "Kullanıcı girişi başarısız: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }



                            }
                        });




            }
        });


    }


}