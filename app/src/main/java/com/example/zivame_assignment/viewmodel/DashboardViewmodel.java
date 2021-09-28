package com.example.zivame_assignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zivame_assignment.Network.APIService;
import com.example.zivame_assignment.Network.ProductRepository;
import com.example.zivame_assignment.Network.RetrofitInstance;
import com.example.zivame_assignment.database.ProductDao;
import com.example.zivame_assignment.database.ProductDatabase;
import com.example.zivame_assignment.model.ProductModel;
import com.example.zivame_assignment.model.Products;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewmodel extends AndroidViewModel {

    private ProductRepository repository;
    private LiveData<List<Products>> getAllProducts;

    public DashboardViewmodel(@NonNull Application application) {
        super(application);

        repository = new ProductRepository(application);
        getAllProducts = repository.getProducts();
    }

    public void insert(List<Products> list) {
        repository.insert(list);
    }

    public LiveData<List<Products>> getAllProducts() {
        return getAllProducts;
    }

    private final MutableLiveData<Products> productsData = new MutableLiveData<>();

    public MutableLiveData<Products> products() {
        return productsData;
    }

    private final MutableLiveData<List<Products>> productList = new MutableLiveData<>();

    public void callAPI() {

        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<ProductModel> call = apiService.getProductList();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                if (response.isSuccessful()) {
                    repository.insert(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                productList.postValue(null);
            }
        });
    }
}
