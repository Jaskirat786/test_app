package com.example.zivame_assignment.Network;

import com.example.zivame_assignment.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("db")
    Call<ProductModel>getProductList();
}
