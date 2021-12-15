package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

/**
 * The class RegisterStoreRequest extends StringRequest
 * @author Raihan Wijaya
 * @description Untuk membuat request ke backend untuk melakukan registrasi toko apabila user ingin membuat toko
 */

import com.MuhammadRaihanWijayaJmartMR.jmart_android.LoginActivity;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterStoreRequest extends StringRequest {
    private static final String URL =  "http://10.0.2.2:9000/account/" + LoginActivity.getLoggedAccount().id + "/registerStore";
    private final Map<String , String> params;

    public RegisterStoreRequest
            (
                    int id,
                    String name,
                    String address,
                    String phoneNumber,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);
        Integer  i = id;
        params = new HashMap<>();
        params.put("id", i.toString());
        params.put("name", name);
        params.put("address", address);
        params.put("phoneNumber", phoneNumber);
    }

    public Map<String , String> getParams() {
        return params;
    }
}