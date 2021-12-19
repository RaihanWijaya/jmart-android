package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

import androidx.annotation.Nullable;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.LoginActivity;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.ProductFragment;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * The class PaymentRequest extends StringRequest
 * @author Raihan Wijaya
 * @description Untuk membuat request ke backend dalam percobaan pembayaran
 */

public class PaymentRequest extends StringRequest {

    /**
     * @description
     * List dari parameter yang ada di bawah akan di passing menuju backend
     * sehingga produk yang dipilih user dapat diproses untuk Payment
     */

    public static final String URL = "http://10.0.2.2:9000/payment/create";
    public final Map<String,String> params;

    /**
     * @param productCount
     * @param shipmentAddress
     * @param listener
     * @param errorListener
     */
    public PaymentRequest
            (
                    String productCount,
                    String shipmentAddress,
                    Response.Listener<String> listener,
                    @Nullable Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId",String.valueOf(LoginActivity.loggedAccount.id));
        params.put("productId",String.valueOf(ProductFragment.productClicked.id));
        params.put("productCount",productCount);
        params.put("shipmentAddress",shipmentAddress);
        params.put("shipmentPlan",String.valueOf(ProductFragment.productClicked.shipmentPlans));
    }

    public Map<String,String> getParams(){
        return params;
    }
}
