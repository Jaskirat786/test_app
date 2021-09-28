package com.example.zivame_assignment.Network;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.zivame_assignment.database.ProductDao;
import com.example.zivame_assignment.database.ProductDatabase;
import com.example.zivame_assignment.model.Products;

import java.util.List;

public class ProductRepository {

    private ProductDatabase productDatabase;
    private LiveData<List<Products>> getProducts;

    public ProductRepository(Application application) {

        productDatabase = ProductDatabase.getInstance(application);
        getProducts = productDatabase.getProductDao().getAllProducts();
    }

    public void insert(List<Products> productsList) {
        new InsertAsyncTask(productDatabase).execute(productsList);
    }


    public LiveData<List<Products>> getProducts() {
        return getProducts;
    }


    static class InsertAsyncTask extends AsyncTask<List<Products>, Void, Void> {

        private ProductDao productDao;

        InsertAsyncTask(ProductDatabase productDatabase) {
            productDao = productDatabase.getProductDao();
        }

        @Override
        protected Void doInBackground(List<Products>... lists) {
            productDao.insert(lists[0]);
            return null;
        }
    }
}