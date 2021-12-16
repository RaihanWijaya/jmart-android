package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class FilterFragment extends Fragment
 * @author Raihan Wijaya
 * @description Disini merupakan salah satu fragment yang digunakan untuk memfilter produk dan ditampilkan di bagian bawah layar
 */

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Product;
import com.MuhammadRaihanWijayaJmartMR.jmart_android.request.RequestFactory;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FilterFragment extends Fragment {

    /**
     * @description
     * Bagian atas disini merupakan inisiasi variabel dan juga
     * mengassign id dari XML ke frontend
     */

    private static final Gson gson = new Gson();
    public static ArrayList<Product> productsList = new ArrayList<>();
    public static int status = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View productView = inflater.inflate(R.layout.fragment_filter, container, false);
        EditText name = productView.findViewById(R.id.nameInputFilter);
        EditText lowestPrice = productView.findViewById(R.id.lowestInputFilter);
        EditText highestPrice = productView.findViewById(R.id.highestInputFilter);
        CheckBox newCheck = productView.findViewById(R.id.newCheckFilter);
        CheckBox usedCheck = productView.findViewById(R.id.usedCheckFilter);
        Spinner category = productView.findViewById(R.id.categorySpinnerFilter);
        Button apply = productView.findViewById(R.id.ApplyFilter);
        Button clear = productView.findViewById(R.id.ClearFilter);

        /**
         * @description
         * Checkbox newCheck dan usedCheck akan saling mengset value lawannya sebagai false
         * Button clear digunakan untuk mengreset list produk ke default
         * Button apply akan memfilter list produk di FragmentProduct
         */

        newCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    usedCheck.setChecked(false);
                }
            }
        });

        usedCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    newCheck.setChecked(false);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = 0;
                getActivity().finish();
                getActivity().overridePendingTransition(0,0);
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Apply Filter!",Toast.LENGTH_SHORT).show();
                Response.Listener<String> listenerFiltered = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray object = new JSONArray(response);
                            if(object != null){
                                productsList = gson.fromJson(object.toString(),new TypeToken<ArrayList<Product>>(){}.getType());
                                System.out.println(productsList);
                                Toast.makeText(getActivity(),"Filtered",Toast.LENGTH_SHORT).show();
                                status = 1;
                            }else{
                                Toast.makeText(getActivity(),"No Data!",Toast.LENGTH_SHORT).show();
                            }
                            getActivity().finish();
                            getActivity().overridePendingTransition(0,0);
                            getActivity().startActivity(getActivity().getIntent());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                requestQueue.add(RequestFactory.getProduct(ProductFragment.page,ProductFragment.pageSize,name.getText().toString(),lowestPrice.getText().toString(),highestPrice.getText().toString(),category.getSelectedItem().toString(),String.valueOf(usedCheck.isChecked()),listenerFiltered,null));
            }
        });

        return productView;
    }
}