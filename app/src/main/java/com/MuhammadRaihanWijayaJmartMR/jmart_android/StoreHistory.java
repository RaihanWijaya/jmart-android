package com.MuhammadRaihanWijayaJmartMR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class StoreHistory extends AppCompatActivity {
    public static ArrayList<Payment> storePaymentList = new ArrayList<>();
    public static Payment storeClickedPayment = null;
    private static final Gson gson = new Gson();
    static int pageSize = 20;
    static int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_history);
        ListView storeHistory = findViewById(R.id.ListPersonalHistory);

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray object = new JSONArray(response);
                    if (object != null) {
                        storePaymentList = gson.fromJson(object.toString(), new TypeToken<ArrayList<Payment>>() {}.getType());
                        ArrayAdapter<Payment> listViewAdapter = new ArrayAdapter<Payment>(
                                StoreHistory.this,
                                android.R.layout.simple_list_item_1,
                                storePaymentList
                        );
                        ListView lv = (ListView) findViewById(R.id.ListStoreHistory);
                        lv.setAdapter(listViewAdapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                storeClickedPayment = (Payment) lv.getItemAtPosition(i);
                                Intent intent = new Intent(StoreHistory.this, PaymentDetailActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(StoreHistory.this);
        requestQueue.add(RequestFactory.getPage("payment", page, pageSize, listener, null));
    }
}