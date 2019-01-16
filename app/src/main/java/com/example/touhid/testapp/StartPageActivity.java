package com.example.touhid.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPageActivity extends AppCompatActivity {

    private Button SignInButton;
    private Button SignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        SignInButton = (Button) findViewById(R.id.SigninButton);
        SignUpButton = (Button) findViewById(R.id.SignUpButton);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to sign in page
                Intent loginIntent = new Intent(StartPageActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to sign up page
                Intent registrationIntent = new Intent(StartPageActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });
    }
}
