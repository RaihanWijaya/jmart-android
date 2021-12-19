package com.MuhammadRaihanWijayaJmartMR.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Payment;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Store;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.AcceptRequest;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.CancelRequest;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.SubmitRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PaymentDetailActivity extends AppCompatActivity {
    public Payment clickedPayment;
    private String tempName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);

        Button confirm = findViewById(R.id.confirmButton);
        Button submit = findViewById(R.id.buttonReceipt);
        Button cancel = findViewById(R.id.cancelButton);

        TextView buyerId = findViewById(R.id.BuyerIdPaymentDetail);
        TextView productId = findViewById(R.id.ProductIdPaymentDetail);
        TextView productCount = findViewById(R.id.ProductCountPaymentDetail);
        TextView textReceipt = findViewById(R.id.TextReceipt);

        EditText inputReceipt = findViewById(R.id.inputReceipt);

        buyerId.setText(String.valueOf(StoreHistory.storeClickedPayment.getBuyerId()));
        productId.setText(String.valueOf(StoreHistory.storeClickedPayment.getProductId()));
        productCount.setText(String.valueOf(StoreHistory.storeClickedPayment.getProductCount()));

        submit.setVisibility(View.GONE);
        textReceipt.setVisibility(View.GONE);
        inputReceipt.setVisibility(View.GONE);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(PaymentDetailActivity.this, "Accepted!", Toast.LENGTH_SHORT).show();
                            confirm.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            textReceipt.setVisibility(View.VISIBLE);
                            inputReceipt.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(PaymentDetailActivity.this, "Accept Process error!", Toast.LENGTH_SHORT).show();
                            confirm.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            textReceipt.setVisibility(View.VISIBLE);
                            inputReceipt.setVisibility(View.VISIBLE);
                        }
                    }
                };
                AcceptRequest acceptRequest = new AcceptRequest(StoreHistory.storeClickedPayment.id, listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentDetailActivity.this);
                requestQueue.add(acceptRequest);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(PaymentDetailActivity.this, "Accepted!", Toast.LENGTH_SHORT).show();
                            confirm.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            textReceipt.setVisibility(View.VISIBLE);
                            inputReceipt.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(PaymentDetailActivity.this, "Accept Process error!", Toast.LENGTH_SHORT).show();
                            confirm.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            textReceipt.setVisibility(View.VISIBLE);
                            inputReceipt.setVisibility(View.VISIBLE);
                        }
                        finish();
                        Intent intent = new Intent(PaymentDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                SubmitRequest submitRequest = new SubmitRequest(StoreHistory.storeClickedPayment.id, inputReceipt.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentDetailActivity.this);
                requestQueue.add(submitRequest);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(PaymentDetailActivity.this, "Canceled!", Toast.LENGTH_SHORT).show();
                            confirm.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            textReceipt.setVisibility(View.VISIBLE);
                            inputReceipt.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(PaymentDetailActivity.this, "Cancel Process error!", Toast.LENGTH_SHORT).show();
                            confirm.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            submit.setVisibility(View.VISIBLE);
                            textReceipt.setVisibility(View.VISIBLE);
                            inputReceipt.setVisibility(View.VISIBLE);
                        }
                        finish();
                        Intent intent = new Intent(PaymentDetailActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                };
                CancelRequest cancelRequest = new CancelRequest(StoreHistory.storeClickedPayment.id, listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentDetailActivity.this);
                requestQueue.add(cancelRequest);
            }
        });
    }
}