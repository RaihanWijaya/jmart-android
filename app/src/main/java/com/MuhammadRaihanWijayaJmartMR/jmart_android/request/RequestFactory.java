package com.MuhammadRaihanWijayaJmartMR.jmart_android.request;

/**
 * The class RequestFactory
 * @author Raihan Wijaya
 * @description Untuk mendapatkan informasi ke backend
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class RequestFactory {
    private static final String URL_FORMAT_ID = "http://10.0.2.2:9000/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:9000/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_PRODUCT = "http://10.0.2.2:9000/product/getFiltered?page=%s&pageSize=%s&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";

    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    public static StringRequest getProduct
            (
                    int page,
                    int pageSize,
                    String search,
                    String minPrice,
                    String maxPrice,
                    String category,
                    String conditionUsed,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PRODUCT,page,pageSize,search,minPrice,maxPrice,category,conditionUsed);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}