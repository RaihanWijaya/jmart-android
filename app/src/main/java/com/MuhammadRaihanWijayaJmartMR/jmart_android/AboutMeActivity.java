package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class AboutMeActivity extends AppCompatActivity
 * @author Raihan Wijaya
 * @description konfigurassi kode dari front-end AboutMeActivity, disini user bisa top-up dan melakukan registrasi toko baru
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Store;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.RegisterStoreRequest;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.TopUpRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AboutMeActivity extends AppCompatActivity {

    /**
     * @description
     * Pada bagian atas ini terdapat inisiasi variabel dan assign dari UI ke frontend
     * disini nama, email, dan balance di assign ke milik user dan ditampilkan ke UI
     */

    private static final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        TextView name = findViewById(R.id.outputNameAbout);
        name.setText("" + LoginActivity.getLoggedAccount().name);
        TextView email = findViewById(R.id.outputEmailAbout);
        email.setText("" + LoginActivity.getLoggedAccount().email);
        TextView balance = findViewById(R.id.outputBalanceAbout);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        EditText topUpInput =  findViewById(R.id.topupInputAbout);
        EditText storeName =  findViewById(R.id.inputNameRegisterStore);
        EditText storeAddress =  findViewById(R.id.inputAddressRegisterStore);
        EditText storePhone =  findViewById(R.id.inputPhoneRegisterStore);

        Button buttonTopUp = findViewById(R.id.topupButtonAbout);
        Button registerButton = findViewById(R.id.registerStoreButtonAbout);
        Button registerStore = findViewById(R.id.buttonRegisterStore);
        Button cancelRegister = findViewById(R.id.cancelRegisterStore);
        Button buttonLogOut = findViewById(R.id.logOutButton);

        CardView cardRegister = findViewById(R.id.cardRegisterAbout);
        CardView cardStore = findViewById(R.id.cardStoreAbout);

        registerButton.setVisibility(View.GONE);
        cardRegister.setVisibility(View.GONE);
        cardStore.setVisibility(View.GONE);

        /**
         * @description
         * Pada bagian ini, program akan melakukan checking apakah akun user mempunyai store
         * jika tidak maka program akan menampilkan RegisterStoreButton, jika ada maka program
         * akan menampilkan info data dari store akun
         */

        if (LoginActivity.getLoggedAccount().store == null){
            registerButton.setVisibility(View.VISIBLE);
        }
        else {
            TextView dataName = findViewById(R.id.dataNameTextAbout);
            dataName.setText("" + LoginActivity.getLoggedAccount().store.name);
            TextView dataAddress = findViewById(R.id.dataAddressTextAbout);
            dataAddress.setText("" + LoginActivity.getLoggedAccount().store.address);
            TextView dataPhone = findViewById(R.id.dataPhoneTextAbout);
            dataPhone.setText("" + LoginActivity.getLoggedAccount().store.phoneNumber);
            cardStore.setVisibility(View.VISIBLE);
        }

        /**
         * @description
         * Pada bagian bawah ini, merupakan kumpulan button yang di setOnClickListener
         * dan setiap button mempunyai fungsi masing masing sesuai dengan namanya
         */

        buttonTopUp.setOnClickListener(new View.OnClickListener() {
            /**
             * @description
             * buttonTopUp digunakan untuk melakukan topUpRequest sehingga topUpInput
             * akan dipass ke topUpRequest dan akan di poss lagi ke backend
             */
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(AboutMeActivity.this, "Topup berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AboutMeActivity.this, "Topup error!", Toast.LENGTH_SHORT).show();
                        }
                        LoginActivity.loggedAccount.balance += Double.parseDouble(topUpInput.getText().toString());
                        finish();
                        startActivity(getIntent());
                    }
                };
                TopUpRequest topUpRequest = new TopUpRequest(LoginActivity.getLoggedAccount().id, Double.parseDouble(topUpInput.getText().toString()), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(topUpRequest);
            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            /**
             * @description
             * buttonTopUp digunakan untuk melakukan log out dari logged account
             * */
            @Override
            public void onClick(View v) {
                Intent logOut = new Intent(AboutMeActivity.this, LoginActivity.class);
                finish();
                startActivity(logOut);
            }
        });

        registerButton.setOnClickListener (new View.OnClickListener() {
            /**
             * @description
             * registerButton digunakan untuk menampilkan cardRegister dimana berisi
             * inputan untuk registrasi store
             */
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

        registerStore.setOnClickListener(new View.OnClickListener() {
            /**
             * @description
             * registerStore adalah button yang digunakan untuk melakukan registrasi dimana,
             * inputan akan di passing dari frontend ke backend
             */
            @Override
            public void onClick(View view) {
                if(!String.valueOf(storeName).isEmpty() && !String.valueOf(storeAddress).isEmpty() && !String.valueOf(storePhone).isEmpty()){
                    Response.Listener<String> listener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject object = new JSONObject(response);
                                LoginActivity.loggedAccount.store = gson.fromJson(object.toString(),Store.class);
                                System.out.println(LoginActivity.loggedAccount.store);
                                Toast.makeText(AboutMeActivity.this, "Create Store Success!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }catch (JSONException e){
                                Toast.makeText(AboutMeActivity.this, "Create Store Failed!", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterStoreRequest request = new RegisterStoreRequest(LoginActivity.getLoggedAccount().id,storeName.getText().toString(),storeAddress.getText().toString(),storePhone.getText().toString(),listener,null);
                    RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                    requestQueue.add(request);
                }
            }
        });

        Button history = findViewById(R.id.historyAbout);
        history.setOnClickListener(new View.OnClickListener() {
            /**
             * @description
             * history adalah button untuk pindah ke StoreInvoiceActivity
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutMeActivity.this, StoreHistory.class);
                startActivity(intent);
            }
        });
    }
}