package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class RegisterActivity extends AppCompatActivity
 * @author Raihan Wijaya
 * @description Disini merupakan activity untuk tampilan register dan menghubungkan ke RegisterRequest
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.RegisterRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText textName = findViewById(R.id.nameRegister);
        EditText textEmail = findViewById(R.id.emailRegister);
        EditText textPassword = findViewById(R.id.passwordRegister);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(textName.getText().toString(), textEmail.getText().toString(), textPassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });

    }
}