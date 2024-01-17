package com.example.trialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class edit_or_view_db extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_view_db);

        Button edit_db = (Button) findViewById(R.id.edit_database);
        Button view_db = (Button) findViewById(R.id.view_database);

        edit_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), edit_db_login.class));
            }
        });

        view_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), View_Database.class));
            }
        });
    }
}