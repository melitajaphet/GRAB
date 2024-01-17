package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText email = (EditText) findViewById(R.id.EmailText1);
        final EditText password1 = (EditText) findViewById(R.id.PasswordText1);

        Button login  = (Button) findViewById(R.id.Login1);
        TextView signuplink = (TextView) findViewById(R.id.signuplink);

        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String e = email.getText().toString().trim();
                final String pw1 = password1.getText().toString().trim();
                int f = 0;

                if(TextUtils.isEmpty(e)){
                    email.setError("Email is a compulsory field");
                    f = 1;
                }
                if(TextUtils.isEmpty(pw1)){
                    password1.setError("Password is a compulsory field");
                    f = 1;
                }

                mAuth.signInWithEmailAndPassword(e, pw1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity2.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            openLogin();
                        }
                        else {
                            Toast.makeText(MainActivity2.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            email.getText().clear();
                            password1.getText().clear();
                        }

                    }
                });
            }
        });

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });
    }
    public void openLogin(){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void openSignup(){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
}