package com.example.product.Dashboard;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Adapter.ProductAdapter;
import com.example.product.Constant.ApiServices;
import com.example.product.Constant.IUrls;
import com.example.product.Objects.Product;
import com.example.product.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductFragment extends Fragment {


    private ArrayList<Product> productArrayList = new ArrayList<Product>();
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    View view;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recycler, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_search);
        progressBar = view.findViewById(R.id.progress_bar_product);


        productAdapter = new ProductAdapter(productArrayList);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        fetchProduct();

        return view;
    }


    public void fetchProduct() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.purple_500), PorterDuff.Mode.SRC_IN);

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


}