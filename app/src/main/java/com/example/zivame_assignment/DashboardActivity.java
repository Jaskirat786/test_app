package com.example.zivame_assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zivame_assignment.adapter.PrdouctRecyclerAdapter;
import com.example.zivame_assignment.adapter.ProductsAdapterListener;
import com.example.zivame_assignment.databinding.ActivityDashboardBinding;
import com.example.zivame_assignment.model.Products;
import com.example.zivame_assignment.viewmodel.DashboardViewmodel;

import java.util.ArrayList;
import java.util.List;


public class DashboardActivity extends AppCompatActivity implements ProductsAdapterListener {

    private ActivityDashboardBinding binding;
    private DashboardViewmodel viewModel;
    private List<Products> productsList;
    private PrdouctRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new PrdouctRecyclerAdapter(productsList, this, this);

        viewModel = new ViewModelProvider(this).get(DashboardViewmodel.class);

        viewModel.getAllProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                binding.recyclerView.setAdapter(adapter);
                adapter.setProductList(products);
            }
        });
        viewModel.callAPI();
    }

    @Override
    public void onItemClicked(Products products) {

        Intent i = new Intent(this, CartActivity.class);
        i.putExtra("extra_product_id",products.getId());
        startActivity(i);

    }
}
