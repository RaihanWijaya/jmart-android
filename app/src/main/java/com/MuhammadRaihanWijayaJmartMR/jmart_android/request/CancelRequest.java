package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

import androidx.annotation.Nullable;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.StoreHistory;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CancelRequest extends StringRequest {
    public static final String URL = "http://10.0.2.2:9000/payment/" + StoreHistory.storeClickedPayment.id + "/cancel";
    public final Map<String,String> params;

    public CancelRequest
            (
                    int id,
                    Response.Listener<String> listener,
                    @Nullable Response.ErrorListener errorListener
            )
    {
        super(Method.POST, URL, listener, errorListener);

        params = new HashMap<>();
        params.put("id", String.valueOf(id));
    }
    public Map<String, String> getParams(){
        return params;
    }
}