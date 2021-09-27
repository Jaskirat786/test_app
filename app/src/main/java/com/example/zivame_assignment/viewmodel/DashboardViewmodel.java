package com.example.zivame_assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zivame_assignment.Network.APIService;
import com.example.zivame_assignment.Network.RetrofitInstance;
import com.example.zivame_assignment.model.ProductModel;
import com.example.zivame_assignment.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewmodel extends ViewModel {

    private final MutableLiveData<List<Products>> productList;

    public DashboardViewmodel() {
        productList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Products>> getProductListObserver() {
        return productList;
    }

    public void callAPI() {

        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<ProductModel> call = apiService.getProductList();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                productList.postValue(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                productList.postValue(null);

            }
        });
    }
}
