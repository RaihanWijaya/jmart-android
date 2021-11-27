package com.MuhammadRaihanWijayaJmartMR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Account;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.RegisterRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText nameRegister = findViewById(R.id.nameRegister);
        EditText emailRegister = findViewById(R.id.emailRegister);
        EditText passwordRegister = findViewById(R.id.passwordRegister);
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
                                Toast.makeText
                                        (
                                                RegisterActivity.this,
                                                "Terima Kasih Sudah Melakukan Registrasi!",
                                                Toast.LENGTH_SHORT
                                        );
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest
                        (
                                nameRegister.getText().toString(),
                                emailRegister.getText().toString(),
                                passwordRegister.getText().toString(),
                                listener,
                                null
                        );

                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }
}