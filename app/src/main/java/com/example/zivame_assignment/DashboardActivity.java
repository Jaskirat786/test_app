package com.example.zivame_assignment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zivame_assignment.adapter.PrdouctRecyclerAdapter;
import com.example.zivame_assignment.databinding.ActivityDashboardBinding;
import com.example.zivame_assignment.model.Products;
import com.example.zivame_assignment.viewmodel.DashboardViewmodel;

import java.util.List;


public class DashboardActivity extends AppCompatActivity {

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

        adapter = new PrdouctRecyclerAdapter(productsList, this);
        binding.recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(DashboardViewmodel.class);

        viewModel.getProductListObserver().observe(this, new Observer<List<Products>>() {

            @Override
            public void onChanged(List<Products> products) {

                if (products != null) {
                    productsList = products;
                    adapter.setProductList(products);
                    binding.errorText.setVisibility(View.GONE);
                } else {
                    binding.errorText.setVisibility(View.VISIBLE);
                }

            }
        });
        viewModel.callAPI();


    }
}
