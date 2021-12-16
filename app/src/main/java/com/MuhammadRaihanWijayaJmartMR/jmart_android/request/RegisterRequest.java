package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

/**
 * The class RegisterRequest extends StringRequest
 * @author Raihan Wijaya
 * @description Untuk membuat request ke backend untuk melakukan registrasi akun baru
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    /**
     * @description
     * List dari parameter yang ada di bawah akan di passing menuju backend
     * disini variable di passing sehingga user dapat membuat akun baru
     */

    private static final String URL =  "http://10.0.2.2:9000/account/register";
    private final Map<String , String> params;

    public RegisterRequest
            (
                    String name,
                    String email,
                    String password,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    public Map<String , String> getParams() {
        return params;
    }
}