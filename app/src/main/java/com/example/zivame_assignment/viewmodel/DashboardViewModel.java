package com.example.zivame_assignment.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.zivame_assignment.Network.APIService;
import com.example.zivame_assignment.Network.RetrofitInstance;
import com.example.zivame_assignment.database.ProductDao;
import com.example.zivame_assignment.model.ProductModel;
import com.example.zivame_assignment.model.Products;

import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    private final ProductDao productDao;
    private final ExecutorService executorService;

    public DashboardViewModel(ProductDao productDao, ExecutorService executorService) {
        this.productDao = productDao;
        this.executorService = executorService;
    }

    public void callAPI() {

        executorService.execute(() -> {

            //fetching all the products from data base object
            List<Products> product =  productDao.getAllProducts();
            if (product != null) {
                productsLiveData.postValue(product);
                return;
            }
        });

        //fetching data from api service
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<ProductModel> call = apiService.getProductList();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                if (response.isSuccessful()) {
                    productDao.insert(response.body().getProducts());
                }
            }
            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                productList.postValue(null);
            }
        });
    }


    //Live Data

    private final MutableLiveData<List<Products>> productsLiveData = new MutableLiveData<>();

    public LiveData<List<Products>> products() { return productsLiveData; }

    private final MutableLiveData<List<Products>> productList = new MutableLiveData<>();


}
