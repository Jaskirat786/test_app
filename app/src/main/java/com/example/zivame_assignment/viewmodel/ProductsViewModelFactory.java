package com.example.zivame_assignment.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.zivame_assignment.database.ProductDao;
import com.example.zivame_assignment.util.Injection;

import java.util.concurrent.ExecutorService;

public class ProductsViewModelFactory implements ViewModelProvider.Factory {

    private final ProductDao productDao;
    private final ExecutorService executorService;

    public ProductsViewModelFactory(
            Context context
    ) {
        this.productDao = Injection.obtainProductsDao(context);
        this.executorService = Injection.obtainExecutorService(context);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        try {
            return modelClass.getConstructor(
                    ProductDao.class,
                    ExecutorService.class
            ).newInstance( productDao, executorService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to instantiate View Model for " + modelClass);
        }
    }
}

