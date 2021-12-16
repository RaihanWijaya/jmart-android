package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class PaymentActivity extends AppCompatActivity
 * @author Raihan Wijaya
 * @description
 * Disini activity yang digunakan untuk menampilkan
 * history Payment dan status dari barang yang dibeli
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Payment;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Product;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.RequestFactory;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PersonalHistory extends AppCompatActivity {

    /**
     * @description
     * Disini variabel di inisiasi dan onCreate, akan ditampilkan
     * list dari history ke ListPersonalHistory
     */

    public static ArrayList<Payment> paymentList = new ArrayList<>();
    public static ArrayList<Product> products = new ArrayList<>();
    private static final Gson gson = new Gson();
    static int pageSize = 20;
    static int page = 0;
    private static int sentinel = 0;
    public static Payment paymentClicked = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_history);

        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PersonalHistory.this,"Menu!", Toast.LENGTH_SHORT).show();
                products.clear();
                sentinel = 0;
                Intent intent = new Intent(PersonalHistory.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button refresh = findViewById(R.id.buttonRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sentinel == 0){
                    Toast.makeText(PersonalHistory.this,"Refresh!", Toast.LENGTH_SHORT).show();
                    PersonalHistory.this.finish();
                    PersonalHistory.this.overridePendingTransition(0,0);
                    PersonalHistory.this.startActivity(PersonalHistory.this.getIntent());
                    sentinel += 1;
                }
                else if(sentinel > 0){
                    Toast.makeText(PersonalHistory.this,"Already Refreshed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray object = new JSONArray(response);
                    if (object != null) {
                        paymentList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Payment>>() {
                        }.getType());
                        convertPayment();
                        ArrayAdapter<Product> listViewAdapter = new ArrayAdapter<Product>(
                                PersonalHistory.this,
                                android.R.layout.simple_list_item_1,
                                products
                        );
                        ListView lv = (ListView) findViewById(R.id.ListPersonalHistory);

                        lv.setAdapter(listViewAdapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                paymentClicked = (Payment) lv.getItemAtPosition(i);
                                Intent intent = new Intent(PersonalHistory.this, PaymentDetailActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(PersonalHistory.this);
        requestQueue.add(RequestFactory.getPage("payment", page, pageSize, listener, null));
    }

    private void convertPayment() {
        for (Payment each : paymentList) {
            if(each.buyerId == LoginActivity.loggedAccount.id){
                Response.Listener<String> listenerConvert = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject objectConvert = new JSONObject(response);
                            if (objectConvert != null) {
                                Product temp = gson.fromJson(objectConvert.toString(),Product.class);
                                products.add(temp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(PersonalHistory.this);
                requestQueue.add(RequestFactory.getById("product", each.productId, listenerConvert, null));
            }
        }
    }
}