package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class LoginActivity extends AppCompatActivity
 * @author Raihan Wijaya
 * @description Disini merupakan activity yang digunakan untuk mengatur login, disini akan menggunakan Class LoginRequest
 */

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

    /**
     * @description
     * Bagian atas adalah inisiasi variabel, mengassign XML ke frontend
     * Button login digunakan untuk membuat request baru sehingga dapat di passing ke backend
     * TextView register dapat di pencet dan digunakan untuk pindah ke RegisterActivity
     */

    private static final Gson gson = new Gson();
    public static Account loggedAccount;

    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText textemail = findViewById(R.id.emailLogin);
        EditText textpassword = findViewById(R.id.passwordLogin);
        Button buttonlogin = findViewById(R.id.buttonLogin);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            loggedAccount = gson.fromJson(object.toString(), Account.class);
                            startActivity(intent);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(textemail.getText().toString(), textpassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });

        TextView register = findViewById(R.id.textToRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}