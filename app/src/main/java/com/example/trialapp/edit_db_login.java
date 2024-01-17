package com.example.trialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class edit_db_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final EditText admin_username = (EditText) findViewById(R.id.admin_username);
        final EditText admin_password = (EditText) findViewById(R.id.admin_password);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_db_login);

        final Button admin_login = (Button) findViewById(R.id.admin_login);

        //final FirebaseAuth aAuth = FirebaseAuth.getInstance();

        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String admin_un = admin_username.getText().toString().trim();
                String admin_pw = admin_password.getText().toString().trim();
                int f = 0;

                if(TextUtils.isEmpty(admin_un)){
                    admin_username.setError("Username cannot be empty");
                    f = 1;
                }

                if(TextUtils.isEmpty(admin_pw)){
                    admin_password.setError("Password cannot be empty");
                    f = 1;
                }

                if(admin_un.equals("admin") && admin_pw.equals("admin")){
                    Toast.makeText(edit_db_login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Edit_Database.class));
                }
                else{
                    Toast.makeText(edit_db_login.this, "Error! Wrong username or password", Toast.LENGTH_SHORT).show();
                    admin_username.getText().clear();
                    admin_password.getText().clear();
                }

            }
        });
    }
}