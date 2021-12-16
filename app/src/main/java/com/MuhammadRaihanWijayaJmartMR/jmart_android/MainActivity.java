package com.MuhammadRaihanWijayaJmartMR.jmart_android;

/**
 * The class MainActivity extends AppCompatActivity
 * @author Raihan Wijaya
 * @description Disini merupakan MainActivity yang berisi menu utama dan VPAdapter untuk pindah fragment
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.MuhammadRaihanWijayaJmartMR.jmart_android.model.Account;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    /**
     * @description
     * Dalam activity ini, digunakan VPAdapter untuk menampilkan fragment
     * Di inisiasikan juga top menu atau bar bagian atas yang berisi search_button, create_button, history_button dan juga account_button
     * Setiap button yang ada di top menu mempunyai fungsi masing masing
     */

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ProductFragment(), "PRODUCTS");
        vpAdapter.addFragment(new FilterFragment(), "FILTER");
        viewPager.setAdapter(vpAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        MenuItem addButton = menu.findItem(R.id.add_button);
        addButton.setVisible(LoginActivity.getLoggedAccount().store != null);
        return true;
    }

    /**
     * @description
     * search_button digunakan untuk search
     * add_button digunakan untuk menambahkan produk baru dan pindah ke CreateProductActivity
     * account_button digunakan untuk melihat info akun dan pindah ke AboutMeActivity
     * history_button digunakan untuk melihat history pembayaran dan pindah ke PersonalHistory
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_button) {
            Toast.makeText(this, "Search Selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.add_button) {
            Toast.makeText(this, "Add Product Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, CreateProductActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.account_button) {
            Toast.makeText(this, "Account Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.history_button) {
            Toast.makeText(this, "History Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, PersonalHistory.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}