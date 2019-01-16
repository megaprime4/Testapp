package com.example.touhid.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText UserID, Password;
    Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserID = (EditText) findViewById(R.id.LoginPageIDField);
        Password = (EditText) findViewById(R.id.LoginPagePasswordField);
        SignInButton = (Button) findViewById(R.id.LoginPageSignInButton);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userID = UserID.getText().toString().trim();
                final String password = Password.getText().toString().trim();


                System.out.println("....................................."+userID);
                System.out.println("....................................."+password);

                //checking data input
                if(userID.equals("") & password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please type your ID/Email and password!", Toast.LENGTH_SHORT).show();
                } else if(userID.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please type your ID or email!", Toast.LENGTH_SHORT).show();
                } else if(password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please type your password!", Toast.LENGTH_SHORT).show();
                } else {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.contains("Please check your id & password")) {
                                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                    } else if (response.contains("Missing required field")) {
                                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                    } else if (response.contains("Improper request method!")) {
                                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                    } else if (response.contains("Invalid platform!")) {
                                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                    }

                                    //server data  retrieve code below...

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("name", jsonObject.getString("name"));
                                        bundle.putString("id", jsonObject.getString("id"));
                                        bundle.putString("email", jsonObject.getString("email"));
                                        bundle.putString("phone", jsonObject.getString("phone"));
                                        bundle.putString("bvc_reg", jsonObject.getString("bvc_reg"));
                                        bundle.putString("password", jsonObject.getString("password"));
                                        mainIntent.putExtras(bundle);
                                        startActivity(mainIntent);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        System.out.println("-----------------------string response error occured ------------!!!");
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(error instanceof TimeoutError){
                                Toast.makeText(LoginActivity.this, "Timeout error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof NoConnectionError) {
                                Toast.makeText(LoginActivity.this, "No connection error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof AuthFailureError) {
                                Toast.makeText(LoginActivity.this, "Authentication failure error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof NetworkError) {
                                Toast.makeText(LoginActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof ServerError) {
                                Toast.makeText(LoginActivity.this, "Server error!", Toast.LENGTH_SHORT).show();
                            } else if(error instanceof ParseError) {
                                Toast.makeText(LoginActivity.this, "JSON parse error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(Constants.KEY_USER_ID, userID);
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

                    MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
                }
            }
        });

    }
}
