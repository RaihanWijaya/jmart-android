package com.MuhammadRaihanWijayaJmartMR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Account;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.LoginRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    private static Account loggedAccount;

    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailLogin = findViewById(R.id.emailLogin);
        EditText passwordLogin = findViewById(R.id.passwordLogin);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView textToRegister = findViewById(R.id.textToRegister);

        textToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText
                                    (
                                            LoginActivity.this,
                                            "Selamat Datang ke Aplikasi!",
                                            Toast.LENGTH_SHORT
                                    );
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            loggedAccount = gson.fromJson(object.toString(), Account.class);
                            startActivity(intent);
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest
                        (
                                emailLogin.getText().toString(),
                                passwordLogin.getText().toString(),
                                listener,
                                null
                        );

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });
    }
}
