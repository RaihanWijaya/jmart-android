package com.MuhammadRaihanWijayaJmartMR.jmart_android;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_about_me);

        TextView name = (TextView) findViewById(R.id.outputNameAbout);
        name.setText("" + LoginActivity.getLoggedAccount().name);
        TextView email = (TextView) findViewById(R.id.outputEmailAbout);
        email.setText("" + LoginActivity.getLoggedAccount().email);
        TextView balance = (TextView) findViewById(R.id.outputBalanceAbout);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        EditText topUpInput = (EditText) findViewById(R.id.topupInputAbout);

        Button buttonTopUp = (Button) findViewById(R.id.topupButtonAbout);
        Button registerButton = (Button) findViewById(R.id.registerStoreButtonAbout);
        Button registerStore = (Button) findViewById(R.id.buttonRegisterStore);
        Button cancelRegister = (Button) findViewById(R.id.cancelRegisterStore);

        CardView cardRegister = (CardView) findViewById(R.id.cardRegisterAbout);
        CardView cardStore = (CardView) findViewById(R.id.cardStoreAbout);

        registerButton.setVisibility(View.GONE);
        cardRegister.setVisibility(View.GONE);
        cardStore.setVisibility(View.GONE);

        if (LoginActivity.getLoggedAccount().store == null){
            registerButton.setVisibility(View.VISIBLE);
        }
        else if (LoginActivity.getLoggedAccount().store != null){
            TextView dataName = (TextView) findViewById(R.id.dataNameTextAbout);
            dataName.setText("" + LoginActivity.getLoggedAccount().store.name);
            TextView dataAddress = (TextView) findViewById(R.id.dataAddressTextAbout);
            dataAddress.setText("" + LoginActivity.getLoggedAccount().store.address);
            TextView dataPhone = (TextView) findViewById(R.id.dataPhoneTextAbout);
            dataPhone.setText("" + LoginActivity.getLoggedAccount().store.phoneNumber);
            cardStore.setVisibility(View.VISIBLE);
        }
        else{
            registerButton.setVisibility(View.VISIBLE);
        }

        registerButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setVisibility(View.GONE);
                cardRegister.setVisibility(View.VISIBLE);
                cancelRegister.setOnClickListener (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardRegister.setVisibility(View.GONE);
                        registerButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}