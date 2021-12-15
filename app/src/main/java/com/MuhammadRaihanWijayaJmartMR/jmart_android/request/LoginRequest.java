package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

/**
 * The class LoginRequest extends StringRequest
 * @author Raihan Wijaya
 * @description Untuk membuat request ke backend ketika user mencoba untuk melakukan login
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String URL =  "http://10.0.2.2:9000/account/login";
    private final Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    public Map<String, String> getParams(){
        return params;
    }
}
