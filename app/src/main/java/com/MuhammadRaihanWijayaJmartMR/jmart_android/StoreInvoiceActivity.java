package com.MuhammadRaihanWijayaJmartMR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class StoreInvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_history);
        ListView storeHistory = findViewById(R.id.ListPersonalHistory);
    }
}