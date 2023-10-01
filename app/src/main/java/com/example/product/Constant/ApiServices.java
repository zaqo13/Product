package com.example.product.Constant;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("app_product_list")
    Call<ResponseBody> getProduct(
            @Field("user_id") String userId,
            @Field("product_id") String product_id,
            @Field("category_id") String category_id,
            @Field("brand_id") String brand_id,
            @Field("variant_id") String variant_id,
            @Field("search") String search,
            @Field("page_no") int page_no
    );


}
