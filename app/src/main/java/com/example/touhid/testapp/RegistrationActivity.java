package com.example.touhid.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

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
                final String name = Name.getText().toString().trim();
                final String userID = UserID.getText().toString().trim();
                final String email = Email.getText().toString().trim();
                final String phone = Phone.getText().toString().trim();
                final String password = Password.getText().toString().trim();
                String passwordVer = PasswordVer.getText().toString().trim();
                final String bvcRegNumber = BVCRegNumber.getText().toString().trim();

                System.out.println("....................................."+name);
                System.out.println("....................................."+userID);
                System.out.println("....................................."+email);
                System.out.println("....................................."+phone);
                System.out.println("....................................."+password);
                System.out.println("....................................."+bvcRegNumber);

                //checking data
                if(name.equals("") || userID.equals("") || email.equals("") || phone.equals("") || password.equals("") || passwordVer.equals("") || bvcRegNumber.equals("")) {
                    Toast.makeText(RegistrationActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(passwordVer)) {
                    Toast.makeText(RegistrationActivity.this, "Password didn't match!", Toast.LENGTH_SHORT).show();
                } else if(Patterns.EMAIL_ADDRESS.matcher(email).matches() == false) {
                    Toast.makeText(RegistrationActivity.this, "E-mail address is not valid!", Toast.LENGTH_SHORT).show();
                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(!response.equals("")) {
                                        Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show(); ///printing php echo
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(error instanceof TimeoutError){
                                Toast.makeText(RegistrationActivity.this, "Timeout error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof NoConnectionError) {
                                Toast.makeText(RegistrationActivity.this, "No connection error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof AuthFailureError) {
                                Toast.makeText(RegistrationActivity.this, "Authentication failure error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof NetworkError) {
                                Toast.makeText(RegistrationActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof ServerError) {
                                Toast.makeText(RegistrationActivity.this, "Server error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof ParseError) {
                                Toast.makeText(RegistrationActivity.this, "JSON parse error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(Constants.KEY_NAME, name);
                            params.put(Constants.KEY_USER_ID, userID);
                            params.put(Constants.KEY_EMAIL, email);
                            params.put(Constants.KEY_PHONE, phone);
                            params.put(Constants.KEY_BVC_REG, bvcRegNumber);
                            params.put(Constants.KEY_PASSWORD, password);
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("User-Agent", "Testapp");  ////security purpose
                            return headers;
                        }
                    };

                    MySingleton.getInstance(RegistrationActivity.this).addToRequestQueue(stringRequest);

                }
            }
        });
    }
}
