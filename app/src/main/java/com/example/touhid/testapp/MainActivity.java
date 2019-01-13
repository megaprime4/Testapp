package com.example.touhid.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check for authentic users
        //if user is in the database
        //then auto login
        //otherwise go to StartPageActivity.class (sign up)
    }
}
