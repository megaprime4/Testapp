package com.example.touhid.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView Name, ID, Email, Phone, BVC_reg, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check for authentic users
        //if user is in the database
        //then auto login
        //otherwise go to StartPageActivity.class (sign up)

        Name = (TextView) findViewById(R.id.mainName);
        ID = (TextView) findViewById(R.id.mainID);
        Email = (TextView) findViewById(R.id.mainEmail);
        Phone = (TextView) findViewById(R.id.mainPhone);
        BVC_reg = (TextView) findViewById(R.id.mainBVC_reg);
        Password = (TextView) findViewById(R.id.mainPassword);

        Bundle bundle = getIntent().getExtras();
        Name.setText(bundle.getString("name"));
        ID.setText(bundle.getString("id"));
        Email.setText(bundle.getString("email"));
        Phone.setText(bundle.getString("phone"));
        BVC_reg.setText(bundle.getString("bvc_reg"));
        Password.setText(bundle.getString("password"));
    }
}
