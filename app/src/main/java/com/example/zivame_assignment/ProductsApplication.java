package com.example.zivame_assignment;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductsApplication extends Application {

    private ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();

        executorService = Executors.newFixedThreadPool(4);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
