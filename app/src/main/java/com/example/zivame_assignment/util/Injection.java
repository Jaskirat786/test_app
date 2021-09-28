package com.example.zivame_assignment.util;

import android.content.Context;

import com.example.zivame_assignment.ProductsApplication;
import com.example.zivame_assignment.database.ProductDao;
import com.example.zivame_assignment.database.ProductDatabase;

import java.util.concurrent.ExecutorService;

public class Injection {

    public static ProductDao obtainProductsDao(Context context) {
        return ProductDatabase.getInstance(context.getApplicationContext()).getProductDao();
    }

    public static ExecutorService obtainExecutorService(Context context) {
        return ((ProductsApplication) context.getApplicationContext()).getExecutorService();
    }
}
