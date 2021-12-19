package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

/**
 * The class CreateProductRequest extends StringRequest
 * @author Raihan Wijaya
 * @description Untuk membuat request ke backend dalam membuat produk baru
 */

import androidx.annotation.Nullable;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateProductRequest extends StringRequest{

    /**
     * @description
     * List dari parameter yang ada di bawah akan di passing menuju backend
     * sehingga user yang memiliki store dapat menambahkan produknya
     */

    public static final String URL = "http://10.0.2.2:9000/product/create";
    public final Map<String,String> params;

    /**
     * @param name
     * @param weight
     * @param conditionUsed
     * @param price
     * @param discount
     * @param category
     * @param shipmentPlans
     * @param listener
     * @param errorListener
     */
    public CreateProductRequest
            (
                    String name,
                    String weight,
                    String conditionUsed,
                    String price,
                    String discount,
                    String category,
                    String shipmentPlans,
                    Response.Listener<String> listener,
                    @Nullable Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        Integer i = LoginActivity.loggedAccount.id;
        params.put("accountId", i.toString());
        params.put("name",name);
        params.put("weight",weight);
        params.put("conditionUsed",conditionUsed);
        params.put("price", price);
        params.put("discount", discount);
        params.put("category", category);
        params.put("shipmentPlans", shipmentPlans);
    }
    public Map<String,String> getParams(){return params;}
}