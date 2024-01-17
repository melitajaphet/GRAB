package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final EditText orgname = (EditText) findViewById(R.id.orgNameText);
        final EditText email = (EditText) findViewById(R.id.EmailText2);
        final EditText contact = (EditText) findViewById(R.id.ContactText);
        final EditText password2 = (EditText) findViewById(R.id.PasswordText2);
        final EditText confirmpassword2 = (EditText) findViewById(R.id.confirmPasswordText2);

        //final FirebaseDatabase root = FirebaseDatabase.getInstance();
        //DatabaseReference root;

        Button signup = (Button) findViewById(R.id.Signup1);
        TextView loginlink = (TextView) findViewById(R.id.loginlink);

        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference user = FirebaseDatabase.getInstance().getReference("User");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //root = FirebaseDatabase.getInstance();
                final String orgn = orgname.getText().toString().trim();
                final String e = email.getText().toString().trim();
                final String c = contact.getText().toString().trim();
                final String pw2 = password2.getText().toString().trim();
                String cpw2 = confirmpassword2.getText().toString().trim();
                int f = 0;

                if(TextUtils.isEmpty(orgn)){
                    orgname.setError("Organization Name is a compulsory field");
                    f = 1;
                }
                if(TextUtils.isEmpty(e)){
                    email.setError("Email is a compulsory field");
                    f = 1;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    email.setError("Invalid email format");
                    f = 1;
                }
                if(TextUtils.isEmpty(c)){
                    contact.setError("Contact is a compulsory field");
                    f = 1;
                }
                if(c.length() != 10 || !TextUtils.isDigitsOnly(c)){
                    contact.setError("Invalid contact number");
                    f = 1;
                }
                if(TextUtils.isEmpty(pw2)){
                    password2.setError("Password is a compulsory field");
                    f = 1;
                }
                if(pw2.length() < 8){
                    password2.setError("Password should be minimum 8 characters in length");
                    f = 1;
                }
                if(TextUtils.isEmpty(cpw2)){
                    confirmpassword2.setError("Confirm Password is a compulsory field");
                    f = 1;
                }
                if(!cpw2.equals(pw2)){
                    confirmpassword2.setError("Passwords don't match");
                    f = 1;
                }

                if(f == 1){
                    return;
                }

                mAuth.createUserWithEmailAndPassword(e, pw2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                       @Override
                                                                                       public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                           if (task.isSuccessful()) {

                                                                                               String userid = mAuth.getCurrentUser().getUid();
                                                                                               String Uid = user.push().getKey();
                                                                                               startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                                                                                               Map<String, Object> u = new HashMap<>();
                                                                                               u.put("UID", userid);
                                                                                               u.put("Organization", orgn);
                                                                                               u.put("Email", e);
                                                                                               u.put("Contact", c);
                                                                                               u.put("Password", pw2);
                                                                                               user.child(Uid).setValue(u);
                                                                                               Toast.makeText(MainActivity3.this, "User Created", Toast.LENGTH_SHORT).show();
                                                                                           }
                                                                                           else {
                                                                                               Toast.makeText(MainActivity3.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                                           }

                                                                                       }
                                                                                   });
                openSignup();
            }
        });

        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    public void openSignup(){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void openLogin(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}