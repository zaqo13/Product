package com.example.product.Dashboard;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Adapter.ProductAdapter;
import com.example.product.Constant.ApiServices;
import com.example.product.Constant.IUrls;
import com.example.product.Objects.Product;
import com.example.product.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends MenuForAll implements ProductAdapter.OnProductClickListener {

    private ArrayList<Product> productArrayList = new ArrayList<Product>();
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    public static Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    ProgressBar progressBar;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed(); // Handle the back navigation
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_product);

        recyclerView = findViewById(R.id.recycler_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        progressBar = findViewById(R.id.progress_bar_product_activity);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // To set the back button on top action bar
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        productAdapter = new ProductAdapter(productArrayList);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        productAdapter.setOnProductClickListener(this);


        // To set the title of app bar on the changing value of recyclerview content
        List<Product> productList = productAdapter.getProductList();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            /**
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                    String category = productList.get(firstVisibleItemPosition).getCategory_name();
                    ProductActivity.toolbar.setTitle(category);
                }
            }
        });

        fetchProduct();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home_product) {
                    Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_profile_product) {
                    Intent intent_product = new Intent(ProductActivity.this, ProductActivity.class);
                    startActivity(intent_product);
                }
                return false;
            }
        });
    }


    public void fetchProduct() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.purple_500), PorterDuff.Mode.SRC_IN);

        ApiServices api = IUrls.getRetrofitClient(IUrls.BASE_URL).create(ApiServices.class);
        Call<ResponseBody> result = api.getProduct(
                "1",
                "",
                "",
                "",
                "",
                "",
                0
        );

        result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            if (response.isSuccessful()) {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                JSONArray jsonArrayProductList = jsonObject.getJSONArray("products_list");
                                for (int i = 0; i < jsonArrayProductList.length(); i++) {
                                    JSONObject jsonObject1 = jsonArrayProductList.getJSONObject(i);
                                    productArrayList.add(new Product(jsonObject1));
                                }

                                productAdapter.notifyDataSetChanged();

                            } else {
                                Log.d("productApi", "API request failed!");
                            }
                        } catch (JSONException | IOException e) {
                            Log.e("productApi", "Exception: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("on_failure", "API request failed: " + t.getMessage());
                        t.printStackTrace();

                    }
                });


    }


    @Override
    public void onProductClick(int position) {
        // Changing the DisplayName and CategoryName on product click
        productArrayList.get(position).setDisplay_name("New Display Name");
        productArrayList.get(position).setCategory_name("New Category Name");

        productAdapter.notifyDataSetChanged();

    }
}


