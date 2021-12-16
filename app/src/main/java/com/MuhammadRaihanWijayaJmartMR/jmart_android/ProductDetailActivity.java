package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class ProductDetailActivity extends AppCompatActivity
 * @author Raihan Wijaya
 * @description Disini activity yang digunakan untuk menampilkan detail produk dan bisa menghubungkan ke PaymentActivities
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    /**
     * @description
     * Disini variabel di inisiasi dan seperti sebelumnya,
     * juga dilakukan assign id dari XML ke frontend
     */

    Product productClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView productName = findViewById(R.id.productName);
        TextView productWeight = findViewById(R.id.weightProduct);
        TextView productCondition = findViewById(R.id.conditionProduct);
        TextView productPrice = findViewById(R.id.priceProduct);
        TextView productDiscount = findViewById(R.id.discountProduct);
        TextView productCategory = findViewById(R.id.categoryProduct);
        TextView productShipmentPlan = findViewById(R.id.shipmentPlanProduct);

        Button buyButton = findViewById(R.id.buyButton);

        /**
         * @description
         * Disini variabel di sambungkan dengan ProductFragment.productClicked,
         * sehingga ketika memilih produk, dapat ditampilkan infonya
         * Button buy digunakan untuk pindah ke PaymentActivities
         */

        productName.setText(String.valueOf(ProductFragment.productClicked.name));
        productWeight.setText(String.valueOf(ProductFragment.productClicked.weight));
        productCondition.setText(convertConditionUsed(ProductFragment.productClicked.conditionUsed));
        productPrice.setText(String.valueOf(ProductFragment.productClicked.price));
        productDiscount.setText(String.valueOf(ProductFragment.productClicked.discount));
        productCategory.setText(String.valueOf(ProductFragment.productClicked.category));
        productShipmentPlan.setText(convertShipmentPlans(ProductFragment.productClicked.shipmentPlans));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @description
     * convertShipmentPlans dan convertConditionUsed akan melakukan tugas sesuai dengan namanya
     */

    private String convertShipmentPlans(byte shipment){
        switch (shipment) {
            case 0:
                return "INSTANT";
            case 1:
                return "SAME DAY";
            case 2:
                return "NEXT DAY";
            case 3:
                return "REGULER";
            default:
                return "CARGO";
        }
    }

    private String convertConditionUsed(boolean conditionUsed){
        if (conditionUsed) {
            return "Used";
        }else{
            return "New";
        }
    }
}