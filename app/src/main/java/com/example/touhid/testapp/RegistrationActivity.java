package com.example.touhid.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Name, UserID, Email, Phone, Password, PasswordVer, BVCRegNumber;
    private Button SignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Name = (EditText) findViewById(R.id.reg_name);
        UserID = (EditText) findViewById(R.id.reg_id);
        Email = (EditText) findViewById(R.id.reg_email);
        Phone = (EditText) findViewById(R.id.reg_phone);
        Password = (EditText) findViewById(R.id.reg_pass1);
        PasswordVer = (EditText) findViewById(R.id.reg_pass2);
        BVCRegNumber = (EditText) findViewById(R.id.bvc_reg_num);
        SignUpButton = (Button) findViewById(R.id.RegSignUpButton);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //reading all the data form text boxes...
                String name = Name.getText().toString().trim();
                String userID = UserID.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String phone = Phone.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String passwordVer = PasswordVer.getText().toString().trim();
                String bvcRegNumber = BVCRegNumber.getText().toString().trim();

                //checking data
                if(name.equals("") || userID.equals("") || email.equals("") || phone.equals("") || password.equals("") || passwordVer.equals("") || bvcRegNumber.equals("")) {
                    Toast.makeText(RegistrationActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(passwordVer)) {
                    Toast.makeText(RegistrationActivity.this, "Password didn't match!", Toast.LENGTH_SHORT).show();
                } else if(Patterns.EMAIL_ADDRESS.matcher(email).matches() == false) {
                    Toast.makeText(RegistrationActivity.this, "E-mail address is not valid!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "All good!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
