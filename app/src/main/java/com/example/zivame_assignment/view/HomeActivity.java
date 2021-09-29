package com.example.zivame_assignment.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zivame_assignment.adapter.PrdouctRecyclerAdapter;
import com.example.zivame_assignment.adapter.ProductsAdapterListener;
import com.example.zivame_assignment.databinding.ActivityHomeBinding;
import com.example.zivame_assignment.model.Products;
import com.example.zivame_assignment.viewmodel.DashboardViewModel;
import com.example.zivame_assignment.viewmodel.ProductsViewModelFactory;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements ProductsAdapterListener {

    private ActivityHomeBinding binding;
    private DashboardViewModel viewModel;
    private List<Products> productsList;
    private PrdouctRecyclerAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding activity with xml layout
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding recycler view to layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);

        //initialization of adapter
        adapter = new PrdouctRecyclerAdapter(productsList, this, this);

        //Initialization of  model factory
        ProductsViewModelFactory factory = new ProductsViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(DashboardViewModel.class);

        viewModel.products().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                //adding data to recycler view
                binding.recyclerView.setAdapter(adapter);
                adapter.setProductList(products);
            }
        });
        //Calling API from viewModel
        viewModel.callAPI();
    }

    @Override
    public void onItemClicked(Products products) {

        //showing progress bar
        binding.bar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.bar.setVisibility(View.GONE);
                Intent i = new Intent(HomeActivity.this, CartActivity.class);
                i.putExtra("extra_product_id", products.getId());
                startActivity(i);
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        binding.bar.setVisibility(View.GONE);
        super.onBackPressed();
    }

}
