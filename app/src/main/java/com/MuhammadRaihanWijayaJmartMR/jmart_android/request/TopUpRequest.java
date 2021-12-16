package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

/**
 * The class TopUpRequest extends StringRequest
 * @author Raihan Wijaya
 * @description Untuk membuat request ke backend dalam melakukan top-up request
 */

import com.MuhammadRaihanWijayaJmartMR.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TopUpRequest extends StringRequest {

    /**
     * @description
     * List dari parameter yang ada di bawah akan di passing menuju backend
     * agar user dapat melakukan topUp ke balance akunnya
     */

    private static final String URL =  "http://10.0.2.2:9000/account/" + LoginActivity.getLoggedAccount().id + "/topUp";
    private final Map<String , String> params;

    public TopUpRequest
            (
                    int id,
                    double balance,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);

        params = new HashMap<>();
        params.put("id", Integer.toString(id));
        params.put("balance", Double.toString(balance));
    }

    public Map<String , String> getParams() {
        return params;
    }
}